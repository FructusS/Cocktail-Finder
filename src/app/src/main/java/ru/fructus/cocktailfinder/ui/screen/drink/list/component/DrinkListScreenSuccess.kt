package ru.fructus.cocktailfinder.ui.screen.drink.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import ru.fructus.cocktailfinder.R
import ru.fructus.cocktailfinder.ui.screen.drink.list.DrinkListContract
import ru.fructus.cocktailfinder.ui.screen.drink.list.DrinkListViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable

fun DrinkListScreenSuccess(state: DrinkListContract.State.Success, viewModel: DrinkListViewModel) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            3.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(state.drinks) { drink ->
            Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.clickable {

            }) {
                Text(
                    text = drink.title, textAlign = TextAlign.Center
                )

                GlideImage(
                    model = drink.image,
                    contentDescription = "Image ${drink.image}",
                    contentScale = ContentScale.Fit,
                    loading = placeholder(R.drawable.ic_downloading)
                )
            }
        }
    }
}