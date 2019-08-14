package com.bappedajabar.infobapeddapp.fierbase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDServices extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed Token : "+refreshedToken);

        sendRegistrationToServer(refreshedToken);
//        super.onTokenRefresh();
    }



    private void sendRegistrationToServer(String refreshedToken) {
        // TODO: Implement this method to send token to your app server.
    }
}
