/**
 * 
 */
package org.mevenk.webservices.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mevenk.webservices.controller.to.root.BasicRequest;
import org.mevenk.webservices.controller.to.root.BasicResponse;
import org.mevenk.webservices.controller.to.root.WelcomeMessage;
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

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("/")
	private ResponseEntity<WelcomeMessage> home(HttpServletRequest request, HttpServletResponse response) {
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
		return new ResponseEntity<BasicResponse>(
				new BasicResponse(basicRequest.getString(), basicRequest.getInteger(), basicRequest.getCharacter()),
				HttpStatus.OK);
	}

}
