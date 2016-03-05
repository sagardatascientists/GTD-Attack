package GtdClassificationCode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class featureSelection {
	String inputFile = "resources/InputDataset1992_2014GTD.txt";
	String outPutFeatureSelectedFile = "resources/FeatureSelectedData.txt";

	public static void main(String[] args) throws Exception {
		featureSelection ob = new featureSelection();
		ob.readInputFile();
	} 	

	/**
	 * this method select value features from all features.
	 * @throws Exception
	 */
	private void readInputFile() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
		String line = "";
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outPutFeatureSelectedFile)));
		boolean isFirst = true;
		while ((line = reader.readLine()) != null) {
			if (isFirst) {
				isFirst = false;
				continue;
			}
			String data[] = line.split("\t");
			String eventid = data[0];
			String iyear = data[1];
			String imonth = data[2];
			String iday = data[3];
			String country = data[7];
			// String region = data[9];
			String attacktsuccess = data[26];
			String attacktype = data[28];
			String targtype = data[34];
			String groupName1 = data[58];
			String groupName2 = data[60];
			String groupName3 = data[62];
			String weaptype = data[80];
			String classLabelClaimed1 = data[70];
			String classLabelClaimed2 = data[73];
			String classLabelClaimed3 = data[76];

			String AllData = eventid + "\t" + iyear + "\t" + imonth + "\t" + iday + "\t" + country + "\t"
					+ attacktsuccess + "\t" + attacktype + "\t" + targtype + "\t" + groupName1 + "\t" + groupName2
					+ "\t" + groupName3 + "\t" + weaptype + "\t" + classLabelClaimed1 + "\t" + classLabelClaimed2 + "\t"
					+ classLabelClaimed3 + "\t";
			writer.write(AllData + "\n");

		}
		reader.close();
		writer.close();
	}
}
