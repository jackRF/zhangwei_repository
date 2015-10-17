
package com.jack.intf.observer;

/**
 * 
 * @author Administrator
 *
 * @param <S>  Support 类型
 * @param <T>  Query 类型
 */
public interface IObServer<S,T> extends ISupport<S>,IModelAndView<T>,IQuery<T>,IProcess<T>,IExport<T>{

}
