
-- CREATE DATABASE registerDb;
USE registerDb;

CREATE TABLE HorarioTrabalho (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entrada TIME NOT NULL,
    saida TIME NOT NULL
);

CREATE TABLE MarcacoesFeitas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entrada TIME NOT NULL,
    saida TIME NOT NULL
);

CREATE TABLE Atraso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entrada TIME NOT NULL,
    saida TIME NOT NULL
);

CREATE TABLE HoraExtra (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entrada TIME NOT NULL,
    saida TIME NOT NULL
);
