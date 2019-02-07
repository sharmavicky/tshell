ALTER TABLE `tshell`.`user` 
CHANGE COLUMN `us_emp_id` `us_emp_id` VARCHAR(10) NOT NULL ;

ALTER TABLE `tshell`.`user` 
MODIFY COLUMN `us_last_login_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ;

INSERT INTO `tshell`.`user` (`us_name`, `us_email`, `us_password`, `us_ur_id`, `us_emp_id`) VALUES ('', '', '21232f297a57a5a743894a0e4a801fc3', '1', 'admin');