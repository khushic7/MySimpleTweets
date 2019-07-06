package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TwitterDetailsActivity extends AppCompatActivity {

    // The tweet to display
    Tweet tweet;

    // The view objects
    @BindView(R.id.tvTweeterName) TextView tvName;
    @BindView(R.id.tvTweeterHandle) TextView tvHandle;
    @BindView(R.id.tvTweetContent) TextView tvOverview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_details);
        ButterKnife.bind(this);

        // Unwrap the tweet passed in via intent, using its simple name as a key
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
        Log.d("TwitterDetailsActivity", String.format("Showing details for '%s'", tweet.getBody()));

        // Set the tweeter name, handle and content
        tvName.setText(tweet.getUser().name);
        tvHandle.setText("@" + tweet.getUser().screenName);
        tvOverview.setText(tweet.getBody());

    }
}
