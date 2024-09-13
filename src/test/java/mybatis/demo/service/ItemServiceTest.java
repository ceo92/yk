package mybatis.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import mybatis.demo.domain.Item;
import mybatis.demo.domain.Member;
import mybatis.demo.dto.ItemDto;
import mybatis.demo.dto.ItemSearch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ItemServiceTest {

  @Autowired private ItemService itemService;
  @Autowired private MemberService memberService;

  @Test
  void register() {
    //given
    ItemDto itemDto = new ItemDto("itemBB" , 20000 , 30);
    Member member = memberService.findMember(1L);

    //when
    itemService.register(itemDto , member);
    //then
    Assertions.assertThat(itemService.findItem(28L).getItemName()).isEqualTo(itemDto.getItemName());
  }



  @Test
  void updateItem() {
  }

  @Test
  void findItemsByMember() {
  }
}