//package com.jbrown.core.restful;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping("/kfc/brands")
//public class JSONController {
//
//	@RequestMapping(value = "{name}", method = RequestMethod.GET)
//	public @ResponseBody
//	Shop getShopInJSON(@PathVariable String name) {
//
//		Shop shop = new Shop();
//		shop.setName(name);
//		shop.setStaffName(new String[] { "mkyong1", "mkyong2" });
//
//		return shop;
//
//	}
//
// }