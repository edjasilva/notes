package pt.iade.edjasilva.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import pt.iade.edjasilva.notes.models.NoteItem;

public class NoteActivity extends AppCompatActivity {
    protected EditText title_edit;
    protected EditText notes_edit;
    protected EditText date_text;
    protected int listPosition;
    protected NoteItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // obter o item passado da atividade anterior
        Intent intent =getIntent();
        listPosition=intent.getIntExtra("position", -1);
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
            commitView();
            this.item.save();

            Intent returnIntent = new Intent();
            returnIntent.putExtra("position", this.listPosition);
            returnIntent.putExtra("item", this.item);
            setResult(AppCompatActivity.RESULT_OK, returnIntent);


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
        title_edit=(EditText) findViewById(R.id.titleLabel);
        notes_edit=(EditText) findViewById(R.id.notesLabel);
        date_text=(EditText) findViewById(R.id.dateLabel);

        populateView();
    }
    protected void populateView(){
        title_edit.setText(item.getTitle());
        notes_edit.setText(item.getContent());
        // Formatando LocalDateTime para String usando DateTimeFormatter
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedModifiedDate = formatter.format(item.getModifiedDate());
        date_text.setText(formattedModifiedDate);


    }
    protected void commitView(){
        item.setTitle(title_edit.getText().toString());
        item.setContent(notes_edit.getText().toString());
        item.setModifiedDate((Date) date_text.getText());
    }
}