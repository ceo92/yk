package mybatis.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemDto {

  @NotBlank
  private String itemName;

  @NotNull
  private int price;

  @NotNull
  private int stockQuantity;
}
