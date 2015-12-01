/*
 * Copyright (C), 2014-2015, 达信财富投资管理（上海）有限公司
 * FileName: IBusinessService.java
 * Author:   zhangwei
 * Date:     2015年10月16日 上午11:57:29
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jack.intf.business;

import java.util.Map;
import com.jack.intf.observer.ISupport;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author zhangwei
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 * @param <S> 业务命名空间
 * @param <A> Action类型
 * @param <B> 具体业务类型
 */
public interface IBusiness<S,A,B> extends ISupport<IBusinessAction<S, A, B>>{
	
}
