CREATE SCHEMA `fruitjanissary`;

CREATE TABLE `fruitjanissary`.`kullanici` (
  `kullaniciID` INT NOT NULL AUTO_INCREMENT,
  `kullaniciAdi` VARCHAR(10) NOT NULL,
  `sifre` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`kullaniviID`));
  
CREATE TABLE ``fruitjanissary`.`scoretablosu` (
  `scoreID` INT NOT NULL AUTO_INCREMENT,
  `kullaniciID` INT NOT NULL,
  `kullaniciadi` VARCHAR(10) NOT NULL,
  `bitirmezamani` VARCHAR(10) NOT NULL,
  `tarih` VARCHAR(10) NOT NULL,
  `score` INT NOT NULL,
   PRIMARY KEY (`scoreID`));
