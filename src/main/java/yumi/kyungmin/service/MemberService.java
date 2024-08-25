package yumi.kyungmin.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yumi.kyungmin.domain.Member;
import yumi.kyungmin.dto.MemberDto;
import yumi.kyungmin.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberMapper memberMapper;

  public Long join(MemberDto memberDto){
    Member member = Member.createMember(memberDto.getMemberName(), memberDto.getBusinessName(), memberDto.getBusinessNum());
    return memberMapper.save(member);
  }

  public Member findMember(Long id){
    return memberMapper.findById(id).orElseThrow(()->new IllegalArgumentException("잘못된 id 입니다."));
  }

  public List<Member> findMembers(){
    return memberMapper.findAll();
  }


}
