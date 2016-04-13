// download by http://www.codefans.net
if (typeof Core == 'undefined' || !Core) {
	var Core = {};
}
Core.namespace = function() {
	var A = arguments, E = null, C, B, D;
	for (C = 0; C < A.length; C += 1) {
		D = A[C].split(".");
		E = Core;
		for (B = (D[0] == "Core") ? 1 : 0; B < D.length; B += 1) {
			E[D[B]] = E[D[B]] || {};
			E = E[D[B]];
		}
	}
	return E;
};
Core.log = function(msg, cat, src) {
	var l = Core.widget.Logger;
	if (l && l.log) {
		return l.log(msg, cat, src);
	} else {
		return false;
	}
};
Core.methodPerfile = function(component) {
	var keys, that = this, returnValue;
	this.timer = {};
	this.component = component;
	for (keys in this.component) {
		if (Core.lang.isFunction(this.component[keys])) {
			(function(methodName) {
				that[methodName] = function() {
					that.startTimer(methodName);
					var returnValue = that.component[methodName].apply(
							that.component, arguments);
					that.displayTime(methodName, that
									.getElapsedTime(methodName));
					return returnValue;
				}
			})(keys);
		}
	}
};
Core.methodPerfile.prototype = {
	startTimer : function(methodName) {
		var time = new Date();
		this.timer[methodName] = time.getTime();
	},
	getElapsedTime : function(methodName) {
		var time = new Date();
		return time.getTime() - this.timer[methodName];
	},
	displayTime : function(methodName, time) {
		Core.log(methodName + '閺傝纭堕惃鍕⒔鐞涘本妞傞梻缈犺礋閿涳拷' + time + '濮ｎ偆顬戦妴锟�');
	}
};
Core.register = function(A, E, D) {
	var I = Core.env.modules;
	if (!I[A]) {
		I[A] = {
			versions : [],
			builds : []
		};
	}
	var B = I[A], H = D.version, G = D.build, F = Core.env.listeners;
	B.name = A;
	B.version = H;
	B.build = G;
	B.versions.push(H);
	B.builds.push(G);
	B.mainClass = E;
	for (var C = 0; C < F.length; C += 1) {
		F[C](B);
	}
	if (E) {
		E.VERSION = H;
		E.BUILD = G;
	} else {
		Core.log('mainClass is undefined for module ' + A, 'warn');
	}
};
Core.env = Core.env || {
	modules : [],
	listeners : []
};
Core.env.getVersion = function(A) {
	return Core.env.modules[A] || null;
};
Core.env.ua = function() {
	var C = {
		ie : 0,
		opera : 0,
		gecko : 0,
		webkit : 0,
		mobile : null,
		air : 0
	};
	var B = navigator.userAgent, A;
	if ((/KHTML/).test(B)) {
		C.webkit = 1;
	}
	A = B.match(/AppleWebKit\/([^\s]*)/);
	if (A && A[1]) {
		C.webkit = parseFloat(A[1]);
		if (/ Mobile\//.test(B)) {
			C.mobile = "Apple";
		} else {
			A = B.match(/NokiaN[^\/]*/);
			if (A) {
				C.mobile = A[0];
			}
		}
		A = B.match(/AdobeAIR\/([^\s]*)/);
		if (A) {
			C.air = A[0];
		}
	}
	if (!C.webkit) {
		A = B.match(/Opera[\s\/]([^\s]*)/);
		if (A && A[1]) {
			C.opera = parseFloat(A[1]);
			A = B.match(/Opera Mini[^;]*/);
			if (A) {
				C.mobile = A[0];
			}
		} else {
			A = B.match(/MSIE\s([^;]*)/);
			if (A && A[1]) {
				C.ie = parseFloat(A[1]);
			} else {
				A = B.match(/Gecko\/([^\s]*)/);
				if (A) {
					C.gecko = 1;
					A = B.match(/rv:([^\s\)]*)/);
					if (A && A[1]) {
						C.gecko = parseFloat(A[1]);
					}
				}
			}
		}
	}
	return C;
}();
Core.Interface = function(name, methods) {
	var i, len;
	if (arguments.length != 2) {
		throw new Error('Interface constructor call with ' + arguments.length
				+ ' arguments, but expected exactly 2.');
	}
	this.name = name;
	this.methods = [];
	for (i = 0, len = methods.length; i < len; i += 1) {
		if (typeof methods[i] !== 'string') {
			throw new Error('Interface constructor expects method name to be passed in as a string');
		}
		this.methods.push[methods[i]];
	}
};
Core.Interface.ensureImpements = function(object) {
	var i, j, len, mehtodLen, interFace, method;
	if (arguments.length < 2) {
		throw new Error('Function Interface.ensureImpements called with '
				+ arguments.length + ' arguments, but expects at least 2.');
	}
	for (i = 1, len = arguments.length; i < len; i += 1) {
		interFace = arguments[i];
		if (interFace.constructor !== Interface) {
			throw new Error('Function Interface.ensureImpements expects arguments two and above to be instances of Interface.');
		}
		for (j = 0, methodLen = interFace.mentods.lentgh; j < methodLen; j += 1) {
			method = interFace.method[j];
			if (!object[method] || typeof object[method] !== 'function') {
				throw new Error('Function Interface.ensureImpements: object does not implement the '
						+ interFace.name
						+ ' interFace.method '
						+ method
						+ ' was not found');
			}
		}
	}
};
Core.extend = function(subc, superc, overrides) {
	if (!superc || !subc) {
		throw new Error('extend failed, please check that all dependencies are included.');
	}
	var F = function() {
	};
	F.prototype = superc.prototype;
	subc.prototype = new F();
	subc.prototype.constructor = subc;
	subc.superclass = superc.prototype;
	if (superc.prototype.constructor == Object.prototype.constructor) {
		superc.prototype.constructor = superc;
	}
	if (overrides) {
		for (var i in overrides) {
			subc.prototype[i] = overrides[i];
		}
	}
};
Core.augment = function(r, s) {
	if (!s || !r) {
		throw new Error('augment failed, please check that all dependencies are included.');
	}
	var rp = r.prototype, sp = s.prototype, a = arguments, i, p;
	if (a[2]) {
		for (i = 2; i < a.length; i += 1) {
			rp[a[i]] = sp[a[i]];
		}
	} else {
		for (p in sp) {
			if (!rp[p]) {
				rp[p] = sp[p];
			}
		}
	}
};
Core.clone = function(object, overrides) {
	var F = function() {
	}, destination, p;
	F.prototype = object;
	destination = new F;
	if (overrides) {
		for (p in overrides) {
			destination[p] = overrides[p];
		}
	}
	return destination;
};
Core.lang = {
	inspect : function(object) {
		try {
			if (this.isUndefined(object)) {
				return 'undefined';
			}
			if (object === null) {
				return 'null';
			}
			return object.inspect ? object.inspect() : String(object);
		} catch (e) {
			if (e instanceof RangeError) {
				return '...';
			}
			throw e;
		}
	},
	keys : function(object) {
		var keys = [];
		for (var property in object) {
			keys.push(property);
		}
		return keys;
	},
	values : function(object) {
		var values = [];
		for (var property in object) {
			values.push(object[property]);
		}
		return values;
	},
	extend : function(r, s) {
		if (!s || !r) {
			throw new Error('extend method failed, please check that all dependencies are included.');
		}
		var a = arguments, i, p;
		if (a[2]) {
			if (this.isString(a[2])) {
				r[a[2]] = s[a[2]];
			} else {
				for (i = 0; i < a[2].length; i += 1) {
					r[a[2][i]] = s[a[2][i]];
				}
			}
		} else {
			for (p in s) {
				r[p] = s[p];
			}
		}
		return r;
	},
	clone : function(object) {
		return this.extend({}, object);
	},
	isArray : function(object) {
		if (object && object.constructor
				&& object.constructor.toString().indexOf('Array') > -1) {
			return true;
		} else {
			return Core.lang.isObject(object) && object.constructor == Array;
		}
	},
	isBoolean : function(object) {
		return typeof object == 'boolean';
	},
	isFunction : function(object) {
		return typeof object == 'function';
	},
	isNull : function(object) {
		return object === null;
	},
	isNumber : function(object) {
		return typeof object == 'number' && isFinite(object);
	},
	isObject : function(object) {
		return object
				&& (typeof object == 'object' || Core.lang.isFunction(object));
	},
	isString : function(object) {
		return typeof object == 'string';
	},
	isUndefined : function(object) {
		return typeof object == 'undefined';
	},
	argsType : function(types, args) {
		var len = args.length, i;
		if (types.length != len) {
			throw new Error('Invalid number of arguments Expected '
					+ types.length + ', received ' + args.length + ' instead.');
		} else {
			for (i = 0; i < len; i += 1) {
				if (args[i].constructor != types[i]) {
					throw new Error('Invalid argument type Expected '
							+ types[i].name + ', received '
							+ args[i].constructor.name + ' insted.');
				} else {
					return true;
				}
			}
		}
	},
	hasOwnProperty : function(object, prop) {
		if (Object.prototype.hasOwnProperty) {
			return object.hasOwnProperty(prop);
		}
		return !Core.lang.isUndefined(object[prop])
				&& object.constructor.prototype[prop] !== object[prop];
	},
	isXMLDoc : function(elem) {
		return elem.documentElement && !elem.body || elem.tagName
				&& elem.ownerDocument && !elem.ownerDocument.body;
	},
	formatNumber : function(num, prefix) {
		prefix = prefix || '';
		num += '';
		var splitStr = num.split('.');
		var splitLeft = splitStr[0];
		var splitRight = splitStr.length > 1 ? '.' + splitStr[1] : '';
		var regx = /(\d+)(\d{3})/;
		while (regx.test(splitLeft)) {
			splitLeft = splitLeft.replace(regx, '$1' + ',' + '$2');
		}
		return prefix + splitLeft + splitRight;
	},
	unformatNumber : function(num) {
		return num.replace(/([^0-9\.\-])/g, '') * 1;
	},
	stringBuffer : function() {
		var strArray = [], i, len = arguments.length;
		for (i = 0; i < len; i += 1) {
			strArray.push(arguments[i]);
		}
		return strArray.join('');
	},
	trim : function(D) {
		try {
			return D.replace(/^\s+|\s+$/g, "");
		} catch (E) {
			return D;
		}
	},
	hasMackup : function(str) {
		var markupTags = /<\/?[^>]+>/g;
		return markupTags.test(str);
	},
	stripTags : function(str) {
		return str.replace(/<\/?[^>]+>/gi, '');
	},
	stripScripts : function(str) {
		return str.replace(/<script[^>]*>([\\S\\s]*?)<\/script>/g, '');
	},
	isJSON : function(str) {
		str = str.replace(/\\./g, '@').replace(/"[^"\\\n\r]*"/g, '');
		return (/^[,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]*$/).test(str);
	},
	encodeHTML : function(str) {
		return str.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g,
				'&gt;');
	},
	decodeHTML : function(str) {
		return str.replace(/&amp;/g, '&').replace(/&lt;/g, '<').replace(
				/&gt;/g, '>');
	},
	makeArray : function(array) {
		var ret = [];
		if (array != null) {
			var i = array.length;
			if (i == null || array.split || array.setInterval || array.call) {
				ret[0] = array;
			} else {
				while (i) {
					ret[--i] = array[i];
				}
			}
		}
		return ret;
	},
	inArray : function(elem, array) {
		var i, length = array.length;
		for (i = 0; i < length; i += 1) {
			if (array[i] === elem) {
				return i;
			}
		}
		return -1;
	},
	grep : function(elems, callback, inv) {
		var ret = [], i, length = elems.length;
		for (i = 0; i < length; i += 1) {
			if (!inv != !callback(elems[i], i)) {
				ret.push(elems[i]);
			}
		}
		return ret;
	},
	map : function(elems, callback) {
		var ret = [], i, length = elems.length;
		for (var i = 0; i < length; i += 1) {
			var value = callback(elems[i], i);
			if (value != null) {
				ret.push(value);
			}
		}
		return ret.concat.apply([], ret);
	},
	each : function(object, callback, args) {
		var name, i = 0, length = object.length;
		if (args) {
			if (length == undefined) {
				for (name in object) {
					if (callback.apply(object[name], args) === false) {
						break;
					}
				}
			} else {
				for (; i < length;) {
					if (callback.apply(object[i++], args) === false) {
						break;
					}
				}
			}
		} else {
			if (length == undefined) {
				for (name in object) {
					if (callback.call(object[name], name, object[name]) === false) {
						break;
					}
				}
			} else {
				for (var value = object[0]; i < length
						&& callback.call(value, i, value) !== false; value = object[++i]) {
				}
			}
		}
		return object;
	}
};
Core.Cookie = {
	_createCookieString : function(name, value, encodeValue, options) {
		var lang = Core.lang;
		var text = encodeURIComponent(name) + "="
				+ (encodeValue ? encodeURIComponent(value) : value);
		if (lang.isObject(options)) {
			if (options.expires instanceof Date) {
				text += "; expires=" + options.expires.toGMTString();
			}
			if (lang.isString(options.path) && options.path != "") {
				text += "; path=" + options.path;
			}
			if (lang.isString(options.domain) && options.domain != "") {
				text += "; domain=" + options.domain;
			}
			if (options.secure === true) {
				text += "; secure";
			}
		}
		return text;
	},
	_createCookieHashString : function(hash) {
		var lang = Core.lang;
		if (!lang.isObject(hash)) {
			throw new TypeError("Cookie._createCookieHashString(): Argument must be an object.");
		}
		var text = new Array();
		for (var key in hash) {
			if (lang.hasOwnProperty(hash, key) && !lang.isFunction(hash[key])
					&& !lang.isUndefined(hash[key])) {
				text.push(encodeURIComponent(key) + "="
						+ encodeURIComponent(String(hash[key])));
			}
		}
		return text.join("&");
	},
	_parseCookieHash : function(text) {
		var hashParts = text.split("&"), hashPart = null, hash = new Object();
		if (text.length > 0) {
			for (var i = 0, len = hashParts.length; i < len; i++) {
				hashPart = hashParts[i].split("=");
				hash[decodeURIComponent(hashPart[0])] = decodeURIComponent(hashPart[1]);
			}
		}
		return hash;
	},
	_parseCookieString : function(text, decode) {
		var cookies = new Object();
		if (Core.lang.isString(text) && text.length > 0) {
			var decodeValue = (decode === false ? function(s) {
				return s;
			} : decodeURIComponent);
			if (/[^=]+=[^=;]?(?:; [^=]+=[^=]?)?/.test(text)) {
				var cookieParts = text.split(/;\s/g);
				var cookieName = null;
				var cookieValue = null;
				var cookieNameValue = null;
				for (var i = 0, len = cookieParts.length; i < len; i++) {
					cookieNameValue = cookieParts[i].match(/([^=]+)=/i);
					if (cookieNameValue instanceof Array) {
						cookieName = decodeURIComponent(cookieNameValue[1]);
						cookieValue = decodeValue(cookieParts[i]
								.substring(cookieNameValue[1].length + 1));
					} else {
						cookieName = decodeURIComponent(cookieParts[i]);
						cookieValue = cookieName;
					}
					cookies[cookieName] = cookieValue;
				}
			}
		}
		return cookies;
	},
	get : function(name, converter) {
		var lang = Core.lang;
		var cookies = this._parseCookieString(document.cookie);
		if (!lang.isString(name) || name === "") {
			throw new TypeError("Cookie.get(): Cookie name must be a non-empty string.");
		}
		if (lang.isUndefined(cookies[name])) {
			return null;
		}
		if (!lang.isFunction(converter)) {
			return cookies[name];
		} else {
			return converter(cookies[name]);
		}
	},
	getSub : function(name, subName, converter) {
		var lang = Core.lang;
		var hash = this.getSubs(name);
		if (hash !== null) {
			if (!lang.isString(subName) || subName === "") {
				throw new TypeError("Cookie.getSub(): Subcookie name must be a non-empty string.");
			}
			if (lang.isUndefined(hash[subName])) {
				return null;
			}
			if (!lang.isFunction(converter)) {
				return hash[subName];
			} else {
				return converter(hash[subName]);
			}
		} else {
			return null;
		}
	},
	getSubs : function(name) {
		if (!Core.lang.isString(name) || name === "") {
			throw new TypeError("Cookie.getSubs(): Cookie name must be a non-empty string.");
		}
		var cookies = this._parseCookieString(document.cookie, false);
		if (Core.lang.isString(cookies[name])) {
			return this._parseCookieHash(cookies[name]);
		}
		return null;
	},
	remove : function(name, options) {
		if (!Core.lang.isString(name) || name === "") {
			throw new TypeError("Cookie.remove(): Cookie name must be a non-empty string.");
		}
		options = options || {};
		options.expires = new Date(0);
		return this.set(name, "", options);
	},
	removeSub : function(name, subName, options) {
		if (!Core.lang.isString(name) || name === "") {
			throw new TypeError("Cookie.removeSub(): Cookie name must be a non-empty string.");
		}
		if (!Core.lang.isString(subName) || subName === "") {
			throw new TypeError("Cookie.removeSub(): Subcookie name must be a non-empty string.");
		}
		var subs = this.getSubs(name);
		if (Core.lang.isObject(subs) && Core.lang.hasOwnProperty(subs, subName)) {
			delete subs[subName];
			return this.setSubs(name, subs, options);
		} else {
			return "";
		}
	},
	set : function(name, value, options) {
		var lang = Core.lang;
		if (!lang.isString(name)) {
			throw new TypeError("Cookie.set(): Cookie name must be a string.");
		}
		if (lang.isUndefined(value)) {
			throw new TypeError("Cookie.set(): Value cannot be undefined.");
		}
		var text = this._createCookieString(name, value, true, options);
		document.cookie = text;
		return text;
	},
	setSub : function(name, subName, value, options) {
		var lang = Core.lang;
		if (!lang.isString(name) || name === "") {
			throw new TypeError("Cookie.setSub(): Cookie name must be a non-empty string.");
		}
		if (!lang.isString(subName) || subName === "") {
			throw new TypeError("Cookie.setSub(): Subcookie name must be a non-empty string.");
		}
		if (lang.isUndefined(value)) {
			throw new TypeError("Cookie.setSub(): Subcookie value cannot be undefined.");
		}
		var hash = this.getSubs(name);
		if (!lang.isObject(hash)) {
			hash = new Object();
		}
		hash[subName] = value;
		return this.setSubs(name, hash, options);
	},
	setSubs : function(name, value, options) {
		var lang = Core.lang;
		if (!lang.isString(name)) {
			throw new TypeError("Cookie.setSubs(): Cookie name must be a string.");
		}
		if (!lang.isObject(value)) {
			throw new TypeError("Cookie.setSubs(): Cookie value must be an object.");
		}
		var text = this._createCookieString(name, this
						._createCookieHashString(value), false, options);
		document.cookie = text;
		return text;
	}
};
(function() {
	var isIE = Core.env.ua.ie;
	var isOpera = Core.env.ua.opera;
	var getStyle;
	var setStyle;
	var id_counter = 0;
	var propertyCache = {};
	var patterns = {
		HYPHEN : /(-[a-z])/i,
		ROOT_TAG : /body|html/i
	};
	var toCamel = function(property) {
		if (!patterns.HYPHEN.test(property)) {
			return property;
		}
		if (propertyCache[property]) {
			return propertyCache[property];
		}
		var converted = property;
		while (patterns.HYPHEN.exec(converted)) {
			converted = converted.replace(RegExp.$1, RegExp.$1.substr(1)
							.toUpperCase());
		}
		propertyCache[property] = converted;
		return converted;
	};
	Core.lang.toCamel = toCamel;
	Core.namespace('widget', 'Effects');
	if (document.defaultView && document.defaultView.getComputedStyle) {
		getStyle = function(el, property) {
			var value = null;
			if (property == 'float') {
				property = 'cssFloat';
			}
			var computed = document.defaultView.getComputedStyle(el, '');
			if (computed) {
				value = computed[toCamel(property)];
			}
			return el.style[property] || value;
		};
	} else {
		if (document.documentElement.currentStyle && isIE) {
			getStyle = function(el, property) {
				switch (toCamel(property)) {
					case 'opacity' :
						var val = 100;
						try {
							val = el.filters['DXImageTransform.Microsoft.Alpha'].opacity;
						} catch (e) {
							try {
								val = el.filters('alpha').opacity;
							} catch (e) {
							}
						}
						return val / 100;
						break;
					case 'float' :
						property = 'styleFloat';
					default :
						var value = el.currentStyle
								? el.currentStyle[property]
								: null;
						return (el.style[property] || value);
				}
			};
		} else {
			getStyle = function(el, property) {
				return el.style[property];
			};
		}
	}
	if (isIE) {
		setStyle = function(el, property, val) {
			switch (property) {
				case 'opacity' :
					if (Core.lang.isString(el.style.filter)) {
						el.style.filter = 'alpha(opacity=' + val * 100 + ')';
						if (!el.currentStyle || !el.currentStyle.hasLayout) {
							el.style.zoom = 1;
						}
					}
					break;
				case 'float' :
					property = 'styleFloat';
				default :
					el.style[property] = val;
			}
		};
	} else {
		setStyle = function(el, property, val) {
			if (property == 'float') {
				property = 'cssFloat';
			}
			el.style[property] = val;
		};
	}
	Core.Dom = {
		get : function(el) {
			if (Core.lang.isString(el)) {
				return document.getElementById(el);
			}
			if (Core.lang.isArray(el)) {
				var c = [];
				for (var i = 0, len = el.length; i < len; i += 1) {
					c[c.length] = Core.Dom.get(el[i]);
				}
				return c;
			}
			if (el) {
				return el;
			}
			return null;
		},
		getByClassName : function(className, tag, parent) {
			parent = parent || document;
			if (!(parent = Core.Dom.get(parent))) {
				return false;
			}
			var allTags = (tag == "*" && parent.all) ? parent.all : document
					.getElementsByTagName(tag);
			var matchingElements = new Array(), i;
			className = className.replace(/\-/g, "\\-");
			var regex = new RegExp("(^|\\s)" + className + "(\\s|$)");
			for (i = 0; i < allTags.length; i += 1) {
				element = allTags[i];
				if (regex.test(element.className)) {
					matchingElements.push(element);
				}
			}
			return matchingElements;
		},
		getElementsByClassName : function(className, tag, root) {
			var method = function(el) {
				return Core.Dom.hasClass(el, className);
			};
			return Core.Dom.getElementsBy(method, tag, root);
		},
		hasClass : function(el, className) {
			var re = new RegExp('(?:^|\\s+)' + className + '(?:\\s+|$)');
			var f = function(el) {
				return re.test(el.className);
			};
			return Core.Dom.batch(el, f, Core.Dom, true);
		},
		addClass : function(el, className) {
			var f = function(el) {
				if (this.hasClass(el, className)) {
					return;
				}
				el.className = [el.className, className].join(' ');
			};
			Core.Dom.batch(el, f, Core.Dom, true);
		},
		removeClass : function(el, className) {
			var re = new RegExp('(?:^|\\s+)' + className + '(?:\\s+|$)', 'g');
			var f = function(el) {
				if (!this.hasClass(el, className)) {
					return;
				}
				var c = el.className;
				el.className = c.replace(re, ' ');
				if (this.hasClass(el, className)) {
					this.removeClass(el, className);
				}
			};
			Core.Dom.batch(el, f, Core.Dom, true);
		},
		replaceClass : function(el, oldClassName, newClassName) {
			if (oldClassName === newClassName) {
				return false;
			}
			var re = new RegExp('(?:^|\\s+)' + oldClassName + '(?:\\s+|$)', 'g');
			var f = function(el) {
				if (!this.hasClass(el, oldClassName)) {
					this.addClass(el, newClassName);
					return;
				}
				el.className = el.className.replace(re, ' ' + newClassName
								+ ' ');
				if (this.hasClass(el, oldClassName)) {
					this.replaceClass(el, oldClassName, newClassName);
				}
			};
			Core.Dom.batch(el, f, Core.Dom, true);
		},
		addClassByTagName : function(tag, className, parent) {
			var elements = parent.getElementsByTagName(tag), i;
			for (i = 0; i < elements.length; i += 1) {
				this.addClass(elemens[i], className)
			}
		},
		getStyle : function(el, property) {
			property = toCamel(property);
			var f = function(element) {
				return getStyle(element, property);
			};
			return Core.Dom.batch(el, f, Core.Dom, true);
		},
		setStyle : function(el, property, val) {
			property = toCamel(property);
			var f = function(element) {
				setStyle(element, property, val);
			};
			Core.Dom.batch(el, f, Core.Dom, true);
		},
		setStyles : function(elem, styles) {
			for (var property in styles) {
				if (!styles.hasOwnProperty(property)) {
					continue;
				}
				Core.Dom.setStyle(elem, property, styles[property]);
			}
		},
		getDocumentWidth : function() {
			var scrollWidth = Core.Dom.getScrollWidth();
			var w = Math.max(scrollWidth, Core.Dom.getViewportWidth());
			return w;
		},
		getDocumentHeight : function() {
			var scrollHeight = Core.Dom.getScrollHeight();
			var h = Math.max(scrollHeight, Core.Dom.getViewportHeight());
			return h;
		},
		getScrollWidth : function() {
			var scrollWidth = (document.compatMode == 'CSS1Compat')
					? document.body.scrollWidth
					: document.documentElement.scrollWidth;
			return scrollWidth;
		},
		getScrollHeight : function() {
			var scrollHeight = (document.compatMode == 'CSS1Compat')
					? document.body.scrollHeight
					: document.documentElement.scrollHeight;
			return scrollHeight;
		},
		getXScroll : function() {
			var xScroll = self.pageXOffset
					|| document.documentElement.scrollLeft
					|| document.body.scrollLeft;
			return xScroll;
		},
		getYScroll : function() {
			var yScroll = self.pageYOffset
					|| document.documentElement.scrollTop
					|| document.body.scrollTop;
			return yScroll;
		},
		getViewportWidth : function() {
			var width = self.innerWidth;
			var mode = document.compatMode;
			if (mode || isIE) {
				width = (mode == 'CSS1Compat')
						? document.documentElement.clientWidth
						: document.body.clientWidth;
			}
			return width;
		},
		getViewportHeight : function() {
			var height = self.innerHeight;
			var mode = document.compatMode;
			if ((mode || isIE) && !isOpera) {
				height = (mode == 'CSS1Compat')
						? document.documentElement.clientHeight
						: document.body.clientHeight;
			}
			return height;
		},
		getRegion : function(el) {
			var f = function(el) {
				var region = new Core.Region.getRegion(el);
				return region;
			};
			return Core.Dom.batch(el, f, Core.Dom, true);
		},
		removeChildren : function(parent) {
			if (!(prent = Core.Dom.get(parent))) {
				return false;
			}
			while (parent.firstChild) {
				parent.firstChild.parentNode.removeChild(parent.firstChild);
			}
			return parent;
		},
		prependChild : function(parent, newChild) {
			if (!(parent = Core.Dom.get(parent))
					|| !(newChild = Core.Dom.get(newChild))) {
				return false;
			}
			if (parent.firstChild) {
				parent.insertBefore(newChild, parent.firstChild);
			} else {
				parent.appendChild(newChild);
			}
			return parent;
		},
		insertAfter : function(newElement, targetElement) {
			var parent = targetElement.parentNode;
			if (parent.lastChild == targetElement) {
				parent.appendChild(newElement);
			} else {
				parent.insertBefore(newElement, targetElement.nextSibling);
			}
		},
		getXY : function(el) {
			var f = function(el) {
				if ((el.parentNode === null || el.offsetParent === null || this
						.getStyle(el, 'display') == 'none')
						&& el != document.body) {
					return false;
				}
				var parentNode = null;
				var pos = [];
				var box;
				if (el.getBoundingClientRect) {
					box = el.getBoundingClientRect();
					var doc = document;
					if (!this.inDocument(el) && parent.document != document) {
						doc = parent.document;
						if (!this.isAncestor(doc.documentElement, el)) {
							return false;
						}
					}
					var scrollTop = Math.max(doc.documentElement.scrollTop,
							doc.body.scrollTop);
					var scrollLeft = Math.max(doc.documentElement.scrollLeft,
							doc.body.scrollLeft);
					return [box.left + scrollLeft, box.top + scrollTop];
				} else {
					pos = [el.offsetLeft, el.offsetTop];
					parentNode = el.offsetParent;
					var hasAbs = this.getStyle(el, 'position') == 'absolute';
					if (parentNode != el) {
						while (parentNode) {
							pos[0] += parentNode.offsetLeft;
							pos[1] += parentNode.offsetTop;
							if (isSafari
									&& !hasAbs
									&& this.getStyle(parentNode, 'position') == 'absolute') {
								hasAbs = true;
							}
							parentNode = parentNode.offsetParent;
						}
					}
					if (isSafari && hasAbs) {
						pos[0] -= document.body.offsetLeft;
						pos[1] -= document.body.offsetTop;
					}
				}
				parentNode = el.parentNode;
				while (parentNode.tagName
						&& !patterns.ROOT_TAG.test(parentNode.tagName)) {
					if (Core.Dom.getStyle(parentNode, 'display') != 'inline') {
						pos[0] -= parentNode.scrollLeft;
						pos[1] -= parentNode.scrollTop;
					}
					parentNode = parentNode.parentNode;
				}
				return pos;
			};
			return Core.Dom.batch(el, f, Core.Dom, true);
		},
		getX : function(el) {
			var f = function(el) {
				return Core.Dom.getXY(el)[0];
			};
			return Core.Dom.batch(el, f, Core.Dom, true);
		},
		getY : function(el) {
			var f = function(el) {
				return Core.Dom.getXY(el)[1];
			};
			return Core.Dom.batch(el, f, Core.Dom, true);
		},
		setXY : function(el, pos, noRetry) {
			var f = function(el) {
				var style_pos = this.getStyle(el, 'position');
				if (style_pos == 'static') {
					this.setStyle(el, 'position', 'relative');
					style_pos = 'relative';
				}
				var pageXY = this.getXY(el);
				if (pageXY === false) {
					return false;
				}
				var delta = [parseInt(this.getStyle(el, 'left'), 10),
						parseInt(this.getStyle(el, 'top'), 10)];
				if (isNaN(delta[0])) {
					delta[0] = (style_pos == 'relative') ? 0 : el.offsetLeft;
				}
				if (isNaN(delta[1])) {
					delta[1] = (style_pos == 'relative') ? 0 : el.offsetTop;
				}
				if (pos[0] !== null) {
					el.style.left = pos[0] - pageXY[0] + delta[0] + 'px';
				}
				if (pos[1] !== null) {
					el.style.top = pos[1] - pageXY[1] + delta[1] + 'px';
				}
				if (!noRetry) {
					var newXY = this.getXY(el);
					if ((pos[0] !== null && newXY[0] != pos[0])
							|| (pos[1] !== null && newXY[1] != pos[1])) {
						this.setXY(el, pos, true);
					}
				}
			};
			Core.Dom.batch(el, f, Core.Dom, true);
		},
		setX : function(el, x) {
			Core.Dom.setXY(el, [x, null]);
		},
		setY : function(el, y) {
			Core.Dom.setXY(el, [null, y]);
		},
		generateId : function(el, prefix) {
			prefix = prefix || 'Core-gen';
			el = el || {};
			var f = function(el) {
				if (el) {
					el = Core.Dom.get(el);
				} else {
					el = {};
				}
				if (!el.id) {
					el.id = prefix + id_counter++;
				}
				return el.id;
			};
			return Core.Dom.batch(el, f, Core.Dom, true);
		},
		isAncestor : function(haystack, needle) {
			haystack = Core.Dom.get(haystack);
			if (!haystack || !needle) {
				return false;
			}
			var f = function(needle) {
				if (haystack.contains && !isSafari) {
					return haystack.contains(needle);
				} else {
					if (haystack.compareDocumentPosition) {
						return !!(haystack.compareDocumentPosition(needle) & 16);
					} else {
						var parent = needle.parentNode;
						while (parent) {
							if (parent == haystack) {
								return true;
							} else {
								if (!parent.tagName
										|| parent.tagName.toUpperCase() == 'HTML') {
									return false;
								}
								parent = parent.parentNode;
							}
						}
						return false;
					}
				}
			};
			return Core.Dom.batch(needle, f, Core.Dom, true);
		},
		inDocument : function(el) {
			var f = function(el) {
				return this.isAncestor(document.documentElement, el);
			};
			return Core.Dom.batch(el, f, Core.Dom, true);
		},
		getElementsBy : function(method, tag, root) {
			tag = tag || '*';
			var nodes = [], i;
			if (root) {
				root = Core.Dom.get(root);
				if (!root) {
					return nodes;
				}
			} else {
				root = document;
			}
			var elements = root.getElementsByTagName(tag);
			if (!elements.length && (tag == '*' && root.all)) {
				elements = root.all;
			}
			for (i = 0, len = elements.length; i < len; i += 1) {
				if (method(elements[i])) {
					nodes.push(elements[i]);
				}
			}
			return nodes;
		},
		batch : function(el, method, o, override) {
			var id = el, i;
			el = Core.Dom.get(el);
			var scope = (override) ? o : window;
			if (!el || el.tagName || !el.length) {
				if (!el) {
					return false;
				}
				return method.call(scope, el, o);
			}
			var collection = [];
			for (i = 0, len = el.length; i < len; i += 1) {
				if (!el[i]) {
					id = el[i];
				}
				collection.push(method.call(scope, el[i], o));
			}
			return collection;
		}
	}
})();
Core.Region = function(t, r, b, l) {
	this.top = t;
	this[1] = t;
	this.right = r;
	this.bottom = b;
	this.left = l;
	this[0] = l;
};
Core.Region.prototype.contains = function(region) {
	return (region.left >= this.left && region.right <= this.right
			&& region.top >= this.top && region.bottom <= this.bottom);
};
Core.Region.prototype.getArea = function() {
	return ((this.bottom - this.top) * (this.right - this.left));
};
Core.Region.prototype.intersect = function(region) {
	var t = Math.max(this.top, region.top);
	var r = Math.min(this.right, region.right);
	var b = Math.min(this.bottom, region.bottom);
	var l = Math.max(this.left, region.left);
	if (b >= t && r >= l) {
		return new Core.Region(t, r, b, l);
	} else {
		return null;
	}
};
Core.Region.prototype.union = function(region) {
	var t = Math.min(this.top, region.top);
	var r = Math.max(this.right, region.right);
	var b = Math.max(this.bottom, region.bottom);
	var l = Math.min(this.left, region.left);
	return new Core.Region(t, r, b, l);
};
Core.Region.prototype.toString = function() {
	return ("Region {" + "top: " + this.top + ", right: " + this.right
			+ ", bottom: " + this.bottom + ", left: " + this.left + "}");
};
Core.Region.getRegion = function(el) {
	var p = Core.Dom.getXY(el);
	var t = p[1];
	var r = p[0] + el.offsetWidth;
	var b = p[1] + el.offsetHeight;
	var l = p[0];
	return new Core.Region(t, r, b, l);
};
Core.Point = function(x, y) {
	if (x instanceof Array) {
		y = x[1];
		x = x[0];
	}
	this.x = this.right = this.left = this[0] = x;
	this.y = this.top = this.bottom = this[1] = y;
};
Core.Point.prototype = new Core.Region();
Core.Builder = (function() {
	var nidx = 0;
	var NODEMAP = {
		AREA : 'map',
		CAPTION : 'table',
		COL : 'table',
		COLGROUP : 'table',
		LEGEND : 'fieldset',
		OPTGROUP : 'select',
		OPTION : 'select',
		PARAM : 'object',
		TBODY : 'table',
		TD : 'table',
		TFOOT : 'table',
		TH : 'table',
		THEAD : 'table',
		TR : 'table'
	};
	var ATTR_MAP = {
		'className' : 'class',
		'htmlFor' : 'for',
		'readOnly' : 'readonly',
		'maxLength' : 'maxlength',
		'cellSpacing' : 'cellspacing'
	};
	var emptyTags = /^(?:br|frame|hr|img|input|link|meta|range|spacer|wbr|area|param|col)$/i;
	var isUndefined = function(object) {
		return typeof object == 'undefined';
	};
	var isFunction = function(object) {
		return typeof object == 'function';
	};
	var isObject = function(object) {
		return object && (typeof object == 'object' || isFunction(object));
	};
	var isString = function(object) {
		return typeof object == 'string';
	};
	var isArray = function(object) {
		if (object && object.constructor
				&& object.constructor.toString().indexOf('Array') > -1) {
			return true;
		} else {
			return isObject(object) && object.constructor == Array;
		}
	};
	var hasOwnProperty = function(object, prop) {
		if (Object.prototype.hasOwnProperty) {
			return object.hasOwnProperty(prop);
		}
		return !isUndefined(object[prop])
				&& object.constructor.prototype[prop] !== object[prop];
	};
	var hasMackup = function(str) {
		var markupTags = /<\/?[^>]+>/g;
		return markupTags.test(str);
	};
	var createHtml = function(o) {
		if (typeof o == 'string') {
			return o;
		}
		var b = "";
		if (isArray(o)) {
			for (var i = 0, l = o.length; i < l; i++) {
				b += createHtml(o[i]);
			}
			return b;
		}
		if (!o.tag) {
			o.tag = "div";
		}
		b += "<" + o.tag;
		for (var attr in o) {
			if (attr == "tag" || attr == "children" || attr == "cn"
					|| attr == "html" || typeof o[attr] == "function") {
				continue;
			}
			if (attr == "style") {
				var s = o["style"];
				if (typeof s == "function") {
					s = s.call();
				}
				if (typeof s == "string") {
					b += ' style="' + s + '"';
				} else {
					if (typeof s == "object") {
						b += ' style="';
						for (var key in s) {
							if (typeof s[key] != "function") {
								b += key + ":" + s[key] + ";";
							}
						}
						b += '"';
					}
				}
			} else {
				b += attr in ATTR_MAP ? (b += ' ' + ATTR_MAP[attr] + '="'
						+ o[attr] + '"') : (" " + attr + '="' + o[attr] + '"');
			}
		}
		if (emptyTags.test(o.tag)) {
			b += "/>";
		} else {
			b += ">";
			var cn = o.children || o.cn;
			if (cn) {
				b += createHtml(cn);
			} else {
				if (o.html) {
					b += o.html;
				}
			}
			b += "</" + o.tag + ">";
		}
		return b;
	};
	var textNode = function(str) {
		return document.createTextNode(str);
	};
	return {
		linkNode : function(url, cssId, charset) {
			var c = charset || 'utf-8', link = null;
			var head = document.getElementsByTagName('head')[0];
			link = this.Node('link', {
						'id' : cssId || ('link-' + (nidx++)),
						'type' : 'text/css',
						'charset' : c,
						'rel' : 'stylesheet',
						'href' : url
					});
			head.appendChild(link);
			return link;
		},
		scriptNode : function(url, scriptId, win, charset) {
			var d = win || document.body;
			var c = charset || 'utf-8';
			return d.appendChild(this.Node('script', {
						'id' : scriptId || ('script-' + (nidx++)),
						'type' : 'text/javascript',
						'charset' : c,
						'src' : url
					}));
		},
		markup : function(o) {
			return createHtml(o);
		},
		Node : function(tag, attr, children) {
			tag = tag.toUpperCase();
			var parentTag = NODEMAP[tag] || 'div';
			var parentElement = document.createElement(parentTag);
			var elem = null;
			try {
				parentElement.innerHTML = "<" + tag + "></" + tag + ">";
			} catch (e) {
			}
			elem = parentElement.firstChild;
			if (elem && (elem.tagName.toUpperCase() != tag)) {
				elem = elem.getElementsByTagName(tag)[0];
			}
			if (!elem) {
				if (isString(tag)) {
					elem = document.createElement(tag);
				}
			}
			if (!elem) {
				return;
			} else {
				if (attr) {
					this.Attributes(elem, attr);
				}
				if (children) {
					this.Child(elem, children);
				}
				return elem;
			}
		},
		Attributes : function(elem, attr) {
			var attrName = '', i;
			for (i in attr) {
				if (attr[i] && hasOwnProperty(attr, i)) {
					attrName = i in ATTR_MAP ? ATTR_MAP[i] : i;
					if (attrName == 'class') {
						elem.className = attr[i];
					} else {
						elem.setAttribute(attrName, attr[i]);
					}
				}
			}
			return elem;
		},
		Child : function(parent, child) {
			if (child.tagName) {
				parent.appendChild(child);
				return false;
			}
			if (isArray(child)) {
				var i, length = child.length;
				for (i = 0; i < length; i += 1) {
					if (child[i].tagName) {
						parent.appendChild(child[i]);
					} else {
						if (isString(child[i])) {
							if (hasMackup(child[i])) {
								parent.innerHTML = child[i];
							} else {
								parent.appendChild(textNode(child));
							}
						}
					}
				}
			} else {
				if (isString(child)) {
					if (hasMackup(child)) {
						parent.innerHTML = child;
					} else {
						parent.appendChild(textNode(child));
					}
				}
			}
		}
	}
})();
Core.CustomEvent = function(type, oScope, silent, signature) {
	this.type = type;
	this.scope = oScope || window;
	this.silent = silent;
	this.signature = signature || Core.CustomEvent.LIST;
	this.subscribers = [];
	if (!this.silent) {
	}
	var onsubscribeType = "_CoreCEOnSubscribe";
	if (type !== onsubscribeType) {
		this.subscribeEvent = new Core.CustomEvent(onsubscribeType, this, true);
	}
};
Core.CustomEvent.LIST = 0;
Core.CustomEvent.FLAT = 1;
Core.CustomEvent.prototype = {
	subscribe : function(fn, obj, override) {
		if (!fn) {
			throw new Error("Invalid callback for subscriber to '" + this.type
					+ "'");
		}
		if (this.subscribeEvent) {
			this.subscribeEvent.fire(fn, obj, override);
		}
		this.subscribers.push(new Core.Subscriber(fn, obj, override));
	},
	unsubscribe : function(fn, obj) {
		if (!fn) {
			return this.unsubscribeAll();
		}
		var found = false;
		for (var i = 0, len = this.subscribers.length; i < len; ++i) {
			var s = this.subscribers[i];
			if (s && s.contains(fn, obj)) {
				this._delete(i);
				found = true;
			}
		}
		return found;
	},
	fire : function() {
		var len = this.subscribers.length;
		if (!len && this.silent) {
			return true;
		}
		var args = [], ret = true, i;
		for (i = 0; i < arguments.length; ++i) {
			args.push(arguments[i]);
		}
		var argslength = args.length;
		if (!this.silent) {
		}
		for (i = 0; i < len; ++i) {
			var s = this.subscribers[i];
			if (s) {
				if (!this.silent) {
				}
				var scope = s.getScope(this.scope);
				if (this.signature == Core.CustomEvent.FLAT) {
					var param = null;
					if (args.length > 0) {
						param = args[0];
					}
					ret = s.fn.call(scope, param, s.obj);
				} else {
					ret = s.fn.call(scope, this.type, args, s.obj);
				}
				if (false === ret) {
					if (!this.silent) {
					}
					return false;
				}
			}
		}
		return true;
	},
	unsubscribeAll : function() {
		for (var i = 0, len = this.subscribers.length; i < len; ++i) {
			this._delete(len - 1 - i);
		}
		return i;
	},
	_delete : function(index) {
		var s = this.subscribers[index];
		if (s) {
			delete s.fn;
			delete s.obj;
		}
		this.subscribers.splice(index, 1);
	},
	toString : function() {
		return "CustomEvent: " + "'" + this.type + "', " + "scope: "
				+ this.scope;
	}
};
Core.Subscriber = function(fn, obj, override) {
	this.fn = fn;
	this.obj = obj || null;
	this.override = override;
};
Core.Subscriber.prototype.getScope = function(defaultScope) {
	if (this.override) {
		if (this.override === true) {
			return this.obj;
		} else {
			return this.override;
		}
	}
	return defaultScope;
};
Core.Subscriber.prototype.contains = function(fn, obj) {
	if (obj) {
		return (this.fn == fn && this.obj == obj);
	} else {
		return (this.fn == fn);
	}
};
Core.Subscriber.prototype.toString = function() {
	return "Subscriber { obj: " + (this.obj || "") + ", override: "
			+ (this.override || "no") + " }";
};
Core.Event = function() {
	var loadComplete = false;
	var DOMReady = false;
	var listeners = [];
	var unloadListeners = [];
	var legacyEvents = [];
	var legacyHandlers = [];
	var retryCount = 0;
	var onAvailStack = [];
	var legacyMap = [];
	var counter = 0;
	var lastError = null;
	return {
		POLL_RETRYS : 200,
		POLL_INTERVAL : 10,
		EL : 0,
		TYPE : 1,
		FN : 2,
		WFN : 3,
		OBJ : 3,
		ADJ_SCOPE : 4,
		isSafari : (/KHTML/gi).test(navigator.userAgent),
		webkit : function() {
			var v = navigator.userAgent.match(/AppleWebKit\/([^ ]*)/);
			if (v && v[1]) {
				return v[1];
			}
			return null;
		}(),
		isIE : (!this.webkit && !navigator.userAgent.match(/opera/gi) && navigator.userAgent
				.match(/msie/gi)),
		_interval : null,
		startInterval : function() {
			if (!this._interval) {
				var self = this;
				var callback = function() {
					self._tryPreloadAttach();
				};
				this._interval = setInterval(callback, this.POLL_INTERVAL);
			}
		},
		onAvailable : function(p_id, p_fn, p_obj, p_override) {
			onAvailStack.push({
						id : p_id,
						fn : p_fn,
						obj : p_obj,
						override : p_override,
						checkReady : false
					});
			retryCount = this.POLL_RETRYS;
			this.startInterval();
		},
		onDOMReady : function(p_fn, p_obj, p_override) {
			this.DOMReadyEvent.subscribe(p_fn, p_obj, p_override);
		},
		onContentReady : function(p_id, p_fn, p_obj, p_override) {
			onAvailStack.push({
						id : p_id,
						fn : p_fn,
						obj : p_obj,
						override : p_override,
						checkReady : true
					});
			retryCount = this.POLL_RETRYS;
			this.startInterval();
		},
		addListener : function(el, sType, fn, obj, override) {
			if (!fn || !fn.call) {
				return false;
			}
			if (this._isValidCollection(el)) {
				var ok = true;
				for (var i = 0, len = el.length; i < len; ++i) {
					ok = this.on(el[i], sType, fn, obj, override) && ok;
				}
				return ok;
			} else {
				if (typeof el == "string") {
					var oEl = this.getEl(el);
					if (oEl) {
						el = oEl;
					} else {
						this.onAvailable(el, function() {
									Core.Event.on(el, sType, fn, obj, override);
								});
						return true;
					}
				}
			}
			if (!el) {
				return false;
			}
			if ("unload" == sType && obj !== this) {
				unloadListeners[unloadListeners.length] = [el, sType, fn, obj,
						override];
				return true;
			}
			var scope = el;
			if (override) {
				if (override === true) {
					scope = obj;
				} else {
					scope = override;
				}
			}
			var wrappedFn = function(e) {
				return fn.call(scope, Core.Event.getEvent(e), obj);
			};
			var li = [el, sType, fn, wrappedFn, scope];
			var index = listeners.length;
			listeners[index] = li;
			if (this.useLegacyEvent(el, sType)) {
				var legacyIndex = this.getLegacyIndex(el, sType);
				if (legacyIndex == -1 || el != legacyEvents[legacyIndex][0]) {
					legacyIndex = legacyEvents.length;
					legacyMap[el.id + sType] = legacyIndex;
					legacyEvents[legacyIndex] = [el, sType, el["on" + sType]];
					legacyHandlers[legacyIndex] = [];
					el["on" + sType] = function(e) {
						Core.Event.fireLegacyEvent(Core.Event.getEvent(e),
								legacyIndex);
					};
				}
				legacyHandlers[legacyIndex].push(li);
			} else {
				try {
					this._simpleAdd(el, sType, wrappedFn, false);
				} catch (ex) {
					this.lastError = ex;
					this.removeListener(el, sType, fn);
					return false;
				}
			}
			return true;
		},
		fireLegacyEvent : function(e, legacyIndex) {
			var ok = true, le, lh, li, scope, ret;
			lh = legacyHandlers[legacyIndex];
			for (var i = 0, len = lh.length; i < len; ++i) {
				li = lh[i];
				if (li && li[this.WFN]) {
					scope = li[this.ADJ_SCOPE];
					ret = li[this.WFN].call(scope, e);
					ok = (ok && ret);
				}
			}
			le = legacyEvents[legacyIndex];
			if (le && le[2]) {
				le[2](e);
			}
			return ok;
		},
		getLegacyIndex : function(el, sType) {
			var key = this.generateId(el) + sType;
			if (typeof legacyMap[key] == "undefined") {
				return -1;
			} else {
				return legacyMap[key];
			}
		},
		useLegacyEvent : function(el, sType) {
			if (this.webkit && ("click" == sType || "dblclick" == sType)) {
				var v = parseInt(this.webkit, 10);
				if (!isNaN(v) && v < 418) {
					return true;
				}
			}
			return false;
		},
		removeListener : function(el, sType, fn) {
			var i, len;
			if (typeof el == "string") {
				el = this.getEl(el);
			} else {
				if (this._isValidCollection(el)) {
					var ok = true;
					for (i = 0, len = el.length; i < len; ++i) {
						ok = (this.removeListener(el[i], sType, fn) && ok);
					}
					return ok;
				}
			}
			if (!fn || !fn.call) {
				return this.purgeElement(el, false, sType);
			}
			if ("unload" == sType) {
				for (i = 0, len = unloadListeners.length; i < len; i++) {
					var li = unloadListeners[i];
					if (li && li[0] == el && li[1] == sType && li[2] == fn) {
						unloadListeners.splice(i, 1);
						return true;
					}
				}
				return false;
			}
			var cacheItem = null;
			var index = arguments[3];
			if ("undefined" == typeof index) {
				index = this._getCacheIndex(el, sType, fn);
			}
			if (index >= 0) {
				cacheItem = listeners[index];
			}
			if (!el || !cacheItem) {
				return false;
			}
			if (this.useLegacyEvent(el, sType)) {
				var legacyIndex = this.getLegacyIndex(el, sType);
				var llist = legacyHandlers[legacyIndex];
				if (llist) {
					for (i = 0, len = llist.length; i < len; ++i) {
						li = llist[i];
						if (li && li[this.EL] == el && li[this.TYPE] == sType
								&& li[this.FN] == fn) {
							llist.splice(i, 1);
							break;
						}
					}
				}
			} else {
				try {
					this._simpleRemove(el, sType, cacheItem[this.WFN], false);
				} catch (ex) {
					this.lastError = ex;
					return false;
				}
			}
			delete listeners[index][this.WFN];
			delete listeners[index][this.FN];
			listeners.splice(index, 1);
			return true;
		},
		getTarget : function(ev, resolveTextNode) {
			var t = ev.target || ev.srcElement;
			return this.resolveTextNode(t);
		},
		resolveTextNode : function(node) {
			if (node && 3 == node.nodeType) {
				return node.parentNode;
			} else {
				return node;
			}
		},
		getPageX : function(ev) {
			var x = ev.pageX;
			if (!x && 0 !== x) {
				x = ev.clientX || 0;
				if (this.isIE) {
					x += this._getScrollLeft();
				}
			}
			return x;
		},
		getPageY : function(ev) {
			var y = ev.pageY;
			if (!y && 0 !== y) {
				y = ev.clientY || 0;
				if (this.isIE) {
					y += this._getScrollTop();
				}
			}
			return y;
		},
		getXY : function(ev) {
			return [this.getPageX(ev), this.getPageY(ev)];
		},
		getRelatedTarget : function(ev) {
			var t = ev.relatedTarget;
			if (!t) {
				if (ev.type == "mouseout") {
					t = ev.toElement;
				} else {
					if (ev.type == "mouseover") {
						t = ev.fromElement;
					}
				}
			}
			return this.resolveTextNode(t);
		},
		getTime : function(ev) {
			if (!ev.time) {
				var t = new Date().getTime();
				try {
					ev.time = t;
				} catch (ex) {
					this.lastError = ex;
					return t;
				}
			}
			return ev.time;
		},
		stopEvent : function(ev) {
			this.stopPropagation(ev);
			this.preventDefault(ev);
		},
		stopPropagation : function(ev) {
			if (ev.stopPropagation) {
				ev.stopPropagation();
			} else {
				ev.cancelBubble = true;
			}
		},
		preventDefault : function(ev) {
			if (ev.preventDefault) {
				ev.preventDefault();
			} else {
				ev.returnValue = false;
			}
		},
		getEvent : function(e) {
			var ev = e || window.event;
			if (!ev) {
				var c = this.getEvent.caller;
				while (c) {
					ev = c.arguments[0];
					if (ev && Event == ev.constructor) {
						break;
					}
					c = c.caller;
				}
			}
			return ev;
		},
		getCharCode : function(ev) {
			return ev.charCode || ev.keyCode || 0;
		},
		_getCacheIndex : function(el, sType, fn) {
			for (var i = 0, len = listeners.length; i < len; ++i) {
				var li = listeners[i];
				if (li && li[this.FN] == fn && li[this.EL] == el
						&& li[this.TYPE] == sType) {
					return i;
				}
			}
			return -1;
		},
		generateId : function(el) {
			var id = el.id;
			if (!id) {
				id = "corevtautoid-" + counter;
				++counter;
				el.id = id;
			}
			return id;
		},
		_isValidCollection : function(o) {
			return (o && o.length && typeof o != "string" && !o.tagName
					&& !o.alert && typeof o[0] != "undefined");
		},
		elCache : {},
		getEl : function(id) {
			return document.getElementById(id);
		},
		clearCache : function() {
		},
		DOMReadyEvent : new Core.CustomEvent("DOMReady", this),
		_load : function(e) {
			if (!loadComplete) {
				loadComplete = true;
				var EU = Core.Event;
				EU._ready();
				if (this.isIE) {
					EU._simpleRemove(window, "load", EU._load);
				}
			}
		},
		_ready : function(e) {
			if (!DOMReady) {
				DOMReady = true;
				var EU = Core.Event;
				EU.DOMReadyEvent.fire();
				EU._simpleRemove(document, "DOMContentLoaded", EU._ready);
			}
		},
		_tryPreloadAttach : function() {
			if (this.locked) {
				return false;
			}
			if (this.isIE && !DOMReady) {
				return false;
			}
			this.locked = true;
			var tryAgain = !loadComplete;
			if (!tryAgain) {
				tryAgain = (retryCount > 0);
			}
			var notAvail = [];
			var executeItem = function(el, item) {
				var scope = el;
				if (item.override) {
					if (item.override === true) {
						scope = item.obj;
					} else {
						scope = item.override;
					}
				}
				item.fn.call(scope, item.obj);
			};
			var i, len, item, el;
			for (i = 0, len = onAvailStack.length; i < len; ++i) {
				item = onAvailStack[i];
				if (item && !item.checkReady) {
					el = this.getEl(item.id);
					if (el) {
						executeItem(el, item);
						onAvailStack[i] = null;
					} else {
						notAvail.push(item);
					}
				}
			}
			for (i = 0, len = onAvailStack.length; i < len; ++i) {
				item = onAvailStack[i];
				if (item && item.checkReady) {
					el = this.getEl(item.id);
					if (el) {
						if (loadComplete || el.nextSibling) {
							executeItem(el, item);
							onAvailStack[i] = null;
						}
					} else {
						notAvail.push(item);
					}
				}
			}
			retryCount = (notAvail.length === 0) ? 0 : retryCount - 1;
			if (tryAgain) {
				this.startInterval();
			} else {
				clearInterval(this._interval);
				this._interval = null;
			}
			this.locked = false;
			return true;
		},
		purgeElement : function(el, recurse, sType) {
			var elListeners = this.getListeners(el, sType);
			if (elListeners) {
				for (var i = 0, len = elListeners.length; i < len; ++i) {
					var l = elListeners[i];
					this.removeListener(el, l.type, l.fn);
				}
			}
			if (recurse && el && el.childNodes) {
				for (i = 0, len = el.childNodes.length; i < len; ++i) {
					this.purgeElement(el.childNodes[i], recurse, sType);
				}
			}
		},
		getListeners : function(el, sType) {
			var results = [], searchLists;
			if (!sType) {
				searchLists = [listeners, unloadListeners];
			} else {
				if (sType == "unload") {
					searchLists = [unloadListeners];
				} else {
					searchLists = [listeners];
				}
			}
			for (var j = 0; j < searchLists.length; ++j) {
				var searchList = searchLists[j];
				if (searchList && searchList.length > 0) {
					for (var i = 0, len = searchList.length; i < len; ++i) {
						var l = searchList[i];
						if (l && l[this.EL] === el
								&& (!sType || sType === l[this.TYPE])) {
							results.push({
										type : l[this.TYPE],
										fn : l[this.FN],
										obj : l[this.OBJ],
										adjust : l[this.ADJ_SCOPE],
										index : i
									});
						}
					}
				}
			}
			return (results.length) ? results : null;
		},
		_unload : function(e) {
			var EU = Core.Event, i, j, l, len, index;
			for (i = 0, len = unloadListeners.length; i < len; ++i) {
				l = unloadListeners[i];
				if (l) {
					var scope = window;
					if (l[EU.ADJ_SCOPE]) {
						if (l[EU.ADJ_SCOPE] === true) {
							scope = l[EU.OBJ];
						} else {
							scope = l[EU.ADJ_SCOPE];
						}
					}
					l[EU.FN].call(scope, EU.getEvent(e), l[EU.OBJ]);
					unloadListeners[i] = null;
					l = null;
					scope = null;
				}
			}
			unloadListeners = null;
			if (listeners && listeners.length > 0) {
				j = listeners.length;
				while (j) {
					index = j - 1;
					l = listeners[index];
					if (l) {
						EU
								.removeListener(l[EU.EL], l[EU.TYPE], l[EU.FN],
										index);
					}
					j = j - 1;
				}
				l = null;
				EU.clearCache();
			}
			for (i = 0, len = legacyEvents.length; i < len; ++i) {
				legacyEvents[i][0] = null;
				legacyEvents[i] = null;
			}
			legacyEvents = null;
			EU._simpleRemove(window, "unload", EU._unload);
		},
		_getScrollLeft : function() {
			return this._getScroll()[1];
		},
		_getScrollTop : function() {
			return this._getScroll()[0];
		},
		_getScroll : function() {
			var dd = document.documentElement, db = document.body;
			if (dd && (dd.scrollTop || dd.scrollLeft)) {
				return [dd.scrollTop, dd.scrollLeft];
			} else {
				if (db) {
					return [db.scrollTop, db.scrollLeft];
				} else {
					return [0, 0];
				}
			}
		},
		regCE : function() {
		},
		_simpleAdd : function() {
			if (window.addEventListener) {
				return function(el, sType, fn, capture) {
					el.addEventListener(sType, fn, (capture));
				};
			} else {
				if (window.attachEvent) {
					return function(el, sType, fn, capture) {
						el.attachEvent("on" + sType, fn);
					};
				} else {
					return function() {
					};
				}
			}
		}(),
		_simpleRemove : function() {
			if (window.removeEventListener) {
				return function(el, sType, fn, capture) {
					el.removeEventListener(sType, fn, (capture));
				};
			} else {
				if (window.detachEvent) {
					return function(el, sType, fn) {
						el.detachEvent("on" + sType, fn);
					};
				} else {
					return function() {
					};
				}
			}
		}()
	};
	(function() {
		var EU = Core.Event;
		EU.on = EU.addListener;
		if (EU.isIE) {
			document.write('<scr'
					+ 'ipt id="_core_eu_dr" defer="true" src="//:"></script>');
			var el = document.getElementById("_core_eu_dr");
			el.onreadystatechange = function() {
				if ("complete" == this.readyState) {
					this.parentNode.removeChild(this);
					Core.Event._ready();
				}
			};
			el = null;
			Core.Event.onDOMReady(Core.Event._tryPreloadAttach, Core.Event,
					true);
		} else {
			if (EU.webkit) {
				EU._drwatch = setInterval(function() {
							var rs = document.readyState;
							if ("loaded" == rs || "complete" == rs) {
								clearInterval(EU._drwatch);
								EU._drwatch = null;
								EU._ready();
							}
						}, EU.POLL_INTERVAL);
			} else {
				EU._simpleAdd(document, "DOMContentLoaded", EU._ready);
			}
		}
		EU._simpleAdd(window, "load", EU._load);
		EU._simpleAdd(window, "unload", EU._unload);
		EU._tryPreloadAttach();
	})();
}();
Core.EventProvider = function() {
};
Core.EventProvider.prototype = {
	__core_events : null,
	__core_subscribers : null,
	subscribe : function(p_type, p_fn, p_obj, p_override) {
		this.__core_events = this.__core_events || {};
		var ce = this.__core_events[p_type];
		if (ce) {
			ce.subscribe(p_fn, p_obj, p_override);
		} else {
			this.__core_subscribers = this.__core_subscribers || {};
			var subs = this.__core_subscribers;
			if (!subs[p_type]) {
				subs[p_type] = [];
			}
			subs[p_type].push({
						fn : p_fn,
						obj : p_obj,
						override : p_override
					});
		}
	},
	unsubscribe : function(p_type, p_fn, p_obj) {
		this.__core_events = this.__core_events || {};
		var ce = this.__core_events[p_type];
		if (ce) {
			return ce.unsubscribe(p_fn, p_obj);
		} else {
			return false;
		}
	},
	unsubscribeAll : function(p_type) {
		return this.unsubscribe(p_type);
	},
	createEvent : function(p_type, p_config) {
		this.__core_events = this.__core_events || {};
		var opts = p_config || {};
		var events = this.__core_events;
		if (events[p_type]) {
		} else {
			var scope = opts.scope || this;
			var silent = opts.silent || null;
			var ce = new Core.CustomEvent(p_type, scope, silent,
					Core.CustomEvent.FLAT);
			events[p_type] = ce;
			if (opts.onSubscribeCallback) {
				ce.subscribeEvent.subscribe(opts.onSubscribeCallback);
			}
			this.__core_subscribers = this.__core_subscribers || {};
			var qs = this.__core_subscribers[p_type];
			if (qs) {
				for (var i = 0; i < qs.length; ++i) {
					ce.subscribe(qs[i].fn, qs[i].obj, qs[i].override);
				}
			}
		}
		return events[p_type];
	},
	fireEvent : function(p_type, arg1, arg2, etc) {
		this.__core_events = this.__core_events || {};
		var ce = this.__core_events[p_type];
		if (ce) {
			var args = [];
			for (var i = 1; i < arguments.length; ++i) {
				args.push(arguments[i]);
			}
			return ce.fire.apply(ce, args);
		} else {
			return null;
		}
	},
	hasEvent : function(type) {
		if (this.__core_events) {
			if (this.__core_events[type]) {
				return true;
			}
		}
		return false;
	}
};
Core.KeyListener = function(attachTo, keyData, handler, event) {
	if (!attachTo) {
	} else {
		if (!keyData) {
		} else {
			if (!handler) {
			}
		}
	}
	if (!event) {
		event = Core.KeyListener.KEYDOWN;
	}
	var keyEvent = new Core.CustomEvent("keyPressed");
	this.enabledEvent = new Core.CustomEvent("enabled");
	this.disabledEvent = new Core.CustomEvent("disabled");
	if (typeof attachTo == 'string') {
		attachTo = document.getElementById(attachTo);
	}
	if (typeof handler == 'function') {
		keyEvent.subscribe(handler);
	} else {
		keyEvent.subscribe(handler.fn, handler.scope, handler.correctScope);
	}
	function handleKeyPress(e, obj) {
		if (!keyData.shift) {
			keyData.shift = false;
		}
		if (!keyData.alt) {
			keyData.alt = false;
		}
		if (!keyData.ctrl) {
			keyData.ctrl = false;
		}
		if (e.shiftKey == keyData.shift && e.altKey == keyData.alt
				&& e.ctrlKey == keyData.ctrl) {
			var dataItem;
			var keyPressed;
			if (keyData.keys instanceof Array) {
				for (var i = 0; i < keyData.keys.length; i++) {
					dataItem = keyData.keys[i];
					if (dataItem == e.charCode) {
						keyEvent.fire(e.charCode, e);
						break;
					} else {
						if (dataItem == e.keyCode) {
							keyEvent.fire(e.keyCode, e);
							break;
						}
					}
				}
			} else {
				dataItem = keyData.keys;
				if (dataItem == e.charCode) {
					keyEvent.fire(e.charCode, e);
				} else {
					if (dataItem == e.keyCode) {
						keyEvent.fire(e.keyCode, e);
					}
				}
			}
		}
	}
	this.enable = function() {
		if (!this.enabled) {
			Core.Event.addListener(attachTo, event, handleKeyPress);
			this.enabledEvent.fire(keyData);
		}
		this.enabled = true;
	};
	this.disable = function() {
		if (this.enabled) {
			Core.Event.removeListener(attachTo, event, handleKeyPress);
			this.disabledEvent.fire(keyData);
		}
		this.enabled = false;
	};
	this.toString = function() {
		return "KeyListener [" + keyData.keys + "] " + attachTo.tagName
				+ (attachTo.id ? "[" + attachTo.id + "]" : "");
	};
};
Core.KeyListener.KEYDOWN = "keydown";
Core.KeyListener.KEYUP = "keyup";
Core.JSON = function() {
	function f(n) {
		return n < 10 ? '0' + n : n;
	};
	Date.prototype.toJSON = function() {
		return this.getUTCFullYear() + '-' + f(this.getUTCMonth() + 1) + '-'
				+ f(this.getUTCDate()) + 'T' + f(this.getUTCHours()) + ':'
				+ f(this.getUTCMinutes()) + ':' + f(this.getUTCSeconds()) + 'Z';
	};
	var m = {
		'\b' : '\\b',
		'\t' : '\\t',
		'\n' : '\\n',
		'\f' : '\\f',
		'\r' : '\\r',
		'"' : '\\"',
		'\\' : '\\\\'
	};
	function stringify(value, whitelist) {
		var a, i, k, l, r = /["\\\x00-\x1f\x7f-\x9f]/g, v;
		switch (typeof value) {
			case 'string' :
				return r.test(value) ? '"' + value.replace(r, function(a) {
							var c = m[a];
							if (c) {
								return c;
							}
							c = a.charCodeAt();
							return '\\u00' + Math.floor(c / 16).toString(16)
									+ (c % 16).toString(16);
						}) + '"' : '"' + value + '"';
			case 'number' :
				return isFinite(value) ? String(value) : 'null';
			case 'boolean' :
			case 'null' :
				return String(value);
			case 'object' :
				if (!value) {
					return 'null';
				}
				if (typeof value.toJSON === 'function') {
					return stringify(value.toJSON());
				}
				a = [];
				if (typeof value.length === 'number'
						&& !(value.propertyIsEnumerable('length'))) {
					l = value.length;
					for (i = 0; i < l; i += 1) {
						a.push(stringify(value[i], whitelist) || 'null');
					}
					return '[' + a.join(',') + ']';
				}
				if (whitelist) {
					l = whitelist.length;
					for (i = 0; i < l; i += 1) {
						k = whitelist[i];
						if (typeof k === 'string') {
							v = stringify(value[k], whitelist);
							if (v) {
								a.push(stringify(k) + ':' + v);
							}
						}
					}
				} else {
					for (k in value) {
						if (typeof k === 'string') {
							v = stringify(value[k], whitelist);
							if (v) {
								a.push(stringify(k) + ':' + v);
							}
						}
					}
				}
				return '{' + a.join(',') + '}';
		}
	};
	return {
		stringify : stringify,
		parse : function(text, filter) {
			var j;
			function walk(k, v) {
				var i, n;
				if (v && typeof v === 'object') {
					for (i in v) {
						if (Object.prototype.hasOwnProperty.apply(v, [i])) {
							n = walk(i, v[i]);
							if (n !== undefined) {
								v[i] = n;
							} else {
								delete v[i];
							}
						}
					}
				}
				return filter(k, v);
			};
			if (/^[\],:{}\s]*$/
					.test(text
							.replace(/\\./g, '@')
							.replace(
									/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
									']').replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
				j = eval('(' + text + ')');
				return typeof filter === 'function' ? walk('', j) : j;
			}
			throw new SyntaxError('parseJSON');
		}
	};
}();
(function() {
	var MsXML = ['MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP'];
	var use_default_xhr_header = true;
	var http_headers = {};
	var has_http_headers = false;
	var use_default_post_header = true;
	var default_xhr_header = 'XMLHttpRequest';
	var has_default_headers = true;
	var default_headers = {};
	var default_post_header = 'application/x-www-form-urlencoded; charset=UTF-8';
	var use_default_xhr_header = true;
	var isOffline = function() {
		var Offline = true;
		var callBack = {
			success : function(data) {
				if (data) {
					Offline = false;
				}
			},
			failure : function(status) {
				if (status) {
					Offline = true;
				}
			}
		};
		var ajax = new Core.Ajax();
		ajax.request('GET', 'js/isOffline.json', callBack);
		return Offline;
	};
	Core.Ajax = function() {
	};
	Core.Ajax.prototype = {
		request : function(method, url, callback, postVars) {
			var xhr = this.createXhrObject();
			if (method.toUpperCase() === 'GET') {
				url = postVars ? url + postVars : url;
			}
			xhr.open(method, url, true);
			xhr.onreadystatechange = function() {
				if (xhr.readyState !== 4) {
					return false;
				}
				(xhr.status === 200) ? callback.success(xhr.responseText,
						xhr.responseXML) : callback.failure(xhr.status);
			};
			if (use_default_xhr_header) {
				if (!default_headers['X-Requested-With']) {
					this.initHeader('X-Requested-With', default_xhr_header,
							true);
				}
			}
			if (has_default_headers || has_http_headers) {
				this.setHeader(xhr);
			}
			if (method.toUpperCase() !== 'POST') {
				postVars = null;
			}
			xhr.send(postVars);
		},
		createXhrObject : function() {
			var XhrObject = null;
			var methods = [function() {
						return new XMLHttpRequest();
					}, function() {
						var j, length;
						for (j = 0, length = MsXML.length; j < length; j += 1) {
							try {
								return new ActiveXObject(MsXML[j]);
							} catch (e) {
								throw new Error('Could not create MsXML object.');
							}
						}
					}], i, len;
			for (i = 0, len = methods.length; i < len; i += 1) {
				try {
					XhrObject = methods[i]();
				} catch (e) {
					throw new Error('Could not create XMLHttpRequest Object.');
				}
				this.createXhrObject = XhrObject;
				return XhrObject;
			}
		},
		initHeader : function(label, value, isDefault) {
			var headerObj = (isDefault) ? default_headers : http_headers;
			if (headerObj[label] === undefined) {
				headerObj[label] = value;
			} else {
				headerObj[label] = value + "," + headerObj[label];
			}
			if (isDefault) {
				has_default_headers = true;
			} else {
				has_http_headers = true;
			}
		},
		setHeader : function(o) {
			if (has_default_headers) {
				for (var prop in default_headers) {
					if (Core.lang.hasOwnProperty(default_headers, prop)) {
						o.setRequestHeader(prop, default_headers[prop]);
					}
				}
			}
			if (has_http_headers) {
				for (var prop in http_headers) {
					if (Core.lang.hasOwnProperty(http_headers, prop)) {
						o.setRequestHeader(prop, http_headers[prop]);
					}
				}
				delete http_headers;
				http_headers = {};
				has_http_headers = false;
			}
		}
	};
	Core.ajaxQueued = function() {
		this.queue = [];
		this.requestInProgress = false;
		this.retryDelay = 5;
	};
	Core.extend(Core.ajaxQueued, Core.Ajax);
	Core.ajaxQueued.prototype = {
		request : function(method, url, callback, postVars, overrides) {
			if (this.requestInPrograss && overrides) {
				this.queue.push({
							method : method,
							url : url,
							callback : callback,
							postVars : postVars
						});
			} else {
				this.requestInPrograss = true;
				var xhr = this.createXhrObject();
				var that = this;
				xhr.open(method, url, true);
				xhr.onreadystatechange = function() {
					if (xhr.readyState !== 4) {
						return false;
					}
					if (xhr.status === 200) {
						callback.success(xhr.responseText, xhr.responseXML);
						that.advanceQueue();
					} else {
						callback.failure(xhr.status);
						setTimeout(function() {
							that.request(method, url, callback, postVars, true);
						}, (that.retryDaly * 1000));
					}
				};
				if (use_default_xhr_header) {
					if (!default_headers['X-Requested-With']) {
						this.initHeader('X-Requested-With', default_xhr_header,
								true);
					}
				}
				if (has_default_headers || has_http_headers) {
					this.setHeader(xhr);
				}
				if (method.toUpperCase() !== 'POST') {
					postVars = null;
				}
				xhr.send(postVars);
			}
		},
		advanceQueue : function() {
			if (this.queue.length === 0) {
				this.requestInPrograss = false;
				return;
			}
			var req = this.queue.shift();
			this.rquest(req.method, req.url, req.callback, req.postVars, true);
		}
	};
	Core.ajaxOffline = function() {
		this.storedRequests = [];
	};
	Core.extend(Core.ajaxOffline, Core.Ajax);
	Core.ajaxOffline.prototype = {
		request : function(method, url, callback, postVars) {
			if (isOffline()) {
				this.storedRequests.push({
							method : method,
							url : url,
							callback : callback,
							postVars : postVars
						});
			} else {
				this.flushStoredRequests();
				Core.ajaxOffline.superclass.rquest(method, url, callback,
						postVars);
			}
		},
		flushStoredRequests : function() {
			var i, req, storeRequests = this.storedRequests, len = storeRequests.length;
			for (i = 0; i < len; i += 1) {
				req = storeRequests[i];
				Core.ajaxOffline.superclass.request(req.method, req.url,
						req.callback, req.postVars);
			}
		}
	};
})();
Core.Effects = {
	Drag : {
		obj : null,
		init : function(o, oRoot, minX, maxX, minY, maxY, bSwapHorzRef,
				bSwapVertRef, fXMapper, fYMapper) {
			if (!o || !oRoot) {
				return false;
			}
			o.style.cursor = 'move';
			o.onmousedown = Core.Effects.Drag.start;
			o.hmode = bSwapHorzRef ? false : true;
			o.vmode = bSwapVertRef ? false : true;
			o.root = oRoot && oRoot != null ? oRoot : o;
			if (o.hmode && isNaN(parseInt(o.root.style.left, 10))) {
				o.root.style.left = '0';
			}
			if (o.vmode && isNaN(parseInt(o.root.style.top, 10))) {
				o.root.style.top = '0';
			}
			if (!o.hmode && isNaN(parseInt(o.root.style.right, 10))) {
				o.root.style.right = '0';
			}
			if (!o.vmode && isNaN(parseInt(o.root.style.bottom, 10))) {
				o.root.style.bottom = '0';
			}
			o.minX = typeof minX != 'undefined' ? minX : null;
			o.minY = typeof minY != 'undefined' ? minY : null;
			o.maxX = typeof maxX != 'undefined' ? maxX : null;
			o.maxY = typeof maxY != 'undefined' ? maxY : null;
			o.xMapper = fXMapper ? fXMapper : null;
			o.yMapper = fYMapper ? fYMapper : null;
			o.root.onDragStart = new Function();
			o.root.onDragEnd = new Function();
			o.root.onDrag = new Function();
		},
		start : function(e) {
			var o = this;
			Core.Effects.Drag.obj = this;
			e = Core.Effects.Drag.fixE(e);
			Core.Effects.Drag.opacity(o.root, 50);
			var y = parseInt(o.vmode ? o.root.style.top : o.root.style.bottom);
			var x = parseInt(o.hmode ? o.root.style.left : o.root.style.right);
			o.root.onDragStart(x, y);
			o.lastMouseX = e.clientX;
			o.lastMouseY = e.clientY;
			if (o.hmode) {
				if (o.minX != null) {
					o.minMouseX = e.clientX - x + o.minX;
				}
				if (o.maxX != null) {
					o.maxMouseX = o.minMouseX + o.maxX - o.minX;
				}
			} else {
				if (o.minX != null) {
					o.maxMouseX = -o.minX + e.clientX + x;
				}
				if (o.maxX != null) {
					o.minMouseX = -o.maxX + e.clientX + x;
				}
			}
			if (o.vmode) {
				if (o.minY != null) {
					o.minMouseY = e.clientY - y + o.minY;
				}
				if (o.maxY != null) {
					o.maxMouseY = o.minMouseY + o.maxY - o.minY;
				}
			} else {
				if (o.minY != null) {
					o.maxMouseY = -o.minY + e.clientY + y;
				}
				if (o.maxY != null) {
					o.minMouseY = -o.maxY + e.clientY + y;
				}
			}
			document.onmousemove = Core.Effects.Drag.drag;
			document.onmouseup = Core.Effects.Drag.end;
			return false;
		},
		drag : function(e) {
			e = Core.Effects.Drag.fixE(e);
			var o = Core.Effects.Drag.obj;
			var ey = e.clientY;
			var ex = e.clientX;
			var y = parseInt(o.vmode ? o.root.style.top : o.root.style.bottom);
			var x = parseInt(o.hmode ? o.root.style.left : o.root.style.right);
			var nx, ny;
			if (o.minX != null) {
				ex = o.hmode ? Math.max(ex, o.minMouseX) : Math.min(ex,
						o.maxMouseX);
			}
			if (o.maxX != null) {
				ex = o.hmode ? Math.min(ex, o.maxMouseX) : Math.max(ex,
						o.minMouseX);
			}
			if (o.minY != null) {
				ey = o.vmode ? Math.max(ey, o.minMouseY) : Math.min(ey,
						o.maxMouseY);
			}
			if (o.maxY != null) {
				ey = o.vmode ? Math.min(ey, o.maxMouseY) : Math.max(ey,
						o.minMouseY);
			}
			nx = x + ((ex - o.lastMouseX) * (o.hmode ? 1 : -1));
			ny = y + ((ey - o.lastMouseY) * (o.vmode ? 1 : -1));
			if (o.xMapper) {
				nx = o.xMapper(y);
			} else {
				if (o.yMapper) {
					ny = o.yMapper(x);
				}
			}
			o.root.style[o.hmode ? 'left' : 'right'] = nx + 'px';
			o.root.style[o.vmode ? 'top' : 'bottom'] = ny + 'px';
			o.lastMouseX = ex;
			o.lastMouseY = ey;
			o.root.onDrag(nx, ny);
			return false;
		},
		end : function() {
			var o = Core.Effects.Drag.obj;
			document.onmousemove = document.onmouseup = function() {
				return null;
			};
			Core.Effects.Drag.opacity(o.root, 100);
			o.root.onDragEnd(parseInt(o.root.style[o.hmode ? "left" : "right"],
							10), parseInt(o.root.style[o.vmode
									? "top"
									: "bottom"], 10));
			o = null;
		},
		fixE : function(e) {
			if (typeof e == 'undefined') {
				e = window.event;
			}
			if (typeof e.layerX == 'undefined') {
				e.layerX = e.offsetX;
			}
			if (typeof e.layerY == 'undefined') {
				e.layerY = e.offsetY;
			}
			return e;
		},
		opacity : function(element, value) {
			var style = element.style;
			style.opacity = value / 100;
			style.filter = 'alpha(opacity=' + value + ')';
		}
	},
	scrollVertical : function(disp, msg, tg) {
		this.scrollArea = null;
		this.scrollMsg = null;
		this.unitHeight = 0;
		this.msgHeight = 0;
		this.copyMsg = null;
		this.scrollValue = 0;
		this.scrollHeight = 0;
		this.isStop = true;
		this.isPause = false;
		this.isMoz = function() {
			if (navigator.userAgent.toLowerCase().match(/mozilla/)) {
				return 1;
			}
		};
		this.scrollTimer = null;
		this.speed = 2000;
		this.play = function(o) {
			var s_msg = o.scrollMsg;
			var c_msg = o.copyMsg;
			var s_area = o.scrollArea;
			var msg_h = o.msgHeight;
			var anim = function() {
				if (o.scrollTimer) {
					clearTimeout(o.scrollTimer);
				}
				if (o.isPause) {
					o.scrollTimer = setTimeout(anim, 10);
					return;
				}
				if (msg_h - o.scrollValue <= 0) {
					o.scrollValue = 0;
				} else {
					o.scrollValue += 1;
					o.scrollHeight += 1;
				}
				if (o.isMoz) {
					s_area.scrollTop = o.scrollValue;
				} else {
					s_msg.style.top = -1 * o.scrollValue + "px";
					c_msg.style.top = (msg_h - o.scrollValue) + "px";
				}
				if (o.scrollHeight % s_area.offsetHeight == 0) {
					o.scrollTimer = setTimeout(anim, o.speed);
				} else {
					o.scrollTimer = setTimeout(anim, 10);
				}
			};
			anim();
		};
		if (typeof(disp) == 'string') {
			this.scrollArea = document.getElementById(disp);
		} else {
			this.scrollArea = disp;
		}
		if (typeof(msg) == 'string') {
			this.scrollMsg = document.getElementById(msg);
		} else {
			this.scrollMsg = msg;
		}
		var s_msg = this.scrollMsg;
		var s_area = this.scrollArea;
		if (!tg) {
			var tg = 'li';
		}
		this.unitHeight = s_msg.getElementsByTagName(tg)[0].offsetHeight;
		this.msgHeight = this.unitHeight
				* s_msg.getElementsByTagName(tg).length;
		s_msg.style.position = "absolute";
		s_msg.style.top = "0";
		s_msg.style.left = "0";
		var copydiv = document.createElement('div');
		copydiv.id = s_area.id + "_copymsgid";
		copydiv.innerHTML = s_msg.innerHTML;
		copydiv.style.height = this.msgHeight + "px";
		s_area.appendChild(copydiv);
		copydiv.style.position = "absolute";
		copydiv.style.left = "0";
		copydiv.style.top = this.msgHeight + "px";
		this.copyMsg = copydiv;
		this.play(this);
	},
	moveElement : function(elementID, final_x, final_y, speed) {
		if (!document.getElementById || !document.getElementById(elementID)) {
			return false;
		}
		var elem = document.getElementById(elementID);
		var style = elem.style;
		if (timer) {
			clearTimeout(timer);
		}
		if (!style.left) {
			style.left = '0';
		}
		if (!style.top) {
			style.top = '0';
		}
		var xpos = parseInt(style.left, 10);
		var ypos = parseInt(style.top, 10);
		if (xpos == final_x && ypos == final_y) {
			return true;
		}
		if (xpos < final_x) {
			var dist = Math.ceil((final_x - xpos) / 10);
			xpos = xpos + dist;
		}
		if (xpos > final_x) {
			var dist = Math.ceil((xpos - final_x) / 10);
			xpos = xpos - dist;
		}
		if (ypos < final_y) {
			var dist = Math.ceil((final_y - ypos) / 10);
			ypos = ypos + dist;
		}
		if (ypos > final_y) {
			var dist = Math.ceil((ypos - final_y) / 10);
			ypos = ypos - dist;
		}
		style.left = xpos + 'px';
		style.top = ypos + 'px';
		var timer = setTimeout(function() {
					moveElement(elementID, final_x, final_y, speed);
				}, speed);
	}
};
function include_files(fn) {
	var hd = document.getElementsByTagName("head")[0], arr_f = [], re, ty, f, url;
	if (typeof fn == "object") {
		arr_f = fn;
	} else {
		arr_f[0] = fn;
	}
	var append_new = function(src, type) {
		switch (type) {
			case "js" :
				document.write("<sc" + "ript src=\"" + src
						+ "\" type=\"text/javascript\"></sc" + "ript>");
				break;
			case "css" :
				f = document.createElement("link");
				f.setAttribute("rel", "stylesheet");
				f.setAttribute("type", "text/css");
				f.href = src;
				hd.appendChild(f);
				break;
		}
	};
	var has_script = function(url) {
		var scripts = document.getElementsByTagName("script");
		for (var i = 0, j = scripts.length; i < j; i++) {
			if (scripts[i].src
					&& scripts[i].src.replace(/\//g, "\\/").replace(/\./g,
							"\\.") == url) {
				return true;
			}
		}
		return false;
	};
	for (var i = 0, j = arr_f.length; i < j; i++) {
		url = arr_f[i].replace(/\//g, "\\/").replace(/\./g, "\\.");
		re = eval("/src=" + url + "|src=\"" + url + "\"|href=" + url
				+ "|href=\"" + url + "\"/ig");
		ty = arr_f[i].substring(arr_f[i].lastIndexOf(".") + 1);
		if (ty == "css") {
			if (hd.innerHTML.search(re) < 0) {
				append_new(arr_f[i], ty);
			}
		} else {
			if (ty == "js" && !has_script(url)) {
				append_new(arr_f[i], ty);
			}
		}
	}
}