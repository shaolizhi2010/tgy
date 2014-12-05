package com.tgy.qq;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.tgy.util.SimpleConnecter;
import com.tgy.util.U;

public class QQCallBackService {

	String appId = "101171952";
	String appSecret = "2fbb262f4c5bcb01a5fae933da191d45";

	public String getToken(String code, String state) {
		String token = SimpleConnecter
				.connect("https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id="
						+ appId
						+ "&client_secret="
						+ appSecret
						+ "&code="
						+ code
						+ "&redirect_uri=http://www.webhezi.com/qqcallback.jsp&state="
						+ state);
		if (token != null) {
			token = StringUtils.substringBetween(token, "access_token=", "&");
		} else {
			return "";
		}

		if (token == null) {
			return "";
		}
		return token;
	}

	public String getOpenId(String token) {
		String json = SimpleConnecter
				.connect("https://graph.qq.com/oauth2.0/me?access_token="
						+ token);
		if (StringUtils.isBlank(json)) {
			return "";
		}

		json = StringUtils.substringBetween(json, "callback( ", " );");

		return U.jsonValue(json, "openid");
	}

	public Map<String, String> getUserQQInfo(String token,  
			String openId) {
		String json = SimpleConnecter.connect(
				"https://graph.qq.com/user/get_user_info?access_token=" + token
						+ "&oauth_consumer_key=" + appId + "&openid=" + openId,
				"utf-8");

		return U.jsonToMap(json);
	}

	public String getNickName(String token, String appId, String openId) {
		return getUserQQInfo(token, openId).get("nickname");
	}

	public String getHead30Url(String token, String appId, String openId) {
		return getUserQQInfo(token, openId).get("figureurl");
	}

}
