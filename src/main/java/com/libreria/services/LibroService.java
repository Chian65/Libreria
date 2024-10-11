package com.libreria.services;

import com.libreria.execptions.RecordNotFoundException;
import com.libreria.models.Autore;
import com.libreria.models.Libro;
import com.libreria.repositories.LibroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

	private final LibroRepository libroRepository;

	public LibroService(LibroRepository libroRepository) {
		this.libroRepository = libroRepository;
	}

	public List<Libro> findAllLibri() {
		return libroRepository.findAll();
	}

	public Libro findLibroById(Long id) {
		return libroRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Libro con ID " + id + " non trovato"));
	}

	public Libro create(Libro libro) {
		return libroRepository.save(libro);
	}

	public void delete(Long id) {
		libroRepository.deleteById(id);
	}

	public List<Libro> searchByAutoreOrTitolo(String cognome, String titolo) {
		return libroRepository.findByAutore_CognomeContainingOrTitoloContaining(cognome, titolo);
	}

	public Libro patch(Long id, Libro patch) {
		Libro libroToUpdate = libroRepository.findById(id).get();

		if (patch.getAutore() != null) {
			libroToUpdate.setAutore(patch.getAutore());
		}

		if (patch.getTitolo() != null) {
			libroToUpdate.setTitolo(patch.getTitolo());
		}

		if (patch.getISBN() != 0) {
			libroToUpdate.setISBN(patch.getISBN());
		}

		if (patch.getPagine() != 0) {
			libroToUpdate.setPagine(patch.getPagine());
		}

		return libroRepository.save(libroToUpdate);
	}

	public int getAvgPagineByIDAutore(Long id) {
		return libroRepository.avgPagineByAutoreId(id);
	}
}
