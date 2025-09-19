with appoint as (select APNT_NO,PT_NO,MDDR_ID,APNT_YMD from APPOINTMENT where DATE_FORMAT(APNT_YMD,"%Y-%m-%d")="2022-04-13" and MCDP_CD="CS" and APNT_CNCL_YN='N')

select 
    a.APNT_NO as APNT_NO,
    b.PT_NAME as PT_NAME,
    b.PT_NO as PT_NO,
    c.MCDP_CD as MCDP_CD,
    c.DR_NAME as DR_NAME,
    a.APNT_YMD as APNT_YMD
    from appoint a inner join PATIENT b on b.PT_NO=a.PT_NO inner join DOCTOR c on c.DR_ID=a.MDDR_ID
    order by APNT_YMD