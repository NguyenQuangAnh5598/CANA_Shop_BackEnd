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



//        @Query(value = "select p.* from product p join category c on p.category_id = c.id where (p.name like %:name%) and" +
//        " (c.id = :id) and (:minPrice < p.price < :maxPrice)",nativeQuery = true)
        @Query(value = "select * from product p join category c on p.category_id = c.id where (:name is null or p.name like %:name%) and" +
        " (:id is null or c.id in (:id)) and (:minPrice is null or :maxPrice is null or p.price between :minPrice and :maxPrice)",nativeQuery = true)
    List<Product> searchProduct(@Param("name") String name,@Param("id")Long id,@Param("minPrice")Long minPrice,@Param("maxPrice")Long maxPrice);

}
