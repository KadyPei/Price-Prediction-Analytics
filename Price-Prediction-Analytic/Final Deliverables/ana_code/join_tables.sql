!connect jdbc:hive2://babar.es.its.nyu.edu:10000/
<username>
<passsword>
use <username>;

--CTE to join first two tables 
with tmp_join
as (select r.zipcode, r.price, i.borough, i.score from rolling_agg r left join inspection_agg i on r.zipcode = i.zipcode)

--join CTE with third table and write to file on HDFS
insert overwrite directory '/user/oka217/tmp/joined' row format delimited fields terminated by ','
select t.zipcode as zipcode, t.price as sale_price, t.borough as borough, t.score as average_inspection_score, a.days_to_close as avg_days_to_close,
a.num_complaints as num_complaints from tmp_join t left join agg_311 a on t.zipcode = a.zipcode;

--create table from file 
drop table if exists joint_table;
create external table joint_table (zipcode string, sale_price int, borough string, inspection_score int, days_to_close int, num_complaints int) row format delimited fields terminated by ',' location '/user/oka217/tmp/joined';


show tables;
