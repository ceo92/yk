package mybatis.demo.domain;

import lombok.Getter;
import mybatis.demo.dto.SearchDto;

@Getter
public class Pagination {

  private int totalDataCount;     // 전체 데이터 수
  private int totalPageCount;       // 전체 페이지 수
  private int startPageNum;            // 첫 페이지 번호
  private int endPageNum;              // 끝 페이지 번호
  private int limitStart;           // LIMIT 시작 위치
  private boolean isExistPrevPage;    // 이전 페이지 존재 여부
  private boolean isExistNextPage;    // 다음 페이지 존재 여부

  public Pagination(int totalRecordCount, SearchDto params) {
    if (totalRecordCount > 0) {
      this.totalDataCount = totalRecordCount;
      calculation(params);
    }
  }

  private void calculation(SearchDto params) {

    // 전체 페이지 수 계산
    totalPageCount = ((totalDataCount - 1) / params.getPrintSize()) + 1;

    // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
    if (params.getCurrentPageNum() > totalPageCount) {
      params.setCurrentPageNum(totalPageCount);
    }

    // 첫 페이지 번호 계산
    startPageNum = ((params.getCurrentPageNum() - 1) / params.getBottomPageSize()) * params.getBottomPageSize() + 1;

    // 끝 페이지 번호 계산
    endPageNum = startPageNum + params.getBottomPageSize() - 1;

    // 끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
    if (endPageNum > totalPageCount) {
      endPageNum = totalPageCount;
    }

    // LIMIT 시작 위치 계산
    limitStart = (params.getCurrentPageNum() - 1) * params.getPrintSize();

    // 이전 페이지 존재 여부 확인 , 1만 아니면 전부 이전 페이지가 존재하므로
    isExistPrevPage = startPageNum != 1;

    // 다음 페이지 존재 여부 확인 , 현재 데이터가 전체 데이터 수보다 작을 때만 다음 페이지가 존재
    isExistNextPage = (endPageNum * params.getPrintSize()) < totalDataCount;
  }

}
