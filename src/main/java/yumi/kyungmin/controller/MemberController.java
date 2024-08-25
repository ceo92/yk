package yumi.kyungmin.controller;

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
import yumi.kyungmin.dto.MemberDto;
import yumi.kyungmin.mapper.MemberMapper;
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
  public String saveMemberForm(Model model){
    model.addAttribute("member", new Member());
    return "member/saveMember";
  }

  @PostMapping("members/save")
  public String saveMemberForm(@Validated @ModelAttribute("member") MemberDto memberDto  , BindingResult bindingResult , RedirectAttributes redirectAttributes){
    if (bindingResult.hasErrors()){
      return "member/saveMember";
    }
    memberService.join(memberDto);
    return "redirect:/members";
  }

  /**
   * 로그인 관리
   */
  public String login(){

  }

}
