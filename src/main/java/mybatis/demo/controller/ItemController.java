package mybatis.demo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mybatis.demo.domain.ItemType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import mybatis.demo.domain.Item;
import mybatis.demo.domain.Member;
import mybatis.demo.dto.ItemDto;
import mybatis.demo.dto.ItemSearch;
import mybatis.demo.login.Login;
import mybatis.demo.service.ItemService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {
  //매 권한 없는 페이지 접근마다 @Login을 해줘야하나?
  private final ItemService itemService;

  @ModelAttribute("countries")
  public Map<String , String> countries(){
    Map<String, String> countries = new LinkedHashMap<>();
    countries.put("SEOUL" , "서울");
    countries.put("ANYANG" , "안양");
    countries.put("DAEGU" , "대구");
    countries.put("SUWON" , "수원");
    countries.put("CHEONAN" , "천안");
    return countries;
  }

  @ModelAttribute("itemTypes")
  public ItemType[] itemTypes(){
    return ItemType.values();
  }

  //추후 연관관계로 빼자
  @ModelAttribute("deliveryCompanies") //이게 그냥 매 호출마다 모델로 넘어감
  public List<String> deliveryCompanies(){
    List<String> deliveryCompanies = new ArrayList<>();
    deliveryCompanies.add("한화 택배");
    deliveryCompanies.add("롯데 택배");
    deliveryCompanies.add("한진 택배");
    deliveryCompanies.add("CJ 대한통운");
    deliveryCompanies.add("CU 편의점택배");
    return deliveryCompanies;
  }


  @GetMapping("items")
  public String getItems(@ModelAttribute("itemSearch") ItemSearch itemSearch ,BindingResult bindingResult , @Login Member member , Model model){
    List<Item> items = itemService.findItemsByMember(member , itemSearch);
    log.info("member = {}", member);
    for (Item item : items) {
      log.info("item = {}", item);
    }
    model.addAttribute("item", itemSearch);
    model.addAttribute("items", items);
    return "item/items";
  }

  @GetMapping("items/save")
  public String saveItemForm(Model model){
    model.addAttribute("item", new ItemDto());
    return "item/saveItem";
  }

  @PostMapping("items/save")
  public String saveItemPost(@Validated @ModelAttribute("item") ItemDto itemDto , BindingResult bindingResult ,
      @Login Member member , RedirectAttributes redirectAttributes){
    if (bindingResult.hasErrors()){ //유효성 검사 및 Member null 검사
      return "item/saveItem";
    }

    List<String> regions = itemDto.getRegions();
    for (String region : regions) {
      log.info("region = {}" , region);
    }
    ItemType itemType = itemDto.getItemType();
    log.info("itemType = {}", itemType);

    //Member 없으면 null
    log.info("member = {}" , member);
    //강제로 localhost:8080/items
    itemService.register(itemDto , member);

    redirectAttributes.addAttribute("saveStatus", true);
    return "redirect:/items"; //PRG
  }

  @GetMapping("items/update/{id}")
  public String updateItemForm(@PathVariable Long id , Model model){
    Item findItem = itemService.findItem(id);
    ItemDto itemDto = new ItemDto(findItem.getItemName() , findItem.getPrice()
        , findItem.getStockQuantity() , findItem.getRegions() , findItem.getItemType());
    model.addAttribute("item" , itemDto);
    return "item/editItem";
  }

  @PostMapping("items/update/{id}")
  public String updateItemPost(@PathVariable Long id , @Validated @ModelAttribute("item") ItemDto itemDto , BindingResult bindingResult , RedirectAttributes redirectAttributes){
    if (bindingResult.hasErrors()){
      return "item/editItem";
    }
    log.info("regions = {}" , itemDto.getRegions());
    log.info("itemType = {}" , itemDto.getItemType());
    itemService.updateItem(id , itemDto);
    redirectAttributes.addAttribute("updateStatus", true);
    return "redirect:/items"; //PRG

  }

}
