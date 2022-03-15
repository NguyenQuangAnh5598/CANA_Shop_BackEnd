package com.example.cana_be.repository;

import com.example.cana_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {
    Optional<User> findUserByUsername(String user_name); //Tìm kiếm User có tồn tại trong DB không?
    Boolean existsByUsername(String user_name); //user_name đã có trong DB hay chưa, khi tạo dữ liệu
    Boolean existsByEmail(String email); //email đã có trong DB chưa

    @Query(value = "select * from users u where u.username like CONCAT('%',:userOrEmail,'%' )or u.email like CONCAT('%',:userOrEmail,'%' )",nativeQuery = true)
    List<User> findByUsernameOrEmail(@Param("userOrEmail") String userOrEmail);
}
