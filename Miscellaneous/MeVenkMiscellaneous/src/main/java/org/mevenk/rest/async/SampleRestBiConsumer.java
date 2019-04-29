package org.mevenk.rest.async;

import java.util.Date;
import java.util.function.BiConsumer;

import org.springframework.http.ResponseEntity;

/**
 * 
 * @author vkolisetty
 *
 */
class SampleRestBiConsumer implements BiConsumer<ResponseEntity<String>, Throwable> {

	@Override
	public void accept(ResponseEntity<String> response, Throwable throwable) {
		System.out.println("Completed @ " + new Date());
		System.out.println("Response: " + response);
		System.out.println("Throwable: " + throwable);
	}

}