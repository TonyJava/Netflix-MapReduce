package edu.cpp.cs499.A3MapReduce.UserRatings;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReduceUserRatings extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text text, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		
		int sum = 0;
		Iterator<IntWritable> i = values.iterator();
		while(i.hasNext()) {
			int count = i.next().get();
			sum += count;
		}
		String temp = text.toString() + ",";
		
		context.write(new Text(temp), new IntWritable(sum));
	}
	
	

}
