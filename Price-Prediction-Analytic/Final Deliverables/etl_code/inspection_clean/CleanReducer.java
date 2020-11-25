import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.lang.Character;
import java.util.*;



public class CleanReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

   @Override
   public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
      
		//int sum =0 ;
		for(IntWritable value : values){
			context.write(key, new IntWritable(value.get()));

		}
		//context.write(key, new IntWritable(sum));
   }
}
