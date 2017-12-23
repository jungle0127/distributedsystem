# Redis安装

下载 redis-4.0.6.tar.gz
解压： tar -zxvf redis-4.0.6.tar.gz
编译： make
安装： make PREFIX=/usr/local/redis
cp ~/redis-4.0.6/redis.conf /usr/local/redis

修改redis.conf的读写权限：
chmod 666 /usr/local/redis/redis.conf

> 权限码描述

> sudo chmod 600 ××× （只有所有者有读和写的权限）
> sudo chmod 644 ××× （所有者有读和写的权限，组用户只有读的权限）
> sudo chmod 700 ××× （只有所有者有读和写以及执行的权限）
> sudo chmod 666 ××× （每个人都有读和写的权限）
> sudo chmod 777 ××× （每个人都有读和写以及执行的权限）

修改配置使redis后台执行：
vi redis.conf
daemonize no 改为： yes

启动：
/usr/local/redis/bin/redis-server /usr/local/redis/redis.conf
默认端口号：6379

测试：
/usr/local/redis/bin/redis-cli
set name ps
get name
keys *
del name
getset name lotus

打开端口6379使其可远程连接：

查看iptables:
whereis iptables;
iptables -L
iptables -S

添加iptables 配置：

iptables -A INPUT -p tcp -m tcp -dport 6379 -j ACCEPT

开机加载iptables:

iptable-save > /etc/iptables.rules
修改：/etc/network/interfaces
末行添加：pre-up iptables-restor < /etc/iptables.rules


http://wiki.ubuntu.org.cn/IptablesHowTo
http://www.linuxidc.com/Linux/2016-12/138605.htm
http://blog.csdn.net/qazcxh/article/details/46315025