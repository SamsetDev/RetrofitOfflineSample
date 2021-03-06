package com.samset.retrooffline.utils.custom;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Copyright (C) RetrofitOfflineSample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential.
 * <p>
 * Created by samset on 16/03/19 at 11:29 AM for RetrofitOfflineSample .
 */


public abstract class ResponseObserver<T> implements Observer<T> {

    private int statusCode;

    private CompositeDisposable disposable;

    public ResponseObserver(CompositeDisposable disposable) {
        this.disposable = disposable;
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            statusCode = ((HttpException) e).response().code();
            onServerError(e, statusCode);
            Log.e("TAG"," onserver error ");
        } else {
            onNetworkError(e);
            Log.e("TAG"," onNetworkError error ");
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onNetworkError(Throwable e);

    public abstract void onServerError(Throwable e, int code);
}
