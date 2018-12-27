/*
SQLyog Community v12.2.1 (64 bit)
MySQL - 5.6.11-enterprise-commercial-advanced : Database - emc_schema
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`emc_schema` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `emc_schema`;

/*Table structure for table `account_detail` */

DROP TABLE IF EXISTS `account_detail`;

CREATE TABLE `account_detail` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_type` varchar(50) NOT NULL,
  `client_correlation_id` varchar(50) NOT NULL,
  `company_address` varchar(150) DEFAULT NULL,
  `company_city` varchar(150) DEFAULT NULL,
  `company_contact` varchar(150) DEFAULT NULL,
  `company_country` varchar(150) DEFAULT NULL,
  `company_fax` varchar(150) DEFAULT NULL,
  `company_pin_code` varchar(150) DEFAULT NULL,
  `company_state` varchar(150) DEFAULT NULL,
  `company_name` varchar(50) NOT NULL,
  `contact_number` varchar(50) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `is_2fa_enabled` bit(1) DEFAULT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `password` varchar(200) NOT NULL,
  `reset_token` varchar(150) DEFAULT NULL,
  `security_code` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','SUSPENDED','TERMINATED') NOT NULL DEFAULT 'ACTIVE',
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `account_detail` */

insert  into `account_detail`(`account_id`,`account_type`,`client_correlation_id`,`company_address`,`company_city`,`company_contact`,`company_country`,`company_fax`,`company_pin_code`,`company_state`,`company_name`,`contact_number`,`created_by`,`created_date`,`description`,`email`,`is_2fa_enabled`,`modified_by`,`modified_date`,`password`,`reset_token`,`security_code`,`status`,`username`) values 
(1,'1','Y8jAm7LzPH','140 Robinson Road, #04-01,','Singapore','9548950904','Singapore','123456','068907','Singapore','SendX Pte Ltd','1234567890',NULL,'2018-02-13 16:07:15','SendX Pte Ltd','info@sendxsg.com','\0',NULL,'2018-02-13 16:07:15','$2a$10$QLEyflY86xYdKC8jQndSz.dIKnPEsdZUAOuMnqkylt4u5sJP8hvMK',NULL,'3bx2 cxuh ryau 47ki dz2u fap4 uoo3 qipn','ACTIVE','sgt-admin'),
(9,'0','f34dc00e9bf94946abcac6ff9add473f',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'John Smith Nic.','7894561230',NULL,'2018-03-16 13:49:26','','JohnSmith@yopmail.com','\0',NULL,'2018-03-16 13:49:26','$2a$10$.YA1pQB4HOmZApvJfRTxEuk4fq2irGlRARHKZ6IhB1M/.Pu8ryitm',NULL,NULL,'ACTIVE','John Smith'),
(10,'0','8762819fa3d1448f8a04d9a1121b72e0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'LG Enterprise','6091898769',NULL,'2018-03-16 15:55:44','Testing to check if account is working','tin@ammbrtech.com','\0',NULL,'2018-03-16 15:55:44','$2a$10$n0eEW2LcvVIwejhiEJqmdeODFGbDzN/3qHLVEMuymTj7bqQEllGe.',NULL,NULL,'ACTIVE','Tin Wee'),
(11,'0','23dbdff23de2474d9f0ef294c8f2ca18',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Samsung Ltd','0146188896',NULL,'2018-03-16 15:57:52','Testing 345678wiurqyweroeuqirjkafhdkhjasdgjk\'f','tinwee25@gmail.com','\0',NULL,'2018-03-16 15:57:52','$2a$10$fA/vRvqOkIqAaBcR/WeYket6kNUL9b7mDzBI5CskwXdXYztfDU3sm','435cdb0d-aa7f-43c5-ba5e-8bd9456d2850',NULL,'ACTIVE','Woo Yeok Bin'),
(12,'0','a48b7d75e6974f13badc536f3b6d52a4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'NSystem','9897095008',NULL,'2018-03-21 05:11:18','','NSystem@yopmail.com','\0',NULL,'2018-03-21 05:11:18','$2a$10$Gd2oIDR7Y.ZIk7ZxzlXcge9idftkF9LTa3Vm6dU38tH9ibqy5O1u2',NULL,NULL,'ACTIVE','NSystem'),
(13,'0','d1d06a48403a407790b2574608edc4fb',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'JP Alliance','8285314693',NULL,'2018-03-21 05:22:28','','JPAlliance@yopmail.com','\0',NULL,'2018-03-21 05:22:28','$2a$10$mI3vRLKFwMvK5K2pPZAFzuWrZEgCmTI2Diz.32OFIz2nOc/lnb6wm',NULL,NULL,'ACTIVE','JP Alliance'),
(16,'0','bca3dcd665314b52814e9bb89ab4b875',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Tin Wee Corp','6591898766',NULL,'2018-03-31 04:07:35','tester account','chen_dingwei@hotmail.com','\0',NULL,'2018-03-31 04:07:35','$2a$10$YZGiLQMpXu68XQ5trBeKxu18RkKU1obBRq1YDjKhr5Qk410iEy6j6','38ecd55d-46e4-43a5-8ba3-787827c33abe',NULL,'ACTIVE','tester'),
(25,'0','6e6651046ea74b7385fd3c630d644982',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'RSM','8285314693',NULL,'2018-04-19 02:18:48','','rsm@yopmail.com','\0',NULL,'2018-04-19 02:18:48','$2a$10$HqZOkvryYhJQK5Wk88SW4.EFYY29ia.hNpzLT1OO2FKOz8kYZJqEC',NULL,NULL,'TERMINATED','rsm'),
(26,'0','85b22b4705bd4edc8c9e00f0a8c5ebcf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'popp','1254789865',NULL,'2018-04-19 16:22:54','test','popp@yopmail.com','\0',NULL,'2018-04-19 16:22:54','$2a$10$Fnav9Gn.VDC0In5jy8s9YueWtbWe2r4RLoXrOdX8ZtqZ2FyWFdgYe','389565b1-8fc5-4ce6-b0ae-03bb8ac04347',NULL,'TERMINATED','popp');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
