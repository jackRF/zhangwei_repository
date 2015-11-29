package com.jack.comp.abstr;

import java.util.List;

import com.jack.intf.observer.IEmitter;
import com.jack.intf.observer.IPublish;

public abstract class  AbstractServicePublisher<S,T,O extends IEmitter<S, T>> extends AbstractBusinessService implements IPublish<S,T>{
	protected abstract List<O> getObservers();
	@Override
	public <R,P>  R publish(S supportKey,T type,P param,R r){
		for(IEmitter<S, T> observer:getObservers()){
			if(observer.isSupport(supportKey)){
				r=observer.emit(type, param, r);
			}
		}
		return r;
	}
}
