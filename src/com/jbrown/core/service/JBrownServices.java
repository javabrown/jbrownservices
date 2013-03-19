//package com.jbrown.core.service;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//
//import javax.activation.DataHandler;
//
//import org.apache.axiom.attachments.Attachments;
//import org.apache.axis2.context.MessageContext;
// 
//import com.jbrown.cache.BrownDataCache;
//import com.jbrown.core.data.CountryData;
//import com.jbrown.core.data.model.StateI;
//import com.jbrown.core.util.BrownConstant;
//
//
//public class JBrownServices {
//    private HashMap map = new HashMap();
//
//    public double getPrice(String symbol) {
//        Double price = (Double) map.get(symbol);
//        if(price != null){
//            return price.doubleValue();
//        }
//        return 42.00;//
//    }
//
//    public void update(String symbol, double price) {
//        map.put(symbol, new Double(price));
//    }
//    
////	public String uploadFile(String name, String attchmentID) 
////	{
////		try{
////        MessageContext msgCtx = MessageContext.getCurrentMessageContext();
////        Attachments attachment = msgCtx.getAttachmentMap();
////        DataHandler dataHandler = attachment.getDataHandler(attchmentID);
////        File file = new File(
////				name);
////        System.out.println("RK-->"+name);
////		FileOutputStream fileOutputStream = new FileOutputStream("c:\\"+file);
////		dataHandler.writeTo(fileOutputStream);
////		fileOutputStream.flush();
////		fileOutputStream.close();
////		}
////		catch(Exception ex){
////			ex.printStackTrace();
////		}
////		return "File saved succesfully.";
////	}
//	
//	public String[] getAllCities(String isoCountryCode, String stateName){
//		return BrownDataCache.getInstance().getCities(isoCountryCode,
//				stateName);
//	}
//	
//	public String[] getState() {
//		return BrownDataCache.getInstance().getStatesName(
//				BrownConstant.ISO_INDIA);
//	}
//}
