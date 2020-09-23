package com.csis3275.servlet;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.StandardOpenOption.APPEND;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//Class handling data on the file.
public class HandleFile {
	
	//Declaring variables
	String modelName;
	String modelPros [];
	String modelCons [];
	
	//Constructor	
	public HandleFile(String modelName, String [] modelPros, String [] modelCons) {
		
		this.modelName = modelName;
		this.modelPros = modelPros.clone();
		this.modelCons = modelCons.clone();
		
		writingCSV();
		writingJSON();
		
	}
	
			//Method to write a JSON file
			public void writingJSON() {
				
				JSONObject newModel = new JSONObject();
				
				JSONArray pros = new JSONArray();
				for (String pro : modelPros) {
					pros.add(pro);
				}
				
				JSONArray cons = new JSONArray();
				for (String con : modelCons) {
					cons.add(con);
				}
				
				newModel.put("Model Name", modelName);
				newModel.put("Pros", pros);
				newModel.put("Cons", cons);
				
				String filePath = "C:\\Users\\fcasa\\eclipse-workspace\\main\\data.json";
				File file = new File(filePath);
				  if (!file.exists()) {
					System.out.println("File does not exist!");
				    return;
				  }
				  try 
				  	{
					FileWriter writer = new FileWriter(file);
				    BufferedWriter bw = new BufferedWriter(writer);

				    bw.write(newModel.toJSONString());
				      
				    bw.flush();
				    System.out.println("JSON written successfully!");
				    }
				  catch (IOException e)
				  {
				   e.printStackTrace();
				  }
			}
	
	
	//Method to write a CSV file
		public void writingCSV() {

			String filePath = "C:\\Users\\fcasa\\eclipse-workspace\\main\\data.csv";
			File file = new File(filePath);
			  if (!file.exists()) {
				System.out.println("File does not exist!");
			    return;
			  }
			  try 
			  	{
				FileWriter writer = new FileWriter(file);
			    BufferedWriter bw = new BufferedWriter(writer);

			    bw.write(modelName + ";");
			    bw.write(modelName + " pros;");
			    bw.write(modelName + " cons");
			    bw.newLine();
			    bw.write("*;");
		      
			    for (String pros : modelPros) {
			    	bw.write("* " + pros + " ");
			    }
			     
			    bw.write(";");
			      
			    for (String cons : modelCons) {
			    	bw.write("* " + cons + " ");
			    }
			      
			    bw.flush();
			    System.out.println("CSV written successfully!");
			    
			    }
			  catch (IOException e)
			  {
			   e.printStackTrace();
			  }
		}
		
}
