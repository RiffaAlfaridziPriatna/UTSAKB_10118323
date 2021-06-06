//5 Juni 2021 - 10118323 - Riffa Alfaridzi Priatna - IF8
package com.example.a10118323_uts.Presenter;

import android.database.Cursor;

import com.example.a10118323_uts.Model.NoteModel;

public interface NoteInterface {
    public Cursor read(String date_modified);
    public boolean create(NoteModel note);
    public boolean update(NoteModel note);
    public boolean delete(String id);
}
