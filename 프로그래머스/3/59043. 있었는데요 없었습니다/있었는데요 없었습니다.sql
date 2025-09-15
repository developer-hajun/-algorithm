WITH o AS (
  SELECT ANIMAL_ID AS id, DATETIME AS time
  FROM ANIMAL_OUTS
),
i AS (
  SELECT ANIMAL_ID AS id, DATETIME AS time, NAME
  FROM ANIMAL_INS
)

SELECT i.id as ANIMAL_ID, i.NAME as NAME
FROM i
INNER JOIN o ON i.id = o.id
where i.time>o.time
order by i.time
