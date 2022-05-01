package com.example.inno_sampleproject.General;

import android.content.Context;
import android.net.NetworkInfo;

public class ConnectivityManager
{
    public static boolean isNetworkAvailable(Context mContext) {
        android.net.ConnectivityManager connectivityManager
                = (android.net.ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
