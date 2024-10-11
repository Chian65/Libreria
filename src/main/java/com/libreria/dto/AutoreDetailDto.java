package com.libreria.dto;

import java.util.List;

public record AutoreDetailDto(
		String nome,
		String cognome,
		List<String> titoloLibri
) {
}
