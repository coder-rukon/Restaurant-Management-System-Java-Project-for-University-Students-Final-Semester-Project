-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 07, 2018 at 10:11 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rms`
--
CREATE DATABASE IF NOT EXISTS `rms` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `rms`;

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `order_id` int(10) NOT NULL,
  `price` double NOT NULL,
  `is_paid` tinyint(1) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `product_id` varchar(50) NOT NULL,
  `product_quantity` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `oreder_date` date NOT NULL,
  `is_served` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `user_id`, `product_id`, `product_quantity`, `price`, `oreder_date`, `is_served`) VALUES
(1, 1, '9', '5', 0, '2017-05-14', 1),
(2, 1, '9', '30', 0, '2017-05-14', 0),
(3, 52, '6', '1', 40, '2017-05-14', 0),
(4, 54, '12', '5', 80, '2017-05-14', 0),
(5, 54, '7', '1', 55, '2017-05-14', 0),
(6, 52, '9', '9', 110, '2017-05-14', 0),
(7, 52, '12', '5', 80, '2017-05-14', 0),
(8, 52, '12', '5', 80, '2017-05-14', 0),
(9, 52, '9', '8', 110, '2017-05-14', 0),
(10, 52, '8', '8', 25, '2017-05-14', 0),
(11, 52, '10', '1', 59, '2017-05-14', 0),
(12, 52, '8', '1', 25, '2017-05-14', 0),
(13, 52, '8', '1', 25, '2017-05-14', 0),
(14, 52, '13', '1', 260, '2017-05-14', 0),
(15, 52, '1', '1', 10, '2017-05-14', 0),
(16, 52, '2', '20', 20, '2017-05-14', 0),
(17, 52, '13', '150', 260, '2017-05-14', 0),
(18, 52, '13', '5', 260, '2018-06-29', 0);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `quantity` int(20) NOT NULL,
  `unit_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `quantity`, `unit_price`) VALUES
(1, 'Product 7', 52, 10),
(2, 'Product 6', 52, 20),
(3, 'Product 5', 100, 15),
(4, 'Product 3', 868, 30),
(5, 'Product 2 update', 10, 40),
(6, 'Product 1', 200, 40),
(7, 'ALUM FOIL', 30, 55),
(8, 'LEG QUARTER 40LB CASE', 152, 25),
(9, 'BREAST QUARTER CHICKEN', 120, 110),
(10, 'BONELESS CHICKEN THIGHS', 110, 59),
(11, 'BEEF BRISKET', 100, 220),
(12, 'Update Data', 320, 80),
(13, 'Pizza', 30, 260),
(14, 'Burger', 250, 120);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `pass` varchar(150) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `rules` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `user_name`, `pass`, `full_name`, `email`, `mobile`, `rules`) VALUES
(1, 'admin', '123', 'Md Rukon Shekh', 'rukon.info@gmail.com', '0173345', 'admin'),
(47, 'sohel', '12345', 'sohel rana', 'sohelran05@gmail.com', ' 01916400222', 'customer'),
(48, 'sumon', '12345', 'Sumon hasan', 'sohelran05@gmail.com', '01916400222', 'customer'),
(49, 'lima', '12345', 'Lima ', 'sohelran05@gmail.com', '01916400222 ', 'customer'),
(50, 'annika', '12345', 'annika ', 'sohelran05@gmail.com', '01916400222 ', 'customer'),
(51, 'karim', '12345', 'korim', 'sohelran05@gmail.com', '01916400222 ', 'customer'),
(52, 'rukon1', '123', 'Md Mizanur Rahman', 'mizan@relaxbd.com', '01733462856', 'customer'),
(53, 'rahim ', '12345', 'rahim', 'sohelran05@gmail.com', '01916400222 ', 'customer'),
(54, 'rukon2', '123', 'Ariful islam', 'arif@hotmail.com', '01632639472', 'customer'),
(55, 'razu', '12345', 'razu', 'sohelran05@gmail.com', '01916400222', 'customer'),
(56, 'mina', '12345', 'mina', 'sohelran05@gmail.com', '01916400222', 'customer'),
(57, 'Rony', '1234', 'AmanUallahAman ', 'auarony92@gmail.com', '01733738983', 'customer'),
(58, 'razu4', '1', 'Razu Ahmmed', 'razu@gmail.com', '1234', 'admin'),
(59, 'rukon33', '123', 'Rukon shekh', 'sfds@edd.com', '44444', 'admin'),
(60, '', '', '', '', '', 'admin'),
(61, 'asad', '123', 'Asaduzzaman Noor', 'asad@ymail.com', '01718003301', 'customer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
