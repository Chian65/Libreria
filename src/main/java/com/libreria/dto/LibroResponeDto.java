package com.libreria.dto;

public record LibroResponeDto(
		String titolo,
		int ISBN,
		int pagine,
		String autoreNome,
		String autoreCogome
) {
}
