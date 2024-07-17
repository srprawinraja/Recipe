package com.example.myrecipes

data class Category (
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
        )

data class CategoriesResponse(val categories:List<Category>)
