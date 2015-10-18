package com.jack.intf.observer;

public interface IObserver<S,T> extends IModelAndView<T>,IQuery<T>,IProcess<T>,IExport<T>,ISupport<S> {

}
