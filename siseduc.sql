CREATE DATABASE IF NOT EXISTS `siseduc`;
USE `siseduc`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: siseduc
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `id_aluno` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `matricula` int(10) unsigned DEFAULT NULL,
  `id_endereco` int(5) unsigned zerofill NOT NULL,
  `nome` varchar(60) NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `foto` varchar(50) DEFAULT NULL,
  `nacionalidade` varchar(35) DEFAULT NULL,
  `data_nascimento` date NOT NULL,
  `naturalidade` varchar(15) DEFAULT NULL,
  `uf_naturalidade` varchar(2) DEFAULT NULL,
  `pratica_ed_fisica` tinyint(1) unsigned NOT NULL,
  `irmao_na_escola` tinyint(1) unsigned DEFAULT NULL,
  `data_cadastro` datetime NOT NULL,
  `data_ultima_atualizacao` date DEFAULT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  `celular` varchar(30) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `is_ativo` tinyint(1) NOT NULL,
  `pai_declarado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_aluno`),
  UNIQUE KEY `matricula` (`matricula`),
  KEY `aluno_FKIndex1` (`id_endereco`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (00001,201700001,00004,'Ariana Leal','Feminino','A42AC5524505DD67CF5208BBFE1A4E46.png','','1990-04-06','','PA',1,0,'2017-01-24 13:18:30','0000-00-00','(  )       -    ','(  )       -    ','',1,1),(00002,201700002,00007,'Ronilde Bessa','Feminino','6DE786C6D9966C91F7856904447CAC9F.png','','1999-01-10','','PA',1,0,'2017-01-27 14:27:48','2017-01-27','(  )       -    ','(  )       -    ','',1,0),(00003,201700003,00009,'Afonso Oliveira','Masculino','BE66089E4C7DC3F78DE009ADEDC05E4F.png','','1990-11-13','','PA',1,0,'2017-01-27 16:44:55','0000-00-00','(  )       -    ','(  )       -    ','',1,1),(00004,201700004,00012,'Antonio Renato','Masculino','51CB0FFE818E8CF0D37B90D5C7BB7594.png','','1999-11-11','','PA',1,0,'2017-01-27 16:45:50','2017-01-27','(  )       -    ','(  )       -    ','',1,0),(00005,201700005,00014,'Adriana Santos','Feminino','D2A40CE17F882EAD4617C9E232DDEEEB.png','','1985-12-12','','PA',1,0,'2017-01-27 16:46:31','2017-01-27','(  )       -    ','(  )       -    ','',1,1),(00006,201700006,00017,'Renata Santos','Feminino','0BC6FAEDF3DBA7E3098298231750656A.png','','2014-12-22','','PA',1,0,'2017-01-27 16:47:14','2017-01-27','(  )       -    ','(  )       -    ','',1,0),(00007,201700007,00019,'Elaine Moreira','Feminino','0229682A7E97C3EA80D049A0F5CD7D9F.png','','1999-10-12','','PA',1,0,'2017-01-27 16:47:54','2017-01-27','(  )       -    ','(  )       -    ','',1,0),(00008,201700008,00021,'Anderson Peixoto','Masculino','39D30DFCAFAD38FE9F020BAC10124E2D.png','','1988-04-02','','PA',1,0,'2017-01-27 16:48:37','2017-01-27','(  )       -    ','(  )       -    ','',1,0),(00009,201700009,00023,'Jose Neto','Masculino','F9410A1518FFD76FF440CCFD8A0D2C79.png','','2015-12-25','','PA',1,0,'2017-01-27 16:49:11','2017-01-27','(  )       -    ','(  )       -    ','',1,1),(00010,201700010,00026,'Frederico Sousa','Masculino','14F5BDE42BDA82E359432B41ED6289EB.png','','2015-12-26','','PA',1,0,'2017-01-27 16:50:00','2017-01-27','(  )       -    ','(  )       -    ','',1,0),(00011,201700011,00028,'Antonio José','Masculino','BF9C35FC74294E5BC6C6737C3DA91B6D.png','','1999-11-15','','PA',1,0,'2017-01-27 16:50:49','2017-01-27','(  )       -    ','(  )       -    ','',1,1);
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno_has_responsavel`
--

DROP TABLE IF EXISTS `aluno_has_responsavel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno_has_responsavel` (
  `id_responsavel` int(5) unsigned zerofill NOT NULL,
  `id_aluno` int(5) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id_responsavel`,`id_aluno`),
  KEY `aluno_has_responsavel_FKIndex1` (`id_aluno`),
  KEY `aluno_has_responsavel_FKIndex2` (`id_responsavel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno_has_responsavel`
--

LOCK TABLES `aluno_has_responsavel` WRITE;
/*!40000 ALTER TABLE `aluno_has_responsavel` DISABLE KEYS */;
INSERT INTO `aluno_has_responsavel` VALUES (00001,00001),(00002,00001),(00003,00002),(00004,00003),(00005,00003),(00006,00004),(00007,00005),(00008,00005),(00009,00006),(00010,00007),(00011,00008),(00012,00009),(00013,00009),(00014,00010),(00015,00011),(00016,00011);
/*!40000 ALTER TABLE `aluno_has_responsavel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno_has_turma`
--

DROP TABLE IF EXISTS `aluno_has_turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno_has_turma` (
  `id_aluno` int(5) unsigned zerofill NOT NULL,
  `id_turma` int(5) unsigned zerofill NOT NULL,
  `repetente` tinyint(1) NOT NULL,
  `ano` char(4) DEFAULT NULL,
  PRIMARY KEY (`id_aluno`,`id_turma`),
  KEY `aluno_has_turma_FKIndex1` (`id_aluno`),
  KEY `aluno_has_turma_FKIndex2` (`id_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno_has_turma`
--

LOCK TABLES `aluno_has_turma` WRITE;
/*!40000 ALTER TABLE `aluno_has_turma` DISABLE KEYS */;
INSERT INTO `aluno_has_turma` VALUES (00001,00001,0,'2017'),(00002,00001,0,'2017'),(00003,00002,0,'2017'),(00004,00002,0,'2017'),(00005,00001,0,'2017'),(00006,00001,0,'2017'),(00007,00001,0,'2017'),(00008,00002,0,'2017'),(00009,00002,0,'2017'),(00010,00002,0,'2017'),(00011,00001,0,'2017');
/*!40000 ALTER TABLE `aluno_has_turma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bem_patrimonial`
--

DROP TABLE IF EXISTS `bem_patrimonial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bem_patrimonial` (
  `id_bem_patrimomial` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_fornecedor` int(5) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id_bem_patrimomial`),
  KEY `bem_patrimomial_FKIndex1` (`id_fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bem_patrimonial`
--

LOCK TABLES `bem_patrimonial` WRITE;
/*!40000 ALTER TABLE `bem_patrimonial` DISABLE KEYS */;
/*!40000 ALTER TABLE `bem_patrimonial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `boletim`
--

DROP TABLE IF EXISTS `boletim`;
/*!50001 DROP VIEW IF EXISTS `boletim`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `boletim` (
  `pai` tinyint NOT NULL,
  `serie` tinyint NOT NULL,
  `media` tinyint NOT NULL,
  `situacao` tinyint NOT NULL,
  `1 avaliação` tinyint NOT NULL,
  `2 avaliação` tinyint NOT NULL,
  `3 avaliação` tinyint NOT NULL,
  `4 avaliação` tinyint NOT NULL,
  `1 Recuperação` tinyint NOT NULL,
  `2 Recuperação` tinyint NOT NULL,
  `data_nascimento` tinyint NOT NULL,
  `disciplina` tinyint NOT NULL,
  `nome_aluno` tinyint NOT NULL,
  `sexo` tinyint NOT NULL,
  `mae` tinyint NOT NULL,
  `cidade` tinyint NOT NULL,
  `uf` tinyint NOT NULL,
  `turma` tinyint NOT NULL,
  `modalidade` tinyint NOT NULL,
  `escola` tinyint NOT NULL,
  `uf_escola` tinyint NOT NULL,
  `cidade_escola` tinyint NOT NULL,
  `matricula` tinyint NOT NULL,
  `ano` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `cargo_funcao`
--

DROP TABLE IF EXISTS `cargo_funcao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo_funcao` (
  `id_cargo_funcao` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `cargo_funcao` varchar(50) DEFAULT NULL,
  `data_cadastro` date DEFAULT NULL,
  `data_modificacao` date DEFAULT NULL,
  `descricao` text,
  `is_ativa` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_cargo_funcao`),
  UNIQUE KEY `cargo` (`cargo_funcao`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo_funcao`
--

LOCK TABLES `cargo_funcao` WRITE;
/*!40000 ALTER TABLE `cargo_funcao` DISABLE KEYS */;
INSERT INTO `cargo_funcao` VALUES (00001,'Auxiliar Administrativo','2017-01-24','2017-01-24','Auxiliar Administrativo',1);
/*!40000 ALTER TABLE `cargo_funcao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disciplina` (
  `id_disciplina` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `disciplina` varchar(75) NOT NULL,
  `data_cadastro` date NOT NULL,
  `data_modificacao` date DEFAULT NULL,
  `descricao` text NOT NULL,
  `is_ativa` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`id_disciplina`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplina`
--

LOCK TABLES `disciplina` WRITE;
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
INSERT INTO `disciplina` VALUES (00001,'Sociologia','2017-01-24','2017-01-24','Sociologia',1),(00002,'Geografia','2017-01-24','2017-01-24','Geografia',1),(00003,'Ensino Religioso','2017-01-24','2017-01-24','Ensino Religioso',1),(00004,'Educação Física','2017-01-24','2017-01-24','Educação Física',1),(00005,'História','2017-01-24','2017-01-24','História',1),(00006,'Língua Portuguesa','2017-01-24','2017-01-24','Língua Portuguesa',1),(00007,'Física','2017-01-24','2017-01-24','Física',1),(00008,'Matemática','2017-01-24','2017-01-24','Matemática',1),(00009,'Língua Estrangeira Moderna','2017-01-24','2017-01-24','Língua Estrangeira Moderna',1),(00010,'Filosofia','2017-01-24','2017-01-24','Filosofia',1),(00011,'Arte','2017-01-24','2017-01-24','Arte',1),(00012,'Química','2017-01-24','2017-01-24','Química',1),(00013,'Biologia','2017-01-24','2017-01-24','Biologia',1);
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `id_endereco` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `endereco` varchar(55) DEFAULT NULL,
  `complemento` varchar(55) DEFAULT NULL,
  `bairro` varchar(55) DEFAULT NULL,
  `cidade` varchar(55) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_endereco`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (00001,'Rua Barão do Rio Branco','Centro','Centro','Castanhal','PA','68745-000'),(00002,'','','','','PA','     -   '),(00003,'','','','','PA','     -   '),(00004,'','','','','PA','     -   '),(00005,'','','','','PA','     -   '),(00006,'','','','','PA','     -   '),(00007,'','','','','PA','     -   '),(00008,'','','','','PA','     -   '),(00009,'','','','','PA','     -   '),(00010,'','','','','PA','     -   '),(00011,'','','','','PA','     -   '),(00012,'','','','','PA','     -   '),(00013,'','','','','PA','     -   '),(00014,'','','','','PA','     -   '),(00015,'','','','','PA','     -   '),(00016,'','','','','PA','     -   '),(00017,'','','','','PA','     -   '),(00018,'','','','','PA','     -   '),(00019,'','','','','PA','     -   '),(00020,'','','','','PA','     -   '),(00021,'','','','','PA','     -   '),(00022,'','','','','PA','     -   '),(00023,'','','','','PA','     -   '),(00024,'','','','','PA','     -   '),(00025,'','','','','PA','     -   '),(00026,'','','','','PA','     -   '),(00027,'','','','','PA','     -   '),(00028,'','','','','PA','     -   '),(00029,'','','','','PA','     -   '),(00030,'','','','','PA','     -   '),(00031,'','','','','PA','     -   '),(00032,'','','','','PA','     -   '),(00033,'','','','','PA','     -   '),(00034,'','','','','PA','     -   '),(00035,'','','','','PA','     -   '),(00036,'','','','','PA','     -   '),(00037,'','','','','PA','     -   '),(00038,'','','','','PA','     -   '),(00039,'','','','','PA','     -   '),(00040,'','','','','PA','     -   '),(00041,'','','','','PA','     -   ');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `escola`
--

DROP TABLE IF EXISTS `escola`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `escola` (
  `id_escola` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `contrato` varchar(45) NOT NULL,
  `id_endereco` int(5) unsigned zerofill NOT NULL,
  `nome` varchar(75) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `telefone` varchar(25) DEFAULT NULL,
  `celular` varchar(25) DEFAULT NULL,
  `email` varchar(75) DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  PRIMARY KEY (`id_escola`,`contrato`),
  UNIQUE KEY `conatrato_UNIQUE` (`contrato`),
  KEY `fk_escola_endereco` (`id_endereco`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `escola`
--

LOCK TABLES `escola` WRITE;
/*!40000 ALTER TABLE `escola` DISABLE KEYS */;
INSERT INTO `escola` VALUES (00001,'D32CB3F4',00001,'E.E.E.F e Médio Cônego Leitão','Pública','(91) 9 8945-8743','(91) 9 8974-5874','conego@conego.com','2017-01-24 12:54:29');
/*!40000 ALTER TABLE `escola` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formacao`
--

DROP TABLE IF EXISTS `formacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formacao` (
  `id_professor` int(5) unsigned zerofill NOT NULL,
  `diploma` varchar(60) NOT NULL,
  `titulo` varchar(25) NOT NULL,
  `curso` varchar(35) NOT NULL,
  `Instituicao` varchar(35) NOT NULL,
  `ano_inicio` year(4) NOT NULL,
  `ano_termino` year(4) NOT NULL,
  PRIMARY KEY (`id_professor`,`diploma`),
  KEY `formacao_FKIndex1` (`id_professor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formacao`
--

LOCK TABLES `formacao` WRITE;
/*!40000 ALTER TABLE `formacao` DISABLE KEYS */;
INSERT INTO `formacao` VALUES (00001,'7829FA16C7F5D40A9AE63629DFB22669.pdf','Graduação','SIstemas de Informação','UFPA',2014,2014),(00002,'06D4E57AB98ED9B75DD38710C0023AF3.pdf','Graduação','Historia','UFPA',2015,2018),(00003,'DDE435A656BAB4509295E4B89C8F0147.pdf','Graduação','Espanhol','UFPA',2000,2014),(00004,'DDE435A656BAB4509295E4B89C8F0147.pdf','Graduação','Pedagogia','UFPA',2014,2015),(00005,'546803A51860DF911D02617A9A79E107.pdf','Graduação','Filosofia','UFPA',2014,2018),(00006,'A2F1EF161157DC26F90F3D545A4E9B7C.pdf','Graduação','Educacao Fisica','UFPA',2002,2006),(00007,'85CDBE24531F1DDB74E9F834D320184F.pdf','Graduação','Quimica','UFPA',2012,2016),(00008,'546803A51860DF911D02617A9A79E107.pdf','Graduação','Fisica','UFPA',2010,2014),(00009,'6B4F738549D261CAEF1581B1596A9B0B.pdf','Graduação','Biologia','UFPA',2014,2018),(00010,'546803A51860DF911D02617A9A79E107.pdf','Graduação','Pedagogia','UFPA',2014,2018),(00011,'546803A51860DF911D02617A9A79E107.pdf','Graduação','Geografia','UFPA',1999,2003),(00011,'FF9856BD3BDFC991DBB37FFD0C18C8A2.pdf','Mestrado','Globalizacao','UFPA',2014,2018);
/*!40000 ALTER TABLE `formacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor` (
  `id_fornecedor` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_endereco` int(5) unsigned zerofill NOT NULL,
  `nome_fantasia` varchar(25) NOT NULL,
  `tipo` varchar(25) NOT NULL,
  `logo` varchar(70) DEFAULT NULL,
  `razao_social` varchar(35) DEFAULT NULL,
  `data_fundacao` date DEFAULT NULL,
  `segmento` varchar(25) NOT NULL,
  `i_e` varchar(15) DEFAULT NULL,
  `cpf_cnpj` varchar(15) NOT NULL,
  `site` varchar(50) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `email` varchar(70) NOT NULL,
  `data_cadastro` date DEFAULT NULL,
  `is_ativo` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`id_fornecedor`),
  KEY `fornecedor_FKIndex1` (`id_endereco`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (00001,00041,'Papelaria','Pessoa Jurídica','7DC83AB4CF0224E67DD0B7272C6F9881.png','Papelaria S/A','1989-11-10','Papelaria','1111111111','1111111111','papelaria.com','(  )       -    ','(  )       -    ','papelaria@papelaria.com','2017-02-03',1);
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frequencia`
--

DROP TABLE IF EXISTS `frequencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frequencia` (
  `id_frequencia` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_turma` int(5) unsigned zerofill NOT NULL,
  `id_disciplina` int(5) unsigned zerofill NOT NULL,
  `id_aluno` int(5) unsigned zerofill NOT NULL,
  `id_professor` int(5) unsigned zerofill NOT NULL,
  `is_presente` tinyint(1) NOT NULL,
  `anotacoes` varchar(100) DEFAULT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`id_frequencia`,`id_turma`),
  KEY `frequencia_FKIndex1` (`id_professor`),
  KEY `frequencia_FKIndex2` (`id_aluno`),
  KEY `frequencia_FKIndex3` (`id_disciplina`),
  KEY `fk_frequencia_turma` (`id_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequencia`
--

LOCK TABLES `frequencia` WRITE;
/*!40000 ALTER TABLE `frequencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `frequencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `id_funcionario` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_cargo_funcao` int(5) unsigned zerofill NOT NULL,
  `id_endereco` int(5) unsigned zerofill NOT NULL,
  `nome` varchar(35) NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `foto` varchar(70) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `telefone` varchar(18) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `nacionalidade` varchar(25) DEFAULT NULL,
  `naturalidade` varchar(20) DEFAULT NULL,
  `uf_naturalidade` varchar(2) DEFAULT NULL,
  `identidade` varchar(10) NOT NULL,
  `cpf` varchar(18) NOT NULL,
  `data_cadastro` date NOT NULL,
  `carga_horaria` int(2) DEFAULT NULL,
  `escolaridade` varchar(40) DEFAULT NULL,
  `numeroCTPS` varchar(25) NOT NULL,
  `serieCTPS` varchar(25) NOT NULL,
  `data_emissao` date NOT NULL,
  `numeroPIS` varchar(30) DEFAULT NULL,
  `dependentes` tinyint(2) unsigned DEFAULT NULL,
  `is_ativo` tinyint(1) unsigned NOT NULL,
  `is_usuario` tinyint(1) unsigned NOT NULL,
  `celular` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id_funcionario`),
  KEY `funcionario_FKIndex1` (`id_endereco`),
  KEY `funcionario_FKIndex2` (`id_cargo_funcao`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (00001,00001,00002,'Sophia Soares','Feminino','C10B0E1477BA5F4D8014D7D62D1174B5.png','','(  )       -    ','2014-12-23','','','PA','     - ','   -   -   .  ','2017-01-24',44,'Ensino Médio Completo','123113','131313','2014-12-23','112',0,1,1,'(  )       -    ');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lembrete`
--

DROP TABLE IF EXISTS `lembrete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lembrete` (
  `id_lembrete` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_usuario` int(5) unsigned zerofill NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `descricao` text NOT NULL,
  `data` datetime NOT NULL,
  `is_ativo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_lembrete`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lembrete`
--

LOCK TABLES `lembrete` WRITE;
/*!40000 ALTER TABLE `lembrete` DISABLE KEYS */;
INSERT INTO `lembrete` VALUES (00001,00001,'Teste atualização','Padrão','','2022-02-19 10:15:00',0),(00002,00001,'Teste1','Padrão','','2018-02-17 10:15:00',1),(00003,00001,'Teste outra atulização','Padrão','','2017-02-08 10:15:00',1),(00004,00000,'Máster','Encontro','','2017-02-09 02:46:00',0),(00005,00001,'Teste3','Padrão','','2018-02-01 10:15:00',0),(00006,00001,'Teste4','Padrão','','2020-02-21 10:15:00',0),(00007,00000,'Mais um teste','Encontro','Mais um teste','2017-02-17 14:01:00',1);
/*!40000 ALTER TABLE `lembrete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_de_consumo`
--

DROP TABLE IF EXISTS `material_de_consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_de_consumo` (
  `id_material_de_consumo` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_fornecedor` int(5) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id_material_de_consumo`),
  KEY `material_de_consumo_FKIndex1` (`id_fornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_de_consumo`
--

LOCK TABLES `material_de_consumo` WRITE;
/*!40000 ALTER TABLE `material_de_consumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_de_consumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota`
--

DROP TABLE IF EXISTS `nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nota` (
  `id_nota` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_turma` int(5) unsigned zerofill NOT NULL,
  `id_disciplina` int(5) unsigned zerofill NOT NULL,
  `id_aluno` int(5) unsigned zerofill NOT NULL,
  `id_professor` int(5) unsigned zerofill NOT NULL,
  `1_avaliacao` varchar(6) DEFAULT '0',
  `2_avaliacao` varchar(6) DEFAULT '0',
  `1_recuperacao` varchar(6) DEFAULT '0',
  `3_avaliacao` varchar(6) DEFAULT '0',
  `4_avaliacao` varchar(6) DEFAULT '0',
  `2_recuperacao` varchar(6) DEFAULT '0',
  `anotacoes` varchar(100) DEFAULT '0',
  PRIMARY KEY (`id_nota`,`id_turma`,`id_disciplina`,`id_aluno`,`id_professor`),
  KEY `nota_FKIndex1` (`id_professor`),
  KEY `nota_FKIndex2` (`id_aluno`),
  KEY `nota_FKIndex3` (`id_disciplina`),
  KEY `fk_nota_turma` (`id_turma`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota`
--

LOCK TABLES `nota` WRITE;
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
INSERT INTO `nota` VALUES (00001,00001,00007,00001,00008,'5','8','8.5','6','7.5','0',''),(00002,00001,00008,00001,00001,'9.5','7.5','0','7','9','0',''),(00003,00002,00007,00008,00001,'5','5.5','0','5.5','9.5','0',''),(00004,00002,00005,00008,00002,'5.2','7','0','5.6','7.3','0',''),(00005,00002,00009,00008,00003,'7.7','9.5','0','7.5','8','0',''),(00006,00002,00013,00008,00009,'7.2','4.4','0','9.5','7.2','0',''),(00007,00002,00008,00008,00001,'4.8','7.2','0','4.3','10','0',''),(00009,00002,00002,00008,00011,'8','7','0','6.5','5','0',''),(00010,00002,00006,00008,00004,'6.5','8','0','5','5','0',''),(00011,00001,00007,00001,00001,'5.5','8','0','6','6','0','');
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor` (
  `id_professor` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_endereco` int(5) unsigned zerofill NOT NULL,
  `nome` varchar(50) NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `nacionalidade` varchar(40) DEFAULT NULL,
  `naturalidade` varchar(30) DEFAULT NULL,
  `uf_naturalidade` varchar(2) DEFAULT NULL,
  `identidade` varchar(10) DEFAULT NULL,
  `cpf` varchar(18) DEFAULT NULL,
  `data_cadastro` date NOT NULL,
  `foto` varchar(70) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `email` varchar(70) DEFAULT NULL,
  `is_ativo` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_professor`),
  KEY `professor_FKIndex1` (`id_endereco`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (00001,00003,'Juan Soares','Masculino','1999-11-10','','','PA','      - ','   -   -   .  ','2017-01-24','341848B9AF5785AD7A1156D32623FC14.png','(  )       -    ','(  )       -    ','',1),(00002,00031,'Adriana Santos','Feminino','2015-02-25','','','PA','      - ','   -   -   .  ','2017-01-29','7DC83AB4CF0224E67DD0B7272C6F9881.png','(  )       -    ','(  )       -    ','',1),(00003,00032,'Frederico Augusto','Masculino','2011-12-15','','','PA','      - ','   -   -   .  ','2017-01-29','7DC83AB4CF0224E67DD0B7272C6F9881.png','(  )       -    ','(  )       -    ','',1),(00004,00033,'Tereza Augusta','Feminino','2002-02-02','','','PA','      - ','   -   -   .  ','2017-01-29','7DC83AB4CF0224E67DD0B7272C6F9881.png','(91) 9 8948-8743','(91) 9 8945-8743','tereza@gmail.com',1),(00005,00034,'Augusto Para','Masculino','1999-10-15','','','PA','      - ','   -   -   .  ','2017-01-29','7DC83AB4CF0224E67DD0B7272C6F9881.png','(  )       -    ','(  )       -    ','',1),(00006,00035,'Maria Antonia','Feminino','1989-10-10','','','PA','      - ','   -   -   .  ','2017-01-29','7DC83AB4CF0224E67DD0B7272C6F9881.png','(  )       -    ','(  )       -    ','',1),(00007,00036,'Ederson Oliveira','Masculino','2005-05-05','','','PA','      - ','   -   -   .  ','2017-01-29','7DC83AB4CF0224E67DD0B7272C6F9881.png','(  )       -    ','(  )       -    ','',1),(00008,00037,'Wilian Cavalvante','Masculino','2011-10-08','','','PA','      - ','   -   -   .  ','2017-01-29','7DC83AB4CF0224E67DD0B7272C6F9881.png','(  )       -    ','(  )       -    ','',1),(00009,00038,'Jordan Altair','Masculino','2000-10-12','','','PA','      - ','   -   -   .  ','2017-01-29','7DC83AB4CF0224E67DD0B7272C6F9881.png','(  )       -    ','(  )       -    ','',1),(00010,00039,'Michel Jordan','Masculino','1999-10-13','','','PA','      - ','   -   -   .  ','2017-01-29','7DC83AB4CF0224E67DD0B7272C6F9881.png','(  )       -    ','(  )       -    ','',1),(00011,00040,'Marcos Pinheiro','Masculino','1975-10-14','','','PA','      - ','   -   -   .  ','2017-01-29','7DC83AB4CF0224E67DD0B7272C6F9881.png','(  )       -    ','(  )       -    ','',1);
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER delete_has_professor
AFTER UPDATE ON professor
FOR EACH ROW
	DELETE professor_has_disciplina, formacao FROM professor_has_disciplina
    LEFT OUTER JOIN professor ON (professor.id_professor = professor_has_disciplina.id_professor)
    INNER JOIN formacao ON (formacao.id_professor = professor.id_professor)
    WHERE professor.id_professor = OLD.id_professor */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `professor_has_disciplina`
--

DROP TABLE IF EXISTS `professor_has_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor_has_disciplina` (
  `id_disciplina` int(5) unsigned zerofill NOT NULL,
  `id_professor` int(5) unsigned zerofill NOT NULL,
  KEY `professor_has_disciplina_FKIndex1` (`id_professor`),
  KEY `professor_has_disciplina_FKIndex2` (`id_disciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor_has_disciplina`
--

LOCK TABLES `professor_has_disciplina` WRITE;
/*!40000 ALTER TABLE `professor_has_disciplina` DISABLE KEYS */;
INSERT INTO `professor_has_disciplina` VALUES (00005,00002),(00010,00005),(00007,00008),(00006,00010),(00011,00010),(00002,00011),(00001,00011),(00009,00003),(00008,00003),(00001,00004),(00003,00004),(00006,00004),(00011,00004),(00004,00006),(00008,00001),(00007,00001),(00012,00007),(00013,00009);
/*!40000 ALTER TABLE `professor_has_disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor_has_turma`
--

DROP TABLE IF EXISTS `professor_has_turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor_has_turma` (
  `id_turma` int(5) unsigned zerofill NOT NULL,
  `id_professor` int(5) unsigned zerofill NOT NULL,
  `id_disciplina` int(5) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id_turma`,`id_professor`,`id_disciplina`),
  KEY `professor_has_turma_FKIndex1` (`id_professor`),
  KEY `professor_has_turma_FKIndex2` (`id_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor_has_turma`
--

LOCK TABLES `professor_has_turma` WRITE;
/*!40000 ALTER TABLE `professor_has_turma` DISABLE KEYS */;
INSERT INTO `professor_has_turma` VALUES (00001,00001,00007),(00001,00001,00008),(00002,00001,00007),(00002,00001,00008),(00001,00002,00005),(00002,00002,00005),(00001,00003,00009),(00002,00003,00009),(00001,00004,00001),(00001,00004,00011),(00002,00004,00003),(00002,00004,00006),(00001,00005,00010),(00001,00006,00004),(00002,00006,00004),(00001,00007,00012),(00001,00009,00013),(00002,00009,00013),(00001,00010,00006),(00002,00010,00011),(00001,00011,00002),(00002,00011,00002);
/*!40000 ALTER TABLE `professor_has_turma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsavel`
--

DROP TABLE IF EXISTS `responsavel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `responsavel` (
  `id_responsavel` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_endereco` int(5) unsigned zerofill NOT NULL,
  `nome` varchar(60) NOT NULL,
  `parentesco` varchar(10) NOT NULL,
  `identidade` varchar(10) DEFAULT NULL,
  `cpf` varchar(18) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `data_cadastro` datetime NOT NULL,
  `mora_com_filho` tinyint(1) unsigned NOT NULL,
  `outro_filho_na_escola` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`id_responsavel`),
  KEY `responsavel_FKIndex1` (`id_endereco`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsavel`
--

LOCK TABLES `responsavel` WRITE;
/*!40000 ALTER TABLE `responsavel` DISABLE KEYS */;
INSERT INTO `responsavel` VALUES (00001,00005,'Maria','Mãe','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-24 13:18:30',1,0),(00002,00006,'Alvaro','Pai','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-24 13:18:30',1,0),(00003,00008,'Maria','Mãe','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 14:27:48',1,0),(00004,00010,'Antonia','Mãe','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:44:55',1,0),(00005,00011,'Antonio','Pai','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:44:55',1,0),(00006,00013,'Fatima','Mãe','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:45:50',1,0),(00007,00015,'Maria','Mãe','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:46:31',1,0),(00008,00016,'José','Pai','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:46:31',1,0),(00009,00018,'Antonia','Mãe','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:47:14',1,0),(00010,00020,'Helena','Mãe','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:47:54',1,0),(00011,00022,'Bianca','Mãe','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:48:37',1,0),(00012,00024,'Tereza','Mãe','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:49:12',1,0),(00013,00025,'Francisco','Pai','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:49:12',1,0),(00014,00027,'Francisca','Mãe','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:50:00',1,0),(00015,00029,'Antonia','Mãe','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:50:49',1,0),(00016,00030,'Antonio','Pai','      - ','   -   -   .  ','(  )       -    ','(  )       -    ','','2017-01-27 16:50:49',1,0);
/*!40000 ALTER TABLE `responsavel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma`
--

DROP TABLE IF EXISTS `turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turma` (
  `id_turma` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `turma` varchar(10) NOT NULL,
  `modalidade` varchar(25) NOT NULL,
  `serie` varchar(5) DEFAULT NULL,
  `descricao_serie` varchar(25) NOT NULL,
  `turno` varchar(15) NOT NULL,
  `descricao_turma` varchar(40) NOT NULL,
  `data_cadastro` date NOT NULL,
  `data_modificacao` date DEFAULT NULL,
  `is_ativa` tinyint(1) unsigned NOT NULL,
  `is_cancelada` tinyint(1) unsigned NOT NULL,
  `data_ativacao` date DEFAULT NULL,
  `data_cancelamento` date DEFAULT NULL,
  PRIMARY KEY (`id_turma`),
  UNIQUE KEY `turma` (`turma`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma`
--

LOCK TABLES `turma` WRITE;
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
INSERT INTO `turma` VALUES (00001,'2017N3M1','Ensino Médio','3','3º Ano | Médio','Noturno','Turma noite ensino médio','2017-01-24','2017-02-04',1,0,'2017-01-24',NULL),(00002,'2017T9F1','Ensino Fundamental','9','9ª Série | Fundamental','Vespertino','','2017-01-27','2017-02-03',1,0,'2017-01-27',NULL);
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `siseduc`.`turma_AFTER_INSERT` AFTER INSERT ON `turma` FOR EACH ROW
BEGIN
	INSERT INTO `turma_has_escola` VALUES (new.id_turma, 1);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `turma_has_escola`
--

DROP TABLE IF EXISTS `turma_has_escola`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turma_has_escola` (
  `id_turma` int(5) unsigned zerofill NOT NULL,
  `id_escola` int(5) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id_turma`,`id_escola`),
  KEY `fk_escola_turma` (`id_escola`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma_has_escola`
--

LOCK TABLES `turma_has_escola` WRITE;
/*!40000 ALTER TABLE `turma_has_escola` DISABLE KEYS */;
INSERT INTO `turma_has_escola` VALUES (00001,00001),(00002,00001);
/*!40000 ALTER TABLE `turma_has_escola` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_funcionario` int(5) unsigned zerofill NOT NULL,
  `usuario` varchar(25) NOT NULL,
  `senha` varchar(20) NOT NULL,
  `is_usuario_master` tinyint(1) unsigned NOT NULL,
  `is_cadastrar_atualizar_aluno` tinyint(1) unsigned DEFAULT NULL,
  `is_cadastrar_atualizar_professor` tinyint(1) unsigned DEFAULT NULL,
  `is_cadastrar_atualizar_funcionario` tinyint(1) unsigned DEFAULT NULL,
  `is_cadastrar_usuario` tinyint(1) unsigned DEFAULT NULL,
  `is_controle_financeiro` tinyint(1) unsigned DEFAULT NULL,
  `is_excluir_aluno` tinyint(1) unsigned DEFAULT NULL,
  `is_excluir_professor` tinyint(1) unsigned DEFAULT NULL,
  `is_excluir_usuario` tinyint(1) unsigned DEFAULT NULL,
  `is_emitir_relatorios` tinyint(1) unsigned DEFAULT NULL,
  `is_cadastrar_alterar_turma` tinyint(1) unsigned DEFAULT NULL,
  `is_cadastrar_alterar_disciplina` tinyint(1) unsigned DEFAULT NULL,
  `is_excluir_turma` tinyint(1) unsigned DEFAULT NULL,
  `is_excluir_disciplina` tinyint(1) unsigned DEFAULT NULL,
  `is_emitir_documentos` tinyint(1) unsigned DEFAULT NULL,
  `is_cadastrar_atualizar_bens_patrimoniais` tinyint(1) unsigned DEFAULT NULL,
  `is_cadastrar_atualizar_fornecedores` tinyint(1) unsigned DEFAULT NULL,
  `is_excluir_bens_patrimoniais` tinyint(1) unsigned DEFAULT NULL,
  `is_excluir_fornecedores` tinyint(1) unsigned DEFAULT NULL,
  `is_excluir_funcionario` tinyint(1) unsigned DEFAULT NULL,
  `is_ativo` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_funcionario`,`usuario`),
  KEY `usuario_FKIndex1` (`id_funcionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (00000,'Máster','2506',1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1),(00001,'fifia','1234',1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'siseduc'
--

--
-- Dumping routines for database 'siseduc'
--
/*!50003 DROP FUNCTION IF EXISTS `count_aluno` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `count_aluno`(turma INT) RETURNS int(11)
BEGIN
	DECLARE count INT DEFAULT 0;
    
    IF (turma) THEN
		SELECT 
			COUNT(DISTINCT `a`.`nome`)
			FROM
				`turma` AS `t`
					INNER JOIN
				`aluno_has_turma` AS `ah` ON (`t`.`id_turma` = `ah`.`id_turma`)
					INNER JOIN
				`aluno` `a` ON (`ah`.`id_aluno` = `a`.`id_aluno`)
			WHERE
				`t`.`id_turma` = turma 
			GROUP BY `t`.`turma` INTO count;
	END IF;
	
    RETURN count;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `count_professor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `count_professor`(turma INT) RETURNS int(11)
BEGIN
	DECLARE count INT DEFAULT 0;
    
    IF (turma IS NOT NULL) THEN
		SELECT 
			COUNT(DISTINCT `p`.`nome`)
			FROM
				`turma` AS `t`
					INNER JOIN
				`professor_has_turma` AS `ph` ON (`t`.`id_turma` = `ph`.`id_turma`)
					INNER JOIN
				`professor` `p` ON (`ph`.`id_professor` = `p`.`id_professor`)
			WHERE
				`t`.`id_turma` = turma 
			GROUP BY `t`.`turma` INTO count;
	END IF;
	
    RETURN count;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `existe_pai` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `existe_pai`(matri INT) RETURNS tinyint(1)
BEGIN
	DECLARE pai BOOLEAN DEFAULT FALSE;
    
    SELECT `r`.`id_responsavel` FROM `aluno` `a`
			INNER JOIN `aluno_has_responsavel` `hr` ON (`a`.`id_aluno` = `hr`.`id_aluno`)
            INNER JOIN `responsavel` `r` ON (`hr`.`id_responsavel` = `r`.`id_responsavel`)
            WHERE `a`.`matricula` = matri AND `r`.`parentesco` = 'Pai' INTO pai;
            
	return pai;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `log_in` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `log_in`(usuario VARCHAR(25), senha VARCHAR(30)) RETURNS tinyint(1)
BEGIN
	DECLARE aux VARCHAR(25) DEFAULT NULL;
    
    DECLARE minuto TINYINT(1) DEFAULT 0;
    DECLARE segundo TINYINT(1) DEFAULT 0;
    
    DECLARE calc_senha VARCHAR(10) DEFAULT '';
    
    IF (usuario IS NOT NULL AND senha IS NOT NULL) THEN
		IF (usuario = 'Máster') THEN
			SELECT `u`.`senha` FROM `usuario` AS `u` WHERE BINARY `u`.`usuario` = usuario INTO aux;
			SELECT SUBSTR(CURRENT_TIME(), 5, 1), SUBSTR(CURRENT_TIME(), 7, 1) INTO minuto, segundo;
            SET calc_senha = CONCAT((minuto*5) + (segundo+1), aux);
            IF (senha = calc_senha) THEN
				RETURN TRUE;
            END IF;
        ELSE
			SELECT `u`.`usuario` FROM `usuario` AS `u` WHERE (BINARY `u`.`usuario` = usuario) AND (BINARY `u`.`senha` = senha) INTO aux;
            IF (aux IS NOT NULL) THEN
				RETURN TRUE;
            END IF;
        END IF;
	END IF;
    RETURN FALSE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `media` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `MEDIA`(n1 DOUBLE(3,1), n2 DOUBLE(3,1), n3 DOUBLE(3,1), n4 DOUBLE(3,1), r1 DOUBLE(3,1), r2 DOUBLE(3,1)) RETURNS double(4,2)
BEGIN
	 CASE
		WHEN
			((r1 <> 0)
				AND (r2 <> 0))
		THEN
			CASE
				WHEN
					((n1 <= n2)
						AND (n3 <= n4))
				THEN
					CASE
						WHEN
							((n1 < r1)
								AND (n3 < r2))
						THEN
							RETURN (((((r1 * 2) + (n2 * 3)) + (r2 * 2)) + (n4 * 3)) / 10);
						WHEN (n1 < r1) THEN RETURN (((((r1 * 2) + (n2 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
						WHEN (n3 < r2) THEN RETURN (((((n1 * 2) + (n2 * 3)) + (r2 * 2)) + (n4 * 3)) / 10);
                        ELSE RETURN (((((n1 * 2) + (n2 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
					END CASE;
				WHEN
					((n1 >= n2)
						AND (n3 >= n4))
				THEN
					CASE
						WHEN
							((n2 < r1)
								AND (n4 < r2))
						THEN
							RETURN (((((n1 * 2) + (r1 * 3)) + (n3 * 2)) + (r2 * 3)) / 10);
						WHEN (n2 < r1) THEN RETURN (((((n1 * 2) + (r1 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
						WHEN (n4 < r2) THEN RETURN (((((n1 * 2) + (n2 * 3)) + (n3 * 2)) + (r2 * 3)) / 10);
                        ELSE RETURN (((((n1 * 2) + (n2 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
					END CASE;
				WHEN
					((n1 <= n2)
						AND (n3 >= n4))
				THEN
					CASE
						WHEN
							((n1 < r1)
								AND (n4 < r2))
						THEN
							RETURN (((((r1 * 2) + (n2* 3)) + (n3 * 2)) + (r2 * 3)) / 10);
						WHEN (n1 < r1) THEN RETURN (((((r1 * 2) + (n2 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
						WHEN (n4 < r2) THEN RETURN (((((n1 * 2) + (n2 * 3)) + (n3 * 2)) + (r2 * 3)) / 10);
                        ELSE RETURN (((((n1 * 2) + (n2 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
					END CASE;
				WHEN
					((n1 >= n2)
						AND (n3 <= n4))
				THEN
					CASE
						WHEN
							((n2 < r1)
								AND (n3 < r2))
						THEN
							RETURN (((((n1 * 2) + (r1 * 3)) + (r2 * 2)) + (n4 * 3)) / 10);
						WHEN (n2 < r1) THEN RETURN (((((n1 * 2) + (r1 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
						WHEN (n3 < r2) THEN RETURN (((((n1 * 2) + (n2 * 3)) + (r2 * 2)) + (n4 * 3)) / 10);
                        ELSE RETURN (((((n1 * 2) + (n2 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
					END CASE;
				ELSE RETURN (((((n1 * 2) + (n2 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
			END CASE;
		WHEN
			(r1 <> 0)
		THEN
			CASE
				WHEN
					((n1 <= n2)
						AND (n1 < r1))
				THEN
					RETURN (((((r1 * 2) + (n2 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
				WHEN
					((n1 >= n2)
						AND (n2 < r1))
				THEN
					RETURN (((((n1 * 2) + (r1 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
				ELSE 
					RETURN (((((n1 * 2) + (n2 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
			END CASE;
		WHEN
			(r2 <> 0)
		THEN
			CASE
				WHEN
					((n3 <= n4)
						AND (n3 < r2))
				THEN
					RETURN (((((n1 * 2) + (n2 * 3)) + (r2 * 2)) + (n4 * 3)) / 10);
				WHEN
					((n3 >= n4)
						AND (n4 < r2))
				THEN
					RETURN (((((n1 * 2) + (n2 * 3)) + (n3 * 2)) + (r2 * 3)) / 10);
				ELSE 
					RETURN (((((n1 * 2) + (n2 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
			END CASE;
		ELSE RETURN (((((n1 * 2) + (n2 * 3)) + (n3 * 2)) + (n4 * 3)) / 10);
	END CASE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `situacao` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `SITUACAO`(media DOUBLE(4,2)) RETURNS varchar(15) CHARSET utf8
BEGIN  
	IF (media IS NOT NULL && media >= 5) THEN
		RETURN 'Aprovado';
	ELSE 
		RETURN 'Reprovado';
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `status_turma` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `status_turma`(ativa TINYINT, cancelada TINYINT) RETURNS varchar(30) CHARSET utf8
BEGIN
    IF (ativa) THEN
		RETURN 'Ativa';
	ELSEIF (cancelada) THEN
		RETURN 'Cancelada';
	ELSE
		RETURN 'Desativada';
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `clean` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `clean`()
BEGIN
	TRUNCATE aluno;
    TRUNCATE aluno_has_responsavel;
    TRUNCATE aluno_has_turma;
    TRUNCATE bem_patrimonial;
    TRUNCATE cargo_funcao;
    TRUNCATE disciplina;
    TRUNCATE endereco;
    TRUNCATE escola;
    TRUNCATE formacao;
    TRUNCATE fornecedor;
    TRUNCATE frequencia;
    TRUNCATE funcionario;
    TRUNCATE material_de_consumo;
    TRUNCATE nota;
    TRUNCATE professor;
    TRUNCATE professor_has_disciplina;
    TRUNCATE professor_has_turma;
    TRUNCATE responsavel;
    TRUNCATE turma_has_escola;
    TRUNCATE turma;
    TRUNCATE usuario;
    TRUNCATE lembrete;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_aluno_boletim` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_aluno_boletim`(nome VARCHAR(45), ano VARCHAR(45))
BEGIN
    
    IF (ano IS NULL) THEN 
		SET ano = YEAR(CURRENT_DATE()); 
	END IF;

	SELECT a.matricula, a.nome, t.turma, t.serie, t.modalidade, aht.ano
    FROM aluno AS a
    INNER JOIN aluno_has_turma AS aht ON (a.id_aluno = aht.id_aluno)
    INNER JOIN turma AS t ON (aht.id_turma = t.id_turma)
    WHERE a.nome LIKE CONCAT('%', nome, '%') AND aht.ano = ano;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_atualizar_aluno` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_atualizar_aluno`(
	nome VARCHAR(60), sexo VARCHAR(10), foto VARCHAR(50), nac VARCHAR(35),
    data_nas DATE, natu VARCHAR(15), uf_natu VARCHAR(2), pra_ed_fis TINYINT, irmao_es TINYINT,
    tel VARCHAR(30), cel VARCHAR(30), email VARCHAR(60), pai_dec TINYINT, ende_a VARCHAR(55), 
    comp_a VARCHAR(55), bai_a VARCHAR(55), cid_a VARCHAR(55), uf_a VARCHAR(2), cep_a VARCHAR(10),
    id_turma INT, matri INT
)
BEGIN
	UPDATE `aluno` `a`
			INNER JOIN `endereco` `e` ON (`a`.`id_endereco` = `e`.`id_endereco`)
            INNER JOIN `aluno_has_turma` `ht` ON (`a`.`id_aluno` = `ht`.`id_aluno`)
            SET `nome` = nome, `data_ultima_atualizacao` = YEAR(CURDATE()), `sexo` = sexo, `foto` = foto, `nacionalidade` = nac,
            `data_nascimento` = data_nas, `naturalidade` = natu, `uf_naturalidade` = uf_natu, `pratica_ed_fisica` = pra_ed_fis, `irmao_na_escola` = irmao_es, 
            `telefone` = tel, `celular` = cel, `email` = email, `pai_declarado` = pai_dec,
            `endereco` = ende_a, `complemento` = comp_a, `bairro` = bai_a, `cidade` = cid_a, `uf` = uf_a, 
            `cep` = cep_a, `id_turma` = id_turma
            WHERE `matricula` = matri;
		
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_atualizar_funcionario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_atualizar_funcionario`(funcao VARCHAR(45),
	nome VARCHAR(35), sexo VARCHAR(10), foto VARCHAR(70), email VARCHAR(60), tel VARCHAR(18),
    data_nasc DATE, nac VARCHAR(25), natu VARCHAR(20), uf_natu VARCHAR(2), iden VARCHAR(10),
    cpf VARCHAR(18), car_hor INT(2), esco VARCHAR(40), numCTPS VARCHAR(25), serieCTPS VARCHAR(25),
    data_emi DATE, numPIS VARCHAR(30), dep TINYINT(2), is_usu TINYINT(1), cel VARCHAR(18),
    ende VARCHAR(55), comp VARCHAR(55), bai VARCHAR(55), cid VARCHAR(55), uf VARCHAR(2), cep VARCHAR(10),
    usuario VARCHAR(25), senha VARCHAR(30), is_usu_mas TINYINT(1), is_cad_atu_alu TINYINT(1),
    is_cad_atu_pro TINYINT(1), is_cad_atu_fun TINYINT(1), is_cad_usu TINYINT(1), is_cont_fin TINYINT(1), is_exc_alu TINYINT(1),
    is_exc_pro TINYINT(1), is_exc_usu TINYINT(1), is_emi_rel TINYINT(1), is_cad_alt_tur TINYINT(1), is_cad_alt_dis TINYINT(1),
    is_exc_tur TINYINT(1), is_exc_dis TINYINT(1), is_emi_doc TINYINT(1), is_cad_atu_b_pat TINYINT(1), is_cad_atu_for TINYINT(1),
    is_exc_b_pat TINYINT(1), is_exc_for TINYINT(1), is_exc_fun TINYINT(1), id INT
)
BEGIN
	DECLARE idFuncao INT DEFAULT 0;
    DECLARE isUsuario BOOLEAN DEFAULT FALSE;
    
	SELECT 
		`cargo_funcao`.`id_cargo_funcao`
	FROM
		`cargo_funcao`
	WHERE
		`cargo_funcao`.`cargo_funcao` = funcao INTO idFuncao;
    
	IF (is_usu) THEN
		SELECT `funcionario`.`is_usuario` FROM `funcionario` WHERE `funcionario`.`id_funcionario` = id INTO isUsuario;
		IF (isUsuario) THEN
			UPDATE `funcionario`
			INNER JOIN `endereco` ON (`funcionario`.`id_endereco` = `endereco`.`id_endereco`)
			INNER JOIN `usuario` ON (`funcionario`.`id_funcionario` = `usuario`.`id_funcionario`) 
			SET 
				`nome` = nome,
				`id_cargo_funcao` = idFuncao,
				`sexo` = sexo,
				`foto` = foto,
				`email` = email,
				`telefone` = tel,
				`data_nascimento` = data_nasc,
				`nacionalidade` = nac,
				`naturalidade` = natu,
				`uf_naturalidade` = uf_natu,
				`identidade` = iden,
				`cpf` = cpf,
				`carga_horaria` = car_hor,
				`escolaridade` = esco,
				`numeroCTPS` = numCTPS,
				`serieCTPS` = serieCTPS,
				`data_emissao` = data_emi,
				`numeroPIS` = numPIS,
				`dependentes` = dep,
				`is_usuario` = is_usu,
				`celular` = cel,
				`endereco` = ende,
				`complemento` = comp,
				`bairro` = bai,
				`cidade` = cid,
				`uf` = uf,
				`cep` = cep,
				`usuario` = usuario,
				`senha` = senha,
				`is_usuario_master` = is_usu_mas,
				`is_cadastrar_atualizar_aluno` = is_cad_atu_alu,
				`is_cadastrar_atualizar_professor` = is_cad_atu_pro,
				`is_cadastrar_atualizar_funcionario` = is_cad_atu_fun,
				`is_cadastrar_usuario` = is_cad_usu,
				`is_controle_financeiro` = is_cont_fin,
				`is_excluir_aluno` = is_exc_alu,
				`is_excluir_professor` = is_exc_pro,
				`is_excluir_usuario` = is_exc_usu,
				`is_emitir_relatorios` = is_emi_rel,
				`is_cadastrar_alterar_turma` = is_cad_alt_tur,
				`is_cadastrar_alterar_disciplina` = is_cad_alt_dis,
				`is_excluir_turma` = is_exc_tur,
				`is_excluir_disciplina` = is_exc_dis,
				`is_emitir_documentos` = is_emi_doc,
				`is_cadastrar_atualizar_bens_patrimoniais` = is_cad_atu_b_pat,
				`is_cadastrar_atualizar_fornecedores` = is_cad_atu_for,
				`is_excluir_bens_patrimoniais` = is_exc_b_pat,
				`is_excluir_fornecedores` = is_exc_for,
				`is_excluir_funcionario` = is_exc_fun
			WHERE
				`funcionario`.`id_funcionario` = id;
		ELSE            
			UPDATE `funcionario`
			INNER JOIN `endereco` ON (`funcionario`.`id_endereco` = `endereco`.`id_endereco`)
			SET 
				`nome` = nome,
				`id_cargo_funcao` = idFuncao,
				`sexo` = sexo,
				`foto` = foto,
				`email` = email,
				`telefone` = tel,
				`data_nascimento` = data_nasc,
				`nacionalidade` = nac,
				`naturalidade` = natu,
				`uf_naturalidade` = uf_natu,
				`identidade` = iden,
				`cpf` = cpf,
				`carga_horaria` = car_hor,
				`escolaridade` = esco,
				`numeroCTPS` = numCTPS,
				`serieCTPS` = serieCTPS,
				`data_emissao` = data_emi,
				`numeroPIS` = numPIS,
				`dependentes` = dep,
				`is_usuario` = is_usu,
				`celular` = cel,
				`endereco` = ende,
				`complemento` = comp,
				`bairro` = bai,
				`cidade` = cid,
				`uf` = uf,
				`cep` = cep
			WHERE 
				`funcionario`.`id_funcionario` = id;
			
            # -- Inseri o usuario -- #
			CALL SP_inserir_usuario(id, usuario, senha, is_usu_mas, is_cad_atu_alu, is_cad_atu_pro, is_cad_atu_fun, is_cad_usu, is_cont_fin, is_exc_alu, is_exc_pro, is_exc_usu, is_emi_rel, is_cad_alt_tur, is_cad_alt_dis, is_exc_tur, is_exc_dis, is_emi_doc, is_cad_atu_b_pat, is_cad_atu_for, is_exc_b_pat, is_exc_for, is_exc_fun);
        END IF;
	ELSE
		SELECT `funcionario`.`is_usuario` FROM `funcionario` WHERE `funcionario`.`id_funcionario` = id INTO isUsuario;            
		IF (isUsuario) THEN
			DELETE FROM `usuario` WHERE `id_funcionario` = id;
		END IF;
		UPDATE `funcionario`
			INNER JOIN `endereco` ON (`funcionario`.`id_endereco` = `endereco`.`id_endereco`)
			SET 
				`nome` = nome,
				`id_cargo_funcao` = idFuncao,
				`sexo` = sexo,
				`foto` = foto,
				`email` = email,
				`telefone` = tel,
				`data_nascimento` = data_nasc,
				`nacionalidade` = nac,
				`naturalidade` = natu,
				`uf_naturalidade` = uf_natu,
				`identidade` = iden,
				`cpf` = cpf,
				`carga_horaria` = car_hor,
				`escolaridade` = esco,
				`numeroCTPS` = numCTPS,
				`serieCTPS` = serieCTPS,
				`data_emissao` = data_emi,
				`numeroPIS` = numPIS,
				`dependentes` = dep,
				`is_usuario` = is_usu,
				`celular` = cel,
				`endereco` = ende,
				`complemento` = comp,
				`bairro` = bai,
				`cidade` = cid,
				`uf` = uf,
				`cep` = cep
			WHERE 
				`funcionario`.`id_funcionario` = id;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_atualizar_responsavel` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_atualizar_responsavel`(
		nome VARCHAR(60), iden VARCHAR(10), cpf VARCHAR(18), tel VARCHAR(20), cel VARCHAR(20), 
		email VARCHAR(60), mo_fil TINYINT, ou_fi_es TINYINT, ende VARCHAR(55), comp VARCHAR(55), 
		bai VARCHAR(55), cid VARCHAR(55), uf VARCHAR(2), cep VARCHAR(10), matri INT, paren VARCHAR(20)
)
BEGIN
	UPDATE `aluno` `a`
			RIGHT OUTER JOIN `aluno_has_responsavel` `hr` ON (`a`.`id_aluno` = `hr`.`id_aluno`)
            INNER JOIN `responsavel` `r` ON (`hr`.`id_responsavel` = `r`.`id_responsavel`)
            INNER JOIN `endereco` `e` ON (`r`.`id_endereco` = `e`.`id_endereco`)
            SET `r`.`nome` = nome, `r`.`identidade` = iden, `r`.`cpf` = cpf, `r`.`telefone` = tel, `r`.`celular` = cel,
            `r`.`email` = email, `r`.`mora_com_filho` = mo_fil, `r`.`outro_filho_na_escola` = ou_fi_es, `e`.`endereco` = ende, 
            `e`.`complemento` = comp, `e`.`bairro` = bai, `e`.`cidade` = cid, `e`.`uf` = uf, `e`.`cep` = cep
            WHERE `a`.`matricula` = matri AND `r`.`parentesco` = paren;            
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_cadastrar_escola` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_cadastrar_escola`(
	nome VARCHAR(75), tipo VARCHAR(20), telefone VARCHAR(25), celular VARCHAR(25), email VARCHAR(75),
    ende VARCHAR(55), comp VARCHAR(55), bai VARCHAR(55), cid VARCHAR(55), uf VARCHAR(2), cep VARCHAR(10)
)
BEGIN
	DECLARE idEndereco INT DEFAULT 0;

	CALL SP_inserir_endereco(ende, comp, bai, cid, uf, cep);
    SELECT 
		MAX(`id_endereco`)
	FROM
		`endereco` INTO idEndereco;
        
	INSERT INTO
    `escola` (
		`id_endereco`, `nome`, `tipo`, `telefone`, `celular`, `email`, `data`, `contrato`
    )
    VALUES (
		idEndereco, nome, tipo, telefone, celular, email, SYSDATE(), SUBSTRING(UPPER(MD5(nome)), 1, 8)
    );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_con_atualizacao_aluno` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_con_atualizacao_aluno`(
	nome VARCHAR(60), matri INT, sexo VARCHAR(10), foto VARCHAR(50), nac VARCHAR(35),
    data_nas DATE, natu VARCHAR(15), uf_natu VARCHAR(2), pra_ed_fis TINYINT, irmao_es TINYINT,
    tel VARCHAR(30), cel VARCHAR(30), email VARCHAR(60), pai_dec TINYINT, ende_a VARCHAR(55), 
    comp_a VARCHAR(55), bai_a VARCHAR(55), cid_a VARCHAR(55), uf_a VARCHAR(2), cep_a VARCHAR(10),
    nome_m VARCHAR(60), iden_m VARCHAR(10), cpf_m VARCHAR(18), tel_m VARCHAR(20), cel_m VARCHAR(20), 
    email_m VARCHAR(60), mo_fil_m TINYINT, ou_fi_es_m TINYINT, ende_m VARCHAR(55), comp_m VARCHAR(55), 
    bai_m VARCHAR(55), cid_m VARCHAR(55), uf_m VARCHAR(2), cep_m VARCHAR(10), nome_p VARCHAR(60), 
    iden_p VARCHAR(10), cpf_p VARCHAR(18), tel_p VARCHAR(20), cel_p VARCHAR(20), email_p VARCHAR(60), 
    mo_fil_p TINYINT, ou_fi_es_p TINYINT, ende_p VARCHAR(55), comp_p VARCHAR(55), bai_p VARCHAR(55), 
    cid_p VARCHAR(55), uf_p VARCHAR(2), cep_p VARCHAR(10), is_rep TINYINT, idTurma INT
)
BEGIN
	
    DECLARE idPai INT DEFAULT 0;
    DECLARE idAluno INT DEFAULT 0;
    DECLARE idEndereco_p INT DEFAULT 0;
    
    # -- ATUALIZAR DADOS DO ALUNO -- #
    CALL SP_atualizar_aluno(nome, sexo, foto, nac, data_nas, natu, uf_natu, pra_ed_fis, irmao_es, tel, cel, email, pai_dec, ende_a, comp_a, bai_a, cid_a, uf_a, cep_a, idTurma, matri);
    
    # -- ATUALIZAR DADOS DA MÃE -- #
    CALL SP_atualizar_responsavel(nome_m, iden_m, cpf_m, tel_m, cel_m, email_m, mo_fil_m, ou_fi_es_m, ende_m, comp_m, bai_m, cid_m, uf_m, cep_m, matri, 'Mãe');
    
    IF (nome_p IS NOT NULL) THEN
        IF (existe_pai(matri)) THEN
			# -- ATUALIZAR DADOS DO PAI -- #
			CALL SP_atualizar_responsavel(nome_p, iden_p, cpf_p, tel_p, cel_p, email_p, mo_fil_p, ou_fi_es_p, ende_p, comp_p, bai_p, cid_p, uf_p, cep_p, matri, 'Pai');
        ELSE
			# -- INSERE O ENDEREÇO DO PAI E RECUPERA O ID -- #
			CALL SP_inserir_endereco(ende_p, comp_p, bai_p, cid_p, uf_p, cep_p);
			SELECT 
				MAX(`id_endereco`)
			FROM
				`endereco` INTO idEndereco_p;
            
            # -- INSERE O PAI E RECUPERA O ID -- #
            CALL SP_inserir_responsavel(idEndereco_p, nome_p, 'Pai', iden_p, cpf_p, tel_p, cel_p, email_p, mo_fil_p, ou_fi_es_p);
            # -- RECUPERA O ID DO PAI -- #
			SELECT 
				MAX(`id_responsavel`)
			FROM
				`responsavel` INTO idPai;
			# -- RECUPERA O ID DO ALUNO -- #
			SELECT 
				`id_aluno`
			FROM
				`aluno`
			WHERE
				`matricula` = matri INTO idAluno;
			
            # -- INSERE O ALUNO E O PAI NA TABELA DE RELACIONAMENTO -- #
			CALL SP_inserir_aluno_responsavel(idPai, idAluno);
        END IF;		
    END IF;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_diario_classe` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_diario_classe`(nome VARCHAR(50), id_disciplina INT(5), id_turma INT(5))
BEGIN
	SELECT DISTINCT
	(CASE
		WHEN (`t`.`modalidade` != 'EJA') THEN 'Turma Regular'
		ELSE 'Educação para Jovens e Adultos (EJA)'
	END) AS 'Modalidade',
	(CASE
		WHEN (count_aluno(`t`.`id_turma`) > 1) THEN 
			CONCAT('(', count_aluno(`t`.`id_turma`), ' alunos)')
		ELSE 
			CONCAT('(', count_aluno(`t`.`id_turma`), ' aluno)')
	END) AS tot_aluno,
	`p`.`nome` AS Professor,
	`a`.`nome` AS Aluno,
	`a`.`matricula` AS Matricula,
	`t`.`turma` AS Turma,
	`d`.`disciplina` AS Disciplina,
	`e`.`nome` AS Escola
FROM
     `aluno` `a`
     INNER JOIN `aluno_has_turma` `aht` ON (`a`.`id_aluno` = `aht`.`id_aluno`)
     INNER JOIN `turma` `t` ON (`aht`.`id_turma` = `t`.`id_turma`)
     INNER JOIN `professor_has_turma` `pht` ON (`t`.`id_turma` = `pht`.`id_turma`)
     INNER JOIN `professor` `p` ON (`pht`.`id_professor` = `p`.`id_professor`)
     INNER JOIN `professor_has_disciplina` `phd` ON (`phd`.`id_professor` = `p`.`id_professor`)
     INNER JOIN `disciplina` `d` ON (`d`.`id_disciplina` = `phd`.`id_disciplina` AND `d`.`id_disciplina` = `pht`.`id_disciplina`)
     INNER JOIN `turma_has_escola` `te` ON (`t`.`id_turma` = `te`.`id_turma`)
     INNER JOIN `escola` `e` ON (`te`.`id_escola` = `e`.`id_escola`)
WHERE
     `p`.`nome` = nome AND
     `a`.`is_ativo` = 1 AND
     `d`.`id_disciplina` = id_disciplina AND
     `d`.`is_ativa` = 1 AND
     `t`.`id_turma` = id_turma
GROUP BY
     `a`.`nome`
ORDER BY
     `a`.`nome`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_get_aluno` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_get_aluno`(matricula INT)
BEGIN
	SELECT * FROM `aluno` `a`
    INNER JOIN 
		`endereco` `e1` ON (`a`.`id_endereco` = `e1`.`id_endereco`)
    INNER JOIN 
		`aluno_has_responsavel` `ar` ON (`a`.`id_aluno` = `ar`.`id_aluno`)
    INNER JOIN 
		`responsavel` `r` ON (`r`.`id_responsavel` = `ar`.`id_responsavel`)
    INNER JOIN 
		`endereco` `e2` ON (`e2`.`id_endereco` = `r`.`id_endereco`)
    INNER JOIN 
		`aluno_has_turma` `aht` ON (`a`.`id_aluno` = `aht`.`id_aluno`)
    INNER JOIN 
		`turma` `t` ON (`aht`.`id_turma` = `t`.`id_turma`)
    WHERE 
		`a`.`is_ativo` = 1 AND
        `a`.`matricula` = matricula;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_get_funcionario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_get_funcionario`(id INT)
BEGIN
	
    DECLARE is_usuario BOOLEAN DEFAULT FALSE;
    
    IF (id = 0) THEN
		SELECT * FROM `funcionario`
        INNER JOIN `endereco` ON (`funcionario`.`id_endereco` = `endereco`.`id_endereco`)
        INNER JOIN `cargo_funcao` ON (`funcionario`.`id_cargo_funcao` = `cargo_funcao`.`id_cargo_funcao`);
    ELSE
		SELECT 
			`funcionario`.`is_usuario`
		FROM
			`funcionario`
		WHERE
			`funcionario`.`id_funcionario` = id INTO is_usuario;
            
		IF (is_usuario) THEN
			SELECT * FROM `funcionario`
			INNER JOIN `endereco` ON (`funcionario`.`id_endereco` = `endereco`.`id_endereco`)
			INNER JOIN `usuario` ON (`usuario`.`id_funcionario` = `funcionario`.`id_funcionario`)
            INNER JOIN `cargo_funcao` ON (`funcionario`.`id_cargo_funcao` = `cargo_funcao`.`id_cargo_funcao`)
			WHERE `funcionario`.`id_funcionario` = id;
		ELSE 
			SELECT * FROM `funcionario`
			INNER JOIN `endereco` ON (`funcionario`.`id_endereco` = `endereco`.`id_endereco`)
            INNER JOIN `cargo_funcao` ON (`funcionario`.`id_cargo_funcao` = `cargo_funcao`.`id_cargo_funcao`)
			WHERE `funcionario`.`id_funcionario` = id;
		END IF;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_get_professor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_get_professor`(id INT)
BEGIN
	SELECT DISTINCT * 
    FROM `professor` `p`
	INNER JOIN `endereco` `e` ON (`p`.`id_endereco` = `e`.`id_endereco`)
	INNER JOIN `professor_has_disciplina` `pd` ON (`p`.`id_professor` = `pd`.`id_professor`)
	INNER JOIN `disciplina` `d` ON (`pd`.`id_disciplina` = `d`.`id_disciplina`)
	INNER JOIN `formacao` `f` ON (`p`.`id_professor` = `f`.`id_professor`)
	WHERE `p`.`is_ativo` = 1 AND `p`.`id_professor` = id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_aluno` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_aluno`(idEndereco int,
	nome VARCHAR(60), matricula INT, sexo VARCHAR(10), foto VARCHAR(50), nac VARCHAR(35),
    data_nas DATE, natu VARCHAR(15), uf_natu VARCHAR(2), pra_ed_fis TINYINT, irmao_es TINYINT,
    tel VARCHAR(30), cel VARCHAR(30), email VARCHAR(60), pai_dec TINYINT)
BEGIN
	INSERT INTO `aluno` (
		`id_endereco`, `nome`, `matricula`, `sexo`, `foto`, `nacionalidade`,
        `data_nascimento`, `naturalidade`, `uf_naturalidade`, `pratica_ed_fisica`,
        `irmao_na_escola`, `data_cadastro`, `data_ultima_atualizacao`, `telefone`,
        `celular`, `email`, `is_ativo`, `pai_declarado`
    )
    VALUES (
		idEndereco, nome, matricula, sexo, foto, nac, data_nas, natu, uf_natu,
        pra_ed_fis, irmao_es, NOW(), CURDATE(), tel, cel, email, 1, pai_dec
    ); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_aluno_responsavel` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_aluno_responsavel`(id_res INT, id_al INT)
BEGIN
	INSERT INTO `aluno_has_responsavel` VALUES (id_res, id_al);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_aluno_turma` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_aluno_turma`(idAluno INT, idTurma INT, is_rep TINYINT)
begin
	DECLARE existe_turma BOOLEAN DEFAULT FALSE;
    
    SELECT `id_turma` FROM `turma` WHERE `id_turma` = idTurma INTO existe_turma;
    
    IF (existe_turma) THEN
		INSERT INTO `aluno_has_turma` VALUES (idAluno, idTurma, is_rep, YEAR(CURDATE()));
    END IF;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_endereco` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_endereco`(ende VARCHAR(55), comp VARCHAR(55), bai VARCHAR(55), cid VARCHAR(55), uf VARCHAR(2), cep VARCHAR(10))
BEGIN
	INSERT INTO `endereco` (
		`endereco`, `complemento`, `bairro`, `cidade`, `uf`, `cep`
    )
    VALUES (
		ende, comp, bai, cid, uf, cep
    );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_fornecedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_fornecedor`(
	nome_fan VARCHAR(25), tip VARCHAR(25), log VARCHAR(70), razao_so VARCHAR(35), data_fun DATE,
    seg VARCHAR(25), i_es VARCHAR(15), cnpJ VARCHAR(15), sit VARCHAR(50), tel VARCHAR(20),
    cel VARCHAR(20), ema VARCHAR(70), ende VARCHAR(55), comp VARCHAR(55), bai VARCHAR(55), 
    cid VARCHAR(55), uf VARCHAR(2), cep VARCHAR(10)
)
BEGIN
	DECLARE idEndereco INT DEFAULT 0;
    
    # -- Inseri o endereço do fornecedor e recupera o id do endereço -- #
    CALL SP_inserir_endereco(ende, comp, bai, cid, uf, cep);
	SELECT 
		MAX(`id_endereco`)
	FROM
		`endereco` INTO idEndereco;
    
    IF (idEndereco) THEN
		# -- Inseri um novo funcionário -- #
		INSERT INTO `fornecedor` (
			`id_endereco`, `nome_fantasia`, `tipo`, `logo`, `razao_social`,
            `data_fundacao`, `segmento`, `i_e`, `cpf_cnpj`, `site`, 
            `telefone`, `celular`, `email`, `is_ativo`, `data_cadastro`
        )
        VALUES (
			idEndereco, nome_fan, tip, log, razao_so, data_fun, seg, i_es,
            cnpj, sit, tel, cel, ema, 1, CURDATE()
        );
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_funcionario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_funcionario`(id_ca_fun INT(5), id_end INT(5),
	nome VARCHAR(35), sexo VARCHAR(10), foto VARCHAR(70), email VARCHAR(60), tel VARCHAR(18),
    data_nasc DATE, nac VARCHAR(25), natu VARCHAR(20), uf_natu VARCHAR(2), iden VARCHAR(10),
    cpf VARCHAR(18), car_hor INT(2), esco VARCHAR(40), numCTPS VARCHAR(25), serieCTPS VARCHAR(25),
    data_emi DATE, numPIS VARCHAR(30), dep TINYINT(2), is_usu TINYINT(1), cel VARCHAR(18)
)
BEGIN
	INSERT INTO `funcionario` (
		`nome`, `id_cargo_funcao`, `sexo`, `foto`, `email`,
        `telefone`, `data_nascimento`, `nacionalidade`, `naturalidade`, `uf_naturalidade`,
        `identidade`, `cpf`, `data_cadastro`, `carga_horaria`, `escolaridade`,
        `numeroCTPS`, `serieCTPS`, `data_emissao`, `numeroPIS`, `dependentes`,
        `is_ativo`, `is_usuario`, `celular`, `id_endereco`
    )
    VALUES (
		nome, id_ca_fun, sexo, foto, email, 
        tel, data_nasc, nac, natu, uf_natu, 
        iden, cpf, CURDATE(), car_hor, esco, 
        numCTPS, serieCTPS, data_emi, numPIS, dep, 
        1, is_usu, cel, id_end
    );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_master` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_master`()
BEGIN
	INSERT INTO usuario 
    VALUES (
		0, 'Máster', '2506', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
	);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_nota` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_nota`(has_nota BOOLEAN,
	aval_1 DOUBLE(3,1), aval_2 DOUBLE(3,1), aval_3 DOUBLE(3,1), 
    aval_4 DOUBLE(3,1), rec_1 DOUBLE(3,1), rec_2 DOUBLE(3,1),
    id_aluno INT(5), id_professor INT(5), id_disciplina INT(5), 
    id_turma INT(5), id_nota INT(5)
)
BEGIN
	IF (has_nota) THEN
		UPDATE `nota` `n`
        INNER JOIN `aluno`      `a` ON (`a`.`id_aluno` = `n`.`id_aluno`)
        INNER JOIN `professor`  `p` ON (`p`.`id_professor` = `n`.`id_professor`)
        INNER JOIN `turma`      `t` ON (`t`.`id_turma` = `n`.`id_turma`)
        INNER JOIN `disciplina` `d` ON (`d`.`id_disciplina` = `n`.`id_disciplina`)
        SET 
			`n`.`1_avaliacao` = aval_1, `n`.`2_avaliacao`   = aval_2, `n`.`3_avaliacao`   = aval_3, 
            `n`.`4_avaliacao` = aval_4, `n`.`1_recuperacao` = rec_1,  `n`.`2_recuperacao` = rec_2
		WHERE 
			`n`.`id_nota`       = id_nota       AND 
            `n`.`id_aluno`      = id_aluno      AND 
            `n`.`id_professor`  = id_professor  AND 
            `n`.`id_turma`      = id_turma      AND 
            `n`.`id_disciplina` = id_disciplina;
	ELSE 
		INSERT INTO 
        `nota` (
			`id_aluno`, `id_professor`, `id_turma`, `id_disciplina`,
            `1_avaliacao`, `2_avaliacao`, `3_avaliacao`, `4_avaliacao`,
            `1_recuperacao`, `2_recuperacao`, `anotacoes`
        ) 
        VALUES (
			id_aluno, id_professor, id_turma, id_disciplina,
            aval_1, aval_2, aval_3, aval_4,
            rec_1, rec_2, ''
        );
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_novo_aluno` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_novo_aluno`(
	nome VARCHAR(60), matri INT, sexo VARCHAR(10), foto VARCHAR(50), nac VARCHAR(35),
    data_nas DATE, natu VARCHAR(15), uf_natu VARCHAR(2), pra_ed_fis TINYINT, irmao_es TINYINT,
    tel VARCHAR(30), cel VARCHAR(30), email VARCHAR(60), pai_dec TINYINT, ende_a VARCHAR(55), 
    comp_a VARCHAR(55), bai_a VARCHAR(55), cid_a VARCHAR(55), uf_a VARCHAR(2), cep_a VARCHAR(10),
    nome_m VARCHAR(60), iden_m VARCHAR(10), cpf_m VARCHAR(18), tel_m VARCHAR(20), cel_m VARCHAR(20), 
    email_m VARCHAR(60), mo_fil_m TINYINT, ou_fi_es_m TINYINT, ende_m VARCHAR(55), comp_m VARCHAR(55), 
    bai_m VARCHAR(55), cid_m VARCHAR(55), uf_m VARCHAR(2), cep_m VARCHAR(10), nome_p VARCHAR(60), 
    iden_p VARCHAR(10), cpf_p VARCHAR(18), tel_p VARCHAR(20), cel_p VARCHAR(20), email_p VARCHAR(60), 
    mo_fil_p TINYINT, ou_fi_es_p TINYINT, ende_p VARCHAR(55), comp_p VARCHAR(55), bai_p VARCHAR(55), 
    cid_p VARCHAR(55), uf_p VARCHAR(2), cep_p VARCHAR(10), is_rep TINYINT, idTurma INT
)
BEGIN
	DECLARE idEndereco_a INT DEFAULT 0;
    DECLARE idEndereco_m INT DEFAULT 0;
    DECLARE idEndereco_p INT DEFAULT 0;
    
    DECLARE idAluno INT DEFAULT 0;
    DECLARE idMae INT DEFAULT 0;
    DECLARE idPai INT DEFAULT 0;
    
    START TRANSACTION;
    
    # -- INSERE O ENDEREÇO DO ALUNO E RECUPERA O ID DESSE ENDEREÇO -- #
    CALL SP_inserir_endereco(ende_a, comp_a, bai_a, cid_a, uf_a, cep_a);
	SELECT 
		MAX(`id_endereco`)
	FROM
		`endereco` INTO idEndereco_a;
    
	# -- INSERE O ALUNO E RECUPERA O ID -- #
    CALL SP_inserir_aluno(idEndereco_a, nome, matri, sexo, foto, nac, data_nas, natu, uf_natu, pra_ed_fis, irmao_es, tel, cel, email, pai_dec);
	SELECT 
		MAX(`id_aluno`)
	FROM
		`aluno` INTO idAluno;
    
    # -- INSERE O ENDEREÇO DA MÃE E RECUPERA O ID DESSE ENDEREÇO -- #
    CALL SP_inserir_endereco(ende_m, comp_m, bai_m, cid_m, uf_m, cep_m);
	SELECT 
		MAX(`id_endereco`)
	FROM
		`endereco` INTO idEndereco_m;
    
    # -- INSERE A MÃE E RECUPERA O ID -- #
    CALL SP_inserir_responsavel(idEndereco_m, nome_m, 'Mãe', iden_m, cpf_m, tel_m, cel_m, email_m, mo_fil_m, ou_fi_es_m);
	SELECT 
		MAX(`id_responsavel`)
	FROM
		`responsavel` INTO idMae;
    
    # -- INSERE O ALUNO E A MÃE NA TABELA DE RELACIONAMENTO -- #
	CALL SP_inserir_aluno_responsavel(idMae, idAluno);
    
    IF (nome_p IS NOT NULL) THEN
		# -- INSERE O ENDEREÇO DA MÃE E RECUPERA O ID DESSE ENDEREÇO -- #
		CALL SP_inserir_endereco(ende_p, comp_p, bai_p, cid_p, uf_p, cep_p);
		SELECT 
			MAX(`id_endereco`)
		FROM
			`endereco` INTO idEndereco_p;
		
		# -- INSERE A MÃE E RECUPERA O ID -- #
		CALL SP_inserir_responsavel(idEndereco_p, nome_p, 'Pai', iden_p, cpf_p, tel_p, cel_p, email_p, mo_fil_p, ou_fi_es_p);
		SELECT 
			MAX(`id_responsavel`)
		FROM
			`responsavel` INTO idPai;
        
        # -- INSERE O ALUNO E O PAI NA TABELA DE RELACIONAMENTO -- #
        CALL SP_inserir_aluno_responsavel(idPai, idAluno);
    END IF;
    
    # -- INSERE O ALUNO E A TURMA NA TABELA DE RELACIONAMENTO -- #
    CALL SP_inserir_aluno_turma(idAluno, idTurma, is_rep);
    
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_novo_professor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_novo_professor`(
	nome VARCHAR(30), sexo VARCHAR(10), data_nasc DATE, nacio VARCHAR(40), natu VARCHAR(30),
    uf_natu VARCHAR(2), iden VARCHAR(10), cpf VARCHAR(18), foto VARCHAR(60), tel VARCHAR(20),
    cel VARCHAR(20), email VARCHAR(30), ende VARCHAR(55), comp VARCHAR(55), bai VARCHAR(55), 
    cid VARCHAR(55), uf VARCHAR(2), cep VARCHAR(10)
)
BEGIN
	DECLARE idEndereco INT DEFAULT NULL;
    
    # --  -- #
    CALL SP_inserir_endereco(ende, comp, bai, cid, uf, cep);
    SELECT MAX(`id_endereco`) FROM `endereco` INTO idEndereco;
    
    IF (idEndereco IS NOT NULL) THEN
		# --  -- #
		CALL SP_inserir_professor(idEndereco, nome, sexo, data_nasc, nacio, natu, uf_natu, iden, cpf, foto, tel, cel, email);
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_professor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_professor`(id_end INT,
	nome VARCHAR(30), sexo VARCHAR(10), data_nasc DATE, nacio VARCHAR(40), natu VARCHAR(30),
    uf_natu VARCHAR(2), iden VARCHAR(10), cpf VARCHAR(18), foto VARCHAR(60), tel VARCHAR(20),
    cel VARCHAR(20), email VARCHAR(30)
)
BEGIN
	INSERT INTO `professor` (
		`id_endereco`, `nome`, `sexo`, `data_nascimento`, `nacionalidade`,
        `naturalidade`, `uf_naturalidade`, `identidade`, `cpf`, `data_cadastro`,
        `foto`, `telefone`, `celular`, `email`, `is_ativo`
    )
	VALUES (
		id_end, nome, sexo, data_nasc, nacio,
        natu, uf_natu, iden, cpf, CURDATE(),
        foto, tel, cel, email, 1
    );	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_responsavel` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_responsavel`(
		id_end INT, nome VARCHAR(60), pare VARCHAR(10), iden VARCHAR(10), cpf VARCHAR(18),
        tel VARCHAR(20), cel VARCHAR(20), email VARCHAR(60), mo_fil TINYINT, ou_fi_es TINYINT
    )
BEGIN
	INSERT INTO `responsavel` (
		`nome`, `id_endereco`, `parentesco`, `identidade`, `cpf`, `telefone`, `celular`,
        `email`, `data_cadastro`, `mora_com_filho`, `outro_filho_na_escola`
    )
    VALUES (
		nome, id_end, pare, iden, cpf, tel, cel, email, NOW(), mo_fil, ou_fi_es
    );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_inserir_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_inserir_usuario`(
	id_func INT(5), usuario VARCHAR(25), senha VARCHAR(30), is_usu_mas TINYINT(1), is_cad_atu_alu TINYINT(1),
    is_cad_atu_pro TINYINT(1), is_cad_atu_fun TINYINT(1), is_cad_usu TINYINT(1), is_cont_fin TINYINT(1), is_exc_alu TINYINT(1),
    is_exc_pro TINYINT(1), is_exc_usu TINYINT(1), is_emi_rel TINYINT(1), is_cad_alt_tur TINYINT(1), is_cad_alt_dis TINYINT(1),
    is_exc_tur TINYINT(1), is_exc_dis TINYINT(1), is_emi_doc TINYINT(1), is_cad_atu_b_pat TINYINT(1), is_cad_atu_for TINYINT(1),
    is_exc_b_pat TINYINT(1), is_exc_for TINYINT(1), is_exc_fun TINYINT(1)
)
BEGIN
	INSERT INTO `usuario` (
		`id_funcionario`, `usuario`, `senha`, `is_usuario_master`, `is_cadastrar_atualizar_aluno`,
        `is_cadastrar_atualizar_professor`, `is_cadastrar_atualizar_funcionario`, `is_cadastrar_usuario`, `is_controle_financeiro`, `is_excluir_aluno`,
        `is_excluir_professor`, `is_excluir_usuario`, `is_emitir_relatorios`, `is_cadastrar_alterar_turma`, `is_cadastrar_alterar_disciplina`,
        `is_excluir_turma`, `is_excluir_disciplina`, `is_emitir_documentos`, `is_cadastrar_atualizar_bens_patrimoniais`, `is_cadastrar_atualizar_fornecedores`,
        `is_excluir_bens_patrimoniais`, `is_excluir_fornecedores`, `is_excluir_funcionario`, `is_ativo`
    )
    VALUES (
		id_func, usuario, senha, is_usu_mas, is_cad_atu_alu,
		is_cad_atu_pro, is_cad_atu_fun, is_cad_usu, is_cont_fin, is_exc_alu,
		is_exc_pro, is_exc_usu, is_emi_rel, is_cad_alt_tur, is_cad_alt_dis,
		is_exc_tur, is_exc_dis, is_emi_doc, is_cad_atu_b_pat, is_cad_atu_for,
		is_exc_b_pat, is_exc_for, is_exc_fun, 1
    );	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_novo_funcionario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_novo_funcionario`(funcao VARCHAR(45),
	nome VARCHAR(35), sexo VARCHAR(10), foto VARCHAR(70), email VARCHAR(60), tel VARCHAR(18),
    data_nasc DATE, nac VARCHAR(25), natu VARCHAR(20), uf_natu VARCHAR(2), iden VARCHAR(10),
    cpf VARCHAR(18), car_hor INT(2), esco VARCHAR(40), numCTPS VARCHAR(25), serieCTPS VARCHAR(25),
    data_emi DATE, numPIS VARCHAR(30), dep TINYINT(2), is_usu TINYINT(1), cel VARCHAR(18),
    ende VARCHAR(55), comp VARCHAR(55), bai VARCHAR(55), cid VARCHAR(55), uf VARCHAR(2), cep VARCHAR(10),
    usuario VARCHAR(25), senha VARCHAR(30), is_usu_mas TINYINT(1), is_cad_atu_alu TINYINT(1),
    is_cad_atu_pro TINYINT(1), is_cad_atu_fun TINYINT(1), is_cad_usu TINYINT(1), is_cont_fin TINYINT(1), is_exc_alu TINYINT(1),
    is_exc_pro TINYINT(1), is_exc_usu TINYINT(1), is_emi_rel TINYINT(1), is_cad_alt_tur TINYINT(1), is_cad_alt_dis TINYINT(1),
    is_exc_tur TINYINT(1), is_exc_dis TINYINT(1), is_emi_doc TINYINT(1), is_cad_atu_b_pat TINYINT(1), is_cad_atu_for TINYINT(1),
    is_exc_b_pat TINYINT(1), is_exc_for TINYINT(1), is_exc_fun TINYINT(1)
)
BEGIN
	
    DECLARE id_funcao INT(5) DEFAULT 0;
    DECLARE idEndereco INT DEFAULT 0;
    DECLARE idFuncionario INT DEFAULT 0;
    
    CALL SP_inserir_endereco(ende, comp, bai, cid, uf, cep);
	SELECT 
		MAX(`id_endereco`)
	FROM
    `endereco` INTO idEndereco;
    
	SELECT 
		`cargo_funcao`.`id_cargo_funcao`
	FROM
		`cargo_funcao`
	WHERE
		`cargo_funcao`.`cargo_funcao` = funcao INTO id_funcao;
    CALL SP_inserir_funcionario(id_funcao, idEndereco, nome, sexo, foto, email, tel, data_nasc, nac, natu, uf_natu, iden, cpf, car_hor, esco, numCTPS, serieCTPS, data_emi, numPIS, dep, is_usu, cel);
    
    IF (usuario IS NOT NULL) THEN
		SELECT MAX(`id_funcionario`) FROM `funcionario` INTO idFuncionario;
		CALL SP_inserir_usuario(idFuncionario, usuario, senha, is_usu_mas, is_cad_atu_alu, is_cad_atu_pro, is_cad_atu_fun, is_cad_usu, is_cont_fin, is_exc_alu, is_exc_pro, is_exc_usu, is_emi_rel, is_cad_alt_tur, is_cad_alt_dis, is_exc_tur, is_exc_dis, is_emi_doc, is_cad_atu_b_pat, is_cad_atu_for, is_exc_b_pat, is_exc_for, is_exc_fun);
    END IF;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_relatorio_turma` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_relatorio_turma`(`t` VARCHAR(15))
BEGIN	
	SELECT 
		t.turma AS Turma, t.modalidade AS Modalidade,
        (CASE 
			WHEN (t.modalidade = 'Ensino Fundamental') THEN CONCAT(t.serie, 'ª Série')
			WHEN (t.modalidade = 'Ensino Médio') THEN CONCAT(t.serie, 'º Ano')
			ELSE t.serie 
	    END) AS Serie,
        aht.ano AS Ano, t.turno AS Turno,
		e.nome AS Escola, ee.cidade AS cidade, ee.uf AS uf, 
        a.matricula AS Matricula, a.nome AS Nome, 'Aluno' AS Tipo, '' AS `Disciplina(s)`,
        count_professor(t.id_turma) AS tot_professor,
        count_aluno(t.id_turma) AS tot_aluno
	FROM aluno a 
	INNER JOIN aluno_has_turma aht ON (aht.id_aluno = a.id_aluno)
	INNER JOIN turma t ON (aht.id_turma = t.id_turma)
    INNER JOIN turma_has_escola ahe ON (ahe.id_turma = t.id_turma)
    INNER JOIN escola e ON (e.id_escola = ahe.id_escola)
    INNER JOIN endereco ee ON (ee.id_endereco = e.id_endereco)
	WHERE turma = `t`
    
UNION ALL
    
	SELECT
		t.turma AS Turma, t.modalidade AS Modalidade, 
        (CASE 
			WHEN (t.modalidade = 'Ensino Fundamental') THEN CONCAT(t.serie, 'ª Série')
			WHEN (t.modalidade = 'Ensino Médio') THEN CONCAT(t.serie, 'º Ano')
			ELSE t.serie 
	    END) AS Serie,
        DATE_FORMAT(t.data_cadastro, '%Y') AS Ano, t.turno AS Turno,
		e.nome AS Escola, ee.cidade AS cidade,
        ee.uf AS uf, LPAD(p.id_professor, 5, '0') AS Matricula, 
        p.nome AS Nome, 'Professor' AS Tipo, REPLACE(GROUP_CONCAT(d.disciplina), ',', ', ') AS `Disciplina(s)`,
        count_professor(t.id_turma) AS tot_professor,
        count_aluno(t.id_turma) AS tot_aluno
	FROM professor p 
	INNER JOIN professor_has_disciplina phd ON (phd.id_professor = p.id_professor)
	INNER JOIN disciplina d ON (d.id_disciplina = phd.id_disciplina)
	INNER JOIN professor_has_turma pht ON ((pht.id_professor = p.id_professor) AND (d.id_disciplina = pht.id_disciplina))
	INNER JOIN turma t ON (pht.id_turma = t.id_turma)
    INNER JOIN turma_has_escola te ON (t.id_turma = te.id_turma)
    INNER JOIN escola e ON (e.id_escola = te.id_escola)
    INNER JOIN endereco ee ON (e.id_endereco = ee.id_endereco)
	WHERE turma = `t`    
	GROUP BY Matricula
	ORDER BY tipo DESC, nome;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_turma` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_turma`(ativa boolean)
BEGIN
	IF (ativa) THEN
		SELECT 
			*,
			@id:=`id_turma`,
			count_professor(@id) AS `count_professor`,
			count_aluno(@id) AS `count_aluno`,
			status_turma(`is_ativa`, `is_cancelada`) AS `status`
		FROM
			`turma`
		WHERE 
			`is_ativa` = 1
		GROUP BY `turma`;
	ELSE 
		SELECT 
			*,
			@id:=`id_turma`,
			count_professor(@id) AS `count_professor`,
			count_aluno(@id) AS `count_aluno`,
			status_turma(`is_ativa`, `is_cancelada`) AS `status`
		FROM
			`turma`
		GROUP BY `turma`;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_update_fornecedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_update_fornecedor`(
	nome_fan VARCHAR(25), tip VARCHAR(25), log VARCHAR(70), razao_so VARCHAR(35), data_fun DATE,
    seg VARCHAR(25), i_es VARCHAR(15), cnpJ VARCHAR(15), sit VARCHAR(50), tel VARCHAR(20),
    cel VARCHAR(20), ema VARCHAR(70), ende VARCHAR(55), comp VARCHAR(55), bai VARCHAR(55), 
    cid VARCHAR(55), uf VARCHAR(2), cep VARCHAR(10), id INT
)
BEGIN
	UPDATE `fornecedor` 
    INNER JOIN `endereco` ON (`fornecedor`.`id_endereco` = `endereco`.`id_endereco`)
    SET `nome_fantasia` = nome_fan, `tipo` = tip, `logo` = log, `razao_social` = razao_so,
		`data_fundacao` = data_fun, `segmento` = seg, `i_e` = i_es, `cpf_cnpj` = cnpJ, `site` = sit, 
		`telefone` = tel, `celular` = cel, `email` = ema, `endereco` = ende, `complemento` = comp, 
        `bairro` = bai, `cidade` = cid, `uf` = uf, `cep` = cep
	WHERE `fornecedor`.`id_fornecedor` = id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `boletim`
--

/*!50001 DROP TABLE IF EXISTS `boletim`*/;
/*!50001 DROP VIEW IF EXISTS `boletim`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=MERGE */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `boletim` AS select (case when (`a`.`pai_declarado` = 1) then `rp`.`nome` else 'Não declarado' end) AS `pai`,(case when (`t`.`modalidade` = 'Ensino Fundamental') then concat(`t`.`serie`,'ª série') when (`t`.`modalidade` = 'Ensino Médio') then concat(`t`.`serie`,'º ano') else `t`.`serie` end) AS `serie`,`MEDIA`(`n`.`1_avaliacao`,`n`.`2_avaliacao`,`n`.`3_avaliacao`,`n`.`4_avaliacao`,`n`.`1_recuperacao`,`n`.`2_recuperacao`) AS `media`,`SITUACAO`(`MEDIA`(`n`.`1_avaliacao`,`n`.`2_avaliacao`,`n`.`3_avaliacao`,`n`.`4_avaliacao`,`n`.`1_recuperacao`,`n`.`2_recuperacao`)) AS `situacao`,(case when (`n`.`1_avaliacao` <> 0) then `n`.`1_avaliacao` else '*' end) AS `1 avaliação`,(case when (`n`.`2_avaliacao` <> 0) then `n`.`2_avaliacao` else '*' end) AS `2 avaliação`,(case when (`n`.`3_avaliacao` <> 0) then `n`.`3_avaliacao` else '*' end) AS `3 avaliação`,(case when (`n`.`4_avaliacao` <> 0) then `n`.`4_avaliacao` else '*' end) AS `4 avaliação`,(case when (`n`.`1_recuperacao` = 0) then '*' else `n`.`1_recuperacao` end) AS `1 Recuperação`,(case when (`n`.`2_recuperacao` = 0) then '*' else `n`.`2_recuperacao` end) AS `2 Recuperação`,date_format(`a`.`data_nascimento`,'%d/%m/%Y') AS `data_nascimento`,ucase(`d`.`disciplina`) AS `disciplina`,`a`.`nome` AS `nome_aluno`,`a`.`sexo` AS `sexo`,`rm`.`nome` AS `mae`,`endereco`.`cidade` AS `cidade`,`endereco`.`uf` AS `uf`,`t`.`turma` AS `turma`,`t`.`modalidade` AS `modalidade`,`e`.`nome` AS `escola`,`ee`.`uf` AS `uf_escola`,`ee`.`cidade` AS `cidade_escola`,`a`.`matricula` AS `matricula`,`aht`.`ano` AS `ano` from ((((((((((((`aluno` `a` join `aluno_has_responsavel` `ahr` on((`a`.`id_aluno` = `ahr`.`id_aluno`))) join `responsavel` `rp` on(((`ahr`.`id_responsavel` = `rp`.`id_responsavel`) and ((`rp`.`parentesco` = 'Pai') or (`a`.`pai_declarado` = 0))))) join `aluno_has_responsavel` `ahr1` on((`ahr1`.`id_aluno` = `a`.`id_aluno`))) join `responsavel` `rm` on(((`rm`.`id_responsavel` = `ahr1`.`id_responsavel`) and (`rm`.`parentesco` = 'Mãe')))) join `endereco` on((`a`.`id_endereco` = `endereco`.`id_endereco`))) join `aluno_has_turma` `aht` on((`aht`.`id_aluno` = `a`.`id_aluno`))) join `turma` `t` on((`aht`.`id_turma` = `t`.`id_turma`))) join `nota` `n` on(((`t`.`id_turma` = `n`.`id_turma`) and (`a`.`id_aluno` = `n`.`id_aluno`)))) join `disciplina` `d` on((`d`.`id_disciplina` = `n`.`id_disciplina`))) join `turma_has_escola` `ahe` on((`ahe`.`id_turma` = `t`.`id_turma`))) join `escola` `e` on((`ahe`.`id_escola` = `e`.`id_escola`))) join `endereco` `ee` on((`e`.`id_endereco` = `ee`.`id_endereco`))) where ((`t`.`is_ativa` = 1) and (`a`.`is_ativo` = 1)) order by `d`.`disciplina` */
/*!50002 WITH LOCAL CHECK OPTION */;
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

-- Dump completed on 2017-02-08 17:01:25
