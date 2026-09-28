-- 코드를 입력하세요
SELECT
    ORDER_ID,
    PRODUCT_ID,
    IFNULL(OUT_DATE,NULL) as OUT_DATE,
    CASE
        WHEN OUT_DATE<='2022-05-01' THEN '출고완료'
        when OUT_DATE>'2022-05-01' THEN '출고대기'
        else '출고미정'
    END AS '출고여부'
FROM FOOD_ORDER
order by ORDER_ID;