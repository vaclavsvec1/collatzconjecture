package com.example.collatz.com.example.collatz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.collatz.com.example.collatz.services.ScannerService;
import com.example.collatz.com.example.collatz.services.SolvingService;

@SpringBootApplication
public class CollatzconjectureApplication {
	@Autowired
	private static ScannerService scannerService;

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = SpringApplication.run(CollatzconjectureApplication.class, args);
		scannerService = applicationContext.getBean(ScannerService.class);
		scannerService.startingSequence();
	}
}
