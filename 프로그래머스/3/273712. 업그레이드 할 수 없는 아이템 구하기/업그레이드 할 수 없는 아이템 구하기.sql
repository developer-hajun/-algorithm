
select ITEM_ID,ITEM_NAME,RARITY from item_info
where item_id not in ( select ifnull(parent_item_id,-1) as id from ITEM_TREE)
order by item_id desc;
