package xyz.net7.sampleroom.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class DataItem {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;
    private String name;
    private String content;
    private String details;
    private String section;

    @Ignore
    public DataItem(String name, String content, String details, String section) {
        this.name = name;
        this.content = content;
        this.details = details;
        this.section = section;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getDetails() {
        return details;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public DataItem() {
        this.name = "Nombre";
        this.content = "Contenido";
        this.details = "Deatalle";
        this.section = "Seccion";
    }
}