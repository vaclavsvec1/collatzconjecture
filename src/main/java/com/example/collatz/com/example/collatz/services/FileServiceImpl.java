package com.example.collatz.com.example.collatz.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	DateTimeService dateTimeService;

	@Override
	public String createFile() {
		String fileName = generateFileName();
		System.out.println("Creating CSV file " + fileName);

		File logFile = new File("C:\\Collatz\\" + fileName + ".csv");

		try {
			if (logFile.createNewFile()) {
				System.out.println("File created: " + fileName);
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logFile.getAbsolutePath().toString();
	}

	@Override
	public void writeToFile(String writePath, List<String[]> data) {
		System.out.println("Writing results to file " + writePath);
		try (CSVWriter writer = new CSVWriter(new FileWriter(writePath))) {
			writer.writeAll(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String generateFileName() {
		return "CollatzConjecture" + dateTimeService.getDateTimeCode();
	}

}
