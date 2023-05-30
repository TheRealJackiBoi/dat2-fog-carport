CREATE DATABASE  IF NOT EXISTS `cudia_dk_db` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cudia_dk_db`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: mysql24.unoeuro.com    Database: cudia_dk_db
-- ------------------------------------------------------
-- Server version	8.0.32-24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `Parts`
--

DROP TABLE IF EXISTS `Parts`;
/*!50001 DROP VIEW IF EXISTS `Parts`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `Parts` AS SELECT 
 1 AS `order_id`,
 1 AS `use_description`,
 1 AS `quantity`,
 1 AS `price`,
 1 AS `variant_id`,
 1 AS `length`,
 1 AS `material_id`,
 1 AS `dimensions`,
 1 AS `unit`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `item_list`
--

DROP TABLE IF EXISTS `item_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_list` (
  `item_list_id` int NOT NULL AUTO_INCREMENT,
  `use_description` varchar(45) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`item_list_id`),
  UNIQUE KEY `item_list_id_UNIQUE` (`item_list_id`),
  KEY `fk_item_list_orders1_idx` (`order_id`),
  CONSTRAINT `fk_item_list_orders1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_list`
--

LOCK TABLES `item_list` WRITE;
/*!40000 ALTER TABLE `item_list` DISABLE KEYS */;
INSERT INTO `item_list` VALUES (1,'Stolper nedgraves 90 cm. i jord',384,161.82000000000002,42),(2,'Stolper nedgraves 90 cm. i jord',384,161.82000000000002,43),(3,'Stolper nedgraves 90 cm. i jord',384,161.82000000000002,44),(4,'Spær, monteres på rem',300,89.784,44),(5,'Remme i sider, sadles ned i stolper',2,75.384,44),(6,'tagplader monteres på spær',23184,199,44),(7,'Stolper nedgraves 90 cm. i jord',2,161.82000000000002,45),(8,'Spær, monteres på rem',3,89.784,45),(9,'Remme i sider, sadles ned i stolper',2,75.384,45),(10,'tagplader monteres på spær',3,199,45),(11,'Stolper nedgraves 90 cm. i jord',2,32364.000000000004,46),(12,'Spær, monteres på rem',3,26935.199999999997,46),(13,'Remme i sider, sadles ned i stolper',2,15076.800000000001,46),(14,'tagplader monteres på spær',3,597,46),(15,'Stolper nedgraves 90 cm. i jord',2,323.64000000000004,47),(16,'Spær, monteres på rem',3,269.35200000000003,47),(17,'Remme i sider, sadles ned i stolper',2,150.768,47),(18,'tagplader monteres på spær',3,597,47),(19,'Stolper nedgraves 90 cm. i jord',6,970.9200000000001,48),(20,'Spær, monteres på rem',5,448.92,48),(21,'Stolper nedgraves 90 cm. i jord',6,970.9200000000001,49),(22,'Spær, monteres på rem',5,448.92,49),(23,'Remme i sider, sadles ned i stolper',2,150.768,49),(24,'tagplader monteres på spær',8,1592,49),(25,'Stolper nedgraves 90 cm. i jord',6,970.9200000000001,50),(26,'Spær, monteres på rem',5,598.5600000000001,50),(27,'Remme i sider, sadles ned i stolper',2,150.768,50),(28,'tagplader monteres på spær',6,1194,50),(29,'Stolper nedgraves 90 cm. i jord',6,970.9200000000001,51),(30,'Spær, monteres på rem',5,598.5600000000001,51),(31,'Remme i sider, sadles ned i stolper',2,150.768,51),(32,'tagplader monteres på spær',8,1592,51),(33,'Stolper nedgraves 90 cm. i jord',4,647.2800000000001,52),(34,'Spær, monteres på rem',3,359.136,52),(35,'Remme i sider, sadles ned i stolper',2,150.768,52),(36,'tagplader monteres på spær',3,597,52),(37,'Stolper nedgraves 90 cm. i jord',4,647.2800000000001,53),(38,'Spær, monteres på rem',3,359.136,53),(39,'Remme i sider, sadles ned i stolper',2,150.768,53),(40,'tagplader monteres på spær',3,597,53),(41,'Stolper nedgraves 90 cm. i jord',6,970.9200000000001,54),(42,'Spær, monteres på rem',5,598.5600000000001,54),(43,'Remme i sider, sadles ned i stolper',2,150.768,54),(44,'tagplader monteres på spær',6,1194,54),(45,'Stolper nedgraves 90 cm. i jord',6,1132.7400000000002,55),(46,'Spær, monteres på rem',5,598.5600000000001,55),(47,'Remme i sider, sadles ned i stolper',2,226.15200000000002,55),(48,'tagplader monteres på spær',8,1592,55),(49,'Stolper nedgraves 90 cm. i jord',6,1132.7400000000002,56),(50,'Spær, monteres på rem',7,837.984,56),(51,'Remme i sider, sadles ned i stolper',2,201.024,56),(52,'tagplader monteres på spær',8,1592,56),(53,'Stolper nedgraves 90 cm. i jord',8,1510.3200000000002,57),(54,'tagplader monteres på spær',18,3582,57),(55,'Stolper nedgraves 90 cm. i jord',8,1510.3200000000002,58),(56,'Stolper nedgraves 90 cm. i jord',8,1510.3200000000002,59),(57,'Stolper nedgraves 90 cm. i jord',8,1510.3200000000002,60),(58,'Stolper nedgraves 90 cm. i jord',8,1510.3200000000002,61),(59,'Remme i sider, sadles ned i stolper',4,452.30400000000003,61),(60,'Remme i sider, sadles ned i stolper',4,452.30400000000003,61),(61,'Remme i sider, sadles ned i stolper',4,452.30400000000003,61),(62,'tagplader monteres på spær',18,3582,61),(63,'Stolper nedgraves 90 cm. i jord',8,1510.3200000000002,62),(64,'Remme i sider, sadles ned i stolper',4,452.30400000000003,62),(65,'tagplader monteres på spær',18,3582,62),(66,'Stolper nedgraves 90 cm. i jord',8,1510.3200000000002,63),(67,'Remme i sider, sadles ned i stolper',4,452.30400000000003,63),(68,'Spær, monteres på rem',28,3166.128,63),(69,'tagplader monteres på spær',18,3582,63),(70,'Stolper nedgraves 90 cm. i jord',8,1510.3200000000002,64),(71,'Remme i sider, sadles ned i stolper',4,452.30400000000003,64),(72,'Spær, monteres på rem',28,3166.128,64),(73,'tagplader monteres på spær',18,3582,64),(74,'Stolper nedgraves 90 cm. i jord',8,1510.3200000000002,65),(75,'Remme i sider, sadles ned i stolper',4,452.30400000000003,65),(76,'Spær, monteres på rem',28,3166.128,65),(77,'tagplader monteres på spær',18,3582,65),(78,'Stolper nedgraves 90 cm. i jord',8,1510.3200000000002,67),(79,'Remme i sider, sadles ned i stolper',4,452.30400000000003,67),(80,'Spær, monteres på rem',14,2094.96,67),(81,'tagplader monteres på spær',18,3582,67),(82,'Stolper nedgraves 90 cm. i jord',8,1510.3200000000002,68),(83,'Remme i sider, sadles ned i stolper',4,402.048,68),(84,'Spær, monteres på rem',14,2094.96,68),(85,'tagplader monteres på spær',18,3582,68),(86,'Stolper nedgraves 90 cm. i jord',6,1132.7400000000002,69),(87,'Remme i sider, sadles ned i stolper',2,201.024,69),(88,'Spær, monteres på rem',8,957.696,69),(89,'tagplader monteres på spær',8,1592,69),(90,'Stolper nedgraves 90 cm. i jord',6,1132.7400000000002,70),(91,'Remme i sider, sadles ned i stolper',2,201.024,70),(92,'Spær, monteres på rem',8,718.272,70),(93,'tagplader monteres på spær',6,1194,70),(94,'Stolper nedgraves 90 cm. i jord',6,1132.7400000000002,71),(95,'Remme i sider, sadles ned i stolper',2,201.024,71),(96,'Spær, monteres på rem',8,957.696,71),(97,'tagplader monteres på spær',8,1592,71),(98,'Stolper nedgraves 90 cm. i jord',6,1132.7400000000002,72),(99,'Remme i sider, sadles ned i stolper',2,201.024,72),(100,'Spær, monteres på rem',8,957.696,72),(101,'tagplader monteres på spær',8,1592,72),(102,'Stolper nedgraves 90 cm. i jord',6,1132.7400000000002,73),(103,'Remme i sider, sadles ned i stolper',2,201.024,73),(104,'Spær, monteres på rem',8,957.696,73),(105,'tagplader monteres på spær',8,1592,73),(106,'Stolper nedgraves 90 cm. i jord',6,1132.7400000000002,74),(107,'Remme i sider, sadles ned i stolper',2,201.024,74),(108,'Spær, monteres på rem',8,957.696,74),(109,'tagplader monteres på spær',8,1592,74);
/*!40000 ALTER TABLE `item_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `link_item_list_material_variants`
--

