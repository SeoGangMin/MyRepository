package com.cattle.framework.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.util.WebUtils;

/**
 * Http Utils
 */
public class WebUtil {
	private static Log LOG = LogFactory.getLog(WebUtil.class);

	/* IP 없을 때의 대체값 */
	public static final String MISSING_IP_ADDR = "000.000.000.000";

	/* MAC 없을 때의 대체값 */
	public static final String MISSING_MAC_ADDR = "FFFFFFFFFFFF";

	private static final String LOCALHOST_IP = "127.0.0.1";

	/**
	 * 헤더의 WL-Proxy-Client-IP을 통해 얻을 수 없는 IP.
	 */
	private static final String UNKNOWN = "unknown";

	private static String listenAddress;
	private static Integer listenPort;

	// ---------------------------------------------------------- Helper Methods : Remote Address
	/**
	 * Client의 MAC Address를 제공 합니다.
	 * 
	 * @param request
	 * @return
	 */
	public static String[] getMACAddress(HttpServletRequest request) {

		String macAddr = (String) request.getHeader("Client-MAC-Address-Info");
		if (LOG.isDebugEnabled()) {
			LOG.debug("Client-MAC-Address-Info: " + macAddr);
		}

		if (StringUtils.isBlank(macAddr))
			return new String[] { MISSING_MAC_ADDR };

		String[] rets = StringUtils.split(macAddr.trim(), ",", 0);

		int len = rets.length;
		if (len > 1) {
			Set<String> macSet = new HashSet<String>();
			for (int i = 0; i < len; i++) {
				macSet.add(rets[i]);
			}

			Object[] objMac = macSet.toArray();
			String[] resultMac = new String[objMac.length];
			for (int i = 0; i < resultMac.length; i++) {
				resultMac[i] = (String) objMac[i];
			}
			return resultMac;
		}
		else {
			return rets;
		}

	}

	/**
	 * Client의 범용 IP를 제공 합니다.
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealClientIp(HttpServletRequest request) {

		String strIP = request.getHeader("WL-Proxy-Client-IP");
		if (LOG.isDebugEnabled()) {
			LOG.debug("WL-Proxy-Client-IP: " + strIP);
		}

		if (StringUtils.isBlank(strIP) || UNKNOWN.equalsIgnoreCase(strIP)) { //unknown인 경우에도 설정.
			strIP = (String) request.getHeader("Client-Proxy-Address-Info");

			if (LOG.isDebugEnabled())
				LOG.debug("Client-Proxy-Address-Info: " + strIP);
		}

		// WEB서버에서 IP를 가져온다.
		if (StringUtils.isBlank(strIP) || UNKNOWN.equalsIgnoreCase(strIP)) { //unknown인 경우에도 설정.
			strIP = request.getHeader("Proxy-Client-IP");

			if (LOG.isDebugEnabled())
				LOG.debug("Proxy-Client-IP: " + strIP);
		}

		if (StringUtils.isBlank(strIP) || UNKNOWN.equalsIgnoreCase(strIP)) { //unknown인 경우에도 설정.
			strIP = request.getRemoteAddr();

			if (LOG.isDebugEnabled())
				LOG.debug("remoteAddr: " + strIP);
		}

		if (LOG.isDebugEnabled())
			LOG.debug("최종리얼IP:" + strIP);

		return strIP;
	}

	/**
	 * Client의 Proxy IP를 제공 합니다.
	 * 
	 * @param request
	 * @return
	 */
	public static String[] getProxyIp(HttpServletRequest request) {

		String strIP = (String) request.getHeader("Client-Proxy-Address-Info");
		if (LOG.isDebugEnabled()) {
			LOG.debug("Client-Proxy-Address-Info: " + strIP);
		}

		// WEB서버에서 IP를 가져온다.
		if (StringUtils.isBlank(strIP)) {
			strIP = request.getHeader("WL-Proxy-Client-IP");
			if (LOG.isDebugEnabled()) {
				LOG.debug("WL-Proxy-Client-IP: " + strIP);
			}
		}

		// WEB서버에서 IP를 가져온다.
		if (StringUtils.isBlank(strIP)) {
			strIP = request.getHeader("Proxy-Client-IP");
			if (LOG.isDebugEnabled()) {
				LOG.debug("Proxy-Client-IP: " + strIP);
			}
		}

		if (StringUtils.isBlank(strIP)) {
			strIP = request.getRemoteAddr();
		}

		String[] rets = StringUtils.split(strIP.trim(), ",", 0);

		// "127.0.0.1" 이면 실IP를 설정한다.
		if (rets != null && rets.length > 0 && LOCALHOST_IP.equals(rets[0])) {
			try {
				rets[0] = InetAddress.getLocalHost().getHostAddress();
			}
			catch (Exception e) {
				// 무시.
			}
		}

		return rets;
	}

