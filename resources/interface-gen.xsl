<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:saxon="http://saxon.sf.net/"
                extension-element-prefixes="saxon" 
		version="2.0">
<xsl:output method="text" indent="no" omit-xml-declaration="yes" />

<xsl:strip-space elements="*"/>


<xsl:template match="/|*">
  <xsl:apply-templates />
</xsl:template>

<xsl:template match="*[@reference]">
  <xsl:apply-templates select="saxon:evaluate(@reference)"/>
</xsl:template>

<xsl:template match="*[@reference]" mode="call-method">
  <xsl:apply-templates select="saxon:evaluate(@reference)" mode="call-method"/>
</xsl:template>

<xsl:template match="*[@reference]" mode="debug-info">
  <xsl:apply-templates select="saxon:evaluate(@reference)" mode="debug-info"/>
</xsl:template>

<xsl:template match="*[@reference]" mode="constructor-body">
  <xsl:apply-templates select="saxon:evaluate(@reference)" mode="constructor-body"/>
</xsl:template>

<xsl:template match="*[@reference]" mode="constructor-args">
  <xsl:apply-templates select="saxon:evaluate(@reference)" mode="constructor-args"/>
</xsl:template>

<xsl:template match="services">
<xsl:result-document href="NewGenWebservicesI.java">
/**
 * NewGenWebservicesI.java
 *
 * This file was auto-generated from api-doc.xml
 * Please do not edit directly.
 * 
 */

package com.raileurope.web.ws.soap;
import java.util.*;

public interface NewGenWebservicesI{
  <xsl:apply-templates/>
}
</xsl:result-document>

<xsl:result-document href="NewGenWebservices.java">
/**
 * NewGenWebservices.java
 *
 * This file was auto-generated from api-doc.xml
 * Please do not edit directly.
 * 
 */

package com.raileurope.web.ws.soap;
import java.util.*;

public class NewGenWebservices implements NewGenWebservicesI {
  <xsl:apply-templates mode="class"/>
}
</xsl:result-document>

</xsl:template>

<xsl:template match="service">
  <xsl:variable name="file" select="concat(@name, 'Result.java')"/>
  <xsl:result-document href="{$file}">
package com.raileurope.web.ws.soap;
import java.util.*;

public class <xsl:value-of select="concat(@name, 'Result')"/> <xsl:text>{

</xsl:text>
    <xsl:apply-templates select="outputs"/>
<xsl:text>
  </xsl:text>
    public <xsl:value-of select="concat(@name, 'Result')"/>(){}
    public <xsl:value-of select="concat(@name, 'Result')"/>(<xsl:apply-templates select="outputs" mode="constructor-args"/>){<xsl:text>
</xsl:text>
      <xsl:apply-templates select="outputs" mode="constructor-body"/>
  }
}
  </xsl:result-document>


  <xsl:variable name="file2" select="concat(@name, '_helper', '.java')"/>
  <xsl:result-document href="{$file2}">
package com.raileurope.web.ws.soap;
import java.util.*;

public class <xsl:value-of select="@name"/> <xsl:text>{

  private </xsl:text>
  <xsl:value-of select="@name"/>()<xsl:text>{}
  
  static </xsl:text>
  <xsl:value-of select="@name"/>Result process(<xsl:apply-templates select="inputs"/>) { 
<xsl:text>  
    //TODO - implement the service 
    return null; 
  }
}
</xsl:text>
  </xsl:result-document>

  public <xsl:value-of select="@name"/>Result 
    do<xsl:value-of select="@name"/>(<xsl:apply-templates select="inputs"/>);  
</xsl:template>


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


<xsl:template match="complexTypes">
  <xsl:apply-templates/>
</xsl:template>


<xsl:template match="complexType">
  <xsl:variable name="file" select="concat(@name, '.java')"/>
  <xsl:result-document href="{$file}">
package com.raileurope.web.ws.soap;
import java.util.*;

public class <xsl:value-of select="@name"/> <xsl:text>{

</xsl:text>
    <xsl:apply-templates/>
<xsl:text>
</xsl:text>
    public <xsl:value-of select="@name"/>(){}
    public <xsl:value-of select="@name"/>(<xsl:apply-templates  mode="constructor-args"/>){<xsl:text>
</xsl:text>
      <xsl:apply-templates  mode="constructor-body"/>
  }
}
  </xsl:result-document>
</xsl:template>


<xsl:template match="inputs">
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="input">
  <xsl:value-of select="type"/><xsl:if test="type/@multiplicity ='true'">[]</xsl:if>
  <xsl:text> </xsl:text>
  <xsl:value-of select="name"/>
  <xsl:if test="position() != last()">, </xsl:if>
</xsl:template>

<xsl:template match="inputs" mode="call-method">
  <xsl:apply-templates mode="call-method"/>
</xsl:template>

<xsl:template match="input" mode="call-method">
  <xsl:value-of select="name"/>
  <xsl:if test="position() != last()">, </xsl:if>
</xsl:template>

<xsl:template match="inputs" mode="debug-info">
  <xsl:apply-templates mode="debug-info"/>
</xsl:template>

<xsl:template match="input" mode="debug-info">
  <xsl:text>debugInfo.put("</xsl:text><xsl:value-of select="name"/><xsl:text>", </xsl:text><xsl:value-of select="name"/><xsl:text>);
  </xsl:text>
</xsl:template>

<xsl:template match="outputs">
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="outputs" mode="constructor-args">
  <xsl:apply-templates mode="constructor-args"/>
</xsl:template>

<xsl:template match="outputs" mode="constructor-body">
  <xsl:apply-templates mode="constructor-body"/>
</xsl:template>

<xsl:template match="output">
  <xsl:text>  public </xsl:text>
  <xsl:value-of select="type"/><xsl:if test="type/@multiplicity ='true'">[]</xsl:if>
  <xsl:text> </xsl:text>
  <xsl:value-of select="name"/>;
  <xsl:text>
</xsl:text> 
</xsl:template>

<xsl:template match="output" mode="constructor-args">
  <xsl:value-of select="type"/><xsl:if test="type/@multiplicity ='true'">[]</xsl:if>
  <xsl:text> </xsl:text>
  <xsl:value-of select="name"/>
  <xsl:if test="position() != last()">, </xsl:if>
</xsl:template>

<xsl:template match="output" mode="constructor-body">
  <xsl:text>    this.</xsl:text>
  <xsl:value-of select="name"/>
  <xsl:text> = </xsl:text><xsl:value-of select="name"/>
  <xsl:text>;
</xsl:text> 
</xsl:template>


<xsl:template match="element">
  <xsl:text>public  </xsl:text>
  <xsl:value-of select="type"/><xsl:if test="type/@multiplicity ='true'">[]</xsl:if>
  <xsl:text> </xsl:text>
  <xsl:value-of select="name"/>
  <xsl:text>;
</xsl:text> 
</xsl:template>

<xsl:template match="element" mode="constructor-args">
  <xsl:value-of select="type"/><xsl:if test="type/@multiplicity ='true'">[]</xsl:if>
  <xsl:text> </xsl:text>
  <xsl:value-of select="name"/>
  <xsl:if test="position() != last()">, </xsl:if>
</xsl:template>

<xsl:template match="element" mode="constructor-body">
  <xsl:text>  this.</xsl:text>
  <xsl:value-of select="name"/>
  <xsl:text> = </xsl:text><xsl:value-of select="name"/>
  <xsl:text>;
</xsl:text> 
</xsl:template>

<xsl:template match="text()">
</xsl:template>

</xsl:stylesheet>