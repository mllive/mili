package com.robot.stock.util;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.robot.stock.model.StockModel;

public class StockUtil {
	final static String apiUrl = "http://hq.sinajs.cn/list=";

	public static String getStocksResult(String[] codes) throws MalformedURLException {
		String url = apiUrl;
		String cs = "";
		for (String c : codes) {
			if (cs.length() > 0) {
				cs += ",";
			}
			cs += c;
		}
		url += cs;
		String result = HttpRequestUtils.httpGet(url);
		return result;
	}

	public static List<StockModel> getStockList(String[] codes) throws MalformedURLException {
		String stocksResult = getStocksResult(codes);
		// var
		// hq_str_sz002237="恒邦股份,9.88,9.84,9.94,10.16,9.82,9.94,9.95,12234750,122446026.30,8700,9.94,37400,9.93,11600,9.92,49100,9.91,24100,9.90,8440,9.95,18500,9.96,13300,9.97,19400,9.98,25500,9.99,2015-10-21,10:53:42,00";
		// var
		// hq_str_sz002293="罗莱家纺,17.27,17.14,16.66,17.28,16.60,16.67,16.68,7473759,126631928.37,1400,16.67,1300,16.66,14600,16.65,13600,16.64,60950,16.63,13100,16.68,6000,16.69,51500,16.70,5850,16.71,200,16.72,2015-10-21,10:53:42,00";
		String[] stockInfos = stocksResult.split("\n");
		List<StockModel> stockList = new ArrayList<StockModel>();
		for (String stockInfo : stockInfos) {
			stockInfo = stockInfo.replace("var hq_str_", "").replace("=\"", ",").replace("\"", "").replace(";", "");
			String[] s = stockInfo.split(",");
			StockModel stock = new StockModel();
			stock.setCode(s[0]);
			stock.setName(s[1]);
			Double openprice = Double.parseDouble(s[2]);
			stock.setOpenprice(openprice);
			Double closeprice = Double.parseDouble(s[3]);
			stock.setCloseprice(closeprice);
			Double nowprice = Double.parseDouble(s[4]);
			stock.setNowprice(nowprice);
			Double maxprice = Double.parseDouble(s[5]);
			stock.setMaxprice(maxprice);
			Double minprice = Double.parseDouble(s[6]);
			stock.setMinprice(minprice);
			stock.setDate(s[31]);
			stock.setTime(s[32]);
			stockList.add(stock);
		}
		return stockList;
	}

	public static void main(String[] args) throws MalformedURLException {
		String[] codes = { "sz002237,sz002293" };
		getStockList(codes);
	}
}
