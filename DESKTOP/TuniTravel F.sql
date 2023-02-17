-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 07 mars 2022 à 17:03
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `tunitravel`
--

-- --------------------------------------------------------

--
-- Structure de la table `hotel`
--

CREATE TABLE `hotel` (
  `id_hotel` int(20) NOT NULL,
  `ref` int(20) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `datedeb` date NOT NULL,
  `datefin` date NOT NULL,
  `image` varchar(200) NOT NULL,
  `ville` varchar(60) NOT NULL,
  `prixhotel` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `hotel`
--

INSERT INTO `hotel` (`id_hotel`, `ref`, `nom`, `description`, `datedeb`, `datefin`, `image`, `ville`, `prixhotel`) VALUES
(50, 122, '1Yosri Resort', 'Pieds dans l\'eaull', '2022-03-04', '2022-03-06', '', 'Tunis', 124.5),
(51, 124, 'Helmi Residency', 'Hotel 5*', '2022-03-04', '2022-03-06', 'kkkk.jpg', 'Sousse', 130.2),
(52, 12231, 'Yosri Resortmmm', 'Pieds dans l\'eau', '2022-03-04', '2022-03-06', '9b977695ea48eb18a2f798717297c076.jpg', 'Tunis', 1114);

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `contenu` longtext COLLATE utf8_unicode_ci NOT NULL,
  `image` longtext COLLATE utf8_unicode_ci NOT NULL,
  `dateajout` date NOT NULL,
  `datemodif` date NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  `nb_like` int(11) DEFAULT NULL,
  `prix` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `offre`
--

INSERT INTO `offre` (`id`, `titre`, `contenu`, `image`, `dateajout`, `datemodif`, `idUser`, `nb_like`, `prix`) VALUES
(50, 'firas', 'AA', '1.PNG', '2021-03-04', '2021-03-18', 1, 1, 200),
(51, 'firas', 'uilj', '1.PNG', '2021-03-04', '2021-03-18', 1, 6, 111),
(52, 'firascxxc', 'uilj', '1.PNG', '2021-03-04', '2021-03-18', 1, 1, 200),
(53, 'de', 'de', 'Capture.PNG', '2022-04-02', '2022-04-06', 0, 1, 290),
(54, 'cvzezc', 'cvzc', 'Capture.PNG', '2022-03-15', '2022-03-27', 0, 1, 300),
(55, 'testrr', 'test1', 'Capture.PNG', '2022-03-25', '2022-04-20', 0, 0, 300);

-- --------------------------------------------------------

--
-- Structure de la table `offre_aimer`
--

CREATE TABLE `offre_aimer` (
  `id_offre_aimer` int(11) NOT NULL,
  `id_offre` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `offre_aimer`
--

INSERT INTO `offre_aimer` (`id_offre_aimer`, `id_offre`, `id_user`, `date`) VALUES
(126, 50, 1, '2021-03-04'),
(127, 52, 1, '2021-03-04'),
(128, 51, 1, '2021-03-04'),
(129, 54, 1, '2022-03-04'),
(130, 53, 1, '2022-03-07');

-- --------------------------------------------------------

--
-- Structure de la table `reservationoffre`
--

CREATE TABLE `reservationoffre` (
  `id` int(11) NOT NULL,
  `idoffre` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `nbpersonne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservationoffre`
--

INSERT INTO `reservationoffre` (`id`, `idoffre`, `prix`, `nbpersonne`) VALUES
(1, 50, 500, 2),
(2, 50, 400, 2),
(3, 53, 580, 2),
(4, 53, 1160, 4),
(5, 54, 1200, 4),
(6, 53, 870, 3),
(7, 53, 870, 3),
(8, 53, 870, 3),
(9, 53, 580, 2),
(10, 55, 600, 2),
(11, 54, 600, 2),
(12, 53, 2900, 10);

-- --------------------------------------------------------

--
-- Structure de la table `reservation_hotel`
--

CREATE TABLE `reservation_hotel` (
  `id_resH` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `prix_total` double NOT NULL,
  `id_hotel` int(11) NOT NULL,
  `nbrperso` int(11) NOT NULL,
  `nbrch` int(11) NOT NULL,
  `typech` varchar(200) NOT NULL,
  `prix_res` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation_hotel`
--

INSERT INTO `reservation_hotel` (`id_resH`, `id_user`, `date_debut`, `date_fin`, `prix_total`, `id_hotel`, `nbrperso`, `nbrch`, `typech`, `prix_res`) VALUES
(10, 45, '2022-03-03', '2022-03-03', 0, 50, 2, 2, 'Chambre1 Single Logement Demi Pension\nChambre2 Triple    Demi Pension\nnull    null', 0),
(17, 46, '2022-04-06', '2022-04-11', 0, 50, 1, 1, 'Chambre1 Single Logement Demi Pension\nnull    null\nnull    null', 124.5),
(33, 46, '2022-03-05', '2022-03-06', 0, 142, 2, 2, 'Chambre1 Single Logement Pension Complète\nChambre2 Single    Demi Pension\nnull    null', 174.5);

-- --------------------------------------------------------

--
-- Structure de la table `reservation_vol`
--

CREATE TABLE `reservation_vol` (
  `id_resV` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `prix_total` double NOT NULL,
  `id_vol` int(11) NOT NULL,
  `nbrpsg` int(11) NOT NULL,
  `aerocomp` varchar(20) NOT NULL,
  `classe` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation_vol`
--

INSERT INTO `reservation_vol` (`id_resV`, `id_user`, `date_debut`, `date_fin`, `prix_total`, `id_vol`, `nbrpsg`, `aerocomp`, `classe`) VALUES
(12, 99, '2022-03-05', '2022-03-07', 1230, 20, 9, 'Tunisair', 'First Class'),
(13, 99, '2022-03-05', '2022-03-07', 700, 16, 8, 'Tunisair', 'First Class'),
(15, 99, '2022-03-05', '2022-03-07', 1230, 20, 9, 'Tunisair', 'First Class'),
(17, 99, '2022-03-05', '2022-03-07', 1230, 20, 3, 'Tunisair', 'Business Class'),
(20, 99, '2022-03-03', '2022-04-02', 400, 16, 2, 'Tunisair', 'Economy Class'),
(21, 99, '2022-02-15', '2022-02-27', 700, 16, 2, 'Tunisair', 'First Class'),
(22, 99, '2022-02-15', '2022-02-27', 700, 18, 2, 'Tunisair', 'First Class'),
(23, 99, '2022-02-15', '2022-02-27', 700, 18, 2, 'Tunisair', 'Economy Class'),
(26, 99, '2022-03-03', '2022-04-02', 400, 16, 2, 'Tunisair', 'Business Class'),
(27, 99, '2022-03-03', '2022-04-02', 550, 16, 2, 'Tunisair', 'Business Class'),
(28, 99, '2022-03-03', '2022-04-02', 850, 16, 2, 'Tunisair', 'First Class'),
(29, 99, '2022-03-03', '2022-04-02', 850, 16, 2, 'Tunisair', 'Economy Class'),
(30, 99, '2022-03-03', '2022-04-02', 150, 16, 2, 'Tunisair', 'Economy Class'),
(31, 99, '2022-02-15', '2022-02-27', 1420, 18, 2, 'Tunisair', 'First Class'),
(32, 99, '2022-02-15', '2022-02-27', 1120, 18, 2, 'Tunisair', 'Business Class'),
(33, 99, '2022-03-05', '2022-03-07', 1230, 20, 3, 'Tunisair', 'First Class');

-- --------------------------------------------------------

--
-- Structure de la table `rreservation_voiture`
--

CREATE TABLE `rreservation_voiture` (
  `id_resvoi` int(11) NOT NULL,
  `matricule` varchar(30) NOT NULL,
  `permis` int(20) NOT NULL,
  `nbr_de_jour` int(10) NOT NULL,
  `prix_res` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `rreservation_voiture`
--

INSERT INTO `rreservation_voiture` (`id_resvoi`, `matricule`, `permis`, `nbr_de_jour`, `prix_res`) VALUES
(3, '112TUN2555', 1010101, 2, 900),
(4, '112TUN2555', 12341234, 3, 150),
(6, '123TUN123', 34567, 3, 1234),
(7, '123TUN123', 12345678, 3, 123),
(8, '205TUN1050', 12341212, 3, 390),
(10, '123TUN123', 12345678, 2, 120),
(11, '112TUN2555', 12345678, 2, 1200),
(12, '112TUN2555', 123456, 3, 900),
(14, '112TUN2555', 124124, 2, 600),
(15, '112TUN2555', 123456, 2, 600),
(16, '112TUN2555', 123456, 12, 3600);

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

CREATE TABLE `voiture` (
  `matricule` varchar(30) NOT NULL,
  `modele` varchar(30) NOT NULL,
  `marque` varchar(30) NOT NULL,
  `transmission` varchar(30) NOT NULL,
  `categorie` varchar(30) NOT NULL,
  `image` varchar(200) NOT NULL,
  `capacite` int(10) NOT NULL,
  `prix` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `voiture`
--

INSERT INTO `voiture` (`matricule`, `modele`, `marque`, `transmission`, `categorie`, `image`, `capacite`, `prix`) VALUES
('112TUN2555', 'range', 'landrover', 'Automatique', 'Grande', 'png-clipart-2018-kia-sportage-kia-motors-car-2016-kia-sportage-kia.png', 7, 300),
('123TUN123', 'panda', 'fiat', 'Manuelle', 'Petite', '135-1358580_mountain-4k-ultra-hd-wallpaper-fond-d-cran.jpg', 5, 60),
('205TUN1050', 'golf7', 'vw', 'Manuelle', 'Moyenne', 'golf7.png', 5, 130),
('222TUN4567', 'C63 AMG', 'mercedes', 'auto', 'moyenne', '', 5, 250);

-- --------------------------------------------------------

--
-- Structure de la table `vol`
--

CREATE TABLE `vol` (
  `id_vol` int(20) NOT NULL,
  `ref` varchar(20) NOT NULL,
  `depart` varchar(40) NOT NULL,
  `destination` varchar(40) NOT NULL,
  `date_d` date NOT NULL,
  `date_r` date NOT NULL,
  `prix_v` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `vol`
--

INSERT INTO `vol` (`id_vol`, `ref`, `depart`, `destination`, `date_d`, `date_r`, `prix_v`) VALUES
(16, 'TN213', 'Tunis', 'Monastir', '2022-03-03', '2022-04-02', 150),
(18, 'TN214', 'Tunis', 'Paris', '2022-02-15', '2022-02-27', 720),
(20, 'TN216', 'Brazil', 'Tunis', '2022-03-05', '2022-03-07', 1500),
(22, 'TN217', 'Dubai', 'Marseille', '2022-03-09', '2022-03-16', 400),
(23, 'TN514', 'Berlin', 'Tunis', '2022-03-19', '2022-03-24', 560),
(24, 'TN612', 'Amsterdam', 'Tunis', '2022-03-29', '2022-04-09', 700),
(25, 'TN811', 'Tunis', 'Cairo', '2022-03-22', '2022-03-31', 813),
(26, 'TN219', 'Tunis', 'Algérie', '2022-03-17', '2022-03-18', 400);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id_hotel`),
  ADD UNIQUE KEY `ref` (`ref`),
  ADD KEY `nom` (`nom`);

--
-- Index pour la table `offre`
--
ALTER TABLE `offre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_54928197FE6E88D7` (`idUser`);

--
-- Index pour la table `offre_aimer`
--
ALTER TABLE `offre_aimer`
  ADD PRIMARY KEY (`id_offre_aimer`);

--
-- Index pour la table `reservationoffre`
--
ALTER TABLE `reservationoffre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idoffre` (`idoffre`);

--
-- Index pour la table `reservation_hotel`
--
ALTER TABLE `reservation_hotel`
  ADD PRIMARY KEY (`id_resH`),
  ADD KEY `id_hotel` (`id_hotel`);

--
-- Index pour la table `reservation_vol`
--
ALTER TABLE `reservation_vol`
  ADD PRIMARY KEY (`id_resV`);

--
-- Index pour la table `rreservation_voiture`
--
ALTER TABLE `rreservation_voiture`
  ADD PRIMARY KEY (`id_resvoi`),
  ADD UNIQUE KEY `id_resvoi` (`id_resvoi`),
  ADD KEY `matricule` (`matricule`);

--
-- Index pour la table `voiture`
--
ALTER TABLE `voiture`
  ADD PRIMARY KEY (`matricule`),
  ADD UNIQUE KEY `matricule` (`matricule`);

--
-- Index pour la table `vol`
--
ALTER TABLE `vol`
  ADD PRIMARY KEY (`id_vol`),
  ADD UNIQUE KEY `ref` (`ref`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id_hotel` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT pour la table `offre`
--
ALTER TABLE `offre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT pour la table `offre_aimer`
--
ALTER TABLE `offre_aimer`
  MODIFY `id_offre_aimer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;

--
-- AUTO_INCREMENT pour la table `reservationoffre`
--
ALTER TABLE `reservationoffre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `reservation_hotel`
--
ALTER TABLE `reservation_hotel`
  MODIFY `id_resH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT pour la table `reservation_vol`
--
ALTER TABLE `reservation_vol`
  MODIFY `id_resV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT pour la table `rreservation_voiture`
--
ALTER TABLE `rreservation_voiture`
  MODIFY `id_resvoi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `vol`
--
ALTER TABLE `vol`
  MODIFY `id_vol` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `rreservation_voiture`
--
ALTER TABLE `rreservation_voiture`
  ADD CONSTRAINT `rreservation_voiture_ibfk_1` FOREIGN KEY (`matricule`) REFERENCES `voiture` (`matricule`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
