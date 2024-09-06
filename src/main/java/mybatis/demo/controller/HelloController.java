package mybatis.demo.controller;

import static mybatis.demo.SessionConst.MEMBER_NAME;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import mybatis.demo.domain.Member;
import mybatis.demo.login.Login;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class HelloController {
  @RequestMapping("auth")
  public String guestMain(){
    return "guest-index";
  }
  //여기서 그냥 요청 메시지에 쿠키 헤더가 존재하면

  @RequestMapping
  public String authorizedMain(@Login Member member , Model model){
    model.addAttribute("member", member);
    return "authorize-index";
  }


  //없으면 /로 감 => /로 가면 인
  /*@RequestMapping
  public String hello(@Login Member loginMember , Model model){
    log.info("member = {}" , loginMember);
    //타임리프에서 th:if="member==null" 해주는 것은 특정 화
    if (loginMember == null){
      return "guest-index";
    }

    model.addAttribute("member", loginMember);
    return "authorize-index";
  }
*/



}
