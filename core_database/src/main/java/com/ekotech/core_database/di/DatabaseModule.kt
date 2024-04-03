package com.ekotech.core_database.di

import android.content.Context
import androidx.room.Room
import com.ekotech.core_database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ) = Room
        .databaseBuilder(
            app,
            PokemonDatabase::class.java,
            DB_NAME
        )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providePokemonDao(db: PokemonDatabase) = db.pokemonDao()

    private const val DB_NAME = "POKEMON_DB"
}