package com.orlandev.viajandoui.ui.screens.home

import androidx.lifecycle.ViewModel
import com.orlandev.viajandoui.data.source.remote.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val listOfNews =  newsRepository.getNews()

    fun haveAlert(): Boolean {
        //Simula una alerta
        return Random.nextInt(0, 3) == 2
    }

}