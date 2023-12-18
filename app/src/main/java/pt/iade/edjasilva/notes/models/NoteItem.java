package pt.iade.edjasilva.notes.models;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class NoteItem implements Serializable {
    private int id;
    private String title;
    private String content;
    private Date creationDate;
    private Date modifiedDate;

public NoteItem(){
    this(0, "", "", new Date(), new Date());
}
    public NoteItem(int id, String title, String content, Date creationDate, Date modifiedDate){
        this.id=id;
        this.title=title;
        this.content=content;
        this.creationDate=creationDate;
        this.modifiedDate=modifiedDate;
    }

    public static ArrayList<NoteItem> List (){
    ArrayList<NoteItem> items =new ArrayList<NoteItem>();


    // Criar listas de forma estática
        items.add(new NoteItem(1, "iam pretty", "", new Date(), new Date()));
        items.add(new NoteItem(2, "you are pretty", "idk", new Date(), new Date()));
        return items;

    }

    public static NoteItem GetById(int id){

    //busca os dados do webserver usando o id e o populate object


        return new NoteItem(id, "", "", new Date(), new Date());

    }


    public void save(){
    if(id==0){
        id=new Random().nextInt(1000)+1;
    }else {


    }

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

    public Date getCreationDate(){
        return creationDate;
    }

    public Date getModifiedDate(){
        return modifiedDate;
    }


    public void setTitle(String title){
        this.title=title;
    }


    public void setContent(String content){
        this.content=content;
    }

    public void setCreationDate(Date creationDate){
        this.creationDate=creationDate;
    }

    public void setModifiedDate(Date modifiedDate){
        this.modifiedDate=modifiedDate;
    }
}
