create table t_user
(
	id						bigserial,
	name					varchar(100)		not null,										-- �ǳ�
	login					varchar(100)        not null,										-- ��½��
	psw						varchar(100)		not null,										-- ����
	qq						varchar(32) ,
	
	
	unique(login),
	primary key (id)
);