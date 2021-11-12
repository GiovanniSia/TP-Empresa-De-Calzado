drop database if exists `zapateria`;
CREATE DATABASE `zapateria`;
USE zapateria;

CREATE TABLE `clientes`
(
  `IdCliente` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `CUIL` varchar(45) NOT NULL,
  `CorreoElectronico` varchar(45) NOT NULL,
  `LimiteCredito` double(45,2) NOT NULL,
  `CreditoDisponible` double(45,2) NOT NULL,
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

CREATE TABLE `HistorialDeCambiosCliente`
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` varchar(45) NOT NULL,
  `IdCliente` varchar(45) NOT NULL,
  `Fecha` DATE NOT NULL,
  `NombreAntiguo` varchar(45) NOT NULL,
  `NombreNuevo` varchar(45) NOT NULL,
  `ApellidoAntiguo` varchar(45) NOT NULL,
  `ApellidoNuevo` varchar(45) NOT NULL,
  `CUILAntiguo` varchar(45) NOT NULL,
  `CUILNuevo` varchar(45) NOT NULL,
  `CorreoElectronicoAntiguo` varchar(45) NOT NULL,
  `CorreoElectronicoNuevo` varchar(45) NOT NULL,
  `LimiteCreditoAntiguo` double(45,2) NOT NULL,
  `LimiteCreditoNuevo` double(45,2) NOT NULL,
  `CreditoDisponibleAntiguo` double(45,2) NOT NULL,
  `CreditoDisponibleNuevo` double(45,2) NOT NULL,
  `TipoClienteAntiguo` varchar(45) NOT NULL,
  `TipoClienteNuevo` varchar(45) NOT NULL,
  `ImpuestoAFIPAntiguo` varchar(45) NOT NULL,
  `ImpuestoAFIPNuevo` varchar(45) NOT NULL,
  `EstadoAntiguo` varchar(45) NOT NULL,
  `EstadoNuevo` varchar(45) NOT NULL,
  `CalleAntiguo` varchar(45) NOT NULL,
  `CalleNuevo` varchar(45) NOT NULL,
  `AlturaAntiguo` varchar(45) NOT NULL,
  `AlturaNuevo` varchar(45) NOT NULL,
  `PaisAntiguo` varchar(45) NOT NULL,
  `PaisNuevo` varchar(45) NOT NULL,
  `ProvinciaAntiguo` varchar(45) NOT NULL,
  `ProvinciaNuevo` varchar(45) NOT NULL,
  `LocalidadAntiguo` varchar(45) NOT NULL,
  `LocalidadNuevo` varchar(45) NOT NULL,
  `CodPostalAntiguo` varchar(45) NOT NULL,
  `CodPostalNuevo` varchar(45) NOT NULL,
  PRIMARY KEY(`Id`)
);

CREATE TABLE `empleados`
(
  `IdEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `CUIL` varchar(45) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `CorreoElectronico` varchar(45) NOT NULL,
  `TipoEmpleado` varchar(45) NOT NULL,
  `Contra` varchar(500) NOT NULL,
  PRIMARY KEY (`IdEmpleado`)
)ENGINE='InnoDB'DEFAULT CHARSET = LATIN1;

