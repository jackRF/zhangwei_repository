(function ($) {
    var toString = Object.prototype.toString;
    $.extend({ code: {} });
    $.extend($.code, {
        idSeed: 1000,
        global: window,
        userAgent: navigator.userAgent.toLowerCase(),
        isDefined: function(value) {
            return typeof value !== 'undefined';
        },isDate: function(value) {
            return toString.call(value) === '[object Date]';
        },isString: function(value) {
            return typeof value === 'string';
        },isBoolean: function(value) {
            return typeof value === 'boolean';
        },isNumber: function(value) {
            return typeof value === 'number' && isFinite(value);
        }, isNumeric: function (value) {
            return !isNaN(parseFloat(value)) && isFinite(value);
        }, id: function (prefix) {
            var me = this,
             id =++me.idSeed;
            if (prefix) {
                id = prefix + me.idSeed;
            }
            return id;
        },
        createNamespaces: function () {
            var me = this, parts,part, ns = arguments ,nsl=ns.length;            
            for (var i = 0; i < nsl; i++) {
                var root =me.global;
                nsi = ns[i];
                if (nsi && typeof nsi === "string") {
                    parts = nsi.split('.');
                    for (var j = 0, jl = parts.length; j < jl; j++) {
                        part=parts[j];
                        root[part] = root[part] || {};
                        root = root[part];
                    }
                    if (i == 0 && nsl == 1) {
                        return root;
                    }
                }                
            }            
        },
        create: function (className,config) {
            var me = this, cls=className;
            if (typeof className == "string") {
                cls = me.createNamespaces(className);
            }
            if (cls && typeof cls == "function") {
                config = config || cls.defaultcfg;
                return new cls(config);
            } else { throw "Can't find class '"+className+"'";}
        }, String: {
            capitalize: function (string) {
                return string.charAt(0).toUpperCase() + string.substr(1);
            }
        }, Json: {
            m :{
                "\b": '\\b',
                "\t": '\\t',
                "\n": '\\n',
                "\f": '\\f',
                "\r": '\\r',
                '"': '\\"',
                "\\": '\\\\',
                '\x0b': '\\u000b'
            },
            charToReplace : /[\\\"\x00-\x1f\x7f-\uffff]/g,
            pad :function(n) {
                return n < 10 ? "0" + n : n;
            },
            encodeDate: function (o) {
               var dateFormat = 'yyyy-MM-dd HH:mm:ss';
               var  me = this;
                return o.getFullYear() + "-"
                + me.pad(o.getMonth() + 1) + "-"
                + me.pad(o.getDate()) + " "
                + me.pad(o.getHours()) + ":"
                + me.pad(o.getMinutes()) + ":"
                + me.pad(o.getSeconds());
            }, encodeString: function (s) {
                var me = this;
                return '"' + s.replace(me.charToReplace, function(a) {
                    var c = me.m[a];
                    return typeof c === 'string' ? c : '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                }) + '"';
            },encodeArray:function(o) {
                var me = this, a = ["[", ""],
                    len = o.length,
                    i;
                for (i = 0; i < len; i += 1) {
                    a.push(me.doEncode(o[i]), ',');
                }
                a[a.length - 1] = ']';
                return a.join("");
            }, encodeObject: function (o) {
                var me = this, a = ["{", ""],
                    i, val;
                for (i in o) {
                    val = o[i];
                    if (o.hasOwnProperty(i)) {
                        if (typeof val === 'function' || val === undefined) {
                            continue;
                        }
                        a.push(me.doEncode(i), ":", me.doEncode(val), ',');
                    }
                }
                a[a.length - 1] = '}';
                return a.join("");
            },
            doEncode: function (o, newline) {
                var me = this;
                if (o === null || o === undefined) {
                    return "null";
                } else if (typeof o == "number") {
                    return isFinite(o) ? String(o) : "null";
                } else if (o===true||o===false) {
                    return String(o);
                } else if ($.code.isDate(o)) {
                    return '"'+me.encodeDate(o)+'"';
                } else if ($.code.isString(o)) {
                    return me.encodeString(o);
                } else if (o.toJSON) {
                    return o.toJSON();
                } else if (typeof o === "function") {
                    return "null";
                }else if (o instanceof Array) {
                    return me.encodeArray(o);
                } else if (o instanceof Object) {
                    return me.encodeObject(o);
                } 
                return 'undefined';
            }
        },
        Array: {
            inArray: function (item, array) {
                var me=this,l=array.length;
                if (item === null || item === undefined||item==NaN) { return false;};
                for (var i = 0; i < l; i++) {
                    if (item == array[i]) {
                        return true;
                    }
                }
                return false;
            },
            each: function (array, fn, scope, reverse) {
                var i,
                    ln = array.length;
                if (reverse !== true) {
                    for (i = 0; i < ln; i++) {
                        if (fn.call(scope || array[i], array[i], i, array) === false) {
                            return i;
                        }
                    }
                }
                else {
                    for (i = ln - 1; i > -1; i--) {
                        if (fn.call(scope || array[i], array[i], i, array) === false) {
                            return i;
                        }
                    }
                }
                return true;
            }
        },
        Function: {
            bind: function (fn, scope, args, appendArgs) {
                if (arguments.length === 2) {
                    return function () {
                        return fn.apply(scope, arguments);
                    };
                }
                var method = fn,
                    slice = Array.prototype.slice;

                return function () {
                    var callArgs = args || arguments;

                    if (appendArgs === true) {
                        callArgs = slice.call(arguments, 0);
                        callArgs = callArgs.concat(args);
                    }
                    return method.apply(scope || $.code.global, callArgs);
                };
            }
        }
    });    
})(jQuery)