create table t_card
(
	id						bigserial,
	name					varchar(128)                not null default '',    -- ����
	level					int4                        not null default 1,     -- ���Ƶȼ�
	icon					varchar(128)                not null,				-- ����ͼ������·��	 
	quality					int4						not null default 1,  	-- �ݶ�1����ͨ��2���м���3���߼� ��Ӧ�ͻ�����ʾ���Ʊ���
	
	action_id				int4						not null default 1,     -- ������ʽ 1����ͨ������ 2��ԭ�����Ҷ���ʽҡ�ڣ�3�����Ƴ��
	total_hp				int4						not null default 100,   -- ��Ѫ����Ĭ��100
	attack					int4						not null default 0,     -- ������
	fangyu					int4						not null default 0,		-- ������		
	des  					text,												-- ����			
	
	primary key (id)
);

create table t_card_skill
(
	id						bigserial,
	card_id					int8						not null references t_card, 		-- ����ID
	skill_id				int8						not null,							-- ����ID
	name					varchar(128)				not null default '',    			-- ��������
		
	des						text,															-- ����			
	
	primary key (id)
);


create table t_user_card
(
	id						bigserial,
	user_id                 bigint		  not null references t_user,
	card_id                 bigint		  not null references t_card,
	leader					int4          not null default 0,             -- �Ƿ��Ƕӳ�
	pos						int4,										  -- ��ǰ����λ��
	enable					int4          not null default 0,			  -- �Ƿ��ս����
	

	unique(user_id, card_id),
	unique(user_id, pos),
	primary key (id)
);

-- ���ùؿ���Ӧmonster
create table t_city_card
(
	id						bigserial,
	city_id                 bigint		  not null references t_city,
	card_id                 bigint		  not null references t_card,
	pos						int4          not null,										  -- ��ǰ����λ��	

	unique(city_id, card_id),
	unique(city_id, pos),
	primary key (id)
);