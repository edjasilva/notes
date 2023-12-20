package pt.iade.edjadasilva.noteserver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pt.iade.edjadasilva.noteserver.models.repositories.NoteItemRepository;

import static pt.iade.edjadasilva.noteserver.models.repositories.NoteItemRepository.populate;

@SpringBootApplication
public class NoteserverApplication {

	public static void main(String[] args) {

		NoteItemRepository.populate();
		SpringApplication.run(NoteserverApplication.class, args);
	}

}

