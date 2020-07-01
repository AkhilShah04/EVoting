/*
SQLyog Community Edition- MySQL GUI v7.01 
MySQL - 5.0.27-community-nt : Database - blockevoting
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`blockevoting` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `blockevoting`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `name` varchar(100) default NULL,
  `username` varchar(100) default NULL,
  `password` varchar(100) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `admin` */

insert  into `admin`(`name`,`username`,`password`) values ('ABC','admin','admin');

/*Table structure for table `candidate_details` */

DROP TABLE IF EXISTS `candidate_details`;

CREATE TABLE `candidate_details` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) default '',
  `partyname` varchar(100) default '',
  `partyimage` varchar(100) default '',
  `manifesto` varchar(100) default '',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `candidate_details` */

insert  into `candidate_details`(`id`,`name`,`partyname`,`partyimage`,`manifesto`) values (1,'Chetan Wagadari','BJP','baleno.jpg','something'),(2,'qwerty asdf','congress','mercedes.jpeg','sfhkjjkj');

/*Table structure for table `datataker` */

DROP TABLE IF EXISTS `datataker`;

CREATE TABLE `datataker` (
  `id` varchar(200) default NULL,
  `product` varchar(200) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `datataker` */

insert  into `datataker`(`id`,`product`) values ('1234','mobiloe,phone,t-shirt'),('1235','mobiloe,tv,t-shirt'),('1236','mobiloe,tv,t-shirt,ik');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `ID` int(100) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `SURNAME` varchar(255) NOT NULL,
  `ADDRESS` varchar(255) NOT NULL,
  `CONTACT_NO` decimal(10,0) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `employee` */

insert  into `employee`(`ID`,`NAME`,`SURNAME`,`ADDRESS`,`CONTACT_NO`) values (1,'Mahendra','Gawade','Airoli','976808363'),(2,'Sagar','Chavan','Thane','976823663'),(3,'Ningesh','More','Rabale','9768084568'),(4,'Nilesh','Tatkari','Panvel','9123454630'),(6,'Mahendra','Gawade','Airoli','9768083631');

/*Table structure for table `partyaccount` */

DROP TABLE IF EXISTS `partyaccount`;

CREATE TABLE `partyaccount` (
  `partyname` longtext,
  `account` longtext,
  `keystorpath` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `partyaccount` */

insert  into `partyaccount`(`partyname`,`account`,`keystorpath`) values ('bsp.jpg','0xda5a8d1a45661dea99bafbe6a68f9c8841c911d5','UTC--2020-01-30T04-53-03.171000000Z--da5a8d1a45661dea99bafbe6a68f9c8841c911d5.json'),('shivsena.jpg','0x137a10d780c0826ae18e78d4609e324a115a0fed','UTC--2020-01-30T04-53-05.327000000Z--137a10d780c0826ae18e78d4609e324a115a0fed.json'),('congress.jpg','0xa9f3b54cab039a71081d653594cc492e22b6d05e','UTC--2020-01-30T04-53-07.203000000Z--a9f3b54cab039a71081d653594cc492e22b6d05e.json'),('bjp.jpg','0x3211284b14f974a0ce6ec849e8f242275d3905dd','UTC--2020-01-30T04-53-08.879000000Z--3211284b14f974a0ce6ec849e8f242275d3905dd.json');

/*Table structure for table `userentry` */

DROP TABLE IF EXISTS `userentry`;

CREATE TABLE `userentry` (
  `username` varchar(255) default NULL,
  `encpassword` varchar(255) default NULL,
  `gender` varchar(255) default NULL,
  `day` varchar(255) default NULL,
  `month` varchar(255) default NULL,
  `year` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `mail` varchar(255) default NULL,
  `mobile` varchar(255) default NULL,
  `uid` varchar(255) NOT NULL,
  `islogin` int(11) default NULL,
  `otp` varchar(10) default NULL,
  `useraccount` longtext,
  `keystorepath` longtext,
  PRIMARY KEY  (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userentry` */

insert  into `userentry`(`username`,`encpassword`,`gender`,`day`,`month`,`year`,`location`,`mail`,`mobile`,`uid`,`islogin`,`otp`,`useraccount`,`keystorepath`) values ('a','a',NULL,NULL,NULL,NULL,NULL,'ningesh1406@gmail.com','8655221446','123456789123',0,'4360','0x0521c8d47b39b0c12a8595d19a0684815a64233c','UTC--2020-01-30T05-12-47.426000000Z--0521c8d47b39b0c12a8595d19a0684815a64233c.json');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
