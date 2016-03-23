package com.tgy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.Page;
import com.tgy.exception.BaseException;
import com.tgy.service.PageService;
import com.tgy.util.ConditionMap;
import com.tgy.util.PageType;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;


@RestController
@RequestMapping(value = { "/page/list"  })
public class ListPageContoller extends HttpServlet {

	@RequestMapping(value = { "/fuli", "" })
	protected void index(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		int pageStart = NumberUtils.toInt(req.getParameter("pageStart"));
		
		ConditionMap conditions = new ConditionMap();
		conditions.add("type", PageType.resource).add("needFulidou >", 0)
			.add("isShare", true);
		
		PageService ps = new PageService();
		
		List<Page>  pages = ps.list(conditions, "", pageStart, 10);
		
	}

}
