package com.goodcat.popularmusicapp.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkConnectivityUtil {

    public static boolean isNetworkConneted(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnected();
    }

    public static boolean isConnectedWithCellular(Context context) {
        int networkType = getNetworkType(context);
        return networkType == ConnectivityManager.TYPE_MOBILE_DUN || networkType == ConnectivityManager.TYPE_MOBILE;
    }

    private static int getNetworkType(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo().getType();
    }

}
