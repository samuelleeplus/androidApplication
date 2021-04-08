package com.example.myapplication.data.model;

import android.net.Uri;

public class PictureItem {
    public Uri uri ;
    public String caption ;
    public String label ;


    @Override
    public String toString() {
        return "PictureItem{" +
                "uri=" + uri +
                ", caption='" + caption + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}


