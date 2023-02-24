package utils;

import javax.servelt.jsp.JspWriter;

public class JSFunction {

private static void alterLocation(String msg, String url, javax.servlet.jsp.JspWriter out) {
	try {
		String script = ""
				+"<script>"
				+"	alert('"+msg+"');"
				+"	location.href='"+url+"';"
				+"<script>";
		out.println(script);
	}
	catch (Exception e) {}
}
public static void alertBack(String msg, JspWriter out) {
	try {
		String script =""
				+"<script>"
				+"	alert('"+msg+"');"
				+"	history.back();"
				+"<script>";
		out.println(script);
	}
	catch (Exception e) {}
}
}
