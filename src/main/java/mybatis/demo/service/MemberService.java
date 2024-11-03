package mybatis.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import mybatis.demo.domain.Pagination;
import mybatis.demo.domain.PagingResponse;
import mybatis.demo.dto.PagingDto;
import mybatis.demo.dto.SearchDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mybatis.demo.domain.Member;
import mybatis.demo.dto.LoginDto;
import mybatis.demo.dto.MemberSaveDto;
import mybatis.demo.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

  private final MemberMapper memberMapper;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public Long join(MemberSaveDto memberSaveDto){
    String loginEmail = memberSaveDto.getLoginEmail();
    String password = memberSaveDto.getPassword();
    String rePassword = memberSaveDto.getRePassword();

    //1. 회원가입 전 검증
    validateBeforeJoin(loginEmail , password , rePassword);

    //2. 비밀번호 암호화
    String encodedPassword = passwordEncoder.encode(password);

    //3. 회원가입 성공
    Member member = Member.createMember(loginEmail, encodedPassword,memberSaveDto.getMemberName(),
        memberSaveDto.getBusinessName(), memberSaveDto.getBusinessNum());

    memberMapper.save(member);
    return member.getId(); //여기서
  }

  public Member findMember(Long id){
    return memberMapper.findById(id).orElseThrow(()->new IllegalArgumentException("잘못된 id 입니다."));
  }

  public List<Member> findMembers(){
    return memberMapper.findAll();
  }


  public Member login(LoginDto loginDto) {
    String loginEmail = loginDto.getLoginEmail();
    String password = loginDto.getPassword();
    return findMembers().stream()
        .filter(member -> member.getLoginEmail().equals(loginEmail))
        .filter(member -> passwordEncoder.matches(password , member.getPassword()))
        .findFirst()
        .orElse(null);
  }

  private void validateBeforeJoin(String loginEmail, String password, String rePassword) {
    //1. 이미 존재하는 아이디인지
    findMembers().stream().filter(member -> member.getLoginEmail().equals(loginEmail)).findFirst().ifPresent(member->{
      throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
    });

    //2.이메일 형식 검증
    if (!Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$", loginEmail)){
      throw new IllegalArgumentException("이메일 형식을 다시 한 번 확인해주세요 ");
    }

    //3. 8자리 이상비밀번호 영문 , 특수문자 , 숫자 검증
    if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$" ,
        password)){
      throw new IllegalArgumentException("비밀번호는 11자리 이상 특수문자 , 영문 , 숫자의 조합이어야합니다.");
    }

    // 4. 비밀번호 더블체크
    if (!password.equals(rePassword)) {
      throw new IllegalArgumentException("비밀번호를 다시 한 번 확인해주세요");
    }
  }


  /**
   * 페이징 적용된 전체 리스트 조회
   */

  public PagingResponse<Member> findMembersWithPaging(SearchDto searchDto) {
    Integer count = memberMapper.count(searchDto);
    if (count < 1){
      return new PagingResponse<>(Collections.emptyList(), null); //총 개수가 아무것도 없으면 빈 리스트와 null을 던져야함
    }
    //PagingResponse는 응답을 List<Member>와 Pagination을 같이 한꺼번에 던져야함 즉 Map<List<Member> , Pagination>도 되지만, 복잡하니 그냥 PagingResponse로 묶은 것!

    Pagination pagination = new Pagination(count , searchDto); //하나라도 있으면 총 개수를 던지고, 컨트롤러로부터 넘어온 searchDto도 넘김
    searchDto.setPagination(pagination);
    List<Member> list = memberMapper.findAllPaging(searchDto);
    return new PagingResponse<>(list, pagination);
  }


}
