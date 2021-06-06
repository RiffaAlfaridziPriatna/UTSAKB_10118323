//5 Juni 2021 - 10118323 - Riffa Alfaridzi Priatna - IF8
package com.example.a10118323_uts.Model;

import java.util.Date;

public class NoteModel {
    private int id;
    private String title;
    private String category;
    private String description;
    private String date_modified;

    public NoteModel(int id, String title, String category, String description, String date_modified) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.date_modified = date_modified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }
}
