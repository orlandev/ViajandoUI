package com.orlandev.viajandoui.ui.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor( ) : ViewModel() {
 
    fun haveAlert(): Boolean {
        //Simula una alerta
        return Random.nextInt(0, 3) == 2
    }

}