-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 18, 2016 at 08:49 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `android`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `book_id` int(11) NOT NULL,
  `isbn` varchar(13) NOT NULL,
  `title` varchar(300) NOT NULL,
  `type` int(3) NOT NULL,
  `detail` tinytext NOT NULL,
  `cover` tinytext NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`book_id`, `isbn`, `title`, `type`, `detail`, `cover`, `created_at`, `updated_at`) VALUES
(1, '9786162870378', '52 เคล็ดวิชาเปลี่ยนคนธรรมดาให้เป็นอัจฉริยะ (The Little Book of Talent)', 2, 'เคยสงสัยไหมว่าอัจฉริยะกับคนธรรมดาต่างกันตรงไหน? ทำไมคนที่ดูเผินๆ เหมือนกับคุณทุกอย่างถึ', '5855ce9677568.jpg', '2016-12-17 23:47:34', NULL),
(2, '9786167852034', 'ทฤษฎีความงาม', 8, 'ทฤษฎีความงาม เล่มนี้ ได้สำรวจองค์ประกอบพื้นฐานของศิลปะ การจัดวางองค์ประกอบศิลป์ และเทคน??', '5855cf7fb67ef.jpg', '2016-12-17 23:51:27', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `fine`
--

CREATE TABLE `fine` (
  `fine_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `fine` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fine`
--

INSERT INTO `fine` (`fine_id`, `member_id`, `fine`) VALUES
(1, 2, 15);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `member_id` int(11) NOT NULL,
  `student_id` varchar(8) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'member',
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`member_id`, `student_id`, `password`, `role`, `created_at`) VALUES
(1, 'admin', '123456', 'admin', NULL),
(2, '13560323', '123456', 'member', NULL),
(3, '13560259', '123456', 'member', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `rentlist`
--

CREATE TABLE `rentlist` (
  `rent_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `rent_date` varchar(13) DEFAULT NULL,
  `deadline_date` varchar(13) DEFAULT NULL,
  `sendback_date` varchar(13) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rentlist`
--

INSERT INTO `rentlist` (`rent_id`, `member_id`, `book_id`, `rent_date`, `deadline_date`, `sendback_date`, `status`) VALUES
(1, 2, 2, '2016-12-18', '2016-12-25', '2016-12-18', 1);

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

CREATE TABLE `type` (
  `type_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type`
--

INSERT INTO `type` (`type_id`, `name`) VALUES
(1, 'วิทยาการคอมพิวเตอร์ สารสนเทศ และงานทั่วไป'),
(2, 'ปรัชญาและจิตวิทยา'),
(3, 'ศาสนา'),
(4, 'สังคมศาสตร์'),
(5, 'ภาษา'),
(6, 'วิทยาศาสตร์'),
(7, 'เทคโนโลยี'),
(8, 'ศิลปะและนันทนาการ'),
(9, 'วรรณกรรม'),
(10, 'ประวัติศาสตร์และภูมิศาสตร์');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `fine`
--
ALTER TABLE `fine`
  ADD PRIMARY KEY (`fine_id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`member_id`);

--
-- Indexes for table `rentlist`
--
ALTER TABLE `rentlist`
  ADD PRIMARY KEY (`rent_id`);

--
-- Indexes for table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`type_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `fine`
--
ALTER TABLE `fine`
  MODIFY `fine_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `member_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `rentlist`
--
ALTER TABLE `rentlist`
  MODIFY `rent_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `type`
--
ALTER TABLE `type`
  MODIFY `type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
