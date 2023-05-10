package com.example.bladecalculator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationVO {

    private int totalPages;

    private int size;

    private long totalElements;

    private int nowPage;

    private int firstPage;

    private int lastPage;

    private Object content;

    public PaginationVO(int page, Page<?> pages, Object content) {
        int pageSize = pages.getSize();
        this.firstPage = (page / pageSize) * pageSize + 1;
        this.lastPage = Math.min((page / pageSize + 1) * pageSize, pages.getTotalPages());
        this.nowPage = page + 1;
        this.totalPages = pages.getTotalPages();
        this.size = pages.getSize();
        this.totalElements = pages.getTotalElements();
        this.content = content;
    }
}
