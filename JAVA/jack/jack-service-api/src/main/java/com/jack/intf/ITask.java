package com.jack.intf;

public interface ITask<O> {
	<R> R toDo(O o);
	void onFail();
}
