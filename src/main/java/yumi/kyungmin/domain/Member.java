package yumi.kyungmin.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

  private Long id;
  private String memberName;
  private String businessName;
  private String businessNum;

  //private List<Item> items = new ArrayList<>(); , 한 회원이 갖는 상품들 조회할 일 없음
  public static Member createMember(String memberName , String businessName , String businessNum){
    Member member = new Member();
    member.setMemberName(memberName);
    member.setBusinessName(businessName);
    member.setBusinessNum(businessNum);
    return member;
  }

}
