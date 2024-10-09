package mybatis.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class PagingResponse<T> {

  private List<T> list = new ArrayList<>();
  private Pagination pagination;

  public PagingResponse(List<T> list , Pagination pagination){
    this.list.addAll(list); //신기하네 ㅋㅋ , 통으로 리스트를 받음 Map<List , Pagination> 해도 되지만 객체지향적으로 객체로 묶음
    this.pagination = pagination;
  }

}
