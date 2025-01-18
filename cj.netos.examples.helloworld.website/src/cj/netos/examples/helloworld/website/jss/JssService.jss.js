/*
 * 2016.0829
 * 作者：赵向彬
 * 说明：services是声明本jss要引用的服务
 * <![jss:{
		scope:'runtime',
	},
 	services:{
        aService:'aService'
 	}
 ]>
 <![desc:{
	ttt:'2323',
	obj:{
		name:'09skdkdk'
		}
* }]>
 */

var String = Java.type('java.lang.String');
var StringUtil = Java.type('cj.ultimate.util.StringUtil');
var StringBuffer = Java.type('java.lang.StringBuffer');
var CircuitException = Java.type('cj.studio.ecm.net.CircuitException');

exports.hello = function (name) {
    var aService = imports.head.services.aService;
    var ret = aService.hello(name);
    return 'JavaScript Services (JSS) OUTPUT: ' + ret;
}