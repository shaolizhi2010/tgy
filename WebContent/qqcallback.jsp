<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.dao.UserDao"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@page import="com.tgy.qq.QQCallBackService"%>
<%@page import="java.util.Random"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Map"%>

<%
QQCallBackService qq = new QQCallBackService();
UserService uService = new UserService();

try{
	out.println("begin");
	String code = request.getParameter("code");
	if(  !StringUtils.isBlank(code) ){	//返回了code
		session.setAttribute("qqcode", code);
		//out.println(" code : " +code);
		String state = new Random().nextInt()+"" ;
		session.setAttribute("qqstate", state);
		
		String token = qq.getToken(code,state);
		//out.println(" token : " +token);
		
		/* //验证state
		if(token.length()>0){
			String returnState = request.getParameter("state");
			if(!state.equalsIgnoreCase(returnState)){
				out.println(" state : " +state);
				out.println(" returnState : " +returnState);
				return;	//cscf攻击
			}
		}
		*/
		
		String openId = qq.getOpenId(token);
		//out.println(" openId : " +openId);
		
		
		
		Map<String, String> userinfo = qq.getUserQQInfo(token, openId);
		String nickname = userinfo.get("nickname");
		String headUrl = userinfo.get("figureurl_qq_1");
		
		User loginUser = uService.dealWithOpenID(openId, nickname);
		if(loginUser!=null){
			Cookie cookie = new Cookie("lastLoginUserID", loginUser.id.toString());
			cookie.setPath("/");
			response.addCookie(cookie);
			
			Cookie cookie2 = new Cookie("lastPsCode",  loginUser.password );
			cookie2.setPath("/");
			response.addCookie(cookie2);
 
			// login success
			//U.refreshSession(req.getSession());
			//session.invalidate();
			
			//req.getSession().setAttribute(C.userID, loginUser.id);
			session.setAttribute(C.user, loginUser);
		}
		//out.println(" nickname : " +nickname);
		//out.println(" headUrl : " +headUrl);
		
		session.setAttribute("headUrl", headUrl);
		
		response.sendRedirect("/");
		
		return;
	}
	else{	//返回 token
		//String token = request.getParameter("code");
		//out.println(" code : " +code);
		out.println(" else "   );
	}
	out.println("end");
	
}catch (Exception e) {
	out.println(e.getMessage());
	//L.exception(this, e.getMessage());
	// ClientAbortException:  java.net.SocketException: Connection reset by peer: socket write error
	//catch this and do nothing, this is because the client do not receive from server any more
}

%>

<div style="display: none;">
		<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fc2dab893ac85ba9830b8dfd19e9ca3ad' type='text/javascript'%3E%3C/script%3E"));
</script>

</div>