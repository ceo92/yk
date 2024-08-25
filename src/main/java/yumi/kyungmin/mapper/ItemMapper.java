package yumi.kyungmin.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yumi.kyungmin.domain.Member;
import yumi.kyungmin.dto.ItemDto;
import yumi.kyungmin.dto.MemberDto;

@Mapper
public interface ItemMapper {

  Long save(ItemDto itemDto);


  void update(@Param("id") Long id , @Param("itemDto") ItemDto itemDto);
  Optional<Member> findById(Long id);

  List<Member> findAll();

}
