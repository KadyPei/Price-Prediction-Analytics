import java.io.IOException;
import java.util.regex.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountRecsMapper
    extends Mapper<LongWritable, Text, Text, IntWritable> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
        String line = value.toString();

        context.write(new Text("Record Count Before Clean"), new IntWritable(1));

        
        //context.write(new Text("Record Count After Clean"), new IntWritable(1));
        
  }
}
