// Regular packages
import java.util.Arrays;
import java.util.List;
// Hadoop packages
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Clean_Mapper extends Mapper<LongWritable, Text, Text, IntWritable > {

   @Override
   public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
      // Variables
      String csv_input = ",";
      int MINIMUM_PRICE = 100000;
      String[] residential_cat_array = new String[] {"09 COOPS - WALKUP APARTMENTS","10 COOPS - ELEVATOR APARTMENTS","12 CONDOS - WALKUP APARTMENTS","13 CONDOS - ELEVATOR APARTMENTS","01 ONE FAMILY DWELLINGS"};
      List<String> residential_cat = Arrays.asList(residential_cat_array);
      // indexes to analyse: {BUILDING CLASS CATEGORY: 3, SALE PRICE: 20}
      // indexes to keep: {ZIP CODE: 11, SALE PRICE: 20}
      // Prep input
      String[] data_entry = value.toString().split(csv_input);
      // doing this because there are some faulty entries under residential
      // that are actually non-residential and the price is larger than the
      // maximum for java integers
      try {
         int price = Integer.parseInt(data_entry[20]);
         // Get the value and enter into context
         // if the entry is residential and is above the minimum price we have set, enter
         if (residential_cat.contains(data_entry[3]) && price >= MINIMUM_PRICE)
            context.write(new Text(data_entry[11]), new IntWritable(price));
      } catch (Exception e) {
         System.out.println("Didn't process entry with price " + data_entry[20]);
      }
   }
}
