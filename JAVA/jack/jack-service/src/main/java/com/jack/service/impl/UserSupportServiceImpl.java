/*
 * Copyright (C), 2014-2015, 达信财富投资管理（上海）有限公司
 * FileName: UserSupportServiceImpl.java
 * Author:   zhangwei
 * Date:     2015年10月15日 上午11:52:30
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jack.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jack.entity.User;
/**
 * 〈一句话功能简述〉<br> 
 * 不要直接@Autowired 注入，而是通过继承AbstractUserSupportImpl使用
 * ，否则它的作用域不是session导致多个用户频繁交替访问，出现重复查询数据的情况
 * 
 *
 * @author zhangwei
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
@Scope("session")
public class UserSupportServiceImpl extends AbstractUserSupportImpl{
	
	private User user;
	
	@Override
	public boolean isSupport(User user,String role){
		if(this.user==null){
			this.user=user;
			//roles
			//return hasRole role
		}
		return false;
	}
}
