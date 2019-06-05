/**
 * 
 */
package org.mevenk.webservices.controller.samplerest;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mevenk.webservices.controller.to.root.BasicResponse;
import org.mevenk.webservices.logger.Logger;
import org.mevenk.webservices.logger.LoggerFactory;
import org.mevenk.webservices.service.samplerest.ISampleRestCallsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vkolisetty
 *
 */
@RestController
public class SampleRestCallsController {

	private static final Logger LOG = LoggerFactory.getlogger(SampleRestCallsController.class);

	@Autowired
	private ISampleRestCallsService sampleRestCallsService;

	@GetMapping("/sampleGet")
	public ResponseEntity<BasicResponse> sampleGet(@RequestParam("basicRequestURI") String basicRequestURI,
			HttpServletRequest request, HttpServletResponse response) throws Throwable {
		LOG.info("Request received @ {}", new Date());
		return sampleRestCallsService.sampleGet(basicRequestURI);
	}

}
