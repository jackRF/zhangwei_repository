package com.jack.service;

import org.springframework.transaction.annotation.Transactional;

import com.jack.intf.business.IBusiness;
import com.jack.intf.business.IBusinessAction;

public interface IBusinessService extends IServiceConstant,IBusiness<String,Integer,String>{
	ThreadLocal<IBusinessAction<String,Integer,String>> LOCAL_BUSINESS_ACTION=new ThreadLocal<IBusinessAction<String,Integer,String>>();
	/**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
	<R> R modelAndView(String businessType,Object...params);
     /**
      * 
      * 功能描述: <br>
      * 〈功能详细描述〉
      *
      * @return
      * @see [相关类/方法](可选)
      * @since [产品/模块版本](可选)
      */
    <R> R query(String businessType,Object...params);
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
    <R> R process(String businessType,Object...params);
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
    <R> R export(String businessType,Object...params);
}
