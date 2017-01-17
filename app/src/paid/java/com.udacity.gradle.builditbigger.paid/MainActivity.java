package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

/*
//Solution to step1 : java library
import com.ud867.data.utils.jokeFetcher;
 */


//import com.ud867.data.utils.jokeFetcher;
import com.udacity.gradle.builditbigger.CustomMessageEvent;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;
import com.udacity.nd801.course.androidlib.JokesActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String JOKE_STRING = "JOKE_STRING";
    @BindView(R.id.loading_indicator)
    ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        spinner.setVisibility(View.GONE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doThis(CustomMessageEvent event){
        launchJokeActivity(event.getCustomJoke());
    }

    private void launchJokeActivity(String customJoke) {
        spinner.setVisibility(View.GONE);
        Intent jokesIntent=new Intent(this,JokesActivity.class);
        jokesIntent.putExtra(JOKE_STRING, customJoke);
        startActivity(jokesIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void tellJoke(View view) {
        //Solution of Step 1
        /*Toast.makeText(this, jokeFetcher.getJoke(), Toast.LENGTH_SHORT).show();*/

        //Solution of Step 2
        /**/

        //Solution of Step 3 learning via GCE
        new EndpointsAsyncTask().execute(this);
        spinner.setVisibility(View.VISIBLE);
    }


}
