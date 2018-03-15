package uk.co.roryharrison5hotmail.lolanalytics.Web;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


/**
 * Created by roryh on 14/03/2018.
 */

public final class APICommunicator {

    VolleyCallback mResultCallback = null;
    Context mContext;

    public APICommunicator(VolleyCallback resultCallback, Context context){
        mResultCallback = resultCallback;
        mContext = context;
    }

    public void getDataVolley(final String requestType, String url, final int call){
            RequestQueue queue = Volley.newRequestQueue(mContext);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(requestType, response, call);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.notifyError(requestType, error);
                }
            });
            queue.add(stringRequest);
    }
}