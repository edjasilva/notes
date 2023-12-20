package pt.iade.edjadasilva.noteserver.models;
import java.time.LocalDate;
import java.util.Calendar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class NoteItem implements Serializable {
    private static int next_id=1;
    private int id;
    private String title;
    private String content;
    private LocalDate creationDate;
    private LocalDate modifiedDate;

    public NoteItem(){

    }

    public NoteItem(String title, String content, LocalDate modifiedDate, LocalDate creationDate) {
        this.id = next_id;
        next_id++;
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedDate;
        this.creationDate= creationDate;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
