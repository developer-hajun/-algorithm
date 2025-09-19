with a as (select CATEGORY,MAX(PRICE) FROM FOOD_PRODUCT WHERE CATEGORY IN ("국","과자","김치","식용유") GROUP BY CATEGORY)

SELECT CATEGORY,price as MAX_PRICE,PRODUCT_NAME 
from FOOD_PRODUCT 
where (CATEGORY,price) in (select * from a) order by price desc