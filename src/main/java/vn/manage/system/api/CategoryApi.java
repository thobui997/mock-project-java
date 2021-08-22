package vn.manage.system.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.manage.system.payload.request.CategoryRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryApi {

    @PostMapping
    public ResponseEntity<?> postCategory(@Valid @RequestBody CategoryRequest req) {
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok().body("");
    }
}
