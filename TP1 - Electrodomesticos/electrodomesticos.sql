-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 30-01-2015 a las 16:18:01
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `electrodomesticos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colores`
--

CREATE TABLE IF NOT EXISTS `colores` (
  `color` varchar(20) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `colores`
--

INSERT INTO `colores` (`color`) VALUES
('ROJO'),
('AZUL'),
('GRIS'),
('NEGRO'),
('BLANCO'),
('ROJO'),
('AZUL'),
('GRIS'),
('NEGRO'),
('BLANCO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consumos`
--

CREATE TABLE IF NOT EXISTS `consumos` (
  `consumo` char(1) COLLATE utf8_spanish2_ci NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `consumos`
--

INSERT INTO `consumos` (`consumo`, `precio`) VALUES
('F', 10),
('A', 100),
('B', 80),
('C', 60),
('D', 50),
('E', 30),
('F', 10),
('A', 100),
('B', 80),
('C', 60),
('D', 50),
('E', 30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lavarropas`
--

CREATE TABLE IF NOT EXISTS `lavarropas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `color` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `consumo` char(1) COLLATE utf8_spanish2_ci NOT NULL,
  `peso` double NOT NULL,
  `carga` double NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=26 ;

--
-- Volcado de datos para la tabla `lavarropas`
--

INSERT INTO `lavarropas` (`id`, `color`, `consumo`, `peso`, `carga`, `precio`) VALUES
(1, 'ROJO', 'A', 999, 999, 99),
(3, 'AZUL', 'C', 10, 130, 2340),
(4, 'GRIS', 'D', 10, 120, 5000),
(5, 'negocio.Color@f08d0f', 'n', 5, 5, 100),
(6, 'negocio.Color@1544e4', 'n', 12, 5, 150),
(7, 'negocio.Color@19bf79', 'n', 123, 5, 144),
(8, 'negocio.Color@169ccc', 'n', 22, 22, 22),
(9, 'negocio.Color@8e3115', 'n', 999, 34, 999),
(10, 'negocio.Color@67e236', 'n', 1211, 12, 11),
(14, 'BLANCO', 'F', 5, 5, 100),
(15, 'BLANCO', 'F', 5, 5, 100),
(16, 'BLANCO', 'F', 5, 5, 100),
(17, 'negocio.Color@16095f', 'n', 25, 5, 100),
(18, 'negocio.Color@1549af', 'n', 111, 5, 111),
(19, 'BLANCO', 'F', 5, 5, 100),
(21, 'ROJO', 'D', 99, 99, 999),
(23, 'BLANCO', 'F', 5, 5, 100),
(24, 'BLANCO', 'F', 5, 5, 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pesos`
--

CREATE TABLE IF NOT EXISTS `pesos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `maximo` int(11) NOT NULL,
  `precio` double NOT NULL,
  `minimo` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `pesos`
--

INSERT INTO `pesos` (`id`, `maximo`, `precio`, `minimo`) VALUES
(1, 20, 10, 0),
(2, 50, 50, 20),
(3, 80, 80, 50),
(4, 999999999, 100, 80);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `televisores`
--

CREATE TABLE IF NOT EXISTS `televisores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `color` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `consumo` char(1) COLLATE utf8_spanish2_ci NOT NULL,
  `peso` double NOT NULL,
  `resolucion` int(11) NOT NULL,
  `sintonizador` tinyint(1) NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=55 ;

--
-- Volcado de datos para la tabla `televisores`
--

INSERT INTO `televisores` (`id`, `color`, `consumo`, `peso`, `resolucion`, `sintonizador`, `precio`) VALUES
(1, 'NEGRO', 'F', 190.05, 50, 1, 555),
(6, 'NEGRO', 'F', 5, 20, 1, 100),
(7, 'BLANCO', 'F', 5, 20, 1, 100),
(32, 'BLANCO', 'F', 5, 20, 0, 5),
(37, 'GRIS', 'B', 5, 5, 0, 5),
(46, 'BLANCO', 'F', 5, 20, 1, 100),
(47, 'BLANCO', 'F', 5, 20, 0, 5),
(48, 'BLANCO', 'F', 5, 20, 0, 5),
(49, 'BLANCO', 'F', 5, 20, 0, 100),
(52, 'BLANCO', 'F', 5, 20, 0, 100),
(53, 'BLANCO', 'F', 5, 20, 1, 100);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
