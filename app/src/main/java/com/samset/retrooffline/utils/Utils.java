package com.samset.retrooffline.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Copyright (C) RetrofitOfflineSample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential.
 * <p>
 * Created by samset on 16/03/19 at 12:14 PM for RetrofitOfflineSample .
 */


public class Utils {

    public static boolean isConnected(Context context) {
        try {
            ConnectivityManager e = (ConnectivityManager) context.getSystemService(
                    Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = e.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            Log.w("TAG", e.toString());
        }

        return false;
    }
}
