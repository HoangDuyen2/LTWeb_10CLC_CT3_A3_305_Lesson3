package vn.iotstar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Không được phép rỗng")
    private String name;
    //    Trong SQLServer ta dung max nhung trong mysql ta dung longtext
    private String images;

    @Column(name = "status")
    private int status;

//    Dung chung giao dien them, sua
    private Boolean isEdit=false;
}
