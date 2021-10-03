CREATE DATABASE IF NOT EXISTS`zapateria`;
USE zapateria;
CREATE TABLE IF NOT EXISTS`producto`
(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `idProveedor` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);