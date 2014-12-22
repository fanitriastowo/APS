-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 21, 2014 at 06:59 PM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pembayaran_siswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `master_kelas`
--

CREATE TABLE IF NOT EXISTS `master_kelas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nama_kelas` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

--
-- Dumping data for table `master_kelas`
--

INSERT INTO `master_kelas` (`id`, `nama_kelas`) VALUES
(1, '7 A'),
(2, '7 B'),
(3, '7 C'),
(4, '7 D'),
(5, '7 E'),
(6, '7 F'),
(7, '7 G'),
(8, '7 H'),
(9, '7 I'),
(10, '8 A'),
(11, '8 B'),
(12, '8 C'),
(13, '8 D'),
(14, '8 E'),
(15, '8 F'),
(16, '8 G'),
(17, '8 H'),
(18, '8 I'),
(19, '9 A'),
(20, '9 B'),
(21, '9 C'),
(22, '9 D'),
(23, '9 F'),
(24, '9 G'),
(25, '9 H'),
(26, '9 I');

-- --------------------------------------------------------

--
-- Table structure for table `master_siswa`
--

CREATE TABLE IF NOT EXISTS `master_siswa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agama` varchar(255) DEFAULT NULL,
  `agama_ortu` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `alamat_ortu` varchar(255) DEFAULT NULL,
  `jenis_kelamin` varchar(255) DEFAULT NULL,
  `kelas` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `nama_ortu` varchar(255) DEFAULT NULL,
  `nis` varchar(255) DEFAULT NULL,
  `pekerjaan` varchar(255) DEFAULT NULL,
  `telp` varchar(255) DEFAULT NULL,
  `tempat_lahir` varchar(255) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

-- --------------------------------------------------------

--
-- Table structure for table `user_admin`
--

CREATE TABLE IF NOT EXISTS `user_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `asAdmin` tinyint(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user_admin`
--

INSERT INTO `user_admin` (`id`, `asAdmin`, `password`, `username`) VALUES
(1, 1, 'admin', 'admin'),
(2, 0, 'user', 'user');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
