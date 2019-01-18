-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-01-2019 a las 19:59:06
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `madali_bd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especificaciones`
--

CREATE TABLE `especificaciones` (
  `ID_Especificacion` int(11) NOT NULL,
  `Nombre_Especificacion` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `Descripcion_Especificacion` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Duracion_Especificacion` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `ID_Proyecto` int(11) NOT NULL,
  `ID_Sprint` int(11) NOT NULL,
  `Estado_Especificacion` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupos_usuarios`
--

CREATE TABLE `grupos_usuarios` (
  `ID_Grupo` int(11) NOT NULL,
  `ID_Proyecto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectos`
--

CREATE TABLE `proyectos` (
  `ID_Proyecto` int(11) NOT NULL,
  `Nombre_Proyecto` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `Descripcion_Proyecto` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Fecha_Inicio_Proyecto` date NOT NULL,
  `Fecha_Final_Proyecto` date NOT NULL,
  `Scrum_Master_Proyecto` int(11) NOT NULL,
  `Product_Owner_Proyecto` int(11) NOT NULL,
  `ID_Grupo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sprints`
--

CREATE TABLE `sprints` (
  `ID_Sprint` int(11) NOT NULL,
  `ID_Proyecto` int(11) NOT NULL,
  `Fecha_Inicio_Sprint` date NOT NULL,
  `Fecha_Final_Sprint` date NOT NULL,
  `Duracion_Sprint` int(11) NOT NULL,
  `Estado_Sprint` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID_Usuario` int(11) NOT NULL,
  `Nombre_Usuario` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `Password_Usuario` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `Nombre_Apellidos` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `Tipo_Usuario` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `Correo_Usuario` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `ID_Grupo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `especificaciones`
--
ALTER TABLE `especificaciones`
  ADD PRIMARY KEY (`ID_Especificacion`),
  ADD KEY `Especificaciones_fk1` (`ID_Proyecto`),
  ADD KEY `Especificaciones_fk2` (`ID_Sprint`);

--
-- Indices de la tabla `grupos_usuarios`
--
ALTER TABLE `grupos_usuarios`
  ADD PRIMARY KEY (`ID_Grupo`),
  ADD KEY `Grupos_Usuarios_fk1` (`ID_Proyecto`);

--
-- Indices de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD PRIMARY KEY (`ID_Proyecto`),
  ADD KEY `Proyectos_fk1` (`ID_Grupo`),
  ADD KEY `Proyectos_fk2` (`Scrum_Master_Proyecto`),
  ADD KEY `Proyectos_fk3` (`Product_Owner_Proyecto`) USING BTREE;

--
-- Indices de la tabla `sprints`
--
ALTER TABLE `sprints`
  ADD PRIMARY KEY (`ID_Sprint`),
  ADD KEY `Sprints_fk1` (`ID_Proyecto`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID_Usuario`),
  ADD KEY `Usuarios_fk1` (`ID_Grupo`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `especificaciones`
--
ALTER TABLE `especificaciones`
  ADD CONSTRAINT `Especificaciones_fk1` FOREIGN KEY (`ID_Proyecto`) REFERENCES `proyectos` (`ID_Proyecto`),
  ADD CONSTRAINT `Especificaciones_fk2` FOREIGN KEY (`ID_Sprint`) REFERENCES `sprints` (`ID_Sprint`);

--
-- Filtros para la tabla `grupos_usuarios`
--
ALTER TABLE `grupos_usuarios`
  ADD CONSTRAINT `Grupos_Usuarios_fk1` FOREIGN KEY (`ID_Proyecto`) REFERENCES `proyectos` (`ID_Proyecto`);

--
-- Filtros para la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD CONSTRAINT `Proyectos_fk3` FOREIGN KEY (`Product_Owner_Proyecto`) REFERENCES `usuarios` (`ID_Usuario`),
  ADD CONSTRAINT `Proyectos_fk1` FOREIGN KEY (`ID_Grupo`) REFERENCES `grupos_usuarios` (`ID_Grupo`),
  ADD CONSTRAINT `Proyectos_fk2` FOREIGN KEY (`Scrum_Master_Proyecto`) REFERENCES `usuarios` (`ID_Usuario`);

--
-- Filtros para la tabla `sprints`
--
ALTER TABLE `sprints`
  ADD CONSTRAINT `Sprints_fk1` FOREIGN KEY (`ID_Proyecto`) REFERENCES `proyectos` (`ID_Proyecto`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `Usuarios_fk1` FOREIGN KEY (`ID_Grupo`) REFERENCES `grupos_usuarios` (`ID_Grupo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
