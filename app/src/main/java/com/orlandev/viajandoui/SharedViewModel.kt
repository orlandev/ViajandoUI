package com.orlandev.viajandoui

import androidx.lifecycle.ViewModel
import com.orlandev.viajandoui.model.SearchTravelModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    var travelData = MutableStateFlow<SearchTravelModel?>(null)
        private set

    fun onSetTravelData(newTravel: SearchTravelModel) {
        travelData.value = newTravel
    }

}

