package com.example.app;

import java.util.ArrayList;
import java.util.Random;

import com.example.data.GetData;
import com.example.model.Model;
import com.example.prediction.Prediction;

public class Application {

	public static void main(String args[]) {


		Application app = new Application();
		app.predict(0);

	}

	public void predict(int dataIndex) {

		ArrayList<Double> predicted = new ArrayList<Double>();

		Model model = new Model(new GetData());
		System.out.print("Working");

		for (int i = 0; i < 5000; i++) {
			for (int j = 0; j < model.dataRange.size(); j++) {
				Prediction temp6 = new Prediction(model, model.dataRange.get(j), i, 1.0);
				if (temp6.result > 0) {

					Double avr = get_avrage(model.groupedData.get(temp6.ingroup));

					predicted.add(avr);
					System.out.println(predicted);

					break;

				} else {
					// do nothing
				}
			}

		}
	}

	public double get_avrage(ArrayList<Float> a) {
		float avrage;

		double random = new Random().nextDouble();
		avrage = a.get(0) + a.get(1);

		return (double) (avrage / 2) + random;
	}
}
