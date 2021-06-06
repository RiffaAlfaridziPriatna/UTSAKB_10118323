//5 Juni 2021 - 10118323 - Riffa Alfaridzi Priatna - IF8
package com.example.a10118323_uts.Presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.a10118323_uts.Model.NoteModel;

public class NoteImpl extends SQLiteOpenHelper implements NoteInterface {
    public NoteImpl(@Nullable Context context) {
        super(context, "db_note", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
          "CREATE TABLE `tb_note` (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(50), category VARCHAR(50), description VARCHAR(1000), date_modified VARCHAR(20))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE `tb_note`");
    }

    @Override
    public Cursor read(String date_modified) {
        SQLiteDatabase db = getReadableDatabase();
//        return db.rawQuery("SELECT * FROM `tb_note` WHERE `date_modified` = '%" + date_modified + "%'", null);
        return db.rawQuery("SELECT * FROM `tb_note` WHERE `date_modified` LIKE '%" + date_modified + "%'", null);
    }

    @Override
    public boolean create(NoteModel note) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO `tb_note` VALUES (null, '" + note.getTitle() + "', '" + note.getCategory() + "', '" + note.getDescription() + "', '" + note.getDate_modified() + "')");
        return true;
    }

    @Override
    public boolean update(NoteModel note) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE `tb_note` SET `title` = '" + note.getTitle() + "', `category` = '" + note.getCategory() + "', `description` = '" + note.getDescription() + "', `date_modified` = '" + note.getDate_modified() + "' WHERE `id` = '" + note.getId() + "'");
        return true;
    }

    @Override
    public boolean delete(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM `tb_note` WHERE `id`='"+id+"'");
        return true;
    }
}
