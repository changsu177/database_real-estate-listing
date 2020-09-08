-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`PropertyContact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PropertyContact` ;

CREATE TABLE IF NOT EXISTS `mydb`.`PropertyContact` (
  `PropertyContactKey` INT NOT NULL,
  `ContactName` VARCHAR(100) NOT NULL,
  `ContactPhone1` VARCHAR(50) NOT NULL,
  `ContactPhone2` VARCHAR(50) NULL DEFAULT NULL,
  `Active` TINYINT NOT NULL DEFAULT '1',
  PRIMARY KEY (`PropertyContactKey`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`PropertyLocation_zip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PropertyLocation_zip` ;

CREATE TABLE IF NOT EXISTS `mydb`.`PropertyLocation_zip` (
  `Zip` CHAR(10) NOT NULL,
  PRIMARY KEY (`Zip`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`PropertyLocation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PropertyLocation` ;

CREATE TABLE IF NOT EXISTS `mydb`.`PropertyLocation` (
  `PropertyLocationKey` INT NOT NULL,
  `State` CHAR(2) NULL DEFAULT NULL,
  `City` VARCHAR(45) NULL DEFAULT NULL,
  `Street1` VARCHAR(45) NULL DEFAULT NULL,
  `Street2` VARCHAR(45) NULL DEFAULT NULL,
  `Zip` CHAR(10) NOT NULL,
  PRIMARY KEY (`PropertyLocationKey`),
  INDEX `fk_PropertyLocation_PropertyLocationFeature1_idx` (`Zip` ASC) VISIBLE,
  CONSTRAINT `fk_PropertyLocation_PropertyLocationFeature1`
    FOREIGN KEY (`Zip`)
    REFERENCES `mydb`.`PropertyLocation_zip` (`Zip`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`Property`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Property` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Property` (
  `PropertyKey` INT NOT NULL,
  `PropertyName` TEXT NOT NULL,
  `PropertyDescription` TEXT NOT NULL,
  `PropertySize` INT NOT NULL,
  `AddTime` DATETIME NULL DEFAULT NULL,
  `UpdateTime` DATETIME NULL DEFAULT NULL,
  `PropertyContactFK` INT NULL DEFAULT NULL,
  `PropertyLocationFK` INT NULL DEFAULT NULL,
  `Status` ENUM('ACTIVE', 'NOT ACTIVE') NOT NULL,
  `DefaultPrice` INT NOT NULL,
  PRIMARY KEY (`PropertyKey`),
  INDEX `fk_Property_PropertyContact1_idx` (`PropertyContactFK` ASC) VISIBLE,
  INDEX `fk_Property_PropertyLocation1_idx` (`PropertyLocationFK` ASC) VISIBLE,
  CONSTRAINT `fk_Property_PropertyContact1`
    FOREIGN KEY (`PropertyContactFK`)
    REFERENCES `mydb`.`PropertyContact` (`PropertyContactKey`),
  CONSTRAINT `fk_Property_PropertyLocation1`
    FOREIGN KEY (`PropertyLocationFK`)
    REFERENCES `mydb`.`PropertyLocation` (`PropertyLocationKey`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`Booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Booking` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Booking` (
  `id` INT NOT NULL,
  `property_id` INT NOT NULL,
  `start_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  `price` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `property_id_5` (`property_id` ASC) VISIBLE,
  CONSTRAINT `property_id_5`
    FOREIGN KEY (`property_id`)
    REFERENCES `mydb`.`Property` (`PropertyKey`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`Feature`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Feature` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Feature` (
  `FeatureKey` INT NOT NULL,
  `FeatureType` ENUM('BEDROOM', 'BATHROOM') NOT NULL,
  `Description` VARCHAR(200) NULL,
  `Count` INT NOT NULL,
  PRIMARY KEY (`FeatureKey`),
  UNIQUE INDEX `unique` (`FeatureType` ASC, `Count` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`FeaturePropertyMapping`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`FeaturePropertyMapping` ;

CREATE TABLE IF NOT EXISTS `mydb`.`FeaturePropertyMapping` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `property_id` INT NOT NULL,
  `feature_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `property_id` (`property_id` ASC) VISIBLE,
  INDEX `feature_id` (`feature_id` ASC) VISIBLE,
  CONSTRAINT `feature_id`
    FOREIGN KEY (`feature_id`)
    REFERENCES `mydb`.`Feature` (`FeatureKey`),
  CONSTRAINT `property_id`
    FOREIGN KEY (`property_id`)
    REFERENCES `mydb`.`Property` (`PropertyKey`))
ENGINE = InnoDB
AUTO_INCREMENT = 124923
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`User` ;

CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `UserName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `gender` ENUM('MALE', 'FEMALE', 'OTHER') NULL DEFAULT NULL,
  UNIQUE INDEX `UserName_UNIQUE` (`UserName` ASC) VISIBLE,
  PRIMARY KEY (`UserName`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`Host`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Host` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Host` (
  `host_since` DATETIME NULL DEFAULT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UserName`),
  CONSTRAINT `fk_Host_User1`
    FOREIGN KEY (`UserName`)
    REFERENCES `mydb`.`User` (`UserName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`HostPropertyMapping`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`HostPropertyMapping` ;

CREATE TABLE IF NOT EXISTS `mydb`.`HostPropertyMapping` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `property_id` INT NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `property_id_3` (`property_id` ASC) VISIBLE,
  INDEX `fk_HostPropertyMapping_Host1_idx` (`UserName` ASC) VISIBLE,
  CONSTRAINT `property_id_3`
    FOREIGN KEY (`property_id`)
    REFERENCES `mydb`.`Property` (`PropertyKey`),
  CONSTRAINT `fk_HostPropertyMapping_Host1`
    FOREIGN KEY (`UserName`)
    REFERENCES `mydb`.`Host` (`UserName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`PropertyImage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PropertyImage` ;

CREATE TABLE IF NOT EXISTS `mydb`.`PropertyImage` (
  `ProertyImageKey` INT NOT NULL,
  `Image` BLOB NULL DEFAULT NULL,
  `PropertyFK` INT NOT NULL,
  PRIMARY KEY (`ProertyImageKey`),
  INDEX `fk_PropertyImage_Property1_idx` (`PropertyFK` ASC) VISIBLE,
  CONSTRAINT `fk_PropertyImage_Property1`
    FOREIGN KEY (`PropertyFK`)
    REFERENCES `mydb`.`Property` (`PropertyKey`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`Review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Review` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` ENUM('1', '2', '3', '4', '5') NULL DEFAULT NULL,
  `comments` TEXT(500) NULL DEFAULT NULL,
  `Property_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Review_Property1_idx` (`Property_id` ASC) VISIBLE,
  CONSTRAINT `fk_Review_Property1`
    FOREIGN KEY (`Property_id`)
    REFERENCES `mydb`.`Property` (`PropertyKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`ZipCode`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ZipCode` ;

CREATE TABLE IF NOT EXISTS `mydb`.`ZipCode` (
  `ZipCodeKey` INT NOT NULL,
  `Zip` CHAR(10) NULL,
  PRIMARY KEY (`ZipCodeKey`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PropertyContact1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PropertyContact1` ;

CREATE TABLE IF NOT EXISTS `mydb`.`PropertyContact1` (
  `PropertyContactKey` INT NOT NULL,
  `ContactName` VARCHAR(100) NULL,
  `ContactPhone1` CHAR(12) NULL,
  `ContactPhone2` CHAR(12) NULL,
  `Active` TINYBLOB NULL,
  PRIMARY KEY (`PropertyContactKey`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Status` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Status` (
  `StatusKey` INT NOT NULL,
  `StatusDescription` VARCHAR(100) NULL,
  PRIMARY KEY (`StatusKey`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Architect`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Architect` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Architect` (
  `ArchitectKey` INT NOT NULL,
  `ArchitectType` VARCHAR(200) NULL,
  `OtherDescription` VARCHAR(200) NULL,
  PRIMARY KEY (`ArchitectKey`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PropertyStyle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PropertyStyle` ;

CREATE TABLE IF NOT EXISTS `mydb`.`PropertyStyle` (
  `PropertyStyleKey` INT NOT NULL,
  `StyleType` VARCHAR(200) NULL,
  `OtherDescription` VARCHAR(200) NULL,
  PRIMARY KEY (`PropertyStyleKey`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Feature1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Feature1` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Feature1` (
  `FeatureKey` INT NOT NULL,
  `FeatureType` VARCHAR(200) NULL,
  `OtherDescription` VARCHAR(200) NULL,
  PRIMARY KEY (`FeatureKey`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Porperty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Porperty` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Porperty` (
  `PorpertyKey` INT NOT NULL,
  `PorpertyName` VARCHAR(45) NULL,
  `PropertyDescription` VARCHAR(400) NULL,
  `Bedroom` DECIMAL(2,1) NULL,
  `Bathroom` DECIMAL(2,1) NULL,
  `ProertyPicture` BLOB NULL,
  `ProertySize` INT NULL,
  `OtherDescription` VARCHAR(300) NULL,
  `AddTime` DATETIME NULL,
  `UpdateTime` DATETIME NULL,
  `ZipCodeFK` INT NOT NULL,
  `PropertyContactFK` INT NOT NULL,
  `StatusFK` INT NOT NULL,
  `ArchitectFK` INT NOT NULL,
  `PropertyStyleFK` INT NOT NULL,
  `FeatureFK` INT NOT NULL,
  `State` VARCHAR(2) NULL,
  `City` VARCHAR(45) NULL,
  `Street1` VARCHAR(45) NULL,
  `Street2` VARCHAR(45) NULL,
  INDEX `fk_Porperty_ZipCode_idx` (`ZipCodeFK` ASC) VISIBLE,
  INDEX `fk_Porperty_PropertyContact1_idx` (`PropertyContactFK` ASC) VISIBLE,
  INDEX `fk_Porperty_Status1_idx` (`StatusFK` ASC) VISIBLE,
  INDEX `fk_Porperty_Architect1_idx` (`ArchitectFK` ASC) VISIBLE,
  INDEX `fk_Porperty_PropertyStyle1_idx` (`PropertyStyleFK` ASC) VISIBLE,
  INDEX `fk_Porperty_Feature1_idx` (`FeatureFK` ASC) VISIBLE,
  PRIMARY KEY (`PorpertyKey`),
  CONSTRAINT `fk_Porperty_ZipCode`
    FOREIGN KEY (`ZipCodeFK`)
    REFERENCES `mydb`.`ZipCode` (`ZipCodeKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Porperty_PropertyContact1`
    FOREIGN KEY (`PropertyContactFK`)
    REFERENCES `mydb`.`PropertyContact1` (`PropertyContactKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Porperty_Status1`
    FOREIGN KEY (`StatusFK`)
    REFERENCES `mydb`.`Status` (`StatusKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Porperty_Architect1`
    FOREIGN KEY (`ArchitectFK`)
    REFERENCES `mydb`.`Architect` (`ArchitectKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Porperty_PropertyStyle1`
    FOREIGN KEY (`PropertyStyleFK`)
    REFERENCES `mydb`.`PropertyStyle` (`PropertyStyleKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Porperty_Feature1`
    FOREIGN KEY (`FeatureFK`)
    REFERENCES `mydb`.`Feature1` (`FeatureKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Transportation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Transportation` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Transportation` (
  `TransportationKey` INT NOT NULL,
  `Description` VARCHAR(200) NULL,
  `TransportationType` VARCHAR(45) NULL,
  `StartTime` DATETIME NULL,
  `CloseTime` DATETIME NULL,
  `Frequency` VARCHAR(45) NULL,
  `Zip` CHAR(10) NOT NULL,
  PRIMARY KEY (`TransportationKey`),
  INDEX `fk_Transportation_PropertyLocationFeature1_idx` (`Zip` ASC) VISIBLE,
  CONSTRAINT `fk_Transportation_PropertyLocationFeature1`
    FOREIGN KEY (`Zip`)
    REFERENCES `mydb`.`PropertyLocation_zip` (`Zip`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Airbnb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Airbnb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Airbnb` (
  `AirbnbKey` INT NOT NULL,
  `ZipCodeFK` INT NOT NULL,
  PRIMARY KEY (`AirbnbKey`),
  INDEX `fk_Airbnb_ZipCode1_idx` (`ZipCodeFK` ASC) VISIBLE,
  CONSTRAINT `fk_Airbnb_ZipCode1`
    FOREIGN KEY (`ZipCodeFK`)
    REFERENCES `mydb`.`ZipCode` (`ZipCodeKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Walkablilty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Walkablilty` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Walkablilty` (
  `WalkabliltyKey` INT NOT NULL,
  `Score` VARCHAR(45) NULL,
  `Description` VARCHAR(45) NULL,
  `Zip` CHAR(10) NOT NULL,
  PRIMARY KEY (`WalkabliltyKey`),
  INDEX `fk_Walkablilty_PropertyLocationFeature1_idx` (`Zip` ASC) VISIBLE,
  CONSTRAINT `fk_Walkablilty_PropertyLocationFeature1`
    FOREIGN KEY (`Zip`)
    REFERENCES `mydb`.`PropertyLocation_zip` (`Zip`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Bikeablity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Bikeablity` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Bikeablity` (
  `BikeablityKey` INT NOT NULL,
  `BikeStationNum` VARCHAR(20) NULL,
  `Description` VARCHAR(200) NULL,
  `Zip` CHAR(10) NOT NULL,
  PRIMARY KEY (`BikeablityKey`),
  INDEX `fk_Bikeablity_PropertyLocationFeature1_idx` (`Zip` ASC) VISIBLE,
  CONSTRAINT `fk_Bikeablity_PropertyLocationFeature1`
    FOREIGN KEY (`Zip`)
    REFERENCES `mydb`.`PropertyLocation_zip` (`Zip`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ZipCode1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ZipCode1` ;

CREATE TABLE IF NOT EXISTS `mydb`.`ZipCode1` (
  `ZipCodeKey` INT NOT NULL,
  `Zip` CHAR(10) NULL,
  PRIMARY KEY (`ZipCodeKey`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PropertyContact2`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PropertyContact2` ;

CREATE TABLE IF NOT EXISTS `mydb`.`PropertyContact2` (
  `PropertyContactKey` INT NOT NULL,
  `ContactName` VARCHAR(100) NULL,
  `ContactPhone1` CHAR(12) NULL,
  `ContactPhone2` CHAR(12) NULL,
  `Active` TINYBLOB NULL,
  PRIMARY KEY (`PropertyContactKey`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Status1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Status1` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Status1` (
  `StatusKey` INT NOT NULL,
  `StatusDescription` VARCHAR(100) NULL,
  PRIMARY KEY (`StatusKey`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Architect1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Architect1` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Architect1` (
  `ArchitectKey` INT NOT NULL,
  `ArchitectType` VARCHAR(200) NULL,
  `OtherDescription` VARCHAR(200) NULL,
  PRIMARY KEY (`ArchitectKey`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PropertyStyle1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PropertyStyle1` ;

CREATE TABLE IF NOT EXISTS `mydb`.`PropertyStyle1` (
  `PropertyStyleKey` INT NOT NULL,
  `StyleType` VARCHAR(200) NULL,
  `OtherDescription` VARCHAR(200) NULL,
  PRIMARY KEY (`PropertyStyleKey`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Feature2`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Feature2` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Feature2` (
  `FeatureKey` INT NOT NULL,
  `FeatureType` VARCHAR(200) NULL,
  `OtherDescription` VARCHAR(200) NULL,
  PRIMARY KEY (`FeatureKey`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Porperty1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Porperty1` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Porperty1` (
  `PorpertyKey` INT NOT NULL,
  `PorpertyName` VARCHAR(45) NULL,
  `PropertyDescription` VARCHAR(400) NULL,
  `Bedroom` DECIMAL(2,1) NULL,
  `Bathroom` DECIMAL(2,1) NULL,
  `ProertyPicture` BLOB NULL,
  `ProertySize` INT NULL,
  `OtherDescription` VARCHAR(300) NULL,
  `AddTime` DATETIME NULL,
  `UpdateTime` DATETIME NULL,
  `ZipCodeFK` INT NOT NULL,
  `PropertyContactFK` INT NOT NULL,
  `StatusFK` INT NOT NULL,
  `ArchitectFK` INT NOT NULL,
  `PropertyStyleFK` INT NOT NULL,
  `FeatureFK` INT NOT NULL,
  `State` VARCHAR(2) NULL,
  `City` VARCHAR(45) NULL,
  `Street1` VARCHAR(45) NULL,
  `Street2` VARCHAR(45) NULL,
  INDEX `fk_Porperty_ZipCode_idx` (`ZipCodeFK` ASC) VISIBLE,
  INDEX `fk_Porperty_PropertyContact1_idx` (`PropertyContactFK` ASC) VISIBLE,
  INDEX `fk_Porperty_Status1_idx` (`StatusFK` ASC) VISIBLE,
  INDEX `fk_Porperty_Architect1_idx` (`ArchitectFK` ASC) VISIBLE,
  INDEX `fk_Porperty_PropertyStyle1_idx` (`PropertyStyleFK` ASC) VISIBLE,
  INDEX `fk_Porperty_Feature1_idx` (`FeatureFK` ASC) VISIBLE,
  PRIMARY KEY (`PorpertyKey`),
  CONSTRAINT `fk_Porperty_ZipCode`
    FOREIGN KEY (`ZipCodeFK`)
    REFERENCES `mydb`.`ZipCode1` (`ZipCodeKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Porperty_PropertyContact1`
    FOREIGN KEY (`PropertyContactFK`)
    REFERENCES `mydb`.`PropertyContact2` (`PropertyContactKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Porperty_Status1`
    FOREIGN KEY (`StatusFK`)
    REFERENCES `mydb`.`Status1` (`StatusKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Porperty_Architect1`
    FOREIGN KEY (`ArchitectFK`)
    REFERENCES `mydb`.`Architect1` (`ArchitectKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Porperty_PropertyStyle1`
    FOREIGN KEY (`PropertyStyleFK`)
    REFERENCES `mydb`.`PropertyStyle1` (`PropertyStyleKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Porperty_Feature1`
    FOREIGN KEY (`FeatureFK`)
    REFERENCES `mydb`.`Feature2` (`FeatureKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Transportation1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Transportation1` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Transportation1` (
  `TransportationKey` INT NOT NULL,
  `Description` VARCHAR(200) NULL,
  `TransportationType` VARCHAR(45) NULL,
  `StartTime` DATETIME NULL,
  `CloseTime` DATETIME NULL,
  `Frequency` VARCHAR(45) NULL,
  `ZipCodeFK` INT NOT NULL,
  PRIMARY KEY (`TransportationKey`),
  INDEX `fk_Transportation_ZipCode1_idx` (`ZipCodeFK` ASC) VISIBLE,
  CONSTRAINT `fk_Transportation_ZipCode1`
    FOREIGN KEY (`ZipCodeFK`)
    REFERENCES `mydb`.`ZipCode1` (`ZipCodeKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Airbnb1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Airbnb1` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Airbnb1` (
  `AirbnbKey` INT NOT NULL,
  `ZipCodeFK` INT NOT NULL,
  PRIMARY KEY (`AirbnbKey`),
  INDEX `fk_Airbnb_ZipCode1_idx` (`ZipCodeFK` ASC) VISIBLE,
  CONSTRAINT `fk_Airbnb_ZipCode1`
    FOREIGN KEY (`ZipCodeFK`)
    REFERENCES `mydb`.`ZipCode1` (`ZipCodeKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Walkablilty1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Walkablilty1` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Walkablilty1` (
  `WalkabliltyKey` INT NOT NULL,
  `Score` VARCHAR(45) NULL,
  `Description` VARCHAR(45) NULL,
  `ZipCodeFK` INT NOT NULL,
  PRIMARY KEY (`WalkabliltyKey`),
  INDEX `fk_Walkablilty_ZipCode1_idx` (`ZipCodeFK` ASC) VISIBLE,
  CONSTRAINT `fk_Walkablilty_ZipCode1`
    FOREIGN KEY (`ZipCodeFK`)
    REFERENCES `mydb`.`ZipCode1` (`ZipCodeKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Bikeablity1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Bikeablity1` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Bikeablity1` (
  `BikeablityKey` INT NOT NULL,
  `BikeStationNum` VARCHAR(20) NULL,
  `Description` VARCHAR(200) NULL,
  `ZipCodeFK` INT NOT NULL,
  PRIMARY KEY (`BikeablityKey`),
  INDEX `fk_Bikeablity_ZipCode1_idx` (`ZipCodeFK` ASC) VISIBLE,
  CONSTRAINT `fk_Bikeablity_ZipCode1`
    FOREIGN KEY (`ZipCodeFK`)
    REFERENCES `mydb`.`ZipCode1` (`ZipCodeKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
