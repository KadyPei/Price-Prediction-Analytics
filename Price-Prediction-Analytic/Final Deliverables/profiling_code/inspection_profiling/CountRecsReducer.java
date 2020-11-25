import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.lang.Character;
import java.util.*;



public class CountRecsReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

   @Override
   public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
      
		//int sum =0 ;
		int sum =0 ;
		for(IntWritable val : values){
			sum += val.get();
		}
		context.write(key, new IntWritable(sum));
		//context.write(key, new IntWritable(sum));
   }
}
