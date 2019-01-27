-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-01-2019 a las 11:42:45
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.2.11

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

--
-- Volcado de datos para la tabla `especificaciones`
--

INSERT INTO `especificaciones` (`ID_Especificacion`, `Nombre_Especificacion`, `Descripcion_Especificacion`, `Duracion_Especificacion`, `ID_Proyecto`, `ID_Sprint`, `Estado_Especificacion`) VALUES
(1, 'Hacer login pulsando el botón enter', 'En la pantalla de login, haz que cuando pulses el botón ENTER haga lo mismo que al hacer click en botón', '1h', 1, 1, 'En desarollo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupos_usuarios`
--

CREATE TABLE `grupos_usuarios` (
  `ID_Grupo` int(11) NOT NULL,
  `ID_Proyecto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `grupos_usuarios`
--

INSERT INTO `grupos_usuarios` (`ID_Grupo`, `ID_Proyecto`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectos`
--

CREATE TABLE `proyectos` (
  `ID_Proyecto` int(11) NOT NULL,
  `Nombre_Proyecto` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `Descripcion_Proyecto` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Fecha_Inicio_Proyecto` date,
  `Fecha_Final_Proyecto` date,
  `Scrum_Master_Proyecto` int(11) NOT NULL,
  `Product_Owner_Proyecto` int(11) NOT NULL,
  `ID_Grupo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `proyectos`
--

INSERT INTO `proyectos` (`ID_Proyecto`, `Nombre_Proyecto`, `Descripcion_Proyecto`, `Fecha_Inicio_Proyecto`, `Fecha_Final_Proyecto`, `Scrum_Master_Proyecto`, `Product_Owner_Proyecto`, `ID_Grupo`) VALUES
(1, 'Gestor de proyectos Scrum', 'Desarollar un gestor de proyectos Scrum en el que habrá usuarios con diferentes permisos', '2019-01-16', '2019-02-06', 4, 1, 1);

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

--
-- Volcado de datos para la tabla `sprints`
--

INSERT INTO `sprints` (`ID_Sprint`, `ID_Proyecto`, `Fecha_Inicio_Sprint`, `Fecha_Final_Sprint`, `Duracion_Sprint`, `Estado_Sprint`) VALUES
(1, 1, '2019-01-16', '2019-01-23', 18, 'En progreso');

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
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID_Usuario`, `Nombre_Usuario`, `Password_Usuario`, `Nombre_Apellidos`, `Tipo_Usuario`, `Correo_Usuario`, `ID_Grupo`) VALUES
(1, 'amurtaza', '1234', 'Ali Murtaza', 'Product Owner', 'amurtaza@madali.com', 1),
(2, 'davidg', '1234', 'David Garcia', 'Administrator', 'davidg@madali.com', 1),
(3, 'marcb', '1234', 'Marc Boakye', 'Developer', 'mboakye@madali.com', 1),
(4, 'root', 'root', 'Root', 'Scrum Master', 'root@root.com', 1),
(5, 'lzabala', 'root', 'Leandro Zabala', 'Scrum Master', 'lzabala@xtec.cat', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `especificaciones`
--
ALTER TABLE `especificaciones`
  ADD PRIMARY KEY (`ID_Especificacion`);

--
-- Indices de la tabla `grupos_usuarios`
--
ALTER TABLE `grupos_usuarios`
  ADD PRIMARY KEY (`ID_Grupo`);

--
-- Indices de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD PRIMARY KEY (`ID_Proyecto`);

--
-- Indices de la tabla `sprints`
--
ALTER TABLE `sprints`
  ADD PRIMARY KEY (`ID_Sprint`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID_Usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `especificaciones`
--
ALTER TABLE `especificaciones`
  MODIFY `ID_Especificacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `grupos_usuarios`
--
ALTER TABLE `grupos_usuarios`
  MODIFY `ID_Grupo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  MODIFY `ID_Proyecto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `sprints`
--
ALTER TABLE `sprints`
  MODIFY `ID_Sprint` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `ID_Usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;