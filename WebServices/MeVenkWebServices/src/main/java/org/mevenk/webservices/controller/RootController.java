/**
 * 
 */
package org.mevenk.webservices.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mevenk.webservices.controller.to.root.BasicRequest;
import org.mevenk.webservices.controller.to.root.BasicResponse;
import org.mevenk.webservices.controller.to.root.WelcomeMessage;
import org.mevenk.webservices.logger.Logger;
import org.mevenk.webservices.logger.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vkolisetty
 *
 */
@RestController
public class RootController {

	private static final Logger LOG = LoggerFactory.getlogger(RootController.class);

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("/")
	private ResponseEntity<WelcomeMessage> home(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.debug("Request received @ {}", new Date());
		return new ResponseEntity<WelcomeMessage>(new WelcomeMessage(request), HttpStatus.OK);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param basicRequest
	 * @return
	 */
	@GetMapping(name = "/basicRequest", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	private ResponseEntity<BasicResponse> basicRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestBody BasicRequest basicRequest) {
		LOG.debug("Request received @ {}", new Date());
		LOG.info("Request: {}", basicRequest);
		return new ResponseEntity<BasicResponse>(
				new BasicResponse(basicRequest.getString(), basicRequest.getInteger(), basicRequest.getCharacter()),
				HttpStatus.OK);
	}

}
