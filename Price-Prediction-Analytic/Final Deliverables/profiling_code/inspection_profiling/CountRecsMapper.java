import java.util.*;
import java.lang.*;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountRecsMapper extends Mapper<LongWritable, Text, Text, IntWritable > {

   @Override
   public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
      
    //  String csv_input = ",";

    //String[] line = value.toString();
    String total= "Total records: ";
    context.write(new Text(total), new IntWritable(1));

}}
