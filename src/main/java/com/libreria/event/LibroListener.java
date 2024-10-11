package com.libreria.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LibroListener {

	@EventListener
	public void handleLibroCreatoEvent(LibroCreatoEvent event) {
		System.out.println("Libro creato: " + event.getLibro().getTitolo());
	}
}
