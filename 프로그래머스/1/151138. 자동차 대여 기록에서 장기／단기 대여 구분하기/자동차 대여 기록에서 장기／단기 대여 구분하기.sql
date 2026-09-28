
SELECT 
    HISTORY_ID,
    CAR_ID,
    START_DATE,
    END_DATE,
    case
        when datediff(end_date,start_date)+1>=30 then '장기 대여'
        else '단기 대여'
    end as RENT_TYPE
from 
    CAR_RENTAL_COMPANY_RENTAL_HISTORY
where
    month(start_date)=9 and year(start_date)=2022
order by
    HISTORY_ID desc
    
