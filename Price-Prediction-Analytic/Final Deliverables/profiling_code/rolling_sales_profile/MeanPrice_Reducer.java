import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeanPrice_Reducer extends Reducer<Text, IntWritable, Text, FloatWritable> {

   @Override
   public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
      int currentSum = 0, len = 0;
      for (IntWritable value : values) {
         currentSum += value.get();
         len += 1;
      }
      context.write(key, new FloatWritable(currentSum/len));
   }
}
