create table t_card
(
	id						bigserial,
	name					varchar(128)                not null default '',    -- 名称
	level					int4                        not null default 1,     -- 卡牌等级
	icon					varchar(128)                not null,				-- 卡牌图标完整路径	 
	pinzhi					int4						not null default 1,  	-- 暂定1：普通，2：中级，3：高级 对应客户端显示卡牌背景
	
	action					int4						not null default 1,     -- 攻击方式 1：普通抖动， 2：原地悬挂定点式摇摆，3：卡牌冲击
	total_hp				int4						not null default 100,   -- 总血量，默认100
	attack					int4						not null default 0,     -- 攻击力
	fangyu					int4						not null default 0,		-- 防御力		
	des  					text,												-- 描述			
	
	primary key (id)
);

create table t_card_skill
(
	id						bigserial,
	card_id					int8						not null references t_card, 		-- 卡牌ID
	skill_id				int8						not null,							-- 技能ID
	name					varchar(128)				not null default '',    			-- 技能名称
		
	des						text,															-- 描述			
	
	primary key (id)
);


create table t_user_card
(
	id						bigserial,
	user_id                 bigint		  not null references t_user,
	card_id                 bigint		  not null references t_user,
	leader					int4          not null default 0,             -- 是否是队长

	unique(user_id, card_id),
	primary key (id)
);