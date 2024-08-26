package yumi.kyungmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import yumi.kyungmin.domain.Member;
import yumi.kyungmin.login.Login;

@Controller
public class HelloController {


 /* @RequestMapping
  public String hello(@Login Member loginMember , Model model){
    if (loginMember == null){
      return "index";
    }
    //model.addAttribute("member", loginMember);
    return "member";
  }*/

  @RequestMapping
  public String index(){
    return "index";
  }

}
