package com.architecture.mvvm.screens.list

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.architecture.mvvm.ui.theme.MvvmTheme
import com.architecture.domain.model.CourseBo
import com.architecture.mvvm.navigation.Routes
import com.architecture.mvvm.ui.theme.Purple40
import org.koin.androidx.compose.koinViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ListCourseRoute(
    navController: NavController,
    viewModel: ListCourseViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState().value
    ListCourseScreen(
        state = state,
        onCourseClicked = {
            val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
            navController.navigate("${Routes.Detail.name}/$encodedUrl")
        }
    )
}

@Composable
fun ListCourseScreen(
    state: CourseListState,
    onCourseClicked: (String) -> Unit,
) {
    val context = LocalContext.current
    when(state) {
        is CourseListState.Failure -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
        }
        CourseListState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is CourseListState.Success -> {
            LazyColumn {
                items(state.list) { course ->
                    CourseItem(
                        course = course,
                        onCourseClicked = onCourseClicked
                    )
                }
            }
        }
    }

}

@Composable
fun CourseItem(
    course: CourseBo,
    onCourseClicked: (String) -> Unit
) {
    Box(
        Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .border(
                border = BorderStroke(2.dp, Purple40),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onCourseClicked(course.link) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            AsyncImage(
                modifier = Modifier.size(60.dp),
                model = course.image,
                contentDescription = null,
            )
            Column {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = course.title,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 16.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = course.description,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(device = Devices.PIXEL_3A)
@Composable
fun ListCourseScreenPreviewPhone() {
    val state = CourseListState.Success(listOf(CourseBo("1","TEST", "description","image", "")))
    MvvmTheme {
        ListCourseScreen(
            state = state,
            onCourseClicked = {}
        )
    }
}