package com.example.jokehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JokeHouseFragment extends Fragment
{
    TextView jokeTextView;

    public JokeHouseFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.joke_house_fragment, container, false);
        jokeTextView = root.findViewById(R.id.joke_textview);
        Intent intent = getActivity().getIntent();

        Bundle bundle = intent.getBundleExtra(getString (R.string.moo));
        String joke  = bundle.getString(getString (R.string.the_joke));

        if (joke != null && joke.length() != 0)
        {
            jokeTextView.setText(joke);
        }

        return root;
    }
}
