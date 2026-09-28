select
    ANIMAL_ID ,name
from
(SELECT o.ANIMAL_ID,o.name,datediff(o.DATETIME,i.DATETIME) as date from ANIMAL_INS i join ANIMAL_OUTS o on i.ANIMAL_ID=o.ANIMAL_ID order by date desc limit 2) as t;

