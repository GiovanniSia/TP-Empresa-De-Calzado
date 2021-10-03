drop database if exists `zapateria`;
CREATE DATABASE `zapateria`;
USE zapateria;

CREATE TABLE `clientes`
(
  `IdCliente` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `CorreoElectronico` varchar(45) NOT NULL,
  `LimiteCredito` int(11) NOT NULL,
  `CreditoDisponible` int(11) NOT NULL,
  `TipoCliente` varchar(45) NOT NULL,
  `ImpuestoAFIP` varchar(45) NOT NULL,
  `Estado` varchar(45) NOT NULL,
  `Calle` varchar(45) NOT NULL,
  `Altura` varchar(45) NOT NULL,
  `Pais` varchar(45) NOT NULL,
  `Provincia` varchar(45) NOT NULL,
  `Localidad` varchar(45) NOT NULL,
  `CodPostal` varchar(45) NOT NULL,
  PRIMARY KEY (`IdCliente`)
);

CREATE TABLE `empleados`
(
  `IdEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `CUIL` varchar(45) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `CorreoElectronico` varchar(45) NOT NULL,
  `TipoEmpleado` varchar(45) NOT NULL,
  `Contra` varchar(45) NOT NULL,
  PRIMARY KEY (`IdEmpleado`)
);

CREATE TABLE `movimientosCaja`
(
  `IdMovimientosCaja` int(11) NOT NULL AUTO_INCREMENT,
  `IdSucursal` int(11) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Detalle` varchar(45) NOT NULL,
  `TipoDeMovimiento` varchar(45) NOT NULL,
  `Monto` int(11) NOT NULL,
  `IdMoneda` int(11) NOT NULL,
  `Monto_Convertido` int(11) NOT NULL,
  `IdFactura` int(11) NOT NULL,
  `IdCierre` int(11) NOT NULL,
  `IdEmpleado` int(11) NOT NULL,
  PRIMARY KEY (`IdMovimientosCaja`)
);

CREATE TABLE `factura`
(
  `IdFactura` int(11) NOT NULL AUTO_INCREMENT,
  `MontoPendiente` int(11) NOT NULL,
  `Detalle` varchar(45) NOT NULL,
  `IdCliente` int(11) NOT NULL,
  `IdEmpleado` int(11) NOT NULL,
  `Fecha` DATE NOT NULL,
  
  `TipoFactura` varchar(45) NOT NULL,
  `NroFacturaCompleta` varchar(45) NOT NULL,
  `IdSucursal` int(11) NOT NULL,
  `TerceraParte` int(11) NOT NULL,
  
  `Descuento` int(11) NOT NULL,
  `TotalFactura` int(11) NOT NULL,
  `IdMedioDePago` int(11) NOT NULL,
  `TipoVenta` varchar(45) NOT NULL,
  PRIMARY KEY (`IdFactura`)
);

CREATE TABLE `cajaSucursal`
(
  `IdSucursal` int(11) NOT NULL AUTO_INCREMENT,
  `IdMoneda` int(11) NOT NULL,
  `CantidadEnCaja` int(11) NOT NULL,
  PRIMARY KEY (`IdSucursal`)
);

CREATE TABLE `detalle`
(
  `IdDetalle` int(11) NOT NULL AUTO_INCREMENT,
  `IdProducto` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  `PrecioCosto` int(11) NOT NULL,
  `PrecioVenta` int(11) NOT NULL,
  `Monto` int(11) NOT NULL,
  PRIMARY KEY (`IdDetalle`)
);

CREATE TABLE `valorMoneda`
(
  `IdMoneda` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  `TasaConversion` int(11) NOT NULL,
  PRIMARY KEY (`IdMoneda`)
);

CREATE TABLE `ModoDePago`
(
  `IdModoDePago` int(11) NOT NULL AUTO_INCREMENT,
  `MetodoDePago` varchar(45) NOT NULL,
  `IdFactura` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `idMovimientoCaja` int(11) NOT NULL,
  `Tarjeta` varchar(45) NOT NULL,
  PRIMARY KEY (`IdModoDePago`)
);

CREATE TABLE `medioPago`
(
  `IdMedioDePago` int(11) NOT NULL AUTO_INCREMENT,
  `MetodoDePago` varchar(45) NOT NULL,
  PRIMARY KEY (`IdMedioDePago`)
);

