import org.apache.ws.axis2.StockQuoteStub;
import org.apache.ws.axis2.StockQuoteStub.GetPrice;
import org.apache.ws.axis2.StockQuoteStub.GetPriceResponse;
import org.apache.ws.axis2.StockQuoteStub.Update;



public class TestMe {
	public static void main(String[] args) {
		try {
			StockQuoteStub stub = new StockQuoteStub(
					"http://192.168.8.130:8080/myservices/services/StockQuoteService?wsdl");
			System.out.println("Done");

			Update update = new Update();
			update.setPrice(1000.00);
			update.setSymbol("USD");
			stub.update(update);
			
			GetPrice price = new GetPrice();
			price.setSymbol("USD");
			
			GetPriceResponse response = stub.getPrice(price);
			System.out.println(response.get_return());
			 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
