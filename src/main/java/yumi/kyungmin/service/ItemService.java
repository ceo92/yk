package yumi.kyungmin.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yumi.kyungmin.domain.Item;
import yumi.kyungmin.dto.ItemDto;
import yumi.kyungmin.mapper.ItemMapper;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemMapper itemMapper;

  public Long register(ItemDto itemDto){
    Item item = Item.builder().itemName(itemDto.getItemName()).price(itemDto.getPrice())
        .stockQuantity(itemDto.getPrice()).registerDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
    return itemMapper.save(item);
  }

  public Item findItem(Long id){
    return itemMapper.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 id 입니다."));
  }

  public void updateItem(Long id , ItemDto itemDto){
    Item findItem = findItem(id);
    findItem.setItemName(itemDto.getItemName());
    findItem.setPrice(itemDto.getPrice());
    findItem.setStockQuantity(itemDto.getStockQuantity());
    findItem.setUpdateDate(LocalDateTime.now());
    itemMapper.update(findItem);
  }

  public List<Item> findAll(){
    return itemMapper.findAll();
  }



}
