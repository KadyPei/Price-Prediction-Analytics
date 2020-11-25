// Regular packages
import java.util.Arrays;
import java.util.List;
// Hadoop packages
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MinMaxDiff_Mapper extends Mapper<LongWritable, Text, Text, IntWritable > {

   @Override
   public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
      // Variables
      String csv_input = ",";
      // Prep input
      String[] line = value.toString().split(csv_input);
      int val = Integer.parseInt(line[1]);
      // Get the value and enter into context
      context.write(new Text(line[0]), new IntWritable(val));
   }
}
