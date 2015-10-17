package com.jack.service;

import com.jack.intf.business.IBusiness;
import com.jack.intf.business.ICCSBusinessAction;
import com.jack.intf.observer.ISupport;

public interface ICCSBusinessService extends IBusiness<String>,ISupport<ICCSBusinessAction>{

}
