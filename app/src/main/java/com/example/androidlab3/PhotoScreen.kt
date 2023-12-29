package com.example.androidlab3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun PhotoScreen(galleryViewModel: GalleryViewModel, galleryState: GalleryState){

    val displayed_photo = galleryState.currentPhoto

    if (displayed_photo!=null) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = "Tytuł: " + displayed_photo.title,
            )
            Text(
                text = "Autor: " + displayed_photo.author
            )
            Text(
                text = "Opublikowane: " + displayed_photo.published
            )
            AsyncImage(
                model = displayed_photo.media.m,
                contentDescription = "photo",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            Text(
                text = "Tagi: " + displayed_photo.tags
            )
            Text(
                text = "Link: " + displayed_photo.link
            )

            //TODO better looks
        }
    }

}