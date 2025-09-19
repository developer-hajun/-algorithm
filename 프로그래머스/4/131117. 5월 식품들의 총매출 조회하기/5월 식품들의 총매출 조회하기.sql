with id as (select PRODUCT_ID,sum(AMOUNT) as AMOUNT from FOOD_ORDER where year(PRODUCE_DATE)=2022 and month(PRODUCE_DATE)=5
           group by PRODUCT_ID)

select 
    a.PRODUCT_ID,a.PRODUCT_NAME,a.PRICE*b.AMOUNT as TOTAL_SALES
from FOOD_PRODUCT a inner join id b on a.PRODUCT_ID=b.PRODUCT_ID
order by TOTAL_SALES desc,PRODUCT_ID asc
