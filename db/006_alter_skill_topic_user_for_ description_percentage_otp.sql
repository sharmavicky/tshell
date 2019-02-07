DROP TABLE IF EXISTS `reference_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reference_skill` (
  `rs_id` int(11) NOT NULL AUTO_INCREMENT,
  `rs_sk_id` int(11) NOT NULL,
  `rs_ref_id` int(11) NOT NULL,
  `rs_classifier` varchar(45) NOT NULL,
  PRIMARY KEY (`rs_id`),
  KEY `rs_sk_id` (`rs_sk_id`),
  KEY `rs_ref_id` (`rs_ref_id`),
  CONSTRAINT `rs_ref_id` FOREIGN KEY (`rs_ref_id`) REFERENCES `skill` (`sk_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rs_sk_id` FOREIGN KEY (`rs_sk_id`) REFERENCES `skill` (`sk_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;


alter table `tshell`.`skill` modify sk_description varchar(500);

alter table `tshell`.`topic` modify tp_percentage int not null default 0;

alter table `tshell`.`user` modify us_otp varchar(50) DEFAULT NULL;

alter table `tshell`.`user` add us_signup_otp varchar(45) DEFAULT NULL;
alter table `tshell`.`user` add us_signup_otp_time datetime DEFAULT NULL;
alter table `tshell`.`user` add us_signup_otp_verify_status varchar(6) DEFAULT NULL;
