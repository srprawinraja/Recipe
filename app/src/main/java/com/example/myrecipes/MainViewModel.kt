package com.example.myrecipes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel :ViewModel() {
    private val _catergoryState = mutableStateOf(RecipeState())
    var catergoriesState : State<RecipeState> = _catergoryState
    init {
        fetchCategories()
    }
    private fun fetchCategories(){
        viewModelScope.launch {
            try{
                val response = recipeService.getCategory()
                _catergoryState.value=_catergoryState.value.copy(
                    list=response.categories,
                    loading = false,
                    error = null
                )
            }
            catch (e : Exception){
                _catergoryState.value=_catergoryState.value.copy(
                    loading = false,
                    error="error fetching Categories $e.message"
                )
            }

        }
    }


    data class RecipeState(
        var loading: Boolean = false,
        var list: List<Category> = emptyList(),
        var error:String? = null
        )
}