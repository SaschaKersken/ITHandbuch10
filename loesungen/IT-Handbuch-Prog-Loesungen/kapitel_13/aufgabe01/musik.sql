-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: musik
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

--
-- Table structure for table `alben`
--

DROP TABLE IF EXISTS `alben`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alben` (
  `album_id` int(11) NOT NULL AUTO_INCREMENT,
  `album_titel` varchar(100) DEFAULT NULL,
  `album_jahr` int(11) DEFAULT NULL,
  `album_interpret` int(11) DEFAULT NULL,
  PRIMARY KEY (`album_id`),
  KEY `album_interpret` (`album_interpret`),
  KEY `album_titel` (`album_titel`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alben`
--

LOCK TABLES `alben` WRITE;
/*!40000 ALTER TABLE `alben` DISABLE KEYS */;
INSERT INTO `alben` VALUES (1,'The Human Equation',2004,1),(2,'Metropolis Pt. 2: Scenes From A Memory',1999,2),(3,'The Book of Souls',2015,3);
/*!40000 ALTER TABLE `alben` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interpreten`
--

DROP TABLE IF EXISTS `interpreten`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interpreten` (
  `interpret_id` int(11) NOT NULL AUTO_INCREMENT,
  `interpret_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`interpret_id`),
  KEY `interpret_name` (`interpret_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interpreten`
--

LOCK TABLES `interpreten` WRITE;
/*!40000 ALTER TABLE `interpreten` DISABLE KEYS */;
INSERT INTO `interpreten` VALUES (1,'Ayreon'),(2,'Dream Theater'),(3,'Iron Maiden');
/*!40000 ALTER TABLE `interpreten` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracks`
--

DROP TABLE IF EXISTS `tracks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tracks` (
  `track_id` int(11) NOT NULL AUTO_INCREMENT,
  `track_titel` varchar(100) DEFAULT NULL,
  `track_interpret` int(11) DEFAULT NULL,
  `track_album` int(11) DEFAULT NULL,
  `track_dauer` int(11) DEFAULT NULL,
  PRIMARY KEY (`track_id`),
  KEY `track_interpret` (`track_interpret`),
  KEY `track_album` (`track_album`),
  KEY `track_titel` (`track_titel`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracks`
--

LOCK TABLES `tracks` WRITE;
/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
INSERT INTO `tracks` VALUES (1,'Day One: Vigil',NULL,1,93),(2,'Day Two: Isolation',NULL,1,522),(3,'Day Three: Pain',NULL,1,298),(4,'Day Four: Mystery',NULL,1,337),(5,'Day Five: Voices',NULL,1,429),(6,'Day Six: Childhood',NULL,1,305),(7,'Day Seven: Hope',NULL,1,167),(8,'Day Eight: School',NULL,1,262),(9,'Day Nine: Playground',NULL,1,135),(10,'Day Ten: Memories',NULL,1,237),(11,'Day Eleven: Love',NULL,1,258),(12,'Day Twelve: Trauma',NULL,1,594),(13,'Day Thirteen: Sign',NULL,1,287),(14,'Day Fourteen: Pride',NULL,1,282),(15,'Day Fifteen: Betrayal',NULL,1,324),(16,'Day Sixteen: Loser',NULL,1,286),(17,'Day Seventeen: Accident?',NULL,1,342),(18,'Day Eighteen: Realization',NULL,1,271),(19,'Day Nineteen: Disclosure',NULL,1,282),(20,'Day Twenty: Confrontation',NULL,1,423),(21,'Scene One: Regression',NULL,2,126),(22,'Scene Two: I. Overture 1928',NULL,2,217),(23,'Scene Two: II. Strange Deja Vu',NULL,2,312),(24,'Scene Three: I. Through My Words',NULL,2,62),(25,'Scene Three: II. Fatal Tragedy',NULL,2,409),(26,'Scene Four: Beyond This Life',NULL,2,682),(27,'Scene Five: Through Her Eyes',NULL,2,329),(28,'Scene Six: Home',NULL,2,773),(29,'Scene Seven: I. The Dance of Eternity',NULL,2,373),(30,'Scene Seven: II. One Last Time',NULL,2,226),(31,'Scene Eight: The Spirit Carries On',NULL,2,398),(32,'Scene Nine: Finally Free',NULL,2,719),(33,'If Eternity Should Fail',NULL,3,508),(34,'Speed of Light',NULL,3,301),(35,'The Great Unknown',NULL,3,397),(36,'The Red and the Black',NULL,3,213),(37,'When the River Runs Deep',NULL,3,352),(38,'The Book of Souls',NULL,3,27),(39,'Death or Glory',NULL,3,313),(40,'Shadows of the Valley',NULL,3,452),(41,'Tears of a Clown',NULL,3,299),(42,'The Man of Sorrows',NULL,3,388),(43,'Empire of the Clouds',NULL,3,481);
/*!40000 ALTER TABLE `tracks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-27 13:57:22
