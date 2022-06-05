-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: weather
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS city;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE city (
  city_id int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  longitude float DEFAULT NULL,
  latitude float DEFAULT NULL,
  weather_id int DEFAULT NULL,
  base varchar(45) DEFAULT NULL,
  visibility int DEFAULT NULL,
  dt int DEFAULT NULL,
  time_zone int DEFAULT NULL,
  cod int DEFAULT NULL,
  created_date_time datetime NOT NULL,
  updated_date_time datetime DEFAULT NULL,
  id int DEFAULT NULL,
  PRIMARY KEY (city_id),
  UNIQUE KEY UK_ifg8hkcccc02e9nhyke862850 (weather_id),
  KEY FKc3jatjk1md91vr1xxf8ofyx6b (id),
  CONSTRAINT FKc3jatjk1md91vr1xxf8ofyx6b FOREIGN KEY (id) REFERENCES weather (id),
  CONSTRAINT FKffah0d8lnfexw9b0ssltrg8gr FOREIGN KEY (weather_id) REFERENCES weather (id)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

/*!40000 ALTER TABLE city DISABLE KEYS */;
/*!40000 ALTER TABLE city ENABLE KEYS */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS hibernate_sequence;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE hibernate_sequence (
  next_val bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

/*!40000 ALTER TABLE hibernate_sequence DISABLE KEYS */;
INSERT INTO hibernate_sequence VALUES (1);
/*!40000 ALTER TABLE hibernate_sequence ENABLE KEYS */;

--
-- Table structure for table `weather`
--

DROP TABLE IF EXISTS weather;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE weather (
  id int NOT NULL AUTO_INCREMENT,
  main varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  icon varchar(45) DEFAULT NULL,
  created_date_time datetime NOT NULL,
  updated_date_time datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weather`
--

/*!40000 ALTER TABLE weather DISABLE KEYS */;
INSERT INTO weather VALUES (1,'Clouds','few clouds','02d','2022-04-02 00:00:00','2022-04-03 15:37:00');
/*!40000 ALTER TABLE weather ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
