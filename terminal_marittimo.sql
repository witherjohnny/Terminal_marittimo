-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mag 18, 2025 alle 12:27
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `terminal_marittimo`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `autista`
--

CREATE TABLE `autista` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `cognome` varchar(64) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_utente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `autista`
--

INSERT INTO `autista` (`id`, `nome`, `cognome`, `id_cliente`, `id_utente`) VALUES
(4, 'johnny', 'cao', 1, 14),
(5, 'test', 'test', 1, 15);

-- --------------------------------------------------------

--
-- Struttura della tabella `buono_consegna`
--

CREATE TABLE `buono_consegna` (
  `id` int(11) NOT NULL,
  `peso` decimal(10,0) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `approvato` tinyint(1) NOT NULL DEFAULT 0,
  `id_polizza` int(11) NOT NULL,
  `id_autista` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `buono_consegna`
--

INSERT INTO `buono_consegna` (`id`, `peso`, `id_cliente`, `approvato`, `id_polizza`, `id_autista`) VALUES
(3, 1, 1, 1, 2, 4),
(4, 123, 1, 1, 1, 4),
(5, 12, 1, 1, 2, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `camion`
--

CREATE TABLE `camion` (
  `id` int(11) NOT NULL,
  `targa` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `camion`
--

INSERT INTO `camion` (`id`, `targa`) VALUES
(1, 'AA123BB'),
(2, 'CC456DD'),
(3, 'EE789FF'),
(4, 'GG012HH'),
(5, 'II345JJ');

-- --------------------------------------------------------

--
-- Struttura della tabella `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `cognome` varchar(64) NOT NULL,
  `id_utente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `cliente`
--

INSERT INTO `cliente` (`id`, `nome`, `cognome`, `id_utente`) VALUES
(1, 'cliente1', 'cognomecliente1', 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `fornitore`
--

CREATE TABLE `fornitore` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `fornitore`
--

INSERT INTO `fornitore` (`id`, `nome`) VALUES
(1, 'Alfa Distribuzioni'),
(2, 'Beta Forniture'),
(3, 'Gamma S.p.A.'),
(4, 'Delta Servizi'),
(5, 'Omega Logistica');

-- --------------------------------------------------------

--
-- Struttura della tabella `guida`
--

CREATE TABLE `guida` (
  `id` int(11) NOT NULL,
  `id_camion` int(11) NOT NULL,
  `id_autista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `guida`
--

INSERT INTO `guida` (`id`, `id_camion`, `id_autista`) VALUES
(1, 1, 4),
(2, 1, 4),
(3, 1, 4),
(4, 1, 4),
(5, 1, 4);

-- --------------------------------------------------------

--
-- Struttura della tabella `merce`
--

CREATE TABLE `merce` (
  `id` int(11) NOT NULL,
  `tipologia_merce` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `merce`
--

INSERT INTO `merce` (`id`, `tipologia_merce`) VALUES
(1, 'Alimentari freschi'),
(2, 'Alimentari confezionati'),
(3, 'Elettronica di consumo'),
(4, 'Abbigliamento'),
(5, 'Calzature'),
(6, 'Libri'),
(7, 'Articoli per la casa'),
(8, 'Utensili da lavoro'),
(9, 'Prodotti per l\'igiene personale'),
(10, 'Farmaci da banco'),
(11, 'Mobili'),
(12, 'Giocattoli'),
(13, 'Componenti elettronici'),
(14, 'Materie prime industriali'),
(15, 'Ricambi auto');

-- --------------------------------------------------------

--
-- Struttura della tabella `nave`
--

CREATE TABLE `nave` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `nave`
--

INSERT INTO `nave` (`id`, `nome`) VALUES
(1, 'Nautilus'),
(2, 'Enterprise'),
(3, 'Serenity'),
(4, 'Millennium Falcon'),
(5, 'Black Pearl');

-- --------------------------------------------------------

--
-- Struttura della tabella `polizza`
--

CREATE TABLE `polizza` (
  `id` int(11) NOT NULL,
  `id_viaggio` int(11) NOT NULL,
  `id_fornitore` int(11) NOT NULL,
  `id_merce` int(11) NOT NULL,
  `peso` decimal(10,2) NOT NULL,
  `durata_franchigia` int(11) NOT NULL,
  `costo_franchigia` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `polizza`
--

INSERT INTO `polizza` (`id`, `id_viaggio`, `id_fornitore`, `id_merce`, `peso`, `durata_franchigia`, `costo_franchigia`) VALUES
(1, 10, 1, 1, 123.00, 13, 2.00),
(2, 10, 4, 4, 123.12, 13, 213.00);

-- --------------------------------------------------------

--
-- Struttura della tabella `porto`
--

CREATE TABLE `porto` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `nazionalita` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `porto`
--

INSERT INTO `porto` (`id`, `nome`, `nazionalita`) VALUES
(1, 'Porto Azzurro', 'Italia'),
(2, 'Nueva Esperanza', 'Spagna'),
(3, 'Soleil Levant', 'Francia'),
(4, 'Thalassa Eghéo', 'Grecia'),
(5, 'Nordwind Hafen', 'Germania'),
(6, 'Oceano Sereno', 'Portogallo'),
(7, 'Zwarte Water Haven', 'Paesi Bassi'),
(8, 'Brygge Nordlys', 'Norvegia'),
(9, 'Südlicher Anker', 'Austria'),
(10, 'Baltyk Brzeg', 'Polonia');

-- --------------------------------------------------------

--
-- Struttura della tabella `ritiro`
--

CREATE TABLE `ritiro` (
  `id` int(11) NOT NULL,
  `id_buono` int(11) NOT NULL,
  `id_guida` int(11) NOT NULL,
  `data` date NOT NULL,
  `approvato` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `ritiro`
--

INSERT INTO `ritiro` (`id`, `id_buono`, `id_guida`, `data`, `approvato`) VALUES
(1, 4, 4, '2025-05-18', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(11) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `ruolo` enum('cliente','fornitore','autista','admin') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `username`, `password`, `ruolo`) VALUES
(4, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin'),
(5, 'cliente', '4983a0ab83ed86e0e7213c8783940193', 'cliente'),
(14, 'autista', '3671166e85b495174f769aaaf592f85f', 'autista'),
(15, 'autista2', 'ba6c4f0701c304291eebc54e61fb0b14', 'autista');

-- --------------------------------------------------------

--
-- Struttura della tabella `viaggio`
--

CREATE TABLE `viaggio` (
  `id` int(11) NOT NULL,
  `id_nave` int(11) NOT NULL,
  `data_partenza` date NOT NULL,
  `data_allibramento` date DEFAULT NULL,
  `id_porto_arrivo` int(11) DEFAULT NULL,
  `id_porto_partenza` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `viaggio`
--

INSERT INTO `viaggio` (`id`, `id_nave`, `data_partenza`, `data_allibramento`, `id_porto_arrivo`, `id_porto_partenza`) VALUES
(1, 1, '2025-05-30', NULL, NULL, 1),
(2, 4, '2025-05-22', NULL, NULL, 6),
(3, 1, '2025-05-31', NULL, NULL, 1),
(4, 1, '2025-05-30', NULL, NULL, 1),
(5, 1, '2025-05-30', NULL, NULL, 1),
(6, 4, '2025-05-31', NULL, NULL, 1),
(7, 5, '2025-05-23', NULL, NULL, 1),
(8, 1, '2025-05-23', NULL, NULL, 1),
(9, 3, '2025-05-22', NULL, NULL, 6),
(10, 4, '2025-05-01', '2025-05-30', 8, 10);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `autista`
--
ALTER TABLE `autista`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_utente` (`id_utente`);

--
-- Indici per le tabelle `buono_consegna`
--
ALTER TABLE `buono_consegna`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_polizza` (`id_polizza`),
  ADD KEY `id_autista` (`id_autista`);

--
-- Indici per le tabelle `camion`
--
ALTER TABLE `camion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `targa` (`targa`);

--
-- Indici per le tabelle `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_utente` (`id_utente`);

--
-- Indici per le tabelle `fornitore`
--
ALTER TABLE `fornitore`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `guida`
--
ALTER TABLE `guida`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_autista` (`id_autista`),
  ADD KEY `id_camion` (`id_camion`);

--
-- Indici per le tabelle `merce`
--
ALTER TABLE `merce`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `nave`
--
ALTER TABLE `nave`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `polizza`
--
ALTER TABLE `polizza`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_viaggio` (`id_viaggio`),
  ADD KEY `id_fornitore` (`id_fornitore`),
  ADD KEY `id_merce` (`id_merce`);

--
-- Indici per le tabelle `porto`
--
ALTER TABLE `porto`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `ritiro`
--
ALTER TABLE `ritiro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cliente` (`id_buono`),
  ADD KEY `id_guida` (`id_guida`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indici per le tabelle `viaggio`
--
ALTER TABLE `viaggio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_nave` (`id_nave`),
  ADD KEY `id_porto_arrivo` (`id_porto_arrivo`),
  ADD KEY `id_porto_partenza` (`id_porto_partenza`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `autista`
--
ALTER TABLE `autista`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `buono_consegna`
--
ALTER TABLE `buono_consegna`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `camion`
--
ALTER TABLE `camion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `fornitore`
--
ALTER TABLE `fornitore`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `guida`
--
ALTER TABLE `guida`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `merce`
--
ALTER TABLE `merce`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT per la tabella `nave`
--
ALTER TABLE `nave`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `polizza`
--
ALTER TABLE `polizza`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `porto`
--
ALTER TABLE `porto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT per la tabella `ritiro`
--
ALTER TABLE `ritiro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT per la tabella `viaggio`
--
ALTER TABLE `viaggio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `autista`
--
ALTER TABLE `autista`
  ADD CONSTRAINT `autista_ibfk_2` FOREIGN KEY (`id_utente`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `autista_ibfk_3` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `buono_consegna`
--
ALTER TABLE `buono_consegna`
  ADD CONSTRAINT `buono_consegna_ibfk_1` FOREIGN KEY (`id_polizza`) REFERENCES `polizza` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `buono_consegna_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `buono_consegna_ibfk_3` FOREIGN KEY (`id_autista`) REFERENCES `autista` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `guida`
--
ALTER TABLE `guida`
  ADD CONSTRAINT `guida_ibfk_1` FOREIGN KEY (`id_autista`) REFERENCES `autista` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `guida_ibfk_2` FOREIGN KEY (`id_camion`) REFERENCES `camion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Limiti per la tabella `polizza`
--
ALTER TABLE `polizza`
  ADD CONSTRAINT `polizza_ibfk_1` FOREIGN KEY (`id_viaggio`) REFERENCES `viaggio` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `polizza_ibfk_2` FOREIGN KEY (`id_fornitore`) REFERENCES `fornitore` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `polizza_ibfk_3` FOREIGN KEY (`id_merce`) REFERENCES `merce` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `ritiro`
--
ALTER TABLE `ritiro`
  ADD CONSTRAINT `ritiro_ibfk_3` FOREIGN KEY (`id_guida`) REFERENCES `guida` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `ritiro_ibfk_4` FOREIGN KEY (`id_buono`) REFERENCES `buono_consegna` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `viaggio`
--
ALTER TABLE `viaggio`
  ADD CONSTRAINT `viaggio_ibfk_1` FOREIGN KEY (`id_porto_arrivo`) REFERENCES `porto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `viaggio_ibfk_2` FOREIGN KEY (`id_porto_partenza`) REFERENCES `porto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `viaggio_ibfk_3` FOREIGN KEY (`id_nave`) REFERENCES `nave` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
