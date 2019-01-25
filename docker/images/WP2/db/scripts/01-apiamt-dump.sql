-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : db
-- Généré le :  ven. 25 jan. 2019 à 00:55
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `apiamt`
--

-- --------------------------------------------------------

--
-- Structure de la table `application_entity`
--

CREATE TABLE `application_entity` (
  `application_id` int(11) NOT NULL,
  `application_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `badge_entity`
--

CREATE TABLE `badge_entity` (
  `badge_id` int(11) NOT NULL,
  `badge_name` varchar(255) DEFAULT NULL,
  `application_application_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `event_entity`
--

CREATE TABLE `event_entity` (
  `event_id` int(11) NOT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) NOT NULL,
  `application_application_id` int(11) DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `point_scale_entity`
--

CREATE TABLE `point_scale_entity` (
  `point_scale_id` int(11) NOT NULL,
  `point_scale_name` varchar(255) DEFAULT NULL,
  `application_application_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reward_entity`
--

CREATE TABLE `reward_entity` (
  `dtype` varchar(31) NOT NULL,
  `reward_id` int(11) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `application_application_id` int(11) DEFAULT NULL,
  `event_event_id` int(11) DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL,
  `badge_badge_id` int(11) DEFAULT NULL,
  `point_scale_point_scale_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rule_entity`
--

CREATE TABLE `rule_entity` (
  `rule_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `badge_id` int(11) NOT NULL,
  `event_trigger` varchar(255) DEFAULT NULL,
  `rule_name` varchar(255) DEFAULT NULL,
  `application_application_id` int(11) DEFAULT NULL,
  `badge_badge_id` int(11) DEFAULT NULL,
  `point_scale_point_scale_id` int(11) DEFAULT NULL,
  `reward_reward_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user_entity`
--

CREATE TABLE `user_entity` (
  `user_id` int(11) NOT NULL,
  `application_application_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `application_entity`
--
ALTER TABLE `application_entity`
  ADD PRIMARY KEY (`application_id`);

--
-- Index pour la table `badge_entity`
--
ALTER TABLE `badge_entity`
  ADD PRIMARY KEY (`badge_id`),
  ADD KEY `FK4b0ssr5cbx3mp2w57e92o9i9u` (`application_application_id`);

--
-- Index pour la table `event_entity`
--
ALTER TABLE `event_entity`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `FKdfihw8cg4ldknulbi9pewnhme` (`application_application_id`),
  ADD KEY `FK92wrfvdbuld4b90d7kpxsudjs` (`user_user_id`);

--
-- Index pour la table `point_scale_entity`
--
ALTER TABLE `point_scale_entity`
  ADD PRIMARY KEY (`point_scale_id`),
  ADD KEY `FKmv0cyd02bgfya1p63pkh72g3c` (`application_application_id`);

--
-- Index pour la table `reward_entity`
--
ALTER TABLE `reward_entity`
  ADD PRIMARY KEY (`reward_id`),
  ADD KEY `FKh46onntb6crh1on9yurx8ehdq` (`application_application_id`),
  ADD KEY `FK18e8ro9dm8t26mghpb2uu8hdl` (`event_event_id`),
  ADD KEY `FKh53u45s90rtsn6eplrur8e9br` (`user_user_id`),
  ADD KEY `FK3eh4l737pkqxllcb77joh7nfh` (`badge_badge_id`),
  ADD KEY `FKpm0sngayh8mou6fj8rt7axy27` (`point_scale_point_scale_id`);

--
-- Index pour la table `rule_entity`
--
ALTER TABLE `rule_entity`
  ADD PRIMARY KEY (`rule_id`),
  ADD KEY `FKmtce509s2fm3le9ivhe8al40n` (`application_application_id`),
  ADD KEY `FK3330y58fi3ikov6kvftqahuyu` (`badge_badge_id`),
  ADD KEY `FK4sfjr04isff1ebi8mtey04268` (`point_scale_point_scale_id`),
  ADD KEY `FKh0aekm9nr2iq3budohmducdql` (`reward_reward_id`);

--
-- Index pour la table `user_entity`
--
ALTER TABLE `user_entity`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `FKguovengirlhuc1h7941nxca9h` (`application_application_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `badge_entity`
--
ALTER TABLE `badge_entity`
  MODIFY `badge_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `event_entity`
--
ALTER TABLE `event_entity`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `point_scale_entity`
--
ALTER TABLE `point_scale_entity`
  MODIFY `point_scale_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reward_entity`
--
ALTER TABLE `reward_entity`
  MODIFY `reward_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user_entity`
--
ALTER TABLE `user_entity`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `badge_entity`
--
ALTER TABLE `badge_entity`
  ADD CONSTRAINT `FK4b0ssr5cbx3mp2w57e92o9i9u` FOREIGN KEY (`application_application_id`) REFERENCES `application_entity` (`application_id`);

--
-- Contraintes pour la table `event_entity`
--
ALTER TABLE `event_entity`
  ADD CONSTRAINT `FK92wrfvdbuld4b90d7kpxsudjs` FOREIGN KEY (`user_user_id`) REFERENCES `user_entity` (`user_id`),
  ADD CONSTRAINT `FKdfihw8cg4ldknulbi9pewnhme` FOREIGN KEY (`application_application_id`) REFERENCES `application_entity` (`application_id`);

--
-- Contraintes pour la table `point_scale_entity`
--
ALTER TABLE `point_scale_entity`
  ADD CONSTRAINT `FKmv0cyd02bgfya1p63pkh72g3c` FOREIGN KEY (`application_application_id`) REFERENCES `application_entity` (`application_id`);

--
-- Contraintes pour la table `reward_entity`
--
ALTER TABLE `reward_entity`
  ADD CONSTRAINT `FK18e8ro9dm8t26mghpb2uu8hdl` FOREIGN KEY (`event_event_id`) REFERENCES `event_entity` (`event_id`),
  ADD CONSTRAINT `FK3eh4l737pkqxllcb77joh7nfh` FOREIGN KEY (`badge_badge_id`) REFERENCES `badge_entity` (`badge_id`),
  ADD CONSTRAINT `FKh46onntb6crh1on9yurx8ehdq` FOREIGN KEY (`application_application_id`) REFERENCES `application_entity` (`application_id`),
  ADD CONSTRAINT `FKh53u45s90rtsn6eplrur8e9br` FOREIGN KEY (`user_user_id`) REFERENCES `user_entity` (`user_id`),
  ADD CONSTRAINT `FKpm0sngayh8mou6fj8rt7axy27` FOREIGN KEY (`point_scale_point_scale_id`) REFERENCES `point_scale_entity` (`point_scale_id`);

--
-- Contraintes pour la table `rule_entity`
--
ALTER TABLE `rule_entity`
  ADD CONSTRAINT `FK3330y58fi3ikov6kvftqahuyu` FOREIGN KEY (`badge_badge_id`) REFERENCES `badge_entity` (`badge_id`),
  ADD CONSTRAINT `FK4sfjr04isff1ebi8mtey04268` FOREIGN KEY (`point_scale_point_scale_id`) REFERENCES `point_scale_entity` (`point_scale_id`),
  ADD CONSTRAINT `FKh0aekm9nr2iq3budohmducdql` FOREIGN KEY (`reward_reward_id`) REFERENCES `reward_entity` (`reward_id`),
  ADD CONSTRAINT `FKmtce509s2fm3le9ivhe8al40n` FOREIGN KEY (`application_application_id`) REFERENCES `application_entity` (`application_id`);

--
-- Contraintes pour la table `user_entity`
--
ALTER TABLE `user_entity`
  ADD CONSTRAINT `FKguovengirlhuc1h7941nxca9h` FOREIGN KEY (`application_application_id`) REFERENCES `application_entity` (`application_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
