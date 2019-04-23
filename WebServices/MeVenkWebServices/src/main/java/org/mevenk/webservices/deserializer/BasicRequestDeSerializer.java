/**
 * 
 */
package org.mevenk.webservices.deserializer;

import java.io.IOException;

import org.mevenk.webservices.controller.to.root.BasicRequest;
import org.mevenk.webservices.logger.Logger;
import org.mevenk.webservices.logger.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vkolisetty
 *
 */
public class BasicRequestDeSerializer extends JsonDeserializer<BasicRequest> {

	private static final Logger LOG = LoggerFactory.getlogger(BasicRequestDeSerializer.class);

	@Override
	public BasicRequest deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		ObjectMapper sortedMapper = new ObjectMapper();
		Object obj = sortedMapper.treeToValue(node, Object.class);
		LOG.info("Request: {}", sortedMapper.writeValueAsString(obj));

		if (node.size() != 3) {
			throw new IllegalArgumentException("Improper request data");
		}

		String string = node.get("string").asText();
		int integer = node.get("integer").asInt();
		char character = node.get("character").asText().charAt(0);

		return new BasicRequest(string, integer, character);
	}

}
