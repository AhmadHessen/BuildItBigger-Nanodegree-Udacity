package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.jokehouse.JokeHouseActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */

public class MainActivityFragment extends Fragment implements TheEndpointAsyncTask.JokeDelivered
{
    String joke;
    TheEndpointAsyncTask.JokeDelivered jokeDelivered;
    private Button tell_joke;

    public MainActivityFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        jokeDelivered=this;
        AdView mAdView = root.findViewById(R.id.adView);

        tell_joke = root.findViewById (R.id.tell_joke);
        tell_joke.setOnClickListener (new View.OnClickListener ()
        {
            @Override
            public void onClick(View view)
            {
                new TheEndpointAsyncTask().execute(getActivity ());
            }
        });

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        return root;
    }

    @Override
    public void onJokeDelivered(String joke)
    {
        this.joke = joke;

        Intent intent = new Intent(getActivity(), JokeHouseActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(getString (R.string.the_joke), this.joke);
        intent.putExtra(getString (R.string.moo), bundle);

        startActivity(intent);
    }
}


