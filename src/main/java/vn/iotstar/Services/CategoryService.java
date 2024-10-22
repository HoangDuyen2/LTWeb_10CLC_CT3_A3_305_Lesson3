package vn.iotstar.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.iotstar.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findByNameContaining(String name);

    long count();

    <S extends Category> S save(S entity);

    List<Category> findAll();

    Optional<Category> findById(Long aLong);

    Page<Category> findAll(Pageable pageable);
}
