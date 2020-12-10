package com.dakiiii.hungerwarriorvendor.networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton sVolleySingleton;
    private Context eContext;
    private RequestQueue eRequestQueue;

    private VolleySingleton(Context context) {
        eContext = context;
        eRequestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (sVolleySingleton == null) {
            sVolleySingleton = new VolleySingleton(context);
        }
        return sVolleySingleton;

    }

    public RequestQueue getRequestQueue() {
        if (eRequestQueue == null) {
            eRequestQueue = Volley.newRequestQueue(eContext);
        }
        return eRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
