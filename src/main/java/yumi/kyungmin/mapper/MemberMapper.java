package yumi.kyungmin.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import yumi.kyungmin.domain.Member;
import yumi.kyungmin.dto.MemberDto;

@Mapper
public interface MemberMapper {

  //insert 하면 파라메터에 값이 즉시 할당되고 리턴 값에 맞춰서 자동 할당되는 거야 ,,?
  Long save(Member member);


  Optional<Member> findById(Long id);


  List<Member> findAll();

}
