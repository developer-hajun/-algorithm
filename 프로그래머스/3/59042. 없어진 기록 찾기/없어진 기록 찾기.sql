with O as (select ANIMAL_ID from ANIMAL_INS)

select ANIMAL_ID,NAME from ANIMAL_OUTS where ANIMAL_ID not in (select * from O)