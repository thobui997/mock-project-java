package vn.manage.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.manage.system.domain.CategoryRequestDto;
import vn.manage.system.domain.CategoryResponseDto;
import vn.manage.system.domain.ResponseHandler;
import vn.manage.system.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public ResponseEntity<?> postCategory(@Valid @RequestBody CategoryRequestDto req) {
    CategoryResponseDto categoryResponseDto = categoryService.createCategory(req);
    return ResponseHandler.generateResponse(HttpStatus.CREATED, categoryResponseDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id) {
    categoryService.deleteCategory(id);
    return ResponseHandler.responseSuccess(HttpStatus.OK, true);
  }
}
