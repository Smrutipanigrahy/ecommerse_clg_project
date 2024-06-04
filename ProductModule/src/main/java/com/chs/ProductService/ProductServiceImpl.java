package com.chs.ProductService;

import com.chs.ProductModel.Product;
import com.chs.ProductModel.VendorModule;
import com.chs.ProductRepository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServices {

    @Autowired
    private ProductRepository repository;

    @Override
    public void storeVendorData(List<VendorModule> vendorDataList) {
        List<Product> products = convertToDataEntities(vendorDataList);
        repository.saveAll(products);
    }

    //Method to convert the Data into list
    private List<Product> convertToDataEntities(List<VendorModule> vendorDataList) {
        return vendorDataList.stream()
                .map(this::convertToDataEntity)
                .collect(Collectors.toList());
    }

    //Method to convert Vendor Module data into Product data and storing in dataEntity
    private Product convertToDataEntity(VendorModule vendorData) {
        Product dataEntity = new Product();

        dataEntity.setProductId(vendorData.getProductId());
        dataEntity.setMain_category(vendorData.getMain_category());
        dataEntity.setSub_category(vendorData.getSub_category());
        dataEntity.setProduct_name(vendorData.getProduct_name());
        dataEntity.setPrice(vendorData.getPrice());
        dataEntity.setQuantity(vendorData.getQuantity());
        dataEntity.setWarrenty(vendorData.getWarrenty());
        dataEntity.setDescription(vendorData.getDescription());
        dataEntity.setSpecification(vendorData.getSpecification());

        return dataEntity;
    }

    @Override
    public Product getProductById(String productId) {
        Optional<Product> product = this.repository.findById(productId);
        if(product.isPresent())
            return product.get();
        else
            throw new IllegalStateException("Record not found with id : " + productId);
    }

    //Method to filter data based on the Product Name
    @Override
    public List<Product> filterDataByName(String productName) {
        List<Product> products = repository.findAll();
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getProduct_name().equals(productName)) {
                filteredProducts.add(product);
            }
        }
        if (filteredProducts.isEmpty()){
            throw new IllegalStateException("Record not found with id : " + productName);// Adding a dummy product with the message
        }
        return filteredProducts;
    }

    // Method to filter data based on the minPrice and maxPrice parameters
    @Override
    public List<Product> filterDataByPrice(Double minPrice, Double maxPrice) {
        List<Product> products = repository.findAll();
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            Double price = product.getPrice();
            if (price != null && price >= minPrice && price <= maxPrice) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    @Override
    public List<Product> getProductsBelow20ByQuantity() {
        List<Product> models = repository.findAll();
        List<Product> filterData = models.stream()
                .filter(product -> (product.getQuantity())<20)
                .collect(Collectors.toList());
        return filterData;
    }

    @Override
    public List<Product> outOffStock() {
        List<Product> productModels = repository.findAll();

        List<Product> outOfStockProduct = productModels.stream()
                .filter(product -> (product.getQuantity())==0)
                .collect(Collectors.toList());
        return outOfStockProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.repository.findAll();
    }


}
