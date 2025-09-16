with o as (select ANIMAL_ID from ANIMAL_OUTS)
,i as (select ANIMAL_ID,NAME,DATETIME from ANIMAL_INS)

select NAME,DATETIME from i where i.ANIMAL_ID not in (select * from o) order by DATETIME asc limit 3