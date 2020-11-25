import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinMaxDiff_Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {
   @Override
   public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
      // min and max
      int currentMin = Integer.MAX_VALUE, currentMax = 0;
      // iterate over values to find min max
      for (IntWritable value : values) {
         if (value.get() < currentMin) currentMin = value.get();
         else if (value.get() > currentMax) currentMax = value.get();
      }
      context.write(key, new IntWritable(currentMax - currentMin));
   }
}
