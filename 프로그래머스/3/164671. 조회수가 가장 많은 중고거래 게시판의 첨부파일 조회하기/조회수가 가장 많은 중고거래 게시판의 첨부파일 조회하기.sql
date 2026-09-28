# -- 코드를 입력하세요
# SELECT 
#     concat('/home/grep/src/',BOARD_ID,'/',FILE_ID,FILE_NAME,FILE_EXT) as FILE_PATH
# from 
#     USED_GOODS_FILE
# where 
#     BOARD_ID = 
#     (select BOARD_ID from USED_GOODS_BOARD u order by u.views desc limit 1)
# order by FILE_PATH desc;



SELECT CONCAT('/home/grep/src/',BOARD_ID,'/',FILE_ID,FILE_NAME,FILE_EXT) AS FILE_PATH FROM USED_GOODS_FILE WHERE BOARD_ID=(SELECT BOARD_ID FROM USED_GOODS_BOARD WHERE VIEWS=(SELECT MAX(VIEWS) FROM USED_GOODS_BOARD)) order by FILE_ID desc;