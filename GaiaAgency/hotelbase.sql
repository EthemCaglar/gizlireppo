-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 12 Kas 2023, 22:23:05
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `hotelbase`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `boarding`
--

CREATE TABLE `boarding` (
  `id` int NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `boarding`
--

INSERT INTO `boarding` (`id`, `type`) VALUES
(1, 'Ultra All Included'),
(2, 'All Included'),
(3, 'Full Board'),
(4, 'Half Board'),
(5, 'Only Bed'),
(6, 'No Alcohol Full Credit');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hotels`
--

CREATE TABLE `hotels` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `province` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `features` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `adress` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `boarding` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` bigint NOT NULL,
  `stars` enum('*','**','***','****','*****') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hotels`
--

INSERT INTO `hotels` (`id`, `name`, `city`, `province`, `features`, `adress`, `boarding`, `phone`, `stars`) VALUES
(1, 'Concierge Downtown\r\n', 'Athens', 'Kallithea', 'Free Park, Pool, Fitness Center, SPA, 7/24 Room Service', 'Nikiou 6, Athina 105 60, Greece', '34', 302103220612, '****'),
(2, 'Royal Olympic Hotel\r\n', 'Athens', 'Akadamia', 'Free Park, Free WiFi, Pool, Non-Smoking Rooms', 'Athanasiou Diakou 28, Athina 117 43 Greece', '23', 302109288400, '****'),
(3, 'Cabana Blu Hotel', 'Dodecanese', 'Kos', 'Free Park, Free WiFi, Free Airport Taxi, Pool, 7/24 Room Service', 'Dimokratias & Aneksartisias Street, Kardamaina, 85302, Greece', '124', 302242091415, '*****'),
(4, 'Hyatt Regency', 'Belgrade', 'Staro Sajmiste', 'Free Park, Free WiFi, 7/24 Room Service', 'Milentija Popovića 5, Beograd 11070, Serbia', '234', 381113011234, '***'),
(5, 'Saint Ten', 'Belgrade', 'Vracar', 'Free Park, Free WiFi, 7/24 Room Service, Pool', 'Svetog Save 10, Beograd 11000, Serbia', '235', 381114116633, '*****'),
(6, 'Don Raffaele Resort\r\n', 'Naples', 'Quartieri Spagnoli', 'Free WiFi, 7/24 Room Service, Electric Vehicle Charging Station', 'P.za Municipio, 84, 80133, Italy', '3456', 393388166079, '****'),
(7, 'Grand Hotel Vesuvio', 'Naples', 'Quartieri Spagnoli', 'Free Park, Free WiFi, Pool, 7/24 Room Service', 'Via Partenope, 45, 80121, Italy', '345', 390817640044, '***'),
(8, 'Hotel Real Palacio', 'Lisbon', 'Azul', 'Free Park, Free WiFi, Free Airport Taxi, 7/24 Room Service', 'R. Tomás Ribeiro 115, 1050-228, Portugal', '234', 351213199500, '*****'),
(9, 'Altis Avenida Hotel\r\n', 'Lisbon', 'Bairro Alto', 'Free Park, Free WiFi, Pets Allowed, 7/24 Room Service', 'R. 1º de Dezembro 120, 1200-360, Portugal', '234', 351210440000, '****'),
(10, 'Sheraton Fallsview Hotel', 'Ontario', 'Niagara', 'Free Parking, Indoor and Outdoor Pool, 7/24 Room Service', '5875 Falls Avenue, L2K 3K7 Niagara Falls, Canada', '2345', 19053744445, '*****');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `reservation`
--

CREATE TABLE `reservation` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `adult` int NOT NULL,
  `child` int NOT NULL,
  `room_id` int NOT NULL,
  `startDate` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `endDate` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `reservation`
--

INSERT INTO `reservation` (`id`, `name`, `adult`, `child`, `room_id`, `startDate`, `endDate`) VALUES
(20, 'Tuğçe Kandemir', 2, 1, 1, '2014-10-15', '2014-10-18'),
(22, 'Boya', 1, 1, 4, '2024-06-17', '2024-06-20');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `rooms`
--

CREATE TABLE `rooms` (
  `id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `season_id` int NOT NULL,
  `boarding_id` int NOT NULL,
  `room_typeID` int NOT NULL,
  `available` int NOT NULL,
  `adult_price` int NOT NULL,
  `child_price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `rooms`
--

INSERT INTO `rooms` (`id`, `hotel_id`, `season_id`, `boarding_id`, `room_typeID`, `available`, `adult_price`, `child_price`) VALUES
(1, 3, 2, 4, 2, 5, 500, 300),
(4, 4, 1, 2, 3, 5, 400, 300),
(6, 6, 1, 6, 1, 2, 1000, 800);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room_type`
--

CREATE TABLE `room_type` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `bed` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `maxCostumer` int NOT NULL,
  `room_size` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room_type`
--

INSERT INTO `room_type` (`id`, `name`, `bed`, `maxCostumer`, `room_size`) VALUES
(1, 'Double', '1 Large', 2, 30),
(2, 'Double', '2 Single', 2, 35),
(3, 'Family', '1 Double 2 Single', 4, 45),
(4, 'Family Suite', '1 Extra Large 2 Sofa Bed', 4, 38),
(5, 'Cabana', '1 Extra Large', 2, 20),
(6, 'Suite', '1 Extra Large', 2, 35);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `seasons`
--

CREATE TABLE `seasons` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `startDate` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `seasons`
--

INSERT INTO `seasons` (`id`, `name`, `startDate`) VALUES
(1, 'High Season', '2024-04-30'),
(2, 'Low Season', '2024-08-30'),
(3, 'Shoulder Season', '2024-12-30');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `usern` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `type` enum('agent_worker','admin') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `name`, `usern`, `pass`, `type`) VALUES
(1, 'Andrew Price', 'DonutAdmin', '1234', 'admin'),
(2, 'Yuki Tsunoda', 'TorroRosso', '4122', 'agent_worker'),
(4, 'Cihan', 'Bono', '4321', 'agent_worker');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `boarding`
--
ALTER TABLE `boarding`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `hotels`
--
ALTER TABLE `hotels`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room_type`
--
ALTER TABLE `room_type`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `seasons`
--
ALTER TABLE `seasons`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `boarding`
--
ALTER TABLE `boarding`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Tablo için AUTO_INCREMENT değeri `hotels`
--
ALTER TABLE `hotels`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Tablo için AUTO_INCREMENT değeri `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Tablo için AUTO_INCREMENT değeri `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `room_type`
--
ALTER TABLE `room_type`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `seasons`
--
ALTER TABLE `seasons`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
