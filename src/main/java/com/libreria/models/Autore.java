package com.libreria.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Autore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String cognome;

	@OneToMany(mappedBy = "autore"  , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Libro> libri;
}
