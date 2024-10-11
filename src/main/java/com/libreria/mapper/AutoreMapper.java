package com.libreria.mapper;

import com.libreria.dto.AutoreBasicDto;
import com.libreria.dto.AutoreDetailDto;
import com.libreria.models.Autore;
import com.libreria.models.Libro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoreMapper {

	public AutoreBasicDto autoreToAutoreResponseDto(Autore autore) {
		return new AutoreBasicDto(
				autore.getNome(),
				autore.getCognome()
		);
	}

	public AutoreDetailDto autoreDetailDto(Autore autore) {

		List<String> titoliLibri = autore.getLibri()
				.stream()
				.map(Libro::getTitolo)
				.toList();

		return new AutoreDetailDto(
				autore.getNome(),
				autore.getCognome(),
				titoliLibri
		);
	}
}
