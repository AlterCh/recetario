-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.27 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;


-- Dumping database structure for recetario
DROP DATABASE IF EXISTS `recetario`;
CREATE DATABASE IF NOT EXISTS `recetario` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `recetario`;

-- Dumping structure for table recetario.categoria
DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria`
(
    `id`     varchar(255) NOT NULL,
    `nombre` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.categoria: ~0 rows (approximately)
/*!40000 ALTER TABLE `categoria`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria`
    ENABLE KEYS */;

-- Dumping structure for table recetario.foto
DROP TABLE IF EXISTS `foto`;
CREATE TABLE IF NOT EXISTS `foto`
(
    `id`        varchar(255) NOT NULL,
    `contenido` longblob,
    `mime`      varchar(255) DEFAULT NULL,
    `nombre`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.foto: ~0 rows (approximately)
/*!40000 ALTER TABLE `foto`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `foto`
    ENABLE KEYS */;

-- Dumping structure for table recetario.unidad
DROP TABLE IF EXISTS `unidad`;
CREATE TABLE IF NOT EXISTS `unidad`
(
    `id`          varchar(255) NOT NULL,
    `abrev`       varchar(255) DEFAULT NULL,
    `nombre`      varchar(255) DEFAULT NULL,
    `tipo_unidad` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.unidad: ~0 rows (approximately)
/*!40000 ALTER TABLE `unidad`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `unidad`
    ENABLE KEYS */;


-- Dumping structure for table recetario.producto
DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto`
(
    `id`        varchar(255) NOT NULL,
    `cantidad`  double       DEFAULT NULL,
    `nombre`    varchar(255) DEFAULT NULL,
    `precio`    double       DEFAULT NULL,
    `stock`     double       DEFAULT NULL,
    `unidad_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK61kvndtovjcqlv5l3eneanlyf` (`unidad_id`),
    CONSTRAINT `FK61kvndtovjcqlv5l3eneanlyf` FOREIGN KEY (`unidad_id`) REFERENCES `unidad` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.producto: ~0 rows (approximately)
/*!40000 ALTER TABLE `producto`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `producto`
    ENABLE KEYS */;

-- Dumping structure for table recetario.producto_categoria
DROP TABLE IF EXISTS `producto_categoria`;
CREATE TABLE IF NOT EXISTS `producto_categoria`
(
    `producto_id`  varchar(255) NOT NULL,
    `categoria_id` varchar(255) NOT NULL,
    UNIQUE KEY `UK_b57auyqnnjl53d4o9d7vhrpul` (`categoria_id`),
    KEY `FKh5teore15e6b0uytoftnh3qnd` (`producto_id`),
    CONSTRAINT `FKh5teore15e6b0uytoftnh3qnd` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
    CONSTRAINT `FKioqw5isra2i5o5qybtege3sgt` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.producto_categoria: ~0 rows (approximately)
/*!40000 ALTER TABLE `producto_categoria`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `producto_categoria`
    ENABLE KEYS */;

-- Dumping structure for table recetario.ingrediente
DROP TABLE IF EXISTS `ingrediente`;
CREATE TABLE IF NOT EXISTS `ingrediente`
(
    `id`          varchar(255) NOT NULL,
    `cantidad`    double       DEFAULT NULL,
    `producto_id` varchar(255) DEFAULT NULL,
    `unidades_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKpk4nqcpjkuib2owyk6482lyki` (`producto_id`),
    KEY `FK8hdsghrxof7rf6633jnnoih0e` (`unidades_id`),
    CONSTRAINT `FK8hdsghrxof7rf6633jnnoih0e` FOREIGN KEY (`unidades_id`) REFERENCES `unidad` (`id`),
    CONSTRAINT `FKpk4nqcpjkuib2owyk6482lyki` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.ingrediente: ~0 rows (approximately)
/*!40000 ALTER TABLE `ingrediente`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `ingrediente`
    ENABLE KEYS */;


-- Dumping structure for table recetario.provincia
DROP TABLE IF EXISTS `provincia`;
CREATE TABLE IF NOT EXISTS `provincia`
(
    `id`     varchar(255) NOT NULL,
    `nombre` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.provincia: ~0 rows (approximately)
/*!40000 ALTER TABLE `provincia`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `provincia`
    ENABLE KEYS */;


-- Dumping structure for table recetario.proveedor
DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE IF NOT EXISTS `proveedor`
(
    `id`           varchar(255) NOT NULL,
    `direccion`    varchar(255) DEFAULT NULL,
    `nombre`       varchar(255) DEFAULT NULL,
    `telefono`     varchar(255) DEFAULT NULL,
    `provincia_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK8lkocw3ukg3e6ub7f0uyr9tn2` (`provincia_id`),
    CONSTRAINT `FK8lkocw3ukg3e6ub7f0uyr9tn2` FOREIGN KEY (`provincia_id`) REFERENCES `provincia` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.proveedor: ~0 rows (approximately)
/*!40000 ALTER TABLE `proveedor`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor`
    ENABLE KEYS */;

-- Dumping structure for table recetario.proveedor_productos
DROP TABLE IF EXISTS `proveedor_productos`;
CREATE TABLE IF NOT EXISTS `proveedor_productos`
(
    `proveedor_id` varchar(255) NOT NULL,
    `productos_id` varchar(255) NOT NULL,
    UNIQUE KEY `UK_t5rtxk6xy1dqx0jfdvuxnqkqe` (`productos_id`),
    KEY `FKno025e9na9xlwauysxv1mywti` (`proveedor_id`),
    CONSTRAINT `FKno025e9na9xlwauysxv1mywti` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedor` (`id`),
    CONSTRAINT `FKwsh1qg7ro59u8piqbx8y0bpr` FOREIGN KEY (`productos_id`) REFERENCES `producto` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.proveedor_productos: ~0 rows (approximately)
/*!40000 ALTER TABLE `proveedor_productos`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor_productos`
    ENABLE KEYS */;


-- Dumping structure for table recetario.receta
DROP TABLE IF EXISTS `receta`;
CREATE TABLE IF NOT EXISTS `receta`
(
    `id`          varchar(255) NOT NULL,
    `descripcion` varchar(255) DEFAULT NULL,
    `nombre`      varchar(255) DEFAULT NULL,
    `porciones`   int          DEFAULT NULL,
    `tiempo`      int          DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.receta: ~0 rows (approximately)
/*!40000 ALTER TABLE `receta`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `receta`
    ENABLE KEYS */;

-- Dumping structure for table recetario.receta_categoria
DROP TABLE IF EXISTS `receta_categoria`;
CREATE TABLE IF NOT EXISTS `receta_categoria`
(
    `receta_id`    varchar(255) NOT NULL,
    `categoria_id` varchar(255) NOT NULL,
    UNIQUE KEY `UK_75nkta76my9sqbowh5h7916rc` (`categoria_id`),
    KEY `FKna1r6ohtelgxedx0bapogbapk` (`receta_id`),
    CONSTRAINT `FKaxu31d56jrivmntvcq7noec9i` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`),
    CONSTRAINT `FKna1r6ohtelgxedx0bapogbapk` FOREIGN KEY (`receta_id`) REFERENCES `receta` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.receta_categoria: ~0 rows (approximately)
/*!40000 ALTER TABLE `receta_categoria`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `receta_categoria`
    ENABLE KEYS */;

-- Dumping structure for table recetario.receta_ingredientes
DROP TABLE IF EXISTS `receta_ingredientes`;
CREATE TABLE IF NOT EXISTS `receta_ingredientes`
(
    `receta_id`       varchar(255) NOT NULL,
    `ingredientes_id` varchar(255) NOT NULL,
    UNIQUE KEY `UK_bprrybbc3qf3xnt994wq3j92` (`ingredientes_id`),
    KEY `FKbl8nm6s0d3hi6qb6wpx8qyrru` (`receta_id`),
    CONSTRAINT `FKbl8nm6s0d3hi6qb6wpx8qyrru` FOREIGN KEY (`receta_id`) REFERENCES `receta` (`id`),
    CONSTRAINT `FKqx3v38baha3exb2c5jbxl4ooc` FOREIGN KEY (`ingredientes_id`) REFERENCES `ingrediente` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.receta_ingredientes: ~0 rows (approximately)
/*!40000 ALTER TABLE `receta_ingredientes`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `receta_ingredientes`
    ENABLE KEYS */;

-- Dumping structure for table recetario.rol
DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol`
(
    `id`     varchar(255) NOT NULL,
    `nombre` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.rol: ~0 rows (approximately)
/*!40000 ALTER TABLE `rol`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `rol`
    ENABLE KEYS */;

-- Dumping structure for table recetario.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario`
(
    `id`           varchar(255) NOT NULL,
    `alta`         datetime(6)  DEFAULT NULL,
    `apellido`     varchar(255) DEFAULT NULL,
    `baja`         datetime(6)  DEFAULT NULL,
    `clave`        varchar(255) DEFAULT NULL,
    `domicilio`    varchar(255) DEFAULT NULL,
    `mail`         varchar(255) DEFAULT NULL,
    `nombre`       varchar(255) DEFAULT NULL,
    `telefono`     varchar(255) DEFAULT NULL,
    `foto_id`      varchar(255) DEFAULT NULL,
    `provincia_id` varchar(255) DEFAULT NULL,
    `rol_id`       varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKfwvsqymdg4nmop6xhb9b1p3oc` (`foto_id`),
    KEY `FKo2ob8iltr4gd5ta5l8pt9pxn3` (`provincia_id`),
    KEY `FKshkwj12wg6vkm6iuwhvcfpct8` (`rol_id`),
    CONSTRAINT `FKfwvsqymdg4nmop6xhb9b1p3oc` FOREIGN KEY (`foto_id`) REFERENCES `foto` (`id`),
    CONSTRAINT `FKo2ob8iltr4gd5ta5l8pt9pxn3` FOREIGN KEY (`provincia_id`) REFERENCES `provincia` (`id`),
    CONSTRAINT `FKshkwj12wg6vkm6iuwhvcfpct8` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table recetario.usuario: ~1 rows (approximately)
/*!40000 ALTER TABLE `usuario`
    DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `alta`, `apellido`, `baja`, `clave`, `domicilio`, `mail`, `nombre`, `telefono`, `foto_id`,
                       `provincia_id`, `rol_id`)
VALUES ('f2b81d0b-382e-487e-af04-f3ba6352698c', '2021-10-29 23:02:26.834000', 'admin', NULL,
        '$2a$10$eX/sn.1W76K7ibGJyivBqe4FVTJ0z2JwWmlee2MuSXs.RjnBnjxp.', NULL, 'admin@admin', 'admin', NULL, NULL, NULL,
        NULL);
/*!40000 ALTER TABLE `usuario`
    ENABLE KEYS */;

/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES = IFNULL(@OLD_SQL_NOTES, 1) */;



