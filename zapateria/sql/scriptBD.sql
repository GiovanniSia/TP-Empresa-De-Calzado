drop database if exists `zapateria`;
CREATE DATABASE `zapateria`;
USE zapateria;

CREATE TABLE `clientes`
(
  `IdCliente` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `DNI` varchar(45) NOT NULL,
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

CREATE TABLE `historialCambioMProducto`
(
  `IdHistorialCambioMProducto` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `IdMaestroProducto` int(11) NOT NULL,
  `Fecha` Date NOT NULL,
  `PrecioCostoAntiguo` double(11,2) NOT NULL,
  `PrecioCostoNuevo` double(11,2) NOT NULL,
  `PrecioMayoristaAntiguo` double(11,2) NOT NULL,
  `PrecioMayoristaNuevo` double(11,2) NOT NULL,
  `PrecioMinoristaAntiguo` double(11,2) NOT NULL,
  `PrecioMinoristaNuevo` double(11,2) NOT NULL,
  PRIMARY KEY (`IdHistorialCambioMProducto`)
);

CREATE TABLE `maestroProductos`
(
  `IdMaestroProducto` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  `Tipo` varchar(45) NOT NULL,
  `Fabricado` varchar(45) NOT NULL,
  `PrecioCosto` double(11,2) NOT NULL,
  `PrecioMayorista` double(11,2) NOT NULL,
  `PrecioMinorista` double(11,2) NOT NULL,
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

insert into maestroProductos values(1,"Zapato Xixo", "TP","S",30,100,120,20,2,"M",300,"Activo",3,2);
insert into maestroProductos values(2,"Zapatilla Muca", "TP","S",15,120,170,520,2,"M",300,"Activo",3,2);
insert into maestroProductos values(3,"Zapato Zzz", "MP","S",40,120,150,202,2,"M",300,"Activo",3,2);
insert into maestroProductos values(4,"Zapatilla Sausa", "MP","S",20,100,120,320,2,"M",300,"Activo",3,2);

INSERT INTO stock VALUES(1,1,1,"ASDF1111",2000);
INSERT INTO stock VALUES(2,1,2,"ASDF2122",2000);
INSERT INTO stock VALUES(3,3,3,"ASDF5325",2000);
INSERT INTO stock VALUES(4,2,2,"ASDF1325",4000);
INSERT INTO stock VALUES(5,1,5,"ASDF5511325",4000);
INSERT INTO stock VALUES(6,1,6,"ASDF55325",55000);

insert into clientes values(1, "Juan", "Lopez","4223004","juan@mgail.com",100,100,"Mayorista","Excento","Activo","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
insert into clientes values(2, "Adriana", "Aula","523004","juanS@mgail.com",200,100,"ayorista","Excento","Activo","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
insert into clientes values(3, "Pedra", "Pula","223004","juanS@mgail.com",200,100,"ayorista","Excento","Activo","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
insert into clientes values(4, "Lua", "Lopez","4223004","juan@mgail.com",100,100,"Mayorista","Excento","Activo","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
insert into clientes values(5, "Delta", "Aula","523004","juanS@mgail.com",200,100,"ayorista","Excento","Activo","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
insert into clientes values(6, "Puda", "Pula","223004","juanS@mgail.com",200,100,"ayorista","Excento","Activo","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
