var baseUrl = 'http://192.168.1.106:8080';

var hxKey = 'suixingkeji#demo1';

var conn  ; //环信 连接
 
//store object in local
function huanxinInit(){
	
	  conn = new Easemob.im.Connection();
	
	conn.init({
		//var conn1 =  Storage.get('conn');
		//conn1.init({
		https : false,
		wss: false,
		url: null,
		//当连接成功时的回调方法
		onOpened : function() {
			//alert('huan xin isConnected');
			window.localStorage.isConnected  = true;
			
			//设置用户上线状态，必须调用
			conn.setPresence();
			
			//启动心跳
			if (conn.isOpened()) {
				conn.heartBeat(conn);
			}
			
			
			//location.href = baseUrl +'/jishi/inquiry-all.jsp';
			/*
			var options = {
					to : 'shaolizhi',
					msg : 'test huanxin chat',
					type : "chat"
				};
			
			conn.sendTextMessage(options);
			*/
			
			//handleOpen(conn);
		},
 
		//当连接关闭时的回调方法
		onClosed : function() {
			//handleClosed();
			 
		},
		//收到文本消息时的回调方法
		onTextMessage : function(message) {
			//alert('onTextMessage');
			
			var from = message.from;//消息的发送者
			var messageContent = message.data;//文本消息体
			
			if(from == localStorage.currentchatTo ){ //当前聊天对象发来了信息
				$('#chatList').append('<div class="weixin-msg-text-user">'+ messageContent +'</div>');
				$('#chatList').append('<div style="width:100%;float:left;height:10px;"></div>');
			}
		},
		//收到表情消息时的回调方法
		onEmotionMessage : function(message) {
			//alert('other');
			//handleEmotion(message);
		},
		//收到图片消息时的回调方法
		onPictureMessage : function(message) {
			//handlePictureMessage(message);
			//alert('other');
		},
		//收到音频消息的回调方法
		onAudioMessage : function(message) {
			//handleAudioMessage(message);
			//alert('other');
		},
		//收到位置消息的回调方法
		onLocationMessage : function(message) {
			//handleLocationMessage(message);
			//alert('other');
		},
		//收到文件消息的回调方法
		onFileMessage : function(message) {
			//handleFileMessage(message);
			//alert('other');
		},
		//收到视频消息的回调方法
		onVideoMessage : function(message) {
			//handleVideoMessage(message);
			//alert('other');
		},
		//收到联系人订阅请求的回调方法
		onPresence : function(message) {
			//handlePresence(message);
			//alert('other');
		},
		//收到联系人信息的回调方法
		onRoster : function(message) {
			//handleRoster(message);
			//alert('other');
		},
		//收到群组邀请时的回调方法
		onInviteMessage : function(message) {
			//handleInviteMessage(message);
			//alert('other');
		},
		//异常时的回调方法
		onError : function(message) {
			//alert('聊天系统失败');
			conn.stopHeartBeat(conn);
			 //alert( JSON.stringify(message)  );
			
			//handleError(message);
		}
	});
	
}

function connect(technicialLoginName,pass){
	conn.open({
		apiUrl : null,
		user : technicialLoginName,
		pwd : pass,
		//连接时提供appkey
		appKey : hxKey
	});
}

function sendTextMessage(to,msg){
	var options = {
			to : to,
			msg :msg,
			type : "chat"
		};
	
	/*
	if(window.localStorage.isConnected == false){
		alert('暂未连接聊天服务器,请稍后');
		//connect(technicianName,pass);
		return;
	}*/
	if(localStorage.isConnected  == false){
		huanxinInit(); //init 环信
		connect(localStorage.technicianName, localStorage.pass); //login 环信
	}
	
	conn.sendTextMessage(options);
}