CREATE TABLE `historialCambioEmpleados`
(
  `IdHistorialCambioEmpleados` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleadoResponsable` varchar(45) NOT NULL,
  `Fecha` Date NOT NULL,
  `IdSucursal` varchar(45) NOT NULL,
  `IdEmpleado` varchar(45) NOT NULL,
  `CUILAntiguo` varchar(45) NOT NULL,
  `CUILNuevo` varchar(45) NOT NULL,
  `NombreAntiguo` varchar(45) NOT NULL,
  `NombreNuevo` varchar(45) NOT NULL,
  `ApellidoAntiguo` varchar(45) NOT NULL,
  `ApellidoNuevo` varchar(45) NOT NULL,
  `CorreoElectronicoAntiguo` varchar(45) NOT NULL,
  `CorreoElectronicoNuevo` varchar(45) NOT NULL,
  `TipoEmpleadoAntiguo` varchar(45) NOT NULL,
  `TipoEmpleadoNuevo` varchar(45) NOT NULL,
  PRIMARY KEY (`IdHistorialCambioEmpleados`)
);

CREATE TABLE `historialCambioMoneda`
(
  `IdCambioMoneda` int(11) NOT NULL AUTO_INCREMENT,
  `IdMoneda` varchar(11) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  `IdEmpleado` int(11) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Hora` TIME NOT NULL,
  `TasaConversionAntigua` double(45,2) NOT NULL,
  `TasaConversionNueva` double(45,2) NOT NULL,
  PRIMARY KEY (`IdCambioMoneda`)
);

CREATE TABLE `stock`
(
  `IdStock` int(11) NOT NULL AUTO_INCREMENT,
  `IdSucursal` int(11) NOT NULL,
  `IdProducto` int(11) NOT NULL,
  `CodigoLote` varchar(45) NOT NULL,
  `StockDisponible` double(45,2) NOT NULL,
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
  `NroSucursal` varchar(45) NOT NULL,
  PRIMARY KEY (`IdSucursal`)
);

CREATE TABLE `historialCambioMProducto`
(
  `IdHistorialCambioMProducto` int(11) NOT NULL AUTO_INCREMENT,
  `IdSucursal` varchar(45) NOT NULL,
  `IdEmpleado` varchar(45) NOT NULL,
  `IdMaestroProducto` varchar(45) NOT NULL,
  `Fecha` Date NOT NULL,
  `DescripcionAntiguo` varchar(45) NOT NULL,
  `DescripcionNuevo` varchar(45) NOT NULL,
  `ProveedorAntiguo` varchar(45) NOT NULL,
  `ProveedorNuevo` varchar(45) NOT NULL,
  `PrecioCostoAntiguo` varchar(45) NOT NULL,
  `PrecioCostoNuevo` varchar(45) NOT NULL,
  `PrecioMayoristaAntiguo` varchar(45) NOT NULL,
  `PrecioMayoristaNuevo` varchar(45) NOT NULL,
  `PrecioMinoristaAntiguo` varchar(45) NOT NULL,
  `PrecioMinoristaNuevo` varchar(45) NOT NULL,
  `PuntoRepositorioAntiguo` varchar(45) NOT NULL,
  `PuntoRepositorioNuevo` varchar(45) NOT NULL,
  `CantidadAReponerAntiguo` varchar(45) NOT NULL,
  `CantidadAReponerNuevo` varchar(45) NOT NULL,
  `DiasParaReponerAntiguo` varchar(45) NOT NULL,
  `DiasParaReponerNuevo` varchar(45) NOT NULL,
  PRIMARY KEY (`IdHistorialCambioMProducto`)
);

CREATE TABLE `maestroProductos`
(
  `IdMaestroProducto` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  `Tipo` varchar(45) NOT NULL,
  `Fabricado` varchar(45) NOT NULL,
  `PrecioCosto` double(45,2) NOT NULL,
  `PrecioMayorista` double(45,2) NOT NULL,
  `PrecioMinorista` double(45,2) NOT NULL,
  `PuntoRepositorio` int(11) NOT NULL,
  `IdProveedor` int(11) NOT NULL,
  `Talle` varchar(45) NOT NULL,
  `UnidadMedida` varchar(45) NOT NULL,
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
  `EstadoR` varchar(45) NOT NULL,
  PRIMARY KEY (`IdReceta`)
);

CREATE TABLE `proveedor`
(
  `Idproveedor` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `CorreoElectronico` varchar(45) NOT NULL,
  `LimiteCredito` double(45,2) NOT NULL,
  PRIMARY KEY (`Idproveedor`)
);

