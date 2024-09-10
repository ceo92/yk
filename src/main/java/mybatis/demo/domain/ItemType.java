package mybatis.demo.domain;

public enum ItemType {


  FOOD("음식") , CLOTHES("옷") , COSMETICS("화장품");

  private String description;

  ItemType(String description){
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
