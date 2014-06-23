create table t_user
(
	id						bigserial,
	name					varchar(100)		not null,										-- êÇ³Æ
	login					varchar(100)        not null,										-- µÇÂ½Ãû
	psw						varchar(100)		not null,										-- ÃÜÂë
	qq						varchar(32) ,
	
	
	unique(login),
	primary key (id)
);