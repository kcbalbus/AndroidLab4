package com.example.androidlab3

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter


@Composable
fun GalleryScreen(){
    val galleryViewModel: GalleryViewModel = GalleryViewModel()
    val galleryState by galleryViewModel.galleryState.collectAsState()

    PhotosGrid(photos = galleryState.photos)

}

@Composable
fun PhotosGrid(photos: List<Photo>) {
    Log.d("AAAA", photos.toString())
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        content = {
            items(photos) { photo ->
                PhotoCard(photo)
            }
        }
    )
}

@Composable
fun PhotoCard(photo: Photo) {
    Image(
        painter = rememberImagePainter(data = photo.link),
        contentDescription = "photo",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}