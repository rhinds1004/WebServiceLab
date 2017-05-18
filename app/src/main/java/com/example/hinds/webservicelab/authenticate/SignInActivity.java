package com.example.hinds.webservicelab.authenticate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hinds.webservicelab.CourseActivity;
import com.example.hinds.webservicelab.R;

public class SignInActivity extends AppCompatActivity implements LoginFragment.LoginInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.sign_in_activity, new LoginFragment() )
                .commit();

    }

    @Override
    public void login(String userId, String pwd) {
        Intent i = new Intent(this, CourseActivity.class);
        startActivity(i);
        finish();

    }
}
