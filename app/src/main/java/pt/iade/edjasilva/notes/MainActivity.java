package pt.iade.edjasilva.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.time.LocalDateTime;
import java.util.ArrayList;

import pt.iade.edjasilva.notes.adapters.NoteItemAdapter;
import pt.iade.edjasilva.notes.models.NoteItem;

public class MainActivity extends AppCompatActivity {
    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    public static final int RESULT_DELETE = 2;
    protected RecyclerView notes_list;
    protected NoteItemAdapter noteRowAdapter;
    protected ArrayList<NoteItem> itemsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupComponents();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_new_note){
            Intent intent= new Intent(MainActivity.this,NoteActivity.class);
            intent.putExtra("position", -1);

            intent.putExtra("item", new NoteItem());
            startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==EDITOR_ACTIVITY_RETURN_ID){
            if(resultCode==AppCompatActivity.RESULT_OK){
                int position=data.getIntExtra("position", -1);
                NoteItem updateItem =(NoteItem) data.getSerializableExtra("item");

                if(position== -1) {


                    itemsList.add(updateItem);

                    noteRowAdapter.notifyItemInserted(itemsList.size() - 1);
                } else {

                    //updates
                    itemsList.set(position, updateItem);

                    noteRowAdapter.notifyItemChanged(position);
                }
            }else if (resultCode == RESULT_DELETE) {
                int positionToDelete = data.getIntExtra("position", -1);
                if (positionToDelete != -1) {
                    itemsList.remove(positionToDelete);
                    noteRowAdapter.notifyItemRemoved(positionToDelete);
                }
            }
        }
    }

    private void setupComponents(){
        setSupportActionBar(findViewById(R.id.toolbar));

        // pegar as listas do websserver
        NoteItem.List(new NoteItem.ListResponse() {
            @Override
            public void response(ArrayList<NoteItem> items) {
                itemsList = items;

                // Set up row adapter with our items list.
                noteRowAdapter = new NoteItemAdapter(MainActivity.this, itemsList);
                noteRowAdapter.setOnClickListener(new NoteItemAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent= new Intent(MainActivity.this,NoteActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("item", itemsList.get(position));
                        startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);

                    }
                });

                notes_list = findViewById(R.id.notes_list);
                notes_list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                notes_list.setAdapter(noteRowAdapter);
            }
        });

    }











}