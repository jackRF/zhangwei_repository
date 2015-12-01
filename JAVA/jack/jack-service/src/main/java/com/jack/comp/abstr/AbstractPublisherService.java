package com.jack.comp.abstr;

import java.util.List;

import com.jack.intf.observer.IEmitter;
import com.jack.intf.observer.IPublish;
import com.jack.service.IBusinessServiceConstant;
import com.jack.intf.ITask;

public abstract class  AbstractPublisherService<S,T,O extends IEmitter<S, T>> implements IPublish<S,T>,IBusinessServiceConstant{
	protected abstract List<O> getObservers();
	@Override
	public <R,P>  R publish(S supportKey,T type,P param,R r){
		for(IEmitter<S, T> observer:getObservers()){
			if(observer.isSupport(supportKey)){
				r=observer.emit(type, param, r);
				break;
			}
		}
		return r;
	}
	protected <R> R callObserver(ITask<O> task, S supportKey,R r){
		boolean isSuccess=false;
		for(O observer:getObservers()){
			if(observer.isSupport(supportKey)){
				isSuccess=true;
				r=task.toDo(observer);
				break;
			}
		}
		if(!isSuccess){
			task.onFail();
		}
		return r;
	}
}
