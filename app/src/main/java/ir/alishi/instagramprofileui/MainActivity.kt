package ir.alishi.instagramprofileui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import ir.alishi.instagramprofileui.ui.theme.InstagramProfileUiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramProfileUiTheme {
                Surface {
                    InstagramProfile( )
                }
            }
        }
    }
}