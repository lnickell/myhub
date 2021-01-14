package com.flexion;

import java.util.ArrayList;

public class data {

	public static ArrayList<String> loadTemps(ArrayList<String> temps) {
		temps.add("Kelvin");
		temps.add("Celsius");
		temps.add("Fahrenheit");
		temps.add("Rankine");

		System.out.println(temps);
		return temps;
	}

	public static ArrayList<String> loadVolumes(ArrayList<String> volumes) {

		volumes.add("liters");
		volumes.add("tablespoons");
		volumes.add("cubic-inches");
		volumes.add("cups");
		volumes.add("cubic-feet");
		volumes.add("gallons");

		System.out.println(volumes);
		return volumes;
	}

	public static boolean checkForNumeric(String valueIn) {
		boolean numeric = true;

		numeric = valueIn.matches("-?\\d+(\\.\\d+)?");

		return numeric;
	}

	/*
	 * Verify the units are in the arrays
	 * 
	 */
	public static boolean verifyUnitInputs(ArrayList<String> inputUnitArrayList, ArrayList<String> targetUnitArrayList,
			String inputUnit, String targetUnit) {
		boolean theResult = true;

		int iundx1 = inputUnitArrayList.indexOf(inputUnit);
		int iundx2 = inputUnitArrayList.indexOf(targetUnit);

		int tundx1 = targetUnitArrayList.indexOf(inputUnit);
		int tundx2 = targetUnitArrayList.indexOf(targetUnit);

		// Note to Laura - could use contains here

		if (iundx1 >= 0 && iundx2 >= 0 || tundx1 >= 0 && tundx2 >= 0) {
			// theResult already init to true
		} else {
			theResult = false;
		}

		return theResult;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
