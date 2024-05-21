-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for edt
CREATE DATABASE IF NOT EXISTS `edt` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `edt`;

-- Dumping structure for table edt.cours
CREATE TABLE IF NOT EXISTS `cours` (
  `id` int NOT NULL AUTO_INCREMENT,
  `classe` varchar(30) NOT NULL,
  `matiere` varchar(80) NOT NULL,
  `num_jour` smallint DEFAULT NULL,
  `Jour` varchar(20) NOT NULL,
  `heure` varchar(20) NOT NULL,
  `matricule_ens` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk` (`matricule_ens`),
  CONSTRAINT `fk` FOREIGN KEY (`matricule_ens`) REFERENCES `enseignant` (`matricule`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table edt.cours: ~24 rows (approximately)
INSERT INTO `cours` (`id`, `classe`, `matiere`, `num_jour`, `Jour`, `heure`, `matricule_ens`) VALUES
	(8, '6eme', 'FRAN', 1, 'LUNDI', '3eme  et 4eme H', 'ens1'),
	(9, '6eme', 'SVT', 1, 'LUNDI', '5eme et 6eme H', 'ens5'),
	(10, '1ING', 'PC', 2, 'MARDI', '1ere et 2eme H', 'ens7'),
	(11, '6eme', 'MATH', 2, 'MARDI', '3eme  et 4eme H', 'ens2'),
	(12, '6eme', 'IC', 2, 'MARDI', '5eme H', 'ens8'),
	(13, '6eme', 'MATH', 3, 'MERCREDI', '1ere H', 'ens2'),
	(14, '6eme', 'SVT', 3, 'MERCREDI', '2eme H', 'ens5'),
	(15, '6eme', 'FRAN', 3, 'MERCREDI', '3eme  et 4eme H', 'ens1'),
	(16, '6eme', 'FRAN', 4, 'JEUDI', '1ere et 2eme H', 'ens1'),
	(17, '6eme', 'PC', 4, 'JEUDI', '3eme  et 4eme H', 'ens7'),
	(18, '6eme', 'ANG', 3, 'MERCREDI', '5eme et 6eme H', 'ens6'),
	(19, '6eme', 'ANG', 5, 'VENDREDI', '1ere H', 'ens6'),
	(20, '5eme', 'FRAN', 1, 'LUNDI', '1ere et 2eme H', 'ens1'),
	(21, '5eme', 'MATH', 1, 'LUNDI', '3eme  et 4eme H', 'ens2'),
	(22, '5eme', 'IC', 1, 'LUNDI', '5eme H', 'ens8'),
	(23, '5eme', 'MATH', 2, 'MARDI', '1ere et 2eme H', 'ens2'),
	(25, '5eme', 'FRAN', 2, 'MARDI', '5eme et 6eme H', 'ens1'),
	(26, '5eme', 'ANG', 3, 'MERCREDI', '1ere et 2eme H', 'ens6'),
	(27, '5eme', 'MATH', 3, 'MERCREDI', '3eme H', 'ens2'),
	(28, '5eme', 'IC', 3, 'MERCREDI', '4eme H', 'ens8'),
	(29, '5eme', 'SVT', 4, 'JEUDI', '1ere et 2eme H', 'ens5'),
	(30, '5eme', 'FRAN', 4, 'JEUDI', '3eme  et 4eme H', 'ens1'),
	(31, '5eme', 'SVT', 2, 'MARDI', '3eme H', 'ens5'),
	(37, '6eme', 'Ondes', 1, 'LUNDI', '3eme  et 4eme H', '333'),
	(38, '6eme', 'Réseaux', 5, 'VENDREDI', '2eme H', '333');

-- Dumping structure for table edt.enseignant
CREATE TABLE IF NOT EXISTS `enseignant` (
  `matricule` varchar(20) NOT NULL,
  `nom` varchar(150) NOT NULL,
  `contact` varchar(50) NOT NULL,
  PRIMARY KEY (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table edt.enseignant: ~9 rows (approximately)
INSERT INTO `enseignant` (`matricule`, `nom`, `contact`) VALUES
	('333', 'P L', '12345678'),
	('ens1', 'R S', '123456'),
	('ens2', 'N V', '123456'),
	('ens3', 'J L', '12345678'),
	('ens4', 'C D', '1234567'),
	('ens5', 'M N', '123456'),
	('ens6', 'A B', '1234567'),
	('ens7', 'T U', '12345678'),
	('ens8', 'E F', '456789');

-- Dumping structure for view edt.enseignant_cours
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `enseignant_cours` (
	`matricule` VARCHAR(20) NOT NULL COLLATE 'utf8mb3_general_ci',
	`nom` VARCHAR(150) NOT NULL COLLATE 'utf8mb3_general_ci',
	`contact` VARCHAR(50) NOT NULL COLLATE 'utf8mb3_general_ci',
	`id` INT(10) NOT NULL,
	`classe` VARCHAR(30) NOT NULL COLLATE 'utf8mb3_general_ci',
	`matiere` VARCHAR(80) NOT NULL COLLATE 'utf8mb3_general_ci',
	`num_jour` SMALLINT(5) NULL,
	`jour` VARCHAR(20) NOT NULL COLLATE 'utf8mb3_general_ci',
	`heure` VARCHAR(20) NOT NULL COLLATE 'utf8mb3_general_ci'
) ENGINE=MyISAM;

-- Dumping structure for view edt.enseignant_cours
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `enseignant_cours`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `enseignant_cours` AS select `enseignant`.`matricule` AS `matricule`,`enseignant`.`nom` AS `nom`,`enseignant`.`contact` AS `contact`,`cours`.`id` AS `id`,`cours`.`classe` AS `classe`,`cours`.`matiere` AS `matiere`,`cours`.`num_jour` AS `num_jour`,`cours`.`Jour` AS `jour`,`cours`.`heure` AS `heure` from (`enseignant` join `cours` on((`enseignant`.`matricule` = `cours`.`matricule_ens`)));

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
