//5 Juni 2021 - 10118323 - Riffa Alfaridzi Priatna - IF8
package com.example.a10118323_uts.View.Note;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a10118323_uts.Model.NoteModel;
import com.example.a10118323_uts.Presenter.NoteImpl;
import com.example.a10118323_uts.Presenter.NoteInterface;
import com.example.a10118323_uts.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class AddNoteFragment extends Fragment {

    private Button btnBack, btnAdd;
    private TextInputEditText etJudul, etKategori, etDesc;
    private NoteInterface noteInterface;
    private TextView tvJudul;
    private boolean isEdit;
    private String[] notes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_note, container, false);

        btnBack = root.findViewById(R.id.btnBack);
        btnAdd = root.findViewById(R.id.btnAdd);
        etJudul = root.findViewById(R.id.etJudul);
        etKategori = root.findViewById(R.id.etKategori);
        etDesc = root.findViewById(R.id.etDesc);
        tvJudul = root.findViewById(R.id.tvJudul);
        isEdit = getArguments().getBoolean("isEdit");
        notes = getArguments().getStringArray("note");

        noteInterface = new NoteImpl(getContext());

        if (isEdit) {
            tvJudul.setText("Edit Note");
            btnAdd.setText("Edit Note");
            etJudul.setText(notes[1]);
            etKategori.setText(notes[2]);
            etDesc.setText(notes[3]);
        } else {
            tvJudul.setText("Add New Note");
            btnAdd.setText("Add New Note");
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etJudul.getText().toString().equals("") || etKategori.getText().toString().equals("") || etDesc.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    if (etJudul.getText().toString().equals("")) etJudul.setError("Please fill Judul field");
                    if (etKategori.getText().toString().equals("")) etKategori.setError("Please fill Kategori field");
                    if (etDesc.getText().toString().equals("")) etDesc.setError("Please fill Note field");
                } else {
                    int id = 0;
                    if (isEdit) {
                        id = Integer.parseInt(notes[0]);
                    }
                    Date d = new Date();
                    CharSequence date = DateFormat.format("EEEE, d MMM yyyy HH:mm",d.getTime());
                    NoteModel n = new NoteModel(
                            id,
                            etJudul.getText().toString(),
                            etKategori.getText().toString(),
                            etDesc.getText().toString(),
                            date+ ""
                    );
                    if (!isEdit) {
                        noteInterface.create(n);
                        Toast.makeText(getContext(), "Note has been added", Toast.LENGTH_SHORT).show();
                    } else {
                        noteInterface.update(n);
                        Toast.makeText(getContext(), "Note has been updated", Toast.LENGTH_SHORT).show();
                    }
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        return root;
    }
}