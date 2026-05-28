-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bolao_bd
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bolao_bd` ;

-- -----------------------------------------------------
-- Schema bolao_bd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bolao_bd` DEFAULT CHARACTER SET utf8mb3 ;
USE `bolao_bd` ;

-- -----------------------------------------------------
-- Table `bolao_bd`.`campus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bolao_bd`.`campus` ;

CREATE TABLE IF NOT EXISTS `bolao_bd`.`campus` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bolao_bd`.`curso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bolao_bd`.`curso` ;

CREATE TABLE IF NOT EXISTS `bolao_bd`.`curso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sigla` VARCHAR(10) NOT NULL,
  `campus` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_curso_campus1_idx` (`campus` ASC) VISIBLE,
  CONSTRAINT `fk_curso_campus1`
    FOREIGN KEY (`campus`)
    REFERENCES `bolao_bd`.`campus` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 43
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bolao_bd`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bolao_bd`.`usuario` ;

CREATE TABLE IF NOT EXISTS `bolao_bd`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `perfil` VARCHAR(15) NOT NULL,
  `tipo` VARCHAR(10) NOT NULL,
  `pontuacao` INT NULL DEFAULT '0',
  `posicao` INT NULL DEFAULT '0',
  `curso` INT NULL DEFAULT NULL,
  `campus` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_usuario_curso1_idx` (`curso` ASC) VISIBLE,
  INDEX `fk_usuario_campus1_idx` (`campus` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_campus1`
    FOREIGN KEY (`campus`)
    REFERENCES `bolao_bd`.`campus` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_usuario_curso1`
    FOREIGN KEY (`curso`)
    REFERENCES `bolao_bd`.`curso` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bolao_bd`.`bolao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bolao_bd`.`bolao` ;

CREATE TABLE IF NOT EXISTS `bolao_bd`.`bolao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `criacao` DATETIME NOT NULL,
  `dono` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bolao_usuario1_idx` (`dono` ASC) VISIBLE,
  CONSTRAINT `fk_bolao_usuario1`
    FOREIGN KEY (`dono`)
    REFERENCES `bolao_bd`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bolao_bd`.`fase`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bolao_bd`.`fase` ;

CREATE TABLE IF NOT EXISTS `bolao_bd`.`fase` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `eliminatoria` TINYINT NOT NULL,
  `inicio` DATE NOT NULL,
  `fim` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bolao_bd`.`grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bolao_bd`.`grupo` ;

CREATE TABLE IF NOT EXISTS `bolao_bd`.`grupo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bolao_bd`.`selecao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bolao_bd`.`selecao` ;

CREATE TABLE IF NOT EXISTS `bolao_bd`.`selecao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sigla` VARCHAR(3) NOT NULL,
  `simbolo` VARCHAR(45) NULL DEFAULT NULL,
  `grupo` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_selecao_grupo_idx` (`grupo` ASC) VISIBLE,
  CONSTRAINT `fk_selecao_grupo`
    FOREIGN KEY (`grupo`)
    REFERENCES `bolao_bd`.`grupo` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 49
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bolao_bd`.`partida`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bolao_bd`.`partida` ;

CREATE TABLE IF NOT EXISTS `bolao_bd`.`partida` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `inicioDaPartida` DATETIME NOT NULL,
  `atualizacaoDoResultado` DATETIME NULL DEFAULT NULL,
  `local` VARCHAR(45) NOT NULL,
  `publicada` TINYINT NOT NULL,
  `golsA` INT NULL DEFAULT NULL,
  `golsB` INT NULL DEFAULT NULL,
  `tipoDeVitoria` VARCHAR(20) NULL DEFAULT 'TEMPO_NORMAL',
  `fase` INT NOT NULL,
  `selecaoA` INT NOT NULL,
  `selecaoB` INT NOT NULL,
  `vencedor` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_partida_fase1_idx` (`fase` ASC) VISIBLE,
  INDEX `fk_partida_selecao1_idx` (`selecaoA` ASC) VISIBLE,
  INDEX `fk_partida_selecao2_idx` (`selecaoB` ASC) VISIBLE,
  INDEX `fk_partida_selecao3_idx` (`vencedor` ASC) VISIBLE,
  CONSTRAINT `fk_partida_fase1`
    FOREIGN KEY (`fase`)
    REFERENCES `bolao_bd`.`fase` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partida_selecao1`
    FOREIGN KEY (`selecaoA`)
    REFERENCES `bolao_bd`.`selecao` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partida_selecao2`
    FOREIGN KEY (`selecaoB`)
    REFERENCES `bolao_bd`.`selecao` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_partida_selecao3`
    FOREIGN KEY (`vencedor`)
    REFERENCES `bolao_bd`.`selecao` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bolao_bd`.`palpite`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bolao_bd`.`palpite` ;

CREATE TABLE IF NOT EXISTS `bolao_bd`.`palpite` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dataDoPalpite` DATETIME NOT NULL,
  `ultimaAlteracao` DATETIME NULL DEFAULT NULL,
  `golsA` INT NOT NULL,
  `golsB` INT NOT NULL,
  `tipoDeVitoria` VARCHAR(20) NOT NULL,
  `pontuacao` INT NULL DEFAULT NULL,
  `selecaoVencedora` INT NULL COMMENT 'Seleção vencedora da partida. Importante na fase eliminatória.',
  `usuario` INT NOT NULL,
  `partida` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_palpite_usuario1_idx` (`usuario` ASC) VISIBLE,
  INDEX `fk_palpite_partida1_idx` (`partida` ASC) VISIBLE,
  INDEX `fk_palpite_selecao1_idx` (`selecaoVencedora` ASC) VISIBLE,
  CONSTRAINT `fk_palpite_partida1`
    FOREIGN KEY (`partida`)
    REFERENCES `bolao_bd`.`partida` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_palpite_usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `bolao_bd`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_palpite_selecao1`
    FOREIGN KEY (`selecaoVencedora`)
    REFERENCES `bolao_bd`.`selecao` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bolao_bd`.`participante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bolao_bd`.`participante` ;

CREATE TABLE IF NOT EXISTS `bolao_bd`.`participante` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bolao` INT NOT NULL,
  `usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bolao_has_usuario_usuario1_idx` (`usuario` ASC) VISIBLE,
  INDEX `fk_bolao_has_usuario_bolao1_idx` (`bolao` ASC) VISIBLE,
  CONSTRAINT `fk_bolao_has_usuario_bolao1`
    FOREIGN KEY (`bolao`)
    REFERENCES `bolao_bd`.`bolao` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_bolao_has_usuario_usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `bolao_bd`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
