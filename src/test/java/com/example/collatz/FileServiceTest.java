package com.example.collatz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.collatz.com.example.collatz.services.DateTimeService;
import com.example.collatz.com.example.collatz.services.FileServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {
	@Mock
	private DateTimeService dateTimeService;
	@InjectMocks
	private FileServiceImpl fileService;

	private static final String FILE_DATE = "20210804210343";
	private static final String FILE_PATH = "C:\\Collatz\\CollatzConjecture" + FILE_DATE + ".csv";

	@Test
	public void createFile() throws IOException {
		givenDateTimeCode();

		fileService.createFile();
		File file = FileUtils.getFile(FILE_PATH);

		Assertions.assertTrue(file.exists());

		deleteTestFile();
	}

	@Test
	public void writeToFile() throws Exception {

		givenDateTimeCode();

		fileService.createFile();
		String[] writeString = { "abc" };
		List<String[]> stringList = Collections.singletonList(writeString);
		fileService.writeToFile(FILE_PATH, stringList);
		Assertions.assertEquals("\"abc\"", readTestCSV()[0]);

		deleteTestFile();
	}

	private String[] readTestCSV() throws Exception {
		String row;
		String[] data = null;
		BufferedReader csvReader = new BufferedReader(new FileReader(FILE_PATH));
		while ((row = csvReader.readLine()) != null) {
			data = row.split(",");
		}
		csvReader.close();
		return data;
	}

	private void givenDateTimeCode() {
		Mockito.when(dateTimeService.getDateTimeCode()).thenReturn(FILE_DATE);
	}

	private void deleteTestFile() throws IOException {
		FileUtils.forceDelete(FileUtils.getFile(FILE_PATH));
	}

}
