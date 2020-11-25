// Hadoop-related packages
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;

public class Clean {

	public static void main(String[] args) throws Exception {
		/*
		 * Check if command line args are sufficient
		 */
	    if (args.length != 2) {
	        System.err.println("Usage: clean <input path> <output path>");
	        System.exit(-1);
	    }
		 // Configuration
		 final Configuration conf = new Configuration();
       conf.set("mapred.textoutputformat.separator", ",");

       // Job object forms the specification of the job
       Job job = Job.getInstance(conf);

       //Job job = new Job();
       job.setJarByClass(Clean.class);
       job.setJobName("Clean data");

       // We can ask for just 1 reducer:
       job.setNumReduceTasks(1);
       // specify the input and output paths.
       FileInputFormat.addInputPath(job, new Path(args[0]));
       FileOutputFormat.setOutputPath(job, new Path(args[1]));
       // specify the map and reduce types
       job.setMapperClass(Clean_Mapper.class);
       job.setReducerClass(Clean_Reducer.class);
       /* ------------------------------------------------------------------------*/
       // control the output types for reduce function - must match what the Reduce class produces
       job.setOutputKeyClass(Text.class);
       job.setOutputValueClass(IntWritable.class);
		 // exit
       System.exit(job.waitForCompletion(true) ? 0 : 1);
	 }

}
