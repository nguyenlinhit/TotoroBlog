package com.totoro.blog.framework.web.page;

import com.totoro.blog.common.StringUtils;

/**
 * @version 1.0
 * @className: PageDomain
 * @description: Paginated domain
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
public class PageDomain {
    /*Start index of current record*/
    private Integer pageNum;
    /*Records per page*/
    private Integer pageSize;
    /*Sorting*/
    private String orderByString;
    /*Sort direction "desc" or "asc"*/
    private String isAsc;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByString() {
        if (StringUtils.isEmpty(orderByString)){
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByString);
    }

    public void setOrderByString(String orderByString) {
        this.orderByString = orderByString;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }
}
