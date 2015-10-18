package com.jack.comp.abstr;

import com.jack.entity.User;
import com.jack.intf.observer.IEmitter;
import com.jack.intf.observer.IObserver;
import com.jack.service.IServiceConstant;

public abstract class AbstractObserver<T> extends AbstractUserSupport implements IObserver<User,T>,IEmitter<User, T>, IServiceConstant {
	protected String role;
	protected AbstractObserver(String role) {
		this.role=role;
	}
	@Override
	public boolean isSupport(User supportKey) {
		return super.isSupport(supportKey, role);
	}
	@Override
	public <R, P> R emit(T type, P param, R r) {
		Integer actionType = LOCAL_ACTION_TYPE.get();
		if (ACTION_TYPE_MODELANDVIEW == actionType) {
			r = this.modelAndView(type, param, r);
		} else if (ACTION_TYPE_QUERY == actionType) {
			r = this.query(type, param, r);
		} else if (ACTION_TYPE_PROCESS == actionType) {
			r = this.process(type, param, r);
		} else if (ACTION_TYPE_PROCESS == actionType) {
			r = this.process(type, param, r);
		}
		return r;
	}
}
