ALTER TABLE `tshell`.`user` 
ADD COLUMN `us_otp` VARCHAR(100) NULL AFTER `us_last_login_time`,
ADD COLUMN `us_otp_generated_time` DATETIME NULL AFTER `us_otp`;
INSERT INTO `tshell`.`user` (`us_name`, `us_email`, `us_password`, `us_ur_id`, `us_emp_id`) VALUES ('rohitha', 'rohi@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '3', '999999');
