-- MySQL dump 10.13  Distrib 5.5.31, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: crawler
-- ------------------------------------------------------
-- Server version	5.5.31-0+wheezy1

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
-- Table structure for table `search_result`
--

DROP TABLE IF EXISTS `search_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `search_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `company` varchar(255) NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `direct_url` varchar(255) DEFAULT NULL,
  `expired` tinyint(1) DEFAULT '0',
  `job_key` varchar(255) DEFAULT NULL,
  `job_title` varchar(255) NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sponsored` tinyint(1) DEFAULT '0',
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search_result`
--

LOCK TABLES `search_result` WRITE;
/*!40000 ALTER TABLE `search_result` DISABLE KEYS */;
INSERT INTO `search_result` VALUES (1,'','Argonauta','IE','2013-10-09','http://ie.indeed.com/rc/clk?jk=a615fcac7db86b16',0,'a615fcac7db86b16','Proyecto internacional - Analistas programadores .NET',NULL,NULL,'Argonauta',0,'http://ie.indeed.com/viewjob?jk=a615fcac7db86b16&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(2,'Dublin','Celtrino','IE','2013-10-08','http://ie.indeed.com/rc/clk?jk=b5b742af822d928e',0,'b5b742af822d928e','Programmer',53.332417,-6.247253,'Celtrino',0,'http://ie.indeed.com/viewjob?jk=b5b742af822d928e&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(3,'','lotusworks','IE','2013-09-27','http://ie.indeed.com/rc/clk?jk=c9c5c7eb894d3fb5',0,'c9c5c7eb894d3fb5','Software Developer',NULL,NULL,'lotusworks',0,'http://ie.indeed.com/viewjob?jk=c9c5c7eb894d3fb5&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(4,'Dublin','Wonga','IE','2013-09-24','http://ie.indeed.com/rc/clk?jk=75b8751b6fb7e35b',0,'75b8751b6fb7e35b','Software Development Manager, Dublin',53.332417,-6.247253,'Wonga',0,'http://ie.indeed.com/viewjob?jk=75b8751b6fb7e35b&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(5,'','HP','IE','2013-09-27','http://ie.indeed.com/rc/clk?jk=5d7c7e9e400f057b',0,'5d7c7e9e400f057b','Graduate Software Engineer',NULL,NULL,'HP',0,'http://ie.indeed.com/viewjob?jk=5d7c7e9e400f057b&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(6,'Dublin West','Globoforce Limited','IE','2013-10-07','http://ie.indeed.com/rc/clk?jk=bd66903ba5d3209f',0,'bd66903ba5d3209f','Software Developer – C#, .Net',53.392857,-6.395605,'Globoforce Limited',0,'http://ie.indeed.com/viewjob?jk=bd66903ba5d3209f&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(7,'Dublin','Bloomberg Polar Lake','IE','2013-10-07','http://ie.indeed.com/rc/clk?jk=d8699a3c49254942',0,'d8699a3c49254942','SQL Server Developer',53.332417,-6.247253,'Bloomberg Polar Lake',0,'http://ie.indeed.com/viewjob?jk=d8699a3c49254942&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(8,'','I.T. Alliance','IE','2013-10-02','http://ie.indeed.com/rc/clk?jk=8b1068e03c990d3c',0,'8b1068e03c990d3c','Desk-side Service Representative',NULL,NULL,'I.T. Alliance',0,'http://ie.indeed.com/viewjob?jk=8b1068e03c990d3c&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(9,'Leixlip','HP','IE','2013-09-24','http://ie.indeed.com/rc/clk?jk=c21939b9e58946d4',0,'c21939b9e58946d4','Graduate Software Designer',53.365383,-6.494505,'HP',0,'http://ie.indeed.com/viewjob?jk=c21939b9e58946d4&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(10,'Cork','Zartis','IE','2013-10-06','http://ie.indeed.com/rc/clk?jk=5bc0e0b9426f6142',0,'5bc0e0b9426f6142','Web Developer',51.89835,-8.494506,'Zartis',0,'http://ie.indeed.com/viewjob?jk=5bc0e0b9426f6142&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(11,'Dublin','Ericsson','IE','2013-10-07','http://ie.indeed.com/rc/clk?jk=caf0da7edcdab898',0,'caf0da7edcdab898','Software Development Engineer in Test',53.332417,-6.247253,'Ericsson',0,'http://ie.indeed.com/viewjob?jk=caf0da7edcdab898&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(12,'','HP','IE','2013-10-08','http://ie.indeed.com/rc/clk?jk=22fa5294afd0f303',0,'22fa5294afd0f303','Graduate IT Developer',NULL,NULL,'HP',0,'http://ie.indeed.com/viewjob?jk=22fa5294afd0f303&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(13,'Dublin','Stelfox.Ltd','IE','2013-09-25','http://ie.indeed.com/rc/clk?jk=13566e6b23f6f2bc',0,'13566e6b23f6f2bc','Software Engineering Opportunities',53.332417,-6.247253,'Indeed',0,'http://ie.indeed.com/viewjob?jk=13566e6b23f6f2bc&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(14,'An Caisleán Nua','Sage','IE','2013-10-03','http://ie.indeed.com/rc/clk?jk=001771c8dd7a6568',0,'001771c8dd7a6568','.Net Web Developer',52.447803,-9.060439,'Sage',0,'http://ie.indeed.com/viewjob?jk=001771c8dd7a6568&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(15,'Dublin','Julia Ross Recruitment','IE','2013-09-30','http://ie.indeed.com/rc/clk?jk=646335235e02120e',0,'646335235e02120e','SQL Developer (SAS Experience) – 6 Month Contract',53.332417,-6.247253,'Indeed',0,'http://ie.indeed.com/viewjob?jk=646335235e02120e&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(16,'Dublin','Wonga','IE','2013-10-06','http://ie.indeed.com/rc/clk?jk=283ce1e3e12b61d3',0,'283ce1e3e12b61d3','Project Manager, Dublin',53.332417,-6.247253,'Wonga',0,'http://ie.indeed.com/viewjob?jk=283ce1e3e12b61d3&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(17,'Dublin','Accenture','IE','2013-10-08','http://ie.indeed.com/rc/clk?jk=7d3fa81016014482',0,'7d3fa81016014482','Digital Consultant',53.332417,-6.247253,'Accenture',0,'http://ie.indeed.com/viewjob?jk=7d3fa81016014482&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(18,'Dublin','SAP','IE','2013-10-03','http://ie.indeed.com/rc/clk?jk=2162c8c8335c6f7b',0,'2162c8c8335c6f7b','Quality Specialist for BI Consumer Services Job',53.332417,-6.247253,'SAP',0,'http://ie.indeed.com/viewjob?jk=2162c8c8335c6f7b&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(19,'Galway','GxP Systems','IE','2013-09-27','http://ie.indeed.com/rc/clk?jk=5dca5412d98fc12b',0,'5dca5412d98fc12b','Software Developer (Galway)',53.26923,-9.043956,'GxP Systems',0,'http://ie.indeed.com/viewjob?jk=5dca5412d98fc12b&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(20,'Dublin','Celtrino','IE','2013-09-30','http://ie.indeed.com/rc/clk?jk=f7c16db07a30b3fb',0,'f7c16db07a30b3fb','Business Analyst',53.332417,-6.247253,'Celtrino',0,'http://ie.indeed.com/viewjob?jk=f7c16db07a30b3fb&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(21,'Dublin','Dell','IE','2013-10-08','http://ie.indeed.com/rc/clk?jk=25f3dd95163e95a8',0,'25f3dd95163e95a8','Enterprise Tech Supp Advisor',53.332417,-6.247253,'Dell',0,'http://ie.indeed.com/viewjob?jk=25f3dd95163e95a8&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(22,'Dublin','Yahoo! Inc.','IE','2013-09-26','http://ie.indeed.com/rc/clk?jk=81799ef7a295fdd9',0,'81799ef7a295fdd9','Search Account Specialist',53.332417,-6.247253,'Yahoo! Inc.',0,'http://ie.indeed.com/viewjob?jk=81799ef7a295fdd9&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(23,'Dublin','Deloitte','IE','2013-10-01','http://ie.indeed.com/rc/clk?jk=f6b4146adbaeb2ac',0,'f6b4146adbaeb2ac','Assistant Manager/Manager, Pensions & Investments',53.332417,-6.247253,'Deloitte',0,'http://ie.indeed.com/viewjob?jk=f6b4146adbaeb2ac&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(24,'Dublin','Citrix Systems','IE','2013-10-02','http://ie.indeed.com/rc/clk?jk=4595ea961ad38415',0,'4595ea961ad38415','Escalation Engineer',53.332417,-6.247253,'Citrix Systems',0,'http://ie.indeed.com/viewjob?jk=4595ea961ad38415&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(25,'Dublin','Salesforce','IE','2013-10-04','http://ie.indeed.com/rc/clk?jk=e6ea57c1587699b0',0,'e6ea57c1587699b0','Account Executive',53.332417,-6.247253,'Salesforce',0,'http://ie.indeed.com/viewjob?jk=e6ea57c1587699b0&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRv296dmBbbnoqMZ_XAvS6dSAsHakdi35g_Y1hqC0GD0uk&indpubnum=5004251131744383&atk=186ejtnev1d2h34i'),(26,'','HP','IE','2013-10-02','http://ie.indeed.com/rc/clk?jk=3f55eba8bb29561a',0,'3f55eba8bb29561a','Ireland HP Graduate Programme - IT',NULL,NULL,'HP',0,'http://ie.indeed.com/viewjob?jk=3f55eba8bb29561a&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(27,'Leopardstown','Fónua','IE','2013-09-26','http://ie.indeed.com/rc/clk?jk=442db575df2b320f',0,'442db575df2b320f','IT Developer',53.266483,-6.197802,'Fónua',0,'http://ie.indeed.com/viewjob?jk=442db575df2b320f&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(28,'Dublin','I.T. Alliance','IE','2013-10-09','http://ie.indeed.com/rc/clk?jk=fa4f10fa5af26e99',0,'fa4f10fa5af26e99','SharePoint Specialist/Developer',53.332417,-6.247253,'I.T. Alliance',0,'http://ie.indeed.com/viewjob?jk=fa4f10fa5af26e99&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(29,'Dublin','Marketo','IE','2013-09-26','http://ie.indeed.com/rc/clk?jk=360909153e5bc28a',0,'360909153e5bc28a','Technical Consultant',53.332417,-6.247253,'Marketo',0,'http://ie.indeed.com/viewjob?jk=360909153e5bc28a&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(30,'Dublin','BNY Mellon','IE','2013-09-26','http://ie.indeed.com/rc/clk?jk=ce5f8857a5056950',0,'ce5f8857a5056950','Fund Acounting Supervisor Job',53.332417,-6.247253,'BNY Mellon',0,'http://ie.indeed.com/viewjob?jk=ce5f8857a5056950&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(31,'Dublin','BNY Mellon','IE','2013-10-08','http://ie.indeed.com/rc/clk?jk=f36eec0e58ba217b',0,'f36eec0e58ba217b','Fund Accountant Job',53.332417,-6.247253,'BNY Mellon',0,'http://ie.indeed.com/viewjob?jk=f36eec0e58ba217b&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(32,'Dublin','Salesforce','IE','2013-09-26','http://ie.indeed.com/rc/clk?jk=59b6448976bd423c',0,'59b6448976bd423c','DACH Sales Account Executive',53.332417,-6.247253,'Salesforce',0,'http://ie.indeed.com/viewjob?jk=59b6448976bd423c&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(33,'Dublin','BearingPoint Ireland','IE','2013-09-30','http://ie.indeed.com/rc/clk?jk=f79fc0aedcce1132',0,'f79fc0aedcce1132','Release Engineer',53.332417,-6.247253,'BearingPoint Ireland',0,'http://ie.indeed.com/viewjob?jk=f79fc0aedcce1132&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(34,'','EY','IE','2013-10-09','http://ie.indeed.com/rc/clk?jk=4a7d286f5d8ffb43',0,'4a7d286f5d8ffb43','Junior Tax Advisor- Personal Tax Services Dublin',NULL,NULL,'EY',0,'http://ie.indeed.com/viewjob?jk=4a7d286f5d8ffb43&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(35,'Dublin','Salesforce','IE','2013-10-05','http://ie.indeed.com/rc/clk?jk=8d9a701da8230f83',0,'8d9a701da8230f83','Account Executive, Mid',53.332417,-6.247253,'Salesforce',0,'http://ie.indeed.com/viewjob?jk=8d9a701da8230f83&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(36,'Dublin','Wonga','IE','2013-09-24','http://ie.indeed.com/rc/clk?jk=779bb6a34678af49',0,'779bb6a34678af49','Technical Business Analyst, Dublin',53.332417,-6.247253,'Wonga',0,'http://ie.indeed.com/viewjob?jk=779bb6a34678af49&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(37,'Dublin','Salesforce','IE','2013-10-05','http://ie.indeed.com/rc/clk?jk=22f96a0414c09efe',0,'22f96a0414c09efe','Account Executive, General Business',53.332417,-6.247253,'Salesforce',0,'http://ie.indeed.com/viewjob?jk=22f96a0414c09efe&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(38,'Dublin','Salesforce','IE','2013-10-05','http://ie.indeed.com/rc/clk?jk=d9d3ccb89c5d4542',0,'d9d3ccb89c5d4542','Account Executive, General Business, Nordics',53.332417,-6.247253,'Salesforce',0,'http://ie.indeed.com/viewjob?jk=d9d3ccb89c5d4542&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(39,'','Bausch & Lomb','IE','2013-09-25','http://ie.indeed.com/rc/clk?jk=cedaefbc1553a540',0,'cedaefbc1553a540','Senior Engineer, Automation Systems',NULL,NULL,'Bausch & Lomb',0,'http://ie.indeed.com/viewjob?jk=cedaefbc1553a540&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd'),(40,'Dublin','Vero Solutions','IE','2013-10-01','http://ie.indeed.com/rc/clk?jk=3ad57b984d00c683',0,'3ad57b984d00c683','SQL BI Data Team Lead – Dublin South',53.332417,-6.247253,'Vero Solutions',0,'http://ie.indeed.com/viewjob?jk=3ad57b984d00c683&qd=VPHN0PdrcxncVkYNgwjvIXA9MZ38N4rHj5gAINWb2ZO1xtTS8C1bGAEqcWpFPuRvrmm8QO9n6qNqWIqfusm9-fD7SAukXx2pMXyYM18Uaa0&indpubnum=5004251131744383&atk=186ejvt8d1d2h3cd');
/*!40000 ALTER TABLE `search_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `search_result_email`
--

DROP TABLE IF EXISTS `search_result_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `search_result_email` (
  `ID` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `search_result_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_search_result_email_search_result_id` (`search_result_id`),
  CONSTRAINT `FK_search_result_email_search_result_id` FOREIGN KEY (`search_result_id`) REFERENCES `search_result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search_result_email`
--

LOCK TABLES `search_result_email` WRITE;
/*!40000 ALTER TABLE `search_result_email` DISABLE KEYS */;
INSERT INTO `search_result_email` VALUES (1,'jobs@lotusworks.com',3),(4,'info@globoforce.com',6),(5,'customerservice@globoforce.com',6),(6,'jenieve.brennan@italliancegroup.com',8),(8,'recruitment@gxpsystems.com',19),(10,'applicant_access@salesforce.com',25),(12,'Keith.McClelland@italliancegroup.com',28),(14,'applicant_access@salesforce.com',32),(15,'applicant_access@salesforce.com',35),(16,'applicant_access@salesforce.com',37),(17,'applicant_access@salesforce.com',38),(18,'info@vero.ie',40),(19,'Krystle.higgins@vero.ie',40),(20,'sales@vero.ie',40);
/*!40000 ALTER TABLE `search_result_email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `search_result_phone`
--

DROP TABLE IF EXISTS `search_result_phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `search_result_phone` (
  `ID` bigint(20) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `search_result_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_search_result_phone_search_result_id` (`search_result_id`),
  CONSTRAINT `FK_search_result_phone_search_result_id` FOREIGN KEY (`search_result_id`) REFERENCES `search_result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search_result_phone`
--

LOCK TABLES `search_result_phone` WRITE;
/*!40000 ALTER TABLE `search_result_phone` DISABLE KEYS */;
INSERT INTO `search_result_phone` VALUES (2,'+353 (0)71 9169895',3),(3,'+353 (0)71 9169783',3),(7,'+44 771 311 0363',8),(9,'+353 21 4215 051',19),(11,'+353 (0)1 2149223',27),(13,'+353 1 869 0230',28),(21,'+353 1 9017818',40);
/*!40000 ALTER TABLE `search_result_phone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-09 14:50:55
