package yumi.kyungmin.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yumi.kyungmin.domain.Member;
import yumi.kyungmin.dto.LoginDto;
import yumi.kyungmin.dto.MemberDto;
import yumi.kyungmin.dto.MemberSaveDto;
import yumi.kyungmin.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberMapper memberMapper;
  private final PasswordEncoder passwordEncoder;

  public Long join(MemberSaveDto memberSaveDto){
    Member member = Member.createMember(memberSaveDto.getMemberName(), memberSaveDto.getBusinessName(), memberSaveDto.getBusinessNum());
    return memberMapper.save(member);
  }

  public Member findMember(Long id){
    return memberMapper.findById(id).orElseThrow(()->new IllegalArgumentException("잘못된 id 입니다."));
  }

  public List<Member> findMembers(){
    return memberMapper.findAll();
  }


  public void login(LoginDto loginDto) {
    String loginEmail = loginDto.getLoginEmail();
    String password = loginDto.getPassword();

    findMembers().stream().filter(member -> )
  }
}
