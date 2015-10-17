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
package com.jack.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author zhangwei
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface IBusinessService<B>{
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
	<R> R modelAndView(B businessType,Object...params);
     /**
      * 
      * 功能描述: <br>
      * 〈功能详细描述〉
      *
      * @return
      * @see [相关类/方法](可选)
      * @since [产品/模块版本](可选)
      */
    <R> R query(B businessType,Object...params);
    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param params
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Transactional
    <R> R process(B businessType,Object...params);
    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param params
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    <R> R export(B businessType,Object...params);
    
}
