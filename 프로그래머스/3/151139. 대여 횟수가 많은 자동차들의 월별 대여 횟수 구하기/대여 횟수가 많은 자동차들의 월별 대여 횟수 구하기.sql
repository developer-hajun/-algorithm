with b as (select CAR_ID from CAR_RENTAL_COMPANY_RENTAL_HISTORY where 
            YEAR(START_DATE)=2022 AND 
            MONTH(START_DATE) BETWEEN 8 AND 10
            Group by CAR_ID having count(*)>=5
          )
select month(START_DATE) as MONTH ,CAR_ID  ,count(*) as RECORDS from CAR_RENTAL_COMPANY_RENTAL_HISTORY a where CAR_ID in (select * from b) and 
YEAR(START_DATE)=2022 AND MONTH(START_DATE) BETWEEN 8 AND 10 group by CAR_ID,month(START_DATE) having count(*)>=1
order by MONTH asc, CAR_ID desc