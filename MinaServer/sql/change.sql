create table t_fuben
(
	id						bigserial,
	name					varchar(64)        not null default '',				-- ��������
	icon					varchar(64)		   not null,						-- �ؿ�ͼ��	
	type					int4			   not null default 1,      		-- ���� 1����ͨ���� 2����Ӣ���� 3:�����
	level       			int4,												-- ��͵ȼ�Ҫ�� 
	quality					int4               not null default 1,				-- Ʒ���Ѷȵ͵���1��2��3
	
	des                     varchar(256),										-- ����
						
    unique(name),	
	primary key (id)
);