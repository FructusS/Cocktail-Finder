package ru.fructus.cocktailfinder.ui.screen.drink.list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import ru.fructus.cocktailfinder.R
import ru.fructus.cocktailfinder.ui.screen.drink.DrinkListContract
import ru.fructus.cocktailfinder.ui.screen.drink.list.DrinkListViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable

fun DrinkListScreenSuccess(state: DrinkListContract.State.Success, viewModel: DrinkListViewModel) {

    state.list.forEach { drink ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                GlideImage(
                    model = drink.image,
                    contentDescription = "Image ${drink.image}",
                    contentScale = ContentScale.Fit,
                    loading = placeholder(R.drawable.ic_downloading)
                )
                Text(text = drink.title)
                Text(text = stringResource(R.string.id, drink.id))
                Text(text = drink.category)
                Text(text = drink.type)
                Text(text = drink.typeGlass)
                Image(painter = if (drink.isFavorite) painterResource(R.drawable.ic_like) else painterResource(
                    R.drawable.ic_dislike
                ), contentDescription = "like button", modifier = Modifier.clickable {
                    viewModel.event(DrinkListContract.Event.OnFavoriteClick(drink))
                })
            }
        }
    }
}