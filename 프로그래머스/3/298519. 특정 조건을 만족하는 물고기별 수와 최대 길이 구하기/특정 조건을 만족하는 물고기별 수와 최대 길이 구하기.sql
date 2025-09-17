with b as (select 
            count(*) as FISH_COUNT,
            AVG(
            case
                when LENGTH<=10 then 10
                else LENGTH
            end
            ) AS AVG_LENGTH, 
            MAX(LENGTH) AS MAX_LENGTH,
            FISH_TYPE
           from 
            FISH_INFO 
           group by 
            FISH_TYPE)

select FISH_COUNT,MAX_LENGTH,FISH_TYPE from b where  AVG_LENGTH>=33 ORDER BY FISH_TYPE ASC