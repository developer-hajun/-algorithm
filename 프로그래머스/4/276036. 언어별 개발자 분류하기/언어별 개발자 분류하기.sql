With a as (select
   case
        when (select sum(code) from SKILLCODES where CATEGORY='Front End') & SKILL_CODE and (select sum(code) from SKILLCODES where NAME='Python') & SKILL_CODE then 'A'
        when (select sum(code) from SKILLCODES where NAME='C#') & SKILL_CODE then 'B'
        when (select sum(code) from SKILLCODES where CATEGORY='Front End') & SKILL_CODE then 'C'
    end as GRADE,
    ID,
    EMAIL
from 
    DEVELOPERS)
    
select * from a where grade is not null order by grade,id;