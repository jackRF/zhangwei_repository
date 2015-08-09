(function ($) {
    $.extend({ direct: {} });
    $.extend($.direct, {
        Manager: {
            providers: {
                _providers: {},
                add: function (provider) {
                    var me = this;
                    var providers = me._providers[provider.type] = me._providers[provider.type] || {};
                    providers[provider.name] = provider;
                }
            },
            addProvider: function (provider) {
                var me = this,
                    args = arguments,
                    i, len;
                if (args.length > 1) {
                    for (i = 0, len = args.length; i < len; ++i) {
                        me.addProvider(args[i]);
                    }
                    return;
                }
                if (!provider.isProvider) {
                    provider = new $.direct[$.code.String.capitalize(provider.type) + "Provider"](provider);
                }
                me.providers.add(provider);
                if (!provider.isConnected()) {
                    provider.connect();
                }
                return provider;
            }
        },
        RemotingMethod: function (config) {
            var me = this,
                params = $.code.isDefined(config.params) ? config.params : config.len,
               name, pLen, p, param;
            me.name = config.name;
            me.formHandler = config.formHandler;
            if (typeof params === "number") {
                me.len = params;
                me.ordered = true;
            }
            else {
                me.params = {};
                pLen = params.length;
                for (p = 0; p < pLen; p++) {
                    param = params[p];
                    name = typeof param === "object" ? param.name : param;
                    me.params[name] = true;
                }
            }
            $.extend(me, {
                getCallData: function (args) {
                    var me = this,
                        data = null,
                        len = me.len,
                        params = me.params,
                        callback, scope, name, options;

                    if (me.ordered) {
                        callback = args[len];
                        scope = args[len + 1];
                        options = args[len + 2];

                        if (len !== 0) {
                            data = args.slice(0, len);
                        }
                    }
                    else {
                        data = $.extend({}, args[0]);
                        callback = args[1];
                        scope = args[2];
                        options = args[3];

                        // filter out any non-existent properties
                        for (name in data) {
                            if (data.hasOwnProperty(name) && !params[name]) {
                                delete data[name];
                            }
                        }
                    }

                    return {
                        data: data,
                        callback: callback,
                        scope: scope,
                        options: options
                    };
                }
            })
            return me;
        },
        RemotingProvider: function (config) {
            $.extend(this, {
                isProvider: true,
                type:"remoting",
                isConnected: function () {
                    return !!this.connected;
                },
                connect: function () {
                    var me = this;
                    if (me.url) {
                        me.initAPI();
                        me.connected = true;
                    }
                },
                initAPI: function () {
                    var me = this,
                        actions = me.actions,
                        namespace = me.namespace,
                        action, cls, methods, i, len, method;
                    for (action in actions) {
                        if (actions.hasOwnProperty(action)) {
                            cls = $.code.createNamespaces(namespace+"."+action);
                            methods = actions[action];
                            for (i = 0, len = methods.length; i < len; ++i) {
                                method = new $.direct.RemotingMethod(methods[i]);
                                cls[method.name] = me.createHandler(action, method);
                            }
                        }
                    }
                },
                createHandler: function (action, method) {
                    var me = this,
                        slice = Array.prototype.slice,
                        handler;
                    if (!method.formHandler) {
                        handler = function () {
                            me.configureRequest(action, method, slice.call(arguments, 0));
                        };
                    }
                    else {
                        handler = function (form, callback, scope) {
                            me.configureFormRequest(action, method, form, callback, scope);
                        };
                    }
                    handler.directCfg = {
                        action: action,
                        method: method
                    };

                    return handler;
                },
                getCallData: function (data, action, method) {
                    var requestData={
                        action:action,
                        method:method,
                        type:'rpc',
                        tid: $.code.id()
                    };
                    if (data) {
                        requestData.data = data;
                    }
                    return requestData;
                },
                configureRequest: function (action, method, args) {
                    var me = this,
                    callData, data, callback, scope, opts, transaction, params;
                    callData = method.getCallData(args);
                    data = callData.data;
                    callback = callData.callback;
                    scope = callData.scope;
                    opts = callData.options || {};

                    params = $.extend({}, {
                        provider: me,
                        args: args,
                        action: action,
                        method: method.name,
                        data: data,
                        callbackOptions: opts,
                        callback: scope &&typeof callback=="function" ? $.code.Function.bind(callback, scope) : callback
                    });
                    data = me.getCallData(data, action, method.name);
                    data = $.code.Json.doEncode(data);
                    $.ajax({
                        async: true,
                        type:"POST",
                        url:me.url,
                        data:data,
                        cache:false,
                        contentType: "application/x-www-form-urlencoded;charset=utf-8",
                        dataType: "json",
                        timeout: 7000,
                        error: function () {
                            throw params;
                        },
                        success:function(data){
                            params.callback(data,params.callbackOptions, params);
                        }
                    })
                },
                configureFormRequest: function () {
                    //待完成
                }
            })
            $.extend(this, config);
            return this;
        }
    });
    $.extend({
        Direct: $.direct.Manager
    });
})(jQuery)