package com.test.recyclerview.data.models

data class Video(
    val channelId: String,
    val channelName: String,
    val lengthText: String,
    val publishedTimeText: String,
    val thumbnails: List<Thumbnail>,
    val title: String,
    val videoId: String,
    val viewCountText: String
)