package com.example.hinds.webservicelab.authenticate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hinds.webservicelab.CourseActivity;
import com.example.hinds.webservicelab.R;

public class SignInActivity extends AppCompatActivity implements LoginFragment.LoginInteractionListener {
private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS)
                , Context.MODE_PRIVATE);
        if (!mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN), false)) {
            setContentView(R.layout.activity_sign_in);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sign_in_activity, new LoginFragment())
                    .commit();
        } else {
            Intent i = new Intent(this, CourseActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void login(String userId, String pwd) {


        mSharedPreferences
                .edit()
                .putBoolean(getString(R.string.LOGGEDIN), true)
                .commit();

        Intent i = new Intent(this, CourseActivity.class);
        startActivity(i);
        finish();

    }
}
