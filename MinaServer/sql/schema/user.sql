create table t_user
(
	id						bigserial,
	name					varchar(100)		not null,									--昵称
	level         			int4            	not null,		                    		--角色的等级
	exp            			int8   				not null,									--角色的当前经验值
	maxexp         			int8   				not null,   								--角色的最大经验值
	gold        		    int8   				not null default 0,   						--金币的数量	
	silver          		int8    			not null default 0, 						--银子的数量
	power         			int8  				not null default 0,							--当前体力值（最大值一样）
	
	primary key (id)
);

create table t_account
(
	id						bigserial,
	user_id					bigint              not null references t_user,
	login					varchar(100)        not null,										-- 登陆名
	psw						varchar(100)		not null,										-- 密码
	count					int4				not null default 0,								-- 登录次数
	last_date			    timestamp,															-- 最后一次登录日期
	
	unique(login),
	primary key (id)
);

create table t_level_exp
(
	id						bigserial,
	level         			int4                not null,   			  						--角色的等级
	exp            			int8				not null,										--角色的当前经验值
	unique(level),
	primary key (id)
);