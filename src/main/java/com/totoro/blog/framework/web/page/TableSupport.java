package com.totoro.blog.framework.web.page;

import com.totoro.blog.common.utils.ServletUtils;
import com.totoro.blog.common.utils.StringUtils;

/**
 * @version 1.0
 * @className: TableSupport
 * @description: Form data processing
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
public class TableSupport {
    public TableSupport() {
    }
    /*Start index of current record*/
    public static final  String PAGE_NUM = "pageNum";
    /*Records per page*/
    public static final String PAGE_SIZE = "pageSize";
    /*Sorting*/
    public static final String ORDER_BY_COLUMN = "orderByColumn";
    /*Sort direction "desc" or "asc".*/
    public static final String IS_ASC = "isAsc";

    /**
     * Encapsulate paging objects
     *
     * @return Pagination
     */
    public static PageDomain getPageDomain(){
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
        pageDomain.setOrderByString(StringUtils.isEmpty(ServletUtils.getParameter(ORDER_BY_COLUMN)) ? "createTime" : ServletUtils.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(StringUtils.isEmpty(ServletUtils.getParameter(IS_ASC)) ? "desc" : ServletUtils.getParameter(IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest(){
        return getPageDomain();
    }
}
