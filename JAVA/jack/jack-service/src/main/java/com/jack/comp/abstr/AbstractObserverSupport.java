package com.jack.comp.abstr;

import java.util.List;

import com.jack.intf.observer.IEmitter;
import com.jack.intf.observer.IObserver;
import com.jack.service.IServiceConstant;

public class AbstractObserverSupport<S,T> implements IEmitter<S, T>,IServiceConstant{

	@Override
	public <R,P> R emit(List<IObserver<S>> observers, S supportKey, T type, P param, R r) {
		for(IObserver<S> observer:observers){
			if(observer.isSupport(supportKey)){
				IObserver.LAST_SUPPORT_RESULT.set(true);
				Integer actionType=LOCAL_ACTION_TYPE.get();
				if(ACTION_TYPE_MODELANDVIEW==actionType){
//					r=observer.modelAndView(type, param);
				}else if(ACTION_TYPE_QUERY==actionType){
					r=observer.query(actionType, param, r);
				}else if(ACTION_TYPE_PROCESS==actionType){
					r=observer.process(actionType, param, r);
				}else if(ACTION_TYPE_PROCESS==actionType){
					r=observer.process(actionType, param, r);
				}
				break;
			}
		}
		IObserver.LAST_SUPPORT_RESULT.set(false);
		return r;
	}

//	@Override
//	public <P, R> R emit(List<IObserver<S>> observers,S supportKey, T type, P param, R r) {
//		boolean isSupport=false;
//		for(ISupport<S> observers:observers){
//			if(approveService.isSupport(user)){
//				Integer actionType=LOCAL_ACTION_TYPE.get();
//				if(ACTION_TYPE_MODELANDVIEW==actionType){
//					r=approveService.modelAndView(type, param);
//				}else if(ACTION_TYPE_QUERY==actionType){
//					r=approveService.query(actionType, param, r);
//				}else if(ACTION_TYPE_PROCESS==actionType){
//					r=approveService.process(actionType, param, r);
//				}else if(ACTION_TYPE_PROCESS==actionType){
//					r=approveService.process(actionType, param, r);
//				}
//				isSupport=true;
//				break;
//			}
//		}
//		LAST_SUPPORT_RESULT.set(isSupport);
//		return r;
//	}

}
