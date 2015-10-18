package com.jack.intf.observer;

public interface IPublish<S,T>{
	<R,P>  R publish(S supportKey,T type,P param,R r);
}
