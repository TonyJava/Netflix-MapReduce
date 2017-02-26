package edu.cpp.cs499.A3MapReduce.MovieRatings;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceMovieRating extends Reducer<Text, Text, Text, Text> {
	
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		double rating = 0.0;
		int ratingCount = 0;
		String line;
		Iterator<Text> i = values.iterator();
		
		while(i.hasNext()){
		
			line = i.next().toString();
			StringTokenizer itr = new StringTokenizer(line,",");
			
			rating += Double.parseDouble(itr.nextToken());
			
			ratingCount++;
		}
		
		String movieTitle = "";
		
		
		rating = rating/ratingCount;
		DecimalFormat df = new DecimalFormat("0.##");
		
		String movieInfo = "," + movieTitle;
		movieInfo  += df.format(rating);
		
		Text movieText = new Text(movieInfo);
		context.write(key, movieText);
	}

}
	