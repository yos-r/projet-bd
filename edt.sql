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
  CONSTRAINT `fk` FOREIGN KEY (`matricule_ens`) REFERENCES `enseignant` (`matricule`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table edt.cours: ~13 rows (approximately)
INSERT INTO `cours` (`id`, `classe`, `matiere`, `num_jour`, `Jour`, `heure`, `matricule_ens`) VALUES
	(10, '1ING', 'Analyse', 1, 'Lundi', '8H', 'MA0001'),
	(12, '1ING', 'Electronique', 1, 'Lundi', '9H30Mn', 'MA0001'),
	(17, '2ING', 'Optimisation', 2, 'Mardi', '13H', 'MA0002'),
	(18, '2ING', 'IA syst experts', 1, 'Lundi', '13H', 'GL0002'),
	(19, '1ING', 'Compilation', 3, 'Mercredi', '14H30Mn', 'GL0002'),
	(22, '2ING', 'Arch. Microp.', 3, 'Mercredi', '16H', 'GE0001'),
	(26, '2ING', 'Unix', 4, 'Jeudi', '8H', 'GL0002'),
	(28, '1ING', 'Tr. Signal', 5, 'Vendredi', '8H', 'GE0001'),
	(39, '1ING', 'Syst. Exp.', 0, 'Lundi', '16H', 'GE0001'),
	(40, '2ING', 'Ondes', 1, 'Lundi', '9H30Mn', 'GE0001'),
	(49, '1ING', 'Compilation', 0, 'Lundi', '17H30Mn', 'GE0001');

-- Dumping structure for table edt.enseignant
CREATE TABLE IF NOT EXISTS `enseignant` (
  `matricule` varchar(20) NOT NULL,
  `nom` varchar(150) NOT NULL,
  `contact` varchar(50) NOT NULL,
  PRIMARY KEY (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table edt.enseignant: ~6 rows (approximately)
INSERT INTO `enseignant` (`matricule`, `nom`, `contact`) VALUES
	('GE0001', 'E F', '12345678'),
	('GL0001', 'J L', '12345678'),
	('GL0002', 'A B', '12345678'),
	('MA0001', 'C D', '12345678'),
	('MA0002', 'T U', '12345678');

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
