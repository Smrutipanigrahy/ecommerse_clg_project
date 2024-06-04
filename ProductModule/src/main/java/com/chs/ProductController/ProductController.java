package com.chs.ProductController;

import com.chs.ProductModel.Product;
import com.chs.ProductModel.VendorModule;
import com.chs.ProductService.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
@EnableDiscoveryClient
public class ProductController {
    @Autowired
    private ProductServices services;
    @Autowired
    private RestTemplate restTemplate;
    private DiscoveryClient discoveryClient;

    @Autowired
    public ProductController(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getDataFromAnotherService")
    public String getDataFromAnotherService() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("VENDOR-MODULE");

        if (serviceInstances.isEmpty()) {
            return "VENDOR-SERVICE is not available";
        }

        ServiceInstance serviceInstance = serviceInstances.get(0);
        String url = serviceInstance.getUri() + "/vendor/datas";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VendorModule[]> responseEntity = restTemplate.getForEntity(url, VendorModule[].class);
        VendorModule[] vendorDataArray = responseEntity.getBody();

        services.storeVendorData(Arrays.asList(vendorDataArray));
        return "Data from Vendor module has been stored in Product module";

    }

    @GetMapping("/getAllData")
    public ResponseEntity<List<Product>> getAllData(){
        return ResponseEntity.ok().body(this.services.getAllProducts());
    }

    @GetMapping("/getDataById/{id}")
    public ResponseEntity<?> getDataById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.services.getProductById(id));
    }

    @GetMapping("/getname/{productname}")
    public ResponseEntity<List<Product>> getDataByName(@PathVariable("productname") String product_name) {
        List<Product> filterData = services.filterDataByName(product_name);
        return ResponseEntity.ok(filterData);
    }

    @GetMapping("/range/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Product>> getPriceData(@PathVariable Double minPrice, @PathVariable Double maxPrice) {
        List<Product> filterData = services.filterDataByPrice(minPrice, maxPrice);
        return ResponseEntity.ok(filterData);
    }

    @GetMapping("/getProductsBelow20ByQuantity")
    public ResponseEntity<List<Product>> getDataByQuantity(){
        List<Product> filterData = services.getProductsBelow20ByQuantity();
        return ResponseEntity.ok(filterData);
    }

    @GetMapping("/outOfStock")
    public ResponseEntity<List<Product>> getOutOfStockProducts() {
        List<Product> outOfStockProducts = services.outOffStock();
        return ResponseEntity.ok(outOfStockProducts);
    }



}
