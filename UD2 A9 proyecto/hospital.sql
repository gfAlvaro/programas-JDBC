CREATE DATABASE IF NOT EXISTS HOSPITAL;

CREATE TABLE IF NOT EXISTS PACIENTE (
	id int(4) PRIMARY KEY,
    nombre varchar(20),
    apellidos varchar(30),
    direccion varchar(50),
    poblacion varchar(30),
    provincia varchar(30),
    codigoPostal int(5),
    telefono int(9),
    fechaNacimiento date
);

CREATE TABLE IF NOT EXISTS MEDICO (
    id int(4) PRIMARY KEY,
    nombre VARCHAR(20),
    apellidos VARCHAR(30),
    telefono INT(9),
    especialidad VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS INGRESO (
	id int(4) PRIMARY KEY,
    idPaciente int(4),
    idMedico int(4),
    fechaIngreso date,
    habitacion int(3),
    cama int(1),
    fechaAlta date DEFAULT NULL,
	CONSTRAINT camaRestriccion CHECK(cama >= 1), CHECK (cama <= 2),
	CONSTRAINT fkIngresoPaciente FOREIGN KEY (idPaciente)
		REFERENCES PACIENTE(id),
	CONSTRAINT fkIngresoMEdico FOREIGN KEY (idMedico)
		REFERENCES MEDICO(id)
);
