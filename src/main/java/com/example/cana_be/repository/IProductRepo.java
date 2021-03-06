package com.example.cana_be.repository;

import com.example.cana_be.model.Category;
import com.example.cana_be.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product,Long> {
    @Query(value = "select * from product p join category c on p.category_id = c.id and c.category_name like CONCAT('%', :categoryName, '%')", nativeQuery = true)
    public List<Product> findProductByCategoryName(@Param("categoryName") String categoryName);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    @Modifying
    @Query(value = "select * from product p where p.name like %:name%",nativeQuery=true)
    List<Product> findByName(String name);

        @Query(value = "select * from product p join category c on p.category_id = c.id where (:name is null or p.name like %:name%) and" +
        " (:id is null or c.id in (:id)) and (:minPrice is null or  p.price >= :minPrice) and (:maxPrice is null or :maxPrice >= p.price)",nativeQuery = true)
    List<Product> searchProduct(@Param("name") String name,@Param("id")Long id,@Param("minPrice")Long minPrice,@Param("maxPrice")Long maxPrice);


    @Query(value = "select p.id,description,image,manufacturer,name,price,p.quantity,category_id,sum(order_quantity)\n" +
            "from product p join order_detail od\n" +
            "on p.id = od.product_id \n" +
            "group by   p.id,description,image,manufacturer,name,price,p.quantity,category_id\n" +
            "order by sum(order_quantity) desc,price asc \n" +
            "limit 4; ",nativeQuery=true)
    List<Product> top3BestSale();
}
