with e as (select ID,PARENT_ID from ECOLI_DATA )

select a.id as ID ,count(e.ID) as CHILD_COUNT  from ECOLI_DATA a left join e  on
a.ID=e.PARENT_ID group by a.id order by id asc 