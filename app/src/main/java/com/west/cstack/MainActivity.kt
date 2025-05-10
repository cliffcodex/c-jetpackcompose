package com.west.cstack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.west.cstack.ui.theme.CStackTheme

class MainActivity : ComponentActivity() {

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("cstack") // Match with CMake target
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CStackTheme {
                Surface {
                    val message = remember { stringFromJNI() }
                    Text(text = message)
                }
            }
        }
    }
}
