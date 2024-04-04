package com.ekotech.poketech.repo.module

import com.ekotech.poketech.repo.AllPokemonRepo
import com.ekotech.poketech.repo.PokemonDetailRepo
import com.ekotech.poketech.repo.ProvideAllPokemon
import com.ekotech.poketech.repo.ProvidePokemonDetail
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AllPokemonModule {

    @Singleton
    @Binds
    abstract fun bindsAllPokemonRepository(repo: AllPokemonRepo): ProvideAllPokemon

    @Singleton
    @Binds
    abstract fun bindsPokemonDetailRepository(repo: PokemonDetailRepo): ProvidePokemonDetail

}