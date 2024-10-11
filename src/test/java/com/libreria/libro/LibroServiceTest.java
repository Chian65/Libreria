package com.libreria.libro;

import com.libreria.models.Autore;
import com.libreria.models.Libro;
import com.libreria.repositories.LibroRepository;
import com.libreria.services.LibroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LibroServiceTest {

	@InjectMocks
	private LibroService libroService;

	@Mock
	private LibroRepository libroRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void should_successfully_save_a_libro() {
		// Arrange (Setup iniziale)
		Autore autore = new Autore(1L, "Angelo", "Ceppari", Collections.emptyList());

		Libro libro = new Libro(
				null, // Non dovresti specificare l'ID, poiché lo stai creando e sarà assegnato dal database
				"Prova",
				1,
				234,
				autore
		);

		Libro savedLibro = new Libro(
				1L, // ID assegnato dopo il salvataggio
				"Prova",
				20,
				234,
				autore
		);

		when(libroRepository.save(libro)).thenReturn(savedLibro);

		// Act (Esegui l'azione da testare)
		Libro salvato = libroService.create(libro);

		// Assert (Verifica)
		assertAll("Verifica che tutte le proprietà siano corrette",
				() -> assertEquals(savedLibro.getId(), salvato.getId(), "L'ID dovrebbe essere 1"),
				() -> assertEquals(savedLibro.getTitolo(), salvato.getTitolo(), "Il titolo dovrebbe corrispondere"),
				() -> assertEquals(savedLibro.getISBN(), salvato.getISBN(), "L'ISBN dovrebbe corrispondere"),
				() -> assertEquals(savedLibro.getPagine(), salvato.getPagine(), "Il numero di pagine dovrebbe corrispondere"),
				() -> assertEquals(savedLibro.getAutore().getNome(), salvato.getAutore().getNome(), "L'autore dovrebbe essere lo stesso")
		);

		// Verifica che il metodo save sia stato chiamato una sola volta
		verify(libroRepository, times(1)).save(libro);
	}

}

