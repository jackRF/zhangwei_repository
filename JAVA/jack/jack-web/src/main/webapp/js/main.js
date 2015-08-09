require.config({ 
    // RequireJS 通过一个相对的路径 baseUrl来加载所有代码。baseUrl通常被设置成data-main属性指定脚本的同级目录。
    baseUrl: "/app/js", 
    // 第三方脚本模块的别名,jquery比libs/jquery-1.11.1.min.js简洁明了；
    paths: { 
    	"jquery": "jquery-1.8.2" 
    } 
});

require(['jquery'],function(jquery){
	console.log(jquery);
	jquery(document).ready(function(){
		//alert('sdfsdf');
	});
	
});
require(['/app/js/mym.js'],function(mym){
	console.log(mym);
});

require(['mym'],function(mym){
	console.log(mym);
});

console.log('sdffyyyyyyyyyyy');
requirejs('/app/js/aa.js');
require(['bb']);