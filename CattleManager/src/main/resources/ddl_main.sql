
-- select_fi_list
SELECT
	fi_seq, 
	fi_no,
	fi_noti_yn,
	DATE_FORMAT(fi_noti_date, "%Y%m%d") AS fi_noti_date,
	DATE_FORMAT(fi_delivery_date, "%Y%m%d") AS fi_delivery_date,
	member_seq
FROM
	tb_fertilization_info
WHERE
	member_seq = '';
	
--select_fi_one
SELECT
	fi_seq, 
	fi_no,
	fi_noti_yn,
	DATE_FORMAT(fi_noti_date, "%Y%m%d") AS fi_noti_date,
	DATE_FORMAT(fi_delivery_date, "%Y%m%d") AS fi_delivery_date,
	member_seq
FROM
	tb_fertilization_info
WHERE
	member_seq = ''
AND 
	fi_seq = ''; 
	
--select_delivery_ready_list
SELECT 
	fi_seq, 
	fi_no, 
	fi_noti_yn, 
	DATE_FORMAT(fi_noti_yn, "%Y%m%d") AS fi_noti_date,
	DATE_FORMAT(fi_delivery_date, "%Y%m%d") AS fi_delivery_date,
	member_seq
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
		fi_seq = ''
	AND	
		member_seq = '';
)

--select_fi_max_seq
SELECT 
	COALESCE(MAX(fi_seq),1) as fi_seq 
FROM 
	tb_fertilization_info;

--delete_expire_fi(modify)
DELETE t1.*, t2.* FROM 
	tb_cattle_info t1, tb_reg_info t2
WHERE 
	t1.cattle_seq = t2.cattle_seq
AND 
	t1.cattle_seq 
IN(
	SELECT sub.cattle_seq FROM(
		SELECT 
			cattle_seq
		FROM 
			tb_cattle_info
		WHERE 
			cattle_out_date = DATE_FORMAT(NOW(), "%Y%m%d")	
		AND	
			member_seq = #member_seq#
	)AS sub
);

--delete_fi_one
DELETE FROM
	tb_fertilization_info
WHERE
	fi_seq = ''
AND 
	member_seq = '';
	
--update_fi_delivery_date
UPDATE 
	tb_fertilization_info 
SET 
	fi_delivery_date = '' 
WHERE 
	fi_seq = ''
AND
	member_seq = '';
	
--insert_fi
INSERT INTO 
	tb_fertilization_info
	(
		fi_seq, 
		fi_no,
		fi_noti_yn,
		fi_noti_date,
		fi_delivery_date,
		member_seq
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
	fd_seq,
	DATE_FORMAT(fd_reg_date, "%Y%m%d") AS fd_reg_date,
	fi_seq
FROM 
	tb_fertilization_date
WHERE
	fd_seq = '';	
AND
	member_seq=''; 

-- select_fd_one
SELECT 	
	fd_seq,
	DATE_FORMAT(fd_reg_date, "%Y%m%d") AS fd_reg_date,
	fi_seq
FROM 
	tb_fertilization_date
WHERE
	fd_seq = ''
AND
	fi_seq = ''
AND
	member_seq='';

--delete_fd_one
DELETE FROM
	tb_fertilization_date
WHERE
	fd_seq = ''
AND
	fi_seq = ''
AND
	member_seq='';

--delete_fd_list
DELETE FROM
	tb_fertilization_date
WHERE
	fd_seq = ''
AND
	member_seq='';
	
--insert_fd
INSERT INTO 
	tb_fertilization_date
	(
		fd_reg_date,
		fi_seq,
		member_seq
	)
VALUES
	(
		'',
		'',
		''
	)
	
	
	
	
	
	