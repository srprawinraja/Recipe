package com.example.myrecipes.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myrecipes.Category

@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
){
    val recipeViewModel:MainViewModel = viewModel()
    val viewState by recipeViewModel.catergoriesState

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewState.error!=null->{
                Text(text = "ERROR OCCURED")
            }
            else->{
                CategoryScreen(viewState.list)
            }
        }
    }
}

@Composable
fun CategoryScreen(category: List<Category>){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {  // fix two column
        items(category){
            category->
            CategoryItem(category)
        }

    }
}

@Composable
fun CategoryItem(category: Category){
    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = rememberAsyncImagePainter(model =category.strCategoryThumb ),
            modifier = Modifier.fillMaxSize().aspectRatio(1f),
            contentDescription = null )
        Text(
            text = category.strCategory,
            color= Color.Black,
            style= TextStyle(fontWeight=FontWeight.Bold)
        )
    }
}

