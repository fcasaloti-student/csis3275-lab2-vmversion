package com.csis3275.servlet;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
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
				
				//Path path = Paths.get(System.getProperty("user.home"), "data.json");
				//String filePath = path.toString();
				//System.out.println(filePath);
				
				String path1 = this.getClass().getClassLoader().getResource("").getPath();
				String fullPath = "";
				try {
					fullPath = URLDecoder.decode(path1, "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String pathArr[] = fullPath.split("/WEB-INF/classes/");
				String filePath = pathArr[0];
				System.out.println(filePath);
				
				//String filePath = "C:\\Users\\fcasa\\eclipse-workspace\\main\\data.json";
				File file = new File(filePath, "data.json");
//				  if (!file.exists()) {
//					System.out.println("File does not exist!");
//				    return;
//				  }
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

			//String filePath = "C:\\Users\\fcasa\\eclipse-workspace\\main\\data.csv";
//			Path path = Paths.get(System.getProperty("user.home"), "data.csv");
//			String filePath = path.toString();
//			System.out.println(filePath);
			
			String path1 = this.getClass().getClassLoader().getResource("").getPath();
			String fullPath = "";
			try {
				fullPath = URLDecoder.decode(path1, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String pathArr[] = fullPath.split("/WEB-INF/classes/");
			String filePath = pathArr[0];
			
			File file = new File(filePath, "data.csv");
//			  if (!file.exists()) {
//				System.out.println("File does not exist!");
//			    return;
//			  }
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
