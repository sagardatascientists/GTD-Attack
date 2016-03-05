package GtdClassificationCode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;


public class createFeatureFile
{
	String dictionaryFile = "resources/GroupNameToId.csv";
	String inputFile = "resources/FeatureSelectedData.txt";
	String outPutFeatureFile = "resources/FeautureFileForGTD.csv";

	public static void main(String[] args) throws Exception
	{
		createFeatureFile ob =  new createFeatureFile();
		ob.readInputFile();

	}
	
	/**
	 * this method create final feature file that we need to feed a Machine learning algorithm
	 * @throws Exception
	 */
	private void readInputFile() throws Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFile)));
		String line = "";
		HashMap<String, Integer> grpNameToId = new HashMap<String, Integer>();

		while ((line = reader.readLine()) != null)
		{
			int id = Integer.parseInt(line.split("\t")[0]);
			String grpname = line.split("\t")[1].toLowerCase().trim();
			grpNameToId.put(grpname,id);
		}
		reader.close();
		
		reader = new BufferedReader(new FileReader(inputFile));
		line = "";
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outPutFeatureFile)));

		while ((line = reader.readLine()) != null)
		{
			String data[] = line.split("\t");
			String eventid = data[0];
			String iyear = data[1];
			String imonth = data[2];
			String iday = data[3];
			String country = data[4];
			// String region = data[9];
			String attacktsuccess = data[5];
			String attacktype = data[6];
			String targtype = data[7];
			String groupName1 = data[8].toLowerCase().trim();
			String groupName2 = data[9].toLowerCase().trim();
			String groupName3 = data[10].toLowerCase().trim();
			String weaptype = data[11];
			
			String classLabelClaimed1 = "null";
			if(data.length>12)
			{
				classLabelClaimed1 = data[12];
			}
			String classLabelClaimed2 ="0";
			if(data.length>13)
			{
				classLabelClaimed2 = data[13];
			}
			String classLabelClaimed3 = "0";
			if(data.length>14)
			{
				classLabelClaimed3 = data[14];	
			}
			
			int grp1Id;
			int grp2Id;
			int grp3Id;
			
			
			if(grpNameToId.containsKey(groupName1) && !classLabelClaimed1.equals("-9") && !classLabelClaimed1.equals("null"))
			{
				grp1Id = grpNameToId.get(groupName1);
				writer.write(imonth+","+iday+","+country+","+attacktsuccess+","+attacktype+","+targtype+","+grp1Id+","+weaptype+","+classLabelClaimed1);
				writer.write("\n");
			}
			
			if(!groupName2.equals("") || !groupName2.isEmpty())
			{
				if(grpNameToId.containsKey(groupName2) && !classLabelClaimed2.equals("-9"))
				{
					grp2Id = grpNameToId.get(groupName2);
					writer.write(imonth+","+iday+","+country+","+attacktsuccess+","+attacktype+","+targtype+","+grp2Id+","+weaptype+","+classLabelClaimed2);
					writer.write("\n");
				}
			}
			
			if(!groupName3.equals("") || !groupName3.isEmpty())
			{
				if(grpNameToId.containsKey(groupName3))
				{
					grp3Id = grpNameToId.get(groupName3);
					writer.write(imonth+","+iday+","+country+","+attacktsuccess+","+attacktype+","+targtype+","+grp3Id+","+weaptype+","+classLabelClaimed3);
					writer.write("\n");
				}
			}
		}
		writer.close();
	}
}

