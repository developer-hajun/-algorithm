with a as ( SELECT distinct WRITER_ID from USED_GOODS_BOARD group by WRITER_ID having count(*)>=3)

select 
    USER_ID,
    NICKNAME,
    concat(CITY,' ',STREET_ADDRESS1,' ',STREET_ADDRESS2)
    AS '전체주소',
    CONCAT(
        SUBSTR(TLNO,1,3),'-',SUBSTR(TLNO,4,4),'-',SUBSTR(TLNO,8,4)
    ) AS '전화번호'
from USED_GOODS_USER u join a on u.USER_ID=a.WRITER_ID
ORDER BY USER_ID DESC;