package com.libreria.controllers;

import com.libreria.models.Autore;
import com.libreria.models.Libro;
import com.libreria.services.LibroService;
import org.hibernate.sql.model.PreparableMutationOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro")
public class LibroController {

	private final LibroService service;

	public LibroController(LibroService service) {
		this.service = service;
	}

	@GetMapping("/libri")
	public List<Libro> findLibri() {
		return service.findAllLibri();
	}

	@GetMapping("/{libro-id}")
	public ResponseEntity<Libro> getLibroById(@PathVariable("libro-id") Long id) {
		Libro libro = service.findLibroById(id);
		return ResponseEntity.ok(libro);
	}

	@GetMapping("/mediaNumeroPagine/{idAutore}")
	public int avgMediaNumeroPagineByIdAutore(@PathVariable("idAutore") Long id) {
		return service.getAvgPagineByIDAutore(id);
	}

	@PostMapping("")
	public Libro create(@RequestBody Libro libro) {
		return service.create(libro);
	}

	@DeleteMapping("/{libro-id}")
	public void delete(@PathVariable("libro-id") Long id) {
		service.delete(id);
	}

	@GetMapping("/search")
	public List<Libro> search(
            @RequestParam(required = false) String cognome,
            @RequestParam(required = false) String titolo
	) {
		return service.searchByAutoreOrTitolo(cognome, titolo);
	}

	@PatchMapping("/{id}")
	public Libro partialUpdate(
			@PathVariable("id") Long id,
			@RequestBody Libro patch
	) {
		return service.patch(id, patch);
	}
}
