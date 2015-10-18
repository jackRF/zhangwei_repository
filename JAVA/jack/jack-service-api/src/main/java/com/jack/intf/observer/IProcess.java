/*
 * Copyright (C), 2014-2015, 达信财富投资管理（上海）有限公司
 * FileName: IProcess.java
 * Author:   zhangwei
 * Date:     2015年10月15日 上午11:52:30
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jack.intf.observer;

import org.springframework.transaction.annotation.Transactional;
/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author zhangwei
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface IProcess<T>{
	/**
     * 
     * 功能描述: <br>
     * 处理业务
     *
     * @param type 处理类型 (不同的处理类型，参数、返回值可以不同，参数、返回值具体类型由service根据处理类型决定
     * ，service实现上要根据处理类型把抽象的P强制转化为实际的类型，把处理结果强制转化为抽象类型R)
     * @param param 参数
     * @param r 参考返回值类型的作用和默认返回值(如果没有"type"这种类型的处理)
     * @return 返回值(类型和r的类型一致)
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
	@Transactional
	<R,P>  R process(T type,P param,R r);
}
