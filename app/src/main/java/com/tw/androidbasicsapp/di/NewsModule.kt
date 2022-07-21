package com.tw.androidbasicsapp.di

import com.tw.androidbasicsapp.repositories.FeedRepository
import com.tw.androidbasicsapp.viewmodels.NewsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsModule = module {
    single { FeedRepository() }
    viewModel {NewsListViewModel(get())}
}