package com.example.movieappmad24.models

import androidx.room.Embedded
import androidx.room.Relation

//https://developer.android.com/training/data-storage/room/relationships?hl=de#kotlin

data class MovieWithImages (
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "dbId",
        entityColumn = "movieDbId"
    )
    val movieImages: List<MovieImage>
)