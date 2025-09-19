select
    a.WRITER_ID as USER_ID,
    b.NICKNAME as NICKNAME,
    sum(a.PRICE) as TOTAL_SALES
from 
    USED_GOODS_BOARD a 
inner join 
    USED_GOODS_USER b
on
    a.WRITER_ID=b.USER_ID
where 
    STATUS="DONE"
group by 
    WRITER_ID
having 
    TOTAL_SALES>=700000
order by 
    TOTAL_SALES

