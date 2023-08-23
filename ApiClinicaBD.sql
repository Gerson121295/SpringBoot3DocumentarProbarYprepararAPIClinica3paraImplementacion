
create database vollmed_api;
use vollmed_api;
show tables;

SELECT * FROM medicos;
SELECT * FROM pacientes;

-- Eliminar las columnas: urbanizacion, codigoPostal y provincia de tabla pacientes.
ALTER TABLE pacientes DROP COLUMN urbanización;
ALTER TABLE pacientes DROP COLUMN codigoPostal;
ALTER TABLE pacientes DROP COLUMN provincia;

-- Añadir columna calle a la tabla pacientes
ALTER TABLE pacientes ADD COLUMN calle VARCHAR(100) NOT NULL;

-- Cambiar nombre de columna de DocumentoIdentidad

ALTER TABLE pacientes CHANGE documentoIdentidad documento_identidad VARCHAR(14) NOT NULL UNIQUE;

SELECT documentoIdentidad as ID from pacientes;





-- Tabla Medicos
/*
create table medicos{
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documento varchar(6) not null unique,
    especialidad varchar(100) not null,
    calle varchar(100) not null,
    distrito varchar(100) not null,
    complemento varchar(100),
    numero varchar(28),
    ciudad varchar(100) not null,

    primary key(id)
}
*/
-- Tabla pacientes
create table pacientes(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documentoIdentidad varchar(14) not null unique,
    telefono varchar(20) not null,
    urbanización varchar(100) not null,
    distrito varchar(100) not null,
    codigoPostal varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    provincia varchar(100) not null,
    ciudad varchar(100) not null,

    primary key(id)
    );
    
    
    



