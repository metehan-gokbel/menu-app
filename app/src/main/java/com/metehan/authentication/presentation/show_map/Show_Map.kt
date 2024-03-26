package com.metehan.authentication.presentation.show_map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.metehan.authentication.domain.models.Merchant
import com.metehan.authentication.util.SharedViewModel

@Composable
fun ShowMap(
    navController: NavController,
    sharedViewModel: SharedViewModel
    ) {
    val merchantList by sharedViewModel.merchantList.observeAsState()
    LaunchedEffect(merchantList) {
        merchantList?.let {
            println("SERKAN IBNESININ MAP SCREENI: $it")
        }
    }
//    val marker = LatLng(40.12150192260742,-100.45039367675781)
//    GoogleMap(
//        modifier = Modifier.fillMaxSize(),
//        properties = MapProperties(
//            isMyLocationEnabled = true,
//            mapType = MapType.HYBRID,
//            isTrafficEnabled = true
//        )
//    ) {
//        Marker(
//            state = MarkerState(position = marker),
//            title = "MyPosition",
//            snippet = "This is a description of this Marker",
//            draggable = true
//        )
//    }

}