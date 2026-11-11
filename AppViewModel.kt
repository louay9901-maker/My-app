package com.wansawi.app.ui

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.wansawi.app.data.Category
import com.wansawi.app.data.FavoritesRepository
import com.wansawi.app.data.SettingsRepository
import com.wansawi.app.data.Suggestion
import com.wansawi.app.data.SuggestionData

sealed interface Screen {
    data object Home : Screen
    data object Result : Screen
    data object Favorites : Screen
    data object Settings : Screen
}

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val favoritesRepo = FavoritesRepository(application)
    private val settingsRepo = SettingsRepository(application)

    var screen by mutableStateOf<Screen>(Screen.Home)
        private set

    var currentCategory by mutableStateOf<Category?>(null)
        private set

    var currentSuggestion by mutableStateOf<Suggestion?>(null)
        private set

    var favorites by mutableStateOf(favoritesRepo.getAll())
        private set

    var isDarkMode by mutableStateOf(settingsRepo.isDarkMode())

    // تتبع الاقتراحات الأخيرة لكل قسم لتجنب التكرار
    private val recentByCategory = mutableMapOf<Category, ArrayDeque<Int>>()

    fun goHome() { screen = Screen.Home }

    fun openFavorites() { screen = Screen.Favorites }

    fun openSettings() { screen = Screen.Settings }

    fun openCategory(category: Category) {
        currentCategory = category
        currentSuggestion = pickSuggestion(category)
        screen = Screen.Result
    }

    fun nextSuggestion() {
        val category = currentCategory ?: return
        currentSuggestion = pickSuggestion(category)
    }

    fun toggleFavorite() {
        val suggestion = currentSuggestion ?: return
        if (favoritesRepo.isFavorite(suggestion.id)) {
            favoritesRepo.remove(suggestion.id)
        } else {
            favoritesRepo.add(suggestion)
        }
        favorites = favoritesRepo.getAll()
    }

    fun isCurrentFavorite(): Boolean =
        currentSuggestion?.let { favoritesRepo.isFavorite(it.id) } ?: false

    fun removeFavorite(id: String) {
        favoritesRepo.remove(id)
        favorites = favoritesRepo.getAll()
    }

    fun clearFavorites() {
        favoritesRepo.clear()
        favorites = emptyList()
    }

    fun setDarkMode(enabled: Boolean) {
        isDarkMode = enabled
        settingsRepo.setDarkMode(enabled)
    }

    private fun pickSuggestion(category: Category): Suggestion {
        val list = SuggestionData.forCategory(category)
        val recent = recentByCategory.getOrPut(category) { ArrayDeque() }
        val available = list.indices.filter { it !in recent }
        val pool = if (available.isEmpty()) list.indices.toList() else available
        val index = pool.random()
        recent.addLast(index)
        // نحتفظ بآخر ثلث القائمة كحد أقصى لضمان التنوع
        val maxRecent = (list.size / 3).coerceAtLeast(1)
        while (recent.size > maxRecent) recent.removeFirst()
        return list[index]
    }
}
