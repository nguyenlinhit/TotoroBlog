package com.totoro.blog.framework.web.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.totoro.blog.common.utils.DateUtils;
import com.totoro.blog.common.utils.SqlUtils;
import com.totoro.blog.common.utils.StringUtils;
import com.totoro.blog.framework.web.domain.AjaxResult;
import com.totoro.blog.framework.web.page.PageDomain;
import com.totoro.blog.framework.web.page.TableDataInfo;
import com.totoro.blog.framework.web.page.TableSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @className: BaseController
 * @description: Web layer general data processing.
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
@Slf4j
public class BaseController {
    /**
     * The date format string passed from the foreground
     * is automatically converted to the Date type
     *
     * @param binder Binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        /*Date type conversion*/
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * Set Request Paging Data
     */
    protected void startPage(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)){
            String orderBy = SqlUtils.escapeOrderBySql(pageDomain.getOrderByString());
            PageMethod.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * Paging data in response to a request.
     *
     * @param list Response
     * @return List data paging
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list){
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.OK.value());
        rspData.setRows(list);
        if (list == null){
            list = new ArrayList<>();
        }
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * Response returns results
     *
     * @param rows Affected rows
     * @return Operation result
     */
    public static AjaxResult toAjax(int rows){
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
