create table t_city
(
	id						bigserial,
	name					varchar(64)        not null default '',				-- �ؿ�����
	icon					varchar(32)		   not null,						-- �ؿ�ͼ��	
	level       			int4			   not null default 1,				-- ��͵ȼ�Ҫ�� 
	quality					int4               not null default 1,				-- Ʒ���Ѷȵ͵���1��2��3
	tili					int4			   not null default 1,				-- ������Ҫ���ĵ�����ֵ
	
	exp						int8			   not null default 0,				-- ��ȡ����
	coin					int8			   not null default 0,				-- ��ȡ���
	des                     varchar(256),										-- ����
						
    unique(name),	
	primary key (id)
);

create table t_fuben
(
	id						bigserial,
	name					varchar(64)        not null default '',				-- ��������
	icon					varchar(32)		   not null,						-- �ؿ�ͼ��	
	type					int4			   not null default 1,      		-- ���� 1����ͨ���� 2����Ӣ���� 3:�����
	level       			int4,												-- ��͵ȼ�Ҫ�� 
	quality					int4               not null default 1,				-- Ʒ���Ѷȵ͵���1��2��3
	
	des                     varchar(256),										-- ����
						
    unique(name),	
	primary key (id)
);

-- ������Ӧ�ؿ���
create table t_fuben_city
(
	id						bigserial,
	fuben_id				bigint              not null references t_fuben,
	city_id					int4                not null references t_city,						-- �ؿ�ID	
						
	unique(fuben_id, city_id),	
	primary key (id)
);

-- �û���Ӧͨ���Ĺؿ�
create table t_user_city
(
	id						bigserial,
	user_id					bigint              not null references t_user,
	city_id					int4                not null references t_city,						-- �ؿ�ID
	star					int4,																-- ��ȡ����		
						
	unique(user_id, city_id),	
	primary key (id)
);