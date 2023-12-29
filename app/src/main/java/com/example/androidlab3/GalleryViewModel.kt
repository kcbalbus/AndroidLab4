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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {
    private val _galleryState = MutableStateFlow(GalleryState())
    val galleryState: StateFlow<GalleryState> = _galleryState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val response = WebClient.client.getPublicPhotos()
                _galleryState.value = GalleryState(response.items)
                Log.d("GalleryViewModel", "Number of photos: ${_galleryState.value.photos.size}")
            } catch (e: Exception) {
                Log.d("GalleryViewModel", "Error: ${e.message}")
                e.printStackTrace()
            }
        }

    }

    fun onPhotoChosen(photo: Photo){
        _galleryState.update { currentState ->
            currentState.copy(
                currentPhoto = photo
            )
        }

    }

    //TODO fix response format
}