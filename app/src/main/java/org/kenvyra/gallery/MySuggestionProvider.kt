package org.kenvyra.gallery

import android.content.SearchRecentSuggestionsProvider

class MySuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY: String = "org.kenvyra.gallery.MySuggestionProvider"
        const val MODE: Int = DATABASE_MODE_QUERIES
    }
}