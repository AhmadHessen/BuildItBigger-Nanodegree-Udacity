package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class TheEndpointAsyncTask extends AsyncTask<Context, Void, String>
{
    private static MyApi myApi = null;
    private Context context;
    private JokeDelivered jokeDelivered;

    public interface JokeDelivered
    {
        void onJokeDelivered(String joke);
    }

    @Override
    protected String doInBackground(Context... params)
    {
        if(myApi == null)
        {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApi = builder.build ();
        }

        context = params[0];
        try
        {
            return myApi.sayHi ("Hi").execute ().getData ();
        } catch (IOException e)
        {
            return null;
        }

    }


    @Override
    protected void onPostExecute(String result)
    {
        jokeDelivered.onJokeDelivered(result);
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}


