package vn.manage.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.manage.system.service.LoggingService;
import vn.manage.system.utils.GsonParserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class LoggingImpl implements LoggingService {
	private static final String REQUEST_ID = "request_id";

	@Override
	public void logRequest(HttpServletRequest httpServletRequest, Object body) {
		if(httpServletRequest.getRequestURI().contains("medias")) {
			return;
		}
		Object requestId = httpServletRequest.getAttribute(REQUEST_ID);
		String data = "\nLOGGING REQUEST BODY-----------------------------------\n" + "[REQUEST-ID]: " + requestId + "\n" +
			"[BODY REQUEST]: " + "\n\n" + GsonParserUtils.parseObjectToString(body) + "\n\n" +
			"LOGGING REQUEST BODY-----------------------------------\n";

		log.info(data);
	}

	@Override
	public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
		if(httpServletRequest.getRequestURI().contains("medias")) {
			return;
		}
		Object requestId = httpServletRequest.getAttribute(REQUEST_ID);
		String data = "\nLOGGING RESPONSE-----------------------------------\n" + "[REQUEST-ID]: " + requestId + "\n" +
			"[BODY RESPONSE]: " + "\n\n" + GsonParserUtils.parseObjectToString(body) + "\n\n" +
			"LOGGING RESPONSE-----------------------------------\n";

		log.info(data);
	}
}
