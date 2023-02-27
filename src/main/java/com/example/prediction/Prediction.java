package com.example.prediction;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.example.model.Model;

public class Prediction {
	public ArrayList<ArrayList<Integer>> weight;
	public ArrayList<OffsetDateTime> date;
	public ArrayList<ArrayList<Double>> posterior_matrix;
	public ArrayList<ArrayList<Double>> probablity;
	public ArrayList<ArrayList<Double>> emptyPosteriorMatrix;
	public ArrayList<ArrayList<Float>> groupedData;
	public ArrayList<Integer> consumptionInSmallGroup;
	public double posterior;
	public double prior;
	public double result;
	public double consumption;
	public int dateIndex;
	public int uniqueIndex;
	public int ingroup;
	public Set<Integer> uniqueDataGroup;

	public Prediction(Model Temp2, double consumption, int dateIndex, double prior) {
		this.date = Temp2.date;
		this.weight = Temp2.weight;
		this.posterior_matrix = new ArrayList<ArrayList<Double>>();
		this.probablity = Temp2.posterior;
		this.consumptionInSmallGroup = Temp2.consumptionInGroupSmall;
		this.groupedData = Temp2.groupedData;
		this.uniqueDataGroup = Temp2.unique;
		this.consumption = consumption;
		this.dateIndex = dateIndex;
		this.prior = prior;

		this.posterior = (double) this.getposterior();
		this.result = this.getprobablityofload();
	}

	public double getposterior() {
		this.ingroup = 0;
		// ArrayList<Integer> indexToBeChecked = new ArrayList<Integer>();
		double Posterior = 0;
		for (int j = 0; j < (this.groupedData.size()); j++) {

			if (this.consumption >= Collections.min(this.groupedData.get(j))
					&& this.consumption < Collections.max(this.groupedData.get(j))) {

				this.ingroup = j;

			} else {
				// do nothing

			}

		}

		Set<Integer> unique1 = new HashSet<Integer>(this.consumptionInSmallGroup);
		Iterator<Integer> iter = unique1.iterator();
		int counter = 0;

		while (iter.hasNext()) {
			counter = counter + 1;

			int temp = iter.next();
			if (this.ingroup == temp) {

				this.uniqueIndex = counter;

			}

		}

		for (int i = 0; i < this.weight.get(this.uniqueIndex).size(); i++) {

			if (this.weight.get(this.uniqueIndex).get(i) == this.dateIndex) {
				Posterior = this.probablity.get(this.uniqueIndex).get(i);
			} else {
				// do nothing
			}

		}
		return Posterior;

	}

	public double getprobablityofload() {
		double result = (double) (this.posterior * this.prior) * this.date.size();

		return result;
	}

}
