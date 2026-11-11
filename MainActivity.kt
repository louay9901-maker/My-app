package com.wansawi.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.wansawi.app.ui.AppViewModel
import com.wansawi.app.ui.Screen
import com.wansawi.app.ui.screens.FavoritesScreen
import com.wansawi.app.ui.screens.HomeScreen
import com.wansawi.app.ui.screens.ResultScreen
import com.wansawi.app.ui.screens.SettingsScreen
import com.wansawi.app.ui.theme.WansawiTheme

class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WansawiTheme(darkTheme = viewModel.isDarkMode) {
                // فرض اتجاه RTL للواجهة العربية بالكامل
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Crossfade(targetState = viewModel.screen, label = "screen") { screen ->
                            when (screen) {
                                Screen.Home -> HomeScreen(viewModel)
                                Screen.Result -> ResultScreen(viewModel)
                                Screen.Favorites -> FavoritesScreen(viewModel)
                                Screen.Settings -> SettingsScreen(viewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}
