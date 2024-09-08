package com.wreckingballsoftware.magicdex.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wreckingballsoftware.magicdex.ui.home.components.MagicTopAppBar
import com.wreckingballsoftware.magicdex.ui.home.models.HomeEvents
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import com.wreckingballsoftware.magicdex.ui.theme.White
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    navGraph: NavGraph,
    viewModel: HomeViewModel = getViewModel()
) {
    Scaffold(
        topBar = {
            MagicTopAppBar(
                searchQuery = viewModel.state.searchQuery,
                searchQueryChanged = { viewModel.onEvent(HomeEvents.OnSearchQueryChanged(it)) },
                searchAction = { viewModel.onEvent(HomeEvents.OnSearchAction) }
            )
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(color = White)
        ) {
        }
    }

//    val context = LocalContext.current
//    viewModel.oneOffEvent.collectOneTimeEvents { nav ->
//        when (nav) {
//            HomeOneOffs.OnGoToMagicDex -> navGraph.navigateToMagicDexScreen("12345")
//            is HomeOneOffs.OnShowToast -> Toast.makeText(context, nav.message, Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//    ) {
//        Text(stringResource(R.string.app_name))
//        Button(
//            onClick = { viewModel.onEvent(HomeEvents.OnMagicDexClick) }
//        ) {
//            Text("Hello, World!")
//        }
//        Button(
//            onClick = { viewModel.onEvent(HomeEvents.OnToastClick) }
//        ) {
//            Text("Toast Me!")
//        }
//        Button(
//            onClick = { viewModel.onEvent(HomeEvents.OnAlertDialogClick) }
//        ) {
//            Text("Alert Me!")
//        }
//    }
//    viewModel.state.alertMessage?.let { message ->
//        MagicDexAlert(
//            onDismissRequest = { viewModel.dismissDialog() },
//            message = message,
//            confirmAction = { viewModel.dismissDialog() }
//        )
//    }
}
