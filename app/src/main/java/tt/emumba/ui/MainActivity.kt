package tt.emumba.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.koin.androidx.viewmodel.ext.android.viewModel
import tt.emumba.presentation.viewmodels.HomeViewModel
import tt.emumba.ui.screens.main.MainScreen
import tt.emumba.ui.theme.EmumbaMVVMTheme

class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            EmumbaMVVMTheme {
                MainScreen(viewModel)
            }
        }
    }
}



