package com.example.androidlab3
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {
    private val _galleryState = MutableStateFlow(GalleryState())
    val galleryState: StateFlow<GalleryState> = _galleryState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _galleryState.value =GalleryState(WebClient.client.getPublicPhotos())
            } catch (e: Exception) {
                Log.d("BBBB", e.toString())
                e.printStackTrace()
            }
        }
    }
}