import java.util.*;
import java.lang.*;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanMapper extends Mapper<LongWritable, Text, Text, IntWritable > {

   @Override
   public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
      
    //  String csv_input = ",";
      String dateInspection = "1/1/1900"; // index 8
      String gradeN = "N";
      String bad_record = "BAD_RECORD";


      String[] line = value.toString().split(",");
     //need to check score not null, and grade not N 
     // int score = Integer.parseInt(line[13]);
      try {
         int score = Integer.parseInt(line[13]);
         if (line.length == 26 && !(line[8].equals(dateInspection)) && !(line[13].isEmpty()) && !(line[14]).equals(gradeN) && line[13]) {
        // String location = (line[2] + "_" +  line[5]); 
       

         context.write(new Text(line[2] + "_" +  line[5]), new IntWritable(score));
      } 
      }catch (Exception e){
        System.out.println("bad record, not processed " + line[13]);
      }
   
   }
}
