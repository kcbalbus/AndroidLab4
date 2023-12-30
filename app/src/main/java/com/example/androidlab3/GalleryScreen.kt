package com.example.androidlab3

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TagSearch({galleryViewModel.fetchPhotos(it)
                        galleryViewModel.updateCurrentSearch(it)})
            Text(
                text = if(galleryState.currentSearch=="") "Najnowsze zdjęcia" else "Wyniki wyszukiwania dla: " + galleryState.currentSearch,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .padding(bottom = 8.dp),
            )
            PhotosGrid(
                photos = galleryState.photos,
                {galleryViewModel.onPhotoChosen(it)},
                onPhotoChosenNavigate
            )
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TagSearch(onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("Search photos") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = androidx.compose.ui.text.input.ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(text)
                    text = ""
                    keyboardController?.hide()
                    focusManager.clearFocus()

                }
            ),
            leadingIcon = {
                IconButton(
                    onClick = {
                        onSearch(text)
                        text = ""
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    text = ""
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "close icon"
                    )
                }
            }
        )
    }
}


@Composable
fun PhotosGrid(photos: List<Photo>, onPhotoChosen:(Photo)->Unit, onPhotoChosenNavigate: () -> Unit) {
    Log.d("HELP", photos.toString())
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
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