package com.metehan.authentication.presentation.show_map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun ShowMap(
    navController: NavController,
    ) {

    val marker = LatLng(38.46307718783008, 27.250583446031168)
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = MapProperties(
            isMyLocationEnabled = true,
            mapType = MapType.HYBRID,
            isTrafficEnabled = true
        )
    ) {
        Marker(
            state = MarkerState(position = marker),
            title = "MyPosition",
            snippet = "This is a description of this Marker",
            draggable = true
        )
    }

}