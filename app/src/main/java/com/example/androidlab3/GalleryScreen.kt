package com.example.androidlab3

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import kotlin.math.log


@Composable
fun GalleryScreen(galleryViewModel: GalleryViewModel, galleryState: GalleryState){

    PhotosGrid(photos = galleryState.photos)

}

@Composable
fun PhotosGrid(photos: List<Photo>) {
    Log.d("HELP", photos.toString())
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
    Log.d("PhotoCard", "Image URL: ${photo.media.m}")
    AsyncImage(
        model = photo.media.m,
        contentDescription = "photo",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f) // Set the aspect ratio for a square image, adjust as needed
    )

}