drop database if exists SOFTLARISM;
create database SOFTLARISM;
use softlarism;
show databases;

create table USUARIOS(
id_usuario int primary key auto_increment,
telefono varchar(10),
contrasena varchar(255),
nombre varchar(50),
apellido varchar(50),
cp1 varchar(5),
numero varchar(255)
);

create table COMUNIDAD(
id_comunidad int primary key auto_increment,
cnombre varchar(50),
descripcion varchar(255),
tipo varchar(20),
cp2 varchar(20)
);

create table EVENTOS(
id_evento int primary key auto_increment,
tipo varchar(30),
descripcion varchar(50),
id_usuario int,
foreign key (id_usuario) references usuarios(id_usuario) on update cascade on delete cascade
);

create table PERTENECE(
id_usuario int,
id_comunidad int,
foreign key (id_usuario) references usuarios(id_usuario) on update cascade on delete cascade,
foreign key (id_comunidad) references comunidad(id_comunidad) on update cascade on delete cascade
);

create table AUTORIDADES(
id_autoridad int primary key not null,
anombre varchar(50),
contacto varchar(10)
);

create table CONTACTA(
id_usuario int,
id_autoridad int,
foreign key (id_usuario) references usuarios(id_usuario) on update cascade on delete cascade,
foreign key (id_autoridad) references autoridades(id_autoridad) on update cascade on delete cascade
);

insert into USUARIOS (id_usuario,telefono,contrasena,nombre,apellido,cp1,numero)
values('1',55672198,'hola','islas','flores',55780,3423)

