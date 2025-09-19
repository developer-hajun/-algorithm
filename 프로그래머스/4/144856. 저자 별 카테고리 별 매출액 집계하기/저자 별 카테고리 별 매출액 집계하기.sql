WITH a AS (
    SELECT 
        b.AUTHOR_ID,
        b.CATEGORY,
        SUM(a.SALES) * b.PRICE AS TOTAL_SALES
    FROM 
        BOOK_SALES a
    INNER JOIN 
        BOOK b 
    ON 
        a.BOOK_ID = b.BOOK_ID
    WHERE 
        DATE_FORMAT(a.SALES_DATE, "%Y-%m") = "2022-01"
    GROUP BY 
        b.BOOK_ID, b.AUTHOR_ID, b.CATEGORY, b.PRICE
)
select a.AUTHOR_ID,AUTHOR_NAME,CATEGORY,sum(TOTAL_SALES) as TOTAL_SALES from a inner join AUTHOR b on a.AUTHOR_ID=b.AUTHOR_ID group by a.AUTHOR_ID,a.CATEGORY
order by a.AUTHOR_ID asc  , CATEGORY desc
