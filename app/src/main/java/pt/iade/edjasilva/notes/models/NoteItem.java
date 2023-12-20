package pt.iade.edjasilva.notes.models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;


import pt.iade.edjasilva.notes.utilities.DateJsonAdapter;
import pt.iade.edjasilva.notes.utilities.WebRequest;

public class NoteItem implements Serializable {
    private int id;
    private String title;
    private String content;
    @JsonAdapter(DateJsonAdapter.class)
    private LocalDate creationDate;
    @JsonAdapter(DateJsonAdapter.class)
    private LocalDate modifiedDate;

    public NoteItem() {
        this(0, "", "", LocalDate.now(), LocalDate.now());
    }

    public NoteItem(int id, String title, String content, LocalDate creationDate, LocalDate modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
    }

    public static void List(ListResponse response) {
        ArrayList<NoteItem> items = new ArrayList<NoteItem>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/notes"));
                    String resp = request.performGetRequest();

                    JsonArray array = new Gson().fromJson(resp, JsonArray.class);

                    for (JsonElement elem : array){
                        items.add(new Gson().fromJson(elem, NoteItem.class));
                    }
                    response.response(items);

                } catch (Exception e){
                    Log.e("List", e.toString());
                }
            }
        });
        thread.start();


    }

    public static NoteItem GetById(int id) {

        //busca os dados do webserver usando o id e o populate object

        return new NoteItem(id, "", "", LocalDate.now(), LocalDate.now());

    }


    public void save(SaveResponse response) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (id == 0) {
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/notes"));
                        String resp = request.performPostRequest(NoteItem.this);

                        NoteItem item = new Gson().fromJson(resp, NoteItem.class);

                        id = item.getId();
                        response.response();

                    } else {
                        WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/notes/"+id));
                        request.performPostRequest(NoteItem.this);

                        response.response();
                    }
                } catch (Exception e){
                    Log.e("Save", e.toString());
                }

            }
        });
        thread.start();
    }

    public void delete(DeleteResponse response){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    WebRequest request = new WebRequest(new URL(WebRequest.LOCALHOST+"/api/notes/" + id));
                    request.performDeleteRequest();

                    response.response();
                } catch (Exception e){
                    Log.e("delete", e.toString());
                }
            }
        });
        thread.start();

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



    public interface ListResponse{
        public void response(ArrayList<NoteItem> items);
    }

    public interface SaveResponse{
        public void response();
    }

    public interface DeleteResponse{
        public void response();
    }
}
