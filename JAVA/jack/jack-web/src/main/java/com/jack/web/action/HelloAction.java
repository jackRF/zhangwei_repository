package com.jack.web.action;

//import java.text.SimpleDateFormat;
//import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.jack.entity.User;
//import com.jack.service.IUserService;
/**
 * 
 * @author Administrator
 *
 */
@ParentPackage(value = "JACK-STRUTS")
@Namespace("/wechat/hello")
public class HelloAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log logger= LogFactory.getLog(HelloAction.class);
	
//	@Autowired
//	private IUserService userService;
	
	@Action(value="test",results={
			@Result(name="success",type="json"),
			@Result(name="fail",type="json")
	})
	public String test(){
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//		System.out.println(sdf.format(new Date()));
//		User u= userService.queryUser(1L);
//		System.out.println(sdf.format(new Date()));
//		if(u!=null){
//			System.out.println(u.getId());
//			System.out.println(u.getName());
//			System.out.println(u.getUserName());
//			System.out.println("dubbo userService ....success");
//			logger.info("dubbo userService ....success");
//		}
		logger.info("test()...");
		return SUCCESS;
	}

}
