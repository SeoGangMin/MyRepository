CREATE TABLE `test`.`tb_fertilization_date` (
  `fd_idx` INT NOT NULL AUTO_INCREMENT,
  `fd_reg_date` DATE NOT NULL,
  `fi_idx` INT NOT NULL,
  `member_idx` INT NOT NULL,
  PRIMARY KEY (`fert_idx`))
COMMENT = '수정날짜 테이블';


CREATE TABLE `test`.`tb_fertilization_info` (
  `fi_idx` INT NOT NULL AUTO_INCREMENT,
  `fi_no` CHAR(9) NOT NULL,
  `fi_noti_yn` CHAR(1) NOT NULL DEFAULT 'N',
  `fi_noti_date` DATE NOT NULL,
  `fi_delivery_date` DATE NOT NULL,
  `member_idx` INT NOT NULL,
  PRIMARY KEY (`fi_idx`))
COMMENT = '임신우 정보 테이블';


CREATE TABLE `test`.`tb_member` (
  `member_idx` INT NOT NULL AUTO_INCREMENT,
  `member_name` CHAR(10) NOT NULL,
  `member_pwd` CHAR(10) NOT NULL,
  `member_phone` CHAR(10) NOT NULL,
  `member_reg_date` DATE NOT NULL,
  `member_renew_date` DATE NULL,
  PRIMARY KEY (`member_idx`))
COMMENT = '회원 정보 테이블		';


