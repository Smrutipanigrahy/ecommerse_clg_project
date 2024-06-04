package com.chs.ProductService;

import com.chs.ProductModel.Product;
import com.chs.ProductModel.VendorModule;

import java.util.List;
public interface ProductServices {

    public void storeVendorData(List<VendorModule> vendorDataList);
    Product getProductById(String productId);
    List<Product> filterDataByName(String productName);
    List<Product> filterDataByPrice(Double minPrice, Double maxPrice);
    public List<Product> getProductsBelow20ByQuantity();
    public List<Product> outOffStock();

    List<Product> getAllProducts();
}




