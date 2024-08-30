package mybatis.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    model.addAttribute("item" , new Item());
    return "item/saveItem";
  }

  @PostMapping("items/save")
  public String saveItemPost(@Validated @ModelAttribute("item") ItemDto itemDto , BindingResult bindingResult ,
      @Login Member member , RedirectAttributes redirectAttributes){
    if (bindingResult.hasErrors()){ //유효성 검사 및 Member null 검사
      return "item/saveItem";
    }

    //Member 없으면 null
    log.info("member = {}" , member);
    //강제로 localhost:8080/items
    itemService.register(itemDto , member);

    redirectAttributes.addAttribute("saveStatus", true);
    return "redirect:/items";
  }

  @GetMapping("items/update/{id}")
  public String updateItemForm(@PathVariable Long id , Model model){
    model.addAttribute("item" , itemService.findItem(id));
    return "item/editItem";
  }

  @PostMapping("items/update/{id}")
  public String updateItemPost(@PathVariable Long id , @Validated @ModelAttribute("item") ItemDto itemDto , BindingResult bindingResult , RedirectAttributes redirectAttributes){
    if (bindingResult.hasErrors()){
      return "item/editItem";
    }
    HttpServletRequest request;
    itemService.updateItem(id , itemDto);
    redirectAttributes.addAttribute("updateStatus", true);
    return "redirect:/items";

  }

}
