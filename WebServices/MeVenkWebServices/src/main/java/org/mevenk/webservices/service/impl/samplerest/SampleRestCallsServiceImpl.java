/**
 * 
 */
package org.mevenk.webservices.service.impl.samplerest;

import java.net.URI;
import java.util.Collections;

import org.mevenk.webservices.controller.to.root.BasicRequest;
import org.mevenk.webservices.controller.to.root.BasicResponse;
import org.mevenk.webservices.logger.Logger;
import org.mevenk.webservices.logger.LoggerFactory;
import org.mevenk.webservices.service.samplerest.ISampleRestCallsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author vkolisetty
 *
 */
@Service
public class SampleRestCallsServiceImpl implements ISampleRestCallsService {

	private static final Logger LOG = LoggerFactory.getlogger(SampleRestCallsServiceImpl.class);

	@Override
	public ResponseEntity<BasicResponse> sampleGet(String basicRequestURI) throws Throwable {

		HttpHeaders headers = new HttpHeaders();
		MediaType applicationJson = MediaType.APPLICATION_JSON;
		headers.setContentType(applicationJson);
		headers.setAccept(Collections.singletonList(applicationJson));

		RequestEntity<BasicRequest> requestEntity = new RequestEntity<BasicRequest>(new BasicRequest("APRIL", 25, 'A'),
				headers, HttpMethod.GET, new URI("http://localhost:13020/" + basicRequestURI));
		LOG.info("Request: {}", requestEntity);

		ResponseEntity<BasicResponse> response = new RestTemplate().exchange(requestEntity, BasicResponse.class);

		LOG.info("GET response: {}", response);
		return response;
	}

}
