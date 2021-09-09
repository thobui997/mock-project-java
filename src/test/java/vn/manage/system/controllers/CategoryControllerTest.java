package vn.manage.system.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import vn.manage.system.domain.CategoryRequestDto;
import vn.manage.system.domain.CategoryResponseDto;
import vn.manage.system.service.CategoryService;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService categoryService;

	@Autowired
	private ObjectMapper objectMapper;

	private final String URL = "/api/v1/categories";

	@Test
	void testPostCategorySuccess() throws Exception {
		CategoryRequestDto requestDto = new CategoryRequestDto();
		requestDto.setName("tho");
		requestDto.setDescription("test description");

		CategoryResponseDto responseDto = new CategoryResponseDto(requestDto);
		responseDto.setId(1);

		when(categoryService.createCategory(any())).thenReturn(responseDto);

		mockMvc
			.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.data.id", is(1)))
			.andExpect(jsonPath("$.data.name", is("tho")))
			.andExpect(jsonPath("$.data.description", is("test description")));

		verify(categoryService).createCategory(any());
	}

	@Test
	void testPostCategoryFailed() throws Exception {
		CategoryRequestDto requestDto = new CategoryRequestDto();
		requestDto.setDescription("test description");

		CategoryResponseDto responseDto = new CategoryResponseDto(requestDto);
		responseDto.setId(1);

		when(categoryService.createCategory(any())).thenReturn(responseDto);

		mockMvc
			.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestDto)))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.message", is("Name is required")));

		verify(categoryService, never()).createCategory(any());

	}

	@Test
	void testDeleteCategoryByIdSuccess() throws Exception {

		mockMvc
			.perform(delete(URL + "/{id}", "1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.success", is(true)));

		verify(categoryService).deleteCategory(any());
	}
}