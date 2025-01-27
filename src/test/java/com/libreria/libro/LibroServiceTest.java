package com.libreria.libro;

import com.libreria.models.Autore;
import com.libreria.models.Categoria;
import com.libreria.models.Libro;
import com.libreria.repositories.LibroRepository;
import com.libreria.services.LibroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroServiceTest {

	@InjectMocks
	private LibroService libroService;

	@Mock
	private LibroRepository libroRepository;

	private Autore createAutore() {
		return new Autore(1L, "Angelo", "Ceppari", Collections.emptyList());
	}

	private Libro createLibroInput(Autore autore) {
		return new Libro(null, "Prova", 1, 234, autore, new ArrayList<Categoria>());
	}

	private Libro createLibroSaved(Autore autore) {
		return new Libro(1L, "Prova", 1, 234, autore, new ArrayList<Categoria>());
	}

	@Test
	void should_successfully_save_a_libro() {
		Autore autore = createAutore();
		Libro libroInput = createLibroInput(autore);
		Libro libroSaved = createLibroSaved(autore);

		when(libroRepository.save(any(Libro.class))).thenReturn(libroSaved);

		Libro salvato = libroService.create(libroInput);

		assertEquals(libroSaved, libroInput);

		assertAll("Verifica che tutte le proprietà siano corrette",
				() -> assertEquals(libroSaved.getId(), salvato.getId(), "L'ID dovrebbe essere 1"),
				() -> assertEquals(libroSaved.getTitolo(), salvato.getTitolo(), "Il titolo dovrebbe corrispondere"),
				() -> assertEquals(libroSaved.getISBN(), salvato.getISBN(), "L'ISBN dovrebbe corrispondere"),
				() -> assertEquals(libroSaved.getPagine(), salvato.getPagine(), "Il numero di pagine dovrebbe corrispondere"),
				() -> assertEquals(libroSaved.getAutore().getNome(), salvato.getAutore().getNome(), "L'autore dovrebbe essere lo stesso")
		);

		verify(libroRepository, times(1)).save(eq(libroInput));
	}
}