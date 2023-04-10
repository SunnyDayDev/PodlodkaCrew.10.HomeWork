package com.example.crewhomework

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform