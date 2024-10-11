package com.libreria.services;

import com.libreria.dto.AutoreBasicDto;
import com.libreria.dto.AutoreDetailDto;
import com.libreria.execptions.RecordNotFoundException;
import com.libreria.mapper.AutoreMapper;
import com.libreria.models.Autore;
import com.libreria.repositories.AutoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoreService {

	private final AutoreRepository autoreRepository;

	private final AutoreMapper autoreMapper;

	public AutoreService(AutoreRepository autoreRepository, AutoreMapper autoreMapper) {
		this.autoreRepository = autoreRepository;
		this.autoreMapper = autoreMapper;
	}

	public List<AutoreBasicDto> findAll() {
		List<Autore> autori = autoreRepository.findAll();
		return autori
				.stream()
				.map(autoreMapper::autoreToAutoreResponseDto)
				.toList();
	}

	public AutoreDetailDto findById(Long id) {
		Autore autore = autoreRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Autore non trovato"));
		return autoreMapper.autoreDetailDto(autore);
	}

	public Autore create(Autore autore) {
		return autoreRepository.save(autore);
	}

	public void delete(Long id) {
		autoreRepository.deleteById(id);
	}
}
