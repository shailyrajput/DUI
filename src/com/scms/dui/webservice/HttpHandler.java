package com.scms.dui.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.graphics.Bitmap;

public class HttpHandler {
	private static HttpHandler obj = null;

	HttpClient httpclient;
	BasicCookieStore cookieStore;
	HttpContext httpContext;

	private HttpHandler() {
		httpclient = new DefaultHttpClient();
		cookieStore = new BasicCookieStore();
		httpContext = new BasicHttpContext();

	}

	public static HttpHandler getInstance() {
		if (obj == null) {
			obj = new HttpHandler();
		}

		return obj;
	}

	public HttpClient getHttpClient() {
		return httpclient;
	}

	public BasicCookieStore getBasicCookieStore() {
		return cookieStore;
	}

	public HttpContext getHttpContext() {
		return httpContext;
	}

}
