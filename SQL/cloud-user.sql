/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : 127.0.0.1:3306
 Source Schema         : cloud-user

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 05/11/2023 22:58:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_user
-- ----------------------------
DROP TABLE IF EXISTS `account_user`;
CREATE TABLE `account_user` (
  `id` varchar(50) NOT NULL COMMENT '账号id',
  `email` varchar(30) NOT NULL DEFAULT '' COMMENT '邮箱',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `username` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登陆时间',
  `login_ip` varchar(12) NOT NULL DEFAULT '' COMMENT '最后一次登陆ip',
  `status` varchar(20) NOT NULL DEFAULT '0' COMMENT '状态 1:启用, 0:关闭, -1:删除标识',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `operate` varchar(50) NOT NULL DEFAULT '0' COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_phone` (`phone`) USING BTREE,
  UNIQUE KEY `idx_email` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户信息';

-- ----------------------------
-- Records of account_user
-- ----------------------------
BEGIN;
INSERT INTO `account_user` (`id`, `email`, `phone`, `username`, `password`, `create_time`, `last_login_time`, `login_ip`, `status`, `update_time`, `operate`) VALUES ('202303232347000382480', 'root@163.com', '88888888', '超级管理', '$2a$10$kTBVjqagneVCauY5YbJRb.92ttgLAs82ELXpsd2T6Qb7t83nU7Nwi', '2023-03-23 23:47:01', '2023-11-04 15:02:35', '0.0.0.0', 'NORMAL', NULL, '0');
INSERT INTO `account_user` (`id`, `email`, `phone`, `username`, `password`, `create_time`, `last_login_time`, `login_ip`, `status`, `update_time`, `operate`) VALUES ('202311042126060104384', 'test@qq.com', '13881844081', 'test@qq.com', '$2a$10$.2YgnXjawTh8ft1QOn5GM.VtHnZseAA08exaIWSfuSk2KFiAZbUAy', '2023-11-04 21:26:07', NULL, '', '0', NULL, '0');
INSERT INTO `account_user` (`id`, `email`, `phone`, `username`, `password`, `create_time`, `last_login_time`, `login_ip`, `status`, `update_time`, `operate`) VALUES ('202311042208240542860', '1370094791@qq.com', '1388184123', 'huqiliang', '$2a$10$odkA19S5IpWvGx5m4ifi/e1fg7J8LdjF0KPdvHVu9eXBAWCRJbwIy', '2023-11-04 22:08:25', NULL, '', '0', NULL, '0');
INSERT INTO `account_user` (`id`, `email`, `phone`, `username`, `password`, `create_time`, `last_login_time`, `login_ip`, `status`, `update_time`, `operate`) VALUES ('202311042210320971211', '21321412@qq.com', '139978123', 'huqwe', '$2a$10$x926bs1T05.9hJIpNlSdg.bJrUjxsu583sOqU0UpAPELLp0gwUkdG', '2023-11-04 22:10:33', NULL, '', '0', NULL, '0');
INSERT INTO `account_user` (`id`, `email`, `phone`, `username`, `password`, `create_time`, `last_login_time`, `login_ip`, `status`, `update_time`, `operate`) VALUES ('202311042212180034574', '1312321@qq.com', '123214541', 'dasd1', '$2a$10$YJI2GqvpCvFSLpqDgSNYLufp6uDxrmEUnSD.MMHXS3hrN0DD68rdO', '2023-11-04 22:12:18', NULL, '', '0', NULL, '0');
INSERT INTO `account_user` (`id`, `email`, `phone`, `username`, `password`, `create_time`, `last_login_time`, `login_ip`, `status`, `update_time`, `operate`) VALUES ('202311042215350703920', '121321@qq.com', '13881844098', '121321', '$2a$10$1iWSNs.t8lwhry4k3f3FKO0RMz7QXC0xrRR9ccOSJaMja83wd/aea', '2023-11-04 22:15:36', NULL, '', '0', NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for auth_item
-- ----------------------------
DROP TABLE IF EXISTS `auth_item`;
CREATE TABLE `auth_item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `ms_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '系统id',
  `menu_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '页面/接口uri',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 1:启用, 0:关闭, -1:删除标识',
  `operate` varchar(50) NOT NULL DEFAULT '0' COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_ms_menu` (`ms_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统权限';

-- ----------------------------
-- Records of auth_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for auth_ms
-- ----------------------------
DROP TABLE IF EXISTS `auth_ms`;
CREATE TABLE `auth_ms` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `ms_name` varchar(255) NOT NULL DEFAULT '0' COMMENT '系统名称',
  `ms_desc` varchar(255) NOT NULL DEFAULT '0' COMMENT '系统描述',
  `ms_domain` varchar(255) NOT NULL DEFAULT '0' COMMENT '系统域名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `operate` varchar(50) NOT NULL DEFAULT '0' COMMENT '操作人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 1:启用, 0:关闭, -1:删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统map(登记目前存在的后台系统信息)';

-- ----------------------------
-- Records of auth_ms
-- ----------------------------
BEGIN;
INSERT INTO `auth_ms` (`id`, `ms_name`, `ms_desc`, `ms_domain`, `create_time`, `operate`, `update_time`, `status`) VALUES (1, 'cloud-framewor平台', 'cloud-framewor云平台', '', '2023-03-23 22:26:26', '0', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for auth_ms_menu
-- ----------------------------
DROP TABLE IF EXISTS `auth_ms_menu`;
CREATE TABLE `auth_ms_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `ms_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '系统id',
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '父菜单id',
  `menu_name` varchar(255) NOT NULL DEFAULT '0' COMMENT '菜单名称',
  `menu_desc` varchar(255) DEFAULT '' COMMENT '菜单描述',
  `menu_uri` varchar(255) NOT NULL DEFAULT '0' COMMENT '菜单uri',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `operator` varchar(50) NOT NULL DEFAULT '0' COMMENT '操作人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 1:启用, 0:关闭, -1:删除标识',
  PRIMARY KEY (`id`),
  KEY `idx_ms_id` (`ms_id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='菜单信息';

-- ----------------------------
-- Records of auth_ms_menu
-- ----------------------------
BEGIN;
INSERT INTO `auth_ms_menu` (`id`, `ms_id`, `parent_id`, `menu_name`, `menu_desc`, `menu_uri`, `create_time`, `operator`, `update_time`, `status`) VALUES (1, 1, 0, '系统配置', '', '/authConfig', '2023-03-30 23:55:26', '0', NULL, 1);
INSERT INTO `auth_ms_menu` (`id`, `ms_id`, `parent_id`, `menu_name`, `menu_desc`, `menu_uri`, `create_time`, `operator`, `update_time`, `status`) VALUES (2, 1, 1, '用户管理', '', '/staff', '2023-03-30 23:59:09', '0', NULL, 1);
INSERT INTO `auth_ms_menu` (`id`, `ms_id`, `parent_id`, `menu_name`, `menu_desc`, `menu_uri`, `create_time`, `operator`, `update_time`, `status`) VALUES (3, 1, 1, '角色管理', '', '/authRole', '2023-03-31 00:02:02', '0', NULL, 1);
INSERT INTO `auth_ms_menu` (`id`, `ms_id`, `parent_id`, `menu_name`, `menu_desc`, `menu_uri`, `create_time`, `operator`, `update_time`, `status`) VALUES (4, 1, 1, '菜单管理', '', '/authMenu', '2023-03-31 00:02:44', '0', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(255) NOT NULL DEFAULT '0' COMMENT '角色名称',
  `desc` varchar(255) NOT NULL DEFAULT '0' COMMENT '角色描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 1:启用, 0:关闭, -1:删除标识',
  `operator` varchar(50) NOT NULL DEFAULT '0' COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='员工角色';

-- ----------------------------
-- Records of auth_role
-- ----------------------------
BEGIN;
INSERT INTO `auth_role` (`id`, `name`, `desc`, `create_time`, `update_time`, `status`, `operator`) VALUES (1, '超级管理员', '系统管理员', '2023-03-23 22:30:15', NULL, 1, '0');
INSERT INTO `auth_role` (`id`, `name`, `desc`, `create_time`, `update_time`, `status`, `operator`) VALUES (2, '超级会员', '充值用户', '2023-03-23 22:31:23', NULL, 1, '0');
INSERT INTO `auth_role` (`id`, `name`, `desc`, `create_time`, `update_time`, `status`, `operator`) VALUES (3, '普通用户', '注册用户', '2023-03-23 22:31:50', NULL, 1, '0');
COMMIT;

-- ----------------------------
-- Table structure for auth_role_staff
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_staff`;
CREATE TABLE `auth_role_staff` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_id` int(11) NOT NULL,
  `staff_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '员工id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `operator` varchar(50) NOT NULL DEFAULT '0' COMMENT '操作人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 1:启用, 0:关闭, -1:删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_staff_id` (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限角色与员工关系';

-- ----------------------------
-- Records of auth_role_staff
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for staff_info
-- ----------------------------
DROP TABLE IF EXISTS `staff_info`;
CREATE TABLE `staff_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `uid` varchar(50) NOT NULL DEFAULT '0' COMMENT '账号id',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '员工姓名',
  `nickname` varchar(30) DEFAULT '' COMMENT '员工昵称',
  `staff_image` varchar(255) DEFAULT '' COMMENT '员工头像',
  `gender` char(1) NOT NULL DEFAULT '' COMMENT '员工性别 1:男 2:女',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `operate` varchar(50) NOT NULL DEFAULT '0' COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='员工信息(多的可以垂直拆表)';

-- ----------------------------
-- Records of staff_info
-- ----------------------------
BEGIN;
INSERT INTO `staff_info` (`id`, `uid`, `name`, `nickname`, `staff_image`, `gender`, `create_time`, `update_time`, `operate`) VALUES (1, '202303232347000382480', '超级管理', '超级管理', '', '1', '2023-03-23 23:47:02', NULL, '0');
INSERT INTO `staff_info` (`id`, `uid`, `name`, `nickname`, `staff_image`, `gender`, `create_time`, `update_time`, `operate`) VALUES (2, '202311042126060104384', 'test测试用户', 'test@qq.com', '', '2', '2023-11-04 21:26:07', NULL, '0');
INSERT INTO `staff_info` (`id`, `uid`, `name`, `nickname`, `staff_image`, `gender`, `create_time`, `update_time`, `operate`) VALUES (3, '202311042208240542860', 'huqiliang', 'huqiliang', '', '1', '2023-11-04 22:08:25', NULL, '0');
INSERT INTO `staff_info` (`id`, `uid`, `name`, `nickname`, `staff_image`, `gender`, `create_time`, `update_time`, `operate`) VALUES (4, '202311042210320971211', 'huqwe', 'huqwe', '', '2', '2023-11-04 22:10:33', NULL, '0');
INSERT INTO `staff_info` (`id`, `uid`, `name`, `nickname`, `staff_image`, `gender`, `create_time`, `update_time`, `operate`) VALUES (5, '202311042212180034574', 'dasd1', 'dasd1', '', '1', '2023-11-04 22:12:18', NULL, '0');
INSERT INTO `staff_info` (`id`, `uid`, `name`, `nickname`, `staff_image`, `gender`, `create_time`, `update_time`, `operate`) VALUES (6, '202311042215350703920', '121321', '121321', '', '1', '2023-11-04 22:15:36', NULL, '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
