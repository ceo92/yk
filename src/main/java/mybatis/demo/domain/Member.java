package mybatis.demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter(AccessLevel.PRIVATE)
public class Member {

  private Long id;
  private String loginEmail;
  private String password; //DB에 저장된 암호 다르더라도 어차피 똑같은 암호화 방법에 의해 저장됨
  private String memberName;
  private String businessName;
  private String businessNum;


  //private List<Item> items = new ArrayList<>(); , 한 회원이 갖는 상품들 조회할 일 없음
  public static Member createMember(String loginEmail , String password , String memberName , String businessName , String businessNum){
    Member member = new Member();
    member.setLoginEmail(loginEmail);
    member.setPassword(password);
    member.setMemberName(memberName);
    member.setBusinessName(businessName);
    member.setBusinessNum(businessNum);
    return member;
  }

}
