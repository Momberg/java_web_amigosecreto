-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: amigosecreto
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupos` (
  `idgrupo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `local` varchar(45) NOT NULL,
  `data` varchar(45) NOT NULL,
  `num_pessoas` int(11) NOT NULL,
  `cod_grupo` varchar(45) NOT NULL,
  `cpf_usuario` varchar(45) NOT NULL,
  `sorteado` int(11) NOT NULL,
  PRIMARY KEY (`idgrupo`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES (1,'teste','fiap','Thu May 18 00:00:00 BRT 2017',0,'','',0),(2,'teste2','fiap','Thu May 18 00:00:00 BRT 2017',0,'','',0),(3,'28SCJ','FIAP','Wed May 24 00:00:00 BRT 2017',0,'','',0),(4,'28SCJ','FIAP','Thu May 25 00:00:00 BRT 2017',12,'','',0),(5,'Teste','Teste','Thu May 25 00:00:00 BRT 2017',30,'9852','',0),(6,'Teste2','Teste2','Thu May 25 00:00:00 BRT 2017',30,'4340','',0),(7,'Momberg\'s','Casa','Wed May 24 00:00:00 BRT 2017',100,'9409','43081408890',0),(8,'teste','teste','Fri May 12 00:00:00 BRT 2017',12,'15461','43081408890',0),(9,'teste2','teste2','Thu May 25 00:00:00 BRT 2017',200,'20673','43081408890',0),(10,'teste3','teste3','Thu May 25 00:00:00 BRT 2017',300,'79115','43081408890',0),(11,'teste','teste','Mon May 15 00:00:00 BRT 2017',12,'80819','43081408890',0),(12,'teste','teste','Mon May 15 00:00:00 BRT 2017',12,'41810','43081408890',0),(13,'teste','teste','Mon May 15 00:00:00 BRT 2017',12,'7378','43081408890',0),(14,'teste','teste','Mon May 15 00:00:00 BRT 2017',12,'1171','43081408890',0),(15,'teste','teste','Tue May 02 00:00:00 BRT 2017',12,'63675','43081408890',0),(16,'a','a','Wed May 10 00:00:00 BRT 2017',2,'47778','43081408890',0),(17,'grupo do alexandre','fiap','Wed May 24 00:00:00 BRT 2017',4,'68477','40319606899',0);
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoas`
--

DROP TABLE IF EXISTS `pessoas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoas` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CPF` varchar(45) NOT NULL,
  `cod_grupo` varchar(45) NOT NULL,
  `nome_sorteado` varchar(45) DEFAULT NULL,
  `participando` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoas`
--

LOCK TABLES `pessoas` WRITE;
/*!40000 ALTER TABLE `pessoas` DISABLE KEYS */;
INSERT INTO `pessoas` VALUES (1,'43081408890','63675',NULL,0),(2,'43081408890','47778',NULL,0),(3,'40319606899','68477',NULL,0);
/*!40000 ALTER TABLE `pessoas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(45) NOT NULL,
  `SENHA` varchar(20) NOT NULL,
  `NIVEL` int(11) DEFAULT NULL,
  `NOME` varchar(45) DEFAULT NULL,
  `CPF` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'fiap','123',1,NULL,NULL),(2,'scj','123',1,NULL,NULL),(3,'alexandre@gmail.com','66708589',0,'Alexandre Dias de Lima','40319606899'),(4,'ale','4444',0,'ale','222'),(5,'1','1',0,'1','1'),(6,'aaa','aaa',0,'Alexandre','333'),(7,'aa','aa',0,'aa','aa'),(8,'gabrielduartemomberg@gmail.com','1234',0,'Gabriel Duarte Momberg','43081408890');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-15  0:16:04
