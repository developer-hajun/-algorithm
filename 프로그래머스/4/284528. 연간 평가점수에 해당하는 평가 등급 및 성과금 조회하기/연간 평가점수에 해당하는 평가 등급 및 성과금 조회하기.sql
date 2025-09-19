with grade as (
    select
        EMP_NO,
        case
            when AVG(SCORE)>=96 then 'S'
            when AVG(SCORE)>=90 then 'A'
            when AVG(SCORE)>=80 then 'B'
            else 'C'
        end as GRADE,
        case
            when AVG(SCORE)>=96 then 0.2
            when AVG(SCORE)>=90 then 0.15
            when AVG(SCORE)>=80 then 0.1
            else 0
        end as percent
    from HR_GRADE
    group by EMP_NO
)
select 
    a.EMP_NO as EMP_NO,
    a.EMP_NAME as EMP_NAME,
    b.GRADE as GRADE,
    a.SAL*b.percent as BONUS

from HR_EMPLOYEES a 
inner join grade b 
on a.EMP_NO=b.EMP_NO
order by EMP_NO