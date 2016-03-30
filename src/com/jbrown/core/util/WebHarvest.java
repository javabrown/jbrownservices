package com.jbrown.core.util;

import java.io.FileNotFoundException;
import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

public class WebHarvest {
	public static void main(String[] args) {
		try {
			String strPageURL = "http://www.javabrown.com/";
			ScraperConfiguration config = new ScraperConfiguration(
					"c:/tmp/Web-Harvesting.xml");
			// "K:/R&D/WebScrapping/src/basic/webHarvestConf.xml");
			Scraper scraper = new Scraper(config, "c:/");
			scraper.addVariableToContext("url", strPageURL);
			scraper.setDebug(true);
			scraper.execute();
			Variable varScrappedContent = (Variable) scraper.getContext()
					.getVar("scrappedContent");

			// Printing the scraped data here
			System.out.println(varScrappedContent.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	static final String xml = 
			"	<?xml version=\"1.0\" encoding=\"UTF-8\"?>					"
			+ "	<config charset=\"UTF-8\">						"
			+ "	 <var-def name=\"scrappedContent\">					"
			+ "	  <xquery>								"
			+ "	     <xq-param name=\"doc\">						"
			+ "		<html-to-xml outputtype=\"browser-compact\" prunetags=\"yes\">	"
			+ "		  <http url=\"${url}\"/>						"
			+ "		</html-to-xml>							"
			+ "	      </xq-param>							"
			+ "										"
			+ "	      <xq-expression><![CDATA[						"
			+ "		 declare variable $doc as node() external;			"
			+ "		 let $title := data($doc//h1)					"
			+ "		 let $data  := data($doc//div[@id=\"defId\"])			"
			+ "		 return								"
			+ "		    <myContent>							"
			+ "		      <title>{$title}</title>					"
			+ "		      <data>{$data}</data>					"
			+ "		    </myContent>						"
			+ "	       ]]>								"
			+ "	   </xq-expression>							"
			+ "	  </xquery>								"
			+ "	 </var-def>  								"
			+ "	</config>								";
}
