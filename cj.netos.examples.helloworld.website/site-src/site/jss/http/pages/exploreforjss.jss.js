/*
 * 2016.0829
 * 作者：赵向彬
 * 说明：services是声明本jss要引用的服务
 * <![jss:{
		scope:'runtime',
		extends:'cj.studio.gateway.socket.app.IGatewayAppSiteWayWebView',
		isStronglyJss:true,
		filter:{
	 	}
	},
	object:{
	 		name:"test..."
	},
 	services:{
        jssService:'$.cj.jss.hellowJSS.jssService'
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

exports.flow = function(f,c,ctx) {
    var action=f.parameter('action');
    if(action!=null&&action!==''){
        if(action==='getName'){
            var name=f.parameter('name');
            var jssService=imports.head.services.jssService;
            c.content().writeBytes(jssService.hello(name).getBytes());
        }
        return;
    }

    var doc=ctx.html('/pages/exploreforjss.html');
    c.content().writeBytes(doc.toString().getBytes());
}
