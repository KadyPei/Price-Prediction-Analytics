!connect jdbc:hive2://babar.es.its.nyu.edu:10000/
<username>
<passowrd>
use <username>;

DROP TABLE IF EXISTS inspection;

create external table inspection (borough string, zipcode string, score int) row format delimited fields terminated by ',' location '/user/oka217/project/sep_zip';

show tables;

select * from inspection limit 10;

insert overwrite directory '/user/oka217/tmp/restaurant' row format delimited fields terminated by ','select borough, zipcode, avg(score) as score FROM inspection GROUP BY borough, zipcode;



create external table inspection_agg (borough string, zipcode string, score int) row format delimited fields terminated by ',' location '/user/oka217/tmp/restaurant';

show tables;

select * from inspection_agg limit 10;
