CREATE DATABASE bigtony_ad_data character set utf8mb4;

use bigtony_ad_data;

-- User Table
CREATE TABLE `ad_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL DEFAULT '' COMMENT 'User Name',
  `token` varchar(256) NOT NULL DEFAULT '' COMMENT 'User Sign In Token',
  `user_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'User Status',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'Created Time',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'Updated Time',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='User Info Table';


-- Advertisement(Promotion) Plan Table
CREATE TABLE `ad_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'Belonged User',
  `plan_name` varchar(48) NOT NULL COMMENT 'Plan Name',
  `plan_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Plan Status',
  `start_date` datetime NOT NULL COMMENT 'Plan Start Time',
  `end_date` datetime NOT NULL COMMENT 'Plan End Time；',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'Created Time',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'Updated Time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='Advertisement(Promotion) Plan Table';


-- Advertisement(Promotion) Unit Table
CREATE TABLE `ad_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `plan_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'Belonged Plan',
  `unit_name` varchar(48) NOT NULL COMMENT 'Unit Name',
  `unit_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Unit Status',
  `position_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Unit Position Type',
  `budget` bigint(20) NOT NULL COMMENT 'Budget',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'Created Time',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'Updated Time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='Advertisement(Promotion) Unit Table';


-- Creative Table
CREATE TABLE `ad_creative` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(48) NOT NULL COMMENT 'Creative Name',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Creative Type(Image, Video)',
  `material_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Creative Material Type(.img, .mp4)',
  `height` int(10) NOT NULL DEFAULT '0' COMMENT 'Height',
  `width` int(10) NOT NULL DEFAULT '0' COMMENT 'Width',
  `size` bigint(20) NOT NULL DEFAULT '0' COMMENT 'Size(KB)',
  `duration` int(10) NOT NULL DEFAULT '0' COMMENT 'Duration',
  `audit_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Audit Status',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'Belonged User',
  `url` varchar(256) NOT NULL COMMENT 'URL to Creative',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'Created Time',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'Updated Time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='Creative Table';


-- Creative and Promotion Unit Table
CREATE TABLE `creative_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creative_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'Creative id',
  `unit_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'Promotion Unit id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='Creative and Promotion Unit Table';


-- Promotion Unit Keyword Feature
CREATE TABLE `ad_unit_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_id` int(11) NOT NULL COMMENT 'Unit id',
  `keyword` varchar(30) NOT NULL COMMENT 'Keyword',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='Promotion Unit Keyword Feature';


-- Promotion Unit Interest Feature
CREATE TABLE `ad_unit_it` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_id` int(11) NOT NULL COMMENT 'Unit id',
  `it_tag` varchar(30) NOT NULL COMMENT 'Interest Tag',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='Promotion Unit Interest Feature';


-- Promotion Unit Region Feature
CREATE TABLE `ad_unit_district` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_id` int(11) NOT NULL COMMENT 'Unit id',
  `province` varchar(30) NOT NULL COMMENT 'State',
  `city` varchar(30) NOT NULL COMMENT 'City',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='Promotion Unit Region Feature';
