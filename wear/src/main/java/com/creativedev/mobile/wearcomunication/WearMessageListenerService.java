package com.creativedev.mobile.wearcomunication;

import android.content.Intent;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by gkielar on 20.11.15.
 */
public class WearMessageListenerService extends WearableListenerService {

    private static final String START_ACTIVITY = "/start_activity";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if( messageEvent.getPath().equalsIgnoreCase( START_ACTIVITY ) ) {
            Intent intent = new Intent( this, MainActivity.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity( intent );
        } else {
            super.onMessageReceived(messageEvent);
        }
    }
}
