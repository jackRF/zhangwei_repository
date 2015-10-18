package com.jack.intf.observer;

public interface IEmitter<S,T> extends ISupport<S>{
	<R,P>  R emit(T type,P param,R r);
}
