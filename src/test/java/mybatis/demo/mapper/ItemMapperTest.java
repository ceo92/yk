package mybatis.demo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import mybatis.demo.domain.Item;
import mybatis.demo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemMapperTest {

  @Autowired ItemMapper itemMapper;
  @Autowired MemberMapper memberMapper;

  @Test
  void save() {

    Member member = memberMapper.findById(1L).orElse(null);
    System.out.println("member = " + member);
    Item item = new Item( "aa" , 20000 , 30 ,
        LocalDateTime.of(1900 , 12 , 12 , 12,11, 30) ,
        LocalDateTime.of(1900 , 12 , 12 , 12,11, 30) ,
        member);

    itemMapper.save(item);

    Assertions.assertThat(item).isEqualTo(itemMapper.findById(item.getId()).get());

  }

  @Test
  void update() {
  }

  @Test
  void findAllByMember() {
  }
}