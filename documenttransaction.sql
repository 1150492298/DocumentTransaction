/*
 Navicat Premium Data Transfer

 Source Server         : dingzhen
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : localhost:3306
 Source Schema         : documenttransaction

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 31/03/2023 18:56:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `departmentId` int(11) NOT NULL AUTO_INCREMENT,
  `departmentIntro` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `departmentName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `departmentStatue` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`departmentId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '普通员工刚进入时的默认部门', '员工部', 1);
INSERT INTO `department` VALUES (2, '办公室，负责办公', '办公室', 1);
INSERT INTO `department` VALUES (3, '市场开发部', '市场开发部', 1);
INSERT INTO `department` VALUES (4, '工程项目管理中心', '工程项目管理中心', 1);
INSERT INTO `department` VALUES (5, '安全环保管理部', '安全环保管理部', 1);
INSERT INTO `department` VALUES (6, '财务部', '财务部', 1);
INSERT INTO `department` VALUES (7, '人力资源部', '人力资源部', 1);
INSERT INTO `department` VALUES (8, '党群工作部', '党群工作部', 1);
INSERT INTO `department` VALUES (9, '审计与风险管理部', '审计与风险管理部', 1);
INSERT INTO `department` VALUES (10, '综合办公室', '综合办公室', 1);
INSERT INTO `department` VALUES (11, '发展规划部', '发展规划部', 1);
INSERT INTO `department` VALUES (12, '饮水管理处', '饮水管理处', 1);
INSERT INTO `department` VALUES (13, '信息与调度中心', '信息与调度中心', 1);
INSERT INTO `department` VALUES (14, '综合保障中心', '综合保障中心', 1);
INSERT INTO `department` VALUES (15, '考绩办', '考绩办', 1);
INSERT INTO `department` VALUES (16, '监察审计室', '监察审计室', 1);

-- ----------------------------
-- Table structure for departmentsearch
-- ----------------------------
DROP TABLE IF EXISTS `departmentsearch`;
CREATE TABLE `departmentsearch`  (
  `departmentSearchId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `departmentId` int(11) NOT NULL,
  `searchTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `manageTime` datetime(0) NULL DEFAULT NULL,
  `manageId` int(11) NULL DEFAULT NULL,
  `searchReason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `searchStatue` int(255) NULL DEFAULT 1,
  PRIMARY KEY (`departmentSearchId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of departmentsearch
-- ----------------------------
INSERT INTO `departmentsearch` VALUES (1, 4, 2, '2023-03-22 14:53:38', '2023-03-22 14:53:38', 4, '想要去办公室行吗aaa呜呜呜啊这', 0);
INSERT INTO `departmentsearch` VALUES (2, 4, 5, '2023-03-22 15:07:49', '2023-03-22 15:07:49', 4, '我要去安全环保管理部', 2);
INSERT INTO `departmentsearch` VALUES (3, 4, 5, '2023-03-22 15:38:46', NULL, NULL, '2222222222222222222222222222222222222222222222222222', 1);
INSERT INTO `departmentsearch` VALUES (4, 4, 5, '2023-03-22 15:20:32', NULL, NULL, '1', 1);
INSERT INTO `departmentsearch` VALUES (5, 4, 5, '2023-03-22 15:20:32', NULL, NULL, '1', 1);
INSERT INTO `departmentsearch` VALUES (6, 4, 5, '2023-03-22 15:20:32', NULL, NULL, '1', 1);
INSERT INTO `departmentsearch` VALUES (7, 4, 5, '2023-03-22 15:20:32', NULL, NULL, '1', 1);
INSERT INTO `departmentsearch` VALUES (8, 4, 5, '2023-03-22 15:20:32', NULL, NULL, '1', 1);

-- ----------------------------
-- Table structure for document
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document`  (
  `documentId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `documentName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `documentAbout` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `documentStatue` int(11) NOT NULL DEFAULT 1,
  `documentClassId` int(11) NOT NULL,
  `documentPostTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `documentExamerString` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `documentContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `documentProcess` int(255) NULL DEFAULT 0,
  `documentExameTimeString` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `documentNowExamerId` int(11) NULL DEFAULT NULL,
  `documentNowDepartmentId` int(11) NULL DEFAULT NULL,
  `documentContentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`documentId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of document
-- ----------------------------
INSERT INTO `document` VALUES (2, 4, '111', '111', 1, 5, '2023-03-28 11:02:14', NULL, NULL, 0, NULL, 6, 6, NULL);
INSERT INTO `document` VALUES (3, 4, '关于今晚吃什么的的通知', '我想好好的吃晚饭', 2, 3, '2023-03-29 10:53:10', '8:测试1-9:测试2-10:测试3-', NULL, 3, '2023-03-28|2023-03-29  10:46:53|2023-03-29  10:53:10|', 6, 13, NULL);
INSERT INTO `document` VALUES (4, 4, '今晚吃什么', '我今晚要吃麻辣香锅', 3, 3, '2023-03-29 15:03:44', '8:测试1-', NULL, 1, '2023-03-29  15:03:44|', 4, 13, NULL);
INSERT INTO `document` VALUES (6, 4, '111', '222', 1, 5, '2023-03-29 17:16:30', NULL, NULL, 0, NULL, 6, 6, NULL);
INSERT INTO `document` VALUES (7, 4, '222', '111', 1, 4, '2023-03-29 17:19:22', NULL, 'http://localhost:8082/api/document/download/1680081561677', 0, NULL, 8, 7, NULL);
INSERT INTO `document` VALUES (8, 4, '111', '222', 1, 5, '2023-03-29 17:35:20', NULL, 'http://localhost:8082/api/document/download/1680082520604', 0, NULL, 6, 6, NULL);
INSERT INTO `document` VALUES (9, 4, '111', '222', 1, 1, '2023-03-29 18:21:01', NULL, 'http://localhost:8082/api/document/download/1680085260684', 0, NULL, 3, 16, '移动办公app大纲.png');
INSERT INTO `document` VALUES (10, 4, '111', '222', 1, 3, '2023-03-29 18:41:40', '8:测试1-', 'http://localhost:8082/api/document/download/1680085714231', 1, '2023-03-29  18:41:40|', 5, 13, '1毕业论文格式规范.doc');

-- ----------------------------
-- Table structure for documentclass
-- ----------------------------
DROP TABLE IF EXISTS `documentclass`;
CREATE TABLE `documentclass`  (
  `documentClassId` int(11) NOT NULL AUTO_INCREMENT,
  `documentDepartmentRoleString` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `documentClassName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `documentClassDescribe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `documentProcessCount` int(255) NOT NULL,
  PRIMARY KEY (`documentClassId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of documentclass
-- ----------------------------
INSERT INTO `documentclass` VALUES (1, '16:3-16:4-16:5-16:6-16:7-12:5', '命令类', '命令类型的公文啊啊啊', 6);
INSERT INTO `documentclass` VALUES (2, '7:7-7:6-5:6-6:6-6:8-5:8-7:8', '决定类', '决定类公文', 7);
INSERT INTO `documentclass` VALUES (3, '13:4-13:5-13:6', '公告类', '公告类公文', 3);
INSERT INTO `documentclass` VALUES (4, '7:8-7:6-4:6-6:6', '通报类', '通报类公文', 4);
INSERT INTO `documentclass` VALUES (5, '6:6-6:8-6:5-7:5-4:5-4:3', '通知类', '通知类公文', 6);
INSERT INTO `documentclass` VALUES (6, '7:7', '议案类', '议案类公文', 1);
INSERT INTO `documentclass` VALUES (7, '7:7', '请示类', '请示类公文', 1);
INSERT INTO `documentclass` VALUES (8, '7:8-10:8-8:8', '决议类', '决议类公文', 3);
INSERT INTO `documentclass` VALUES (9, '5:7-5:6-5:4', '批复类', '批复类公文', 3);

-- ----------------------------
-- Table structure for rights
-- ----------------------------
DROP TABLE IF EXISTS `rights`;
CREATE TABLE `rights`  (
  `rightId` int(11) NOT NULL AUTO_INCREMENT,
  `secondId` int(11) NULL DEFAULT NULL,
  `rightClass` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rightOperate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rightDescribe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rightIcon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rightId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rights
-- ----------------------------
INSERT INTO `rights` VALUES (1, NULL, '权限相关', '权限相关', NULL, NULL);
INSERT INTO `rights` VALUES (2, NULL, '公文相关', '公文相关', NULL, NULL);
INSERT INTO `rights` VALUES (3, NULL, '用户相关', '用户相关', NULL, 'el-icon-user');
INSERT INTO `rights` VALUES (4, NULL, '部门相关', '部门相关', NULL, NULL);
INSERT INTO `rights` VALUES (5, 1, '权限管理', '/rightoperate', NULL, 'el-icon-s-ticket');
INSERT INTO `rights` VALUES (7, 1, '角色添加', '/roleadd', NULL, 'el-icon-user-solid');
INSERT INTO `rights` VALUES (8, 2, '公文发布', '/documentpost', NULL, 'el-icon-s-fold');
INSERT INTO `rights` VALUES (9, 2, '流程管理', '/documentprocessmanage', NULL, 'el-icon-s-operation');
INSERT INTO `rights` VALUES (11, 2, '我的公文', '/documentmyself', NULL, 'el-icon-s-data');
INSERT INTO `rights` VALUES (12, 2, '公文管理', '/documentmanage', NULL, 'el-icon-s-management');
INSERT INTO `rights` VALUES (13, 4, '部门管理', '/departmentmanage', NULL, 'el-icon-folder-opened');
INSERT INTO `rights` VALUES (14, 4, '新增部门', '/departmentadd', NULL, 'el-icon-office-building');
INSERT INTO `rights` VALUES (16, 4, '部门申请', '/departmentapply', NULL, 'el-icon-zoom-in');
INSERT INTO `rights` VALUES (17, 3, '用户管理', '/usermanage', NULL, 'el-icon-s-tools');
INSERT INTO `rights` VALUES (18, 3, '新增用户', '/useradd', NULL, 'el-icon-circle-plus');
INSERT INTO `rights` VALUES (19, 1, '权限申请', '/rolesearch', NULL, 'el-icon-circle-plus');
INSERT INTO `rights` VALUES (20, 3, '通讯录', '/maillist', NULL, 'el-icon-phone-outline');
INSERT INTO `rights` VALUES (22, 1, '申请处理', '/rolesearchmanage', NULL, 'el-icon-wallet');
INSERT INTO `rights` VALUES (23, 4, '申请处理', '/departmentapplymanage', NULL, 'el-icon-news');
INSERT INTO `rights` VALUES (24, 2, '流程添加', '/documentclassadd', NULL, 'el-icon-news');
INSERT INTO `rights` VALUES (25, 2, '公文批阅', '/documentexamine', NULL, 'el-icon-news');
INSERT INTO `rights` VALUES (26, 3, '查找用户', '/usersearch', NULL, 'el-icon-s-tools');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roleDescribe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleWeight` int(11) NOT NULL,
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '可以使用所有功能，权限最大', 999);
INSERT INTO `role` VALUES (2, '普通用户', '最普通的用户，只能使用基础功能', 1);
INSERT INTO `role` VALUES (3, '总经理', NULL, 1);
INSERT INTO `role` VALUES (4, '书记', '是书记', 1);
INSERT INTO `role` VALUES (5, '生产副总', '生产副总', 1);
INSERT INTO `role` VALUES (6, '主任', '主任', 1);
INSERT INTO `role` VALUES (7, '值长', NULL, 1);
INSERT INTO `role` VALUES (8, '主值', NULL, 1);
INSERT INTO `role` VALUES (9, '副值', NULL, 1);
INSERT INTO `role` VALUES (10, '点检长', NULL, 1);
INSERT INTO `role` VALUES (11, '点检员', NULL, 1);

-- ----------------------------
-- Table structure for rolerelation
-- ----------------------------
DROP TABLE IF EXISTS `rolerelation`;
CREATE TABLE `rolerelation`  (
  `connectId` int(11) NOT NULL AUTO_INCREMENT,
  `rightId` int(11) NULL DEFAULT NULL,
  `roleId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`connectId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rolerelation
-- ----------------------------
INSERT INTO `rolerelation` VALUES (35, 13, 1);
INSERT INTO `rolerelation` VALUES (36, 14, 1);
INSERT INTO `rolerelation` VALUES (37, 15, 1);
INSERT INTO `rolerelation` VALUES (38, 16, 1);
INSERT INTO `rolerelation` VALUES (39, 23, 1);
INSERT INTO `rolerelation` VALUES (40, 8, 1);
INSERT INTO `rolerelation` VALUES (41, 9, 1);
INSERT INTO `rolerelation` VALUES (42, 10, 1);
INSERT INTO `rolerelation` VALUES (43, 11, 1);
INSERT INTO `rolerelation` VALUES (44, 12, 1);
INSERT INTO `rolerelation` VALUES (45, 5, 1);
INSERT INTO `rolerelation` VALUES (46, 6, 1);
INSERT INTO `rolerelation` VALUES (47, 7, 1);
INSERT INTO `rolerelation` VALUES (48, 19, 1);
INSERT INTO `rolerelation` VALUES (49, 22, 1);
INSERT INTO `rolerelation` VALUES (50, 17, 1);
INSERT INTO `rolerelation` VALUES (51, 18, 1);
INSERT INTO `rolerelation` VALUES (52, 20, 1);
INSERT INTO `rolerelation` VALUES (53, 21, 1);
INSERT INTO `rolerelation` VALUES (54, 16, 2);
INSERT INTO `rolerelation` VALUES (55, 8, 2);
INSERT INTO `rolerelation` VALUES (56, 19, 2);
INSERT INTO `rolerelation` VALUES (57, 20, 2);
INSERT INTO `rolerelation` VALUES (58, 24, 1);
INSERT INTO `rolerelation` VALUES (59, 25, 1);
INSERT INTO `rolerelation` VALUES (60, 25, 4);
INSERT INTO `rolerelation` VALUES (61, 25, 5);
INSERT INTO `rolerelation` VALUES (62, 25, 6);
INSERT INTO `rolerelation` VALUES (63, 26, 1);

-- ----------------------------
-- Table structure for rolesearch
-- ----------------------------
DROP TABLE IF EXISTS `rolesearch`;
CREATE TABLE `rolesearch`  (
  `roleSearchId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  `searchTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `manageTime` datetime(0) NULL DEFAULT NULL,
  `manageId` int(11) NULL DEFAULT NULL,
  `searchReason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `searchStatue` int(11) NULL DEFAULT 1,
  PRIMARY KEY (`roleSearchId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rolesearch
-- ----------------------------
INSERT INTO `rolesearch` VALUES (1, 4, 2, '2023-03-17 23:48:20', NULL, NULL, '123', 1);
INSERT INTO `rolesearch` VALUES (2, 5, 3, '2023-03-22 12:11:18', '2023-03-22 12:11:19', 4, '我要当总经理，我要当总经理，我要当总经理11111111111111111111111总经理', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL DEFAULT 2,
  `userName` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userPwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userAccount` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userIntro` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userSex` int(11) NOT NULL DEFAULT 1,
  `userPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userAddressList` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userStatue` int(11) NULL DEFAULT 1,
  `departmentId` int(11) NULL DEFAULT 1,
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `updateTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (4, 1, '容志鹏啊', '5dc14892bb1b4c0807713590812993af', '20230316439267003', '我是容志鹏啊', 1, '13040992530', '10-9-8-7-6-5-15-14-13-12-', 1, 2, '2023-03-31 11:47:28', '2023-03-31 11:47:28');
INSERT INTO `user` VALUES (5, 3, '123', '4297f44b13955235245b2497399d7a93', '202303161148575036', NULL, 0, '13545113353', NULL, 1, 1, '2023-03-22 12:11:18', '2023-03-22 12:11:18');
INSERT INTO `user` VALUES (6, 2, '123', '4297f44b13955235245b2497399d7a93', '20230317779447721', '123', 1, '13545113353', NULL, 1, 1, '2023-03-17 19:40:19', '2023-03-17 19:40:19');
INSERT INTO `user` VALUES (7, 2, '容志鹏', '4297f44b13955235245b2497399d7a93', '202303201289374586', NULL, 1, '13040992531', NULL, 1, 1, '2023-03-20 17:47:21', '2023-03-20 17:47:21');
INSERT INTO `user` VALUES (8, 4, '测试1', '4297f44b13955235245b2497399d7a93', '20230328775854020', '123', 0, '13040992531', NULL, 1, 13, '2023-03-28 17:11:48', '2023-03-28 17:11:48');
INSERT INTO `user` VALUES (9, 5, '测试2', '4297f44b13955235245b2497399d7a93', '202303281024060878', '123', 0, '13040992531', NULL, 1, 13, '2023-03-28 17:11:56', '2023-03-28 17:11:56');
INSERT INTO `user` VALUES (10, 6, '测试3', '4297f44b13955235245b2497399d7a93', '2023032898469268', '123', 0, '13040992531', NULL, 1, 13, '2023-03-28 17:12:02', '2023-03-28 17:12:02');
INSERT INTO `user` VALUES (11, 7, '222', '4297f44b13955235245b2497399d7a93', '202303311305628929', '123', 1, '13040992533', NULL, 1, 7, '2023-03-31 11:47:02', '2023-03-31 11:47:02');
INSERT INTO `user` VALUES (12, 7, '222', '4297f44b13955235245b2497399d7a93', '20230331838126075', '123', 1, '13040992533', NULL, 1, 7, '2023-03-31 11:47:06', '2023-03-31 11:47:06');
INSERT INTO `user` VALUES (13, 7, '222', '4297f44b13955235245b2497399d7a93', '202303311119217898', '123', 1, '13040992533', NULL, 1, 7, '2023-03-31 11:47:06', '2023-03-31 11:47:06');
INSERT INTO `user` VALUES (14, 7, '222', '4297f44b13955235245b2497399d7a93', '20230331641535300', '123', 1, '13040992533', NULL, 1, 7, '2023-03-31 11:47:07', '2023-03-31 11:47:07');
INSERT INTO `user` VALUES (15, 7, '222', '4297f44b13955235245b2497399d7a93', '202303312051108013', '123', 1, '13040992533', NULL, 1, 7, '2023-03-31 11:47:07', '2023-03-31 11:47:07');

SET FOREIGN_KEY_CHECKS = 1;