CREATE TABLE `historialCambioMoneda`
(
  `IdCambioMoneda` int(11) NOT NULL AUTO_INCREMENT,
  `IdMoneda` int(11) NOT NULL,
  `IdEmpleado` int(11) NOT NULL,
  `TasaAnterior` int(11) NOT NULL,
  `NuevaTasa` int(11) NOT NULL,
  `Fecha` DATE NOT NULL,
  PRIMARY KEY (`IdCambioMoneda`)
);

CREATE TABLE `cierreCaja`
(
  `IdCierre` int(11) NOT NULL AUTO_INCREMENT,
  `IdSucursal` int(11) NOT NULL,
  `FechaCierre` DATE NOT NULL,
  `SaldoTotal` int(11) NOT NULL,
  PRIMARY KEY (`IdCierre`)
);

CREATE TABLE `stock`
(
  `IdStock` int(11) NOT NULL AUTO_INCREMENT,
  `IdSucursal` int(11) NOT NULL,
  `IdProducto` int(11) NOT NULL,
  `CodigoLote` varchar(45) NOT NULL,
  `StockDisponible` int(11) NOT NULL,
  PRIMARY KEY (`IdStock`)
);

CREATE TABLE `sucursales`
(
  `IdSucursal` int(11) NOT NULL AUTO_INCREMENT,
  `Telefono` varchar(45) NOT NULL,
  `Calle` varchar(45) NOT NULL,
  `Altura` varchar(45) NOT NULL,
  `Provincia` varchar(45) NOT NULL,
  `Localidad` varchar(45) NOT NULL,
  `Pais` varchar(45) NOT NULL,
  `CodigoPostal` varchar(45) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`IdSucursal`)
);

CREATE TABLE `maestroProductos`
(
  `IdMaestroProducto` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  `Tipo` varchar(45) NOT NULL,
  `Fabricado` varchar(45) NOT NULL,
  `PrecioCosto` int(11) NOT NULL,
  `PrecioVenta` int(11) NOT NULL,
  `PuntoRepositorio` int(11) NOT NULL,
  `IdProveedor` int(11) NOT NULL,
  `Talle` varchar(45) NOT NULL,
  `UnidadMedida` int(11) NOT NULL,
  `Estado` varchar(45) NOT NULL,
  
  `CantidadAReponer` int(11) NOT NULL,
  `DiasParaReponer` int(11) NOT NULL,
  PRIMARY KEY (`IdMaestroProducto`)
);

CREATE TABLE `recetas`
(
  `IdReceta` int(11) NOT NULL AUTO_INCREMENT,
  `IdProducto` int(11) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`IdReceta`)
);

CREATE TABLE `proveedor`
(
  `Idproveedor` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `CorreoElectronico` varchar(45) NOT NULL,
  `LimiteCredito` int(11) NOT NULL,
  `CreditoDisponible` int(11) NOT NULL,
  PRIMARY KEY (`Idproveedor`)
);

CREATE TABLE `pasosReceta`
(
  `IdPasoReceta` int(11) NOT NULL AUTO_INCREMENT,
  `IdReceta` int(11) NOT NULL,
  `NroOrden` int(11) NOT NULL,
  `IdPaso` int(11) NOT NULL,
  PRIMARY KEY (`IdPasoReceta`)
);

CREATE TABLE `paso`
(
  `IdPaso` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`IdPaso`)
);

CREATE TABLE `materialesDePaso`
(
  `IdMaterialDePaso` int(11) NOT NULL AUTO_INCREMENT,
  `IdMaterial` int(11) NOT NULL,
  `CantidadUsada` int(11) NOT NULL,
  `IdPaso` int(11) NOT NULL,
  PRIMARY KEY (`IdMaterialDePaso`)
);

CREATE TABLE `ordenFabrica`
(
  `IdOrdenFabrica` int(11) NOT NULL AUTO_INCREMENT,
  `IdProd` int(11) NOT NULL,
  `FechaRequerido` DATE NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `CodigoLote` varchar(45) NOT NULL,
  `IdSucursal` int(11) NOT NULL,
  PRIMARY KEY (`IdOrdenFabrica`)
);

CREATE TABLE `fabricacionesEnMarcha`
(
  `IdOrdenFabrica` int(11) NOT NULL AUTO_INCREMENT,
  `IdReceta` int(11) NOT NULL,
  `NroPasoActual` int(11) NOT NULL,
  PRIMARY KEY (`IdOrdenFabrica`)
);

CREATE TABLE `estadoMaterialFabricacion`
(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);
insert into estadoMaterialFabricacion values(1,'activo');
insert into estadoMaterialFabricacion values(2,'inactivo');
insert into estadoMaterialFabricacion values(3,'suspendido');
insert into estadoMaterialFabricacion values(4,'desactualizado');