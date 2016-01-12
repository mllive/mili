package com.robot.stock.service;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.robot.stock.model.StockModel;
import com.robot.stock.util.StockUtil;

/**
 * stock Service实现类
 *
 */
@Service
public class StockService {

	public List<StockModel> getStockList(String[] codes) throws MalformedURLException {
		return StockUtil.getStockList(codes);
	}

}
