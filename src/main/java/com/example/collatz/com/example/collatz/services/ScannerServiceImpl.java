package com.example.collatz.com.example.collatz.services;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScannerServiceImpl implements ScannerService {

	private static final String YES_OPTION = "Y";
	private static final String NO_OPTION = "N";

	@Autowired
	private SolvingService solvingService;

	@Override
	public void startingSequence() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to solve specific number? Y - Yes; N - No");
		String scannedOption = scanner.nextLine();

		if (scannedOption.equals(YES_OPTION)) {
			System.out.println("Enter your number:");
			String scannedNumber = scanner.nextLine();
			solvingService.startSolving(scannedNumber);
		} else if (scannedOption.equals(NO_OPTION)) {
			System.out.println("Solving random numbers...");
			solvingService.startSolving();
		} else {
			scanner.close();
			throw new Exception("Unsopported option.");
		}
		scanner.close();
	}

}
