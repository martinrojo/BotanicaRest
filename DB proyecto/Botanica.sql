-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: botanica
-- ------------------------------------------------------
-- Server version	5.6.17

use botanica;

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(120) NOT NULL,
  `descripcion` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `climas`
--

DROP TABLE IF EXISTS `climas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `climas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `climas`
--

LOCK TABLES `climas` WRITE;
/*!40000 ALTER TABLE `climas` DISABLE KEYS */;
INSERT INTO `climas` VALUES (1,'polar','El clima polar o gélido se caracteriza por tener casi permanentemente temperaturas por debajo de 0 °C; y escasas precipitaciones. La humedad en el aire es inexistente y el viento suele ser bastante intenso, lo que hace muy malas las condiciones de vida.'),(2,'templado','El clima templado es un tipo de clima que se caracteriza por temperaturas medias anuales de alrededor de 15 °C y precipitaciones medias entre 600 mm y 2000 mm anuales.'),(3,'arido','El clima árido es aquel donde las precipitaciones anuales son menores a los 400 mm y normalmente se ubica entre los 15º y 35º de latitud (proximidades a los trópicos de cada hemisferio). '),(4,'tropical','El clima tropical se da en las zonas situadas en los trópicos. Se caracteriza por tener temperaturas elevadas y por la poca diferencia de éstas entre las estaciones del año, es decir, no tiene una verdadera estación fría en invierno.  '),(5,'mediterráneo','El clima mediterráneo está situado en los climas templados junto a otros como el chino o pampeano y el oceánico. Se caracteriza por inviernos templados y lluviosos y veranos secos y calurosos, con otoños y primaveras variables.'),(6,'montaña','Se caracteriza por unos inviernos fríos y largos con temperaturas negativas, y veranos frescos y cortos. Las precipitaciones son muy escasas en forma de lluvia en primavera, verano, de nieve en invierno y otoño en zonas templadas.');
/*!40000 ALTER TABLE `climas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(512) DEFAULT NULL,
  `etapas_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_estados_etapas1_idx` (`etapas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (1,'sana','La planta se encuentra en optimas condiciones para continuar su desarrollo',0),(2,'enferma','La planta se encuentra con problemas que todavia se pueden resolver antes de cancelar su seguimiento',0),(3,'muerta','La planta se encuentra en un estado irreversible,ya no se puede continuar con su desarrollo',0);
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etapas`
--

DROP TABLE IF EXISTS `etapas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `etapas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(512) DEFAULT NULL,
  `tarea_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tarea_id` (`tarea_id`),
  CONSTRAINT `fk_tarea_id` FOREIGN KEY (`tarea_id`) REFERENCES `tareas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etapas`
--

LOCK TABLES `etapas` WRITE;
/*!40000 ALTER TABLE `etapas` DISABLE KEYS */;
INSERT INTO `etapas` VALUES (1,'inicio','',0),(2,'transplante','',0),(3,'finalizado','',0),(4,'poda','',0),(5,'cosecha','',0);
/*!40000 ALTER TABLE `etapas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `usuarios_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_personas_user_idx` (`usuarios_id`),
  CONSTRAINT `fk_personas_user` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_personas_usuarios` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (1,'ascurra','daiana','0000-00-00',1),(2,'padilla','perla','0000-00-00',2),(3,'rojo','martin','0000-00-00',3),(4,'romani','matias','0000-00-00',4),(5,'nosecomoseescribe','alvaro','0000-00-00',5),(6,'sabio','leandro','0000-00-00',6),(7,'giandinoto','ramiro','0000-00-00',7);
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plantas`
--

DROP TABLE IF EXISTS `plantas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plantas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `suelos_id` int(11) NOT NULL,
  `climas_id` int(11) NOT NULL,
  `temporadas_id` int(11) NOT NULL,
  `descripcion` varchar(512) DEFAULT NULL,
  `tiempo_riego` time DEFAULT NULL,
  `tipo_planta_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_plantas_clima_idx` (`climas_id`),
  KEY `fk_plantas_suelos_idx` (`suelos_id`),
  KEY `fk_plantas_tempo_idx` (`temporadas_id`),
  KEY `fk_plantas_tiposPl_idx` (`tipo_planta_id`),
  CONSTRAINT `fk_plantas_clima` FOREIGN KEY (`climas_id`) REFERENCES `climas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_plantas_suelos` FOREIGN KEY (`suelos_id`) REFERENCES `suelos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_plantas_tempo` FOREIGN KEY (`temporadas_id`) REFERENCES `temporadas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_plantas_climas` FOREIGN KEY (`climas_id`) REFERENCES `climas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_plantas_suelo` FOREIGN KEY (`suelos_id`) REFERENCES `suelos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_plantas_tiempo` FOREIGN KEY (`temporadas_id`) REFERENCES `temporadas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_plantas_tiposPl` FOREIGN KEY (`tipo_planta_id`) REFERENCES `tipos_plantas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plantas`
--

LOCK TABLES `plantas` WRITE;
/*!40000 ALTER TABLE `plantas` DISABLE KEYS */;
INSERT INTO `plantas` VALUES (1,'frutillas',1,2,1,'fruto rojo pequeño','00:00:00',1),(2,'limonero',1,2,1,'planta de tipo arbol','00:00:00',1),(3,'acelga',3,2,1,'planta de hojas verdes pequeña','00:00:00',2),(4,'SOLANUM LYCOPERSICUM/ tomate \"perita\"',3,3,3,'Es una planta anual, pero a veces puede perdurar más de un año en el terreno.\r\n\r\nLos tallos son ligeramente angulosos, semileñosos, de grosor mediano (cercano a 4 cm en la base) y con tricomas simples y glandulares.\r\n\r\nHojas de tamaño medio a grande (10 a 50 cm), alternas, pecioladas, bipinatisectas (con folíolos a su vez divididos) y con numerosos tricomas simples y glandulares.\r\nNecesitan mucho sol.','00:00:00',1),(5,'plantaslavanda',1,1,1,'planta de tipo arbusto mediana','00:00:00',3),(6,'petunia multiflora',2,2,2,'Son originarias de Brasil. Son plantas perennes de escasa estatura, aproximadamente entre 15 y 60 centímetros. Desde principios de primavera hasta los finales de otoño, la floración se produce de manera abundante. ','00:00:00',3),(7,'echinocactus grusonii / asiento de suegra',2,1,1,'Suculenta, puede llegar a medir 80 cm de ancho y diámetro (15 cm a los 10 años) y es de crecimiento lento. Florece cuando es adulto y sólo en verano por 3 días. Requiere mucho sol y poca agua. ','00:00:00',5),(8,'epipremnum pinnatum / potus colgante',1,2,2,'Conocido como \"potus\", es originario del sudeste asiático. Liana que puede alcanzar 20 m de alto, con tallos de hasta 4 cm de diámetro. Trepa mediante raíces aéreas que se enganchan a las ramas de los árboles. Las hojas son perennes, alternas y acorazonadas, enteras en las plantas jóvenes, pero irregularmente pinnadas en las maduras y de hasta 1 m de largo por 45 cm de ancho (en las plantas jóvenes no superan los 20 cm de largo). Crecimiento rápido. Abundante agua y sombra luminosa. necesita buen drenaje. ','00:00:00',6),(9,'albaca',1,2,3,'aromatica para ensaladas','00:00:00',4),(10,'ruda',2,4,5,'aromatica con propiedades digestivas','00:00:00',4),(11,'menta',2,1,2,'pequeñas hojas con propiedades digestivas','00:00:00',4),(12,'aloe variegata/aloe tigre',1,2,3,'Pequeñas plantas suculentas de fácil cultivo, sin tallo o con uno muy corto y que alcanzan los 20-30 cm de altura. Las hojas carnosas y lanceoladas surgen en roseta, son de color verde oscuro con bandas transversales y borde blanco con pequeños dientes. Las flores se presentan en inflorescencias ramificadas, son de color rojo o rosa y de forma tubular. Florecen en la segunda mitad del invierno. Son muy sensibles al exceso de humedad que pudre rápidamente la planta;pueden ser víctimas de cochinillas.','00:00:00',5),(13,'junco',1,2,3,'planca acuatica','00:00:00',6),(14,'flor de loto',1,2,3,'acuatica','00:00:00',6);
/*!40000 ALTER TABLE `plantas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuestas`
--

DROP TABLE IF EXISTS `respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `respuestas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `temas_id` int(11) DEFAULT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_respuestas_preg_idx` (`temas_id`),
  KEY `fk_respuestas_user_idx` (`usuario_id`),
  CONSTRAINT `fk_respuestas_preg` FOREIGN KEY (`temas_id`) REFERENCES `temas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_respuestas_user` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_respuestas_preguntas` FOREIGN KEY (`temas_id`) REFERENCES `temas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_respuestas_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuestas`
--

LOCK TABLES `respuestas` WRITE;
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
/*!40000 ALTER TABLE `respuestas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rol` varchar(50) DEFAULT NULL,
  `descripcion` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN','Administrador'),(2,'ROLE_USER','Usuario Comun'),(3,'ROLE_VENDEDOR','Usuario Vendedor');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seguimientos`
--

DROP TABLE IF EXISTS `seguimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seguimientos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) NOT NULL,
  `planta_id` int(11) NOT NULL,
  `ultimo_riego` datetime DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL,
  `proximo_riego` datetime DEFAULT NULL,
  `etapa_id` int(11) DEFAULT NULL,
  `tarea_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_seguim_user_idx` (`usuario_id`),
  KEY `fk_seguim_planta_idx` (`planta_id`),
  KEY `fk_seguimiento_estados` (`estado_id`),
  CONSTRAINT `fk_seguim_planta` FOREIGN KEY (`planta_id`) REFERENCES `plantas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_seguim_user` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_seguimiento_estados` FOREIGN KEY (`estado_id`) REFERENCES `estados` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_seguimiento_planta` FOREIGN KEY (`planta_id`) REFERENCES `plantas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_seguimiento_user` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seguimientos`
--

LOCK TABLES `seguimientos` WRITE;
/*!40000 ALTER TABLE `seguimientos` DISABLE KEYS */;
INSERT INTO `seguimientos` VALUES (1,1,6,'0000-00-00 00:00:00',1,'0000-00-00 00:00:00','',''),
(2,2,4,'0000-00-00 00:00:00',1,'0000-00-00 00:00:00','','');
/*!40000 ALTER TABLE `seguimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suelos`
--

DROP TABLE IF EXISTS `suelos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suelos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suelos`
--

LOCK TABLES `suelos` WRITE;
/*!40000 ALTER TABLE `suelos` DISABLE KEYS */;
INSERT INTO `suelos` VALUES (1,'arcilloso','Están compuestos en gran parte por el mineral conocido como arcilla. Esto los convierte en un suelo de textura pesada, pegajoso cuando está húmedo y muy duro ante carencia de agua. Controlar el riego; suele podrir las raíces. '),(2,'arenoso','Formados por arena;micropartículas de silicio. Son suelos muy drenantes, con poca o nula capacidad de retención de humedad y por lo tanto secos. Magros en cuanto a los nutrientes que aportan.  '),(3,'calcáreo','Estos suelos tienen gran cantidad de sales calcáreas. Suelen ser de color blanco, seco y árido. Al ser tan dura no favorece la agricultura, puesto que las plantas no pueden absorber bien los nutrientes.'),(4,'humífero','Son las superficies que albergan abundante material orgánico en descomposición y ya descompuesto. La palabra humífero infiere que el suelo es abundante en humus, sustancia compuesta por productos orgánicos de origen coloidal.\r\n\r\n');
/*!40000 ALTER TABLE `suelos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tareas`
--

DROP TABLE IF EXISTS `tareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tareas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `etapas_id` int(11) NOT NULL,
  `descripcion` varchar(512) NOT NULL,
  PRIMARY KEY (`id`,`etapas_id`),
  KEY `fk_tareas_etapas_idx` (`etapas_id`),
  CONSTRAINT `fk_tareas_estados` FOREIGN KEY (`etapas_id`) REFERENCES `etapas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tareas_etapas` FOREIGN KEY (`etapas_id`) REFERENCES `etapas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tareas`
--

LOCK TABLES `tareas` WRITE;
/*!40000 ALTER TABLE `tareas` DISABLE KEYS */;
INSERT INTO `tareas` VALUES (2,'preparar suelo',1,'Preparar todo el terreno / maceta donde será plantada la planta'),(3,'transplantar',2,'Cambiar la planta de contenedor, por ejemplo pasar de maceta a tierra'),(4,'podar',4,'Se cortan los brotes viejos para darle paso a los mas nuevos'),(5,'cosechar',5,'Obtencios de los frutos que pueda dar la planta'),(6,'regar',1,'Regar'),(7,'regar',2,'Regar'),(8,'regar',3,'Regar'),(9,'regar',4,'Regar'),(10,'regar',5,'Regar'),(11,'abonar',1,'Cambiar la tierra o agregar abono o algun tipo de fertilizante que ayude a su crecimiento'),(12,'abonar',2,'Cambiar la tierra o agregar abono o algun tipo de fertilizante que ayude a su crecimiento');
/*!40000 ALTER TABLE `tareas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temas`
--

DROP TABLE IF EXISTS `temas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temas` (
  `id` int(11) NOT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `cerrado` tinyint(1) DEFAULT NULL,
  `texto` varchar(256) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_preguntas_user_idx` (`usuario_id`),
  KEY `fk_preguntas_temas_idx` (`categoria_id`),
  CONSTRAINT `fk_preguntas_user` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_preguntas_categ` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_preguntas_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temas`
--

LOCK TABLES `temas` WRITE;
/*!40000 ALTER TABLE `temas` DISABLE KEYS */;
/*!40000 ALTER TABLE `temas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temporadas`
--

DROP TABLE IF EXISTS `temporadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temporadas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temporadas`
--

LOCK TABLES `temporadas` WRITE;
/*!40000 ALTER TABLE `temporadas` DISABLE KEYS */;
INSERT INTO `temporadas` VALUES (1,'primavera',''),(2,'verano',''),(3,'otoño',''),(4,'invierno','');
/*!40000 ALTER TABLE `temporadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_plantas`
--

DROP TABLE IF EXISTS `tipos_plantas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_plantas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_plantas`
--

LOCK TABLES `tipos_plantas` WRITE;
/*!40000 ALTER TABLE `tipos_plantas` DISABLE KEYS */;
INSERT INTO `tipos_plantas` VALUES (1,'frutas','Aptos para consumo'),(2,'verduras y hortalizas','Aptos para consumo'),(3,'decorativas','Con flores, sin flores, para decorar espacios'),(4,'aromaticas y medicinales','Hierbas aromaticas aptas para consumo ideal para cocinar. Hierbas medicinales con propiedades curativas'),(5,'cactus y suculentas','Bulbosas'),(6,'acuaticas','Plantas adaptadas a los medios muy húmedos o acuáticos, incluso sin tierra');
/*!40000 ALTER TABLE `tipos_plantas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_ventas`
--

DROP TABLE IF EXISTS `tipos_ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_ventas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_ventas`
--

LOCK TABLES `tipos_ventas` WRITE;
/*!40000 ALTER TABLE `tipos_ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipos_ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8 NOT NULL,
  `user` varchar(16) CHARACTER SET utf8 NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `roles_id` int(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UNIQUE` (`user`),
  KEY `fk_usuarios_rol` (`roles_id`),
  CONSTRAINT `fk_usuarios_rol` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'1234','day','day@gmail',2),(2,'2345','perlis','perlis@gmail',1),(3,'3456','tincho','tincho@gmail',2),(4,'7890','mati','mati@gmail',3),(5,'0123','lea','lea@gmail',2),(6,'4567','alvarito','alvarito@gmail',3),(7,'4567','rami','rami@gmail',2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `producto` varchar(45) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `usuarios_id` int(11) DEFAULT NULL,
  `cerrado` tinyint(1) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `tipo_venta_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ventas_tipos_idx` (`tipo_venta_id`),
  KEY `fk_ventas_user_idx` (`usuarios_id`),
  CONSTRAINT `fk_ventas_user` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_tipos` FOREIGN KEY (`tipo_venta_id`) REFERENCES `tipos_ventas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_usuario` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-26 18:27:08
