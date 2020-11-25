!connect jdbc:hive2://babar.es.its.nyu.edu:10000/
<username>
<password>
use <username>;

DROP TABLE IF EXISTS temp_311;

create external table temp_311 (zipcode string, created string, closed string, comp_type string, borough string, days_to_close int) row format delimited fields terminated by ',' location '/user/oka217/project/clean_output';

show tables;

select * from temp_311 limit 10;

insert overwrite directory '/user/oka217/tmp/311' row format delimited fields terminated by ',' select borough, zipcode, avg(days_to_close) as avg_days_to_close, count(comp_type) as num_complaints FROM temp_311 GROUP BY borough, zipcode;


--create table with aggregated data to be used for join later

drop table if exists agg_311;
create external table agg_311 (borough string, zipcode string, days_to_close int, num_complaints int) row format delimited fields terminated by ',' location '/user/oka217/tmp/311';

show tables;

select * from agg_311 limit 10;

