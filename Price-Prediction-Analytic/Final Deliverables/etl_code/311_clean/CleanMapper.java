import java.io.IOException;
import java.util.regex.*;
import java.time.Instant;
import java.time.Duration;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanMapper
    extends Mapper<LongWritable, Text, Text, Text> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
        String line = value.toString();
        String[] line_arr = line.split(",");

        if(line_arr.length != 5){
        context.write(new Text("Not 5 columns"), new IntWritable(1));
        }

        if(line_arr.length == 5){
          context.write(new Text("5 columns"), new IntWritable(1));
          }


        //TODO
        //drop records that do not have values in any of the columns
        //compute new column - time taken for event to be closed since its creation
        boolean dropFlag = false;
        for(int i=0; i<line_arr.length; i++){
                if(line_arr[i].equals(" ") || line_arr[i].equals("N/A") || line_arr[i].equals("UNKNOWN") || line_arr[i].equals("Unspecified")){
                        dropFlag = true;
                        break;
                }
/*
                if((i==1) || (i==2)){
                        //try to match date pattern with regex
                        Pattern pattern = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d");
                        Matcher m = pattern.matcher(line_arr[i]);
                        if(!m.find()){
                        //no match
                        dropFlag = true;
                        break;
                        }
                }
*/
        }

        if(!dropFlag){
                //copmute new column value
                String new_col_value = time_taken(line_arr[1], line_arr[2]);

                 //create record with new column and insert into new clean file
                 context.write(new Text(line), new Text(new_col_value));
        }


          
  }

  public String time_taken(String created, String closed){
    //      Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
      //      Matcher m1 = pattern.matcher(created);
    ///     Matcher m2 = pattern.matcher(closed);
       //     String create_date = m1.group(0);
    //      String close_date = m2.group(0);
    
            String temp1 = created.substring(0, created.length()-1) + "Z";
            String temp2 = closed.substring(0, closed.length()-1) + "Z";
    
            Instant creation = Instant.parse(temp1);
            Instant closing = Instant.parse(temp2);
    
            Duration period = Duration.between(creation, closing);
    
            long time = period.toDays();
            if(time <= 0){
              return 1+"";
            }
            else{
              return ""+time;
            }
    
      }
    
}
