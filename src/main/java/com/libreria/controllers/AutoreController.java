package com.libreria.controllers;

import com.libreria.dto.AutoreBasicDto;
import com.libreria.dto.AutoreDetailDto;
import com.libreria.models.Autore;
import com.libreria.services.AutoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autore")
public class AutoreController {

	private final AutoreService autoreService;

	public AutoreController(AutoreService autoreService) {
		this.autoreService = autoreService;
	}

	@GetMapping("/autori")
	public List<AutoreBasicDto> findAll() {
		return autoreService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AutoreDetailDto> findAutoreById(@PathVariable("id") Long id) {
		AutoreDetailDto autore = autoreService.findById(id);
		return ResponseEntity.ok(autore);
	}


	@PostMapping("")
	public Autore create(@RequestBody Autore autore) {
		return autoreService.create(autore);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		autoreService.delete(id);
	}

}
