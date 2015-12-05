/*
 * Copyright (C), 2014-2015, 达信财富投资管理（上海）有限公司
 * FileName: ICTSBusinessConstant.java
 * Author:   zhangwei
 * Date:     2015年10月15日 上午11:52:30
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jack.cts.intf;
/**
 * 〈一句话功能简述〉<br> 
 * 信审业务常量,用于service实现和Controller配置
 *
 * @author zhangwei
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface ICTSBusinessConstant {
	/**
	 * 信审业务命名空间
	 */
	String NS_CTS="CTS";
	/**
	 * 
	 */
	String BT_MV_LOANAPPLY_LIST="MV_LOANAPPLY_LIST";
	/**
	 * 信审借款详细
	 */
	String BT_MV_LOANAPPLY_DETAIL="MV_LOANAPPLY_DETAIL";
	/**
	 * 信审借款列表查询
	 */
	String BT_QUERY_LOANAPPLY_LIST="QUERY_LOANAPPLY_LIST";
	/**
	 * 保存User
	 */
	String BT_PROCESS_USER_SAVE="PROCESS_USER_SAVE";
	
}
