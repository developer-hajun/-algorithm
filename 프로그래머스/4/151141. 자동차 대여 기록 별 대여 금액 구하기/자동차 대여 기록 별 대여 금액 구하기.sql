with a as (select car_id,daily_fee from CAR_RENTAL_COMPANY_CAR where car_type = '트럭'),
b as (select 
    case 
        when DURATION_TYPE='7일 이상' then 7
        when DURATION_TYPE='30일 이상' then 30
        when DURATION_TYPE='90일 이상' then 90
    end as `day`
    , DISCOUNT_RATE from CAR_RENTAL_COMPANY_DISCOUNT_PLAN where CAR_TYPE='트럭'),
c as ( select history_id,car_id,datediff(end_date,start_date)+1 as `day` from CAR_RENTAL_COMPANY_RENTAL_HISTORY where car_id in (select car_id from a))
select 
    history_id,
    floor(daily_fee*c.`day`*(1-IFNULL(max(DISCOUNT_RATE),0)/100)) as FEE
    from a 
    join c on a.car_id=c.car_id 
    left join b on c.`day`>=b.`day`
    group by history_id
    order by FEE desc,history_id desc