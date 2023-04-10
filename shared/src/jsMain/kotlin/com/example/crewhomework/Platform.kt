package com.example.crewhomework

actual fun getPlatform(): Platform = object : Platform {
    override val name: String = "Web version"
}