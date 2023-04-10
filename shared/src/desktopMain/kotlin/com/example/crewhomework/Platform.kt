package com.example.crewhomework

class DesktopPlatform : Platform {
    override val name: String = "Desktop Version!"
}

actual fun getPlatform(): Platform = DesktopPlatform()