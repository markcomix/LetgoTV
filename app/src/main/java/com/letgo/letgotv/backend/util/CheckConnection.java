package com.letgo.letgotv.backend.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.letgo.letgotv.application.App;

/**
 * Created by marcos.buricchi on 04/11/2016.
 */
public class CheckConnection {

    private static CheckConnection instance = new CheckConnection();

    ConnectivityManager connectivityManager;

    boolean connected = false;

    public static CheckConnection getInstance() {

        return instance;
    }

    public boolean isOnline() {

        try {

            connectivityManager = (ConnectivityManager) App.applicationContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();

            return connected;

        } catch (Exception e) {

            if (e.getMessage() != null) {

                System.out.println("CheckConnectivity Exception: " + e.getMessage());
            }
        }

        return connected;
    }
}
