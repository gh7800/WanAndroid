package cn.shineiot.base.bean;

import java.io.Serializable;
import java.util.List;

import okhttp3.Cookie;

/**
 * @author GF63
 */
public class HttpCookie implements Serializable {

	private List<Cookie> cookies;

	public List<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
	}
}
