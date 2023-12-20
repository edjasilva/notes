package pt.iade.edjadasilva.noteserver.models.repositories;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import pt.iade.edjadasilva.noteserver.models.NoteItem;
public class NoteItemRepository {

    private static ArrayList<NoteItem> noteItems = new ArrayList<>();

    public static void populate() {
        // Criar listas de forma estática
       }

    public static List<NoteItem> getNoteItems() {
        return noteItems;
    }

    public static NoteItem getNoteItem(int id) {
        for (NoteItem noteItem : noteItems) {
            if (noteItem.getId() == id) {
                return noteItem;
            }
        }
        return null;
    }

    public static boolean deleteNoteItem(int id) {
        return noteItems.removeIf((ni) -> ni.getId() == id);
    }

    public static NoteItem putNoteItem(NoteItem updatedNote) {

        for (int i = 0; i < noteItems.size(); i++) {
            NoteItem existingItem = noteItems.get(i);
            if (existingItem.getId() == updatedNote.getId()) {

                noteItems.set(i, updatedNote);
                return noteItems.get(i);
            }
        }
        return null;
    }

    public static NoteItem addNoteItem(NoteItem noteItem) {
        NoteItem newNote = new NoteItem(noteItem.getTitle(), noteItem.getContent(),
                noteItem.getCreationDate(), noteItem.getModifiedDate());
        noteItems.add(newNote);
        return newNote;
    }
}
