import java.io.IOException;
import java.util.regex.*;
import java.time.Instant;
import java.time.Duration;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GetzipMapper
    extends Mapper<LongWritable, Text, Text, Text> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
        String line = value.toString();
        
        if(line.charAt(0)=='B'){
          //Brooklyn or Bronx

          if(line.substring(0,4).equals("Bron")){
            String zip = line.substring(6, 11);
            String bor = "Bronx";
            String score = line.substring(12);
            context.write(new Text(bor+","+zip), new Text(score));
          }

          else{
            //brooklyn
            String zip = line.substring(9, 14);
            String bor = "Brooklyn";
            String score = line.substring(15);
            context.write(new Text(bor+","+zip), new Text(score));
          }
        } 
        else if(line.charAt(0)=='Q'){
          //queens
          String zip = line.substring(7, 12);
          String bor = "Queens";
          String score = line.substring(13);
          context.write(new Text(bor+","+zip), new Text(score));
        } 
        
        else if(line.charAt(0)=='M'){
          //Manhattan
          String zip = line.substring(10, 15);
          String bor = "Manhattan";
          String score = line.substring(16);
          context.write(new Text(bor+","+zip), new Text(score));

        }

        else if(line.charAt(0)=='S'){
          //Staten Island
          String zip = line.substring(15, 20);
          String bor = "Staten Island";
          String score = line.substring(21);
          context.write(new Text(bor+","+zip), new Text(score));

        }
  }
}
