package pt.iade.edjasilva.notes.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class NoteItem implements Serializable {
    private int id;
    private String title;
    private String content;
    private Date creationDate;
    private Calendar modifiedDate;

    public NoteItem() {
        this(0, "", "", Calendar.getInstance());
    }

    public NoteItem(int id, String title, String content, Calendar modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedDate;
    }

    public static ArrayList<NoteItem> List() {
        ArrayList<NoteItem> items = new ArrayList<NoteItem>();


        // Criar listas de forma estática
        items.add(new NoteItem(1, "iam pretty", "", new GregorianCalendar(2022, Calendar.FEBRUARY, 1)));
        items.add(new NoteItem(2, "you are pretty", "idk", new GregorianCalendar(2022, Calendar.MAY, 1)));
        return items;

    }

    public static NoteItem GetById(int id) {

        //busca os dados do webserver usando o id e o populate object


        return new NoteItem(id, "", "", new GregorianCalendar(2022, Calendar.JANUARY, 1));

    }


    public void save() {
        if (id == 0) {
            id = new Random().nextInt(1000) + 1;

        } else {


        }

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

    public Date getCreationDate() {
        return creationDate;
    }

    public Calendar getModifiedDate() {
        return modifiedDate;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setModifiedDate(Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
