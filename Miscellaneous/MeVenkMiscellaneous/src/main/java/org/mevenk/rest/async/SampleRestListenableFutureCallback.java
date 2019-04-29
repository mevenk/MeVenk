package org.mevenk.rest.async;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * 
 * @author vkolisetty
 *
 */
class SampleRestListenableFutureCallback implements ListenableFutureCallback<ResponseEntity<String>> {

	@Override
	public void onSuccess(ResponseEntity<String> result) {
		System.out.println("Success: @ " + new Date() + "|||" + result);

	}

	@Override
	public void onFailure(Throwable ex) {
		System.out.println("FAIL @ " + new Date());
		ex.printStackTrace();

	}

}