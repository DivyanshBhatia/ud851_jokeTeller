package com.udacity.nd801.course.androidlib;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JokesActivity extends AppCompatActivity {

    private static final String jokeString="JOKE_STRING";
    private String currentJoke=null;

    @BindView(R2.id.joke_text_view_id)
    TextView jokeTextView;

    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();
        actionBar=this.getSupportActionBar();
        if(bundle!=null){
            currentJoke=bundle.getString(jokeString);
            jokeTextView.setText(currentJoke);
        }
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
