package com.example.productservice_proxy.services;

import com.example.productservice_proxy.clients.fakestore.client.FakeStoreClient;
import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreProductDto;
import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder,FakeStoreClient fakeStoreClient){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }
    @Override
    public Product getSingleProduct(Long productId){
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.getSingleProduct(productId);
        Product product = new Product();
        setProduct(product, fakeStoreProductDto);
        return product;
    }

    private static void setProduct(Product product, FakeStoreProductDto productDto) {
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setId(productDto.getId());
        Categories categories = new Categories();
        categories.setName(productDto.getCategory());
        product.setCategory(categories);
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
    }

    private static void setProductDto(Product productDto, FakeStoreProductDto product) {
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setId(productDto.getId());
        product.setCategory(productDto.getCategory().getName());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
    }

    @Override
    public List<Product> getAllProduct(){
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();
        List<Product> productList = new ArrayList<>();

        for(FakeStoreProductDto productDto : fakeStoreProductDtos){
            Product product = new Product();
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            product.setId(productDto.getId());
            Categories categories = new Categories();
            categories.setName(productDto.getCategory());
            product.setCategory(categories);
            product.setImageUrl(productDto.getImageUrl());
            product.setDescription(productDto.getDescription());
            productList.add(product);
        }

        return productList;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setImageUrl(product.getImageUrl());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity
                = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );

        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreProductDtoResponseEntity.getBody();
        Product product1 = new Product();
        setProduct(product1,fakeStoreProductDto1);
        return product1;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public Product addNewProduct(Product product){
        FakeStoreProductDto productDto = new FakeStoreProductDto();
        setProductDto(product,productDto);
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("http://fakestoreapi.com/products",productDto,ProductDto.class);
        Product product1 = new Product();
        setProduct(product1, productDto);
        return product;
    }

    @Override
    public Product putProduct(Long productId, Product product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setImageUrl(product.getImageUrl());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
                HttpMethod.PUT,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );
        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreProductDtoResponseEntity.getBody();
        Product product1 = new Product();
        setProduct(product1,fakeStoreProductDto1);
        return product1;
    }

    @Override
    public Product deleteProduct(Long productId){
        fakeStoreClient.deleteProduct(productId);
        return new Product();
    }
}
