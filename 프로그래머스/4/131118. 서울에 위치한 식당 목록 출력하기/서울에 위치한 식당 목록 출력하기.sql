select 
    a.REST_ID,	
    REST_NAME,
    FOOD_TYPE,
    FAVORITES,
    ADDRESS,
    ROUND(AVG(REVIEW_SCORE),2) AS SCORE
from
    REST_INFO a
inner join
    REST_REVIEW b
on
    a.REST_ID=b.REST_ID
where
    ADDRESS like "서울%"
GROUP BY
    REST_ID
ORDER BY 
    SCORE DESC,
    FAVORITES DESC