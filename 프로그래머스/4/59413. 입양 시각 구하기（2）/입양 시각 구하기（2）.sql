with recursive hours as (
    select 0 as hour
    union
    select hour+1 from hours where hour<23
)

select HOUR , count(animal_id) as COUNT from ANIMAL_OUTS right join hours on hours.hour = hour(DATETIME) group by hour order by hour