package com.jack.comp.abstr;

import com.jack.entity.User;
import com.jack.intf.observer.IEmitter;
import com.jack.intf.observer.IObserver;
import com.jack.service.IBusinessServiceConstant;

public abstract class AbstractServiceObserver<T> extends AbstractUserSupport implements IObserver<User,T>,IEmitter<User, T>, IBusinessServiceConstant {
	protected String role;
	protected AbstractServiceObserver(String role) {
		this.role=role;
	}
	@Override
	public boolean isSupport(User supportKey) {
		return super.isSupport(supportKey, role);
	}
	@Override
	public <R, P> R emit(T type, P param, R r) {
		Integer actionType = LOCAL_BUSINESS_ACTION.get().getActionType();
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
