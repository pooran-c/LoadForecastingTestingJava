package com.example.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GetData {

	public  List<String> all_data = new ArrayList<String>();

	public  List<OffsetDateTime> date = new ArrayList<OffsetDateTime>();
	public  List<Float> data = new ArrayList<Float>();

	public int interval = 5;
	public GetData() {
		this.read_csv();
		this.get_date_and_data();
	}

	public void read_csv() {
		try {
			String pathFile = "C:\\Users\\bishal.ghimire\\Desktop\\data\\loadValues.csv";
			Scanner sc = new Scanner(new File(pathFile));

			// parsing a CSV file into the constructor of Scanner class
			sc.useDelimiter(",");

			// setting comma as delimiter pattern
			while (sc.hasNext()) {
				// System.out.println(sc.next());
				all_data.add(sc.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * it creates the array of date time rather than reading from CSV
	 */

	public void get_date_and_data() {
		try {

			/*
			 * "2018-05-01T00:00:00+02:00" is the starting date in csv
			 */

			OffsetDateTime z = OffsetDateTime.parse("2018-05-01T00:00:00+02:00", DateTimeFormatter.ISO_DATE_TIME);
			for (int i = 0; i < all_data.size() - 1; i++) {

				String b = all_data.get(i + 1).toString();

				date.add(z);
				data.add(Float.parseFloat(b));
				i = i + 1;
				z = z.plusMinutes(5);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}