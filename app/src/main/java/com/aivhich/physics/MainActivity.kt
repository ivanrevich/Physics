package com.aivhich.physics

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aivhich.passport.AuthActivity
import com.aivhich.physics.ui.theme.PhysicsTheme
import com.aivhich.zaryaui.HeadlineText


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        setContent {
            PhysicsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ///navigation
                }
            }
        }
    }
}