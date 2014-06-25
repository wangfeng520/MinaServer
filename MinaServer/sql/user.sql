create table t_user
(
	id						bigserial,
	name					varchar(100)		not null,									--�ǳ�
	level         			int4            	not null,		                    		--��ɫ�ĵȼ�
	exp            			int8   				not null,									--��ɫ�ĵ�ǰ����ֵ
	maxexp         			int8   				not null,   								--��ɫ�������ֵ
	gold        		    int8   				not null default 0,   						--��ҵ�����	
	silver          		int8    			not null default 0, 						--���ӵ�����
	power         			int8  				not null default 0,							--��ǰ����ֵ�����ֵһ����
	
	primary key (id)
);

create table t_account
(
	id						bigserial,
	user_id					bigint              not null references t_user,
	login					varchar(100)        not null,										-- ��½��
	psw						varchar(100)		not null,										-- ����
	count					int4				not null default 0,								-- ��¼����
	last_date			    timestamp,															-- ���һ�ε�¼����
	
	unique(login),
	primary key (id)
);

create table t_level_exp
(
	id						bigserial,
	level         			int4                not null,   			  						--��ɫ�ĵȼ�
	exp            			int8				not null,										--��ɫ�ĵ�ǰ����ֵ
	unique(level),
	primary key (id)
);