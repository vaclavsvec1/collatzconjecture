package com.example.collatz.com.example.collatz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NumberResult {
	private String sequence;
	private String testValidation;
	private String iterationCount;
}
