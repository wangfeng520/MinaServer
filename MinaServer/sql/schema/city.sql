create table t_user_city
(
	id						bigserial,
	user_id					bigint              not null references t_user,
	city_id					int4                not null,						-- �ؿ�ID
	star					int4,												-- ��ȡ����		
						
	unique(user_id, city_id),	
	primary key (id)
);