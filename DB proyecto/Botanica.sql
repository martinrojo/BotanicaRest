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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Consejos Utiles','Tips y consejos en general para mantener tu jardin en orden y siempre bello'),(2,'Posibles Enfermedades','Algunas enfermedades comunes con las que nos encontramos a la hora de cuidar nuestros cultivos'),(3,'Pesticidas','Qué son, para qué y cómo se usan. Algunos de los mas utilizados.'),(4,'Cómo y Cuándo Podar','Consejos de poda. Cómo y cuándo podar. Beneficios de poda en las plantas');
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (1,'sana','La planta se encuentra en optimas condiciones para continuar su desarrollo'),(2,'enferma','La planta se encuentra con problemas que todavia se pueden resolver antes de cancelar su seguimiento'),(3,'muerta','La planta se encuentra en un estado irreversible,ya no se puede continuar con su desarrollo');
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
  `estados_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`estados_id`),
  KEY `fk_etapas_estados1_idx` (`estados_id`),
  CONSTRAINT `fk_etapas_estdos` FOREIGN KEY (`estados_id`) REFERENCES `estados` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etapas`
--

LOCK TABLES `etapas` WRITE;
/*!40000 ALTER TABLE `etapas` DISABLE KEYS */;
INSERT INTO `etapas` VALUES (1,'inicio','',1),(2,'transplante','',1),(3,'finalizado','',1),(4,'poda','',1),(5,'cosecha','',1),(6,'fumigar','',2),(7,'quitar larvas e insectos','',2),(8,'reciclar maceta','Sentimos lo sucedido',3);
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
  CONSTRAINT `fk_personas_user` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (1,'ascurra','daiana','1989-12-12',1),(2,'padilla','perla','1995-01-15',2),(3,'rojo','martin','1996-01-13',3),(4,'romani','matias','1992-02-15',4),(5,'nosecomoseescribe','alvaro','1990-03-05',5),(6,'sabio','leandro','1995-05-12',6),(7,'giandinoto','ramiro','1989-10-20',7),(8,'Bruseghini','Alvaro','1992-11-22',8),(9,'Bruseghini','Alvaro','1992-11-22',9);
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
  CONSTRAINT `fk_plantas_tipoPlan` FOREIGN KEY (`tipo_planta_id`) REFERENCES `tipos_plantas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plantas`
--

