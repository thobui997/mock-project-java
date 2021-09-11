package vn.manage.system.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import vn.manage.system.domain.SystemSettingRequestDto;
import vn.manage.system.domain.SystemSettingResponseDto;
import vn.manage.system.models.SystemSetting;
import vn.manage.system.service.SystemSettingService;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SystemSettingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private SystemSettingService systemSettingService;

	private final String URL = "/api/v1/system-settings";


	@Test
	void postSystemSettingsSuccess() throws Exception {
		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setKey("key1");
		requestDto.setValue("value1");
		requestDto.setType("string");
		requestDto.setAllowValues(List.of("value1", "value2"));
		requestDto.setCategories(List.of("category1"));

		SystemSettingResponseDto mockResponse = new SystemSettingResponseDto(requestDto);
		mockResponse.setId(1);

		given(systemSettingService.createSystemSetting(any())).willReturn(mockResponse);

		mockMvc
			.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.data.id", is(1)))
			.andExpect(jsonPath("$.data.key", is("key1")))
			.andExpect(jsonPath("$.data.value", is("value1")))
			.andExpect(jsonPath("$.data.type", is("string")))
			.andExpect(jsonPath("$.data.allowOverride", is(false)))
			.andExpect(jsonPath("$.data.allowValues", is(List.of("value1", "value2"))))
			.andExpect(jsonPath("$.data.categories", is(List.of("category1"))));

		verify(systemSettingService).createSystemSetting(any());
	}

	@Test
	void postSystemSettingsFailedWhenRequestNotContainTheKeyField() throws Exception {
		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setValue("value1");
		requestDto.setType("string");
		requestDto.setAllowValues(List.of("value1", "value2"));
		requestDto.setCategories(List.of("category1"));

		mockMvc
			.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message", is("Key is required")));


		verify(systemSettingService, never()).createSystemSetting(any());
	}

	@Test
	void postSystemSettingsFailedWhenRequestNotContainTheValueField() throws Exception {
		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setKey("key1");
		requestDto.setType("string");
		requestDto.setAllowValues(List.of("value1", "value2"));
		requestDto.setCategories(List.of("category1"));

		mockMvc
			.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message", is("Value is required")));


		verify(systemSettingService, never()).createSystemSetting(any());
	}

	@Test
	void putSystemSettingsSuccess() throws Exception {
		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setKey("key1");
		requestDto.setValue("value1");
		requestDto.setType("string");
		requestDto.setAllowValues(List.of("value1", "value2"));
		requestDto.setCategories(List.of("category1"));

		SystemSettingResponseDto mockResponse = new SystemSettingResponseDto(requestDto);
		mockResponse.setId(1);

		given(systemSettingService.updatedSystemSetting(any(), any())).willReturn(mockResponse);

		mockMvc
			.perform(put(URL + "/{id}", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.data.id", is(1)))
			.andExpect(jsonPath("$.data.key", is("key1")))
			.andExpect(jsonPath("$.data.value", is("value1")))
			.andExpect(jsonPath("$.data.type", is("string")))
			.andExpect(jsonPath("$.data.allowOverride", is(false)))
			.andExpect(jsonPath("$.data.allowValues", is(List.of("value1", "value2"))))
			.andExpect(jsonPath("$.data.categories", is(List.of("category1"))));

		verify(systemSettingService).updatedSystemSetting(any(), any());
	}

	@Test
	void putSystemSettingsFailedWhenRequestNotContainTheKeyField() throws Exception {
		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setValue("value1");
		requestDto.setType("string");
		requestDto.setAllowValues(List.of("value1", "value2"));
		requestDto.setCategories(List.of("category1"));

		mockMvc
			.perform(put(URL + "/{id}", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.message", is("Key is required")));

		verify(systemSettingService, never()).updatedSystemSetting(any(), any());
	}

	@Test
	void putSystemSettingsFailedWhenRequestNotContainTheValueField() throws Exception {
		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setKey("key 1");
		requestDto.setType("string");
		requestDto.setAllowValues(List.of("value1", "value2"));
		requestDto.setCategories(List.of("category1"));

		mockMvc
			.perform(put(URL + "/{id}", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.message", is("Value is required")));

		verify(systemSettingService, never()).updatedSystemSetting(any(), any());
	}

	@Test
	void getSystemSettingByIdSuccess() throws Exception {

		Pageable paging = PageRequest.of(0, 10);


		SystemSetting systemSetting = new SystemSetting();
		systemSetting.setId(1);
		systemSetting.setKey("key1");
		systemSetting.setValue("value1");
		systemSetting.setDataType("string");
		systemSetting.setAllowValues("value1,value2");

		List<SystemSetting> systemSettingList = List.of(systemSetting);

		Page<SystemSetting> systemSettingPage = new PageImpl<>(systemSettingList, paging, 1L);

		given(systemSettingService.getAllSystemSetting(any(), any(), any())).willReturn(systemSettingPage);

		mockMvc
			.perform(get(URL))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.data[0].id", is(1)))
			.andExpect(jsonPath("$.data[0].key", is("key1")))
			.andExpect(jsonPath("$.data[0].type", is("string")))
			.andExpect(jsonPath("$.data[0].allowValues", is(List.of("value1", "value2"))))
			.andExpect(jsonPath("$.paging.size", is(10)))
			.andExpect(jsonPath("$.paging.currentPage", is(0)))
			.andExpect(jsonPath("$.paging.totalPages", is(1)))
			.andExpect(jsonPath("$.totalCount", is(1)));

		verify(systemSettingService).getAllSystemSetting(any(), any(), any());
	}

	@Test
	void deleteSystemSettingByIdSuccess() throws Exception {
		mockMvc
			.perform(delete(URL + "/{id}", "1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.success", is(true)));

		verify(systemSettingService).deleteSystemSetting(any());
	}
}