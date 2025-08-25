-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: jspndb
-- ------------------------------------------------------
-- Server version	8.4.6

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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `bnum` int NOT NULL AUTO_INCREMENT,
  `btitle` varchar(200) DEFAULT NULL,
  `bcontent` varchar(200) DEFAULT NULL,
  `memberid` varchar(45) DEFAULT NULL,
  `bhit` int DEFAULT NULL,
  `bdate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bnum`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (4,'제목테스트lion','내용테스트lion','lion',3,'2025-08-25 13:49:29'),(5,'[안내] 추석 연휴 배송 일정11','안녕하세요, 추석 연휴 배송 일정 안내드립니다.\r\n      추석 기간에는 배송이 지연될 수 있으니 참고 부탁드립니다11','관리자',6,'2025-08-25 13:49:31'),(6,'제목테스트tiger',NULL,'tiger',7,'2025-08-25 13:49:47'),(7,'제목테스트tiger','내용테스트tiger','tiger',0,'2025-08-25 14:12:14'),(8,'제목테스트tiger',NULL,'tiger',1,'2025-08-25 14:12:15'),(9,'제목테스트tiger','내용테스트tiger','tiger',0,'2025-08-25 14:12:15'),(10,'제목테스트tiger','내용테스트tiger','tiger',0,'2025-08-25 14:12:15'),(11,'11','tiger','tiger',0,'2025-08-25 14:12:15'),(12,'제목테스트tiger','내용테스트tiger','tiger',0,'2025-08-25 14:12:15'),(13,'제목테스트tiger','내용테스트tiger','tiger',0,'2025-08-25 14:12:15'),(14,'제목테스트tiger','내용테스트tiger','tiger',0,'2025-08-25 14:12:16'),(15,'제목테스트tiger','내용테스트tiger','tiger',0,'2025-08-25 14:12:16'),(16,'제목테스트tiger','내용테스트tiger','tiger',0,'2025-08-25 14:12:16'),(17,'제목테스트tiger','내용테스트tiger','tiger',0,'2025-08-25 14:12:16'),(18,'제목테스트tiger','내용테스트tiger','tiger',0,'2025-08-25 14:12:16'),(19,'제목테스트tiger','내용테스트tiger','tiger',0,'2025-08-25 14:12:16'),(20,'제목테스트tiger','내용테스트tiger','tiger',2,'2025-08-25 14:12:17'),(21,'제목테스트tiger','내용테스트tiger','tiger',2,'2025-08-25 14:12:17'),(22,'제목테스트tiger123123123123','123123','tiger',15,'2025-08-25 14:12:17'),(25,'11','11','tiger',1,'2025-08-25 17:00:48'),(26,'11','11','tiger',11,'2025-08-25 17:01:00'),(28,'관리자페이지제목11','내용글테스트11','관리자',3,'2025-08-25 17:01:43');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-25 17:43:57
