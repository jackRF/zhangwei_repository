/*
 * Copyright (C), 2014-2015, 达信财富投资管理（上海）有限公司
 * FileName: ISuppert.java
 * Author:   zhangwei
 * Date:     2015年10月15日 上午10:09:52
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
public interface ISupport<T>{    
    /**
     * 
     * 功能描述: <br>
     * 用于检测是否有权限
     *
     * @param supportKey
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
   boolean isSupport(T supportKey);
   /**
    * 多个观察者时，循环遍历到isSupport(T supportKey)返回true时会中断循环，通过判断最后一个结果状态来判断是否有观察者支持
    */
   ThreadLocal<Boolean> LAST_SUPPORT_RESULT=new ThreadLocal<Boolean>();
}
