package yumi.kyungmin.domain;

import lombok.Data;

@Data
public class Item {
  private Long id;

  private String itemName;
  private int price;
  private int stockQuantity;

  private Member member; //한 회원이 상품 등록하므로 , 회원 1<=>N 상품

}
