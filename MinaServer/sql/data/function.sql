-- 获取指定表中刚插入行的ID
create or replace function get_generated_id(text) returns int8 as $BODY$
	select currval($1 || '_id_seq');
$BODY$ language sql;
/**/

-- 时间运算函数
create or replace function add_seconds(timestamp with time zone, integer) returns timestamp without time zone as $BODY$
	select ($1 + ($2 || 'second')::interval)::timestamp without time zone;
$BODY$ language sql;
/**/

create or replace function add_seconds(timestamp without time zone, integer) returns timestamp without time zone as $BODY$
	select $1 + ($2 || 'second')::interval;
$BODY$ language sql;
/**/
