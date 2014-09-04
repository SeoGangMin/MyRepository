--select_member_one
SELECT 
	member_idx, 
	member_name, 
	member_pwd,
	member_phone, 
	DATE_FORMAT(member_reg_date, '%Y%m%d')AS member_reg_date
FROM 
	tb_member
WHERE
	member_name = '',
AND
	member_pwd  = '',
AND
	member_phone = '';
	
--insert_member
INSERT INTO tb_member(			
	member_name,
	member_pwd,
	member_phone,
	member_reg_date
	)
VALUES(
		'', 
		'', 
		'',
		DATE_FORMAT(now(), '%Y%m%d')
	)