//5 Juni 2021 - 10118323 - Riffa Alfaridzi Priatna - IF8
package com.example.a10118323_uts.View.Note;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a10118323_uts.Model.NoteModel;
import com.example.a10118323_uts.Presenter.NoteImpl;
import com.example.a10118323_uts.Presenter.NoteInterface;
import com.example.a10118323_uts.R;
import com.example.a10118323_uts.View.MainActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    private Context context;
    private List<NoteModel> note;
    private NoteInterface noteInterface;

    public NoteAdapter(Context context, List<NoteModel> note) {
        this.context = context;
        this.note = note;
        this.noteInterface = new NoteImpl(context);
    }

    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvJudul.setText(note.get(position).getTitle());
        holder.tvKategori.setText(note.get(position).getCategory());
        holder.tvTanggal.setText(note.get(position).getDate_modified());
        holder.tvDesc.setText(note.get(position).getDescription());
        holder.cvHover.setVisibility(View.GONE);

        final String id = Integer.toString(note.get(position).getId());
        final String[] notes = {
                String.valueOf(note.get(position).getId()),
                note.get(position).getTitle(),
                note.get(position).getCategory(),
                note.get(position).getDescription(),
                note.get(position).getDate_modified()
        };

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) v.getContext();
                Fragment fragment = new AddNoteFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isEdit", true);
                bundle.putStringArray("note", notes);
                fragment.setArguments(bundle);
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.cvHover.setVisibility(View.VISIBLE);
                return true;
            }
        });

        holder.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cvHover.setVisibility(View.GONE);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteInterface.delete(id);
                MainActivity mainActivity = (MainActivity) v.getContext();
                Fragment fragment = new NoteFragment();
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                Toast.makeText(context, "Note has been deleted", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) v.getContext();
                Fragment fragment = new AddNoteFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isEdit", true);
                bundle.putStringArray("note", notes);
                fragment.setArguments(bundle);
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return note.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvJudul, tvKategori, tvTanggal, tvDesc;
        ConstraintLayout cvHover;
        Button btnEdit, btnDelete, btnClose;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            cvHover = itemView.findViewById(R.id.cvHover);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnClose = itemView.findViewById(R.id.btnClose);
        }
    }
}
