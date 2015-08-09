package com.jack.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
/**
 * 
 * @author Administrator
 *
 */
@ParentPackage(value = "JACK-STRUTS")
public class HelloAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log logger= LogFactory.getLog(HelloAction.class);
	
	@Action(value="test",results={
			@Result(name="success",type="json"),
			@Result(name="fail",type="json")
	})
	public String test(){
		logger.info("test()...");
		return SUCCESS;
	}

}
