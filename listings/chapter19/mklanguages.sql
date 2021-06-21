-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: dev    Database: papaya5_ith_kersken
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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

CREATE DATADBASE IF NOT EXISTS programming_languages;

USE programming_languages;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `language_id` int(11) NOT NULL AUTO_INCREMENT,
  `language_name` varchar(30) NOT NULL,
  `language_architecture` enum('imperative','oop','other') NOT NULL,
  `language_implementation` enum('compiler','interpreter','VM','mixed') NOT NULL,
  `language_system` set('Unix','Windows','other') NOT NULL,
  `language_description` varchar(255) NOT NULL,
  `language_year` int(11) NOT NULL,
  PRIMARY KEY (`language_id`),
  KEY `language_name` (`language_name`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (1,'C','imperative','compiler','Unix,Windows,other','Klassische Programmiersprache, die zur Neuimplementierung von UNIX entwickelt wurde. Ihre Syntax inspirierte viele andere Sprachen.',1970),(2,'C++','oop','compiler','Unix,Windows,other','Weiterentwicklung von C mit OOP-Fähigkeiten',1983),(3,'Python','oop','interpreter','Unix,Windows,other','Moderne Multiparadigmen-Skriptsprache mit objektorientierten und funktionalen Aspekten',1990),(4,'Ruby','oop','interpreter','Unix,Windows,other','Skriptsprache mit fast allen Perl-Features sowie sauberer, moderner OOP-Implementierung',1993),(5,'Java','oop','VM','Unix,Windows,other','OOP-Sprache mit Multi-Plattform-VM',1995),(6,'Perl','imperative','interpreter','Unix,Windows,other','Skriptsprache für Admin- und Textbearbeitungsaufgaben; neuere Versionen bieten (umständliche) OOP.',1987),(14,'JavaScript','other','interpreter','Unix,Windows,other','Skriptsprache, die vor allem in Browsern verwendet wird',1995),(23,'Scala','other','VM','Unix,Windows,other','Funktionale Sprache für die Java-VM',2003);
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-25 13:39:16
