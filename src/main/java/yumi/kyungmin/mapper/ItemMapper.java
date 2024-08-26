package yumi.kyungmin.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yumi.kyungmin.domain.Item;
import yumi.kyungmin.domain.Member;
import yumi.kyungmin.dto.ItemDto;
import yumi.kyungmin.dto.MemberDto;

@Mapper
public interface ItemMapper {

  Long save(Item item);

  void update(Item item);

  Optional<Item> findById(Long id);

  List<Item> findAll();

  List<Item> findAllByMember();

}
