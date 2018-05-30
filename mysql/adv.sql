/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : adv

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-05-28 16:46:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adv
-- ----------------------------
DROP TABLE IF EXISTS `adv`;
CREATE TABLE `adv` (
  `id` bigint(20) NOT NULL COMMENT '广告id，主键',
  `file_url` varchar(255) DEFAULT NULL COMMENT '广告文件url，用于下载',
  `name` varchar(255) DEFAULT NULL COMMENT '广告名称',
  `type` varchar(255) DEFAULT NULL COMMENT '广告类型，文字广告/图片广告/视频广告',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效。1：有效，0：无效。',
  `homepage` varchar(255) DEFAULT NULL COMMENT '跳转到商家主页的URL',
  `display_detail` varchar(255) DEFAULT NULL COMMENT '广告的播放时间段',
  `start_date` datetime DEFAULT NULL COMMENT '广告的有效日期，如果过了该日期，则广告为过期广告',
  `end_date` datetime DEFAULT NULL COMMENT '广告的有效日期，如果过了该日期，则广告为过期广告',
  `modify_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of adv
-- ----------------------------
INSERT INTO `adv` VALUES ('2', '2.jpg', '肯德基', '1', '1', 'www.bilibili.com', '[\"0\", \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\", \"9\", \"10\", \"11\", \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\", \"19\", \"20\", \"21\", \"22\", \"23\"]', '2018-01-04 04:04:00', '2018-05-31 21:35:00', '2018-05-26 20:24:37');
INSERT INTO `adv` VALUES ('66', '', 'iPhone推广', '1', '1', '', null, '2018-05-04 04:04:15', '2018-03-04 21:35:46', '2018-05-28 16:45:31');
INSERT INTO `adv` VALUES ('77', '1', 'Nike宣传', '1', '1', '1', '[\"1\", \"2\"]', '2018-05-04 04:04:20', '2018-03-30 21:35:00', '2018-05-28 16:45:31');
INSERT INTO `adv` VALUES ('444', '44', '小米Mix2', '1', '1', null, null, '2018-05-04 04:04:27', '2018-03-31 21:35:58', '2018-05-28 16:45:31');
INSERT INTO `adv` VALUES ('1702', '1702.txt', '相册广告', '1', '1', '', null, '2018-05-04 04:19:08', '2018-03-29 09:00:43', '2018-05-28 16:45:31');
INSERT INTO `adv` VALUES ('2703', '2703.txt', 'MacBook', '1', '1', '', null, '2018-05-04 04:19:16', '2018-03-30 03:19:08', '2018-05-28 16:45:31');
INSERT INTO `adv` VALUES ('11204', '11204.jpg', 'NBA季后赛', '1', '1', 'www.bilibili.com', '[\"0\", \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\", \"9\", \"10\", \"11\", \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\", \"19\", \"20\", \"21\", \"22\", \"23\"]', '2018-05-04 04:19:00', '2150-06-05 21:02:00', '2018-05-26 20:09:13');
INSERT INTO `adv` VALUES ('19502', '19502.jpg', '新垣结衣', '1', '1', 'https://www.baidu.com', '[\"0\", \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\", \"9\", \"10\", \"11\", \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\", \"19\", \"20\", \"21\", \"22\", \"23\"]', '2018-01-11 11:01:00', '2018-11-11 11:11:00', '2018-05-26 20:00:12');
SET FOREIGN_KEY_CHECKS=1;
