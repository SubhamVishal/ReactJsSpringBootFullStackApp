package com.subham.demo;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.subham.demo.model.Event;
import com.subham.demo.model.Group;
import com.subham.demo.model.GroupRepository;

@Component
public class Initializer implements CommandLineRunner {
	private final GroupRepository repository;

	public Initializer(GroupRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		Stream.of("Denver JUG", "Utah JUG", "Seattle JUG", "Richmond JUG")
				.forEach(name -> repository.save(new Group(name)));
		Group djug = repository.findByName("Denver JUG");
		Event e = Event.builder().title("Full Stack Reactive").description("Reactive with Spring Boot + React")
				.date(Instant.now()).build();
		djug.setEvents(Collections.singleton(e));
		repository.save(djug);

		repository.findAll().forEach(System.out::println);
	}

}
