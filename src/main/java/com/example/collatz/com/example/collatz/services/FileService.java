package com.example.collatz.com.example.collatz.services;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface FileService {
	/* Creates CSV file. */

	public String createFile();

	/* Writes result to CSV file. */

	void writeToFile(String writePath, List<String[]> data);
}
