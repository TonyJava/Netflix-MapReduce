package edu.cpp.cs499.A3MapReduce.TopTenUsers;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class TopTenUsers {

	public static void main(String[] args) {
		
		if(args.length != 2) {
			System.out.println("Input, output file not provided ...");
			System.exit(0);
		}
		String inFile = args[0];
		String outFile = args[1];
		
		HashMap<String, Integer> users = getUsers(inFile);
		Map<String, Integer> map = sortByValues(users); 
		toFile(outFile, map);
	}
	
	private static void toFile(String file, Map<String,Integer> map) {
		List<String> list = new ArrayList<String>(map.keySet());
		try {
	         PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	
	         for(int i = 0; i < 10; i++){
	        	    out.println( list.get(i) + " " + map.get(list.get(i)) );
	        	}
	         out.close();
	         
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
	private static HashMap<String, Integer> getUsers(String file){
		HashMap<String, Integer> user = new HashMap<String, Integer>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line="";
			
			while((line = reader.readLine()) != null) {
			
				StringTokenizer st = new StringTokenizer(line,",");
				String userId = st.nextToken().trim();
				//System.out.println(userId);
				
				String count = st.nextToken().trim();
				int ratingsCount = Integer.parseInt(count);
				
				user.put(userId, ratingsCount);
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static HashMap sortByValues(HashMap map) { 
	       List<String> list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o2)).getValue())
	                  .compareTo(((Map.Entry) (o1)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }

	
}
