package org.kenvyra.gallery

import android.app.Application
import com.google.android.material.color.DynamicColors

class GalleryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}