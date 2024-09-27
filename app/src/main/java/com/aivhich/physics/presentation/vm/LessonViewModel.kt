package com.aivhich.physics.presentation.vm

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aivhich.passport.common.Event
import com.aivhich.passport.common.UiText
import com.aivhich.physics.common.AnswerCheck
import com.aivhich.physics.common.Result
import com.aivhich.physics.common.UIStatus
import com.aivhich.physics.data.remote.dto.InfoType
import com.aivhich.physics.data.remote.dto.LessonDto
import com.aivhich.physics.data.remote.dto.LevelDto
import com.aivhich.physics.data.remote.dto.PageDto
import com.aivhich.physics.data.remote.dto.UpdateProgress
import com.aivhich.physics.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    private val _pageIdx = MutableLiveData<Int>(0)
    val pageIdx: LiveData<Int> get() = _pageIdx

    private val statusMessage = Channel<Event<UiText>>()
    val message = statusMessage.receiveAsFlow()

    private val _isResult = MutableLiveData<Boolean>(false)
    val isResult: LiveData<Boolean> get() = _isResult

    private var updateProgress: UpdateProgress = UpdateProgress(listOf(), listOf())

    var points:Long = 0L;

    private val _lesson = MutableLiveData<LessonDto>()
    val lesson: LiveData<LessonDto> get() = _lesson
    private fun setLesson(lessonDto: LessonDto) {
        _lesson.value = lessonDto
    }

    private val _isCorrectAnswer = MutableLiveData<AnswerCheck>(AnswerCheck.NOT_CHECKED)
    val isCorrectAnswer: LiveData<AnswerCheck> get() = _isCorrectAnswer
    private fun setIsCorrectAnswer(answer: AnswerCheck) {
        _isCorrectAnswer.value = answer
    }

    private fun setPage(index: Int) {
        _pageIdx.value = index
    }


    fun nextPage() {
        if ((lesson.value?.pages?.size?.minus(1) ?: 0) > pageIdx.value!!) {
            setPage(pageIdx.value!!.plus(1))

            _answers.value = listOf()
            setIsCorrectAnswer(AnswerCheck.NOT_CHECKED)
        } else {
            viewModelScope.launch {
                when(val out = userUseCase.updateProgressUseCase(updateProgress)){
                    is Result.Success->{
                        points = out.data
                        _isResult.value = true
                        setIsCorrectAnswer(AnswerCheck.IS_CORRECT)
                    }
                    is Result.Error->{ }
                }
            }
        }
    }

    fun previousPage() {
        if (pageIdx.value!! > 0) {
            setPage(pageIdx.value!!.minus(1))
            _answers.value = listOf()
        }
    }


    private val _answers = MutableLiveData<List<String>>()
    val answers: LiveData<List<String>> get() = _answers
    fun updateAnswers(answer: String) {
        if (answers.value?.contains(answer)!!) {
            _answers.value = answers.value?.filter { it -> it != answer }
        } else {
            _answers.value = answers.value?.plus(answer)
        }
    }

    fun setAnswers(answer: String) {
        _answers.value = listOf(answer)
    }

    fun init(id: UUID) {
        viewModelScope.launch {
            when (val out = userUseCase.getOneLesson(id = id)) {
                is Result.Success -> {
                    out.data.pages = out.data.pages.sortedBy { it.stepId }
                    setLesson(out.data)
                }

                is Result.Error -> {}
            }
        }
    }

    fun check() {
        viewModelScope.launch {
            if (lesson.value?.pages?.get(pageIdx.value!!)?.infoType == InfoType.TASK) {
                if(answers.value?.isNotEmpty()==true) {
                    if (answers.value!!.containsAll(
                            lesson.value?.pages?.get(
                                pageIdx.value ?: 0
                            )?.form?.rightAnswers!!
                        )
                    ) {
                        setIsCorrectAnswer(AnswerCheck.IS_CORRECT)

                        updateProgress.passedPages =
                            updateProgress.passedPages
                                .toSet()
                                .plus(lesson.value?.pages?.get(pageIdx.value!!)!!.id)
                                .toList()
                        updateProgress.rightForms =
                            updateProgress.rightForms
                                .toSet()
                                .plus(lesson.value?.pages?.get(pageIdx.value!!)!!.form.id)
                                .toList()
                        statusMessage.send(
                            Event(UiText.DynamicString("Верно!"))
                        )
                    } else {
                        setIsCorrectAnswer(AnswerCheck.NOT_CORRECT)
                        statusMessage.send(
                            Event(
                                UiText.DynamicString(
                                    "Неверно, правильный ответ: \n"
                                            + lesson.value?.pages?.get(
                                        pageIdx.value ?: 0
                                    )?.form?.rightAnswers?.joinToString(", ")
                                )
                            )
                        )
                    }
                }else{
                    statusMessage.send(
                        Event(
                            UiText.DynamicString(
                                "Выберите вариант ответа"
                            )
                        )
                    )
                }
            } else {
                updateProgress.passedPages =
                    updateProgress.passedPages
                        .toSet()
                        .plus(lesson.value?.pages?.get(pageIdx.value!!)!!.id)
                        .toList()
                if (isResult.value == false) {
                    nextPage()
                    setIsCorrectAnswer(AnswerCheck.NOT_CHECKED)
                }
            }
        }
    }
}