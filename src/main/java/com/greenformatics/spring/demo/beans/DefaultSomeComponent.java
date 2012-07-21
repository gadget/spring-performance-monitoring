package com.greenformatics.spring.demo.beans;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class DefaultSomeComponent implements SomeComponent {

	public void calculateSomething() throws InterruptedException {
		Thread.sleep(500L + new Random().nextInt(500));
	}

}
