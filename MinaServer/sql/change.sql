create table t_fuben
(
	id						bigserial,
	name					varchar(64)        not null default '',				-- 副本名称
	icon					varchar(64)		   not null,						-- 关卡图标	
	type					int4			   not null default 1,      		-- 类型 1：普通副本 2：精英副本 3:活动副本
	level       			int4,												-- 最低等级要求 
	quality					int4               not null default 1,				-- 品质难度低到高1，2，3
	
	des                     varchar(256),										-- 描述
						
    unique(name),	
	primary key (id)
);