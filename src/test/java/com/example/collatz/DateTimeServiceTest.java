package com.example.collatz;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.collatz.com.example.collatz.services.DateTimeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DateTimeServiceTest {
	@InjectMocks
	DateTimeServiceImpl dateTimeService;
	
	@Test
	public void getDateTimeCode() {
		Integer year = LocalDateTime.now().getYear();
		
		String dateTimeCode = dateTimeService.getDateTimeCode();
		Assertions.assertNotNull(dateTimeCode);
		Assertions.assertTrue(dateTimeCode.contains(year.toString()));
	}

}
