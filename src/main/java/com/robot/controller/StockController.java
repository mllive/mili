package com.robot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.httpModel.actionmessage.ActionMessage;
import com.robot.stock.model.StockModel;
import com.robot.stock.service.StockService;

/**
 * stock控制器
 * 
 **/
@Controller
@RequestMapping("/stock")
public class StockController extends BaseController {

	@Resource
	private StockService stockService;

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request) {
		return "/stock/list";
	}

	@RequestMapping("/json")
	@ResponseBody
	public Map<String, Object> json() {
		Map<String, Object> mapData = new HashMap<String, Object>();
		List<StockModel> stockList = new ArrayList<StockModel>();
		try {
			String[] codes = { "sz002293,sh600881,sz000923,sz000731,sh600313,sz002237,sh000001" };
			stockList = stockService.getStockList(codes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mapData.put("data", stockList);
		return mapData;
	}

	@RequestMapping("/addcode")
	@ResponseBody
	public ActionMessage addcode(String newcode) {
		ActionMessage message = new ActionMessage();
		message.setSuccess(true);
		return message;
	}
}
