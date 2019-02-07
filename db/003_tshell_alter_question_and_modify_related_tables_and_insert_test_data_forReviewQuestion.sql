UPDATE `tshell`.`question_difficulty` SET `qd_difficulty`='Easy' WHERE `qd_id`='1';

INSERT INTO `tshell`.`question_difficulty` (`qd_id`, `qd_difficulty`) VALUES (2, 'Medium'),(3, 'Hard');


INSERT INTO `question`(qu_qd_id,qu_question,qu_status,qu_created_date)
VALUES (2,'Which of the following is not OOPS concept in Java?','Pending','2013-12-12'),
       (2,'Which of the following is a type of polymorphism in Java?','Pending','2017-11-12'),
       (2,'When does method overloading is determined?','Pending','2018-11-15'),
       (2,'What is it called if an object has its own lifecycle and there is no owner?','Pending','2018-12-10'),
       (2,'Which concept of Java is a way of converting real world objects in terms of class?','Pending','2018-12-05'),
       (2,'Which concept of Java is achieved by combining methods and attribute into a class?','Pending','2016-02-12');    
       
UPDATE `tshell`.`question` SET `qu_status`='Pending' where qu_id!=0;
UPDATE `tshell`.`question` SET `qu_qd_id`=2 where qu_id!=0;

INSERT INTO `tshell`.`skill` (sk_name,sk_search_count,sk_active,sk_test_count,sk_description)
VALUES ('Core Java',100,'Yes',5,'A high level programming language');

INSERT INTO `tshell`.`topic` (tp_name,tp_sk_id,tp_percentage)
VALUES ('OOP',5,0);

INSERT INTO `tshell`.`user_skill` (uk_us_id,uk_sk_id)
VALUES (3,5);


INSERT INTO `tshell`.`topic_question` (`tq_tp_id`, `tq_qu_id`) VALUES (7, 13);

INSERT INTO `tshell`.`topic_question` (`tq_tp_id`, `tq_qu_id`) VALUES (7, 14);

INSERT INTO `tshell`.`topic_question` (`tq_tp_id`, `tq_qu_id`) VALUES (7, 15);

INSERT INTO `tshell`.`topic_question` (`tq_tp_id`, `tq_qu_id`) VALUES (7, 16);

INSERT INTO `tshell`.`topic_question` (`tq_tp_id`, `tq_qu_id`) VALUES (7, 17);

INSERT INTO `tshell`.`topic_question` (`tq_tp_id`, `tq_qu_id`) VALUES (7, 18);


INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Inheritance', 13, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Encapsulation', 13, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Polymorphism', 13, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Compilation', 13, 1);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES (' Compile time polymorphism', 14, 1);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Execution time polymorphism', 14, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Multiple polymorphism', 14, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Multilevel polymorphism', 14, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('At run time', 15, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('At compile time', 15, 1);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('At coding time', 15, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('At execution time', 15, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Aggregation', 16, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Composition', 16, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Encapsulation', 16, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Association', 16, 1);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Polymorphism', 17, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Encapsulation', 17, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Abstraction', 17, 1);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Inheritance', 17, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Encapsulation', 18, 1);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Inheritance', 18, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Polymorphism', 18, 0);

INSERT INTO `tshell`.`option` (`op_description`, `op_qu_id`, `op_is_correct`) VALUES ('Polymorphism', 18, 0);

alter table `tshell`.`question` add fulltext(qu_question);