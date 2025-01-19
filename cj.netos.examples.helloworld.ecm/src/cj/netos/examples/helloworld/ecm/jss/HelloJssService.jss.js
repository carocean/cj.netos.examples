/*
 * 2016.0829
 * Author: Andrew
 * Description: `services` declares the services that this JSS will reference.
 * <![jss:{
		scope:'runtime',
		extends:'cj.netos.examples.helloworld.ecm.IHello',
		isStronglyJss:true,
		filter:{
	 	}
	},
 	services:{

 	}
 ]>
 <![desc:{
	ttt:'test',
	obj:{
		name:'test'
		}
* }]>
 */

var String = Java.type('java.lang.String');
var StringBuffer = Java.type('java.lang.StringBuffer');

exports.hello = function (name) {
    return 'Hello '+name+'!';
}
