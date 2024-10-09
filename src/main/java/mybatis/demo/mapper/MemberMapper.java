package mybatis.demo.mapper;

import java.util.List;
import java.util.Optional;
import mybatis.demo.dto.PagingDto;
import mybatis.demo.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;
import mybatis.demo.domain.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

@Mapper
public interface MemberMapper {

  //insert 하면 파라메터에 값이 즉시 할당되고 리턴 값에 맞춰서 자동 할당되는 거야 ,,?
  Long save(Member member);


  Optional<Member> findById(Long id);


  List<Member> findAll();


  /**
   * 게시글 리스트 조회
   * return 게시글 리스트
   */
  List<Member> findAllPaging(@Param("searchDto") SearchDto searchDto);


  /**
   * 총 게시글 수 카운팅
   */
  Integer count(SearchDto params);

}
