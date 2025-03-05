package app.textile.oltyems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import app.textile.oltyems.R;
import app.textile.oltyems.common.SharedPref;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPref.init(SplashActivity.this);
        startNextScreen();

    }

    private void startNextScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!SharedPref.getString("token", "").isEmpty()) {
                    startDashboard();
                } else {
                    startLogin();
                }

            }
        }, 3000);
    }

    private void startDashboard() {
        Intent i = new Intent(SplashActivity.this, DashboardActivity.class);
        startActivity(i);
        finish();
    }

    private void startLogin() {
        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}
