package com.udacity.gradle.builditbigger;

/**
 * Created by dnbhatia on 1/15/2017.
 */

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import static junit.framework.Assert.fail;



@RunWith(AndroidJUnit4.class)
@SmallTest
public class MainUnitTests {

    String joke=null;
    @Before
    public void jokeInitialize() {
        joke = null;
    }
    @Test
    public void verifyJokeAsyncTaskResponse() {
        EndpointsAsyncTask jokeTask=new EndpointsAsyncTask();
        jokeTask.execute(InstrumentationRegistry.getTargetContext());
        try {
            joke = jokeTask.get(30, TimeUnit.SECONDS);
            Assert.assertNotNull(joke);
        } catch (InterruptedException e) {
            fail("InterruptedException");
        } catch (ExecutionException e) {
            fail("ExecutionException");
        } catch (TimeoutException e) {
            fail("Timed out");
        }
    }
}