	/**
	 * 논리 하드디스크 Serial Number 를 제공 합니다.
	 * 
	 * @param request
	 * @return
	 */
	public static String getHddSerial(HttpServletRequest request) {

		return (String) request.getHeader("Client-Logical-HDD-Serial-Info");
	}

	/**
	 * WEB 서버를 통한 요청일때는 WEB Server의 IP를 그렇지 않을경우는 요청 Client IP를 반환한다.
	 * 
	 * @param request
	 * @return
	 */
	public static String getWEBServerIp(HttpServletRequest request) {
		return request.getRemoteAddr();
	}

	/**
	 * 고객 PC의 IP를 제공 합니다.
	 * 
	 * @return
	 */
	public static String[] getClientIp(HttpServletRequest request) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("HEADER:Client-IP-Address-Info: " + request.getHeader("Client-IP-Address-Info"));
			LOG.debug("HEADER:Proxy-Client-IP: " + request.getHeader("Proxy-Client-IP"));
			LOG.debug("REQUEST:getRemoteAddr: " + request.getRemoteAddr());
		}

		// Initech 보안모듈에서 IP를 가져온다.
		String strIP = request.getHeader("Client-IP-Address-Info");

		// WEB서버에서 IP를 가져온다.
		if (StringUtils.isBlank(strIP)) {
			strIP = request.getHeader("Proxy-Client-IP");
		}

		if (StringUtils.isBlank(strIP)) {
			strIP = request.getRemoteAddr();
		}

		String[] rets = StringUtils.split(strIP.trim(), ",", 0);

		// "127.0.0.1" 이면 실IP를 설정한다.
		if (rets != null && rets.length > 0 && LOCALHOST_IP.equals(rets[0])) {
			try {
				rets[0] = InetAddress.getLocalHost().getHostAddress();
			}
			catch (Exception e) {
				// 무시.
			}
		}

		if (rets != null && rets.length > 1) {
			Set<String> ipSet = new HashSet<String>();
			int len = rets.length;
			for (int i = 0; i < len; i++) {
				ipSet.add(rets[i]);
			}

			Object[] objAdr = ipSet.toArray();
			String[] resultAdr = new String[objAdr.length];
			for (int i = 0; i < resultAdr.length; i++) {
				resultAdr[i] = (String) objAdr[i];
			}
			return resultAdr;
		}
		else {
			return rets;
		}
	}

	/**
	 * 시스템 식별번호. 각 물리적인 서버의 마지막 IP 3자리(IPv6는 4자리)).
	 * 
	 * @return
	 */
	public static String getServerId(HttpServletRequest request) {

		String systemId = "";
		try {
			String webServerIp = getWEBServerIp(request);
			if (StringUtils.isNotEmpty(webServerIp)) {
				systemId = webServerIp.substring(webServerIp.lastIndexOf('.') + 1);
				if (StringUtils.isEmpty(systemId)) {
					systemId = webServerIp.substring(webServerIp.lastIndexOf(':') + 1);
				}

				if (systemId.length() > 3)
					systemId = systemId.substring(systemId.length() - 3);
			}
			else {
				systemId = "000";
				LOG.error("Cannot read host address");
			}
		}
		catch (Exception e) {
			LOG.error("UnknownHostException", e);
		}

		return systemId;
	}

	/**
	 * 실행 WAS IP 를 반환한다.
	 * 
	 * @return
	 */
	public static String getWasIp() {

		if (StringUtils.isEmpty(listenAddress)) {
			InitialContext ctx = null;
			javax.management.MBeanServer server = null;
			javax.management.MBeanServerConnection conn = null;
			javax.management.ObjectName service = null;

			try {
				ctx = new InitialContext();
				server = (MBeanServer) ctx.lookup("java:comp/env/jmx/runtime");
				conn = (MBeanServerConnection) server;

				service = new ObjectName("com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");

				ObjectName serverRuntime = (ObjectName) (conn.getAttribute(service, "ServerRuntime"));

				listenAddress = (String) conn.getAttribute(serverRuntime, "ListenAddress");
				if (StringUtils.isNotEmpty(listenAddress) && listenAddress.indexOf("/") > -1) {
					listenAddress = listenAddress.substring(listenAddress.lastIndexOf("/") + 1);
				}

				if (LOG.isDebugEnabled()) {
					LOG.debug("WAS ListenAddress : " + listenAddress);
				}

			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (ctx != null)
						ctx.close();
				}
				catch (Exception ig) {
				}
			}
		}

		return listenAddress;
	}

	/**
	 * 실행 WAS PORT 를 반환한다.
	 * 
	 * @return
	 */
	public static Integer getWasPort() {

		if (listenPort == null) {
			InitialContext ctx = null;
			javax.management.MBeanServer server = null;
			javax.management.MBeanServerConnection conn = null;
			javax.management.ObjectName service = null;

			try {
				ctx = new InitialContext();
				server = (MBeanServer) ctx.lookup("java:comp/env/jmx/runtime");
				conn = (MBeanServerConnection) server;

				service = new ObjectName("com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");

				ObjectName serverRuntime = (ObjectName) (conn.getAttribute(service, "ServerRuntime"));

				listenPort = (Integer) conn.getAttribute(serverRuntime, "ListenPort");

				if (LOG.isDebugEnabled()) {
					LOG.debug("WAS ListenPort : " + listenPort);
				}

			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (ctx != null)
						ctx.close();
				}
				catch (Exception ig) {
				}
			}
		}

		return listenPort;
	}

	/**
	 * Request 정보를 출력
	 * 
	 * @param request
	 */
	public static void printRequestInfo(HttpServletRequest request) {

		if (LOG.isDebugEnabled()) {

			StringBuilder sbLog = new StringBuilder();
			sbLog.append("================[Begin Request Info]=================\n");

			Enumeration<?> e = request.getAttributeNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				sbLog.append("Attribute : " + key + "=" + request.getAttribute(key) + "\n");
			}
			sbLog.append("-----------------------------------------------------\n");

			e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				sbLog.append("Parameter : " + key + "=" + request.getParameter(key) + "\n");
			}
			sbLog.append("-----------------------------------------------------\n");

			e = request.getHeaderNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				sbLog.append("Header : " + key + "=" + request.getHeader(key) + "\n");
			}
			sbLog.append("-----------------------------------------------------\n");

			e = request.getSession().getServletContext().getInitParameterNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				sbLog.append("ServletContext InitParameter : " + key + "=" + request.getSession().getServletContext().getInitParameter(key) + "\n");
			}
			sbLog.append("-----------------------------------------------------\n");

			ServletContext ctx = request.getSession().getServletContext();
			e = ctx.getAttributeNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				sbLog.append("Context : " + key + "=" + ctx.getAttribute(key) + "\n");
			}

			sbLog.append("================[End Request Info]=================\n");

			LOG.debug(sbLog.toString());
		}

	}

	// ---------------------------------------------------------- Helper Methods : Session
	/**
	 * Session에 저장된 key의 값을 반환한다.
	 */
	public static Object getSessionObject(HttpServletRequest request, String key) {
		return org.springframework.web.util.WebUtils.getSessionAttribute(request, key);
	}

	/**
	 * Session에 key의 값을 저장한다.
	 */
	public static void setSessionObject(HttpServletRequest request, String key, Object value) {
		WebUtils.setSessionAttribute(request, key, value);
	}

	/**
	 * Session에서 key의 값을 삭제한다.
	 */
	public static void removeSessionObject(HttpServletRequest request, String key) {
		WebUtils.setSessionAttribute(request, key, null);
	}

	// ---------------------------------------------------------- Helper Methods : HttpURLConnection
	/**
	 * HttpURLConnection으로 호출한 데이터를 반환한다.
	 * @param requestUrl : 호출할 URL
	 * @return
	 */
	public static String getHttpURLConnection(String requestUrl) {
		return getHttpURLConnection(requestUrl, "GET", null);
	}
	/**
	 * HttpURLConnection으로 호출한 데이터를 반환한다.
	 * @param requestUrl : 호출할 URL
	 * @param method : 호출시 적용할 Method
	 * @param requestParams : 전송할 POST 파라미터(name=value의 List)
	 * @return
	 */
	public static String getHttpURLConnection(String requestUrl, String method, List<Map<String, Object>> requestParams) {
		StringBuffer receiveMsg = new StringBuffer();
		try {
			// -- receive servlet connect
			URL servletUrl = new URL(requestUrl);
			HttpURLConnection uc = (HttpURLConnection) servletUrl.openConnection();
			uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			uc.setRequestProperty("User-Agent", "Mozilla/5.0");
			uc.setRequestMethod( requestParams != null && requestParams.size() > 0 ? "POST" : method );
			uc.setDoOutput(true);
			uc.setDoInput(true);
			uc.setUseCaches(false);
			uc.connect();

			// POST 전송
			if (requestParams != null && requestParams.size() > 0) {
				String param = "";
				for (int i = 0; i < requestParams.size(); i++) {
					Map<String, Object> map = (Map<String, Object>) requestParams.get(i);
					param += map.get("name").toString() + "=" + URLEncoder.encode(map.get("value").toString(), "UTF-8") + ((i + 1) < requestParams.size() ? "&" : "");
				}
				OutputStream out = uc.getOutputStream();
				out.write(param.getBytes("UTF-8"));
				out.flush();
				out.close();
			}

			// -- Network error check
			if (uc.getResponseCode() == HttpURLConnection.HTTP_OK) {
				String currLine = new String();
				// UTF-8인 경우
				BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
				while ((currLine = in.readLine()) != null) {
					receiveMsg.append(currLine).append("\r\n");
				}
				in.close();
			}
			uc.disconnect();
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
		}
		return receiveMsg.toString();
	}

}
