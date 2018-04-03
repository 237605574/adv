/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : adv

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 03/04/2018 21:23:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES (0, '123', '123');

-- ----------------------------
-- Table structure for adv
-- ----------------------------
DROP TABLE IF EXISTS `adv`;
CREATE TABLE `adv`  (
  `id` bigint(20) NOT NULL COMMENT '广告id，主键',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '广告文件url，用于下载',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '广告名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '广告类型，文字广告/图片广告/视频广告',
  `is_valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否有效。1：有效，0：无效。',
  `homepage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '跳转到商家主页的URL',
  `display_detail` json COMMENT '广告的播放时间段',
  `start_date` datetime(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '广告的有效日期，如果过了该日期，则广告为过期广告',
  `end_date` datetime(0) DEFAULT NULL COMMENT '广告的有效日期，如果过了该日期，则广告为过期广告',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adv
-- ----------------------------
INSERT INTO `adv` VALUES (2, '2', '2', '2', 0, '2', NULL, '2018-04-02 11:08:51', '2018-03-31 21:35:42');
INSERT INTO `adv` VALUES (66, '', 'hahahahah', NULL, 0, '', NULL, '2018-03-28 22:04:24', '2018-03-04 21:35:46');
INSERT INTO `adv` VALUES (77, '1', '1', '1', 0, '1', NULL, '2018-04-02 11:08:51', '2018-03-30 21:35:49');
INSERT INTO `adv` VALUES (444, NULL, NULL, NULL, 0, NULL, NULL, '2018-03-30 14:41:47', '2018-03-31 21:35:58');
INSERT INTO `adv` VALUES (1702, '1702.txt', 'test adv obj', NULL, 0, '', NULL, '2018-03-30 14:43:37', '2018-03-29 09:00:43');
INSERT INTO `adv` VALUES (2703, '2703.txt', '2702', NULL, 0, '', NULL, '2018-03-30 14:43:37', '2018-03-30 03:19:08');
INSERT INTO `adv` VALUES (11204, '11204.png', '测试一个而广告', '1', 1, 'test url', '[\"0\", \"2\", \"3\"]', '2125-05-04 22:59:00', '2150-06-05 21:02:00');

-- ----------------------------
-- Table structure for adv_tag
-- ----------------------------
DROP TABLE IF EXISTS `adv_tag`;
CREATE TABLE `adv_tag`  (
  `adv_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`adv_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adv_tag
-- ----------------------------
INSERT INTO `adv_tag` VALUES (1, 2);
INSERT INTO `adv_tag` VALUES (3, 4);
INSERT INTO `adv_tag` VALUES (1502, 5);
INSERT INTO `adv_tag` VALUES (1702, 5);
INSERT INTO `adv_tag` VALUES (2703, 5);
INSERT INTO `adv_tag` VALUES (4444, 7456);
INSERT INTO `adv_tag` VALUES (4444, 7456123);
INSERT INTO `adv_tag` VALUES (11204, 9999);
INSERT INTO `adv_tag` VALUES (11204, 10000);

-- ----------------------------
-- Table structure for id_generator
-- ----------------------------
DROP TABLE IF EXISTS `id_generator`;
CREATE TABLE `id_generator`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `step` int(255) DEFAULT NULL,
  `current_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of id_generator
-- ----------------------------
INSERT INTO `id_generator` VALUES ('adv', 100, 11301);
INSERT INTO `id_generator` VALUES ('test', 3, 916);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`tag_id`) USING BTREE,
  UNIQUE INDEX `name唯一`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10009 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (10000, '10000');
INSERT INTO `tag` VALUES (10001, '10001');
INSERT INTO `tag` VALUES (10002, '10002');
INSERT INTO `tag` VALUES (10003, '10003');
INSERT INTO `tag` VALUES (10004, '10004');
INSERT INTO `tag` VALUES (10005, '10005');
INSERT INTO `tag` VALUES (10006, '10006');
INSERT INTO `tag` VALUES (10007, '10007');
INSERT INTO `tag` VALUES (10008, '10008');
INSERT INTO `tag` VALUES (10, '11');
INSERT INTO `tag` VALUES (5, '2');
INSERT INTO `tag` VALUES (9999, '9999');
INSERT INTO `tag` VALUES (111, 'dasda');
INSERT INTO `tag` VALUES (9, '大');
INSERT INTO `tag` VALUES (6, '大爱是');
INSERT INTO `tag` VALUES (7, '委屈翁');
INSERT INTO `tag` VALUES (8, '达到');

-- ----------------------------
-- Table structure for temp
-- ----------------------------
DROP TABLE IF EXISTS `temp`;
CREATE TABLE `temp`  (
  `test` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of temp
-- ----------------------------
INSERT INTO `temp` VALUES ('11');

-- ----------------------------
-- Table structure for user_adv
-- ----------------------------
DROP TABLE IF EXISTS `user_adv`;
CREATE TABLE `user_adv`  (
  `user_id` bigint(20) NOT NULL,
  `adv_id` bigint(20) NOT NULL,
  PRIMARY KEY (`adv_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_adv
-- ----------------------------
INSERT INTO `user_adv` VALUES (44, 2);
INSERT INTO `user_adv` VALUES (44, 1402);
INSERT INTO `user_adv` VALUES (44, 1502);
INSERT INTO `user_adv` VALUES (9999, 11204);
INSERT INTO `user_adv` VALUES (10000, 11204);

-- ----------------------------
-- Table structure for user_id_tag
-- ----------------------------
DROP TABLE IF EXISTS `user_id_tag`;
CREATE TABLE `user_id_tag`  (
  `user_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_id_tag
-- ----------------------------
INSERT INTO `user_id_tag` VALUES (1, 1);
INSERT INTO `user_id_tag` VALUES (1, 2);
INSERT INTO `user_id_tag` VALUES (1, 3);
INSERT INTO `user_id_tag` VALUES (2, 1);
INSERT INTO `user_id_tag` VALUES (2, 3);
INSERT INTO `user_id_tag` VALUES (3, 1);
INSERT INTO `user_id_tag` VALUES (3, 2);
INSERT INTO `user_id_tag` VALUES (3, 3);
INSERT INTO `user_id_tag` VALUES (4, 1);
INSERT INTO `user_id_tag` VALUES (5, 3);
INSERT INTO `user_id_tag` VALUES (6, 2);
INSERT INTO `user_id_tag` VALUES (44, 5);
INSERT INTO `user_id_tag` VALUES (9999, 9999);
INSERT INTO `user_id_tag` VALUES (10000, 10000);
INSERT INTO `user_id_tag` VALUES (10001, 10001);
INSERT INTO `user_id_tag` VALUES (10002, 10002);
INSERT INTO `user_id_tag` VALUES (10003, 10003);
INSERT INTO `user_id_tag` VALUES (10004, 10004);
INSERT INTO `user_id_tag` VALUES (10005, 10005);
INSERT INTO `user_id_tag` VALUES (10006, 10006);
INSERT INTO `user_id_tag` VALUES (10007, 10007);
INSERT INTO `user_id_tag` VALUES (10008, 10008);

SET FOREIGN_KEY_CHECKS = 1;
