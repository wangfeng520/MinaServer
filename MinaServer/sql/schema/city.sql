create table t_user_city
(
	id						bigserial,
	user_id					bigint              not null references t_user,
	city_id					int4                not null,						-- 关卡ID
	star					int4,												-- 获取的心		
						
	unique(user_id, city_id),	
	primary key (id)
);