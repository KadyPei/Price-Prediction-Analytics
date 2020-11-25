!connect jdbc:hive2://babar.es.its.nyu.edu:10000/
<username>
<passsword>
use <username>;


drop table if exists rolling;

--create table from rolling sales data
create external table rolling (zipcode string, price int) row format delimited fields terminated by ',' location '/user/oka217/tmp/rolling/';

show tables;


select * from rolling limit 10;

--aggregate rolling data by zipcode 
insert overwrite directory '/user/oka217/tmp/rolling_agg' row format delimited fields terminated by ','select zipcode, avg(price) as price FROM rolling GROUP BY zipcode;

--create table with aggregated data
drop table if exists rolling_agg;
create external table rolling_agg (zipcode string, price int) row format delimited fields terminated by ',' location '/user/oka217/tmp/rolling_agg';


show tables;
select * from rolling_agg;
