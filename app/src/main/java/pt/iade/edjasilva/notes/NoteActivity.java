package pt.iade.edjasilva.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

import pt.iade.edjasilva.notes.models.NoteItem;

public class NoteActivity extends AppCompatActivity {
    protected EditText title_edit;
    protected EditText notes_edit;
    protected EditText date_text;
    protected NoteItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // obter o item passado da atividade anterior
        Intent intent =getIntent();
        item =(NoteItem) intent.getSerializableExtra("item");

        setupComponents();
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return super.onPrepareOptionsMenu(menu);
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_save_notes){
           finish();
            return true;
        } else if (item.getItemId()==R.id.action_delete_notes) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    private void setupComponents(){
        setSupportActionBar(findViewById(R.id.toolbar));
        title_edit=(EditText) findViewById(R.id.title_edit);
        notes_edit=(EditText) findViewById(R.id.notes_edit);
        date_text=(EditText) findViewById(R.id.date_text);

        populateView();
    }
    protected void populateView(){
        title_edit.setText(item.getTitle());
        notes_edit.setText(item.getContent());


        // Formatando LocalDateTime para String usando DateTimeFormatter

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedModifiedDate = item.getModifiedDate().format(formatter);

        date_text.setText(formattedModifiedDate);


    }
}