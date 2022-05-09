/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : score_manager

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 09/05/2022 19:17:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for arrang
-- ----------------------------
DROP TABLE IF EXISTS `arrang`;
CREATE TABLE `arrang`  (
  `classes_id` int NULL DEFAULT NULL,
  `course_id` int NULL DEFAULT NULL,
  INDEX `classes_id`(`classes_id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of arrang
-- ----------------------------
INSERT INTO `arrang` VALUES (2, 12);
INSERT INTO `arrang` VALUES (2, 9);
INSERT INTO `arrang` VALUES (2, 7);
INSERT INTO `arrang` VALUES (2, 6);
INSERT INTO `arrang` VALUES (1, 4);
INSERT INTO `arrang` VALUES (1, 1);
INSERT INTO `arrang` VALUES (7, 4);
INSERT INTO `arrang` VALUES (7, 1);
INSERT INTO `arrang` VALUES (8, 12);
INSERT INTO `arrang` VALUES (8, 9);
INSERT INTO `arrang` VALUES (8, 7);
INSERT INTO `arrang` VALUES (8, 6);
INSERT INTO `arrang` VALUES (6, 12);
INSERT INTO `arrang` VALUES (6, 9);
INSERT INTO `arrang` VALUES (6, 7);
INSERT INTO `arrang` VALUES (6, 6);
INSERT INTO `arrang` VALUES (5, 12);
INSERT INTO `arrang` VALUES (5, 9);
INSERT INTO `arrang` VALUES (5, 7);
INSERT INTO `arrang` VALUES (5, 6);
INSERT INTO `arrang` VALUES (7, 12);
INSERT INTO `arrang` VALUES (7, 9);
INSERT INTO `arrang` VALUES (7, 7);
INSERT INTO `arrang` VALUES (4, 12);
INSERT INTO `arrang` VALUES (4, 9);
INSERT INTO `arrang` VALUES (4, 7);
INSERT INTO `arrang` VALUES (4, 6);
INSERT INTO `arrang` VALUES (3, 12);
INSERT INTO `arrang` VALUES (3, 9);
INSERT INTO `arrang` VALUES (3, 7);
INSERT INTO `arrang` VALUES (3, 6);
INSERT INTO `arrang` VALUES (7, 6);
INSERT INTO `arrang` VALUES (9, 12);
INSERT INTO `arrang` VALUES (9, 9);
INSERT INTO `arrang` VALUES (9, 7);
INSERT INTO `arrang` VALUES (9, 6);
INSERT INTO `arrang` VALUES (1, 12);
INSERT INTO `arrang` VALUES (1, 9);
INSERT INTO `arrang` VALUES (2, 4);
INSERT INTO `arrang` VALUES (2, 1);

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes`  (
  `classes_id` int NOT NULL AUTO_INCREMENT,
  `classes_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classes_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classes_all_number` int NULL DEFAULT NULL COMMENT '班级总人数',
  `classes_code` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级编码',
  `grade_id` int NULL DEFAULT NULL COMMENT '外键ID',
  `teacher_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`classes_id`) USING BTREE,
  INDEX `grade_id`(`grade_id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES (1, 'ok8yqw4t9aqp1eo', '服务外办二班', 18, '100', 2, 1);
INSERT INTO `classes` VALUES (2, '65p3sxp5rsho6tj', '服务外办一班', 30, '101', 2, 1);
INSERT INTO `classes` VALUES (3, 'km5aflho2xy3c4w', '云计算一班', 30, '102', 9, 2);
INSERT INTO `classes` VALUES (4, 'ae131yna17dpfdj', '云计算二班', 30, '103', 9, 2);
INSERT INTO `classes` VALUES (5, 'll358t8aaqvtz8b', '网络技术一班', 25, '104', 10, 3);
INSERT INTO `classes` VALUES (6, '1073xr3w3qo2w5p', '网络技术二班', 25, '105', 10, 3);
INSERT INTO `classes` VALUES (7, 'gsas4673r66wmgo', '临床医学一班', 35, '106', 19, 6);
INSERT INTO `classes` VALUES (8, 'pbt4fqwy5hkqajj', '临床医学二班', 35, '107', 20, 6);
INSERT INTO `classes` VALUES (9, 'biarkq6qc3x1ryh', '俄语一班', 10, '108', 6, 2);

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `college_id` int NOT NULL AUTO_INCREMENT,
  `college_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `college_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `college_code` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`college_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, '9yvo5mvi8ldun20', '计算机科学学院', '10');
INSERT INTO `college` VALUES (2, 'hbyrz2vq7qj97ah', '师范学院', '11');
INSERT INTO `college` VALUES (3, 'c32f7rk2geyezpc', '数学科学学院', '12');
INSERT INTO `college` VALUES (4, 'w72pyg7z2uamiz2', '化学与分子工程学院', '13');
INSERT INTO `college` VALUES (5, 'xhxd4lef4kppg13', '外国语学院', '14');
INSERT INTO `college` VALUES (6, '1vvxsiq0w1uz1w6', '马克思主义学院', '15');
INSERT INTO `college` VALUES (7, '25atd5soz5nvehq', '教育学院', '16');
INSERT INTO `college` VALUES (8, '5mt8t9sx9wjssqj', '医学院', '17');
INSERT INTO `college` VALUES (9, '8os4qtx2cirigz6', '艺术学院', '18');
INSERT INTO `college` VALUES (10, 'jxkytil3kpm0css', '商学院', '19');
INSERT INTO `college` VALUES (11, 'hsvtlze1ta3q3w9', '生命科学学院', '20');
INSERT INTO `college` VALUES (13, 'kl0c3noot6nx11a', '地理科学学院', '21');
INSERT INTO `college` VALUES (14, 'tjpizf6xhpouu5a', '金融学院', '22');
INSERT INTO `college` VALUES (15, '85sb87f687ssoce', '护理学院', '23');
INSERT INTO `college` VALUES (30, 's8oiuev5xm7g462', '物理学院', '24');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `course_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `course_desc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程描述',
  `course_state` int NULL DEFAULT NULL COMMENT '课程类别 0-必修课  1-选修课',
  `course_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `course_credit` int NULL DEFAULT NULL COMMENT '学分',
  `start_time` date NULL DEFAULT NULL COMMENT '开始日期',
  `end_time` date NULL DEFAULT NULL COMMENT '结束日期',
  `teacher_id` int NULL DEFAULT NULL,
  `number` int NULL DEFAULT NULL COMMENT '选修课人数限制',
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `course_ibfk_1`(`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '大学语文', 'ooiyrx6ifaeh06q', '大学语文', 0, 'image_upload/494bfdd36b824c9cb9e26b8808fe9ac4.jpg', 2, '2022-01-01', '2023-06-01', 4, 0);
INSERT INTO `course` VALUES (2, '财务分析', '8dsu2o942yuy6ji', '财务分析', 1, 'image_upload/62128c1a2dba4aba8b45b1dc4e596d99.jpg', 2, '2021-09-01', '2022-09-01', 5, 30);
INSERT INTO `course` VALUES (3, '中国写意人物画艺法', 'x8zmr5ksnak4vhh', '中国写意人物画艺法', 1, 'image_upload/2356faca0499424c8add21d197dd287e.jpg', 1, '2021-09-01', '2022-09-01', 5, 20);
INSERT INTO `course` VALUES (4, '大学物理', 'x5jdmo29wxpvwo4', '大学物理', 0, 'image_upload/110ad05c75794aed8d25c29d4df660a4.jpg', 2, '2021-06-01', '2022-06-01', 1, 0);
INSERT INTO `course` VALUES (5, '放射防护学', '40mwerp31wcfq45', '放射防护学', 1, 'image_upload/1b2972b3ab0d49b58b37e11cfe180e7a.jpg', 1, '2021-01-01', '2022-01-01', 1, 10);
INSERT INTO `course` VALUES (6, '大学英语', 'xm020fvuqgpowfk', '大学英语', 0, 'image_upload/c344994a35d94fae9814fc35b262019c.jpg', 2, '2021-02-01', '2022-06-01', 5, 0);
INSERT INTO `course` VALUES (7, '大学计算机基础', '8rwn5hqse9lu7zw', '大学计算机基础', 0, 'image_upload/e58c48bf421d4344aa7202a613c6f406.jpg', 2, '2021-06-01', '2022-06-01', 1, 0);
INSERT INTO `course` VALUES (8, '当代大学生教育与管理', '64plj8si71fp8lg', '当代大学生教育与管理', 1, 'image_upload/ed1301811faf4d3da8ef2d71464c5255.jpg', 1, '2021-05-01', '2022-06-01', 5, 35);
INSERT INTO `course` VALUES (9, '中医内科学', '35893cefd3kan3y', '中医内科学', 0, 'image_upload/ccda590c895f42f58a35575fc477aeb4.jpg', 2, '2021-06-01', '2022-06-01', 4, 0);
INSERT INTO `course` VALUES (10, '大学军事教程', '9nju2aqx2e4myc1', '大学军事教程', 1, 'image_upload/13abe0f3c123476eaceddd04aec60ba9.jpg', 1, '2022-01-01', '2023-06-01', 5, 30);
INSERT INTO `course` VALUES (11, '大学生安全教育课程', 'ifcfsych6stzps7', '大学生安全教育课程', 1, 'image_upload/fcfb6b34e3584237a31fa7a5f37307f0.jpg', 1, '2022-02-01', '2023-01-01', 1, 20);
INSERT INTO `course` VALUES (12, '经济政治与社会', '7br77c9zx4xthrv', '经济政治与社会', 0, 'image_upload/5e5d9dd91b0d433992c6a44b6a78796b.jpg', 1, '2022-02-01', '2022-06-01', 1, 15);

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `grade_id` int NOT NULL AUTO_INCREMENT,
  `grade_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `professional_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`grade_id`) USING BTREE,
  INDEX `professional_id`(`professional_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES (1, '9i5y3yifeln1k1o', '2019', 13);
INSERT INTO `grade` VALUES (2, 'z0m5z6pqxezo5aa', '2019', 1);
INSERT INTO `grade` VALUES (3, 'n8ggebjtv52nwem', '2018', 1);
INSERT INTO `grade` VALUES (4, 'sqtahgxggn5m3ap', '2020', 1);
INSERT INTO `grade` VALUES (5, 'lc0gr63yfh0zvdj', '2020', 4);
INSERT INTO `grade` VALUES (6, 'kbyk63q3ltwo3yd', '2019', 7);
INSERT INTO `grade` VALUES (7, 'y2khlyyvz176bh0', '2019', 18);
INSERT INTO `grade` VALUES (8, 'ahi2e44o2u0tfru', '2021', 5);
INSERT INTO `grade` VALUES (9, 'kdtsemjg2cvpanf', '2020', 3);
INSERT INTO `grade` VALUES (10, 's7f4gi31wddjuvw', '2021', 2);
INSERT INTO `grade` VALUES (12, '7ahkqvv6aaztkvx', '2017', 2);
INSERT INTO `grade` VALUES (13, '4c93ecm7swuaznv', '2018', 2);
INSERT INTO `grade` VALUES (14, 'vvvmd1rztbtesq7', '2018', 3);
INSERT INTO `grade` VALUES (15, 'axest4rp2ic6fdp', '2017', 3);
INSERT INTO `grade` VALUES (16, 'd033t5k1828ww0m', '2019', 2);
INSERT INTO `grade` VALUES (17, 'yo6bokijqpz7cng', '2019', 3);
INSERT INTO `grade` VALUES (18, '27b53oheudom9ah', '2020', 2);
INSERT INTO `grade` VALUES (19, 'dz48i8fubcr6py3', '2019', 11);
INSERT INTO `grade` VALUES (20, '0ljucjtxxujfbg9', '2018', 11);

-- ----------------------------
-- Table structure for professional
-- ----------------------------
DROP TABLE IF EXISTS `professional`;
CREATE TABLE `professional`  (
  `professional_id` int NOT NULL AUTO_INCREMENT,
  `professional_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `professional_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `professional_code` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `college_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`professional_id`) USING BTREE,
  INDEX `professional_ibfk_1`(`college_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of professional
-- ----------------------------
INSERT INTO `professional` VALUES (1, 'qhwfy7yh07t8n3w', '计算机科学与技术', '10', 1);
INSERT INTO `professional` VALUES (2, 'd6jus1nlqvpgcgb', '网络技术', '11', 1);
INSERT INTO `professional` VALUES (3, '25a5wj2c4p7sws6', '云计算', '12', 1);
INSERT INTO `professional` VALUES (4, 'seymaq9ql23xzis', '汉语言文学', '13', 2);
INSERT INTO `professional` VALUES (5, 'v05ejlese07n00n', '高数', '14', 2);
INSERT INTO `professional` VALUES (6, 'ytsehlsj2ldpjwz', '英语', '15', 2);
INSERT INTO `professional` VALUES (7, 'oqx0gokqmgqpile', '俄语', '16', 5);
INSERT INTO `professional` VALUES (8, 'mfzgsonylnge5gz', '日语', '17', 5);
INSERT INTO `professional` VALUES (9, 'jsl33dc5ek6zx9x', '法语', '18', 5);
INSERT INTO `professional` VALUES (10, 'be5tvgt4yehwa2c', '基础医学', '19', 8);
INSERT INTO `professional` VALUES (11, 'y0qwr0n2r3e9inx', '临床医学', '20', 8);
INSERT INTO `professional` VALUES (12, '787arv3gn0ms4t7', '口腔医学', '21', 8);
INSERT INTO `professional` VALUES (13, 'sdjnomwq93wdmyj', '化学高分子', '22', 4);
INSERT INTO `professional` VALUES (14, 'agdw9xfc3rqygv7', '临床护理', '23', 15);
INSERT INTO `professional` VALUES (15, 'o73z5f0tl7tb6vn', '美术', '24', 9);
INSERT INTO `professional` VALUES (16, '7a5zd7yq0jm8rkw', '舞蹈', '25', 9);
INSERT INTO `professional` VALUES (17, 'vd7lr1x5zt8skxo', '音乐', '26', 9);
INSERT INTO `professional` VALUES (18, 'f93q9ohuyug4ovc', '马克思理论', '27', 6);
INSERT INTO `professional` VALUES (19, 'jgyerroep7uyxse', '会计', '28', 14);
INSERT INTO `professional` VALUES (20, 'o9negn5g9b7i7ao', '经济与金融', '29', 10);
INSERT INTO `professional` VALUES (21, 'vk54lofbr4cuisl', '财务管理', '30', 10);
INSERT INTO `professional` VALUES (22, 'g1rqxafyctv5499', '生命科学', '31', 11);
INSERT INTO `professional` VALUES (23, 'x8jqlzu708ti60i', '幼师教育', '32', 2);
INSERT INTO `professional` VALUES (24, '4crgs5qx0nqbgpr', '物理', '33', 30);

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `score_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NULL DEFAULT NULL,
  `course_id` int NULL DEFAULT NULL,
  `credit` int NULL DEFAULT NULL COMMENT '获得学分',
  `usual_grade` double NULL DEFAULT NULL COMMENT '平时成绩',
  `test_grade` double NULL DEFAULT NULL COMMENT '期末成绩',
  `stage_grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阶段成绩',
  `score_grade` double NULL DEFAULT NULL COMMENT '最终成绩',
  `state` int NULL DEFAULT NULL COMMENT '选课状态--0 选择 1 取消 2必修',
  `grade_state` int NULL DEFAULT NULL COMMENT '0无成绩  1 有成绩',
  PRIMARY KEY (`score_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 92 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (1, 1, 11, 0, 34, 45, '阶段1:34 ', 38.4, 0, 1);
INSERT INTO `score` VALUES (2, 1, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (3, 1, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (4, 4, 11, 1, 90, 87, '', 87.9, 0, 1);
INSERT INTO `score` VALUES (5, 4, 10, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (7, 5, 11, 1, 80, 80, '', 80, 0, 1);
INSERT INTO `score` VALUES (8, 5, 10, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (10, 7, 5, 1, 80, 90, '阶段1:66 阶段2:45 阶段3:56 ', 74.3, 0, 1);
INSERT INTO `score` VALUES (11, 7, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (12, 7, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (13, 1, 7, 0, 90, 30, '', 48, 2, 1);
INSERT INTO `score` VALUES (14, 2, 7, 2, 90, 60, '', 69, 2, 1);
INSERT INTO `score` VALUES (15, 3, 7, 2, 90, 90, '', 90, 2, 1);
INSERT INTO `score` VALUES (16, 2, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (17, 2, 5, 1, 85, 95, NULL, 92, 0, 1);
INSERT INTO `score` VALUES (19, 8, 4, 2, 90, 90, NULL, 90, 2, 1);
INSERT INTO `score` VALUES (20, 9, 4, 2, 60, 60, NULL, 60, 2, 1);
INSERT INTO `score` VALUES (21, 8, 11, 1, 80, 80, NULL, 80, 0, 1);
INSERT INTO `score` VALUES (22, 8, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (23, 8, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (24, 6, 11, 0, 80, 50, NULL, 59, 0, 1);
INSERT INTO `score` VALUES (25, 3, 11, 1, 70, 70, NULL, 70, 0, 1);
INSERT INTO `score` VALUES (26, 6, 4, 0, 0, 0, NULL, 0, 2, 1);
INSERT INTO `score` VALUES (27, 16, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (28, 16, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (29, 16, 5, 1, 90, 90, NULL, 90, 0, 1);
INSERT INTO `score` VALUES (30, 10, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (31, 10, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (32, 10, 5, 1, 80, 80, NULL, 80, 0, 1);
INSERT INTO `score` VALUES (33, 21, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (34, 21, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (35, 21, 5, 1, 70, 60, NULL, 63, 0, 1);
INSERT INTO `score` VALUES (36, 2, 4, 2, 90, 90, NULL, 90, 2, 1);
INSERT INTO `score` VALUES (37, 4, 4, 2, 80, 95, NULL, 91, 2, 1);
INSERT INTO `score` VALUES (38, 5, 4, 2, 100, 95, NULL, 97, 2, 1);
INSERT INTO `score` VALUES (39, 11, 4, 2, 80, 80, NULL, 80, 2, 1);
INSERT INTO `score` VALUES (40, 7, 4, 2, 60, 70, NULL, 67, 2, 1);
INSERT INTO `score` VALUES (41, 12, 4, 0, 60, 50, '', 57, 2, 1);
INSERT INTO `score` VALUES (42, 17, 7, 2, 60, 70, NULL, 67, 2, 1);
INSERT INTO `score` VALUES (43, 36, 7, 2, 60, 70, NULL, 67, 2, 1);
INSERT INTO `score` VALUES (44, 35, 7, 2, 60, 80, NULL, 74, 2, 1);
INSERT INTO `score` VALUES (45, 37, 7, 2, 70, 80, NULL, 77, 2, 1);
INSERT INTO `score` VALUES (46, 13, 4, 0, 40, 40, '阶段1:78 阶段2:87 ', 57, 2, 1);
INSERT INTO `score` VALUES (47, 15, 4, 2, 45, 45, '阶段1:89 阶段2:89 阶段3:98 ', 63.8, 2, 1);
INSERT INTO `score` VALUES (48, 16, 4, 2, 90, 89, '阶段1:45 阶段2:45 阶段3:43 ', 71.3, 2, 1);
INSERT INTO `score` VALUES (49, 20, 4, 2, 90, 89, '阶段1:67 ', 80.4, 2, 1);
INSERT INTO `score` VALUES (50, 21, 4, 2, 90, 90, '阶段1:67 阶段2:87 阶段3:56 ', 82, 2, 1);
INSERT INTO `score` VALUES (51, 37, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (52, 37, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (53, 37, 5, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (54, 69, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (55, 69, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (56, 69, 5, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (57, 68, 2, NULL, NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `score` VALUES (58, 68, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (59, 68, 5, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (61, 2, 1, 2, 78, 67, '阶段1:89 阶段2:67 ', 75.8, 2, 1);
INSERT INTO `score` VALUES (62, 2, 9, 2, 67, 87, '阶段1:89 阶段2:89 ', 79.8, 2, 1);
INSERT INTO `score` VALUES (63, 4, 9, 2, 78, 67, '阶段1:78 ', 75.8, 2, 1);
INSERT INTO `score` VALUES (64, 6, 9, 2, 67, 78, '阶段1:67 阶段2:56 ', 67, 2, 1);
INSERT INTO `score` VALUES (65, 1, 12, 0, 45, 34, '阶段1:68 阶段2:78 阶段3:78 ', 54.7, 2, 1);
INSERT INTO `score` VALUES (66, 17, 12, 2, 67, 79, '阶段1:89 阶段2:67 ', 73.8, 2, 1);
INSERT INTO `score` VALUES (67, 4, 1, 2, 78, 67, '阶段1:89 阶段2:78 ', 78, 2, 1);
INSERT INTO `score` VALUES (68, 6, 1, 2, 79, 78, '阶段1:90 阶段2:78 ', 80.8, 2, 1);
INSERT INTO `score` VALUES (69, 68, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (70, 142, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (71, 142, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (72, 142, 11, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (73, 141, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (74, 141, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (75, 141, 8, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (76, 140, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (77, 140, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (78, 140, 8, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (79, 67, 11, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (80, 67, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (81, 67, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (82, 66, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (83, 66, 5, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (84, 66, 8, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (85, 143, 2, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (86, 143, 3, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (87, 143, 5, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (88, 22, 4, 0, 56, 67, '阶段1:45 阶段2:56 ', 56, 2, 1);
INSERT INTO `score` VALUES (89, 4, 2, NULL, NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `score` VALUES (90, 4, 8, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `score` VALUES (91, 1, 4, 2, 90, 80, '阶段1:56 阶段2:65 ', 76.2, 2, 1);
INSERT INTO `score` VALUES (92, 17, 4, 0, 56, 45, '', 52.7, 2, 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_num` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_sex` int NULL DEFAULT NULL,
  `student_age` int NULL DEFAULT NULL,
  `student_national` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `student_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_card` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classes_id` int NULL DEFAULT NULL COMMENT '班级外键',
  PRIMARY KEY (`student_id`) USING BTREE,
  INDEX `classes_id`(`classes_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 178 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '19101010105', '高峰其', '202cb962ac59075b964b07152d234b70', 0, 20, '蒙古族', '126659@qq.com', '123456789123456719', '13695456335', 'image_upload/255d41cb4fde45bdb3ac8cc811d626fe.jpg', 2);
INSERT INTO `student` VALUES (2, '19101010011', '赵宇', '202cb962ac59075b964b07152d234b70', 0, 22, '汉族', '123652145@qq.com', '15635478963254661x', '16532456984', NULL, 1);
INSERT INTO `student` VALUES (3, '20101210203', '赵平安', '202cb962ac59075b964b07152d234b70', 0, 22, '汉族', '454612@163.com', '196454846464531818', '18545663698', NULL, 3);
INSERT INTO `student` VALUES (4, '19101010012', '张翠山', '202cb962ac59075b964b07152d234b70', 1, 23, '汉族', '24324212@qq.com', '154864565956484504', '15632145631', 'image_upload/40412fca9a834a4bad331395d0ce56e3.jpg', 1);
INSERT INTO `student` VALUES (5, '19101010013', '孙浩然', '202cb962ac59075b964b07152d234b70', 0, 24, '汉族', '24324213@qq.com', '154864564589484504', '15635987563', NULL, 1);
INSERT INTO `student` VALUES (6, '19101010014', '李慧', '202cb962ac59075b964b07152d234b70', 0, 25, '汉族', '24324214@qq.com', '134864565956484504', '15423655478', NULL, 1);
INSERT INTO `student` VALUES (7, '19101010015', '张武', '202cb962ac59075b964b07152d234b70', 0, 26, '汉族', '24324215@qq.com', '144586565956484504', '13695456335', NULL, 1);
INSERT INTO `student` VALUES (8, '19101010016', '宋旭', '202cb962ac59075b964b07152d234b70', 0, 27, '汉族', '24324216@qq.com', '129456595886484504', '15632145631', NULL, 1);
INSERT INTO `student` VALUES (9, '19172010606', '阿斯顿', '202cb962ac59075b964b07152d234b70', 0, 23, '蒙古族', '15635@qq.com', '156325478963254789', '15326987452', NULL, 7);
INSERT INTO `student` VALUES (10, '20101210301', '高晓飞', '202cb962ac59075b964b07152d234b70', 0, 21, '汉', '15629@1.com', '156365215963512422', '15632547896', NULL, 4);
INSERT INTO `student` VALUES (11, '19101010001', '张思思', '202cb962ac59075b964b07152d234b70', 0, 24, '汉族', '24324213@qq.com', '154864564589484504', '15635987563', NULL, 1);
INSERT INTO `student` VALUES (12, '19101010002', '高慧', '202cb962ac59075b964b07152d234b70', 0, 25, '汉族', '24324214@qq.com', '134864565956484504', '15423655478', NULL, 1);
INSERT INTO `student` VALUES (13, '19101010003', '汉娜', '202cb962ac59075b964b07152d234b70', 0, 26, '汉族', '24324215@qq.com', '144586565956484504', '13695456335', NULL, 1);
INSERT INTO `student` VALUES (14, '20101210301', '李雨青', '202cb962ac59075b964b07152d234b70', 1, 30, '汉族', '24324219@qq.com', '125584565956484504', '15632489654', NULL, 4);
INSERT INTO `student` VALUES (15, '19101010004', '韩束', '202cb962ac59075b964b07152d234b70', 0, 31, '汉族', '24324220@qq.com', '154864565956484504', '16365588528', NULL, 1);
INSERT INTO `student` VALUES (16, '19101010005', '王超', '202cb962ac59075b964b07152d234b70', 0, 21, '汉族', '126362@qq.com', '156324568796354116', '15632489654', NULL, 1);
INSERT INTO `student` VALUES (17, '19101010104', '高峰', '202cb962ac59075b964b07152d234b70', 0, 20, '蒙古族', '126659@qq.com', '123456789123456719', '13695456335', NULL, 2);
INSERT INTO `student` VALUES (18, '20101210201', '胡图', '202cb962ac59075b964b07152d234b70', 0, 22, '汉族', '123652145@qq.com', '15635478963254661x', '16532456984', NULL, 3);
INSERT INTO `student` VALUES (19, '20101210202', '张强', '202cb962ac59075b964b07152d234b70', 0, 22, '汉族', '454612@163.com', '196454846464531818', '18545663698', NULL, 3);
INSERT INTO `student` VALUES (20, '19101010006', '王琦', '202cb962ac59075b964b07152d234b70', 1, 23, '汉族', '24324212@qq.com', '154864565956484504', '15632145631', NULL, 1);
INSERT INTO `student` VALUES (21, '19101010007', '张拉斯', '202cb962ac59075b964b07152d234b70', 0, 24, '汉族', '24324213@qq.com', '154864564589484504', '15635987563', NULL, 1);
INSERT INTO `student` VALUES (22, '19101010008', '高慧', '202cb962ac59075b964b07152d234b70', 0, 25, '汉族', '24324214@qq.com', '134864565956484504', '15423655478', NULL, 1);
INSERT INTO `student` VALUES (23, '19101010009', '汉娜', '202cb962ac59075b964b07152d234b70', 0, 26, '汉族', '24324215@qq.com', '144586565956484504', '13695456335', NULL, 1);
INSERT INTO `student` VALUES (35, '21101110502', '胡强', '202cb962ac59075b964b07152d234b70', 0, 21, '汉', '123456@qq.com', '156354789632541478', '15632547896', NULL, 6);
INSERT INTO `student` VALUES (36, '18172010701', '李富荣', '202cb962ac59075b964b07152d234b70', 1, 21, '汉', '15713@com', '156325478963521458', '15632548963', NULL, 8);
INSERT INTO `student` VALUES (37, '21101110501', '张雅琪', '202cb962ac59075b964b07152d234b70', 1, 21, '汉', '13@com', '156325478963654721', '18547563254', NULL, 6);
INSERT INTO `student` VALUES (38, '19101010010', '李阳', '202cb962ac59075b964b07152d234b70', 0, 21, '汉族', '126362@qq.com', '156324568796354116', '15632489654', NULL, 1);
INSERT INTO `student` VALUES (65, '21101110402', '陈振', '202cb962ac59075b964b07152d234b70', 1, 28, '汉族', '24324217@qq.com', '154823565956484504', '15635987563', NULL, 5);
INSERT INTO `student` VALUES (66, '21101110401', '程功', '202cb962ac59075b964b07152d234b70', 1, 29, '汉族', '24324218@qq.com', '135486456956484504', '15635987563', NULL, 5);
INSERT INTO `student` VALUES (67, '19141610802', '李阿松', '202cb962ac59075b964b07152d234b70', 1, 30, '汉族', '24324219@qq.com', '125584565956484504', '15632489654', NULL, 9);
INSERT INTO `student` VALUES (68, '19101010017', '胡松', '202cb962ac59075b964b07152d234b70', 0, 31, '汉族', '24324220@qq.com', '154864565956484504', '16365588528', NULL, 1);
INSERT INTO `student` VALUES (69, '19141610801', '赵强', '202cb962ac59075b964b07152d234b70', 1, 32, '汉族', '24324221@qq.com', '123656456956484504', '15789532458', NULL, 9);
INSERT INTO `student` VALUES (140, '21101110503', '高晓', '202cb962ac59075b964b07152d234b70', 1, 18, '汉', '515152626@qq.com', '152634896354121123', '15965345871', NULL, 6);
INSERT INTO `student` VALUES (141, '19101010018', '李易鹏', '202cb962ac59075b964b07152d234b70', 0, 23, '汉', '2516154@163.com', '126354789635478963', '15879635472', NULL, 1);
INSERT INTO `student` VALUES (142, '19141610803', '张炎昭', '202cb962ac59075b964b07152d234b70', 0, 24, '蒙古族', '15659623@163.com', '156354789635426358', '15635478963', NULL, 9);
INSERT INTO `student` VALUES (143, '19141610804', '高清', '202cb962ac59075b964b07152d234b70', 0, 23, '汉', '1351862@qq.com', '156354478963544878', '15478965475', NULL, 9);
INSERT INTO `student` VALUES (158, NULL, '张晓刚', '202cb962ac59075b964b07152d234b70', 0, 21, '汉', '15635478@qq.com', '156325478963547896', '15648965347', NULL, 0);
INSERT INTO `student` VALUES (159, NULL, '王玉', '202cb962ac59075b964b07152d234b70', 1, 19, '汉', '86184@163.com', '156354789963325415', '15632578654', NULL, 0);
INSERT INTO `student` VALUES (160, NULL, '高校', '202cb962ac59075b964b07152d234b70', 0, 21, '汉', '1565@qq.com', '156355478962365478', '15635478963', NULL, 0);
INSERT INTO `student` VALUES (161, NULL, '胡适', '202cb962ac59075b964b07152d234b70', 0, 21, '汉', '15615@qq.com', '156354789635412548', '15654896545', NULL, 0);
INSERT INTO `student` VALUES (162, '19141610807', '汪小菲', '202cb962ac59075b964b07152d234b70', 1, 19, '汉', '4895@163.com', '145632547896354128', '15635449632', NULL, 9);
INSERT INTO `student` VALUES (163, '19141610806', '李飞', '202cb962ac59075b964b07152d234b70', 0, 19, '蒙古族', '15626@163.com', '136547965454896541', '15632644896', NULL, 9);
INSERT INTO `student` VALUES (164, '19141610805', '张霞', '202cb962ac59075b964b07152d234b70', 1, 21, '蒙古族', '5963w@qq.com', '156365489635236541', '15632547896', NULL, 9);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `bean_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `job_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务状态 0开启 1关闭',
  `job_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务分组',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (32, '开启学生选课', '学生选课定时开启', '0 52 20 * * ? ', 'com.example.task.QuartzJob', '0', '学生选课');
INSERT INTO `task` VALUES (37, '关闭学生选课', '学生选课定时关闭', '0 07 21 * * ? ', 'com.example.task.CloseJob', '0', '学生选课');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacher_id` int NOT NULL AUTO_INCREMENT,
  `teacher_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师工号',
  `teacher_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_age` int NULL DEFAULT NULL,
  `teacher_card` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_national` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `teacher_sex` int NULL DEFAULT NULL,
  `teacher_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacher_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, 'wmo0zgjc4j0ttci', '李达', 45, '156324589632458912', '汉族', 0, '1232@qq.com', 'image_upload/808ec269b0054159b6f1787cf2fc0299.jpg', '15632456984', '202cb962ac59075b964b07152d234b70');
INSERT INTO `teacher` VALUES (2, '30pgxy3uyng2iv9', '赵汉升', 40, '156324478963254716', '汉族', 0, '214212@qq.com', 'image_upload/8e9c578ab4944f298207e3fe729dd0ea.jpg', '15635478963', '202cb962ac59075b964b07152d234b70');
INSERT INTO `teacher` VALUES (3, 'wlv7fyl9ozt7xns', '赵如', 36, '156324778963325513', '汉族', 1, '12232@qq.com', NULL, '15632478963', '202cb962ac59075b964b07152d234b70');
INSERT INTO `teacher` VALUES (4, 'ssalm7r9ih3unit', '刘雅', 32, '156325478996556605', '汉族', 1, '12132@qq.com', NULL, '15632545896', '202cb962ac59075b964b07152d234b70');
INSERT INTO `teacher` VALUES (5, 'egelex1971tzrrv', '韩峰云', 32, '156325478996556936', '汉族', 1, '21432412@qq.com', NULL, '15632563658', '202cb962ac59075b964b07152d234b70');
INSERT INTO `teacher` VALUES (6, 'c2pdu1mtjptu7or', '韩宇', 54, '145632547896354268', '汉', 0, '1235@45.com', NULL, '15632489635', '202cb962ac59075b964b07152d234b70');
INSERT INTO `teacher` VALUES (37, 'wcseyho4gojw5jh', '赵山东', 45, '156321452632457672', '汉族', 0, '1232@qq.com', NULL, '15564456984', '202cb962ac59075b964b07152d234b70');
INSERT INTO `teacher` VALUES (38, 'tgfdneldi9e1ra9', '刘飒', 40, '156324247963254716', '汉族', 0, '214212@qq.com', NULL, '15635785963', '202cb962ac59075b964b07152d234b70');
INSERT INTO `teacher` VALUES (39, 'cy9tnhu80e332c7', '高宇', 36, '156357782163325513', '汉族', 0, '12232@qq.com', NULL, '15645478963', '202cb962ac59075b964b07152d234b70');
INSERT INTO `teacher` VALUES (40, 'jha8fdbjzrror8p', '赵宁', 32, '156324874726556605', '汉族', 1, '12132@qq.com', NULL, '15675458968', '202cb962ac59075b964b07152d234b70');
INSERT INTO `teacher` VALUES (41, 'i1wi3kjime9h1fp', '刘芳', 32, '151452775655693658', '汉族', 1, '21432412@qq.com', NULL, '15645763658', '202cb962ac59075b964b07152d234b70');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_num` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_age` int NULL DEFAULT NULL,
  `user_email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_sex` int NULL DEFAULT NULL,
  `user_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_national` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (4, 'wyl', '高兴', '202cb962ac59075b964b07152d234b70', 23, 'wyl_66666@163.com', '15636548963', 0, 'image_upload/a81000dbe3274be5a8a596eb1bc73023.jpg', '汉族', '156325634896352148');
INSERT INTO `user` VALUES (5, '1hdyi2eaqkcspbj', '张岩', '202cb962ac59075b964b07152d234b70', 23, '12659@qq.com', '15632489632', 0, 'image_upload/1414b0e6ab3a47cca50d8d4514cef013.jpg', '汉', '156325478963545698');
INSERT INTO `user` VALUES (6, 'j6co7xyqzutwxur', '阿斯顿', '202cb962ac59075b964b07152d234b70', 23, '15636542@qq.com', '15632478963', 0, NULL, '汉族', '156236547896352483');
INSERT INTO `user` VALUES (7, 'jb65ycwuywcygxw', '王旭', '202cb962ac59075b964b07152d234b70', 23, 'wyl_66666@163.com', '15693654789', 0, NULL, '汉族', '156324569845635489');
INSERT INTO `user` VALUES (8, 'tx1z4ohjovv49h8', '赵信', '202cb962ac59075b964b07152d234b70', 35, '12365@163.com', '15632489963', 0, NULL, '汉族', '156324596345896325');
INSERT INTO `user` VALUES (9, 'xyl9b7jge4qbmel', '张岩', '202cb962ac59075b964b07152d234b70', 32, '1634247158@qq.com', '15632547896', 1, NULL, '汉族', '156324596535478963');
INSERT INTO `user` VALUES (10, 'uchpfs48o79w123', '王飞', '202cb962ac59075b964b07152d234b70', 52, '1131378126@qq.com', '15632458961', 1, NULL, '汉', '156325478963541268');

SET FOREIGN_KEY_CHECKS = 1;
