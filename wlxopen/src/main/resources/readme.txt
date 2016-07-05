本项目采用技术SpringMvc3.0+Hibernate3.0+缓存Ehcache+数据源c3p0

初始化项目：用于添加两个管理员账号
http://localhost:8080/wlxopen/common/systeminit.uhtml

该系统有三种类型文章和一个回音壁。
一是新闻类型文章：有标题有内容，有附件，有预览图片。适合发布新闻通知
二是图片类型文章：有图片标题，图片。适合通过图片展示新闻。
三是下载文件文章：有文件标题，文件，可以下载。

下面是不同类型新闻和回音壁的程序连接。

1.新闻浏览程序连接：
control/news/list.action?
管理连接：
control/news/list.action?

2.下载文件浏览连接：
control/download/list.action?type=NO_IMAGE&
管理连接：
control/download/list.action?type=NO_IMAGE&

3.图片文件浏览连接：
control/download/list.action?type=IMAGE&
管理连接：
control/download/list.action?type=IMAGE&

4.回音壁浏览连接：
control/question/list.action?
管理连接：
control/question/list.action?
