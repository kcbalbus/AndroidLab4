package com.example.androidlab3

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

data class FlickrResponse(
    val title: String,
    val link: String,
    val description: String,
    val modified: String,
    val generator: String,
    val items: List<Photo>
)

data class Photo(
    val title: String,
    val link: String,
    val media: Media,
    val dateTaken: String,
    val description: String,
    var published: String,
    var author: String,
    val authorId: String,
    val tags: String
)

data class Media(val m: String)