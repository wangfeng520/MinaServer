-- ��ȡָ�����иղ����е�ID
create or replace function get_generated_id(text) returns int8 as $BODY$
	select currval($1 || '_id_seq');
$BODY$ language sql;
/**/




create table dual
(
	dummy		varchar(1)
);

insert into dual(dummy) values('X');