DROP TABLE IF EXISTS `link_item_list_material_variants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_item_list_material_variants` (
  `id` int NOT NULL AUTO_INCREMENT,
  `item_list_id` int NOT NULL,
  `material_variants_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_link_item_list_material_variants_item_list1_idx` (`item_list_id`),
  KEY `fk_link_item_list_material_variants_material_variants1_idx` (`material_variants_id`),
  CONSTRAINT `fk_link_item_list_material_variants_item_list1` FOREIGN KEY (`item_list_id`) REFERENCES `item_list` (`item_list_id`),
  CONSTRAINT `fk_link_item_list_material_variants_material_variants1` FOREIGN KEY (`material_variants_id`) REFERENCES `material_variants` (`variant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_item_list_material_variants`
--

LOCK TABLES `link_item_list_material_variants` WRITE;
/*!40000 ALTER TABLE `link_item_list_material_variants` DISABLE KEYS */;
INSERT INTO `link_item_list_material_variants` VALUES (1,2,3),(2,3,3),(3,4,2),(4,5,1),(5,6,4),(6,7,3),(7,8,2),(8,9,1),(9,10,4),(10,11,3),(11,12,2),(12,13,1),(13,14,4),(14,15,3),(15,16,2),(16,17,1),(17,18,4),(18,19,3),(19,20,2),(20,21,3),(21,22,2),(22,23,1),(23,24,4),(24,25,3),(25,26,2),(26,27,1),(27,28,4),(28,29,3),(29,30,2),(30,31,1),(31,32,4),(32,33,3),(33,34,2),(34,35,1),(35,36,4),(36,37,3),(37,38,2),(38,39,1),(39,40,4),(40,41,3),(41,42,2),(42,43,1),(43,44,4),(44,45,6),(45,46,4),(46,47,9),(47,48,8),(48,49,6),(49,50,4),(50,51,2),(51,52,8),(52,53,6),(53,54,8),(54,55,6),(55,56,6),(56,57,6),(57,58,6),(58,59,9),(59,60,9),(60,61,9),(61,62,8),(62,63,6),(63,64,9),(64,65,8),(65,66,6),(66,67,9),(67,68,10),(68,69,8),(69,70,6),(70,71,9),(71,72,10),(72,73,8),(73,74,6),(74,75,9),(75,76,10),(76,77,8),(77,78,6),(78,79,9),(79,80,12),(80,81,8),(81,82,6),(82,83,2),(83,84,12),(84,85,8),(85,86,6),(86,87,2),(87,88,4),(88,89,8),(89,90,6),(90,91,2),(91,92,3),(92,93,8),(93,94,6),(94,95,2),(95,96,4),(96,97,8),(97,98,6),(98,99,2),(99,100,4),(100,101,8),(101,102,6),(102,103,2),(103,104,4),(104,105,8),(105,106,6),(106,107,2),(107,108,4),(108,109,8);
/*!40000 ALTER TABLE `link_item_list_material_variants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_variants`
--

DROP TABLE IF EXISTS `material_variants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_variants` (
  `variant_id` int NOT NULL AUTO_INCREMENT,
  `length` double NOT NULL,
  `quantity` int NOT NULL,
  `material_id` int NOT NULL,
  PRIMARY KEY (`variant_id`),
  UNIQUE KEY `variant_id_UNIQUE` (`variant_id`),
  KEY `fk_material_variants_materials1_idx` (`material_id`),
  CONSTRAINT `fk_material_variants_materials1` FOREIGN KEY (`material_id`) REFERENCES `materials` (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_variants`
--

LOCK TABLES `material_variants` WRITE;
/*!40000 ALTER TABLE `material_variants` DISABLE KEYS */;
INSERT INTO `material_variants` VALUES (1,360,1,1),(2,480,1,1),(3,360,1,2),(4,480,1,2),(5,300,1,3),(6,420,1,3),(7,480,1,3),(8,360,1,4),(9,540,1,1),(10,540,1,2),(11,480,1,3),(12,600,1,2),(13,600,1,1);
/*!40000 ALTER TABLE `material_variants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  `unit` varchar(45) NOT NULL,
  `unit_price` double DEFAULT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`material_id`),
  UNIQUE KEY `material_id_UNIQUE` (`material_id`),
  KEY `fk_materials_units1_idx` (`unit`),
  KEY `fk_materials_type1_idx` (`type`),
  CONSTRAINT `fk_materials_type1` FOREIGN KEY (`type`) REFERENCES `type` (`type`),
  CONSTRAINT `fk_materials_units1` FOREIGN KEY (`unit`) REFERENCES `units` (`unit`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'45X95 MM FYR TRYKIMP','meter',20.94,'Raisingplate'),(2,'45X100 MM SPÆRTRÆ HØVLET','meter',24.94,'Rafts'),(3,'97X97 MM FULDKANTET FYR IMPR','meter',44.95,'Posts'),(4,'109X TRAPEZPLADE(Tag) BLÅTONET','unit',199,'Roofplates');
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `material_cost` double NOT NULL,
  `sales_price` double NOT NULL,
  `c_width` double NOT NULL,
  `c_length` double NOT NULL,
  `c_height` double NOT NULL,
  `user_id` int NOT NULL,
  `status` varchar(45) NOT NULL,
  `s_width` double DEFAULT NULL,
  `s_length` double DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_orders_user1_idx` (`user_id`),
  KEY `fk_orders_status1_idx` (`status`),
  CONSTRAINT `fk_orders_status1` FOREIGN KEY (`status`) REFERENCES `status` (`status`),
  CONSTRAINT `fk_orders_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (5,0,0,4,4,4,1,'Cancelled',0,0),(6,0,0,6,6,3,1,'Creating',0,0),(7,0,0,5,5,3,1,'Order_placed',0,0),(8,0,0,5,5,3,1,'Creating',0,0),(9,0,0,6,7,3,1,'Order_placed',0,0),(10,0,0,6,6,3,1,'Creating',0,0),(11,0,0,6,7,3,1,'Creating',0,0),(12,0,0,8,8,4,1,'Creating',0,0),(13,0,0,4,6,4,1,'Creating',0,0),(14,0,0,7,4.7,4,1,'Cancelled',0,0),(15,0,0,6,7,3,1,'Order_placed',0,0),(16,0,0,3,4,3,1,'Creating',0,0),(17,0,0,5,5,36,1,'Order_placed',0,0),(18,0,0,2,2,5,1,'Cancelled',0,0),(19,0,0,5,6,4,1,'Creating',0,0),(20,0,0,4,5,3,1,'Cancelled',0,0),(21,0,0,5,6,4,1,'Cancelled',0,0),(22,0,0,6,4,3,1,'Order_placed',0,0),(23,0,20222,5,3.5,3,2,'Cancelled',0,0),(24,0,0,4,6,3,1,'Order_placed',0,0),(25,0,0,4,6,3,1,'Order_placed',0,0),(26,0,0,4,5,2,1,'Order_placed',0,0),(27,0,0,4,5,2,1,'Order_placed',0,0),(28,0,0,4,5,2,1,'Order_placed',0,0),(29,0,0,5,6,3,1,'Cancelled',0,0),(30,0,0,3,7,2,1,'Order_placed',0,0),(31,0,0,3,7,2,1,'Order_placed',0,0),(32,0,2000,6,5,3,1,'Order_placed',0,0),(33,0,0,6,5,3,1,'Cancelled',0,0),(34,0,0,5,6,3,1,'Cancelled',0,0),(35,0,0,3,4,3,2,'Order_placed',0,0),(36,0,0,2,2,1,2,'Order_placed',0,0),(37,0,0,5,5,5,2,'Order_placed',0,0),(38,0,0,3,3,2,2,'Order_placed',0,0),(39,0,0,3,3,3,2,'Order_placed',0,0),(40,0,0,3,3,3,2,'Order_placed',0,0),(41,0,0,3,3,3,2,'Order_placed',0,0),(42,0,0,3,3,3,2,'Order_placed',0,0),(43,161.82000000000002,224.9298,3,3,3,2,'Order_placed',0,0),(44,525.988,731.12332,3,3,3,2,'Order_placed',0,0),(45,525.988,731.12332,3,3,3,2,'Order_placed',0,0),(46,74973,10000,3,3,3,2,'Order_placed',0,0),(47,1340.7600000000002,1863.6564,3,3,3,2,'Order_placed',0,0),(48,1419.8400000000001,1973.5776,3,5,3,2,'Order_placed',0,0),(49,3162.608,4396.02512,4,5,3,2,'Order_placed',0,0),(50,2914.248,4050.8047199999996,3,5,3,2,'Order_placed',0,0),(51,3312.248,4604.024719999999,4,5,3,2,'Order_placed',0,0),(52,1754.1840000000002,2438.31576,3,3,3,2,'Accepted',0,0),(53,1754.1840000000002,2438.31576,3,3,3,2,'Accepted',0,0),(54,2914.248,4050.8047199999996,3,5,3,2,'Order_placed',0,0),(55,3549.452,5000,4,5,3,2,'Order_placed',0,0),(56,3763.748,5231.6097199999995,4,4,3,2,'Order_placed',0,0),(57,5092.32,7078.324799999999,6,7.8,3,2,'Order_placed',0,0),(58,0,0,6,7.8,3,2,'Order_placed',0,0),(59,0,0,6,7.8,3,2,'Order_placed',0,0),(60,0,0,6,7.8,3,2,'Order_placed',0,0),(61,6449.232,8964.43248,6,7.8,3,2,'Order_placed',0,0),(62,5544.624,7707.027359999999,6,7.8,3,2,'Order_placed',0,0),(63,8710.752,12107.94528,6,7.8,3,2,'Order_placed',0,0),(64,8710.752,12107.94528,6.1,7.8,3,2,'Order_placed',0,0),(65,8710.752,12107.94528,6.1,7.8,3,2,'Order_placed',0,0),(66,0,0,6,6,3,2,'Creating',0,0),(67,7639.584000000001,10619.02176,6,7.8,3,2,'Order_placed',0,0),(68,7589.328,10549.16592,6,7.8,3,2,'Order_placed',0,0),(69,3883.46,10000,4,4,3,1,'Pending',0,0),(70,3246.036,5411.142012,3,4,3,9,'Creating',0,0),(71,3883.46,6473.72782,4,4,3,1,'Order_placed',0,0),(72,3883.46,6473.72782,4,4,3,1,'Order_placed',0,0),(73,3883.46,6473.72782,4,4,3,1,'Order_placed',0,0),(74,3883.46,6473.72782,4,4,3,1,'Order_placed',0,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`role`),
  UNIQUE KEY `role_UNIQUE` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('admin'),('customer'),('salesman');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`status`),
  UNIQUE KEY `status_UNIQUE` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES ('Accepted'),('Cancelled'),('Creating'),('Order_placed'),('Pending');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES ('Posts'),('Rafts'),('Raisingplate'),('Roofplates');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `units`
--

DROP TABLE IF EXISTS `units`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `units` (
  `unit` varchar(45) NOT NULL,
  PRIMARY KEY (`unit`),
  UNIQUE KEY `unit_UNIQUE` (`unit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `units`
--

LOCK TABLES `units` WRITE;
/*!40000 ALTER TABLE `units` DISABLE KEYS */;
INSERT INTO `units` VALUES ('meter'),('unit');
/*!40000 ALTER TABLE `units` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `zip` int NOT NULL,
  `city` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_user_role_idx` (`role`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role`) REFERENCES `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'jack@oulund.dk','1234','Jack',2605,'BrÃ¸ndby','BrÃ¸ndbyÃ¸ster Torv 45','admin'),(2,'a@a.dk','1234','Julius',2900,'Charlottenlund','HosdinMoar 43','admin'),(3,'b@b.dk','1234','Bjarkfar',4760,'Vordingborg','Hosjulles Mutter','salesman'),(4,'w@w.dk','1234','William',2950,'HÃ¸rsholm','Bjarkfarsmorsadresse','customer'),(6,'n@n.dk','1234','William',2950,'HÃ¸rsholm','Bjarkfarsmorsadresse','customer'),(7,'s@s.dk','1234','William',2950,'HÃ¸rsholm','Bjarkfarsmorsadresse','customer'),(8,'f@f.dk','1234','William',2950,'HÃ¸rsholm','Bjarkfarsmorsadresse','customer'),(9,'k@k.dk','123','Jon',9999,'bjarkby','Ã¸stergade','customer');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `Parts`
--

/*!50001 DROP VIEW IF EXISTS `Parts`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`cudia_dk`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Parts` AS select `item_list`.`order_id` AS `order_id`,`item_list`.`use_description` AS `use_description`,`item_list`.`quantity` AS `quantity`,`item_list`.`price` AS `price`,`material_variants`.`variant_id` AS `variant_id`,`material_variants`.`length` AS `length`,`material_variants`.`material_id` AS `material_id`,`materials`.`description` AS `dimensions`,`materials`.`unit` AS `unit` from (((`item_list` join `link_item_list_material_variants` on((`item_list`.`item_list_id` = `link_item_list_material_variants`.`item_list_id`))) join `material_variants` on((`link_item_list_material_variants`.`material_variants_id` = `material_variants`.`variant_id`))) join `materials` on((`material_variants`.`material_id` = `materials`.`material_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-30 14:42:00
