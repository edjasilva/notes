package pt.iade.edjasilva.notes;
import java.util.GregorianCalendar;

public class NoteItem {
    private String title;
    private String content;
    private GregorianCalendar creationDate;
    private GregorianCalendar modifiedDate;


    public NoteItem(String title, String content, GregorianCalendar creationDate, GregorianCalendar modifiedDate){
        this.title=title;
        this.content=content;
        this.creationDate=creationDate;
        this.modifiedDate=modifiedDate;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public GregorianCalendar getCreationDate(){
        return creationDate;
    }

    public GregorianCalendar getModifiedDate(){
        return modifiedDate;
    }


    public void setTitle(String title){
        this.title=title;
    }

    public void setContent(String content){
        this.content=content;
    }

    public void setCreationDate(GregorianCalendar creationDate){
        this.creationDate=creationDate;
    }

    public void setModifiedDate(GregorianCalendar modifiedDate){
        this.modifiedDate=modifiedDate;
    }
}
