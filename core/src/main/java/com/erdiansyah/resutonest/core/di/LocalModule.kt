package com.erdiansyah.resutonest.core.di

import android.content.Context
import androidx.room.Room
import com.erdiansyah.resutonest.core.BuildConfig
import com.erdiansyah.resutonest.core.data.source.local.db.FavoriteDao
import com.erdiansyah.resutonest.core.data.source.local.db.FavoriteDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context) : FavoriteDb {
        val passPhrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.PASSBASE.toCharArray())
        val factory = SupportFactory(passPhrase)
        return Room.databaseBuilder(
            context.applicationContext,
            FavoriteDb::class.java, "favorite.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideDao(favoriteDb: FavoriteDb) : FavoriteDao {
        return favoriteDb.favoriteDao()
    }
}