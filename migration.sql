-- MySQL Script generated by MySQL Workbench
-- Thu Nov 19 13:06:45 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

DROP SCHEMA IF EXISTS `adlister_db`;
CREATE SCHEMA IF NOT EXISTS `adlister_db` DEFAULT CHARACTER SET utf8;
USE `adlister_db`;


DROP TABLE IF EXISTS `adlister_db`.`users`;

CREATE TABLE IF NOT EXISTS `adlister_db`.`users`
(
    `id`                 INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `lister_type`        TINYINT      NOT NULL DEFAULT 0,
    `username`           VARCHAR(75)  NOT NULL UNIQUE,
    `email`              VARCHAR(255) NOT NULL UNIQUE,
    `password`           VARCHAR(100) NOT NULL,
    `first_name`         VARCHAR(75)  NOT NULL DEFAULT 'NA',
    `last_name`          VARCHAR(75)  NOT NULL DEFAULT 'NA',
    `about_me`           TEXT         NULL,
    `phone_number`       CHAR(15)     NULL,
    `viewing_preference` TINYINT      NULL     DEFAULT 0,
    `isAdmin`            TINYINT      NULL     DEFAULT 0,
    `create_time`        TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `adlister_db`.`businesses`;

CREATE TABLE IF NOT EXISTS `adlister_db`.`businesses`
(
    `id`              INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `lister_type`     TINYINT      NOT NULL DEFAULT 1,
    `name`            VARCHAR(255) NOT NULL UNIQUE,
    `email`           VARCHAR(255) NOT NULL UNIQUE,
    `password`        VARCHAR(100) NOT NULL,
    `website_url`     TEXT         NULL,
    `about_us`        TEXT         NULL,
    `phone_number`    CHAR(15)     NULL,
    `view_preference` TINYINT      NULL     DEFAULT 0,
    `isAdmin`         TINYINT      NULL     DEFAULT 0,
    `create_time`     TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `adlister_db`.`business_pictures`;

CREATE TABLE IF NOT EXISTS `adlister_db`.`business_pictures`
(
    `id`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `business_img_url` TEXT         NULL,
    `alt_text`         VARCHAR(255) NOT NULL DEFAULT 'user_profile_img',
    `business_id`      INT UNSIGNED NOT NULL,
    `create_time`      TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`business_id`) REFERENCES `adlister_db`.`businesses` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS `adlister_db`.`categories`;

CREATE TABLE IF NOT EXISTS `adlister_db`.`categories`
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);


DROP TABLE IF EXISTS `adlister_db`.`user_pictures`;

CREATE TABLE IF NOT EXISTS `adlister_db`.`user_pictures`
(
    `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_img_url` TEXT         NULL,
    `alt_text`     VARCHAR(255) NOT NULL DEFAULT 'user_profile_img',
    `user_id`      INT UNSIGNED NOT NULL,
    `create_time`  TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `adlister_db`.`users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS `adlister_db`.`ads`;

CREATE TABLE IF NOT EXISTS `adlister_db`.`ads`
(
    `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`     INT UNSIGNED NOT NULL,
    `title`       VARCHAR(255) NOT NULL,
    `description` TEXT         NOT NULL,
    `create_time` TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `adlister_db`.`users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS `adlister_db`.`ad_pictures`;

CREATE TABLE IF NOT EXISTS `adlister_db`.`ad_pictures`
(
    `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `ad_img_url`  TEXT         NULL,
    `alt_text`    VARCHAR(255) NOT NULL DEFAULT 'user_ad_img',
    `ad_id`       INT UNSIGNED NOT NULL,
    `create_time` TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`ad_id`) REFERENCES `adlister_db`.`ads` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS `adlister_db`.`adCategories`;

CREATE TABLE IF NOT EXISTS `adlister_db`.`adCategories`
(
    `ad_id`       INT UNSIGNED NOT NULL,
    `category_id` INT UNSIGNED NOT NULL,
    FOREIGN KEY (`ad_id`) REFERENCES `adlister_db`.`ads` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (`category_id`) REFERENCES `adlister_db`.`categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `adlister_db`.`business_ads`;

CREATE TABLE IF NOT EXISTS `adlister_db`.`business_ads`
(
    `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `business_id` INT UNSIGNED NOT NULL,
    `title`       VARCHAR(255) NOT NULL,
    `description` TEXT         NOT NULL,
    `create_time` TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`business_id`) REFERENCES `adlister_db`.`businesses` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS `adlister_db`.`businessAds_pictures`;

CREATE TABLE IF NOT EXISTS `adlister_db`.`businessAds_pictures`
(
    `id`                  INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `business_ad_img_url` TEXT         NULL,
    `alt_text`            VARCHAR(255) NOT NULL DEFAULT 'business_ad_img',
    `businessAd_id`       INT UNSIGNED NOT NULL,
    `create_time`         TIMESTAMP    NULL     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`businessAd_id`) REFERENCES `adlister_db`.`business_ads` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS `adlister_db`.`business_categories`;

CREATE TABLE IF NOT EXISTS `adlister_db`.`business_categories`
(
    `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `businessAd_id` INT UNSIGNED NOT NULL,
    `name`          VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `adlister_db`.`businessAdCategories`;

CREATE TABLE IF NOT EXISTS `adlister_db`.`businessAdCategories`
(
    `businessAd_id`       INT UNSIGNED NOT NULL,
    `businessCategory_id` INT UNSIGNED NOT NULL,
    FOREIGN KEY (`businessAd_id`) REFERENCES `adlister_db`.`business_ads` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (`businessCategory_id`) REFERENCES `adlister_db`.`business_categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
