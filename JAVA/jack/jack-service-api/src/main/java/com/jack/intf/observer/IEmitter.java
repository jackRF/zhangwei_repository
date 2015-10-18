package com.jack.intf.observer;

import java.util.List;

public interface IEmitter<S,T> {
	<R,P>  R emit(List<IObserver<S>> observers, S supportKey,T type,P param,R r);
}
