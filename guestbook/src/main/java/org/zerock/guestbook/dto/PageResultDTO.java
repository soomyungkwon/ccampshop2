package org.zerock.guestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {

    private List<DTO> dtoList;

    private int totalPage;

    private int page;       //현재 페이지

    private int size;

    private int start, end;

    private boolean prev, next;

    private List<Integer> pageList;      //페이지번호 목록

    //페이지 결과로 dtoList를 만들고, 페이지리스트 함수 makePageList() 선언
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){

        dtoList=result.stream().map(fn).collect(Collectors.toList());

        totalPage=result.getTotalPages();

        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {

        this.page=pageable.getPageNumber() + 1;
        this.size=pageable.getPageSize();

        int tempEnd=(int) (Math.ceil(page/10.0)) * 10;      //페이지의 가상 끝번호

        start = tempEnd - 9;

        prev = start > 1;      //2페이지부터 이전 가기 생성

        end = totalPage > tempEnd ? tempEnd : totalPage;

        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
