/**
 * 
 */
package org.mevenk.webservices.service.impl.samplerest;

import java.net.URI;

import org.mevenk.webservices.logger.Logger;
import org.mevenk.webservices.logger.LoggerFactory;
import org.mevenk.webservices.service.samplerest.ISampleRestCallsService;
import org.springframework.http.HttpMethod;
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
	public String sampleGet() throws Throwable {
		ResponseEntity<String> response = new RestTemplate()
				.exchange(new RequestEntity<String>(HttpMethod.GET, new URI("https://www.google.com")), String.class);
		LOG.info("GET response: {}", response);
		return response.getBody();
	}

}
