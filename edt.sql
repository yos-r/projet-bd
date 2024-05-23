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
CREATE DATABASE IF NOT EXISTS `edt_isi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `edt_isi`;

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
	(1, '1ING', 'Compilation', 1, 'Lundi', '8H', 'GL0001'),
	(2, '1ING', 'Business Communication', 1, 'Lundi', '9H40', 'MA0005'),
	(3, '1ING', 'Ondes', 1, 'Lundi', '11H20', 'GE0001'),
	(4, '1ING', 'Analyse Numérique', 2, 'Mardi', '8H', 'MA0001'),
	(5, '1ING', 'Telecom', 2, 'Mardi', '9H40', 'GE0002'),
	(6, '1ING', 'Analyse Appliquée', 2, 'Mardi', '11H20', 'MA0002'),
	(7, '1ING', 'Prog Objet', 3, 'Mercredi', '8H', 'GL0002'),
	(8, '1ING', 'Tr. Signal', 3, 'Mercredi', '9H20', 'GE0001'),
	(9, '1ING', 'Syst. Exp.', 4, 'Jeudi', '13H50', 'GL0001'),
	(10, '1ING', 'Base de données', 4, 'Jeudi', '15H10', 'GL0004'),
	(11, '2ING', 'Concept Prog Interf', 1, 'Lundi', '8H', 'GL0003'),
	(12, '2ING', 'Stochastique', 1, 'Lundi', '9H40', 'MA0003'),
	(13, '2ING', 'Parallélisme Calcul', 1, 'Lundi', '11H20', 'GL0001'),
	(14, '2ING', 'Qualité et tests', 2, 'Mardi', '8H', 'GL0002'),
	(15, '2ING', 'Analyse de données', 2, 'Mardi', '9H40', 'GL0004'),
	(16, '2ING', 'Dev Web Avancé', 2, 'Mardi', '11H20', 'GL0005'),
	(17, '2ING', 'Computer Vision', 3, 'Mercredi', '8H', 'GL0001'),
	(18, '2ING', 'Internet Marketing', 3, 'Mercredi', '9H20', 'MA0005'),
	(19, '2ING', 'Dev App Mobiles', 4, 'Jeudi', '13H50', 'GL0005'),
	(20, '2ING', 'Ing Processus Mérier', 4, 'Jeudi', '15H10', 'MA0003'),
	(21, '3ING', 'IA Distribuée', 1, 'Lundi', '8H', 'GL0004'),
	(22, '3ING', 'Archi Cloud', 1, 'Lundi', '9H40', 'GL0001'),
	(23, '3ING', 'Aprentissage Artif', 1, 'Lundi', '11H20', 'GE0002'),
	(24, '3ING', 'Innov Entreprenariat', 2, 'Mardi', '8H', 'MA0002'),
	(25, '3ING', 'Tech Blockchain', 2, 'Mardi', '9H40', 'GE0005'),
	(26, '3ING', 'MCI', 2, 'Mardi', '11H20', 'GL0001'),
	(27, '3ING', 'Tech Calcul Intellig', 3, 'Mercredi', '8H', 'MA0002'),
	(28, '3ING', 'Verif Form Syst Comm', 3, 'Mercredi', '9H20', 'GL0002'),
	(29, '3ING', 'Anal Mod Arch Logic', 4, 'Jeudi', '13H50', 'GL0004'),
	(30, '3ING', 'Anglais', 4, 'Jeudi', '15H10', 'MA0005');

-- Dumping structure for table edt.enseignant
CREATE TABLE IF NOT EXISTS `enseignant` (
  `matricule` varchar(20) NOT NULL,
  `nom` varchar(150) NOT NULL,
  `contact` varchar(50) NOT NULL,
  PRIMARY KEY (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table edt.enseignant: ~6 rows (approximately)
INSERT INTO `enseignant` (`matricule`, `nom`, `contact`) VALUES
	('GR0001', 'Monia Najjar', 'monia.najjar@isi.utm.tn'),
	('GR0002', 'Azza Aouled Zied', 'azza.ouled-zaid@laposte.net'),
	('GR0003', 'Med Anis Loghmani', 'anisloghmari@yahoo.fr'),
	('GR0004', 'Sourour Mhenni', 'sourour.mhenni@isi.utm.tn'),
	('GR0005', 'Mourad Ouertani', 'ouertani.mourad@laposte.net'),
	('GL0001', 'Ezzeddine Zagrouba', 'e.zagrouba@gmail.com'),
	('GL0002', 'Sahbi Bahroun', 'sahbi.bahroun@gmail.com'),
	('GL0003', 'Amel Borgi', 'amel.borgi1@gmail.com'),
	('GL0004', 'Naoufel Kraiem', 'naoufel.kraiem@gmail.com'),
	('GL0005', 'Adel Khalfallah', 'adel.khalfallah@isi.utm.tn'),
	('GE0001', 'Najiba Belaaj', 'najiba.bellaj@isi.utm.tn'),
	('GE0002', 'Ali Tlili', 'ali.tlili@isi.utm.tn'),
	('GE0003', 'Farhat Yatim', 'farhat.yatim@isi.rnu.tn'),
	('GE0004', 'Mediha Ghedamsi', 'ghedamsi_mediha@yahoo.fr'),
	('GE0005', 'Imen Ayachi', 'ayachiimen@gmail.com'),
	('MA0001', 'Fathi Dkhil', 'fathi.dkhil@isi.rnu.tn'),
	('MA0002', 'Cyrine Baccar', 'cyrinebaccar@yahoo.fr'),
	('MA0003', 'Lobna Derbel', 'lobnaderbel@yahoo.fr'),
	('MA0004', 'Hejer Metoui', 'hajermetoui@enit.rnu.tn'),
	('MA0005', 'Ines Rekik', 'i.rekik@yahoo.fr');

-- Dumping structure for view edt.enseignant_cours
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `enseignant_cours` (
	`mcoursatricule` VARCHAR(20) NOT NULL COLLATE 'utf8mb3_general_ci',
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
