<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:saxon="http://saxon.sf.net/"
                extension-element-prefixes="saxon" 
  version="2.0">

<xsl:output method="html" omit-xml-declaration="yes"/>

<xsl:strip-space elements="*"/>

<xsl:template match="/">
  <html>
    <head>
      <title>API Documentaion - New Generation Webservices</title>
    </head>  

    <body>
      <xsl:apply-templates/>
    </body>
      <script language="javascript">
        var version = window.location.search;
        if(version.length &gt; 1) {
          version = version.substring(1, version.length);
          var listitems= document.getElementsByTagName("tr");
          for (i=0; i &lt; listitems.length; i++) {
            if (listitems[i].getAttribute("v") == version) {
              listitems[i].style.backgroundColor = "lightgreen";   
            }
            else {
              listitems[i].style.backgroundColor = "white";   
            }
          }
        }
      </script>    
  </html> 
</xsl:template>


<xsl:template match="services">
  <table border="0" width="860">
   <tr>
    <td>
      <table border="0" width="95%">
        <tr>
          <td><strong>List of Services Offered:</strong></td>
        </tr>
        <xsl:for-each select="service">
          <tr>
            <td>
              <xsl:number level="any"/><xsl:text>.</xsl:text>&#160;&#160;
              <a href="{concat('#', translate(@name, ' ', '_'))}"><xsl:value-of select="@name"/></a>
            </td>
          </tr>
        </xsl:for-each> 
      </table> 
    </td>
   </tr>

   <xsl:apply-templates/>

  </table>
</xsl:template>


<xsl:template match="service">
  <tr>
    <td>
      <table border="0" width="95%">           
        <tr><td>&#160;</td></tr>
        <tr>
          <td>
            <strong>
              <xsl:number level="any"/><xsl:text>.</xsl:text>&#160;&#160;
              <a name="{translate(@name, ' ', '_')}"/>
              <xsl:value-of select="@name"/>
            </strong>
          </td>
        </tr>

        <xsl:apply-templates/>
      </table>                   
    </td>
  </tr>
</xsl:template>


<xsl:template match="service-description">
  <tr><td><p><xsl:apply-templates/></p></td></tr>
</xsl:template>

<xsl:template match="*[@reference]">
  <xsl:apply-templates select="saxon:evaluate(@reference)"/>
<!--
  <tr><td>&#160;</td></tr> 
  <tr>
    <td>
      <strong>
        <i>
          <xsl:value-of select="concat(translate(substring(local-name(.), 1, 1), 'ioe', 'IOE'), substring(local-name(.), 2))" />
          same as 
          <a href="{concat('#', translate(@sameAs, ' ', '_'))}"> 
            <xsl:value-of select="@sameAs"/>
          </a> service
        </i>
      </strong>
    </td>
  </tr>
  <tr><td>&#160;</td></tr>
-->
</xsl:template>

<xsl:template match="inputs">
  <tr><td><strong><i>Input Parameters</i></strong></td></tr>

  <tr>
    <td>
      <table border="2" bordercolor="lightblue" width="100%" bgcolor="#EEEEFF">
        <tr bgcolor="lightblue">
          <td>Name</td>   
          <td>Type</td>
          <td>Description</td>
          <td>Examples</td> 
          <td>Is Optional</td>
        </tr>
 
        <xsl:apply-templates/>

      </table>
    </td>
  </tr>
</xsl:template>

<xsl:template match="outputs">
  <tr><td><strong><i>Output Parameters</i></strong></td></tr>

  <tr>
    <td>
      <table border="2" bordercolor="lightblue" width="100%" bgcolor="#EEEEFF">
        <tr bgcolor="lightblue">
          <td>Name</td>   
          <td>Type</td>
          <td>Description</td>
          <td>Examples</td> 
        </tr>
 
        <xsl:apply-templates/>

      </table>
    </td>
  </tr>
</xsl:template>

<xsl:template match="input | output">
  <tr>
    <xsl:if test="isOptional = 'false'">
      <xsl:attribute name="bgcolor">yellow</xsl:attribute>
    </xsl:if>
    <xsl:if test="modifiedVersion">
      <xsl:attribute name="v">
        <xsl:value-of select="modifiedVersion"/>
      </xsl:attribute>
    </xsl:if>      
    <xsl:apply-templates/>
  </tr>  
</xsl:template>

<xsl:template match="errorsAndMessages">
  <tr><td><strong><i>Errors and Messages</i></strong></td></tr>

  <tr>
    <td>
      <table border="2" bordercolor="lightblue" width="100%" bgcolor="#EEEEFF">
        <tr bgcolor="lightblue">
          <td>Code</td>   
          <td>Description</td>
        </tr>
 
        <xsl:apply-templates/>

      </table>
    </td>
  </tr>  
</xsl:template>

<xsl:template match="codeAndDescription">
  <tr>
    <xsl:apply-templates/>
  </tr>
</xsl:template>

<xsl:template match="type[@multiplicity]">
  <td><xsl:apply-templates/> Array</td>
</xsl:template>

<xsl:template match="name | type | isOptional | description | examples | code">
  <td><xsl:apply-templates/>&#160;</td>   
</xsl:template>

<xsl:template match="modifiedVersion">
</xsl:template>

<xsl:template match="complex">
  <a href="{concat('#Type_', translate(., ' ', '_'))}"><xsl:apply-templates/></a>
</xsl:template>

<xsl:template match="complexTypes">
  <table border="0" width="860">
    <tr><td>&#160;</td></tr>
    <tr><td>&#160;</td></tr>
    <tr><td>&#160;</td></tr>
    <tr><td>&#160;</td></tr>
    <tr><td>&#160;</td></tr>
    <tr><td valign="bottom"><strong>Type Definitions</strong></td></tr> 
    <xsl:apply-templates/>    
  </table>
</xsl:template>

<xsl:template match="complexType">
  <tr><td><a name="{concat('Type_', translate(@name, ' ', '_'))}"/><strong><i><xsl:value-of select="@name"/></i></strong></td></tr>
  <tr>
    <td>
      <table border="2" bordercolor="orange" width="100%" bgcolor="#FFEEEE"> 
        <tr  bgcolor="orange">
          <td>Name</td>
          <td>Type</td>
          <td>Description</td>
          <td>Example</td> 
        </tr>
        <xsl:apply-templates/> 
      </table>
    </td>
  </tr>   
  <tr><td>&#160;</td></tr>
  <tr><td>&#160;</td></tr>
  <tr><td>&#160;</td></tr>
  <tr><td>&#160;</td></tr>
  <tr><td>&#160;</td></tr>
  <tr><td>&#160;</td></tr>
</xsl:template>

<xsl:template match="element">
  <tr>
    <xsl:if test="modifiedVersion">
      <xsl:attribute name="v">
        <xsl:value-of select="modifiedVersion"/>
      </xsl:attribute>
    </xsl:if>        
    <xsl:apply-templates/>
  </tr>
</xsl:template>

<xsl:template match="values">
  The valid values are<br/>
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="value">
  <li><xsl:apply-templates/></li>
</xsl:template>

<xsl:template match="a[@href]">
  <a>
    <xsl:attribute name="href"><xsl:value-of select="@href"/></xsl:attribute>
    <xsl:apply-templates/>
  </a>    
</xsl:template>

<xsl:template match="a[@name]">
  <a>
    <xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
  </a>    
</xsl:template>

<xsl:template match="text()"><xsl:value-of select="."/></xsl:template>

</xsl:stylesheet>
