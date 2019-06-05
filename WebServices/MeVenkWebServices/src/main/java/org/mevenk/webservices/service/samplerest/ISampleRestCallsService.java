/**
 * 
 */
package org.mevenk.webservices.service.samplerest;

import org.mevenk.webservices.controller.to.root.BasicResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author vkolisetty
 *
 */
public interface ISampleRestCallsService {

	ResponseEntity<BasicResponse> sampleGet(String basicRequestURI) throws Throwable;

}
