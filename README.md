# AopspringbootDemo
aop,restful,springboot

框架总结
SpringBoot 项目 java 11 aop框架 restful 风格
案例：对接阿里云短信
使用到主要的api
数据库:mybatis ，模板:freemarker，缓存:redis，文档:swagger，工具类:hutool
特点:aop切面编程，自生成代码，编码简洁规范，方便前后端分离
使用到的设计模式/场景：
1.枚举单例/配置参数
2.工厂模式/发送方式
3.模板方法模式/封装短信流程 （注：目前用的使其思想，但未按照抽象类方法去实现，使用了工厂模式代替抽象类部分）
swagger 地址:
http://127.0.0.1:8080/wend/swagger-ui.html
流程图：

问题:
解密目前放在了aop权限校验里面，这块是不是可以提出来
