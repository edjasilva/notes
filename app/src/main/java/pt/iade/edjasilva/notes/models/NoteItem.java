package pt.iade.edjasilva.notes.models;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NoteItem {
    private int id;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime modifiedDate;

public NoteItem(){
    this(0, "", "", LocalDateTime.now(), LocalDateTime.now());
}
    public NoteItem(int id, String title, String content, LocalDateTime creationDate, LocalDateTime modifiedDate){
        this.id=id;
        this.title=title;
        this.content=content;
        this.creationDate=creationDate;
        this.modifiedDate=modifiedDate;
    }

    public String getTitle(){
        return title;
    }

    public int getId(){
        return id;
    }
    public String getContent(){
        return content;
    }

    public LocalDateTime getCreationDate(){
        return creationDate;
    }

    public LocalDateTime getModifiedDate(){
        return modifiedDate;
    }


    public void setTitle(String title){
        this.title=title;
    }


    public void setContent(String content){
        this.content=content;
    }

    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate=creationDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate){
        this.modifiedDate=modifiedDate;
    }
}
