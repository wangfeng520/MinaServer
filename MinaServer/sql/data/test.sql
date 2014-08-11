 

alter table t_card add	name					varchar(64)        not null default '';	
alter table t_card add	icon					varchar(128)	   not null;
alter table t_card add	level       			int4			   not null default 1;	
alter table t_card add	quality					int4               not null default 1;
alter table t_card add	tili					int4			   not null default 1;
	
alter table t_card add	action_id				int4						not null default 1;
alter table t_card add	total_hp				int4						not null default 100;
alter table t_card add	attack					int4						not null default 0;
alter table t_card add	fangyu					int4						not null default 0;
alter table t_card add	des  					text;