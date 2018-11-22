package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.jokedisplay.JokeActivity;


public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.JokeDeliver {
String joke;
    EndpointsAsyncTask.JokeDeliver jokeDeliver;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        jokeDeliver=this;
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        Button button=(Button) root.findViewById(R.id.jokebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EndpointsAsyncTask(jokeDeliver).execute();

            }
        });

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    @Override
    public void onJokeDelivered(String joke) {
        this.joke=joke;
        Intent intent = new Intent(getActivity(),JokeActivity.class);
        Bundle b=new Bundle();
        b.putString("joke",this.joke);
        intent.putExtra("moo",b);
        startActivity(intent);

    }
}

