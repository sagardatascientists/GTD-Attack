package GtdClassificationCode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class dictionaryCreation {

	public String inputFile = "resources/FeatureSelectedData.txt";
	public String outPutDictionaryFile = "resources/GroupNameToId.csv";

	public static void main(String[] args) throws Exception {
		dictionaryCreation ob = new dictionaryCreation();
		ob.readInputFile();
	}

	/**
	 * This method create dictionary of terrorist group name with its unique ID
	 * 
	 * @throws Exception
	 */
	private void readInputFile() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
		String line = "";
		Map<String, Integer> groupNameToFrequency = new HashMap<String, Integer>();
		while ((line = reader.readLine()) != null) {
			String data[] = line.split("\t");
			String groupName1 = data[8].toLowerCase().trim();
			String groupName2 = data[9].toLowerCase().trim();
			String groupName3 = data[10].toLowerCase().trim();
			if (groupNameToFrequency.containsKey(groupName1)) {
				int c = groupNameToFrequency.get(groupName1);
				c++;
				groupNameToFrequency.put(groupName1, c);
			} else {
				int c = 1;
				groupNameToFrequency.put(groupName1, c);
			}
			if (!groupName2.equals("") || !groupName2.isEmpty()) {
				if (groupNameToFrequency.containsKey(groupName2)) {
					int c = groupNameToFrequency.get(groupName2);
					c++;
					groupNameToFrequency.put(groupName2, c);
				} else {
					int c = 2;
					groupNameToFrequency.put(groupName2, c);
				}
			}

			if (!groupName3.equals("") || !groupName3.isEmpty()) {
				if (groupNameToFrequency.containsKey(groupName3)) {
					int c = groupNameToFrequency.get(groupName3);
					c++;
					groupNameToFrequency.put(groupName3, c);
				} else {
					int c = 2;
					groupNameToFrequency.put(groupName3, c);
				}

			}

		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outPutDictionaryFile)));
		Iterator<String> itrMap = groupNameToFrequency.keySet().iterator();
		int i = 1;
		while (itrMap.hasNext()) {
			String groupName = (String) itrMap.next();
			int groupFrequency = groupNameToFrequency.get(groupName);
			writer.write(i + "\t" + groupName + "\t" + groupFrequency);
			writer.write("\n");
			i++;
		}
		reader.close();
		writer.close();
	}
}
