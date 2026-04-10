#2021년에 가입한 전체 회원들 찾기
#년,월 별로 2021년에 가입한 회원들중 구매한 회원 개수 -> 중복 제외

with a as (select USER_ID from USER_INFO where year(JOINED)=2021),
b as (select count(distinct user_id) as PURCHASED_USERS,year(SALES_DATE) as year ,month(SALES_DATE) as month
    from ONLINE_SALE 
    where USER_ID in (select * from a)
    group by year,month),
c as (select count(*) as all_user from USER_INFO where year(JOINED)=2021)

select YEAR,MONTH,PURCHASED_USERS,round(PURCHASED_USERS/all_user,1) as PUCHASED_RATIO
    from b,c