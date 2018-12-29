# SQL Manager 2007 for MySQL 4.2.0.2
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : db_battlemonsters


SET FOREIGN_KEY_CHECKS=0;

USE `db_battlemonsters`;

#
# Structure for the `tb_tipo_item` table :
#

DROP TABLE IF EXISTS `tb_tipo_item`;

CREATE TABLE `tb_tipo_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Structure for the `tb_item` table :
#

DROP TABLE IF EXISTS `tb_item`;

CREATE TABLE `tb_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idTipoItem` int(11) DEFAULT NULL,
  `nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `preco` int(11) NOT NULL,
  `valor` int(11) NOT NULL,
  `descricao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_61phbcimiu960ubkf7bxgfuj9` (`idTipoItem`),
  CONSTRAINT `FK_61phbcimiu960ubkf7bxgfuj9` FOREIGN KEY (`idTipoItem`) REFERENCES `tb_tipo_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Structure for the `tb_jogador` table :
#

DROP TABLE IF EXISTS `tb_jogador`;

CREATE TABLE `tb_jogador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `senha` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sexo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Structure for the `tb_tipo_monstro` table :
#

DROP TABLE IF EXISTS `tb_tipo_monstro`;

CREATE TABLE `tb_tipo_monstro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Structure for the `tb_monstro` table :
#

DROP TABLE IF EXISTS `tb_monstro`;

CREATE TABLE `tb_monstro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idTipoMonstro` int(11) DEFAULT NULL,
  `nome` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `baseLvlMin` int(11) NOT NULL,
  `baseLvlMax` int(11) NOT NULL,
  `minHpPorLvl` int(11) NOT NULL,
  `maxHpPorLvl` int(11) NOT NULL,
  `minAtkPorLvl` int(11) NOT NULL,
  `maxAtkPorLvl` int(11) NOT NULL,
  `minDefPorLvl` int(11) NOT NULL,
  `maxDefPorLvl` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lm24d2hm3gcqsvhyutko12qgg` (`idTipoMonstro`),
  CONSTRAINT `FK_lm24d2hm3gcqsvhyutko12qgg` FOREIGN KEY (`idTipoMonstro`) REFERENCES `tb_tipo_monstro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Structure for the `tb_jogador_monstro` table :
#

DROP TABLE IF EXISTS `tb_jogador_monstro`;

CREATE TABLE `tb_jogador_monstro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idJogador` int(11) DEFAULT NULL,
  `idMonstro` int(11) DEFAULT NULL,
  `flagPrincipal` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `flagReserva` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `lvl` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `atk` int(11) NOT NULL,
  `def` int(11) NOT NULL,
  `hp` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7em2si3nno8pdh7ve6cypy3u1` (`idJogador`),
  KEY `FK_m6ewiwk81s3rlqe1xhwnfrscm` (`idMonstro`),
  CONSTRAINT `FK_m6ewiwk81s3rlqe1xhwnfrscm` FOREIGN KEY (`idMonstro`) REFERENCES `tb_monstro` (`id`),
  CONSTRAINT `FK_7em2si3nno8pdh7ve6cypy3u1` FOREIGN KEY (`idJogador`) REFERENCES `tb_jogador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for the `tb_jogador` table  (LIMIT 0,500)
#

INSERT INTO `tb_jogador` (`id`, `login`, `nome`, `senha`, `sexo`) VALUES
  (1,'sando1','Sando1','123456','M');

COMMIT;

#
# Data for the `tb_tipo_monstro` table  (LIMIT 0,500)
#

INSERT INTO `tb_tipo_monstro` (`id`, `descricao`) VALUES
  (1,'Água'),
  (2,'Fogo'),
  (3,'Terra'),
  (4,'Vento');

COMMIT;

#
# Data for the `tb_monstro` table  (LIMIT 0,500)
#

INSERT INTO `tb_monstro` (`id`, `idTipoMonstro`, `nome`, `baseLvlMin`, `baseLvlMax`, `minHpPorLvl`, `maxHpPorLvl`, `minAtkPorLvl`, `maxAtkPorLvl`, `minDefPorLvl`, `maxDefPorLvl`) VALUES
  (1,3,'Sapograma',1,99,1,3,1,3,2,4),
  (4,2,'Lagartochama',1,99,1,3,2,4,1,3),
  (7,1,'Tartaragua',1,99,2,4,1,3,1,3);

COMMIT;

#
# Data for the `tb_jogador_monstro` table  (LIMIT 0,500)
#

INSERT INTO `tb_jogador_monstro` (`id`, `idJogador`, `idMonstro`, `flagPrincipal`, `flagReserva`, `nome`, `lvl`, `atk`, `def`, `hp`) VALUES
  (1,1,7,'S','N','Donatelo',1,16,18,23);

COMMIT;

