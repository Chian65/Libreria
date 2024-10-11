package com.libreria.repositories;

import com.libreria.models.Autore;
import com.libreria.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

	public List<Libro> findByAutore_CognomeContainingOrTitoloContaining(String cognome, String titolo);

	@Query("SELECT AVG(l.pagine) FROM Libro l WHERE l.autore.id = :idAutore")
	int avgPagineByAutoreId(@Param("idAutore") Long idAutore);
}
