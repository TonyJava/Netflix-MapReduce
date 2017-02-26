package edu.cpp.cs499.A3MapReduce.UserRatings;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapUserRatings extends Mapper<LongWritable, Text, Text, IntWritable> {

	private static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		StringTokenizer st = new StringTokenizer(line, ",");
		//get rid of movie id
		st.nextToken();
		//user id as key
		word.set(st.nextToken());
		context.write(word, one);
		
		
	}


}
