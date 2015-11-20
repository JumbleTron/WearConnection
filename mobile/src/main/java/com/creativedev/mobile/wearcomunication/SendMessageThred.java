package com.creativedev.mobile.wearcomunication;

import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;


public class SendMessageThred extends Thread implements Runnable {

    private GoogleApiClient mApiClient;
    private String path;
    private String text;

    public SendMessageThred(GoogleApiClient client, final String p, final String t) {
        mApiClient = client;
        path = p;
        text = t;
    }

    public void runThred() {
        this.run();
    }

    @Override
    public void run() {
        Log.d("grzes_log", "Uruchomiono wątek");
        NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( mApiClient ).await();
        Log.d("grzes_log", "Ilość nodów: " + nodes.getNodes().size());
        for(Node node : nodes.getNodes()) {
            MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(mApiClient, node.getId(), path, text.getBytes() ).await();
            Log.d("grzes_log",result.getStatus().getStatusMessage());
        }
    }
}
