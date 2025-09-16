
with a as (SELECT HOST_ID from PLACES group by HOST_ID Having count(*)>=2)

select ID,NAME,HOST_ID from PLACES where HOST_ID in (select HOST_ID from a) order by id;