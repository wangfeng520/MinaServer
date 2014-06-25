create table t_card
(
	id						bigserial,
	
	primary key (id)
);


create table t_user_card
(
	id						bigserial,
	user_id                 bigint		  not null references t_user,
	card_id                 bigint		  not null references t_user,
	leader					int4          not null defalut 0,             -- 是否是队长

	unique(user_id, card_id),
	primary key (id)
);