CREATE TABLE `productosDeProveedor`
(
	`Id` int(11) NOT NULL AUTO_INCREMENT,
    `IdProveedor` int(11) NOT NULL,
    `IdMaestroProducto` int(11) NOT NULL,
    `PrecioVenta` double(45,2) NOT NULL,
    `CantidadPorLote` int(11) NOT NULL,
    PRIMARY KEY (`Id`)
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
  `Estado` varchar(45) NOT NULL,
  PRIMARY KEY (`IdPaso`)
);

CREATE TABLE `materialesDePaso`
(
  `IdMaterialDePaso` int(11) NOT NULL AUTO_INCREMENT,
  `IdMaterial` int(11) NOT NULL,
  `CantidadUsada` double(11,2) NOT NULL,
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
  `IdFabricacionesEnMarcha` int(11) NOT NULL AUTO_INCREMENT, 
  `IdOrdenFabrica` int(11) NOT NULL,
  `IdReceta` int(11) NOT NULL,
  `NroPasoActual` int(11) NOT NULL,
  `Estado` varchar(45) NOT NULL,
  
  `FechaCompletado` DATE NOT NULL,
  `DiaDisponible` int(11) NOT NULL,
  PRIMARY KEY (`IdFabricacionesEnMarcha`)
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

CREATE TABLE `Ingresos`
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdSucursal` int(11) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Hora` TIME NOT NULL,
  `Tipo` varchar(45) NOT NULL,
  `IdCliente` int(11) NOT NULL,
  `TipoFactura` varchar(45) NOT NULL,
  `NroFactura` varchar(45) NOT NULL,
  `MedioPago` varchar(45) NOT NULL,
  `Cantidad` double(45,2) NOT NULL,
  `Cotizacion` double(45,2) NOT NULL,
  `Operacion` varchar(45) NOT NULL,
  `Total` double(45,2) NOT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `Egresos`
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdSucursal` int(11) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Hora` TIME NOT NULL,
  `Tipo` varchar(45) NOT NULL,
  `MedioPago` varchar(45) NOT NULL,
  `Detalle` varchar(45) NOT NULL,
  `Total` double(45,2) NOT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `MotivoEgresos`
(
  `NroFacturaCompleta` varchar(45) NOT NULL,
  `Motivo` TEXT NOT NULL/*,
  PRIMARY KEY (`NroFacturaCompleta`)*/
);

CREATE TABLE `Caja`
(
  `IdCaja` int(11) NOT NULL AUTO_INCREMENT,
  `IdSucursal` int(11) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Hora` TIME NOT NULL,
  `Apertura` int(11) NOT NULL,
  `Cierre` int(11) NOT NULL,	
  `AperturaNombre` varchar(45) NOT NULL,
  `CierreNombre` varchar(45) NOT NULL,
  `AuditApertura` TIME NOT NULL,
  `AuditCierre` TIME NOT NULL,
  PRIMARY KEY (`IdCaja`)
);

CREATE TABLE `Factura`
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `MontoPendiente` double(45,2) NOT NULL,
  `IdCliente` int(11) NOT NULL,
  `NombreCliente` varchar(45) NOT NULL,
  `IdCajero` int(11) NOT NULL,
  `NombreCajero` varchar(45) NOT NULL,
  `IdVendedor` int(11) NOT NULL,
  `NombreVendedor` varchar(45) NOT NULL,
  `Fecha` varchar(45) NOT NULL,
  `TipoFactura` varchar(45) NOT NULL,
  `NroFacturaCompleta` varchar(45) NOT NULL,
  `IdSucursal` int(11) NOT NULL,
  `Descuento` double(45,2) NOT NULL,
  `TotalBruto` double(45,2) NOT NULL,
  `TotalFactura` double(45,2) NOT NULL,
  `TipoVenta` varchar(45) NOT NULL,
  `Calle` varchar(45) NOT NULL,
  `Altura` varchar(45) NOT NULL,
  `Pais` varchar(45) NOT NULL,
  `Provincia` varchar(45) NOT NULL,
  `Localidad` varchar(45) NOT NULL,
  `CodPostal` varchar(45) NOT NULL,
  `CUIL` varchar(45) NOT NULL,
  `CorreoElectronico` varchar(45) NOT NULL,
  `ImpuestoAFIP` varchar(45) NOT NULL,
  `IVA` double(45,2) NOT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `Detalle`
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdProducto` int(11) NOT NULL,
  `Cantidad` double(45,2) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  `PrecioCosto` double(45,2) NOT NULL,
  `PrecioVenta` double(45,2) NOT NULL,
  `Monto` double(45,2) NOT NULL,
  `idFactura` int(11) NOT NULL,
  `UnidadMedida` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `Carrito`
(
  `idCarrito` int(11) NOT NULL AUTO_INCREMENT,
  `idSucursal` int(11) NOT NULL,
  `IdCliente` int(45) NOT NULL,
  `IdVendedor` int(45) NOT NULL,
  `Total` double(45,2) NOT NULL,
  `Hora` varchar(20) NOT NULL,
  PRIMARY KEY (`idCarrito`)
);

CREATE TABLE `DetalleCarrito`
(					
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCarrito` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `idStock` int(11) NOT NULL,
  `cantidad` double(45,2) NOT NULL,
  `precio` double(45,2) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `medioPago`
(					
  `IdMoneda` varchar(45) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  `TasaConversion` double(45,2) NOT NULL,
  PRIMARY KEY (`IdMoneda`)
);

CREATE TABLE `medioPagoEgreso`
(					
  `IdMoneda` varchar(45) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  `TasaConversion` double(45,2) NOT NULL,
  PRIMARY KEY (`IdMoneda`)
);

CREATE TABLE `tipoEgreso`
(					
  `IdTipoEgreso` varchar(45) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`IdTipoEgreso`)
);

CREATE TABLE `HistorialPasos`
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Hora` Time NOT NULL,
  `Fecha` Date NOT NULL,
  `IdOrden` int(11) NOT NULL,
  `IdEmpleado` int(11) NOT NULL,
  `NombreCompleto` varchar(45) NOT NULL,
  `DescrPasoCompletado` varchar(45) NOT NULL,
  `Descr` TEXT NOT NULL,
  `TipoCancelacion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `PedidosPendientes`
(
	`Id` int(11) NOT NULL AUTO_INCREMENT,
    `IdProveedor` int(11) NOT NULL,
    `NombreProveedor` varchar(45) NOT NULL,
    `IdMaestroProducto` int(11) NOT NULL,
    `NombreMaestroProducto` varchar(45) NOT NULL,
    `Cantidad` double(45,2) NOT NULL,
    `Fecha` Date NOT NULL,
    `Hora` Time NOT NULL,
    `PrecioUnidad` double(45,2) NOT NULL,
    `PrecioTotal` double(45,2) NOT NULL,
    `Estado` varchar(45) NOT NULL,
    `IdSucursal` int(11) NOT NULL,
    `IdEmpleado` int(11) NOT NULL,
    `FechaEnvioMail` Date DEFAULT NULL,
    `HoraEnvioMail` TIME DEFAULT NULL,
	`FechaCompleto` Date DEFAULT NULL,
    `HoraCompleto` TIME DEFAULT NULL,
    `UnidadMedida` varchar(45) NOT NULL,
    PRIMARY KEY(`Id`)
);

CREATE TABLE `RechazoCompraVirtual`
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Hora` Time NOT NULL,
  `Fecha` Date NOT NULL,
  `idSucursal` int(11) NOT NULL,
  `pago` double(45,2) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `CUIL` varchar(45) NOT NULL,
	`CorreoElectronico` varchar(45) NOT NULL,/*
	`TipoCliente` varchar(45) NOT NULL,*/
	`ImpuestoAFIP` varchar(45) NOT NULL,
	`Estado` varchar(45) NOT NULL,
	`Calle` varchar(45) NOT NULL,
	`Altura` varchar(45) NOT NULL,
	`Pais` varchar(45) NOT NULL,
	`Provincia` varchar(45) NOT NULL,
	`Localidad` varchar(45) NOT NULL,
	`CodPostal` varchar(45) NOT NULL,
	`Motivo` TEXT NOT NULL,
    `Telefono` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `RechazoCompraVirtualDetalle`
(
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdRechazoCompraVirtual` int(11) NOT NULL,
  `IdProducto` int(11) NOT NULL,
  `NombreProducto` varchar(45) NOT NULL,
  `PrecioMayorista` double(45,2) NOT NULL,
  `PrecioMinorista` double(45,2) NOT NULL,
  `PrecioCosto` double(45,2) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
);


CREATE TABLE IF NOT EXISTS `paises`
(
    `idPais` int(11) NOT NULL AUTO_INCREMENT,
    `nombrePais` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`idPais`)
);

CREATE TABLE IF NOT EXISTS `provincias`
(
    `idProvincia` int(11) NOT NULL AUTO_INCREMENT,
    `nombreProvincia` varchar(45) DEFAULT NULL,
    `idForeignPais` int(11) NOT NULL,
    PRIMARY KEY (`idProvincia`)
);

ALTER TABLE `provincias` ADD FOREIGN KEY(`idForeignPais`) REFERENCES `paises`(`idPais`) ON DELETE CASCADE;

CREATE TABLE IF NOT EXISTS `localidades`
(
    `idLocalidad` int(11) NOT NULL AUTO_INCREMENT,
    `nombreLocalidad` varchar(45) DEFAULT NULL,
    `idForeignProvincia` int(11) NOT NULL,
    PRIMARY KEY (`idLocalidad`)
);

insert into paises values(1,"Argentina");
INSERT INTO paises VALUES(2,"Brasil");
INSERT INTO paises VALUES(3,"Chile");
INSERT INTO paises VALUES(4,"Uruguay");

insert into provincias values(1,"Buenos Aires",1);
INSERT INTO provincias VALUES(2,"Capital Federal",1);
INSERT INTO provincias VALUES(3,"Catamarca",1);
INSERT INTO provincias VALUES(4,"Chaco",1);
INSERT INTO provincias VALUES(5,"Chubut",1);
INSERT INTO provincias VALUES(6,"Córdoba",1);
INSERT INTO provincias VALUES(7,"Corrientes",1);
INSERT INTO provincias VALUES(8,"Entre Ríos",1);
INSERT INTO provincias VALUES(9,"Formosa",1);
INSERT INTO provincias VALUES(10,"Jujuy",1);
INSERT INTO provincias VALUES(11,"La Pampa",1);
INSERT INTO provincias VALUES(12,"La Rioja",1);
INSERT INTO provincias VALUES(13,"Mendoza",1);
INSERT INTO provincias VALUES(14,"Neuquén",1);
INSERT INTO provincias VALUES(15,"Río Negro",1);
INSERT INTO provincias VALUES(16,"Salta",1);
INSERT INTO provincias VALUES(17,"San Juan",1);
INSERT INTO provincias VALUES(18,"San Luis",1);
INSERT INTO provincias VALUES(19,"Santa Cruz",1);
INSERT INTO provincias VALUES(20,"Santa Fe",1);
INSERT INTO provincias VALUES(21,"Santiago del Estero",1);
INSERT INTO provincias VALUES(22,"Tierra del Fuego",1);
INSERT INTO provincias VALUES(23,"Tucumán",1);

INSERT INTO provincias VALUES(24,"Artigas",4);
INSERT INTO provincias VALUES(25,"Canelones",4);
INSERT INTO provincias VALUES(26,"Cerro Largo",4);
INSERT INTO provincias VALUES(27,"Colonia",4);
INSERT INTO provincias VALUES(28,"Durazno",4);
INSERT INTO provincias VALUES(29,"Montevideo",4);

insert into localidades values(1,"Malvinas Argentinas",1);
insert into localidades values(2,"Pilar",1);

insert into localidades values(3,"Reducto",29);


insert into tipoEgreso values("AS","Adelanto de sueldo");
insert into tipoEgreso values("FA","Faltante");
insert into tipoEgreso values("PP","Pago proveedor");
insert into tipoEgreso values("NC","Nota Credito");

INSERT INTO medioPagoEgreso values ("EFE", "Efectivo",1);
INSERT INTO medioPagoEgreso values ("NC", "Nota Credito",1);

INSERT INTO medioPago values ("EFE","Efectivo",1);
INSERT INTO medioPago values ("USD","Dolar",180);
INSERT INTO medioPago values ("TCV","Tarjeta Credito VISA",1);
INSERT INTO medioPago values ("MPA","Mercado Pago",1);
INSERT INTO medioPago values ("CC","Cuenta Corriente",1);
INSERT INTO medioPago values ("PV","Portal Virtual",1);

INSERT INTO maestroProductos values(1,"ZapatillaNike"    ,"PT","S",100 ,7000 ,2020,1500,2,"M",200,"Activo",36,100);
INSERT INTO maestroProductos values(2,"ZapatillaPuma"    ,"PT","N",200 ,8000 ,4000,2   ,4,"XL",200,"Activo",10,100);
INSERT INTO maestroProductos values(3,"ZapatillaAdidas"  ,"PT","S",100 ,1050 ,2000,5   ,1,"XXL",300,"Activo",200,100);
INSERT INTO maestroProductos values(4,"ZapatillaReebok"  ,"PT","S",1000,5000 ,6000,3   ,2,"N",300,"Activo",1000,1);
INSERT INTO maestroProductos values(5,"ZapatillaAdidas"  ,"PT","S",100 ,5050 ,2000,5   ,1,"M",300,"Activo",200,100);
INSERT INTO maestroProductos values(6,"ZapatillaAdidas"  ,"PT","S",100 ,8050 ,1100,5   ,2,"M",300,"Activo",200,100);

INSERT INTO maestroProductos values(7,"Pegamento"  ,"MP","N",100 ,1050,2000,5  ,1,"XXL","frasco x 4Lts","Activo",200,100);
INSERT INTO maestroProductos values(8,"Goma espuma"  ,"MP","N",1000,5000,6000,3  ,2,"N","5mm Rollo 1 X 20 Mts","Activo",1000,1);
INSERT INTO maestroProductos values(9,"Hilo 100 mts"  ,"MP","N",100 ,5050,2000,5  ,1,"M","1 bobina x 100 mts","Activo",200,100);

INSERT INTO maestroProductos values(10,"ZapatillaXXX"     ,"PT","N",100 ,8050 ,1100 ,200,1,"M",300,"Activo",300,10);

insert into proveedor values(1,"Naik","zapateriaargento198@gmail.com",20000);
insert into proveedor values(2,"Adida","zapateriaargento198@gmail.com",3000);
insert into proveedor values(3,"Rebuk","zapateriaargento198@gmail.com",200);
insert into proveedor values(4,"ProveedorX","zapateriaargento198@gmail.com",2000);

INSERT INTO stock VALUES(1,2,1,"ASDF1111",2000);
INSERT INTO stock VALUES(2,2,2,"ASDF2122",2000);
INSERT INTO stock VALUES(3,4,3,"ASDF5325",2000);
INSERT INTO stock VALUES(4,3,2,"ASDF1325",4000);
INSERT INTO stock VALUES(5,2,5,"ASDF5511325",4000);
INSERT INTO stock VALUES(6,2,6,"ASDF55325",55000);

insert into stock values(8,1,7,'LOTECREADOAMANO2',350);
insert into stock values(9,1,8,'LOTECREADOAMANO3',400);
insert into stock values(10,1,9,'LOTECREADOAMANO4',500);

INSERT INTO stock VALUES(11,2,10,"ASDF11111",1000);


insert into clientes values(1, "Consumidor Final", "","00000000","",0,0,"Minorista","E","Activo","0","0","","","","0");
insert into clientes values(2, "Juan", "Lopez","4223004","juan@mgail.com",100,100,"Mayorista","E","Moroso","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
insert into clientes values(3, "Adriana", "Aula","523004","juanS@mgail.com",200,100,"Mayorista","E","Activo","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
insert into clientes values(4, "Pedra", "Pula","223004","juanS@mgail.com",200,100,"Minorista","E","Activo","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
insert into clientes values(5, "Lua", "Lopez","4223004","juan@mgail.com",100,100,"Mayorista","E","Activo","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
insert into clientes values(6, "Delta", "Aula","523004","juanS@mgail.com",200,100,"Mayorista","E","Activo","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
insert into clientes values(7, "Puda", "Pula","223004","juanS@mgail.com",200,100,"Mayorista","E","Activo","1002","201","Argentina","Buenos Aires","Bella Vista","1661");
insert into clientes values(8, "Vicentino", "Reboredo","223004","pepeptS@mgail.com",200,100,"Mayorista","RI","Activo","1002","201","Argentina","Buenos Aires","Bella Vista","1661");

insert into recetas values(1,1,'Receta eficiente','Activo');

insert into paso values(1,"Cortado","Activo");
insert into paso values(2,"Aparado","Activo");
insert into paso values(3,"Preparación","Activo");
insert into paso values(4,"Montado","Activo");
insert into paso values(5,"Pegado","Activo");
insert into paso values(6,"Terminado","Activo");

insert into pasosReceta values(1,1,1,1);
insert into pasosReceta values(2,1,2,2);
insert into pasosReceta values(3,1,3,3);
insert into pasosReceta values(4,1,4,4);
insert into pasosReceta values(5,1,5,5);
insert into pasosReceta values(6,1,6,6);

insert into materialesDePaso values(1,7,10,5);
insert into materialesDePaso values(2,8,6,1);
insert into materialesDePaso values(3,9,7,5);

insert into recetas values(2,1,'Receta Dupli','Activo');
insert into pasosReceta values(7,2,1,1);
insert into pasosReceta values(8,2,2,4);
insert into pasosReceta values(9,2,3,6);
insert into materialesDePaso values(4,9,7,9);
insert into stock values(7,1,9,'LOTECREADOAMANO',70);

insert into recetas values(3,1,'Receta Auxiliar','Activo');
insert into pasosReceta values(10,3,1,2);
insert into pasosReceta values(11,3,2,5);

insert into recetas values(4,2,'Receta sin material','Activo');
insert into pasosReceta values(12,4,1,1);
insert into pasosReceta values(13,4,2,2);
insert into ordenfabrica values(1,1,'2011-02-5',20,'L493132',3);

insert into recetas values(5,3,'Receta sin material','Activo');
insert into pasosReceta values(14,5,1,1);
insert into pasosReceta values(15,5,2,2);

insert into recetas values(6,4,'Receta sin material','Activo');
insert into pasosReceta values(16,6,1,1);
insert into pasosReceta values(17,6,2,2);

insert into recetas values(7,5,'Receta sin material','Activo');
insert into pasosReceta values(18,7,1,1);
insert into pasosReceta values(19,7,2,2);

insert into recetas values(8,6,'Receta sin material','Activo');
insert into pasosReceta values(20,8,1,1);
insert into pasosReceta values(21,8,2,2);
/*
insert into ordenfabrica values(1,1,'2020-03-20',36,'L123123',1);
insert into ordenfabrica values(2,1,'2019-06-19',10,'L213132',2);
insert into ordenfabrica values(3,2,'2021-03-20',7,'L393132',2);
insert into ordenfabrica values(4,2,'2018-10-10',20,'L493132',3);
insert into ordenfabrica values(5,1,'2011-02-5',20,'L493132',3);
*/
insert into sucursales values(1, '14513123','Juan doming.','596','BSAS','TORTGUITAS','ARG','1234','La fabrica',"0000");
INSERT INTO sucursales VALUES(2, "72342323", "JOSE HERNANDEZ","123","BSAS","TORTGUITAS","ARG","1234","Polvorines","1234");
INSERT INTO sucursales VALUES(3, "62348323", "Peron","1434","BSAS","TORTGUITAS","ARG","1224","Los hermanos","1234");
insert into sucursales values(4,'12243423','Ituzaingo','2323','BSAS','TORTGUITAS','ARG','123','grand borg',"1234");
insert into sucursales values(5,'22342623','Puente alcorta','232','BSAS','TORTGUITAS','ARG','123','Don torcuachino',"1234");
insert into sucursales values(6,'42342323','Ruta 8','1232','BSAS','TORTGUITAS','ARG','123','El tata',"1234");

insert into ingresos values (1,2,'2021-10-18','12:20','VT',1,'A','aaaa','EFE',100,1,'',1000);
insert into ingresos values (2,3,'2021-10-17','12:20','VT',2,'A','aaaa','EFE',150,1,'',600);
insert into ingresos values (3,4,'2021-10-16','12:20','VT',2,'A','aaaa','USD',100,3,'',300);

insert into factura values (1,0,1,'nombre cliente',1,'nombre cajero',1,'PEREZ PEPE','2021-10-18','B','1234B00000001',2,00,1000,1000,'Mayorista','CALLE FALSA','1234','Argentina','BSAS','Tortuguitas','1667','cuil','Correo electronico','CF',0);
insert into factura values (2,0,2,'nombre cliente',1,'nombre cajero',2,'JUAN VENDEDOR','2021-10-18','B','1234B00000001',3,00,600,600,'Mayorista','CALLE FALSA','1234','Argentina','BSAS','Tortuguitas','1667','cuil','Correo electronico','CF',0);
insert into factura values (3,0,3,'nombre cliente',1,'nombre cajero',3,'JOAQUIN ADOLFO','2021-10-18','B','1234B00000001',4,00,300,300,'Mayorista','CALLE FALSA','1234','Argentina','BSAS','Tortuguitas','1667','cuil','Correo electronico','CF',0);

INSERT INTO empleados VALUES(1,"123","Pepe","Perez","pepe@gmail.com","Cajero",aes_encrypt('1234','AES'));
insert into zapateria.empleados values (2, 512,'Juan','Vera','juan@gmail.com','Vendedor',aes_encrypt('1234','AES'));
insert into zapateria.empleados values (3, 523,'Joaquin','Adolfo','joaquin@gmail.com','Vendedor',aes_encrypt('1234','AES'));
insert into zapateria.empleados values (4, 153,'Fernando','Lel','fer@gmail.com','Vendedor',aes_encrypt('1234','AES'));
insert into zapateria.empleados values (5, 153541,'Ivo','Robotnik','ivo@gmail.com','Vendedor',aes_encrypt('1234','AES'));
insert into zapateria.empleados values (6, 4233,'Camila','Alfano','camila@gmail.com','Supervisor',aes_encrypt('1234','AES'));
insert into zapateria.empleados values (7, 1773,'Josselyn','Llanos','josselyn@gmail.com','Supervisor de Fabrica',aes_encrypt('1234','AES'));
insert into zapateria.empleados values (8, 1554,'Maximiliano','Sungarunga','maximiliano@gmail.com','Operario de Fabrica',aes_encrypt('1234','AES'));
insert into zapateria.empleados values (9, 2323,'Michelle','Robi','michelle@gmail.com','Administrativo',aes_encrypt('1234','AES'));
insert into zapateria.empleados values (10, 2323,'Matias','Robi','matias@gmail.com','Gerente',aes_encrypt('1234','AES'));