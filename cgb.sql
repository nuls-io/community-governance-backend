-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.21-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.2.0.4675
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 cg 的数据库结构
CREATE DATABASE IF NOT EXISTS `cg` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cg`;


-- 导出  表 cg.tb_agency_relation 结构
CREATE TABLE IF NOT EXISTS `tb_agency_relation` (
  `agent` varchar(40) NOT NULL COMMENT '代理人地址',
  `mandator` varchar(40) NOT NULL COMMENT '委托人地址',
  `status` tinyint(4) NOT NULL COMMENT '该条数据是否有效 0:失效 1有效',
  `create_time` bigint(15) NOT NULL COMMENT 'DB数据创建时间',
  `update_time` bigint(15) NOT NULL COMMENT 'DB数据修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理地址与委托人关系表';

-- 数据导出被取消选择。


-- 导出  表 cg.tb_alias 结构
CREATE TABLE IF NOT EXISTS `tb_alias` (
  `address` varchar(40) NOT NULL COMMENT '账户地址',
  `alias` varchar(20) NOT NULL COMMENT '账户别名',
  PRIMARY KEY (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地址对应别名';

-- 数据导出被取消选择。


-- 导出  表 cg.tb_applicant 结构
CREATE TABLE IF NOT EXISTS `tb_applicant` (
  `address` varchar(40) NOT NULL COMMENT '账户地址',
  `type` tinyint(4) unsigned NOT NULL COMMENT '申请理事的类型',
  `director` tinyint(4) unsigned NOT NULL COMMENT '是否已成为理事 0:不是 1是',
  `desc` text COMMENT '介绍',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `counts` int(11) NOT NULL COMMENT '投票用户总数',
  `amount` decimal(20,4) unsigned NOT NULL COMMENT '总得票数(后期是按照用户所有余额来统计的)',
  `status` tinyint(4) unsigned NOT NULL COMMENT '该条数据是否有效 0:失效 1有效',
  `create_time` bigint(15) NOT NULL COMMENT 'DB数据创建时间',
  `update_time` bigint(15) NOT NULL COMMENT 'DB数据更新时间',
  PRIMARY KEY (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='理事申请人';

-- 数据导出被取消选择。


-- 导出  表 cg.tb_applicant_record 结构
CREATE TABLE IF NOT EXISTS `tb_applicant_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `applicant` varchar(40) NOT NULL COMMENT '理事申请人(被投票者)',
  `voter` varchar(40) NOT NULL COMMENT '投票者',
  `amount` decimal(20,4) NOT NULL COMMENT '票数',
  `status` tinyint(4) NOT NULL COMMENT '该条数据是否有效 0:失效 1有效',
  `create_time` bigint(15) NOT NULL COMMENT 'DB数据创建时间',
  `update_time` bigint(15) NOT NULL COMMENT 'DB数据修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='给理事申请人投票的记录表';

-- 数据导出被取消选择。


