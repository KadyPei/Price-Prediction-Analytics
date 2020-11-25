###############################################################################
# Step 1: Clean data
###############################################################################
hdfs dfs -mkdir final_proj
hdfs dfs -mkdir final_proj/input
hdfs dfs -put rollingsales_data_clean.txt final_proj/input
./run_1.sh
#hdfs dfs -cat final_proj/output/part-r-00000
# bring the output into local
hdfs dfs -get final_proj/output/part-r-00000
# rename the output
mv part-r-00000 cleandata.txt
./teardown.sh
rm clean.jar Clean.class Clean_Mapper.class  Clean_Reducer.class
###############################################################################
# Step 2a: Analyse/Explore
###############################################################################
# transfer new, clean data back into HDFS
hdfs dfs -mkdir final_proj
hdfs dfs -mkdir final_proj/input
hdfs dfs -put cleandata.txt final_proj/input
# run the first analysis
./run_2.sh
#hdfs dfs -cat final_proj/output/part-r-00000
# bring the output into local
hdfs dfs -get final_proj/output/part-r-00000
# rename the output
mv part-r-00000 unitsperzipcodecount.txt
#
./teardown.sh
rm countperzipcode.jar CountPerZipcode.class CountPerZipcode_Mapper.class  CountPerZipcode_Reducer.class
###############################################################################
# Step 2b: Analyse/Explore
###############################################################################
# transfer new, clean data back into HDFS
hdfs dfs -mkdir final_proj
hdfs dfs -mkdir final_proj/input
hdfs dfs -put cleandata.txt final_proj/input
# run the first analysis
./run_3.sh
#hdfs dfs -cat final_proj/output/part-r-00000
# bring the output into local
hdfs dfs -get final_proj/output/part-r-00000
# rename the output
mv part-r-00000 avgprice.txt
#
./teardown.sh
rm meanprice.jar MeanPrice.class MeanPrice_Mapper.class  MeanPrice_Reducer.class
