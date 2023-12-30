package com.example.androidlab3

data class GalleryState(
    var photos: List<Photo> = emptyList(),
    val currentPhoto: Photo? = null,
    val currentSearch: String = ""
)