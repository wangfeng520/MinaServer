copy t_user(id, name, level, exp, maxexp, gold, silver, power) from stdin;
1	wangfeng	1	10	100	200	300	10
2	guiming	1	10	100	200	300	10
3	wuyufan	1	10	100	200	300	10
\.


copy t_account(id, user_id, login, psw, count, last_date) from stdin;
1	1	wf	123	100	2014-6-18 
2	2	gm	123	100	2014-6-18
3	3	yf	123	100	2014-6-18
\.

copy t_level_exp(id, level, exp) from stdin;
1	1	500
2	2	1000
3	3	2000
4	4	5000
\.