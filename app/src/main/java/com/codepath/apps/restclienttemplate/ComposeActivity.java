package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    private TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        EditText editText = findViewById(R.id.tweetMessage);
        final TextView charsTextView = findViewById(R.id.tweetCharsLeft);

        final TextWatcher txWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                charsTextView.setText(String.valueOf(280-s.length()));
            }
        };

        editText.addTextChangedListener(txWatcher);

    }

    public void onTweetBtnClick(View v) {
        EditText simpleEditText = (EditText) findViewById(R.id.tweetMessage);
        String strValue = simpleEditText.getText().toString();

        client = TwitterApp.getRestClient(this);
        client.sendTweet(strValue, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Tweet composedTweet = Tweet.fromJSON(response);
                    Intent i = new Intent(ComposeActivity.this, TimelineActivity.class);
                    i.putExtra("message", Parcels.wrap(composedTweet));
                    i.putExtra("code", RESULT_OK);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
