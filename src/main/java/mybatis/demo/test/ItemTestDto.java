package mybatis.demo.test;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import mybatis.demo.domain.ItemType;

@Data
public class ItemTestDto {

  private List<String> regions = new ArrayList<>();
  private ItemType itemType;
  private String deliveryCompany;

}