LOCK TABLES `plantas` WRITE;
/*!40000 ALTER TABLE `plantas` DISABLE KEYS */;
INSERT INTO `plantas` VALUES (1,'frutillas',1,2,1,'fruto rojo pequeño','10:00:00',1),(2,'limonero',1,2,1,'planta de tipo arbol','05:00:00',1),(3,'acelga',3,2,1,'planta de hojas verdes pequeña','07:00:00',2),(4,'SOLANUM LYCOPERSICUM/ tomate \"perita\"',3,3,3,'Es una planta anual, pero a veces puede perdurar más de un año en el terreno.\r\n\r\nLos tallos son ligeramente angulosos, semileñosos, de grosor mediano (cercano a 4 cm en la base) y con tricomas simples y glandulares.\r\n\r\nHojas de tamaño medio a grande (10 a 50 cm), alternas, pecioladas, bipinatisectas (con folíolos a su vez divididos) y con numerosos tricomas simples y glandulares.\r\nNecesitan mucho sol.','05:00:00',1),(5,'plantaslavanda',1,1,1,'planta de tipo arbusto mediana','07:00:00',3),(6,'petunia multiflora',2,2,2,'Son originarias de Brasil. Son plantas perennes de escasa estatura, aproximadamente entre 15 y 60 centímetros. Desde principios de primavera hasta los finales de otoño, la floración se produce de manera abundante. ','05:00:00',3),(7,'echinocactus grusonii / asiento de suegra',2,1,1,'Suculenta, puede llegar a medir 80 cm de ancho y diámetro (15 cm a los 10 años) y es de crecimiento lento. Florece cuando es adulto y sólo en verano por 3 días. Requiere mucho sol y poca agua. ','05:00:00',5),(8,'epipremnum pinnatum / potus colgante',1,2,2,'Conocido como \"potus\", es originario del sudeste asiático. Liana que puede alcanzar 20 m de alto, con tallos de hasta 4 cm de diámetro. Trepa mediante raíces aéreas que se enganchan a las ramas de los árboles. Las hojas son perennes, alternas y acorazonadas, enteras en las plantas jóvenes, pero irregularmente pinnadas en las maduras y de hasta 1 m de largo por 45 cm de ancho (en las plantas jóvenes no superan los 20 cm de largo). Crecimiento rápido. Abundante agua y sombra luminosa. necesita buen drenaje. ','07:00:00',6),(9,'albaca',1,2,3,'aromatica para ensaladas','05:00:00',4),(10,'ruda',2,4,1,'aromatica con propiedades digestivas','10:00:00',4),(11,'menta',2,1,2,'pequeñas hojas con propiedades digestivas','07:00:00',4),(12,'aloe variegata/aloe tigre',1,2,3,'Pequeñas plantas suculentas de fácil cultivo, sin tallo o con uno muy corto y que alcanzan los 20-30 cm de altura. Las hojas carnosas y lanceoladas surgen en roseta, son de color verde oscuro con bandas transversales y borde blanco con pequeños dientes. Las flores se presentan en inflorescencias ramificadas, son de color rojo o rosa y de forma tubular. Florecen en la segunda mitad del invierno. Son muy sensibles al exceso de humedad que pudre rápidamente la planta;pueden ser víctimas de cochinillas.','05:00:00',5),(13,'junco',1,2,3,'planca acuatica','10:00:00',6),(14,'flor de loto',1,2,3,'acuatica','07:00:00',6);
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
  KEY `fk_respuestas_user_idx` (`usuario_id`),
  KEY `fk_respuestas_temas_idx` (`temas_id`),
  CONSTRAINT `fk_respuestas_temas` FOREIGN KEY (`temas_id`) REFERENCES `temas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_respuestas_user` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuestas`
--

LOCK TABLES `respuestas` WRITE;
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
INSERT INTO `respuestas` VALUES (1,1,'Hay que sacar los brotes mas pequeños para darle fuerzas a los que quedan. Tener en cunata la direccion que quieras darle a tu planta por ejemplo si es una enredadera para donde quieres que se vaya?',2,'2018-03-05 07:02:55'),(2,2,'En producción forestal se emplea para obtener fustes más rectos y con menos ramificaciones, por tanto de mayor calidad. En arbolado urbano para prevenir el riesgo de caída de ramas y controlar el tamaño de árboles.En jardinería,se utiliza la poda para con',5,'2018-03-05 07:02:55'),(3,3,'revisaste que la maceta tenga buen drenaje? si no lo tiene asi lo riegues poco se pudren las raices, yo que tu sacaria esquejes hoy mismo.si es asi y aun hay raices salvables, es decir, que no esten negras y blanditas, cambiale la tierra a una que este se',3,'2018-03-05 07:02:55'),(4,3,'En casa tengo un potus y lo mantengo \"redondito\" cuando larga guías las corto y hago plantas nuevas que mantengo sólo en agua durante años, creciendo hermosas. Unicamente les pongo de vez en cuando un pedacito de carbón de leña que me dijeron que les dá t',2,'2018-03-05 07:02:55'),(5,3,'puede ser la calefaccion, aca no usamos eso porq el clima es caliente pero a si vez es bastante humedo,el problema de la calefaccion es que seca los ambientes, si la tienes cerca de la calefaccion puede ser eso',1,'2018-03-05 07:02:55'),(6,4,'De entrada decirte que no conozco la marca de producto que comentas, pero si te puedo confirmar que para luchar contra la cochinilla no necesitas un fungicida, sino un insecticida. Lo puedes adquirir en tu centro de productos fitosanitarios o invernadero ',4,'2018-03-05 07:02:55'),(7,5,'Con estos pasos tendras resultados excelentes ->Para evitar que una enfermedad se introduzca al jardín, 1) inspeccione todas las plantas nuevas y cerciorese de que no tengan síntomas de la enfermedad (manchas foliares, raíces obscuras, etc.) o señas (crec',2,'2018-03-05 07:02:55'),(8,5,'Hola encontre informacion muy valiosa te conviene leerla.Mantenga el Jardín o Paisaje Limpio: Mantenga el paisaje libre de insectos o malezas. Si va a añadir materia orgánica al suelo, cerciorese de que este venga de un vendedor confiable. Cerciorese de q',2,'2018-03-05 07:02:55');
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
  `estado_id` int(11) DEFAULT NULL,
  `etapa_id` int(11) DEFAULT NULL,
  `tarea_id` int(11) DEFAULT NULL,
  `ultimo_riego` datetime DEFAULT NULL,
  `proximo_riego` datetime DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_abono` date DEFAULT NULL,
  `fecha_poda` date DEFAULT NULL,
  `fecha_cosecha` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_seguim_user_idx` (`usuario_id`),
  KEY `fk_seguim_planta_idx` (`planta_id`),
  KEY `fk_seguim_estados_idx` (`estado_id`),
  KEY `fk_seguim_etapa_idx` (`etapa_id`),
  KEY `fk_seguim_tarea_idx` (`tarea_id`),
  CONSTRAINT `fk_seguim_estados` FOREIGN KEY (`estado_id`) REFERENCES `estados` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_seguim_planta` FOREIGN KEY (`planta_id`) REFERENCES `plantas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_seguim_tarea` FOREIGN KEY (`tarea_id`) REFERENCES `tareas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_seguim_user` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seguimientos`
--

LOCK TABLES `seguimientos` WRITE;
/*!40000 ALTER TABLE `seguimientos` DISABLE KEYS */;
INSERT INTO `seguimientos` VALUES (1,1,6,1,1,1,'2017-10-05 05:00:00','2017-10-05 05:00:00',NULL,NULL,NULL,NULL),(2,2,4,1,1,1,'2017-10-05 05:00:00','2017-10-05 05:00:00',NULL,NULL,NULL,NULL),(3,8,1,1,3,8,'2018-03-04 02:52:14','2018-03-04 12:52:14','2018-02-02','2018-03-04','2019-03-04','2019-03-04');
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
  `descripcion` varchar(512) NOT NULL,
  `etapas_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`etapas_id`),
  KEY `fk_tareas_etapas_idx` (`etapas_id`),
  CONSTRAINT `fk_tareas_etapas` FOREIGN KEY (`etapas_id`) REFERENCES `etapas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tareas`
