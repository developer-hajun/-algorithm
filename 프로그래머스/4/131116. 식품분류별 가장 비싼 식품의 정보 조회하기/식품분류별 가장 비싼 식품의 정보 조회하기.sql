select
    a.CATEGORY,
    a.MAX_PRICE,
    b.PRODUCT_NAME 
from 
    FOOD_PRODUCT b 
join 
    (SELECT 
        CATEGORY,
        max(PRICE) as MAX_PRICE 
    from 
        FOOD_PRODUCT 
     where 
        CATEGORY in ('식용유','국','김치','과자') 
     group by CATEGORY ) a 
on a.CATEGORY=b.CATEGORY and b.price = a.MAX_PRICE 
ORDER BY MAX_PRICE DESC
