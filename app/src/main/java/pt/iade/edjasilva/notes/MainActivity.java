package pt.iade.edjasilva.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
    protected RecyclerView itemsListView;
    protected NoteItemAdapter noteRowAdapter;
    protected ArrayList<NoteItem> itemsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // pegar as listas do websserver

        itemsList= NoteItem.List();

        setupComponents();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onPrepareOptionsMenu(menu);
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
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_new_note){
            Intent intent= new Intent(MainActivity.this,NoteActivity.class);
            intent.putExtra("position", -1);

            intent.putExtra("item", new NoteItem());
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupComponents(){
        setSupportActionBar(findViewById(R.id.toolbar));

        // Set up row adapter with our items list.
        noteRowAdapter = new NoteItemAdapter(MainActivity.this, itemsList);
        noteRowAdapter.setOnClickListener(new NoteItemAdapter.ViewHolder.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent= new Intent(MainActivity.this,NoteActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("item", itemsList.get(position));
                startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);

            }
        });


        itemsListView=(RecyclerView) findViewById(R.id.notes_list);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsListView.setAdapter(noteRowAdapter);
    }











}