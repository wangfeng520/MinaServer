create table t_city
(
	id						bigserial,
	name					varchar(64)        not null default '',				-- 关卡名称
	icon					varchar(32)		   not null,						-- 关卡图标	
	level       			int4			   not null default 1,				-- 最低等级要求 
	quality					int4               not null default 1,				-- 品质难度低到高1，2，3
	tili					int4			   not null default 1,				-- 进入需要消耗的体力值
	
	exp						int8			   not null default 0,				-- 获取经验
	coin					int8			   not null default 0,				-- 获取金币
	des                     varchar(256),										-- 描述
						
    unique(name),	
	primary key (id)
);

create table t_fuben
(
	id						bigserial,
	name					varchar(64)        not null default '',				-- 副本名称
	icon					varchar(32)		   not null,						-- 关卡图标	
	type					int4			   not null default 1,      		-- 类型 1：普通副本 2：精英副本 3:活动副本
	level       			int4,												-- 最低等级要求 
	quality					int4               not null default 1,				-- 品质难度低到高1，2，3
	
	des                     varchar(256),										-- 描述
						
    unique(name),	
	primary key (id)
);

-- 副本对应关卡表
create table t_fuben_city
(
	id						bigserial,
	fuben_id				bigint              not null references t_fuben,
	city_id					int4                not null references t_city,						-- 关卡ID	
						
	unique(fuben_id, city_id),	
	primary key (id)
);

-- 用户对应通过的关卡
create table t_user_city
(
	id						bigserial,
	user_id					bigint              not null references t_user,
	city_id					int4                not null references t_city,						-- 关卡ID
	star					int4,																-- 获取的心		
						
	unique(user_id, city_id),	
	primary key (id)
);