package com.wansawi.app.data

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONObject

class FavoritesRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("wansawi_favorites", Context.MODE_PRIVATE)

    fun getAll(): List<Suggestion> {
        val raw = prefs.getString(KEY_ITEMS, "[]") ?: "[]"
        val array = JSONArray(raw)
        val result = mutableListOf<Suggestion>()
        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            result.add(
                Suggestion(
                    id = obj.getString("id"),
                    title = obj.getString("title"),
                    description = obj.getString("description")
                )
            )
        }
        return result
    }

    fun add(suggestion: Suggestion) {
        if (isFavorite(suggestion.id)) return
        val all = getAll().toMutableList()
        all.add(0, suggestion)
        save(all)
    }

    fun remove(id: String) {
        save(getAll().filterNot { it.id == id })
    }

    fun clear() {
        save(emptyList())
    }

    fun isFavorite(id: String): Boolean =
        getAll().any { it.id == id }

    private fun save(items: List<Suggestion>) {
        val array = JSONArray()
        items.forEach { s ->
            array.put(
                JSONObject()
                    .put("id", s.id)
                    .put("title", s.title)
                    .put("description", s.description)
            )
        }
        prefs.edit().putString(KEY_ITEMS, array.toString()).apply()
    }

    companion object {
        private const val KEY_ITEMS = "items"
    }
}
