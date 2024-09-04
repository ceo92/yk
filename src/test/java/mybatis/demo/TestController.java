package mybatis.demo;

import java.util.List;
import mybatis.demo.domain.Item;
import mybatis.demo.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestController {
  @Autowired
  private ItemService itemService;

  @Test
  void abc(){
    List<Item> items = itemService.findItems();
    for (Item item : items) {
      System.out.println("item.getMember() = " + item.getMember());
    }
  }
}
