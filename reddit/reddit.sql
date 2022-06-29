-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: localhost    Database: reddit
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `r_banned`
--

DROP TABLE IF EXISTS `r_banned`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `r_banned` (
  `id` int NOT NULL AUTO_INCREMENT,
  `timestamp` datetime DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `community_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_banned_user1_idx` (`user_id`),
  KEY `fk_banned_community1_idx` (`community_id`),
  CONSTRAINT `fk_banned_community1` FOREIGN KEY (`community_id`) REFERENCES `r_community` (`id`),
  CONSTRAINT `fk_banned_user1` FOREIGN KEY (`user_id`) REFERENCES `r_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_banned`
--

LOCK TABLES `r_banned` WRITE;
/*!40000 ALTER TABLE `r_banned` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_banned` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_comment`
--

DROP TABLE IF EXISTS `r_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `r_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(1000) DEFAULT NULL,
  `timestamp` date DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  `comment_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_post1_idx` (`post_id`),
  KEY `fk_comment_user1_idx` (`user_id`),
  KEY `fk_r_comment_2_idx` (`comment_id`),
  CONSTRAINT `fk_r_comment_1` FOREIGN KEY (`user_id`) REFERENCES `r_user` (`id`),
  CONSTRAINT `fk_r_comment_2` FOREIGN KEY (`comment_id`) REFERENCES `r_comment` (`id`),
  CONSTRAINT `fk_r_comment_3` FOREIGN KEY (`post_id`) REFERENCES `r_post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_comment`
--

LOCK TABLES `r_comment` WRITE;
/*!40000 ALTER TABLE `r_comment` DISABLE KEYS */;
INSERT INTO `r_comment` VALUES (1,'Any recomenndation for detailing in Novi Sad?','2022-05-19',1,1,0,NULL),(2,'CarWash near city center is the best','2022-05-19',NULL,2,0,1),(3,'Is there any advice how to polish car properly?','2022-05-19',1,2,0,NULL),(4,'It\'s easier to pay for the service mate','2022-05-19',NULL,3,0,3),(5,'Don\'t try that if you\'re not experienced!!!','2022-05-19',NULL,1,0,3),(6,'Who repairs leather?','2022-06-10',1,1,0,NULL),(8,'I have a bad experience there','2022-06-10',NULL,1,0,1),(9,'I love fruits','2022-06-10',2,1,0,NULL),(10,'apple is the best one','2022-06-10',NULL,1,0,9);
/*!40000 ALTER TABLE `r_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_community`
--

DROP TABLE IF EXISTS `r_community`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `r_community` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(100) DEFAULT NULL,
  `user_id` int NOT NULL,
  `creation_date` date DEFAULT NULL,
  `is_suspended` tinyint NOT NULL,
  `suspended_reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_community_user_idx` (`user_id`),
  CONSTRAINT `fk_community_user` FOREIGN KEY (`user_id`) REFERENCES `r_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_community`
--

LOCK TABLES `r_community` WRITE;
/*!40000 ALTER TABLE `r_community` DISABLE KEYS */;
INSERT INTO `r_community` VALUES (1,'cars',1,'2022-05-10',0,NULL),(2,'fashion',1,'2022-06-10',0,NULL),(3,'cooking',1,'2022-06-10',0,NULL),(7,'lifestyle',1,'2022-06-10',0,NULL);
/*!40000 ALTER TABLE `r_community` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_flair`
--

DROP TABLE IF EXISTS `r_flair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `r_flair` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `post_id` int DEFAULT NULL,
  `community_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_flair_post1_idx` (`post_id`),
  KEY `fk_flair_community1_idx` (`community_id`),
  CONSTRAINT `fk_flair_community1` FOREIGN KEY (`community_id`) REFERENCES `r_community` (`id`),
  CONSTRAINT `fk_flair_post1` FOREIGN KEY (`post_id`) REFERENCES `r_post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_flair`
--

LOCK TABLES `r_flair` WRITE;
/*!40000 ALTER TABLE `r_flair` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_flair` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_post`
--

DROP TABLE IF EXISTS `r_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `r_post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) DEFAULT NULL,
  `text` varchar(1000) DEFAULT NULL,
  `community_id` int NOT NULL,
  `user_id` int NOT NULL,
  `creation_date` date DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_post_community1_idx` (`community_id`),
  KEY `fk_post_user1_idx` (`user_id`),
  CONSTRAINT `fk_post_community1` FOREIGN KEY (`community_id`) REFERENCES `r_community` (`id`),
  CONSTRAINT `fk_post_user1` FOREIGN KEY (`user_id`) REFERENCES `r_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_post`
--

LOCK TABLES `r_post` WRITE;
/*!40000 ALTER TABLE `r_post` DISABLE KEYS */;
INSERT INTO `r_post` VALUES (1,'Title','detailing',1,1,'2022-05-13','https://www.nicepng.com/png/detail/431-4311452_car-detailing-car-cleaning-car-wash-services-car.png'),(2,'Title','summer food',3,1,NULL,'https://www.eatthis.com/wp-content/uploads/sites/4/2020/08/watermelon-pieces.jpg?quality=82&strip=1'),(3,'Title','barbeque',3,1,NULL,'https://www.licious.in/blog/wp-content/uploads/2020/12/Chicken-Barbeque-Kebab.jpg');
/*!40000 ALTER TABLE `r_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_reaction`
--

DROP TABLE IF EXISTS `r_reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `r_reaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `timestamp` date DEFAULT NULL,
  `type` enum('UP','DOWN') DEFAULT NULL,
  `comment_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reaction_comment1_idx` (`comment_id`),
  KEY `fk_reaction_post1_idx` (`post_id`),
  KEY `fk_r_reaction_1_idx` (`user_id`),
  CONSTRAINT `fk_r_reaction_1` FOREIGN KEY (`user_id`) REFERENCES `r_user` (`id`),
  CONSTRAINT `fk_reaction_comment1` FOREIGN KEY (`comment_id`) REFERENCES `r_comment` (`id`),
  CONSTRAINT `fk_reaction_post1` FOREIGN KEY (`post_id`) REFERENCES `r_post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_reaction`
--

LOCK TABLES `r_reaction` WRITE;
/*!40000 ALTER TABLE `r_reaction` DISABLE KEYS */;
INSERT INTO `r_reaction` VALUES (1,NULL,'UP',NULL,1,1);
/*!40000 ALTER TABLE `r_reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_report`
--

DROP TABLE IF EXISTS `r_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `r_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reason` enum('BREAKES_RULES','HARASSMENT','HATE','SHARING_PERSONAL_INFORMATION','IMPERSONATION','COPYRIGHT_VIOLATION','TRADEMARK_VIOLATION','SPAM','SELF_HARM_OR_SUICIDE','OTHER') DEFAULT NULL,
  `timestamp` date DEFAULT NULL,
  `accepted` tinyint DEFAULT NULL,
  `comment_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_report_comment1_idx` (`comment_id`),
  KEY `fk_report_post1_idx` (`post_id`),
  CONSTRAINT `fk_report_comment1` FOREIGN KEY (`comment_id`) REFERENCES `r_comment` (`id`),
  CONSTRAINT `fk_report_post1` FOREIGN KEY (`post_id`) REFERENCES `r_post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_report`
--

LOCK TABLES `r_report` WRITE;
/*!40000 ALTER TABLE `r_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_user`
--

DROP TABLE IF EXISTS `r_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `r_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `avatar` varchar(1000) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `role` enum('USER','ADMIN') DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_user`
--

LOCK TABLES `r_user` WRITE;
/*!40000 ALTER TABLE `r_user` DISABLE KEYS */;
INSERT INTO `r_user` VALUES (1,'desc','$2a$10$Xdgjn9zFz.SwKbBzGaUTYuZvwDUPz7hNwcg233ERKm6/gStVNw0pu','https://gravatar.com/avatar/f1a9e42ce3ed47bd89db3c839da87366?s=200&d=robohash&r=x','desc','USER','CarDealer','2022-06-09'),(2,'oto','$2a$10$Xdgjn9zFz.SwKbBzGaUTYuZvwDUPz7hNwcg233ERKm6/gStVNw0pu','https://gravatar.com/avatar/8e72d5312849343a0a27227052fc5cba?s=200&d=robohash&r=x','desc','USER','CoffeLover','2022-06-09'),(3,'simba','$2a$10$Xdgjn9zFz.SwKbBzGaUTYuZvwDUPz7hNwcg233ERKm6/gStVNw0pu','https://gravatar.com/avatar/06813dbc2e60b78fa6f5d511b901a553?s=200&d=robohash&r=x','desc','USER','MakeupArtist','2022-06-09'),(4,'admin','$2a$10$8gtoPT3gzgOdewg9mDHfYOi8Ybye.c/aefqqtfc7bl/KVZ.aoEMZm','https://gravatar.com/avatar/62141fab75fd285d637ce612f6372c67?s=200&d=robohash&r=x','admin','USER','admin','2022-06-10');
/*!40000 ALTER TABLE `r_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-10 15:21:34
