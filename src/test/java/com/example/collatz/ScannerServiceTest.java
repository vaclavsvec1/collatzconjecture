package com.example.collatz;

import static org.assertj.core.api.Assertions.fail;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.collatz.com.example.collatz.services.ScannerServiceImpl;
import com.example.collatz.com.example.collatz.services.SolvingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ScannerServiceTest {
	@InjectMocks
	ScannerServiceImpl scannerService;
	@Mock
	SolvingServiceImpl solvingService;

	@Test
	public void startingSequence() {
		Mockito.doNothing().when(solvingService).startSolving();
		try {
			scannerService.startingSequence();
			ByteArrayInputStream userInput = new ByteArrayInputStream("N".getBytes());
			System.setIn(userInput);
		} catch (Exception e) {
			fail("Exception occured.");
		}
	}
}
