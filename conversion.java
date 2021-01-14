package com.flexion;

import java.util.ArrayList;
import java.util.Scanner;

public class conversion {

	static ArrayList<String> temps = new ArrayList<String>(); // Create an ArrayList object
	static ArrayList<String> volumes = new ArrayList<String>(); // Create an ArrayList object

	static boolean theResult = true;
	static String inputValue, inputUnit, targetUnit, studentResponse;
	static double inputValueRounded = 0.0;
	static double studentResponseRounded = 0.0;
	static double authoritativeAnswerRounded = 0.0;
	static final String spaces = "  ";
	static final String correctStr = "correct";
	static final String incorrectStr = "incorrect";
	static final String invalidStr = "invalid";
	static StringBuffer sbf = new StringBuffer("");
	static boolean moreToDo = true;
	static Scanner sc = new Scanner(System.in);

	public static void seeResult() {
		sbf.append(inputValue);
		sbf.append(spaces);
		sbf.append(inputUnit);
		sbf.append(spaces);
		sbf.append(targetUnit);
		sbf.append(spaces);
		sbf.append(studentResponse);
		sbf.append(spaces);
	}

	static double farhenheitToRankine(double f) {
		return f + 459.67;
	}

	public static double kelvinToFahrenheit(Double k) {
		double f = (((k - 273) * 9 / 5.0) + 32);
		return f;
	}

	public static double cupsToLiters(double c) {
		double l = c * 0.23659;
		return l;
	}

	public static double roundIt(double value, int places) {
		double scale = Math.pow(10, places);
		return Math.round(value * scale) / scale;
	}

	public static void process() {
		System.out.println("Enter Input Numerical Value");

		inputValue = com.flexion.util.nullCheck(sc.nextLine());

		System.out.println("Enter Input Unit of Measure");
		inputUnit = sc.nextLine();

		System.out.println("Enter Target Unit of Measure");
		targetUnit = sc.nextLine();
		System.out.println("Enter Student Response");
		studentResponse = sc.nextLine();

		seeResult();

		try {
			if ((!com.flexion.data.checkForNumeric(inputValue))
					&& (!com.flexion.data.checkForNumeric(studentResponse))) {

				sbf.append("incorrect");

			} else {
				// Numeric checks passed - keep going

				inputValueRounded = roundIt(Double.parseDouble(inputValue), 1);
				studentResponseRounded = roundIt(Double.parseDouble(studentResponse), 1);

				theResult = com.flexion.data.verifyUnitInputs(temps, volumes, inputUnit, targetUnit);

				// No need to verify units if non numeric entered
				if (theResult) {
					if (inputUnit.equalsIgnoreCase("fahrenheit") && (targetUnit.equalsIgnoreCase("rankine"))) {
						authoritativeAnswerRounded = roundIt(farhenheitToRankine(Double.parseDouble(inputValue)), 1);
					} else if (inputUnit.equalsIgnoreCase("Kelvin") && (targetUnit.equalsIgnoreCase("fahrenheit"))) {
						authoritativeAnswerRounded = roundIt(kelvinToFahrenheit(inputValueRounded), 1);
					} else if (inputUnit.equalsIgnoreCase("cups") && (targetUnit.equalsIgnoreCase("liters"))) {
						authoritativeAnswerRounded = roundIt(cupsToLiters(inputValueRounded), 1);
					}

					int dc = Double.compare(studentResponseRounded, authoritativeAnswerRounded);
					if (dc == 0) {

						sbf.append("correct");
					} else {

						sbf.append("incorrect");
					}
				} else {

					sbf.append("invalid");
				}
			}
		} catch (NumberFormatException nfe) {
			sbf.append("incorrect");
		}

		System.out.println(sbf.toString());
		sbf.setLength(0); // clear it out
		System.out.println("Continue Y/N");

		if (sc.nextLine().equalsIgnoreCase("N")) {
			// any entry other than n - and we keep going
			moreToDo = false;
		}

	}

	public static void main(String[] args) {

		com.flexion.data.loadTemps(temps);
		com.flexion.data.loadVolumes(volumes);

//		System.out.println(volumes);

		

		while (moreToDo) {
			process();
		}

		sc.close();

		System.exit(1);
	}
}
