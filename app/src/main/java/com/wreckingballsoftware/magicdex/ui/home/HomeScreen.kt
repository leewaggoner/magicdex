package com.wreckingballsoftware.magicdex.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wreckingballsoftware.magicdex.R
import com.wreckingballsoftware.magicdex.extensions.collectOneTimeEvents
import com.wreckingballsoftware.magicdex.ui.home.dialogs.MagicDexAlert
import com.wreckingballsoftware.magicdex.ui.home.models.HomeEvents
import com.wreckingballsoftware.magicdex.ui.home.models.HomeOneOffs
import com.wreckingballsoftware.magicdex.ui.navigation.NavGraph
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    navGraph: NavGraph,
    viewModel: HomeViewModel = getViewModel()
) {
    val context = LocalContext.current
    viewModel.oneOffEvent.collectOneTimeEvents { nav ->
        when (nav) {
            HomeOneOffs.OnGoToMagicDex -> navGraph.navigateToMagicDexScreen("12345")
            is HomeOneOffs.OnShowToast -> Toast.makeText(context, nav.message, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(stringResource(R.string.app_name))
        Button(
            onClick = { viewModel.onEvent(HomeEvents.OnMagicDexClick) }
        ) {
            Text("Hello, World!")
        }
        Button(
            onClick = { viewModel.onEvent(HomeEvents.OnToastClick) }
        ) {
            Text("Toast Me!")
        }
        Button(
            onClick = { viewModel.onEvent(HomeEvents.OnAlertDialogClick) }
        ) {
            Text("Alert Me!")
        }
    }
    viewModel.state.alertMessage?.let { message ->
        MagicDexAlert(
            onDismissRequest = { viewModel.dismissDialog() },
            message = message,
            confirmAction = { viewModel.dismissDialog() }
        )
    }
}
