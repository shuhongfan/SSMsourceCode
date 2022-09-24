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

 Date: 31/05/2020 15:16:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `account` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mark` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_nick_phone_time`(`nick`, `phone`, `create_time`) USING BTREE,
  INDEX `idx_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8561001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


INSERT INTO `mybatis`.`users`(`id`, `nick`, `phone`, `password`, `email`, `account`, `create_time`, `mark`) VALUES (1, 'cat0', '1372001000', '74bf882c89cc4fc0b342957a53073d58', 'cat0@163.com', '0', '2020-02-09 14:42:43', NULL);
INSERT INTO `mybatis`.`users`(`id`, `nick`, `phone`, `password`, `email`, `account`, `create_time`, `mark`) VALUES (2, 'cat1', '1372001001', '2143fd5caa8949d4b223794ef00fcaf1', 'cat1@163.com', '1', '2020-02-08 14:42:43', NULL);
INSERT INTO `mybatis`.`users`(`id`, `nick`, `phone`, `password`, `email`, `account`, `create_time`, `mark`) VALUES (3, 'cat2', '1372001002', 'ae64db5bd8804d36af85ce850228539a', 'cat2@163.com', '2', '2020-02-07 14:42:43', NULL);
INSERT INTO `mybatis`.`users`(`id`, `nick`, `phone`, `password`, `email`, `account`, `create_time`, `mark`) VALUES (4, 'cat3', '1372001003', '63fa16f6933a408db29783e75de9940e', 'cat3@163.com', '3', '2020-02-06 14:42:43', NULL);
INSERT INTO `mybatis`.`users`(`id`, `nick`, `phone`, `password`, `email`, `account`, `create_time`, `mark`) VALUES (5, 'cat4', '1372001004', '67a3ae3104514058902d8315aeb89265', 'cat4@163.com', '4', '2020-02-05 14:42:43', NULL);
INSERT INTO `mybatis`.`users`(`id`, `nick`, `phone`, `password`, `email`, `account`, `create_time`, `mark`) VALUES (6, 'cat5', '1372001005', '13c3914d416547f4bdfeb0b3bf058f82', 'cat5@163.com', '5', '2020-02-04 14:42:43', NULL);
INSERT INTO `mybatis`.`users`(`id`, `nick`, `phone`, `password`, `email`, `account`, `create_time`, `mark`) VALUES (7, 'cat6', '1372001006', '0b5adf6fc198454291da5a100f0dd1f1', 'cat6@163.com', '6', '2020-02-03 14:42:43', NULL);
INSERT INTO `mybatis`.`users`(`id`, `nick`, `phone`, `password`, `email`, `account`, `create_time`, `mark`) VALUES (8, 'cat7', '1372001007', 'dea6fbafe30e4479b5022eccc5b67f0d', 'cat7@163.com', '7', '2020-02-02 14:42:43', NULL);
INSERT INTO `mybatis`.`users`(`id`, `nick`, `phone`, `password`, `email`, `account`, `create_time`, `mark`) VALUES (9, 'cat8', '1372001008', '2fbb64f0d25a4093af092e3b915acbb8', 'cat8@163.com', '8', '2020-02-01 14:42:43', NULL);
INSERT INTO `mybatis`.`users`(`id`, `nick`, `phone`, `password`, `email`, `account`, `create_time`, `mark`) VALUES (10, 'cat9', '1372001009', 'ce3b6c14872f42249d628efc23e22c21', 'cat9@163.com', '9', '2020-01-31 14:42:43', NULL);

SET FOREIGN_KEY_CHECKS = 1;

