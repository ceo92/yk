package mybatis.demo.controller;

import static mybatis.demo.SessionConst.MEMBER_NAME;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import mybatis.demo.domain.Member;
import mybatis.demo.dto.LoginDto;
import mybatis.demo.dto.MemberSaveDto;
import mybatis.demo.login.Login;
import mybatis.demo.service.MemberService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

  private final MemberService memberService;

  @GetMapping("members")
  public String getMembers(Model model){
    List<Member> members = memberService.findMembers();
    model.addAttribute("members" , members);
    return "member/members";
  }




  @GetMapping("auth/join")
  public String joinForm(Model model){
    model.addAttribute("member", new MemberSaveDto());
    return "member/join";
  }

  @PostMapping("auth/join")
  public String joinForm(@Validated @ModelAttribute("member") MemberSaveDto memberSaveDto  , BindingResult bindingResult ,
      RedirectAttributes redirectAttributes , HttpServletRequest request){
    if (bindingResult.hasErrors()){
      return "member/join";
    }
    try {
      Long joinedId = memberService.join(memberSaveDto);
      log.info("joinedId = {}", joinedId);
      Member joinedMember = memberService.findMember(joinedId);
      HttpSession session = request.getSession(true);
      session.setAttribute(MEMBER_NAME, joinedMember);

      redirectAttributes.addAttribute("status", true); //리다이렉트 된 경로에 /members?status=true가 됨
      return "redirect:/";
    }catch (IllegalArgumentException e){
      bindingResult.reject("joinError", e.getMessage());
      return "member/join";
    }
  }

  /**
   * 로그인 관리
   */
  @GetMapping("auth/login")
  public String loginForm(Model model){
    model.addAttribute("login" , new LoginDto());
    return "member/login";
  }

  @PostMapping("auth/login")
  public String loginPost(@Validated @ModelAttribute("login") LoginDto loginDto , BindingResult bindingResult
      ,  HttpServletRequest request , Model model){
    if (bindingResult.hasErrors()){
      return "member/login";
    }

    Member loginMember = memberService.login(loginDto);
    if (loginMember == null){ //예외 처리보다 null에 대한 처리가 BindingResult를 처리하는데 더 깔끔할듯? 어차피 이건 예외가 아닌 유효성 검사이니 null로 처리하는 게맞는듯?
      bindingResult.reject("loginError");
      return "member/login";
    }

    HttpSession session = request.getSession(true);
    session.setAttribute(MEMBER_NAME , loginMember);
    model.addAttribute("member", loginMember);
    return "redirect:/";
  }

  @PostMapping("logout")
  public String logout(HttpServletRequest request){
    HttpSession session = request.getSession(false);
    if (session != null){
      session.invalidate();
    }
    return "redirect:/auth"; //루트로 보내도 어차피 인터셉터에서 알아서 걸러짐
  }

}
