package com.greenformatics.spring.demo.beans;

import java.util.Random;

import org.springframework.stereotype.Repository;

@Repository
public class DefaultSomeRepository implements SomeRepository {

	public void loadSomethingById() throws InterruptedException {
		Thread.sleep(500L + new Random().nextInt(1000));
	}

}
