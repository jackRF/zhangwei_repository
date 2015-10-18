package com.jack.intf.observer;

public interface IEmitter<S,T> {
	<P,R>  R emit(S supportKey,T type,P param,R r);
}
