package com.rainyhills.controller;

import com.rainyhills.client.model.CalculateWaterAmountRequest;
import com.rainyhills.client.model.CalculateWaterAmountResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Vadzim Mikhalenak.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RainyHillsControllerTests {

	private final static MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testCalculateWaterVolumeWithNullHillsArrayInRequest() throws Exception {

		CalculateWaterAmountRequest request = new CalculateWaterAmountRequest();
		mockMvc.perform(post("/rainyhills/calculatewater")
				.content(json(request))
				.contentType(contentType))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void testCalculateWaterVolumeWithEmptyHillsArrayInRequest() throws Exception {

		CalculateWaterAmountRequest request = new CalculateWaterAmountRequest();

		final int[] hills = {};
		request.setHills(hills);
		CalculateWaterAmountResponse expectedResponse = new CalculateWaterAmountResponse(0);
		mockMvc.perform(post("/rainyhills/calculatewater")
				.content(json(request))
				.contentType(contentType))
				.andExpect(status().isOk())
				.andExpect(content().json(json(expectedResponse)));

	}

	@Test
	public void testCalculateWaterVolumeWithSomeDataHillsArrayInRequest() throws Exception {

		CalculateWaterAmountRequest request = new CalculateWaterAmountRequest();

		final int[] hills = {3, 2, 4, 1, 2};
		request.setHills(hills);
		CalculateWaterAmountResponse expectedResponse = new CalculateWaterAmountResponse(2);
		mockMvc.perform(post("/rainyhills/calculatewater")
				.content(json(request))
				.contentType(contentType))
				.andExpect(status().isOk())
				.andExpect(content().json(json(expectedResponse)));

	}

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		mappingJackson2HttpMessageConverter = Arrays.stream(converters)
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny()
				.orElse(null);

		assertNotNull("the JSON message converter must not be null",
				mappingJackson2HttpMessageConverter);
	}

	private String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		mappingJackson2HttpMessageConverter.write(
				o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
