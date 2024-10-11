package com.libreria.repositories;

import com.libreria.models.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AutoreRepository extends JpaRepository<Autore, Long> {
}
