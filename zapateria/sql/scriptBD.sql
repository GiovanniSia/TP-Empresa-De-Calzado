CREATE DATABASE IF NOT EXISTS`zapateria`;
USE zapateria;
CREATE TABLE IF NOT EXISTS`producto`
(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `idProveedor` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS clientes
(
	idCliente int(11) NOT NULL AUTO_INCREMENT,
	nombre varchar(45) DEFAULT NULL,
	apellido varchar(45) DEFAULT NULL,
	correo varchar(45) DEFAULT NULL,
	limiteCredito int(10) DEFAULT NULL,
	creditoDisponible int(10) DEFAULT NULL,
	tipoCliente varchar(45) DEFAULT NULL,
	impuestoAFIP varchar(45) DEFAULT NULL,
	estado varchar(45) DEFAULT NULL,
	calle  varchar(45) DEFAULT NULL,
	altura varchar(45) DEFAULT NULL,
	pais varchar(45) DEFAULT NULL,
	provincia varchar(45) DEFAULT NULL,
	localidad varchar(45) DEFAULT NULL,
	codPostal varchar(45) DEFAULT NULL,
	PRIMARY KEY(idCliente)
);





CREATE TABLE IF NOT EXISTS empleados
(
	idEmpleado int(11) NOT NULL AUTO_INCREMENT,
	CUIL varchar(45) DEFAULT NULL,
	nombre varchar(45) DEFAULT NULL,
	apellido varchar(45) DEFAULT NULL,
	correo varchar(45) DEFAULT NULL,
	tipoEmpleado varchar(45) DEFAULT NULL,
	contrasenia varchar(45) DEFAULT NULL,
	PRIMARY KEY (idEmpleado)
);

CREATE TABLE IF NOT EXISTS sucursales
(
	idSucursal int(11) NOT NULL AUTO_INCREMENT,
	telefono varchar(45) DEFAULT NULL,
	calle varchar(45) DEFAULT NULL,
	altura varchar(45) DEFAULT NULL,
	provincia varchar(45) DEFAULT NULL,
	localidad varchar(45) DEFAULT NULL,
	pais varchar(45) DEFAULT NULL,
	codPostal varchar(45) DEFAULT NULL,
	nombre varchar(45) DEFAULT NULL,
	PRIMARY KEY (idSucursal)
);