package mybatis.demo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.jms.JmsProperties.DeliveryMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Item {
  private Long id;

  private String itemName;

  @NumberFormat(pattern = "#,###원")
  private Integer price;

  @NumberFormat(pattern = "#,###개")
  private Integer stockQuantity;

  @DateTimeFormat(pattern =  "yyyy-MM-dd hh:mm")
  private LocalDateTime registerDate;

  @DateTimeFormat(pattern =  "yyyy-MM-dd hh:mm")
  private LocalDateTime updateDate;


  private List<String> regions = new ArrayList<>();

  private ItemType itemType;


  private Member member; //한 회원이 상품 등록하므로 , 회원 1<=>N 상품



  public Item(String itemName, int price, int stockQuantity, LocalDateTime registerDate, LocalDateTime updateDate ,Member member) {
    this.itemName = itemName;
    this.price = price;
    this.stockQuantity = stockQuantity;
    this.registerDate = registerDate;
    this.updateDate = updateDate;
    this.member = member;

  }
}
