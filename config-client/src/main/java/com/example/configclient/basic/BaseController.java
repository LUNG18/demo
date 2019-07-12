package com.example.configclient.basic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.configclient.basic.HttpBasic;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController extends HttpBasic {

    protected Page defaultPage() {
        HttpServletRequest request = getHttpServletRequest();
        long limit = 10;
        long offset = 0;
        if (StringUtils.isNumeric(request.getParameter("limit"))) {
            limit = Integer.valueOf(request.getParameter("limit"));
        }
        if (StringUtils.isNumeric(request.getParameter("offset"))) {
            offset = Integer.valueOf(request.getParameter("offset"));
        }
        String sort = request.getParameter("sort");         //排序字段名称
        String order = request.getParameter("order");       //asc或desc(升序或降序)

        return new Page((offset / limit + 1), limit);

    }
}
