-- create database estadoAlumnos;

/*create table alumno(
 legajo int not null primary key,
 nombre varchar(50) not null,
 apellido varchar(50) not null,
 dni int not null unique
);

create table materia(
  idMateria int not null primary key auto_increment,
  nombre varchar(50),
  estado varchar(20),
  alumnoId int, -- Relación 1:N con alumno
  foreign key (alumnoId) references alumno (legajo) ON DELETE cascade
);

create table nota(
  idNota int not null primary key auto_increment,
  nota int not null,
  materiaId int,
  foreign key (materiaId) references materia (idMateria) ON DELETE cascade
);*/


