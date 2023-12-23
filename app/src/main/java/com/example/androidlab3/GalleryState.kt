package com.example.androidlab3

data class GalleryState(
    val photos: List<Photo> = emptyList(),
    val currentPhoto: Photo? = null
)