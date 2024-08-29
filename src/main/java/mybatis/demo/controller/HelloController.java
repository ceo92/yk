package mybatis.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import mybatis.demo.domain.Member;
import mybatis.demo.login.Login;

@Controller
@Slf4j
public class HelloController {


  @RequestMapping
  public String hello(@Login Member loginMember , Model model){
    log.info("member = {}" , loginMember);
    //타임리프에서 th:if="member==null" 해주는 것은 특정 화
    if (loginMember == null){
      return "index";
    }
    model.addAttribute("member", loginMember);
    return "member/member";
  }



}
