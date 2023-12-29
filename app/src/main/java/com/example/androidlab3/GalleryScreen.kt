package com.example.androidlab3

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import kotlin.math.log


@Composable
fun GalleryScreen(galleryViewModel: GalleryViewModel, galleryState: GalleryState, onPhotoChosenNavigate:()->Unit){

    if (galleryState.photos.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Trwa ładowanie zdjęć...",
            )
        }
    }
    else {
        PhotosGrid(
            photos = galleryState.photos,
            {galleryViewModel.onPhotoChosen(it)},
            onPhotoChosenNavigate
        )
    }

}

@Composable
fun PhotosGrid(photos: List<Photo>, onPhotoChosen:(Photo)->Unit, onPhotoChosenNavigate: () -> Unit) {
    Log.d("HELP", photos.toString())
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        content = {
            items(photos) { photo ->
                PhotoCard(
                    photo,
                    {onPhotoChosen(it)},
                    onPhotoChosenNavigate
                )
            }
        }
    )
}

@Composable
fun PhotoCard(photo: Photo, onPhotoChosen: (Photo) -> Unit, onPhotoChosenNavigate: () -> Unit) {
    Log.d("PhotoCard", "Image URL: ${photo.media.m}")
    AsyncImage(
        model = photo.media.m,
        contentDescription = "photo",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .clickable {
                onPhotoChosen(photo)
                onPhotoChosenNavigate()
            }
    )

}