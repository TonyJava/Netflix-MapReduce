package edu.cpp.cs499.A3MapReduce.TopTenMovies;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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

public class TopTenMovies {

	public static void main(String[] args) {
		
		if(args.length != 3) {
			System.out.println("Input, output file not provided ...");
			System.exit(0);
		}
		String inFile = args[0];
		String movieFile = args[1];
		String outFile = args[2];
		
		HashMap<String, Double> moviesRating = getMovieRatings(inFile);
		HashMap<String, String> movieTitles = getTitles(movieFile);
		Map<String, Double> map = sortByValues(moviesRating); 
		toFile(outFile,map,movieTitles);
		
		
	}
	private static void toFile(String outFile, Map<String,Double> ratings, HashMap<String,String> titles) {
		List<String> list = new ArrayList<String>(ratings.keySet());
		try {
	         PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));
	
	         for(int i = 0; i < 10; i++){
	        	    out.println( list.get(i) + ", " + titles.get(list.get(i)) +", "+ ratings.get(list.get(i)) );
	        	}
	         out.close();
	         
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
	private static HashMap<String, String> getTitles(String file) {
		HashMap<String, String> titles = new HashMap<String, String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line="";
			
			while((line = reader.readLine()) != null) {
			
				StringTokenizer st = new StringTokenizer(line,",");
				String movieId = st.nextToken().trim();
				
				
				String yeartitle = st.nextToken();
				yeartitle += " " + st.nextToken();
				if(st.hasMoreTokens()){
					yeartitle += "," + st.nextToken();
				}
				
				
				titles.put(movieId, yeartitle);
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return titles;
	}
	
	private static HashMap<String, Double> getMovieRatings(String file) {
		HashMap<String, Double> ratings = new HashMap<String, Double>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line="";
			
			while((line = reader.readLine()) != null) {
			
				StringTokenizer st = new StringTokenizer(line,",");
				String movieId = st.nextToken().trim();
				//System.out.println(userId);
				
				String rating = st.nextToken().trim();
				double ratingsCount = Double.parseDouble(rating);
				
				ratings.put(movieId, ratingsCount);
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ratings;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static HashMap<String, Double> sortByValues(HashMap<String,Double> map) { 
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
