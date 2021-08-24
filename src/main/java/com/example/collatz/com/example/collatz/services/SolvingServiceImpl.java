package com.example.collatz.com.example.collatz.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.collatz.com.example.collatz.model.NumberResult;
import com.example.collatz.com.example.collatz.model.Sequence;

@Service
public class SolvingServiceImpl implements SolvingService {
	private static final Integer MAX_ITER = 100;
	private static final Integer TEST_SAMPLE_SIZE = 500;
	private static final BigInteger STOP_VALUE = BigInteger.valueOf(1L);
	private static final Integer INTEGER_BIT_SIZE = 20;
	private static final String EMPTY_SEQUENCE = "";
	private static final Integer MAX_DUPLICITY = 20;

	private ArrayList<BigInteger> solvedNumbers = new ArrayList<>();

	@Autowired
	private FileService fileService;

	@Override
	public void startSolving(String number) {
		calculateResultForNumber(new BigInteger(number));
	}

	@Override
	public void startSolving() {
		Long computationStart = System.currentTimeMillis();

		System.out.println("Starting to solve problem...");
		String writePath = fileService.createFile();
		fileService.writeToFile(writePath, getResults());

		Long computationTime = System.currentTimeMillis() - computationStart;
		System.out.println("Problem solved in " + computationTime + " millisec");
	}

	private List<String[]> getResults() {
		List<String[]> header = getHeader();
		return calculateSample(header);
	}

	private List<String[]> calculateSample(List<String[]> header) {
		List<BigInteger> numberSample = generateNumberSample();
		for (BigInteger number : numberSample) {
			NumberResult numberResult = calculateResultForNumber(number);
			String[] iterationResult = { number.toString(), numberResult.getTestValidation(),
					numberResult.getIterationCount(), numberResult.getSequence() };
			header.add(iterationResult);
		}

		return header;
	}

	private NumberResult calculateResultForNumber(BigInteger number) {
		Sequence sequence = Sequence.builder().sequenceString(EMPTY_SEQUENCE).solvedNumbers(new ArrayList<>()).build();
		BigInteger stepResult = number;
		Integer counter = 0;
		do {
			counter++;
			if (isOdd(stepResult)) {
				stepResult = stepResult.multiply(BigInteger.valueOf(3L));
				stepResult = stepResult.add(BigInteger.valueOf(1L));
				sequence = getSequence(sequence, stepResult);
			} else {
				stepResult = stepResult.divide(BigInteger.valueOf(2L));
				sequence = getSequence(sequence, stepResult);
			}

		} while (!stepResult.equals(STOP_VALUE) && counter < MAX_ITER && !solvedNumbers.contains(stepResult)); 
																												

		Boolean testFailed = counter.equals(MAX_ITER);

		System.out.println("Number " + number.toString() + " tested in " + counter.toString() + " iterations."
				+ " Test failed: " + testFailed.toString() + ". Sequence: " + sequence.getSequenceString());

		if (testFailed) {
			return NumberResult.builder().sequence(sequence.getSequenceString()).testValidation("false")
					.iterationCount(counter.toString()).build();
		} else {
			saveSolvedNumbers(sequence.getSolvedNumbers());
			return NumberResult.builder().sequence(sequence.getSequenceString()).testValidation("true")
					.iterationCount(counter.toString()).build();
		}
	}

	private void saveSolvedNumbers(ArrayList<BigInteger> newSolvedNumbers) {
		for (BigInteger newSolvedNumber : newSolvedNumbers) {
			if (!solvedNumbers.contains(newSolvedNumber)) {
				solvedNumbers.add(newSolvedNumber);
			}
		}
	}

	private Sequence getSequence(Sequence sequence, BigInteger stepResult) {
		if (!sequence.getSequenceString().isEmpty()) {
			ArrayList<BigInteger> solvedNumbers = sequence.getSolvedNumbers();
			solvedNumbers.add(stepResult);

			sequence.setSequenceString(sequence.getSequenceString() + "-" + stepResult.toString());
			sequence.setSolvedNumbers(solvedNumbers);
			return sequence;
		} else {
			ArrayList<BigInteger> solvedNumbers = new ArrayList<>();
			solvedNumbers.add(stepResult);
			return Sequence.builder().sequenceString(stepResult.toString()).solvedNumbers(solvedNumbers).build();
		}
	}

	private List<BigInteger> generateNumberSample() {
		List<BigInteger> numberSample = new ArrayList<>();
		Integer sampleSize = 0;
		Integer duplicityCount = 0;
		do {
			BigInteger randomNumber = getRandomNumber();
			if (numberSample.contains(randomNumber)) {
				duplicityCount++;
				System.out.println(randomNumber + " is already in sample. Generating anotherone.");
				if (duplicityCount.equals(MAX_DUPLICITY)) {
					System.out.println(
							"Breaking generating sample after " + MAX_DUPLICITY.toString() + " duplicated values.");
					break;
				}
			} else {
				numberSample.add(randomNumber);
				duplicityCount = 0;
			}
			sampleSize = numberSample.size();
		} while (!sampleSize.equals(TEST_SAMPLE_SIZE));
		return numberSample;
	}

	private boolean isOdd(BigInteger i) {
		return i.testBit(0);
	}

	private List<String[]> getHeader() {
		List<String[]> list = new ArrayList<>();
		String[] header = { "Tested Number", "Test Validation", "Iteration Count", "Sequence" };

		list.add(header);
		return list;
	}

	private BigInteger getRandomNumber() {
		BigInteger randomNumber = new BigInteger(INTEGER_BIT_SIZE, new Random()); // Give you a number between 0 and
																					// 2^512 - 1
		randomNumber = randomNumber.setBit(0); // Set the first bit so number is between 2^511 and 2^512 - 1
		System.out.println("Generated number " + randomNumber.toString());

		return randomNumber;
	}
}
