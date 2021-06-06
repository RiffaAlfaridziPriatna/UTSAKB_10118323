//5 Juni 2021 - 10118323 - Riffa Alfaridzi Priatna - IF8
package com.example.a10118323_uts.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.a10118323_uts.R;
import com.example.a10118323_uts.View.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class GetStartedActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tab;
    private Button btnStarted;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        tab = findViewById(R.id.tabStart);
        viewPager = findViewById(R.id.pagerStart);
        btnStarted = findViewById(R.id.btnStarted);
        adapter = new ViewPagerAdapter(this);

        btnStarted.setVisibility(View.INVISIBLE);
        viewPager.setAdapter(adapter);

        tab.setupWithViewPager(viewPager);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 4) {
                    btnStarted.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        btnStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences("PREFERENCES", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();
                startActivity(new Intent(GetStartedActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GetStartedActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(GetStartedActivity.this).inflate(R.layout.custom_exit, (ConstraintLayout) findViewById(R.id.layoutExit));
        builder.setView(view);

        final AlertDialog alertDialog= builder.create();
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        view.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}