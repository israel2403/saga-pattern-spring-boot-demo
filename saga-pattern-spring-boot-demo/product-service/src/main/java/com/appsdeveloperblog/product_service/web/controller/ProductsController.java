package com.appsdeveloperblog.product_service.web.controller;

import com.appsdeveloperblog.product_service.dto.ProductCreationRequest;
import com.appsdeveloperblog.product_service.dto.ProductCreationResponse;
import com.appsdeveloperblog.product_service.service.ProductService;
import com.example.core.dto.Product;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
  private final ProductService productService;

  public ProductsController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Product> findAll() {
    return productService.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductCreationResponse save(@RequestBody @Valid ProductCreationRequest request) {
    var product = new Product();
    BeanUtils.copyProperties(request, product);
    Product result = productService.save(product);

    var productCreationResponse = new ProductCreationResponse();
    BeanUtils.copyProperties(result, productCreationResponse);
    return productCreationResponse;
  }
}
