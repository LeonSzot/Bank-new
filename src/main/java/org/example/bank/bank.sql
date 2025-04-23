-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2025 at 02:45 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `blik`
--

CREATE TABLE `blik` (
  `ID` int(11) NOT NULL,
  `KontoID` bigint(20) NOT NULL,
  `NumerBlik` int(11) NOT NULL,
  `Aktywny` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `blik`
--

INSERT INTO `blik` (`ID`, `KontoID`, `NumerBlik`, `Aktywny`) VALUES
(1, 1, 66997, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `karty`
--

CREATE TABLE `karty` (
  `ID` int(11) NOT NULL,
  `KontoID` int(11) DEFAULT NULL,
  `NumerKarty` varchar(16) DEFAULT NULL,
  `TerminWaznosci` date DEFAULT NULL,
  `CVV` char(3) DEFAULT NULL,
  `Limit` decimal(15,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `karty`
--

INSERT INTO `karty` (`ID`, `KontoID`, `NumerKarty`, `TerminWaznosci`, `CVV`, `Limit`) VALUES
(1, 1, '1111222233334444', '2026-12-31', '123', 10000.00),
(2, 2, '5555666677778888', '2025-11-30', '456', 15000.00),
(3, 3, '9999000011112222', '2027-10-31', '789', 20000.00);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klienci`
--

CREATE TABLE `klienci` (
  `ID` int(11) NOT NULL,
  `Imie` varchar(50) DEFAULT NULL,
  `Nazwisko` varchar(50) DEFAULT NULL,
  `Adres` varchar(100) DEFAULT NULL,
  `Telefon` varchar(15) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `DataUrodzenia` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `klienci`
--

INSERT INTO `klienci` (`ID`, `Imie`, `Nazwisko`, `Adres`, `Telefon`, `Email`, `DataUrodzenia`) VALUES
(1, 'Jan', 'Kowalski', 'ul. Lipowa 10, Warszawa', '123456789', 'jan.kowalski@example.com', '1985-05-12'),
(2, 'Anna', 'Nowak', 'ul. Długa 15, Kraków', '987654321', 'anna.nowak@example.com', '1990-07-22');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `konta`
--

CREATE TABLE `konta` (
  `ID` int(11) NOT NULL,
  `KlientID` int(11) DEFAULT NULL,
  `NumerKonta` varchar(20) DEFAULT NULL,
  `TypKonta` enum('Oszczędnościowe','Rachunek bieżący','Inwestycyjne') DEFAULT NULL,
  `Saldo` decimal(15,2) DEFAULT NULL,
  `login` varchar(15) NOT NULL,
  `haslo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `konta`
--

INSERT INTO `konta` (`ID`, `KlientID`, `NumerKonta`, `TypKonta`, `Saldo`, `login`, `haslo`) VALUES
(1, 1, '12345678901234567890', 'Rachunek bieżący', 5000.00, 'japaklapku17', 'awe2'),
(2, 1, '09876543210987654321', 'Oszczędnościowe', 15000.00, 'siemsiema1121', 'sdfsf4'),
(3, 2, '11223344556677889900', 'Rachunek bieżący', 7000.00, 'dicho', 'gfdh43563');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `transakcje`
--

CREATE TABLE `transakcje` (
  `ID` int(11) NOT NULL,
  `KartaID` int(11) DEFAULT NULL,
  `Data` date DEFAULT NULL,
  `Kwota` decimal(15,2) DEFAULT NULL,
  `TypTransakcji` enum('Zakup','Wpłata','Wypłata') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transakcje`
--

INSERT INTO `transakcje` (`ID`, `KartaID`, `Data`, `Kwota`, `TypTransakcji`) VALUES
(1, 1, '2025-01-01', 250.00, 'Zakup'),
(2, 1, '2025-01-02', 500.00, 'Wpłata'),
(3, 2, '2025-01-03', 700.00, 'Zakup'),
(4, 3, '2025-01-04', 1000.00, 'Wypłata');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `blik`
--
ALTER TABLE `blik`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `karty`
--
ALTER TABLE `karty`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NumerKarty` (`NumerKarty`),
  ADD KEY `KontoID` (`KontoID`);

--
-- Indeksy dla tabeli `klienci`
--
ALTER TABLE `klienci`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `konta`
--
ALTER TABLE `konta`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NumerKonta` (`NumerKonta`),
  ADD KEY `KlientID` (`KlientID`);

--
-- Indeksy dla tabeli `transakcje`
--
ALTER TABLE `transakcje`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `KartaID` (`KartaID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blik`
--
ALTER TABLE `blik`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `karty`
--
ALTER TABLE `karty`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `klienci`
--
ALTER TABLE `klienci`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `konta`
--
ALTER TABLE `konta`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transakcje`
--
ALTER TABLE `transakcje`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `karty`
--
ALTER TABLE `karty`
  ADD CONSTRAINT `karty_ibfk_1` FOREIGN KEY (`KontoID`) REFERENCES `konta` (`ID`) ON DELETE CASCADE;

--
-- Constraints for table `konta`
--
ALTER TABLE `konta`
  ADD CONSTRAINT `konta_ibfk_1` FOREIGN KEY (`KlientID`) REFERENCES `klienci` (`ID`) ON DELETE CASCADE;

--
-- Constraints for table `transakcje`
--
ALTER TABLE `transakcje`
  ADD CONSTRAINT `transakcje_ibfk_1` FOREIGN KEY (`KartaID`) REFERENCES `karty` (`ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
