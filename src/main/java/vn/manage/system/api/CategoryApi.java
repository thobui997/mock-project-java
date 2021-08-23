package vn.manage.system.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.manage.system.payload.request.CategoryRequest;
import vn.manage.system.payload.response.ApiResponse;
import vn.manage.system.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryApi {

  private final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<?> postCategory(@Valid @RequestBody CategoryRequest req) {
    return ResponseEntity.ok().body(ApiResponse.success(categoryService.createCategory(req)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(categoryService.deleteCategory(id));
  }
}
