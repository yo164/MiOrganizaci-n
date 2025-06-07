-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 25, 2025 at 22:16 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `organizacion`
--
CREATE DATABASE IF NOT EXISTS `organizacion` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `organizacion`;

-- --------------------------------------------------------

--
-- Table structure for table `empleados`
--

CREATE TABLE IF NOT EXISTS empleados (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    jefe_id INT DEFAULT 0, -- Si es 0, significa que no tiene jefe (es el máximo responsable)
    FOREIGN KEY (jefe_id) REFERENCES empleados(ID) ON DELETE SET NULL
);

-- Deshabilitar la comprobación de claves foráneas temporalmente
SET foreign_key_checks = 0;

-- Insertar a Ana primero, sin que se valide la clave foránea
INSERT INTO empleados (nombre, apellido, cargo, jefe_id) VALUES
('Ana', 'Pérez', 'Directora General', 0);  -- Ana es la jefa de todos

-- Volver a habilitar la comprobación de claves foráneas
SET foreign_key_checks = 1;

-- Insertar datos de ejemplo para 9 empleados restantes, ahora con jefe_id válidos
INSERT INTO empleados (nombre, apellido, cargo, jefe_id) VALUES
('Luis', 'Gómez', 'Director de Finanzas', 1),  -- Luis depende de Ana
('María', 'Sánchez', 'Director de Marketing', 1),  -- María depende de Ana
('Carlos', 'Ramírez', 'Gerente de TI', 2),  -- Carlos depende de Luis
('José', 'López', 'Analista Financiero', 2),  -- José depende de Luis
('Laura', 'Torres', 'Gerente de Ventas', 3),  -- Laura depende de María
('Elena', 'Martínez', 'Ejecutiva de Marketing', 3),  -- Elena depende de María
('Fernando', 'Jiménez', 'Desarrollador', 4),  -- Fernando depende de Carlos
('Patricia', 'Díaz', 'Contadora', 5),  -- Patricia depende de José
('Ricardo', 'Hernández', 'Vendedor', 6);  -- Ricardo depende de Laura

