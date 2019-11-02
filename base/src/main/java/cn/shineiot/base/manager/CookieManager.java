package cn.shineiot.base.manager;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @author GF63
 */
public class CookieManager implements CookieJar {

	Context context;
	private PersistentCookieStore cookieStore;

	public CookieManager(Context context) {
		this.context = context;
		cookieStore = new PersistentCookieStore(context);
	}

	@Override
	public List<Cookie> loadForRequest(HttpUrl url) {
		List<Cookie> cookies = cookieStore.get(url);
		return cookies;
	}

	@Override
	public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
		if (cookies != null && cookies.size() > 0) {
			for (Cookie item : cookies) {
				cookieStore.add(url, item);
			}
		}
	}

}
