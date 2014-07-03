create table t_city
(
	id						bigserial,
	name					varchar(64)        not null default '',				-- 关卡名称
	icon					varchar(32)		   not null,						-- 关卡图标	
	level       			int4			   not null default 1,				-- 最低等级要求 
	quality					int4               not null default 1,				-- 品质难度低到高1，2，3
	
	exp						int8			   not null default 0,				-- 获取经验
	coin					int8			   not null default 0,				-- 获取金币
	mark                    varchar(256),										-- 描述
						
    unique(name),	
	primary key (id)
);


create table t_user_city
(
	id						bigserial,
	user_id					bigint              not null references t_user,
	city_id					int4                not null references t_city,						-- 关卡ID
	star					int4,												-- 获取的心		
						
	unique(user_id, city_id),	
	primary key (id)
);