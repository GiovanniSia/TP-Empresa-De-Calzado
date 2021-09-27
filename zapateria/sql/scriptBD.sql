CREATE DATABASE `zapateria`;
USE zapateria;
CREATE TABLE `producto`
(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `idProveedor` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);