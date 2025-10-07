package com.example.midterm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.midterm.ui.theme.MidtermTheme
import com.example.midterm.ui.viewmodel.ImageExplorerUiState
import com.example.midterm.ui.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Hilt requirement for DI in an Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MidtermTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ImageExplorerScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ImageExplorerScreen(
    modifier: Modifier = Modifier,
    viewModel: ImageViewModel = hiltViewModel() // Inject the ViewModel
) {
    // Collect state from the ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // Pass state and event lambda to the stateless UI
    ImageExplorerContent(
        modifier = modifier,
        uiState = uiState,
        onNextClicked = { viewModel.showNextImage() }
    )
}

@Composable
fun ImageExplorerContent(
    modifier: Modifier = Modifier,
    uiState: ImageExplorerUiState,
    onNextClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Get title string from resource ID
        val imageTitle = stringResource(id = uiState.currentTitleResId)

        // 1. Image
        Image(
            painter = painterResource(id = uiState.currentImageResId),
            contentDescription = imageTitle, // Accessibility requirement
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f), // Keeps a reasonable aspect ratio
            contentScale = ContentScale.Crop // Cropped fit requirement
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Text Title
        Text(
            text = imageTitle,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 3. Button
        Button(onClick = onNextClicked) {
            Text(text = "Next")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ImageExplorerContentPreview() {
    MidtermTheme {
        ImageExplorerContent(
            uiState = ImageExplorerUiState(
                currentImageResId = R.drawable.compro_admission_team, // Use a placeholder for preview
                currentTitleResId = R.string.compro_admission_team
            ),
            onNextClicked = {}
        )
    }
}
