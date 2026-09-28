with a as (
    select 
        e.EMP_NO,
        e.EMP_NAME,
        avg(g.SCORE) as AVG_SCORE,
        e.SAL
    from HR_DEPARTMENT d
    join HR_EMPLOYEES e on d.DEPT_ID = e.DEPT_ID
    join HR_GRADE g on e.EMP_NO = g.EMP_NO
    group by e.EMP_NO, e.EMP_NAME, e.SAL
)

select
    EMP_NO,
    EMP_NAME,
    case
        when AVG_SCORE >= 96 then 'S'
        when AVG_SCORE >= 90 then 'A'
        when AVG_SCORE >= 80 then 'B'
        else 'C'
    end as GRADE,
    SAL * case
        when AVG_SCORE >= 96 then 0.20
        when AVG_SCORE >= 90 then 0.15
        when AVG_SCORE >= 80 then 0.10
        else 0
    end as BONUS
from a
order by EMP_NO;