-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 17, 2017 at 11:03 PM
-- Server version: 5.5.46-log
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `customerreviewproduct`
-- Notes: (1) one statement by line
--        (2) indexed are created by spring - hibernate

-- --------------------------------------------------------

--
-- Table structure for table `customerreview`
--

CREATE TABLE IF NOT EXISTS `customerreview` (`id` bigint(20) NOT NULL, `alias` varchar(50) NOT NULL, `approvalstatus` varchar(15) NOT NULL, `blocked` bit(1) NOT NULL, `comment` varchar(255) NOT NULL, `headline` varchar(50) NOT NULL, `language` varchar(15) NOT NULL, `productid` bigint(20) NOT NULL, `rating` double NOT NULL, `userid` bigint(20) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customerreview`
--

INSERT INTO `customerreview` (`id`, `alias`, `approvalstatus`, `blocked`, `comment`, `headline`, `language`, `productid`, `rating`, `userid`) VALUES (1, 'anonymous', 'none', b'0', 'I like Hegel, he is one of my favorites philosophers', 'Philosofers', 'english', 1, 1, 0), (2, 'anonymous', 'none', b'0', 'I like Hegel too, one of the greatest german philosophers', 'Philosofers', 'english', 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (`id` bigint(20) NOT NULL, `description` varchar(2048) NOT NULL, `name` varchar(50) NOT NULL, `status` int(11) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `description`, `name`, `status`) VALUES (1, 'Spinoza''s magnum opus, Ethics, was published posthumously in 1677. The work opposed Descartes'' philosophy on mind–body dualism, and earned Spinoza recognition as one of Western philosophy''s most important thinkers. In the Ethics, ''Spinoza wrote the last indisputable Latin masterpiece, and one in which the refined conceptions of medieval philosophy are finally turned against themselves and destroyed entirely''. Georg Wilhelm Friedrich Hegel said, ''You are either a Spinozist or not a philosopher at all.'' His philosophical accomplishments and moral character prompted 20th-century philosopher Gilles Deleuze to name him the ''prince'' of philosophers.', 'Baruch Spinoza', 0), (2, 'Hegel''s principal achievement is his development of a distinctive articulation of idealism sometimes termed ''absolute idealism'', in which the dualisms of, for instance, mind and nature and subject and object are overcome. His philosophy of spirit conceptually integrates psychology, the state, history, art, religion, and philosophy. His account of the master–slave dialectic has been highly influential, especially in 20th-century France.[16] Of special importance is his concept of spirit (Geist: sometimes also translated as ''mind'') as the historical manifestation of the logical concept and the ''sublation'' (Aufhebung: integration without elimination or reduction) of seemingly contradictory or opposing factors; examples include the apparent opposition between nature and freedom and between immanence and transcendence. Hegel has been seen in the 20th century as the originator of the thesis, antithesis, synthesis triad; however, as an explicit phrase, it originated with Johann Gottlieb Fichte.', 'Georg Wilhelm Friedrich Hegel', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (`id` bigint(20) NOT NULL, `email` varchar(50) NOT NULL, `enabled` bit(1) NOT NULL, `firstname` varchar(50) NOT NULL, `lastpasswordresetdate` datetime NOT NULL, `lastname` varchar(50) NOT NULL, `password` varchar(100) NOT NULL, `username` varchar(50) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `enabled`, `firstname`, `lastpasswordresetdate`, `lastname`, `password`, `username`) VALUES (1, 'jorge@gaviotaways.com', b'1', 'Jorge', '2017-09-15 20:00:00', 'Whittembury', 'fakePassword', ''), (2, 'fabiolagaviota@hotmail.com', b'1', 'Fabiola', '2017-09-16 20:00:00', 'Palomino', 'fakePassword', 'FabiolaCecilia');

--
-- AUTO_INCREMENT for table `customerreview`
--
ALTER TABLE `customerreview` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;