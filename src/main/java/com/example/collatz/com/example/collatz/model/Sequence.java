package com.example.collatz.com.example.collatz.model;

import java.math.BigInteger;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Sequence {
	private String sequenceString;
	private ArrayList<BigInteger> solvedNumbers;
}
