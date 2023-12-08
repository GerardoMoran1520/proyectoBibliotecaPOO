-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 08-12-2023 a las 02:22:42
-- Versión del servidor: 5.7.42-log
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pooproyecto1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargo`
--

DROP TABLE IF EXISTS `cargo`;
CREATE TABLE IF NOT EXISTS `cargo` (
  `IDCARGO` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRECARGO` varchar(20) DEFAULT NULL,
  `ESTADO` bit(1) DEFAULT NULL,
  PRIMARY KEY (`IDCARGO`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cargo`
--

INSERT INTO `cargo` (`IDCARGO`, `NOMBRECARGO`, `ESTADO`) VALUES
(1, 'ADMINISTRADOR', b'1'),
(2, 'ESTUDIANTE', b'1'),
(3, 'DOCENTE', b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuraciones`
--

DROP TABLE IF EXISTS `configuraciones`;
CREATE TABLE IF NOT EXISTS `configuraciones` (
  `idConfiguracion` int(11) NOT NULL AUTO_INCREMENT,
  `maxEjemplaresPrestables` int(11) DEFAULT NULL,
  `moraDiaria` double DEFAULT NULL,
  `anioMoraDiaria` int(11) DEFAULT NULL,
  PRIMARY KEY (`idConfiguracion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documentos`
--

DROP TABLE IF EXISTS `documentos`;
CREATE TABLE IF NOT EXISTS `documentos` (
  `IDDOCUMENTO` int(11) NOT NULL AUTO_INCREMENT,
  `TIPODOCUMENTO` varchar(50) DEFAULT NULL,
  `TITULO` varchar(255) DEFAULT NULL,
  `AUTOR` varchar(255) DEFAULT NULL,
  `UBICACIONFISICA` varchar(255) DEFAULT NULL,
  `CANTIDADTOTAL` int(11) DEFAULT NULL,
  `CANTIDADDISPONIBLE` int(11) DEFAULT NULL,
  `ANIOPUBLICACION` int(11) DEFAULT NULL,
  `ESTADO` bit(1) DEFAULT NULL,
  PRIMARY KEY (`IDDOCUMENTO`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `documentos`
--

INSERT INTO `documentos` (`IDDOCUMENTO`, `TIPODOCUMENTO`, `TITULO`, `AUTOR`, `UBICACIONFISICA`, `CANTIDADTOTAL`, `CANTIDADDISPONIBLE`, `ANIOPUBLICACION`, `ESTADO`) VALUES
(1, 'libro', 'don quijote', 'cervantes', 'pasillo 10 estante 4', 1, 1, 2000, b'1'),
(2, 'libro', 'programando en java ', 'java', 'pasillo 4 estante 2', 2, 1, 2011, b'1'),
(5, 'asda', 'Como salvar el ciclo en 2 dias', 'fdsfsd', 'dsfsd', 2, 1, 2023, b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
CREATE TABLE IF NOT EXISTS `prestamos` (
  `IDPRESTAMO` int(11) NOT NULL AUTO_INCREMENT,
  `IDUSUARIO` int(11) NOT NULL,
  `IDDOCUMENTO` int(11) NOT NULL,
  `FECHAPRESTAMO` varchar(20) CHARACTER SET utf8 NOT NULL,
  `FECHADEVOLUCION` varchar(20) CHARACTER SET utf8 NOT NULL,
  `MORA` int(11) DEFAULT NULL,
  `ESTADO` bit(1) DEFAULT NULL,
  PRIMARY KEY (`IDPRESTAMO`),
  KEY `IDUSARIO` (`IDUSUARIO`),
  KEY `IDDOCUMENTO` (`IDDOCUMENTO`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `prestamos`
--

INSERT INTO `prestamos` (`IDPRESTAMO`, `IDUSUARIO`, `IDDOCUMENTO`, `FECHAPRESTAMO`, `FECHADEVOLUCION`, `MORA`, `ESTADO`) VALUES
(10, 4, 2, '25/02/23', '28/02/23', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `IDUSUARIO` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBREUSUARIO` varchar(20) DEFAULT NULL,
  `CLAVE` varchar(10) DEFAULT NULL,
  `ESTADO` bit(1) DEFAULT NULL,
  `IDCARGO` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDUSUARIO`),
  KEY `FK_USUARIO_CARGO` (`IDCARGO`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`IDUSUARIO`, `NOMBREUSUARIO`, `CLAVE`, `ESTADO`, `IDCARGO`) VALUES
(1, 'GERARDO', '123', b'1', 1),
(2, 'RAFAEL', '2323', b'1', 3),
(3, 'ROBERTO', '123', b'1', 1),
(4, 'MANUEL', '123', b'1', 2),
(5, 'ALEXANDRA', '122', b'1', 2),
(19, 'asdas', '123', b'1', 3);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`IDUSUARIO`) REFERENCES `usuario` (`IDUSUARIO`),
  ADD CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`IDDOCUMENTO`) REFERENCES `documentos` (`IDDOCUMENTO`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK_USUARIO_CARGO` FOREIGN KEY (`IDCARGO`) REFERENCES `cargo` (`IDCARGO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
