import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.crewhomework.App
import com.example.crewhomework.design.theme.CrewHomeWorkTheme

@Composable
fun MainView() {
    CrewHomeWorkTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //App()
            Column {
                Text("Some text")
                App()
            }
        }
    }
}