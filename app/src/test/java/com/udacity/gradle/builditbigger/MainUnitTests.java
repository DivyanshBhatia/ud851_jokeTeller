package com.udacity.gradle.builditbigger;

/**
 * Created by dnbhatia on 1/15/2017.
 */

import org.junit.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.fail;



public class MainUnitTests extends MainActivity {

    CountDownLatch signal = null;
    String joke=null;
    @Test
    public void verifyJokeAsyncTaskResponse() {
        EndpointsAsyncTask jokeTask=new EndpointsAsyncTask();
        jokeTask.execute(this);
        try {
            joke = jokeTask.get(30, TimeUnit.SECONDS);
            assert !joke.isEmpty();
        } catch (InterruptedException e) {
            fail("InterruptedException");
        } catch (ExecutionException e) {
            fail("ExecutionException");
        } catch (TimeoutException e) {
            fail("Timed out");
        }
    }
}
