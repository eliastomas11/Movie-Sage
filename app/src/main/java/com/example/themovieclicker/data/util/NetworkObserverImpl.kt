package com.example.themovieclicker.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.core.content.getSystemService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkObserverImpl @Inject constructor(private val context: Context) : NetworkObserver {

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun checkForInternetConnection(): Flow<NetworkObserver.Status> = callbackFlow {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: android.net.Network) {
                super.onAvailable(network)
                launch { send(NetworkObserver.Status.Available) }
            }

            override fun onUnavailable() {
                super.onUnavailable()
                launch { send(NetworkObserver.Status.Unavailable) }

            }

            override fun onLost(network: Network) {
                super.onLost(network)
                launch { send(NetworkObserver.Status.Lost) }

            }
        }
        connectivityManager.registerNetworkCallback(networkRequest, callback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged()


}