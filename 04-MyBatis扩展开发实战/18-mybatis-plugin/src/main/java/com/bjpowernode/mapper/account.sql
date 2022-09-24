/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.172.127
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 192.168.172.127:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 31/05/2020 15:15:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `realName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `idCard` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sex` int(255) DEFAULT NULL,
  `address` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `money` decimal(11, 2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 1, '张三丰', '110235178203036578', 1, '湖北武当山', 9999999.00);
INSERT INTO `account` VALUES (2, 2, '张冰', '220235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (3, 3, '李华', '330235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (4, 4, '谢平', '440235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (5, 5, '王三', '550235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (6, 6, '赵四', '660235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (7, 7, '路南安', '770235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (8, 8, '卢秀秀', '880235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (9, 9, '曾颖', '990235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (10, 10, '郝仁', '100235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (11, 11, '郝仁任', '500235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (12, 12, '翟大友', '920235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (13, 13, '杨华', '830235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (14, 14, '李平', '740235178203036578', 1, '湖北武当山', 1000000.00);
INSERT INTO `account` VALUES (15, 15, '张三', '650235178203036578', 1, '湖北武当山', 1000000.00);

SET FOREIGN_KEY_CHECKS = 1;
