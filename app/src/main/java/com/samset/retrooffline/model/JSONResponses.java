package com.samset.retrooffline.model;

import java.io.Serializable;

/**
 * Copyright (C) RetrofitOfflineSample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential.
 * <p>
 * Created by samset on 16/03/19 at 12:24 PM for RetrofitOfflineSample .
 */


public class JSONResponses implements Serializable {

    private String id;
    private String image;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
