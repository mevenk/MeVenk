/**
 * 
 */
package org.mevenk.webservices.controller.samplerest;

import org.mevenk.webservices.logger.Logger;
import org.mevenk.webservices.logger.LoggerFactory;
import org.mevenk.webservices.service.samplerest.ISampleRestCallsService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
