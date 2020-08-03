package com.mastercard.challenge;

import com.mastercard.challenge.controllers.CityController;
import com.mastercard.challenge.services.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertEquals;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CityController.class)
public class CityControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CityService cityService;

	@Test
	public void testConnected() throws Exception {

		Mockito.when(
				cityService.areCitiesConnected(Mockito.matches("a"),
						Mockito.matches("b"))).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/connected?origin=a&destination=b").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "yes";
		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public void testNotConnected() throws Exception {

		Mockito.when(
				cityService.areCitiesConnected(Mockito.matches("a"),
						Mockito.matches("b"))).thenReturn(false);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/connected?origin=a&destination=b").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "no";
		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public void testInvalidOrigin() throws Exception {

		Mockito.when(
				cityService.areCitiesConnected(Mockito.matches("a"),
						Mockito.anyString())).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/connected?origin=b&destination=c").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "no";
		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public void testInvalidDestination() throws Exception {

		Mockito.when(
				cityService.areCitiesConnected(Mockito.anyString(),
						Mockito.matches("b"))).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/connected?origin=b&destination=c").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "no";
		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public void testNoOrigin() throws Exception {

		Mockito.when(
				cityService.areCitiesConnected(Mockito.anyString(),
						Mockito.anyString())).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/connected?destination=c").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "no";
		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public void testNoDestination() throws Exception {

		Mockito.when(
				cityService.areCitiesConnected(Mockito.anyString(),
						Mockito.anyString())).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/connected?origin=a").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "no";
		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public void testNoOriginDestination() throws Exception {

		Mockito.when(
				cityService.areCitiesConnected(Mockito.anyString(),
						Mockito.anyString())).thenReturn(false);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/connected").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "no";
		assertEquals(expected, result.getResponse().getContentAsString());
	}

}
