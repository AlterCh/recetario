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


USE `recetario`;
/*!40000 ALTER TABLE `usuario`
    DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `alta`, `apellido`, `baja`, `clave`, `domicilio`, `mail`, `nombre`, `telefono`, `foto_id`,
                       `provincia_id`, `rol_id`)
VALUES ('f2b81d0b-382e-487e-af04-f3ba6352698c', '2021-10-29 23:02:26.834000', 'admin', NULL,
        '$2a$10$eX/sn.1W76K7ibGJyivBqe4FVTJ0z2JwWmlee2MuSXs.RjnBnjxp.', NULL, 'admin@admin', 'admin', NULL, NULL, NULL,
        NULL);

/*!40000 ALTER TABLE `usuario`
    ENABLE KEYS */;

/*!40000 ALTER TABLE `unidad`
    DISABLE KEYS */;
INSERT INTO `unidad` (`id`, `abrev`, `nombre`, `tipo_unidad`)
VALUES ('1', 'kg', 'KILOGRAMO', 'MASA'),
       ('2', 'gr', 'GRAMO', 'MASA'),
       ('3', 'mg', 'MILIGRAMO', 'MASA');
/*!40000 ALTER TABLE `unidad`
    ENABLE KEYS */;

use `recetario`;
-- Dumping data for table recetario.producto: ~0 rows (approximately)
/*!40000 ALTER TABLE `producto`
    DISABLE KEYS */;
INSERT INTO `producto` (`id`, `cantidad`, `nombre`, `precio`, `stock`, `unidad_id`)
VALUES ('1', 1, 'Producto 1', 25.5, 10, '1'),
       ('2', 3, 'Producto 2', 25.5, 10, '2'),
       ('3', 4, 'Producto 3', 25.5, 10, '3');

/*!40000 ALTER TABLE `producto`
    ENABLE KEYS */;
/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES = IFNULL(@OLD_SQL_NOTES, 1) */;
