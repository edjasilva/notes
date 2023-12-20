package pt.iade.edjadasilva.noteserver.controllers;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.edjadasilva.noteserver.models.NoteItem;
import pt.iade.edjadasilva.noteserver.models.exceptions.NotFoundException;
import pt.iade.edjadasilva.noteserver.models.repositories.NoteItemRepository;
import pt.iade.edjadasilva.noteserver.models.responses.Response;
@RestController
@RequestMapping(path = "/api/notes")

public class NoteItemController {
    private Logger logger = LoggerFactory.getLogger(NoteItemController.class);

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteItem> getNoteItems() {
        logger.info("Sending all note items");
        return NoteItemRepository.getNoteItems();
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteItem getNoteItem(@PathVariable("id") int id) throws NotFoundException {
        logger.info("Sending note item with id " + id);
        NoteItem noteItem = NoteItemRepository.getNoteItem(id);
        if (noteItem != null)
            return noteItem;
        else
            throw new NotFoundException("" + id, "NoteItem", "id");
    }

    @DeleteMapping(path = "{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteNoteItem(@PathVariable("number") int number) {
        logger.info("Deleting note item with number " + number);
        if (NoteItemRepository.deleteNoteItem(number))
            return new Response(number + " was deleted.", null);
        else
            return new Response(number + " not found.", null);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteItem addNoteItem(@RequestBody NoteItem noteItem) {
        logger.info("Including new note item " + noteItem);
        return NoteItemRepository.addNoteItem(noteItem);
    }

    @PostMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteItem updateNoteItem(@RequestBody NoteItem noteItem){
        logger.info("Updating an existing note item.");
        return NoteItemRepository.putNoteItem(noteItem);
    }
}
