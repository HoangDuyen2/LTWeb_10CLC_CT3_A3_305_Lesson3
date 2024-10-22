package vn.iotstar.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="categories")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private Long id;

    @Column(name = "categoryName",columnDefinition = "VARCHAR(200) NOT NULL")
    @NotEmpty(message = "Không được phép rỗng")
    private String name;

    //    Trong SQLServer ta dung max nhung trong mysql ta dung longtext
    @Column(name = "images", columnDefinition = "LONGTEXT NULL")
    private String images;

    @Column(name = "status")
    private int status;
}
