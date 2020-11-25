javac -classpath `yarn classpath` -d . Clean_Mapper.java
javac -classpath `yarn classpath` -d . Clean_Reducer.java
javac -classpath `yarn classpath`:. -d . Clean.java

jar -cvf clean.jar *.class

hadoop jar clean.jar Clean /user/rm4467/final_proj/input/rollingsales_data_clean.txt /user/rm4467/final_proj/output
