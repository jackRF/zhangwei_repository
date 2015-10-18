package com.jack.cts.observer.service;

import com.jack.entity.User;
import com.jack.intf.observer.IExport;
import com.jack.intf.observer.IModelAndView;
import com.jack.intf.observer.IProcess;
import com.jack.intf.observer.IQuery;
import com.jack.intf.observer.ISupport;

public interface IApproveService extends IModelAndView<Integer>,IQuery<Integer>,IProcess<Integer>,IExport<Integer>,ISupport<User>{

}
