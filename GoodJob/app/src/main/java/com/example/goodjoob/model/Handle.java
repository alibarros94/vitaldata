package com.example.goodjoob.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Handle {

    public static Handle mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private Handle(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }
    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public static  synchronized Handle getInstance(Context context){
        if (mInstance == null){
            mInstance = new Handle(context);
        }
        return mInstance;
    }
    public <T>void addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }
}
