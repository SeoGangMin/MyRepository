
-- select_fi_list
SELECT
	fi_idx, 
	fi_no,
	fi_noti_yn,
	DATE_FORMAT(fi_noti_date, "%Y%m%d") AS fi_noti_date,
	DATE_FORMAT(fi_delivery_date, "%Y%m%d") AS fi_delivery_date,
	member_idx
FROM
	tb_fertilization_info
WHERE
	member_idx = '';
	
--select_fi_one
SELECT
	fi_idx, 
	fi_no,
	fi_noti_yn,
	DATE_FORMAT(fi_noti_date, "%Y%m%d") AS fi_noti_date,
	DATE_FORMAT(fi_delivery_date, "%Y%m%d") AS fi_delivery_date,
	member_idx
FROM
	tb_fertilization_info
WHERE
	member_idx = ''
AND 
	fi_idx = ''; 
	
--select_delivery_ready_list
SELECT 
	fi_idx, 
	fi_no, 
	fi_noti_yn, 
	DATE_FORMAT(fi_noti_yn, "%Y%m%d") AS fi_noti_date,
	DATE_FORMAT(fi_delivery_date, "%Y%m%d") AS fi_delivery_date,
	member_idx
FROM 
	tb_fertilization_info
WHERE 
	fi_delivery_date  
IN(
	SELECT 
		DATE_FORMAT(fi_delivery_date, "%Y%m%d") 
	FROM 
		tb_fertilization_info 
	WHERE 
		fi_delivery_date <= ''
	AND	
		fi_idx = ''
	AND	
		member_idx = '';
)

--select_fi_max_idx
SELECT 
	COALESCE(MAX(fi_idx),1) as fi_idx 
FROM 
	tb_fertilization_info;

--delete_expire_fi(modify)
DELETE t1.*, t2.* FROM 
	tb_cattle_info t1, tb_reg_info t2
WHERE 
	t1.cattle_idx = t2.cattle_idx
AND 
	t1.cattle_idx 
IN(
	SELECT sub.cattle_idx FROM(
		SELECT 
			cattle_idx
		FROM 
			tb_cattle_info
		WHERE 
			cattle_out_date = DATE_FORMAT(NOW(), "%Y%m%d")	
		AND	
			member_idx = #member_idx#
	)AS sub
);

--delete_fi_one
DELETE FROM
	tb_fertilization_info
WHERE
	fi_idx = ''
AND 
	member_idx = '';
	
--update_fi_delivery_date
UPDATE 
	tb_fertilization_info 
SET 
	fi_delivery_date = '' 
WHERE 
	fi_idx = ''
AND
	member_idx = '';
	
--insert_fi
INSERT INTO 
	tb_fertilization_info
	(
		fi_idx, 
		fi_no,
		fi_noti_yn,
		fi_noti_date,
		fi_delivery_date,
		member_idx
	)
VALUES
	(
		'',
		'',
		'',
		'',
		''		
	);
	

-- select_fd_list
SELECT 	
	fd_idx,
	DATE_FORMAT(fd_reg_date, "%Y%m%d") AS fd_reg_date,
	fi_idx
FROM 
	tb_fertilization_date
WHERE
	fd_idx = '';	
AND
	member_idx=''; 

-- select_fd_one
SELECT 	
	fd_idx,
	DATE_FORMAT(fd_reg_date, "%Y%m%d") AS fd_reg_date,
	fi_idx
FROM 
	tb_fertilization_date
WHERE
	fd_idx = ''
AND
	fi_idx = ''
AND
	member_idx='';

--delete_fd_one
DELETE FROM
	tb_fertilization_date
WHERE
	fd_idx = ''
AND
	fi_idx = ''
AND
	member_idx='';

--delete_fd_list
DELETE FROM
	tb_fertilization_date
WHERE
	fd_idx = ''
AND
	member_idx='';
	
--insert_fd
INSERT INTO 
	tb_fertilization_date
	(
		fd_reg_date,
		fi_idx,
		member_idx
	)
VALUES
	(
		'',
		'',
		''
	)
	
	
	
	
	
	