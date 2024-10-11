package com.libreria.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titolo;

	@JsonProperty("ISBN")
	private int ISBN;

	private int pagine;

	@ManyToOne()
	@JoinColumn(name = "autore_id")
	@JsonBackReference
	private Autore autore;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "libro_categoria",
			joinColumns = @JoinColumn(name = "libro_id"),
			inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<Categoria> categorie;
}
