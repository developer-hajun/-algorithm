SELECT
    BOARD_ID,WRITER_ID,TITLE,PRICE,
    case 
        when STATUS='DONE' then '거래완료'
        when STATUS='SALE' then '판매중'
        else '예약중'
    end as STATUS
from 
    USED_GOODS_BOARD 
where
    CREATED_DATE='2022.10.05'
ORDER BY
   BOARD_ID DESC ;