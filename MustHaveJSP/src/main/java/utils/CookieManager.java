package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

	public static void makeCookie(HttpServletResponse response, String cName, String cValue, int cTime) {
		Cookie cookie = new Cookie(cName, cValue);
		cookie.setPath("/");
		cookie.setMaxAge(cTime);
		response.addCookie(cookie);
	}

	Cookie[] cookies = request.getCookies();
	if(cookies!=null) {
		for (Cookie c : cookies) {
			String cookieNmae = c.getName();
			if (cookieName.equals(cName)) {
				cookieValue = c.getValue();
			}
		}
	}
	return cookieValue;
}

public static void makeCookie(HttpServletResponse response, String cName) {
	makeCookie(response,cName,"",0);
	}
}
