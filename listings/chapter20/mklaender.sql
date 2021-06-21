-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: welt
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `laender`
--

DROP TABLE IF EXISTS `laender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `laender` (
  `id` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `hauptstadt` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laender`
--

LOCK TABLES `laender` WRITE;
/*!40000 ALTER TABLE `laender` DISABLE KEYS */;
INSERT INTO `laender` VALUES ('L1','Ägypten','Kairo'),('L10','Armenien','Jerewan'),('L100','Luxemburg','Luxemburg'),('L101','Madagaskar','Antananarivo'),('L102','Malawi','Lilongwe'),('L103','Malaysia','Kuala Lumpur'),('L104','Malediven','Malé'),('L105','Mali','Bamako'),('L106','Malta','Valletta'),('L107','Marokko','Rabat'),('L108','Marshallinseln','Majuro'),('L109','Mauretanien','Nouakchott'),('L11','Aserbaidschan','Baku'),('L110','Mauritius','Port Louis'),('L111','Mazedonien','Skopje'),('L112','Mexiko','Mexiko-Stadt'),('L113','Mikronesien','Palikir'),('L114','Moldawien','Chisinau'),('L115','Monaco','Stadtstaat'),('L116','Mongolei','Ulaanbaatar'),('L117','Montenegro','Podgorica'),('L118','Mosambik','Maputo'),('L119','Myanmar','Naypyidaw'),('L12','Äthiopien','Addis Abeba'),('L120','Namibia','Windhoek'),('L121','Nauru','Yaren'),('L122','Nepal','Kathmandu'),('L123','Neuseeland','Wellington'),('L124','Nicaragua','Managua'),('L125','Niederlande','Amsterdam'),('L126','Niger','Niamey'),('L127','Nigeria','Abuja'),('L128','Niue','Alofi'),('L129','Nordzypern','Lefkosa'),('L13','Australien','Canberra'),('L130','Norwegen','Oslo'),('L131','Oman','Maskat'),('L132','Österreich','Wien'),('L133','Osttimor / Timor-Leste','Dili'),('L134','Pakistan','Islamabad'),('L135','Palästina','Ostjerusalem (Gaza und Ramallah)'),('L136','Palau','Melekeok'),('L137','Panama','Panama-Stadt'),('L138','Papua-Neuguinea','Port Moresby'),('L139','Paraguay','Asunción'),('L14','Bahamas','Nassau'),('L140','Peru','Lima'),('L141','Philippinen','Manila'),('L142','Polen','Warschau'),('L143','Portugal','Lissabon'),('L144','Ruanda','Kigali'),('L145','Rumänien','Bukarest'),('L146','Russland','Moskau'),('L147','Salomonen','Honiara'),('L148','Sambia','Lusaka'),('L149','Samoa','Apia'),('L15','Bahrain','Manama'),('L150','San Marino','San Marino'),('L151','São Tomé und Principe','São Tomé'),('L152','Saudi-Arabien','Riad'),('L153','Schweden','Stockholm'),('L154','Schweiz','Bern'),('L155','Senegal','Dakar'),('L156','Serbien','Belgrad'),('L157','Seychellen','Victoria'),('L158','Sierra Leone','Freetown'),('L159','Simbabwe','Harare'),('L16','Bangladesch','Dhaka'),('L160','Singapur','Stadtstaat'),('L161','Slowakei','Bratislava'),('L162','Slowenien','Ljubljana'),('L163','Somalia','Mogadischu'),('L164','Somaliland','Hargeysa'),('L165','Spanien','Madrid'),('L166','Sri Lanka','Colombo'),('L167','St. Kitts und Nevis','Basseterre'),('L168','St. Lucia','Castries'),('L169','St. Vincent und die Grenadinen','Kingstown'),('L17','Barbados','Bridgetown'),('L170','Südafrika','Pretoria'),('L171','Sudan','Khartum'),('L172','Südossetien','Zchinwali'),('L173','Südsudan','Juba'),('L174','Suriname','Paramaribo'),('L175','Swasiland','Mbabane'),('L176','Syrien','Damaskus'),('L177','Tadschikistan','Duschanbe'),('L178','Tansania','Dodoma'),('L179','Thailand','Bangkok'),('L18','Belgien','Brüssel'),('L180','Togo','Lomé'),('L181','Tonga','Nuku\'alofa'),('L182','Transnistrien','Tiraspol'),('L183','Trinidad und Tobago','Port of Spain'),('L184','Tschad','NÕDjamena'),('L185','Tschechien','Prag'),('L186','Tunesien','Tunis'),('L187','Türkei','Ankara'),('L188','Turkmenistan','Aschgabad'),('L189','Tuvalu','Funafuti'),('L19','Belize','Belmopan'),('L190','Uganda','Kampala'),('L191','Ukraine','Kiew'),('L192','Ungarn','Budapest'),('L193','Uruguay','Montevideo'),('L194','Usbekistan','Taschkent'),('L195','Vanuatu','Port Vila'),('L196','Vatikanstadt','Stadtstaat'),('L197','Venezuela','Caracas'),('L198','Vereinigte Arabische Emirate','Abu Dhabi'),('L199','Vereinigte Staaten','Washington, D.C.'),('L2','Afghanistan','Kabul'),('L20','Benin','Porto Novo'),('L200','Vereinigtes Königreich','London'),('L201','Vietnam','Hanoi'),('L202','Weißrussland','Minsk'),('L203','Westsahara','El Aaiún'),('L204','Zentralafrikanische Republik','Bangui'),('L205','Zypern','Nikosia'),('L21','Bergkarabach','Stepanakert'),('L22','Bhutan','Thimphu'),('L23','Bolivien','Sucre'),('L24','Bosnien und Herzegowina','Sarajevo'),('L25','Botswana','Gaborone'),('L26','Brasilien','Brasília'),('L27','Brunei','Bandar Seri Begawan'),('L28','Bulgarien','Sofia'),('L29','Burkina Faso','Ouagadougou'),('L3','Albanien','Tirana'),('L30','Burundi','Bujumbura'),('L31','Chile','Santiago de Chile'),('L32','Republik China','Taipeh'),('L33','Volksrepublik China','Peking'),('L34','Cookinseln','Avarua'),('L35','Costa Rica','San José'),('L36','Dänemark','Kopenhagen'),('L37','Deutschland','Berlin'),('L38','Dominica','Roseau'),('L39','Dominikanische Republik','Santo Domingo'),('L4','Algerien','Algier'),('L40','Dschibuti','Dschibuti-Stadt'),('L41','Ecuador','Quito'),('L42','El Salvador','San Salvador'),('L43','Elfenbeinküste','Yamoussoukro'),('L44','Eritrea','Asmara'),('L45','Estland','Tallinn'),('L46','Fidschi','Suva'),('L47','Finnland','Helsinki'),('L48','Frankreich','Paris'),('L49','Gabun','Libreville'),('L5','Andorra','Andorra la Vella'),('L50','Gambia','Banjul'),('L51','Georgien','Tiflis'),('L52','Ghana','Accra'),('L53','Grenada','St. GeorgeÕs'),('L54','Griechenland','Athen'),('L55','Guatemala','Guatemala-Stadt'),('L56','Guinea','Conakry'),('L57','Guinea-Bissau','Bissau'),('L58','Guyana','Georgetown'),('L59','Haiti','Port-au-Prince'),('L6','Angola','Luanda'),('L60','Honduras','Tegucigalpa'),('L61','Indien','Neu-Delhi'),('L62','Indonesien','Jakarta'),('L63','Irak','Bagdad'),('L64','Iran','Teheran'),('L65','Irland','Dublin'),('L66','Island','Reykjavik'),('L67','Israel','Jerusalem'),('L68','Italien','Rom'),('L69','Jamaika','Kingston'),('L7','Antigua und Barbuda','Saint John\'s'),('L70','Japan','Tokio'),('L71','Jemen','Sanaa'),('L72','Jordanien','Amman'),('L73','Kambodscha','Phnom Penh'),('L74','Kamerun','Yaoundé'),('L75','Kanada','Ottawa'),('L76','Kap Verde','Praia'),('L77','Kasachstan','Astana'),('L78','Katar','Doha'),('L79','Kenia','Nairobi'),('L8','Äquatorialguinea','Malabo'),('L80','Kirgisistan','Bischkek'),('L81','Kiribati','South Tarawa'),('L82','Kolumbien','Bogotà'),('L83','Komoren','Moroni'),('L84','Kongo, Demokratische Republik','Kinshasa'),('L85','Kongo, Republik','Brazzaville'),('L86','Korea, Nord','Pjöngjang'),('L87','Korea, Süd','Seoul'),('L88','Kosovo','Prishtina'),('L89','Kroatien','Zagreb'),('L9','Argentinien','Buenos Aires'),('L90','Kuba','Havanna'),('L91','Kuwait','Kuwait-Stadt'),('L92','Laos','Vientiane'),('L93','Lesotho','Maseru'),('L94','Lettland','Riga'),('L95','Libanon','Beirut'),('L96','Liberia','Monrovia'),('L97','Libyen','Tripolis'),('L98','Liechtenstein','Vaduz'),('L99','Litauen','Vilnius');
/*!40000 ALTER TABLE `laender` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-23  7:36:26
