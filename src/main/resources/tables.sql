/*创建日志表*/
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `TYPE` varchar(255) NOT NULL COMMENT '类型：1表示系统信息，2表示日志内容信息',
  `SYSTEM_INFO` varchar(255) DEFAULT NULL COMMENT '系统类型-位数，当type=1时候启用',
  `HOST` varchar(255) DEFAULT NULL COMMENT 'ip，当type=1时候启用',
  `DB_CONNECT` varchar(255) DEFAULT NULL COMMENT '连接目标数据库结果，当type=1时候启用',
  `LOCATION` varchar(255) DEFAULT NULL,
  `LOG_LEVEL` varchar(255) DEFAULT NULL,
  `MESSAGE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/**
 用户表
 */
 create table user(
 id int not null primary key auto_increment,
 name varchar(20) null default null,
 phone varchar(11) null default  null
);

/*创建密码表*/
create table user_password(
   id int not null primary key auto_increment,
   t_id int not null ,
   t_password varchar(32) not null,
   level int(1)
)

/*创建token表*/
create table token(
 id  int primary  key not null auto_increment,
 user_id int not null ,
 token char(32),
 createDate timestamp
)
/*邮件推送表*/
CREATE TABLE msg_push(
 id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 config_id INT COMMENT '邮件配置表',
 STATUS INT(1) COMMENT '0:未发送;1:已发送',
 receiver VARCHAR(300) COMMENT '接收方，以json格式储存',
 copyReceiver VARCHAR(300) COMMENT '抄送方，以json格式储存',
 subject VARCHAR(300) COMMENT '主题',
 content LONGTEXT COMMENT '正文',
 createTime TIMESTAMP COMMENT '创建时间'
)
/*邮件配置表*/
CREATE TABLE msg_config(
 id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 HOST VARCHAR(50) COMMENT '发送主机host',
 sender VARCHAR(50) COMMENT '发送方',
 PASSWORD VARCHAR(50) COMMENT '密码',
 LEVEL INT(1) DEFAULT 0 COMMENT '级别，默认为0'
)