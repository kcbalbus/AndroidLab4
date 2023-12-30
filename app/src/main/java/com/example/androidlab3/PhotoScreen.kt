package com.example.androidlab3

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PhotoScreen(galleryViewModel: GalleryViewModel, galleryState: GalleryState){

    val displayed_photo = galleryState.currentPhoto

    if (displayed_photo!=null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            Text(
                text = if (displayed_photo.title!=" ") displayed_photo.title else "-",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = "Autor: " + displayed_photo.author,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "Opublikowane: " + displayed_photo.published,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            AsyncImage(
                model = displayed_photo.media.m,
                contentDescription = "photo",
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color.Black)
            )
            Text(
                text = "Link: " + displayed_photo.link,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Tagi: " + displayed_photo.tags,
                style = MaterialTheme.typography.bodyLarge,
            )

        }
    }

}