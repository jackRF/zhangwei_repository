/*
 * Copyright (C), 2014-2015, 达信财富投资管理（上海）有限公司
 * FileName: IModelAndView.java
 * Author:   zhangwei
 * Date:     2015年10月15日 上午11:03:54
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jack.intf.observer;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author zhangwei
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface IModelAndView<T>{
    /**
     * 
     * 功能描述: <br>
     * 根据视图类型 准备model和获取视图
     *
     * 
     * @param type 视图类型(不同的视图类型，model可以不同，model具体类型由service根据视图类型决定
     * ,service实现上要根据视图类型把抽象的P强制转化为实际的类型)
     * @param param
     * @return 返回视图(如果没有“type”类型的视图返回首页 REDIRECT_INDEX或抛出异常 )
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
//    <R,P> R modelAndView(T type,P model);
    <R,P>  R modelAndView(T type,P param,R r);
}
