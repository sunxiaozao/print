package com.lubian.cpf.common.filter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * we add session listener because of flash cookie bug *
 */
public class SessionListener implements HttpSessionListener {
	private static Map<String, HttpSession> sessionMaps = new ConcurrentHashMap<String, HttpSession>();

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		String sessionId = session.getId();
		sessionMaps.put(sessionId, session);

	}

	public void sessionDestroyed(HttpSessionEvent event) {
		String sessionId = event.getSession().getId();
		sessionMaps.remove(sessionId);
	}


	public static Map<String, HttpSession> getSessionMaps() {
		return sessionMaps;
	}

	public static HttpSession getSessionByID(String key) {
		return sessionMaps.get(key);
	}
}
