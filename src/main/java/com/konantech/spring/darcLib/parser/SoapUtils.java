package com.konantech.spring.darcLib.parser;

import com.konantech.spring.darcLib.xml.XMLBean;
import org.apache.commons.lang.StringUtils;

public class SoapUtils {

    private SoapUtils() {
    }
    
	public static void checkAndThrow(XMLBean bean) throws Exception {
		int error = getErrorCode(bean);
		if (error == 0) {
			return;
		}
		if (100005 == error) {
			throw new Exception();
		}
		String errmsg = getErrorMessage(bean);
		throw new Exception(errmsg);
	}
	
	public static int getErrorCode(XMLBean bean) {
		String error = bean.getString("error.errorcode");
		if (StringUtils.isEmpty(error)) {
			error = bean.getString("error.errcode");
		}
		return (StringUtils.isEmpty(error)) ? 0 : Integer.parseInt(error);
	}
    
	public static String getErrorMessage(XMLBean bean) {
		String errmsg = bean.getString("error.errormsg");
		if (StringUtils.isEmpty(errmsg)) {
			errmsg = bean.getString("error.errmsg");
		}
		return errmsg;
	}

}