--

LOCK TABLES `tareas` WRITE;
/*!40000 ALTER TABLE `tareas` DISABLE KEYS */;
INSERT INTO `tareas` VALUES (1,'preparar suelo','Preparar todo el terreno / maceta donde será plantada la planta',1),(2,'sembrar','Deposite las semillas en el suelo que hemos preparado con anterioridad',1),(3,'transplantar','Cambiar la planta de contenedor, por ejemplo pasar de maceta a tierra',2),(4,'podar','Se cortan los brotes viejos para darle paso a los mas nuevos',4),(5,'cosechar','Obtencios de los frutos que pueda dar la planta',5),(6,'regar','Regar',1),(7,'regar','Regar',2),(8,'regar','Regar',3),(9,'regar','Regar',4),(10,'regar','Regar',5),(11,'abonar','Cambiar la tierra o agregar abono o algun tipo de fertilizante que ayude a su crecimiento',1),(12,'abonar','Cambiar la tierra o agregar abono o algun tipo de fertilizante que ayude a su crecimiento',2),(13,'abonar','Cambiar la tierra o agregar abono o algun tipo de fertilizante que ayude a su crecimiento',3);
/*!40000 ALTER TABLE `tareas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temas`
--

DROP TABLE IF EXISTS `temas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `cerrado` tinyint(1) DEFAULT NULL,
  `texto` varchar(256) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_preguntas_user_idx` (`usuario_id`),
  KEY `fk_preguntas_categ_idx` (`categoria_id`),
  CONSTRAINT `fk_preguntas_categ` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_preguntas_user` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temas`
--

LOCK TABLES `temas` WRITE;
/*!40000 ALTER TABLE `temas` DISABLE KEYS */;
INSERT INTO `temas` VALUES (0,NULL,NULL,NULL,NULL,NULL,NULL),(1,'Como podar mi planta?',1,0,'Hola mi planta tiene  muchos brotes algunos mas grandes que otros, cómo se cual es el que tengo que cortar??',4,'2018-03-05 07:02:55'),(2,'Para qué sirve Podar?',2,1,'para qué se tiene que podar?es necesario que lo haga?ayuda no tengo idea cómo se hace',4,'2018-03-05 07:02:55'),(3,'Potus se está muriendo',3,0,'Me regalaron un potus precioso, lo puse en una maceta mas grande, lo riego poco, pero se me han secado casi todas las hojas',2,'2018-03-05 07:02:55'),(4,'limonero 4 estaciones',4,0,'Cómo están?Estoy en Argentina. El limonero que tengo, (según me han dicho) tiene Cochilla. Este es el funjicida que me han recomendado, Glaco-Max. ¿Cosideran qué, es fectivo o sería mejor otro producto?',3,'2018-03-05 07:02:55'),(5,'Como puedo evitar que entren enfermedades?',5,0,'Logré despues de mucho tiempo el jardin de mis sueños y todas son sanas y fuertes, como puedo hacer para conservarlas asi y que ninguna enfermedad entre en él???',2,'2018-03-05 07:02:55');
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
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temporadas`
--

LOCK TABLES `temporadas` WRITE;
/*!40000 ALTER TABLE `temporadas` DISABLE KEYS */;
INSERT INTO `temporadas` VALUES (1,'primavera','','2018-09-21','2018-12-20'),(2,'verano','','2018-12-21','2019-03-22'),(3,'otoño','','2018-03-23','2018-06-20'),(4,'invierno','','2018-06-21','2018-09-20');
/*!40000 ALTER TABLE `temporadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_plantas`
--

DROP TABLE IF EXISTS `tipos_plantas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_plantas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_ventas`
--

LOCK TABLES `tipos_ventas` WRITE;
/*!40000 ALTER TABLE `tipos_ventas` DISABLE KEYS */;
INSERT INTO `tipos_ventas` VALUES (1,'TIPO_CONSERVAS','Conservas'),(2,'TIPO_VERDURAS','Verduras'),(3,'TIPO_FRUTAS','Furtas'),(4,'TIPO_HERRAMIENTAS','Herramientas'),(5,'TIPO_FERTILIZANTES','Fertilizantes'),(6,'TIPO_REMEDIOS','Remedios');
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
  `last_password_reset_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UNIQUE` (`user`),
  KEY `fk_usuarios_rol` (`roles_id`),
  CONSTRAINT `fk_usuarios_rol` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'1234','day','day@gmail',2,'2018-03-02'),(2,'2345','perlis','perlis@gmail',1,'2018-03-02'),(3,'3456','tincho','tincho@gmail',2,'2018-03-02'),(4,'7890','mati','mati@gmail',3,'2018-03-02'),(5,'0123','lea','lea@gmail',2,'2018-03-02'),(6,'4567','alvarito','alvarito@gmail',3,'2018-03-02'),(7,'4567','rami','rami@gmail',2,'2018-03-02'),(8,'$2a$10$X.a2LYY9A7zDRY0sDSyNXe0E0qmtNtKfIOdn2y.k0MCIGgxhMsyDi','Alvaro92','bruseghini_92@live.com.ar',2,'2018-03-01'),(9,'$2a$10$i1yHBZlFTKwUvIVWPcwPiOgeZaW8xqudKizT6CvjBuTxZB/s/hQ4.','Admin','bruseghini_92@live.com.ar',1,'2018-03-04');
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
  KEY `fk_ventas_user_idx` (`usuarios_id`),
  KEY `fk_ventas_tiposVen_idx` (`tipo_venta_id`),
  CONSTRAINT `fk_ventas_tiposVen` FOREIGN KEY (`tipo_venta_id`) REFERENCES `tipos_ventas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_user` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (1,'Conserva de frutillas','conserva de frutillas',1,1,'2017-09-20 00:00:00',1),(2,'Conserva de Cerezas','conserva de Cerezas',1,1,'2017-09-21 00:00:00',1);
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

-- Dump completed on 2018-03-04  5:55:54
