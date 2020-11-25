javac -classpath `yarn classpath` -d . MeanPrice_Mapper.java
javac -classpath `yarn classpath` -d . MeanPrice_Reducer.java
javac -classpath `yarn classpath`:. -d . MeanPrice.java

jar -cvf meanprice.jar *.class

hadoop jar meanprice.jar MeanPrice /user/rm4467/final_proj/input/cleandata.txt /user/rm4467/final_proj/output
