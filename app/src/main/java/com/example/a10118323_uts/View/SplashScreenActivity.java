//5 Juni 2021 - 10118323 - Riffa Alfaridzi Priatna - IF8
package com.example.a10118323_uts.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.a10118323_uts.R;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView ivLogo;
    private ProgressBar prBar;
    private TextView tvTunggu;

    private boolean tampil = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivLogo = findViewById(R.id.ivLogo);
        prBar = findViewById(R.id.prBar);
        tvTunggu = findViewById(R.id.tvTunggu);

        tvTunggu.setVisibility(View.GONE);
        prBar.setVisibility(View.GONE);

        boolean isFirstRun = getSharedPreferences("PREFERENCES", MODE_PRIVATE).getBoolean("isFirstRun", true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvTunggu.setVisibility(View.VISIBLE);
                prBar.setVisibility(View.VISIBLE);
                prBar.setProgress(5);

                new Handler().postDelayed(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        prBar.setProgress(35, true);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                prBar.setProgress(65, true);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        prBar.setProgress(100, true);

                                        if (isFirstRun) {
                                            startActivity(new Intent(SplashScreenActivity.this, GetStartedActivity.class));
                                            finish();
                                        } else {
                                            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                                            finish();
                                        }
                                    }
                                }, 1000);
                            }
                        }, 1000);
                    }
                }, 1000);
            }
        }, 1000);
    }
}