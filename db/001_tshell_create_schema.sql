-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: tshell
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

create schema tshell;

use tshell;

--
-- Table structure for table `assessment`
--

DROP TABLE IF EXISTS `assessment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment` (
  `as_id` int(11) NOT NULL AUTO_INCREMENT,
  `as_type` varchar(6) NOT NULL,
  `as_start_time` datetime NOT NULL,
  `as_score` float NOT NULL,
  `as_sk_id` int(11) NOT NULL,
  `as_us_id` int(11) NOT NULL,
  `as_end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`as_id`),
  KEY `as_sk_id` (`as_sk_id`),
  KEY `as_us_id` (`as_us_id`),
  CONSTRAINT `as_sk_id` FOREIGN KEY (`as_sk_id`) REFERENCES `skill` (`sk_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `as_us_id` FOREIGN KEY (`as_us_id`) REFERENCES `user` (`us_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assessment`
--

LOCK TABLES `assessment` WRITE;
/*!40000 ALTER TABLE `assessment` DISABLE KEYS */;
/*!40000 ALTER TABLE `assessment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assessment_question`
--

DROP TABLE IF EXISTS `assessment_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_question` (
  `aq_id` int(11) NOT NULL AUTO_INCREMENT,
  `aq_as_id` int(11) NOT NULL,
  `aq_qu_id` int(11) NOT NULL,
  PRIMARY KEY (`aq_id`),
  KEY `aq_as_id` (`aq_as_id`),
  KEY `aq_qu_id` (`aq_qu_id`),
  CONSTRAINT `aq_as_id` FOREIGN KEY (`aq_as_id`) REFERENCES `assessment` (`as_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `aq_qu_id` FOREIGN KEY (`aq_qu_id`) REFERENCES `question` (`qu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assessment_question`
--

LOCK TABLES `assessment_question` WRITE;
/*!40000 ALTER TABLE `assessment_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `assessment_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assessment_question_option`
--

DROP TABLE IF EXISTS `assessment_question_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assessment_question_option` (
  `ao_id` int(11) NOT NULL AUTO_INCREMENT,
  `ao_aq_id` int(11) DEFAULT NULL,
  `ao_op_id` int(11) DEFAULT NULL,
  `ao_is_selected` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ao_id`),
  KEY `ao_aq_fk` (`ao_aq_id`),
  KEY `ao_op_fk` (`ao_op_id`),
  CONSTRAINT `ao_aq_fk` FOREIGN KEY (`ao_aq_id`) REFERENCES `assessment_question` (`aq_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ao_op_fk` FOREIGN KEY (`ao_op_id`) REFERENCES `option` (`op_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assessment_question_option`
--

LOCK TABLES `assessment_question_option` WRITE;
/*!40000 ALTER TABLE `assessment_question_option` DISABLE KEYS */;
/*!40000 ALTER TABLE `assessment_question_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `option`
--

DROP TABLE IF EXISTS `option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `option` (
  `op_id` int(11) NOT NULL AUTO_INCREMENT,
  `op_description` varchar(250) DEFAULT NULL,
  `op_qu_id` int(11) NOT NULL,
  `op_is_correct` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`op_id`),
  KEY `op_qu_fk` (`op_qu_id`),
  CONSTRAINT `op_qu_fk` FOREIGN KEY (`op_qu_id`) REFERENCES `question` (`qu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `option`
--

LOCK TABLES `option` WRITE;
/*!40000 ALTER TABLE `option` DISABLE KEYS */;
/*!40000 ALTER TABLE `option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `qu_id` int(11) NOT NULL AUTO_INCREMENT,
  `qu_question` varchar(500) DEFAULT NULL,
  `qu_qd_id` int(11) NOT NULL,
  `qu_status` varchar(45) NOT NULL,
  `qu_created_by_us_id` int(11) DEFAULT NULL,
  `qu_qt_id` int(11) DEFAULT NULL,
  `qu_reviewed_by_us_id` int(11) DEFAULT NULL,
  `qu_created_date` date DEFAULT NULL,
  `qu_reviewed_date` date DEFAULT NULL,
  PRIMARY KEY (`qu_id`),
  KEY `qu_df_id` (`qu_qd_id`),
  KEY `qu_us_fk` (`qu_created_by_us_id`),
  KEY `qu_qt_id` (`qu_qt_id`),
  CONSTRAINT `qu_df_id` FOREIGN KEY (`qu_qd_id`) REFERENCES `question_difficulty` (`qd_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `qu_qt_id` FOREIGN KEY (`qu_qt_id`) REFERENCES `question_answer_type` (`qt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `qu_us_fk` FOREIGN KEY (`qu_created_by_us_id`) REFERENCES `user` (`us_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'what is ..',1,'y',1,1,1,'2018-01-01','2018-01-01'),(2,'how..',1,'n',1,1,1,'2018-01-01','2018-01-01'),(3,'when',1,'n',1,1,1,'2018-01-01','2018-01-01'),(4,'weeef',1,'n',1,1,1,'2018-01-01','2018-01-01'),(5,'weff',1,'n',1,1,1,'2018-01-01','2018-01-01'),(6,'head tag',1,'n',1,1,1,'2018-01-01','2018-01-01'),(7,'body tag',1,'n',1,1,1,'2018-01-01','2018-01-01'),(8,'IOC',1,'n',1,1,1,'2018-01-01','2018-01-01'),(9,'DI',1,'n',1,1,1,'2018-01-01','2018-01-04'),(10,'service',1,'n',1,1,1,'2018-01-01','2018-01-01'),(11,'color',1,'n',1,1,1,'2018-01-01','2018-01-01'),(12,'style',1,'n',1,1,1,'2018-01-01','2018-01-01');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_answer_type`
--

DROP TABLE IF EXISTS `question_answer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_answer_type` (
  `qt_id` int(11) NOT NULL AUTO_INCREMENT,
  `qt_type` varchar(15) NOT NULL DEFAULT 'single',
  PRIMARY KEY (`qt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_answer_type`
--

LOCK TABLES `question_answer_type` WRITE;
/*!40000 ALTER TABLE `question_answer_type` DISABLE KEYS */;
INSERT INTO `question_answer_type` VALUES (1,'Single');
INSERT INTO `question_answer_type` VALUES (2,'Multiple');
/*!40000 ALTER TABLE `question_answer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_difficulty`
--

DROP TABLE IF EXISTS `question_difficulty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_difficulty` (
  `qd_id` int(11) NOT NULL AUTO_INCREMENT,
  `qd_difficulty` varchar(7) NOT NULL,
  PRIMARY KEY (`qd_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_difficulty`
--

LOCK TABLES `question_difficulty` WRITE;
/*!40000 ALTER TABLE `question_difficulty` DISABLE KEYS */;
INSERT INTO `question_difficulty` VALUES (1,'hard');
/*!40000 ALTER TABLE `question_difficulty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skill`
--

DROP TABLE IF EXISTS `skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skill` (
  `sk_id` int(11) NOT NULL AUTO_INCREMENT,
  `sk_name` varchar(45) DEFAULT NULL,
  `sk_search_count` int(11) DEFAULT NULL,
  `sk_active` varchar(45) NOT NULL,
  `sk_test_count` int(11) DEFAULT NULL,
  `sk_description` varchar(500) DEFAULT NULL,
  `sk_image` longblob,
  `sk_creation_date` date DEFAULT NULL,
  PRIMARY KEY (`sk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill`
--

LOCK TABLES `skill` WRITE;
/*!40000 ALTER TABLE `skill` DISABLE KEYS */;
INSERT INTO `skill` VALUES (1,'Java',14,'active',0,'Java is a popular programming language, created in 1995. It is owned by Oracle, and more than 3 billion devices run Java. Java works on different platforms (Windows, Mac, Linux, Raspberry Pi, etc.). It is one of the most popular programming language in the world. It is easy to learn and simple to use. It is used for: Mobile applications (specially Android apps), Desktop applications, Web applications, Web servers and application servers etc.',NULL,'2018-01-10'),(2,'HTML',0,'active',0,'HTML stands for Hyper Text Markup Language. It is used to design web pages using markup language. HTML is the combination of Hypertext and Markup language. Hypertext defines the link between the web pages. Markup language is used to define the text document within tag which defines the structure of web pages. This language is used to annotate (make notes for the computer) text so that a machine can understand it and manipulate text accordingly.',NULL,'2018-01-10'),(3,'JavaScript',2,'active',0,'JavaScript was initially created to “make web pages alive”. The programs in this language are called scripts. They can be written right in a web page’s HTML and executed automatically as the page loads. They don’t need special preparation or compilation to run. In this aspect, JavaScript is very different from another language called Java. Today, JavaScript can execute not only in the browser, but also on the server, or on any device that has a special program called the JavaScript engine.',NULL,'2018-01-10'),(4,'C++',0,'active',0,'C++ is a highly efficient and flexible language, first created back in 1985. It has remained in high demand due to its performance, reliability, and variety of contexts you can use it in. Plenty of large systems have been created and maintained successfully using C++, including the likes of Microsoft, Oracle, PayPal, and Adobe.',NULL,'2018-01-10'),(5,'C',0,'active',0,'C is a procedural programming language. It was initially developed by Dennis Ritchie between 1969 and 1973. It was mainly developed as a system programming language to write operating system. The main features of C language include low-level access to memory, simple set of keywords, and clean style, these features make C language suitable for system programming like operating system or compiler development.',NULL,'2018-01-10'),(6,'Elixir',0,'active',0,'Elixir was inspired by Erlang, a language developed back in the 1980s by Ericsson and stands as arguably one of the best tools for heavy concurrency. Elixir’s author José Valim himself said that he liked everything about Erlang, but also saw room for improvement. The biggest drawback of Erlang for developers is the often quirky syntax and usability plus the lack of intuitive package management. Thus, a highly developer-friendly language and ecosystem, with those from Erlang.',NULL,'2018-01-10'),(7,'Scala',0,'active',0,'Scala stands for Scalable Language, and is one of the many attempts to “rewrite Java” while improving its drawbacks. Scala code is compiled to run on the Java Virtual Machine (JVM). Scala has the reputation of being a complex language to learn for a junior developer. But those who make it past the learning curve probably enjoy a great career as open positions for Scala developers are definitely popping up more and more.',NULL,'2018-01-10'),(8,'R',2,'active',0,'R is a programming language and environment used for statistics, graphic representation and data analysis. This is the #1 choice for data scientists. If you find yourself interested in this field, then R is a stable and profitable career choice for you.',NULL,'2018-01-10'),(9,'C#',0,'active',0,'C# is a general-purpose, multi-paradigm programming language encompassing strong typing, imperative, declarative, functional, generic, object-oriented, and component-oriented programming disciplines. It was developed around 2000 by Microsoft within its .NET initiative and later approved as a standard by Ecma and ISO.',NULL,'2018-01-10'),(10,'TypeScript',0,'active',0,'JavaScript was introduced as a language for the client side. The development of Node.js has marked JavaScript as an emerging server-side technology too. However, as JavaScript code grows, it tends to get messier, making it difficult to maintain and reuse the code. Moreover, its failure to embrace the features of Object Orientation, strong type checking and compile-time error checks prevents JavaScript from succeeding at the enterprise level as a full-fledged server-side technology.',NULL,'2018-01-10'),(11,'Kotlin',0,'active',0,'Kotlin is a statically typed programming language that runs on the Java virtual machine and also can be compiled to JavaScript source code. It is sponsored and developed by JetBrains. The JVM implementation of the Kotlin standard library is designed to interoperate with Java code and relies on Java code from the existing Java Class Library. Kotlin uses aggressive type inference to determine the types of values and expressions for which type has been left unstated.',NULL,'2018-01-10'),(12,'Go',0,'active',0,'Go (often referred to as Golang) is a programming language designed by Google engineers Robert Griesemer, Rob Pike, and Ken Thompson. Go is statically typed, compiled, and syntactically similar to C, with the added benefits of memory safety, garbage collection, structural typing, and CSP-style concurrency. The compiler, tools, and source code are all free and open source.',NULL,'2018-01-10'),(13,'F#',0,'active',0,'F# is a mature, open source, cross-platform, functional-first programming language. It empowers users and organizations to tackle complex computing problems with simple, maintainable and robust code. F# runs on Linux, Mac OS X, Android, iOS, Windows, GPUs, and browsers. It is free to use and is open source under an OSI-approved license. F# is used in a wide range of application areas and is supported by both an active open community and industry of professionals.',NULL,'2018-01-10'),(14,'Clojure',0,'active',0,'Clojure is a dynamic, general-purpose programming language, combining the approachability and interactive development of a scripting language with an efficient and robust infrastructure for multithreaded programming. Clojure is a compiled language, yet remains completely dynamic – every feature supported by Clojure is supported at runtime. Clojure provides easy access to the Java frameworks to ensure that calls to Java can avoid reflection.',NULL,'2018-01-10'),(15,'Perl',0,'active',0,'Perl is a family of two high-level, general-purpose, interpreted, dynamic programming languages, Perl 5 and Perl 6.[7]',NULL,'2018-01-10'),(16,'Matlab',0,'active',0,'MATLAB (matrix laboratory) is a multi-paradigm numerical computing environment and proprietary programming language developed by MathWorks. MATLAB allows matrix manipulations, plotting of functions and data, implementation of algorithms, creation of user interfaces, and interfacing with programs written in other languages, including C, C++, C#, Java, Fortran and Python. Although MATLAB is intended primarily for numerical computing, an optional toolbox uses the MuPAD symbolic engine.',NULL,'2018-01-10'),(17,'Express',0,'active',0,'Express or Expressjs is a minimal and flexible framework that provides a robust set of features for web and mobile applications. It is relatively minimal meaning many features are available as plugins. Express facilitates rapid development of Node.js based Web applications. Express is also one major component of the MEAN software bundle. Language : JavaScript',NULL,'2018-01-10'),(18,'Socket.io',0,'active',0,'Socket.IO is a JavaScript library for realtime web applications. It enables realtime, bi-directional communication between web clients and servers. It has two parts: a client-side library that runs in the browser, and a server-side library for Node.js. Both components have a nearly identical API. Like Node.js, it is event-driven. Socket.IO primarily uses the WebSocket protocol with polling as a fallback option, while providing the same interface. It can be installed with the npm tool.',NULL,'2018-01-10'),(19,'Meteor',0,'active',0,'Meteor or MeteorJS is another framework which gives one a radically simpler way to build realtime mobile and web apps. It allows for rapid prototyping and produces cross-platform (Web, Android, iOS) code. Its cloud platform, Galaxy, greatly simplifies deployment, scaling, and monitoring. Language : JavaScript',NULL,'2018-01-10'),(20,'Mean.js',1,'active',0,'The term MEAN stack refers to a collection of JavaScript based technologies used to develop web applications. MEAN is an acronym for MongoDB, ExpressJS, AngularJS and Node.js. From client to server to database, MEAN is full stack JavaScript. This article explores the basics of the MEAN stack and shows how to create a simple bucket list application.',NULL,'2018-01-10'),(21,'Sails.js',0,'active',0,'Sails is a Javascript framework designed to resemble the MVC architecture from frameworks like Ruby on Rails. It makes the process of building Node.js apps easier, especially APIs, single page apps and realtime features, like chat. To install Sails, it is quite simple. The prerequisites are to have Node.js installed and also npm, which comes with Node. Then one must issue the following command in the terminal: npm install sails -g.',NULL,'2018-01-10'),(22,'Ruby on Rails',0,'active',0,'Ruby on Rails is an extremely productive web application framework written by David Heinemeier Hansson. One can develop an application at least ten times faster with Rails than a typical Java framework. Moreover, Rails includes everything needed to create a database-driven web application, using the Model-View-Controller pattern. Language : Ruby',NULL,'2018-01-10'),(23,'Django',0,'active',0,'Django is another framework that helps in building quality web applications. It was invented to meet fast-moving newsroom deadlines, while satisfying the tough requirements of experienced Web developers. Django developers say the applications are it’s ridiculously fast, secure, scalable and versatile. Language : Python',NULL,'2018-01-10'),(24,'Angular ',0,'active',0,'Angular JS is a framework by Google (originally developed by Misko Hevery and Adam Abrons) which helps us in building powerful Web Apps. It is a framework to build large scale and high performance web application while keeping them as easy-to-maintain. There are a huge number of web apps that are built with Angular JS and can be found here https://www.madewithangular.com. Language : JavaScript',NULL,'2018-01-10'),(25,'ASP.NET',0,'active',0,'ASP.NET is a framework developed by Microsoft, which helps us to build robust web applications for PC, as well as mobile devices. It is a high performance and lightweight framework for building Web Applications using .NET.  All in all, a framework with Power, Productivity and Speed. Language : C#',NULL,'2018-01-10'),(26,'Laravel',0,'active',0,'Laravel is a framework created by Taylor Otwell in 2011 and like all other modern frameworks, it also follows the MVC architectural pattern. Laravel values Elegance, Simplicity, and Readability. One can rightaway start learning and developing Laravel with Laracasts which has hundreds of tutorials in it. Language : PHP',NULL,'2018-01-10'),(27,'Spring',0,'active',0,'Spring, developed by Pivotal Software, is the most popular application development framework for enterprise Java. Myriads of developers around the globe use Spring to create high performance and robust Web apps. Spring helps in creating simple, portable, fast and flexible JVM-based systems and applications. Language : Java',NULL,'2018-01-10'),(28,'PLAY',0,'active',0,'Play is one of the modern web application framework written in Java and Scala. It  follows the MVC architecture and aims to optimize developer productivity by using convention over configuration, hot code reloading and display of errors in the browser. Play quotes itself as “The High Velocity Web Framework”.  Language : Scala and Java',NULL,'2018-01-10'),(29,'CodeIgniter',0,'active',0,'CodeIgniter, developed by EllisLab, is a famous web application framework to build dynamic websites. It is loosely based on MVC architecture since Controller classes are necessary but models and views are optional. CodeIgnitor promises with exceptional performance, nearly zero configuration and no large-scale monolithic libraries. Language : PHP',NULL,'2018-01-10'),(30,'Rust',0,'active',0,'Rust is a compiled language which is often compared to C both in terms of use cases and performance. The main difference is that Rust is memory safe. One of the most common faults you could find in C code are dangling pointers, buffer overflows, or any other kind of memory errors. Rust is created with the purpose of avoiding those the language literally makes it impossible for you to make such errors as they are caught during compilation (before the code was ever run).',NULL,'2018-01-10'),(31,'Swift',0,'active',0,'Swift is a relatively new programming language released by Apple in 2014. This is a language for developing native iOS or macOS applications. It is considered an improvement in terms of usability and performance compared to Objective-C the language used for Apple’s iOS and macOS operating systems.',NULL,'2018-01-10'),(32,'Ruby',0,'active',0,'Ruby is a high-level language which aims to achieve a lot with few lines of clean, readable code. This sometimes takes significant effort “under the hood,” which makes Ruby relatively slower in terms of efficiency compared to other popular languages - but it definitely boosts your productivity. Well-written Ruby code almost looks like sentences written in plain English. It’s a great choice for the first language to learn, as beginners typically pick it up fast and enjoy it along the way.',NULL,'2018-01-10'),(33,'Python',0,'active',0,'Python is a general purpose language which you can find almost anywhere today. You’ll find it in web applications, desktop apps, network servers, machine learning,  media tools and more. It’s used by big players like NASA or Google. Python code is neat, readable, and well structured.',NULL,'2018-01-10');
/*!40000 ALTER TABLE `skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `tp_id` int(11) NOT NULL AUTO_INCREMENT,
  `tp_name` varchar(45) NOT NULL,
  `tp_sk_id` int(11) NOT NULL,
  `tp_percentage` float DEFAULT '0',
  PRIMARY KEY (`tp_id`),
  KEY `tp_sk_id` (`tp_sk_id`),
  CONSTRAINT `tp_sk_id` FOREIGN KEY (`tp_sk_id`) REFERENCES `skill` (`sk_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'oops',1,0),(2,'thread',1,0),(3,'ng model',2,0),(4,'router',2,0),(5,'tag',3,0),(6,'anotation',4,0);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_question`
--

DROP TABLE IF EXISTS `topic_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic_question` (
  `tq_id` int(11) NOT NULL AUTO_INCREMENT,
  `tq_tp_id` int(11) NOT NULL,
  `tq_qu_id` int(11) NOT NULL,
  PRIMARY KEY (`tq_id`),
  KEY `tq_tp_id` (`tq_tp_id`),
  KEY `tq_qu_id` (`tq_qu_id`),
  CONSTRAINT `tq_qu_id` FOREIGN KEY (`tq_qu_id`) REFERENCES `question` (`qu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tq_tp_id` FOREIGN KEY (`tq_tp_id`) REFERENCES `topic` (`tp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_question`
--

LOCK TABLES `topic_question` WRITE;
/*!40000 ALTER TABLE `topic_question` DISABLE KEYS */;
INSERT INTO `topic_question` VALUES (1,1,1),(2,1,2),(4,3,4),(5,4,5),(6,2,3),(7,6,6),(8,5,7),(9,5,8),(10,6,9),(11,5,11),(12,6,10),(13,5,12);
/*!40000 ALTER TABLE `topic_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `us_id` int(11) NOT NULL AUTO_INCREMENT,
  `us_name` varchar(45) DEFAULT NULL,
  `us_email` varchar(45) NOT NULL,
  `us_password` varchar(45) NOT NULL,
  `us_ur_id` int(11) NOT NULL,
  `us_emp_id` varchar(15) DEFAULT NULL,
  `us_image` longblob,
  `us_signup_date` date DEFAULT NULL,
  `us_last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`us_id`),
  KEY `us_ur_id` (`us_ur_id`),
  CONSTRAINT `us_ur_id` FOREIGN KEY (`us_ur_id`) REFERENCES `user_role` (`ur_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'akash','saha.sarvajit@gmail.com','e10adc3949ba59abbe56e057f20f883e',1,'123456',NULL,'2018-04-06','2008-11-09 15:45:21'),(2,'sarvajit','saha.sarvajit@gmail.com','fcea920f7412b5da7be0cf42b8c93759',2,'1234567',NULL,'2018-04-06','2008-11-09 15:45:21');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `ur_id` int(11) NOT NULL AUTO_INCREMENT,
  `ur_role` varchar(45) NOT NULL,
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'admin'),(2,'learner'),(3,'sme');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_skill`
--

DROP TABLE IF EXISTS `user_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_skill` (
  `uk_id` int(11) NOT NULL AUTO_INCREMENT,
  `uk_us_id` int(11) NOT NULL,
  `uk_sk_id` int(11) NOT NULL,
  PRIMARY KEY (`uk_id`),
  KEY `uk_us_id` (`uk_us_id`),
  KEY `uk_sk_id` (`uk_sk_id`),
  CONSTRAINT `uk_sk_id` FOREIGN KEY (`uk_sk_id`) REFERENCES `skill` (`sk_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `uk_us_id` FOREIGN KEY (`uk_us_id`) REFERENCES `user` (`us_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_skill`
--

LOCK TABLES `user_skill` WRITE;
/*!40000 ALTER TABLE `user_skill` DISABLE KEYS */;
INSERT INTO `user_skill` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4);
/*!40000 ALTER TABLE `user_skill` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-07 18:01:33
