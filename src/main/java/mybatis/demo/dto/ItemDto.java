package mybatis.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

  @NotBlank
  private String itemName;

  @NotNull
  private int price;

  @NotNull
  private int stockQuantity;


  private List<String> regions = new ArrayList<>();

   



}
