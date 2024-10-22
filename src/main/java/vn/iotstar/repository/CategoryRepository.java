package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.iotstar.Entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    Cach 1: ghi de len mot phuong thuc moi
//    @Query("SELECT c from Category c where c.name = :name")
//    List<Category> findByNameInCategory(String name);

//    Cach 2
    List<Category>findByNameContaining(String name);

}
