package com.example.itemservice;

import com.example.itemservice.dto.FilterProductRequest;
import com.example.itemservice.entity.Product;
import com.example.itemservice.repository.ProductFilterRepository;
import com.example.itemservice.vo.ResponseProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/product-service")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductFilterRepository productFilterRepository;

    @GetMapping("/health_check")
    public String status(HttpServletRequest request){
        return String.format("Server on port %s", request.getServerPort());
    }

    @GetMapping("/products")
    public ResponseEntity<List<ResponseProduct>> findAllProducts(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getAllProducts());
    }

    @GetMapping("/products_filter")
    public ResponseEntity<List<Product>> filter(
            @RequestParam(required = false) List<String> productMadeBy,
            @RequestParam(required = false) List<String> productWeightRange,
            @RequestParam(required = false) List<String> productPriceRange) {

        FilterProductRequest filterProductRequest = new FilterProductRequest();
        filterProductRequest.setProductMadeBy(productMadeBy);
        filterProductRequest.setProductWeightRange(productWeightRange);
        filterProductRequest.setProductPriceRange(productPriceRange);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productFilterRepository.filterProducts(filterProductRequest));
    }
}
