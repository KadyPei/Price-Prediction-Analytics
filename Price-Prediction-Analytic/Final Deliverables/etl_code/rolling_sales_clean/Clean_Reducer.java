import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clean_Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {

   @Override
   public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
      for (IntWritable value : values) {
         context.write(key, value);
      }
   }
}
