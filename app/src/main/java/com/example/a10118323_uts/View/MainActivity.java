//5 Juni 2021 - 10118323 - Riffa Alfaridzi Priatna - IF8
package com.example.a10118323_uts.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.a10118323_uts.R;
import com.example.a10118323_uts.View.Info.InfoFragment;
import com.example.a10118323_uts.View.Note.NoteFragment;
import com.example.a10118323_uts.View.Profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NoteFragment()).commit();
        navigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            }

            switch (item.getItemId()) {
                case R.id.nav_note :
                    selectedFragment = new NoteFragment();
                    break;
                case R.id.nav_info :
                    selectedFragment = new InfoFragment();
                    break;
                case R.id.nav_profile :
                    selectedFragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_exit, (ConstraintLayout) findViewById(R.id.layoutExit));
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
}