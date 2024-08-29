package yumi.kyungmin.dto;

import lombok.Data;

@Data
public class ItemSearch {

  private String itemName;
  private Integer maxPrice;
  private Integer maxStockQuantity;
}
