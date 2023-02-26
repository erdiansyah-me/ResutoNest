package com.erdiansyah.favorite

import android.content.Context
import com.erdiansyah.resutonest.app.di.FavoriteModule
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModule::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)
    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun dependency(favoriteModule: FavoriteModule): Builder
        fun build(): FavoriteComponent
    }
}