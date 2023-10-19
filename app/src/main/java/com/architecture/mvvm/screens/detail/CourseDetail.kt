package com.architecture.mvvm.screens.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.architecture.mvvm.ui.theme.MvvmTheme
import coil.compose.AsyncImage
import com.architecture.mvvm.ui.theme.Purple40
import org.koin.androidx.compose.koinViewModel

@Composable
fun CourseDetailRoute(
    link: String,
    viewModel: CourseDetailViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.loadDetail(link)
    }
    val state = viewModel.state.collectAsState().value
    CourseDetailScreen(state = state)
}

@Composable
fun CourseDetailScreen(state: CourseDetailState) {
    val context = LocalContext.current
    when(state) {
        is CourseDetailState.Failure -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
        }
        CourseDetailState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is CourseDetailState.Success -> {
            LazyColumn {
                item {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = state.course.image,
                        contentDescription = null,
                    )
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            text = state.course.title,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = state.course.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                items(state.course.content) {
                    Column(Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Divider(
                            thickness = 2.dp,
                            color = Purple40,
                            modifier = Modifier
                                .padding(top = 4.dp, bottom = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CourseDetailPreviewPhone() {
    val state = CourseDetailState.Loading
    MvvmTheme {
        CourseDetailScreen(state)
    }
}