package yumi.kyungmin.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Item {
  private Long id;

  private String itemName;

  private int price;

  private int stockQuantity;

  @DateTimeFormat(pattern =  "yyyy-MM-dd hh:mm")
  private LocalDateTime registerDate;

  @DateTimeFormat(pattern =  "yyyy-MM-dd hh:mm")
  private LocalDateTime updateDate;


  private Member member; //한 회원이 상품 등록하므로 , 회원 1<=>N 상품

}
