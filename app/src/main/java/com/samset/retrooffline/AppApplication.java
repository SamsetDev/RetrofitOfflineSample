package com.samset.retrooffline;

import android.app.Application;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import androidx.multidex.MultiDex;

/**
 * Copyright (C) RetrofitOfflineSample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential.
 * <p>
 * Created by samset on 15/03/19 at 1:52 PM for RetrofitOfflineSample .
 */


public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

    }

}