-- 导出  表 cg.tb_block_header 结构
CREATE TABLE IF NOT EXISTS `tb_block_header` (
  `height` bigint(20) NOT NULL COMMENT '高度',
  `hash` varchar(128) NOT NULL COMMENT '区块头hash',
  `pre_hash` varchar(128) NOT NULL COMMENT '上一区块头hash',
  `create_time` bigint(15) NOT NULL COMMENT 'DB数据创建时间',
  PRIMARY KEY (`height`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区块同步表，主要获取之前同步记录';

-- 数据导出被取消选择。


-- 导出  表 cg.tb_player 结构
CREATE TABLE IF NOT EXISTS `tb_player` (
  `address` varchar(40) NOT NULL COMMENT '社区治理所有投票参与者(需要维护票数)',
  PRIMARY KEY (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社区治理所有投票参与者(需要维护票数)';

-- 数据导出被取消选择。


-- 导出  表 cg.tb_proposal 结构
CREATE TABLE IF NOT EXISTS `tb_proposal` (
  `proposal_id` int(11) NOT NULL COMMENT '合约提案id',
  `name` varchar(255) NOT NULL COMMENT '提案名称',
  `type` tinyint(4) NOT NULL COMMENT '提案类型 1;角色, 2:系统参数, 3:社区基金, 4:其他',
  `desc` text NOT NULL COMMENT '提案简介',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `owner` varchar(40) NOT NULL COMMENT '提案发起人地址',
  `status` tinyint(4) NOT NULL COMMENT '状态 1:审核中, 2:审核拒绝, 3:投票中, 4:投票通过,执行中, 5:投票未通过, 6:已执行',
  `start_time` datetime DEFAULT NULL COMMENT '投票开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '投票结束时间',
  `counts` int(11) NOT NULL COMMENT '投票人总数',
  `favour` decimal(20,4) NOT NULL COMMENT '同意总票数',
  `against` decimal(20,4) NOT NULL COMMENT '不同意总票数',
  `abstention` decimal(20,4) NOT NULL COMMENT '弃权总票数',
  `refund` tinyint(4) NOT NULL COMMENT '是否退还押金 0未退还, 1：已退还',
  `create_time` bigint(15) NOT NULL COMMENT 'DB数据创建时间',
  `update_time` bigint(15) NOT NULL COMMENT 'DB数据修改时间',
  PRIMARY KEY (`proposal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提案申请表';

-- 数据导出被取消选择。


-- 导出  表 cg.tb_proposal_audit 结构
CREATE TABLE IF NOT EXISTS `tb_proposal_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proposal_id` int(11) NOT NULL DEFAULT '0' COMMENT '合约提案id',
  `address` varchar(40) NOT NULL DEFAULT '0' COMMENT '审核人地址',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核结果 0:审核拒绝, 1:审核通过',
  `reason` varchar(255) DEFAULT '0' COMMENT '拒绝原因',
  `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT 'DB数据创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提案审核记录表';

-- 数据导出被取消选择。


-- 导出  表 cg.tb_proposal_vote_record 结构
CREATE TABLE IF NOT EXISTS `tb_proposal_vote_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `proposal_id` int(11) NOT NULL COMMENT '合约提案id',
  `voter` varchar(40) NOT NULL COMMENT '投票人地址',
  `result` tinyint(4) NOT NULL COMMENT '投票结果 1:支持, 2:反对, 3:弃权',
  `amount` decimal(20,4) NOT NULL COMMENT '当前票数',
  `create_time` bigint(15) NOT NULL COMMENT 'DB数据创建时间',
  `update_time` bigint(15) NOT NULL COMMENT 'DB数据修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 cg.tb_vote 结构
CREATE TABLE IF NOT EXISTS `tb_vote` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `contract_address` varchar(64) NOT NULL COMMENT '合约地址',
  `contract_vote_id` bigint(10) NOT NULL COMMENT '智能合约的投票id',
  `title` varchar(90) NOT NULL COMMENT '投票标题',
  `description` varchar(450) NOT NULL COMMENT '投票信息介绍',
  `has_multiple_select` tinyint(4) unsigned DEFAULT NULL COMMENT '单选或者是多选 0:单选 1:多选',
  `min_select_count` tinyint(4) unsigned DEFAULT NULL COMMENT '至少可选几个选项',
  `max_select_count` tinyint(4) unsigned DEFAULT NULL COMMENT '最多可选几个选项',
  `vote_can_modify` tinyint(4) unsigned DEFAULT NULL COMMENT '投票后是否允许改票 0:不允许 1:允许',
  `start_time` datetime DEFAULT NULL COMMENT '投票开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '投票结束时间',
  `deposit` bigint(40) unsigned NOT NULL COMMENT '保证金(押金)',
  `status` tinyint(4) unsigned NOT NULL COMMENT '待确认:0  未开始:1 进行中:2  已结束:3',
  `counts` int(11) DEFAULT NULL COMMENT '总投票用户数',
  `amount` decimal(20,4) NOT NULL COMMENT '总投票数(后期是按照用户所有余额来统计的)',
  `block_height` bigint(10) DEFAULT NULL COMMENT '创建投票时区块高度',
  `creator` varchar(90) NOT NULL COMMENT '创建人钱包地址',
  `refund` tinyint(4) NOT NULL COMMENT '是否退还押金 0未退还, 1：已退还',
  `create_time` bigint(15) NOT NULL COMMENT 'DB数据创建时间',
  `update_time` bigint(15) NOT NULL COMMENT 'DB数据更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投票表';

-- 数据导出被取消选择。


-- 导出  表 cg.tb_vote_item 结构
CREATE TABLE IF NOT EXISTS `tb_vote_item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '选项id',
  `vote_id` bigint(20) unsigned NOT NULL COMMENT '关联投票id',
  `item_id` bigint(20) unsigned NOT NULL COMMENT '选项的id',
  `content` varchar(60) NOT NULL COMMENT '投票选项内容',
  `counts` int(11) DEFAULT NULL COMMENT '总投票用户数',
  `amount` decimal(20,4) NOT NULL COMMENT '总投票数(后期是按照用户所有余额来统计的)',
  `create_time` bigint(15) NOT NULL COMMENT 'DB数据创建时间',
  `update_time` bigint(15) NOT NULL COMMENT 'DB数据更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投票选项表';

-- 数据导出被取消选择。


-- 导出  表 cg.tb_vote_record 结构
CREATE TABLE IF NOT EXISTS `tb_vote_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `vote_id` bigint(20) unsigned NOT NULL COMMENT '关联投票id',
  `item_id` bigint(20) unsigned NOT NULL COMMENT '关联投票选项id',
  `voter` varchar(90) NOT NULL COMMENT '投票人地址',
  `amount` decimal(20,4) unsigned NOT NULL COMMENT '票数',
  `cancel_type` tinyint(4) NOT NULL COMMENT '是否取消投票 0:否 1:是',
  `create_time` bigint(15) NOT NULL COMMENT 'DB数据创建时间',
  `update_time` bigint(15) NOT NULL COMMENT 'DB数据更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投票记录表';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
