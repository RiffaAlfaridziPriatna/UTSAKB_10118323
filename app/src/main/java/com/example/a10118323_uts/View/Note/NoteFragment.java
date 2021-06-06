//5 Juni 2021 - 10118323 - Riffa Alfaridzi Priatna - IF8
package com.example.a10118323_uts.View.Note;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.a10118323_uts.Model.NoteModel;
import com.example.a10118323_uts.Presenter.NoteImpl;
import com.example.a10118323_uts.Presenter.NoteInterface;
import com.example.a10118323_uts.R;
import com.example.a10118323_uts.View.MainActivity;
import com.example.a10118323_uts.View.SplashScreenActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NoteFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<NoteModel> note;
    private NoteAdapter adapter;
    private NoteInterface noteInterface;
    private FloatingActionButton btnAdd, btnMenu, btnCalendar;
    private TextView tvKosong;

    private boolean isFABOpen = false;
    private int yearSet, monthSet, daySet;
    private String dateSet;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_note, container, false);
        getActivity().getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));

        recyclerView = root.findViewById(R.id.rvNotes);
        btnAdd = root.findViewById(R.id.btnAdd);
        btnMenu = root.findViewById(R.id.btnOpen);
        btnCalendar = root.findViewById(R.id.btnCalendar);
        tvKosong = root.findViewById(R.id.tvKosong);

        btnAdd.setVisibility(View.INVISIBLE);
        btnCalendar.setVisibility(View.INVISIBLE);
        Date d = new Date();
        dateSet = String.valueOf(DateFormat.format("EEEE, d MMM yyyy",d.getTime()));

        yearSet = 0;
        monthSet = 0;
        daySet = 0;

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AddNoteFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isEdit", false);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar();
            }
        });
        read(dateSet);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        read(dateSet);
    }

    private void read(String date) {
        note = new ArrayList<NoteModel>();
        noteInterface = new NoteImpl(getContext());
        Cursor cursor = noteInterface.read(date);

        if (cursor.getCount() > 0) {
            tvKosong.setVisibility(View.GONE);
        } else {
            tvKosong.setVisibility(View.VISIBLE);
        }

        if (cursor.moveToFirst()) {
            do {
                NoteModel n = new NoteModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                );
                note.add(n);
            } while (cursor.moveToNext());
        }
        adapter = new NoteAdapter(getContext(), note);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }

    private void showFABMenu() {
        isFABOpen = true;
        btnAdd.setVisibility(View.VISIBLE);
        btnCalendar.setVisibility(View.VISIBLE);
        btnMenu.setImageResource(R.drawable.ic_baseline_menu_open_24);
        btnAdd.animate().translationY(-getResources().getDimension(R.dimen.standard_60));
        btnCalendar.animate().translationY(-getResources().getDimension(R.dimen.standard_120));
    }
    private void closeFABMenu() {
        isFABOpen = false;
        btnAdd.animate().translationY(0);
        btnCalendar.animate().translationY(0);
        btnMenu.setImageResource(R.drawable.ic_baseline_menu_24);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnAdd.setVisibility(View.INVISIBLE);
                btnCalendar.setVisibility(View.INVISIBLE);
            }
        }, 400);
    }

    private void showCalendar() {
        Calendar calendar = Calendar.getInstance();
        if (yearSet > 0 && monthSet > 0 && daySet > 0) calendar.set(yearSet, monthSet, daySet);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMM yyyy");
                yearSet = year;
                monthSet = month;
                daySet = dayOfMonth;
                dateSet = dateFormat.format(newDate.getTime());
                read(dateSet);
                closeFABMenu();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}