package mybatis.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import mybatis.demo.domain.Member;
import mybatis.demo.dto.MemberSaveDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

  @Autowired MemberService memberService;

  @Rollback(value = false)
  @Test
  void findMembersWithPaging() {
    for (int i=201; i<=1000 ; i++){
      MemberSaveDto memberSaveDto = new MemberSaveDto(i+"aaa123@naver.com",
          "abcd1234!@#$"+i ,"abcd1234!@#$"+i, "member"+i ,
          "businessName"+i , "businessNum" + i);
      memberService.join(memberSaveDto);
    }
  }
}