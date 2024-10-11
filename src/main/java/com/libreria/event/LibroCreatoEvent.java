package com.libreria.event;

import com.libreria.models.Libro;
import org.springframework.context.ApplicationEvent;

public class LibroCreatoEvent extends ApplicationEvent {
	private final Libro libro;

	public LibroCreatoEvent(Object source, Libro libro) {
		super(source);
		this.libro = libro;
	}

	public Libro getLibro() {
		return libro;
	}
}
