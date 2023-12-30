package com.example.androidlab3
import android.icu.text.StringSearch
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
        fetchPhotos("")
    }

    fun fetchPhotos(tag: String) {
        viewModelScope.launch {
            try {
                val response = WebClient.client.getTagPhotos(tag)
                _galleryState.update { currentState ->
                    currentState.copy(
                        photos = response.items
                    )
                }
            } catch (e: Exception) {
                Log.d("GalleryViewModel", "Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun onPhotoChosen(photo: Photo){
        fixChosenPhotoFormat(photo)
        _galleryState.update { currentState ->
            currentState.copy(
                currentPhoto = photo
            )
        }
    }

    fun fixChosenPhotoFormat(photo: Photo){
        photo.author = fixAuthorFormat(photo.author)
        photo.published = fixDatetimeFormat(photo.published)

    }

    fun fixAuthorFormat(author: String): String {
        val regex = Regex("\"([^\"]+)\"")
        val matchResult = regex.find(author)
        val extractedAuthor = matchResult?.groupValues?.get(1)

        if (extractedAuthor != null) {
            return extractedAuthor
        } else {
            return author
        }
    }


    fun fixDatetimeFormat(datetime: String): String {
        val regex = Regex("[TZ]")
        val datetimeFixed = regex.replace(datetime, " ")

        return datetimeFixed
    }

    fun updateCurrentSearch (search: String) {
        _galleryState.update { currentState ->
            currentState.copy(
                currentSearch = search
            )
        }
    }
}