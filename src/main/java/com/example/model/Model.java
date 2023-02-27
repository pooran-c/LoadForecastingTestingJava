package com.example.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;

import com.example.data.GetData;

import java.util.*;

public class Model {
	public ArrayList<Float> data;
	public ArrayList<OffsetDateTime> date;
	public ArrayList<Float> dataRange;
	public ArrayList<ArrayList<Float>> groupedData;
	public ArrayList<Float> smallGroup;
	public ArrayList<ArrayList<Integer>> consumptionInGroup;
	public ArrayList<Integer> consumptionInGroupSmall;
	public ArrayList<ArrayList<Integer>> weight;
	public ArrayList<ArrayList<Double>> posterior;
	public Set<Integer> unique;
	public Float max;
	public Float min;

	public Model(GetData Temp1) {

		this.data = (ArrayList<Float>) Temp1.data;
		this.date = (ArrayList<OffsetDateTime>) Temp1.date;
		this.dataRange = new ArrayList<Float>();
		this.groupedData = new ArrayList<ArrayList<Float>>();
		this.smallGroup = new ArrayList<Float>();
		this.consumptionInGroup = new ArrayList<ArrayList<Integer>>();
		this.consumptionInGroupSmall = new ArrayList<Integer>();
		this.weight = new ArrayList<ArrayList<Integer>>();
		this.posterior = new ArrayList<ArrayList<Double>>();
		this.max = (float) 0;
		this.min = (float) 0;
		this.getMaxMin();
		this.getDataRange();
		this.makeGroup();
		this.putDataToGroup();
		this.getProbablityOfData();
		this.posterior();

	}

	public void getMaxMin() {

		this.max = Collections.max(this.data);
		this.min = Collections.min(this.data);

	}

	public void getDataRange() {
		try {

			Float i = this.min;
			while (i <= this.max + 2) {
				this.dataRange.add(i);
				i = i + 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void makeGroup() {
		try {

			int small_group_size = 2;

			for (int i = 0; i < (this.dataRange.size() - small_group_size); i++) {
				this.smallGroup = new ArrayList<Float>();
				for (int j = 0; j < small_group_size; j++) {
					this.smallGroup.add(this.dataRange.get(i + j));
				}
				// i=i+small_group_size-1;

				this.groupedData.add(this.smallGroup);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void putDataToGroup() {

		for (int i = 0; i < (this.data.size()); i++) {
			for (int j = 0; j < (this.groupedData.size()); j++) {
				if (this.data.get(i) >= Collections.min(this.groupedData.get(j))
						&& this.data.get(i) < Collections.max(this.groupedData.get(j))) {

					this.consumptionInGroupSmall.add(j);

				} else {
				}

			}

		}

	}

	public void getProbablityOfData() {

		Set<Integer> unique = new HashSet<Integer>(this.consumptionInGroupSmall);
		// System.out.println(unique);
		Iterator<Integer> iter = unique.iterator();

		while (iter.hasNext()) {
			ArrayList<Integer> temp1 = new ArrayList<Integer>();
			int temp = iter.next();
			for (int i = 0; i < this.consumptionInGroupSmall.size(); i++) {

				if (temp == this.consumptionInGroupSmall.get(i)) {
					temp1.add(i);

				} else {
				}

			}
			this.weight.add(temp1);
		}
	}

	public void posterior() {

		for (int i = 0; i < this.weight.size(); i++) {
			ArrayList<Double> temp2 = new ArrayList<Double>();

			for (int j = 0; j < this.weight.get(i).size(); j++) {
				double Probablity_of_consumption = (double) this.weight.get(i).size()
						/ this.consumptionInGroupSmall.size();
				double Probablity_of_date_given_consumption = (double) Probablity_of_consumption * 1
						/ this.weight.get(i).size();
				temp2.add(Probablity_of_date_given_consumption);

			}
			this.posterior.add(temp2);

		}
	}
}
