package pt.iade.edjasilva.notes.models;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NoteItem implements Serializable {
    private int id;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime modifiedDate;

public NoteItem(int i, String iamPretty, String first, LocalDateTime now){
    this(0, "", "", LocalDateTime.now(), LocalDateTime.now());
}
    public NoteItem(int id, String title, String content, LocalDateTime creationDate, LocalDateTime modifiedDate){
        this.id=id;
        this.title=title;
        this.content=content;
        this.creationDate=creationDate;
        this.modifiedDate=modifiedDate;
    }

    public static ArrayList<NoteItem> List (){
    ArrayList<NoteItem> items =new ArrayList<NoteItem>();


    // Criar listas de forma estática
    items.add(new NoteItem(1,"iam pretty",  "",   LocalDateTime.now(),LocalDateTime.now() ));
    items.add(new NoteItem(2, "you are pretty", "idk",LocalDateTime.now(),LocalDateTime.now() ));
    return items;

    }

    public static NoteItem GetById(int id){

    //busca os dados do webserver usando o id e o populate object

    return new NoteItem(id, "", "",LocalDateTime.now(), LocalDateTime.now() );

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
