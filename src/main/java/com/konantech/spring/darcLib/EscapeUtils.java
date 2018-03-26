package com.konantech.spring.darcLib;

public class EscapeUtils {
	
	private EscapeUtils() {}

    public static String escapeXml(String str) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        StringBuilder buffer = new StringBuilder((int) (len + (len * 0.1)));
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            switch (c) {
            case '&':
            	buffer.append("&amp;");
            	break;
            case '<':
            	buffer.append("&lt;");
            	break;
            case '>':
            	buffer.append("&gt;");
            	break;
           	default:
           		buffer.append(c);
           		break;
            }
        }
        return buffer.toString();
    }

}
