-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: botanica
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

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

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema botanica
-- -----------------------------------------------------

DROP SCHEMA IF EXISTS `botanica` ;

-- -----------------------------------------------------
-- Schema botanica
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `botanica` DEFAULT CHARACTER SET utf8 ;
USE `botanica` ;

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
-- Table structure for table `climas`
--

DROP TABLE IF EXISTS `climas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `climas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(256) DEFAULT NULL,
  primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


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
  `seguimientos_id` int(11) NOT NULL,
  `plantas_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_estados_seguimientos1_idx` (`seguimientos_id`),
  KEY `fk_estados_plantas1_idx` (`plantas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  primary key (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

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
  CONSTRAINT `fk_personas_user` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


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
  CONSTRAINT `fk_plantas_tempo` FOREIGN KEY (`temporadas_id`) REFERENCES `temporadas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


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
  CONSTRAINT `fk_respuestas_user` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`id`),
  KEY `fk_seguim_user_idx` (`usuario_id`),
  KEY `fk_seguim_planta_idx` (`planta_id`),
  CONSTRAINT `fk_seguim_planta` FOREIGN KEY (`planta_id`) REFERENCES `plantas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_seguim_user` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


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
  KEY `fk_tareas_etapas_idx` (`etapas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


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
  CONSTRAINT `fk_preguntas_user` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


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
  KEY `fk_usuarios_rol` (`roles_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
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
  CONSTRAINT `fk_ventas_user` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`, `descripcion`) VALUES
(1, 'ROLE_ADMIN', 'Administrador'),
(2, 'ROLE_USER', 'Usuario Comun'),
(3, 'ROLE_VENDEDOR', 'Usuario Vendedor');


--
-- Table structure for table `usuarios`
--
INSERT INTO `usuarios` (`id`, `password`, `user`, `email`, `roles_id`) VALUES
(1, '1234', 'day', 'day@gmail',2),
(2, '2345', 'perlis','perlis@gmail',1),
(3, '3456', 'tincho','tincho@gmail',2),
(4, '7890', 'mati', 'mati@gmail',3),
(5, '0123', 'lea', 'lea@gmail',2),
(6, '4567', 'alvarito','alvarito@gmail',3),
(7, '4567', 'rami','rami@gmail',2);


--
-- Volcado de datos para la tabla `personas`
--
INSERT INTO `personas` (`id`, `apellido`, `nombre`, `fecha_nacimiento`, `usuarios_id`) VALUES
(1,'ascurra', 'daiana', '12-12-1989', null),
(2,'padilla', 'perla', '15-01-1995', null),
(3,'rojo', 'martin', '1-01-1995', null),
(4,'romani', 'matias', '15-02-1995', null),
(5,'nosecomoseescribe', 'alvaro', '15-01-1999', null),
(6,'sabio', 'leandro', '5-10-1995', null),
(7,'giandinoto', 'ramiro', '10-10-1988', null);

--
-- Volcado de datos para la tabla `suelos`
--
INSERT INTO `suelos` (`id`, `nombre`, `descripcion`) VALUES
(1, 'arcilloso', 'Están compuestos en gran parte por el mineral conocido como arcilla. Esto los convierte en un suelo de textura pesada, pegajoso cuando está húmedo y muy duro ante carencia de agua. Controlar el riego; suele podrir las raíces. '),
(2, 'arenoso', 'Formados por arena;micropartículas de silicio. Son suelos muy drenantes, con poca o nula capacidad de retención de humedad y por lo tanto secos. Magros en cuanto a los nutrientes que aportan.  '),
(3, 'calcáreo', 'Estos suelos tienen gran cantidad de sales calcáreas. Suelen ser de color blanco, seco y árido. Al ser tan dura no favorece la agricultura, puesto que las plantas no pueden absorber bien los nutrientes.'),
(4, 'humífero', 'Son las superficies que albergan abundante material orgánico en descomposición y ya descompuesto. La palabra humífero infiere que el suelo es abundante en humus, sustancia compuesta por productos orgánicos de origen coloidal.\r\n\r\n');


--
-- Volcado de datos para la tabla `temporadas`
--

INSERT INTO `temporadas` (`id`, `nombre`, `descripcion`) VALUES
(1, 'primavera', ''),
(2, 'verano', ''),
(3, 'otoño', ''),
(4, 'invierno', '');

--
-- Volcado de datos para la tabla `tipos_plantas`/
--

INSERT INTO `tipos_plantas` (`id`, `nombre`, `descripcion`) VALUES
(1, 'frutas', 'Aptos para consumo'),
(2, 'verduras y hortalizas', 'Aptos para consumo'),
(3, 'decorativas', 'Con flores, sin flores, para decorar espacios'),
(4, 'aromaticas y medicinales', 'Hierbas aromaticas aptas para consumo ideal para cocinar. Hierbas medicinales con propiedades curativas'),
(5, 'cactus y suculentas', 'Bulbosas'),
(6, 'acuaticas', 'Plantas adaptadas a los medios muy húmedos o acuáticos, incluso sin tierra');

--
-- Volcado de datos para la tabla `climas`
--

INSERT INTO `climas` (`id`, `nombre`, `descripcion`) VALUES
(1,'templado', 'El clima templado es un tipo de clima que se caracteriza por temperaturas medias anuales de alrededor de 15 °C y precipitaciones medias entre 600 mm y 2000 mm anuales.'),
(2,'arido', 'El clima árido es aquel donde las precipitaciones anuales son menores a los 400 mm y normalmente se ubica entre los 15º y 35º de latitud (proximidades a los trópicos de cada hemisferio). '),
(3,'tropical', 'El clima tropical se da en las zonas situadas en los trópicos. Se caracteriza por tener temperaturas elevadas y por la poca diferencia de éstas entre las estaciones del año, es decir, no tiene una verdadera estación fría en invierno.'),
(4,'mediterráneo', 'El clima mediterráneo está situado en los climas templados junto a otros como el chino o pampeano y el oceánico. Se caracteriza por inviernos templados y lluviosos y veranos secos y calurosos, con otoños y primaveras variables.');


--
-- Volcado de datos para la tabla `plantas`
--

INSERT INTO `plantas` (`id`, `nombre`, `suelos_id`, `climas_id`, `temporadas_id`, `descripcion`, `tiempo_riego`, `tipo_planta_id`) VALUES
(1, 'frutillas', 1, 1, 1, 'fruto rojo pequeño','tiempo riego', 1),
(2, 'limonero', 2, 3, 1, 'planta de tipo arbol', null, 1),
(3, 'acelga', 1, 2, 3, 'planta de hojas verdes pequeña', null, 2),
(4, 'SOLANUM LYCOPERSICUM/ tomate "perita"', 1, 1, 3, 'Es una planta anual, pero a veces puede perdurar más de un año en el terreno.\r\n\r\nLos tallos son ligeramente angulosos, semileñosos, de grosor mediano (cercano a 4 cm en la base) y con tricomas simples y glandulares.\r\n\r\nHojas de tamaño medio a grande (10 a 50 cm), alternas, pecioladas, bipinatisectas (con folíolos a su vez divididos) y con numerosos tricomas simples y glandulares.\r\nNecesitan mucho sol.', null, 2),
(5, 'lavanda', 2, 2, 3, 'planta de tipo arbusto mediana', null, 3),
(6, 'petunia multiflora', 4, 2, 1, 'Son originarias de Brasil. Son plantas perennes de escasa estatura, aproximadamente entre 15 y 60 centímetros. Desde principios de primavera hasta los finales de otoño, la floración se produce de manera abundante. ', null, 3),
(7, 'echinocactus grusonii / asiento de suegra', 2, 2, 3, 'Suculenta, puede llegar a medir 80 cm de ancho y diámetro (15 cm a los 10 años) y es de crecimiento lento. Florece cuando es adulto y sólo en verano por 3 días. Requiere mucho sol y poca agua. ',null, 4),
(8, 'epipremnum pinnatum / potus colgante', 1, 2, 3, 'Conocido como "potus", es originario del sudeste asiático. Liana que puede alcanzar 20 m de alto, con tallos de hasta 4 cm de diámetro. Trepa mediante raíces aéreas que se enganchan a las ramas de los árboles. Las hojas son perennes, alternas y acorazonadas, enteras en las plantas jóvenes, pero irregularmente pinnadas en las maduras y de hasta 1 m de largo por 45 cm de ancho (en las plantas jóvenes no superan los 20 cm de largo). Crecimiento rápido. Abundante agua y sombra luminosa. necesita buen drenaje. ', null, 4),
(9, 'albaca', 4, 3, 1, 'aromatica para ensaladas', null, 5),
(10, 'ruda', 1, 2, 2, 'aromatica con propiedades digestivas', null, 5),
(11, 'menta', 1, 2, 1, 'pequeñas hojas con propiedades digestivas', null, 5),
(12, 'aloe variegata/aloe tigre', 1, 2, 2, 'Pequeñas plantas suculentas de fácil cultivo, sin tallo o con uno muy corto y que alcanzan los 20-30 cm de altura. Las hojas carnosas y lanceoladas surgen en roseta, son de color verde oscuro con bandas transversales y borde blanco con pequeños dientes. Las flores se presentan en inflorescencias ramificadas, son de color rojo o rosa y de forma tubular. Florecen en la segunda mitad del invierno. Son muy sensibles al exceso de humedad que pudre rápidamente la planta;pueden ser víctimas de cochinillas.', null,5),
(13, 'junco', 1, 2, 4, 'planca acuatica', null, 6),
(14, 'flor de loto', 4, 2, 4, 'acuatica', null, 6);


--
-- Volcado de datos para la tabla `estados`
--
INSERT INTO `estados` (`id`, `nombre`, `descripcion`) VALUES
(1,'sana', 'La planta se encuentra en optimas condiciones para continuar su desarrollo'),
(2,'enferma', 'La planta se encuentra con problemas que todavia se pueden resolver antes de cancelar su seguimiento'),
(3,'muerta', 'La planta se encuentra en un estado irreversible,ya no se puede continuar con su desarrollo');


--
-- Volcado de datos para la tabla `etapas`
--
INSERT INTO `etapas` (`id`, `nombre`, `descripcion`) VALUES
(1,'inicio', ''),
(2,'transplante', ''),
(3,'finalizado', ''),
(4,'poda', ''),
(5,'cosecha', '');


--
-- Volcado de datos para la tabla `tareas`
--

INSERT INTO `tareas` (`id`, `nombre`, `etapas_id`, `descripcion`) VALUES
(1,'preparar suelo',1, 'Preparar todo el terreno / maceta donde será plantada la planta'),
(2,'transplantar',2, 'Cambiar la planta de contenedor, por ejemplo pasar de maceta a tierra'),
(3,'podar',4, 'Se cortan los brotes viejos para darle paso a los mas nuevos'),
(4,'cosechar',5, 'Obtencios de los frutos que pueda dar la planta'),
(5,'regar',1, 'Regar'),
(6,'regar',2, 'Regar'),
(7,'regar',3, 'Regar'),
(8,'regar',4, 'Regar'),
(9,'regar',5, 'Regar'),
(10,'abonar',1, 'Cambiar la tierra o agregar abono o algun tipo de fertilizante que ayude a su crecimiento'),
(11,'abonar',2, 'Cambiar la tierra o agregar abono o algun tipo de fertilizante que ayude a su crecimiento');


--
-- Volcado de datos para la tabla `seguimientos`
--

INSERT INTO `seguimientos` (`id`, `usuario_id`, `planta_id`, `ultimo_riego`, `estado_id`, `proximo_riego`) VALUES
(null, 1, 1, '', 1, ''),
(null, 1, 1, '', 1, '');

--
-- Filtros para la tabla `personas`
--
ALTER TABLE `personas`
  ADD CONSTRAINT `fk_personas_usuarios` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `plantas`
--
ALTER TABLE `plantas`
  ADD CONSTRAINT `fk_plantas_climas` FOREIGN KEY (`climas_id`) REFERENCES `climas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_plantas_suelo` FOREIGN KEY (`suelos_id`) REFERENCES `suelos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_plantas_tiempo` FOREIGN KEY (`temporadas_id`) REFERENCES `temporadas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_plantas_tiposPl` FOREIGN KEY (`tipo_planta_id`) REFERENCES `tipos_plantas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD CONSTRAINT `fk_respuestas_preguntas` FOREIGN KEY (`temas_id`) REFERENCES `temas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_respuestas_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `seguimientos`
--
ALTER TABLE `seguimientos`
  ADD CONSTRAINT `fk_seguimiento_estados` FOREIGN KEY (`estado_id`) REFERENCES `estados` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_seguimiento_planta` FOREIGN KEY (`planta_id`) REFERENCES `plantas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_seguimiento_user` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tareas`
--
ALTER TABLE `tareas`
  ADD CONSTRAINT `fk_tareas_estados` FOREIGN KEY (`etapas_id`) REFERENCES `etapas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tareas_etapas` FOREIGN KEY (`etapas_id`) REFERENCES `etapas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
/*  ADD CONSTRAINT `fk_tareas_plantas` FOREIGN KEY (`plantas_id`) REFERENCES `plantas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;*/

--
-- Filtros para la tabla `temas`
--
ALTER TABLE `temas`
  ADD CONSTRAINT `fk_preguntas_categ` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_preguntas_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_usuarios_rol` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `fk_ventas_tipos` FOREIGN KEY (`tipo_venta_id`) REFERENCES `tipos_ventas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ventas_usuario` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
/*fin datos de ramiro*/


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;