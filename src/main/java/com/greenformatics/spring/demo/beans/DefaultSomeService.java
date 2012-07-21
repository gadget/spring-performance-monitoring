package com.greenformatics.spring.demo.beans;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class DefaultSomeService implements SomeService {

	@Inject
	private SomeRepository repository;
	@Inject
	private SomeComponent component;

	public void loadSomething() throws InterruptedException {
		repository.loadSomethingById();
		component.calculateSomething();
	}

}
