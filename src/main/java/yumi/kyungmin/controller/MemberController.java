package yumi.kyungmin.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yumi.kyungmin.domain.Member;
import yumi.kyungmin.dto.LoginDto;
import yumi.kyungmin.dto.MemberDto;
import yumi.kyungmin.dto.MemberSaveDto;
import yumi.kyungmin.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("members")
  public String getMembers(Model model){
    List<Member> members = memberService.findMembers();
    model.addAttribute("members" , members);
    return "member/members";
  }

  @GetMapping("members/save")
  public String joinForm(Model model){
    model.addAttribute("member", new MemberSaveDto());
    return "member/join";
  }

  @PostMapping("members/save")
  public String joinForm(@Validated @ModelAttribute("member") MemberSaveDto memberSaveDto  , BindingResult bindingResult , RedirectAttributes redirectAttributes){
    if (bindingResult.hasErrors()){
      return "member/join";
    }
    memberService.join(memberSaveDto);
    redirectAttributes.addAttribute("status",true); //리다이렉트 된 경로에 /members?status=true가 됨
    return "redirect:/members";
  }

  /**
   * 로그인 관리
   */
  @GetMapping("login")
  public String loginForm(Model model){
    model.addAttribute("login" , new LoginDto());
    return "member/login";
  }

  @PostMapping("login")
  public String loginPost(@Validated @ModelAttribute("login") LoginDto loginDto , BindingResult bindingResult , HttpServletRequest request){
    if (bindingResult.hasErrors()){
      return "member/login";
    }
    memberService.login(loginDto);
  }



}
