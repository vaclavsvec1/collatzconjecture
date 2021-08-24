package com.example.collatz.com.example.collatz.services;

public interface SolvingService {
	/*
	 * Problem: https://www.youtube.com/watch?v=094y1Z2wpJg&ab_channel=Veritasium
	 * https://cs.wikipedia.org/wiki/Collatz%C5%AFv_probl%C3%A9m Starts solving
	 * algorithm.
	 */

	public void startSolving();

	/* Solves one number. */

	public void startSolving(String number);
}

