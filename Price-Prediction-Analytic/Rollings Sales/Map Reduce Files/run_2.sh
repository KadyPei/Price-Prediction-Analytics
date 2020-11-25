javac -classpath `yarn classpath` -d . CountPerZipcode_Mapper.java
javac -classpath `yarn classpath` -d . CountPerZipcode_Reducer.java
javac -classpath `yarn classpath`:. -d . CountPerZipcode.java

jar -cvf countperzipcode.jar *.class

hadoop jar countperzipcode.jar CountPerZipcode /user/rm4467/final_proj/input/cleandata.txt /user/rm4467/final_proj/output
