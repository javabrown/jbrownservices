<xsl:stylesheet version='1.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>

<xsl:template match="services">

	<xsl:result-document href="JBrownWebservices.java">
		/**
		* JBrownWebservices.java
		*
		* This file was auto-generated from api-doc.xml
		* Please do not edit directly.
		*
		*/

		package com.jbrown.web.ws.soap;
		import java.util.*;

		public class JBrownWebservices implements JBrownWebservicesI {
	  	<xsl:apply-templates mode="class" />
		}
	</xsl:result-document>

<xsl:template match="service" mode="class">
  public <xsl:value-of select="@name"/>Result 
      do<xsl:value-of select="@name"/>(<xsl:apply-templates select="inputs"/>) {
    try {
      return <xsl:value-of select="@name"/>
        .process(<xsl:apply-templates select="inputs" mode="call-method"/>);  
    }
    catch(Exception e) {      
      Map debugInfo = new HashMap();
      debugInfo.put("type", "soap");
      debugInfo.put("url", System.getenv("WS_EXTERNAL_URL"));
      <xsl:apply-templates select="inputs" mode="debug-info"/> 
      com.raileurope.web.ws.util.PanicMailer.sendPanic(e, debugInfo);
      return null;
    }
  }
</xsl:template>


</xsl:template>



</xsl:stylesheet> 