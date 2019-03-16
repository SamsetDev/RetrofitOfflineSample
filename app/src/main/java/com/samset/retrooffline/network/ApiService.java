package com.samset.retrooffline.network;

import com.samset.retrooffline.model.BasicResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Copyright (C) RetrofitOfflineSample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential.
 * <p>
 * Created by samset on 16/03/19 at 12:18 PM for RetrofitOfflineSample .
 */


public interface ApiService {

    @GET("/users/{user}/repos")
    Call<List<BasicResponse>> reposForuser(@Path("user") String user);



}
