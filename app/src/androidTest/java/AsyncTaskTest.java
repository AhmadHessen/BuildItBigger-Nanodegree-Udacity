import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.udacity.gradle.builditbigger.TheEndpointAsyncTask;


import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest  implements TheEndpointAsyncTask.JokeDelivered
{
    @Test
    public void testVerifyEchoResponse() {
        String result = null;
        TheEndpointAsyncTask theEndpointAsyncTask = new TheEndpointAsyncTask(this);
        theEndpointAsyncTask.execute();
        try {
            result = theEndpointAsyncTask.get();
        } catch (Exception e) {
            return;
        }
        assertNotNull(result);
    }
    @Override
    public void onJokeDelivered(String joke) {
        Log.d("Test Result : ", joke);
    }

}
