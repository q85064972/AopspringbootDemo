package com.sms.msgsend.configure;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName MybatisMetaObjectHandler
 * Description
 *
 * @author JIJL
 * @date 2020/8/18 15:45
 * @Copyright 2020 www.yqcx.faw.cn Inc. All rights reserved.
 */
@Slf4j
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {

		Date date = new Date();
		// todo userId 的获取 从redis里面获取用户登录信息
		Long userId = 123L;

		this.strictInsertFill(metaObject, "createBy", Long.class, userId);
		this.strictInsertFill(metaObject, "updateBy", Long.class, userId);
		this.strictInsertFill(metaObject, "createDate", Date.class, date);
		this.strictInsertFill(metaObject, "updateDate", Date.class, date);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		Date date = new Date();
		Long userId = 123L;

		this.strictUpdateFill(metaObject, "updateBy", Long.class, userId);
		this.strictUpdateFill(metaObject, "updateDate", Date.class, date);
	}
}
