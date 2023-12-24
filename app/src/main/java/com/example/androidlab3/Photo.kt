package com.example.androidlab3

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "entry")
data class Photo (
    @field:XmlElement(name = "title")
    val title: String,
    @field:XmlElement(name = "link")
    val link: String,
    @field:XmlElement(name = "enclosure")
    val url: String,
    @field:XmlElement(name = "author")
    val author: String,
    @field:XmlElement(name = "published")
    val published: String
)