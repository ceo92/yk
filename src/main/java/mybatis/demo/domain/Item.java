package mybatis.demo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

  @NumberFormat(pattern = "#,###원")
  private Integer price;

  @NumberFormat(pattern = "#,###개")
  private Integer stockQuantity;

  @DateTimeFormat(pattern =  "yyyy-MM-dd hh:mm")
  private LocalDateTime registerDate;

  @DateTimeFormat(pattern =  "yyyy-MM-dd hh:mm")
  private LocalDateTime updateDate;

  //단일 체크박스
  //private Boolean is //즉시 판매여부?

  private List<String> regions = new ArrayList<>(); //복수 체크박스 , 어느 지역 지점들에서 판매하게 할 것인지





  private Member member; //한 회원이 상품 등록하므로 , 회원 1<=>N 상품

}
