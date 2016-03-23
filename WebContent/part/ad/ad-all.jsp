
<%@page import="java.util.Random"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<%
	Random rd = new Random();
	String width  = request.getParameter("width");
	String height = request.getParameter("height");
	
	if(StringUtils.isBlank(width)){
		width = "300";
	}
	if(StringUtils.isBlank(height)){
		height = "250";
	}
	
	if("300".equals(width) && "250".equals(height)){
		int max = 8;
		if(rd.nextInt(max)==0){
			%>
				<!-- 橱窗 -->
				<script type="text/javascript">
				    var cpro_id="u2199079";
				    (window["cproStyleApi"] = window["cproStyleApi"] || {})[cpro_id]={at:"3",rsi0:"300",rsi1:"250",pat:"17",tn:"baiduCustNativeAD",rss1:"#FFFFFF",conBW:"1",adp:"1",ptt:"0",titFF:"",titFS:"14",rss2:"#000000",titSU:"0"}
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
			
				
			<%
			
		}
		else if(rd.nextInt(max) ==1){
			%>
				<script type="text/javascript">
				    /*侧边栏 主题链接 pc*/
				    var cpro_id = "u2188174";
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
			
			
			<%
		}
		else if(rd.nextInt(max) ==2){
			%>
				<script type="text/javascript">
				    /*文字*/
				    var cpro_id = "u2205283";
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>

			
			<%
		}
		else if(rd.nextInt(max) ==3){
			%>
				<script type="text/javascript">
				    /*图片*/
				    var cpro_id = "u2205291";
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
			
			<%
		}
		else if(rd.nextInt(max) ==4){
			%>
				<script type="text/javascript">
				    /*图文*/
				    var cpro_id = "u2205322";
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>

			
			<%
		}
		else if(rd.nextInt(max) ==5){
			%>
				<script type="text/javascript">
				    /*链接*/
				    var cpro_id = "u2205327";
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>


			
			<%
		}
		else if(rd.nextInt(max) ==6){
			%>
				<script type="text/javascript">
				    /*标签云*/
				    var cpro_id = "u2205332";
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
			
			<%
		}
		else if(rd.nextInt(max) ==7){
			%>
				<script type="text/javascript">
				    /*图片排行*/
				    var cpro_id = "u2205336";
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
								
			
			<%
		}
		
		else if(rd.nextInt(max) ==8){
			%>
				<script>QIHOO_UNION_SLOT={w:300, h:250, ls:"s7c75f22977",t: "inlay"};</script>
				<script src="http://s.lianmeng.360.cn/so/inlay.js" charset="utf-8"></script>
			<%
		}
		else if(rd.nextInt(max) ==9){
			%>
				<script>QIHOO_UNION_SLOT={w:300, h:250, ls:"s0dac322b73",t: "link"};</script>
				<script src="http://s.lianmeng.360.cn/so/inlay.js" charset="utf-8"></script>
			<%
		}		
		
		
		
	}
	else if("960".equals(width) && "60".equals(height)){
		%>
		<script type="text/javascript">
		    /*主题链接 页面页脚*/
		    var cpro_id = "u2188178";
		</script>
		<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
		
		<%
	}
	else if("640".equals(width) && "60".equals(height)){
		%>
		<script type="text/javascript">
		    /*640*60 创建于 2015-07-11 图文*/
		    var cpro_id = "u2202318";
		</script>
		<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
		
		<%
	}
	else if("468".equals(width) && "60".equals(height)){
		%>
		<script type="text/javascript">
		    /*主题链接 页面页脚  468*60  */
		    var cpro_id = "u2188200";
		</script>
		<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
		
		<%
	}
%>
