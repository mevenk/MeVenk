/**
 * 
 */
package org.mevenk.rest.async;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

/**
 * @author vkolisetty
 *
 */
public class SampleRestAsync {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		sendAsyncRequest();

	}

	private static void sendAsyncRequest() {

		System.out.println("Sending request @ " + new Date());
		ListenableFuture<ResponseEntity<String>> exchange = new AsyncRestTemplate()
				.exchange("http://VENKATESH-NUC:13020", HttpMethod.GET, null, String.class);
		System.out.println("Request sent @ " + new Date());
		exchange.addCallback(new SampleRestListenableFutureCallback());
		System.out.println("Added callback @ " + new Date());

		System.out.println("Adding CompletableFuture @ " + new Date());
		CompletableFuture<ResponseEntity<String>> completable = exchange.completable();
		completable.whenComplete(new SampleRestBiConsumer());
		System.out.println("Added CompletableFuture @ " + new Date());

		System.out.println("Method complete!!");

	}

}
