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

  /*
  //단일 체크박스
  private Boolean isOpen; //즉시 판매여부?

  //복수 체크박스 , 값 타입 컬렉션
  private List<String> regions = new ArrayList<>(); //복수 체크박스 , 어느 지역 지점들에서 판매하게 할 것인지

  //라디오 버튼 : 화장품 , 옷 , 영양제 여부
  private ItemType itemType;

  //셀렉트 박스
  private String deliveryCompany  ; //배송사 선택하기 , 한진택배 , CJ대한통운 , 로즈택배 , 경민택배 , ...

*/
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
