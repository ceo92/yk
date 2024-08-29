package mybatis.demo.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import mybatis.demo.domain.Item;
import mybatis.demo.domain.Member;
import mybatis.demo.dto.ItemSearch;

@Mapper
public interface ItemMapper {

  Long save(Item item);

  void update(Item item);

  Optional<Item> findById(Long id);

  List<Item> findAll();

  List<Item> findAllByMember(@Param("member") Member member , @Param("itemSearch") ItemSearch itemSearch);

}
