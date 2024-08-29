package yumi.kyungmin.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yumi.kyungmin.domain.Item;
import yumi.kyungmin.domain.Member;
import yumi.kyungmin.dto.ItemDto;
import yumi.kyungmin.dto.ItemSearch;
import yumi.kyungmin.mapper.ItemMapper;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ItemService {

  private final ItemMapper itemMapper;

  @Transactional
  public Long register(ItemDto itemDto , Member member){
    Item item = Item.builder().itemName(itemDto.getItemName()).price(itemDto.getPrice())
        .stockQuantity(itemDto.getPrice()).registerDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).member(member).build();
    return itemMapper.save(item);
  }

  public Item findItem(Long id){
    return itemMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 id 입니다."));
  }

  @Transactional
  public void updateItem(Long id , ItemDto itemDto){
    Item findItem = findItem(id);
    findItem.setItemName(itemDto.getItemName());
    findItem.setPrice(itemDto.getPrice());
    findItem.setStockQuantity(itemDto.getStockQuantity());
    findItem.setUpdateDate(LocalDateTime.now());
    itemMapper.update(findItem);
  }

  public List<Item> findItems(){
    return itemMapper.findAll();
  }

  public List<Item> findItemsByMember(Member member , ItemSearch itemSearch){

    return itemMapper.findAllByMember(member ,itemSearch);
  }



}
