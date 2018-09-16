/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : aopauth

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-09-16 22:53:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `falg` int(11) DEFAULT NULL,
  `parnet_role_id` bigint(20) DEFAULT NULL,
  `role_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '1', null, 'demo1');
INSERT INTO `role` VALUES ('2', '2', '1', 'demo1:add');
INSERT INTO `role` VALUES ('3', '2', '1', 'demo1:delete');
INSERT INTO `role` VALUES ('4', '2', '1', 'demo1:update');
INSERT INTO `role` VALUES ('5', '2', '1', 'demo1:query');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'b19ab179d236864a39d8c4c5572a949a9df71dd2', 'admin');
INSERT INTO `user` VALUES ('2', '6ec18ff4c4dfb01c5ebc93f3dcdf74fa4b23971e', 'test1');
INSERT INTO `user` VALUES ('3', '71f013f0f0fe955222096573bdc8dde5ecd51ba2', 'test2');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', 'admin');
INSERT INTO `user_role` VALUES ('2', '2', 'admin');
INSERT INTO `user_role` VALUES ('3', '3', 'admin');
INSERT INTO `user_role` VALUES ('4', '4', 'admin');
INSERT INTO `user_role` VALUES ('5', '1', 'test1');
INSERT INTO `user_role` VALUES ('6', '2', 'test1');
INSERT INTO `user_role` VALUES ('7', '1', 'test2');
INSERT INTO `user_role` VALUES ('8', '2', 'test2');
INSERT INTO `user_role` VALUES ('9', '3', 'test2');
INSERT INTO `user_role` VALUES ('10', '5', 'admin');
