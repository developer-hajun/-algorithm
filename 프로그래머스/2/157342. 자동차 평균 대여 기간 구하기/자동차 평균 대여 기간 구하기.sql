with a as (SELECT car_id,datediff(end_date,start_date)+1 as day from CAR_RENTAL_COMPANY_RENTAL_HISTORy),
b as (
SELECT car_id,count(*) as date from CAR_RENTAL_COMPANY_RENTAL_HISTORY group by car_id)

select a.car_id,round(sum(day)/date,1) as AVERAGE_DURATION from a join b on a.car_id=b.car_id group by a.car_id having AVERAGE_DURATION>=7 order by AVERAGE_DURATION desc, car_id desc;