package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

/*
//Solution to step1 : java library
import com.ud867.data.utils.jokeFetcher;
 */


//import com.ud867.data.utils.jokeFetcher;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
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
    InterstitialAd mInterstitialAd;
    @BindView(R.id.tell_joke_button)
    Button jokeButton;
    @BindView(R.id.loading_indicator)
    ProgressBar spinner;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context=getApplicationContext();
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        spinner.setVisibility(View.GONE);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                //requestNewInterstitial();
                new EndpointsAsyncTask().execute(context);
                spinner.setVisibility(View.VISIBLE);
            }
        });
        requestNewInterstitial();
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
        requestNewInterstitial();
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
        showInterstitial();

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            mInterstitialAd.loadAd(adRequest);
        }
        mInterstitialAd.loadAd(adRequest);
    }
    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, getResources().getString(R.string.interstital_not_loaded), Toast.LENGTH_SHORT).show();
            spinner.setVisibility(View.VISIBLE);
            new EndpointsAsyncTask().execute(context);
        }
    }


}
