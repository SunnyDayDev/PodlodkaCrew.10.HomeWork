import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.ui.window.Window
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        Window("Podlodka Demo") {
            Column {
                Text("Test")
                MainView()
            }
        }
    }
}