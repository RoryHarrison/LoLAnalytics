package uk.co.roryharrison5hotmail.lolanalytics.Web;

import com.android.volley.VolleyError;

/**
 * Created by roryh on 14/03/2018.
 */

public interface VolleyCallback {
    void notifySuccess(String requestType,String response, int call);
    void notifyError(String requestType,VolleyError error);
}