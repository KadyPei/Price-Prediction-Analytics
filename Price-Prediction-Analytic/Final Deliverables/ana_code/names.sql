!connect jdbc:hive2://babar.es.its.nyu.edu:10000/
<username>
<passsword>
use <username>;

drop table if exists name;

create external table name (id string, rname string, borough string, zip string) row format delimited fields terminated by ',' location '/user/oka217/project/tmp';

show tables;

--select * from name;
insert overwrite directory '/user/oka217/tmp/test' row format delimited fields terminated by ',' 
select borough, zip, count(distinct rname) as num_rest FROM name GROUP BY borough, zip 
having borough like "Bronx" or borough like "Brooklyn" or borough like "Queens" or 
borough like 'Mannhattan' or borough like 'Staten Island'; 

--insert overwrite directory '/user/oka217/tmp/test' row format delimited fields terminated by ','select borough, zip, count(distinct name) as num_rest FROM name GROUP BY borough, zip;



