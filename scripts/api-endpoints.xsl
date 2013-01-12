<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- @author-rkhan -->

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" indent="no" omit-xml-declaration="yes" />
	<xsl:strip-space elements="*" />

	<xsl:template match="/|*">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template name="endl">
		<xsl:text></xsl:text>
	</xsl:template>

 
 
	<xsl:template name="get-params">
	  <xsl:variable name="var">
	    <xsl:for-each select="web-params/web-param">
	      @PathVariable <xsl:value-of select="." />,</xsl:for-each>
	  </xsl:variable>
	  <xsl:text>(</xsl:text><xsl:value-of select="$var" />HttpServletRequest req, HttpServletResponse res, ModelMap model<xsl:text>);</xsl:text>
	</xsl:template>
	
 <xsl:template name="post-params">
   <xsl:variable name="var">
      <xsl:text>
      (@RequestBody String body, HttpServletRequest req, HttpServletResponse res);
      </xsl:text>   
   </xsl:variable>
   
   <xsl:value-of select="$var" />
 </xsl:template>	

	<xsl:template name="getwebmethods">
		<xsl:for-each select="web-methods/web-method">
		  <xsl:variable name="requesttype"><xsl:value-of select="request-type" /></xsl:variable>
    <xsl:variable name="version">v<xsl:value-of select="version" /></xsl:variable>
    <xsl:variable name="uri"><xsl:value-of select="mapping-uri" /></xsl:variable>
    <xsl:variable name="processed_uri">
          <xsl:value-of select="concat('/', $version, $uri)"/>
    </xsl:variable>
    
    <xsl:if test="$requesttype='GET'">
     @RequestMapping(value = "<xsl:value-of select="$processed_uri" />", method = RequestMethod.<xsl:value-of select="$requesttype"/>)
     public ModelAndView <xsl:value-of select="name" /> <xsl:call-template name="get-params" />
    </xsl:if>
    
    <xsl:if test="$requesttype='POST'">
     @RequestMapping(value = "<xsl:value-of select="$processed_uri" />", method = RequestMethod.<xsl:value-of select="$requesttype"/>)    
     public ModelAndView <xsl:value-of select="name" /> <xsl:call-template name="post-params" />
    </xsl:if>
		</xsl:for-each>
	</xsl:template>


	<xsl:template match="mobile-api">


		<xsl:call-template name="api-header" />
		<xsl:for-each select="service">
			<xsl:variable name="var">
				<xsl:value-of select="name" />
			</xsl:variable>
@Controller
@RequestMapping("/ws")
public interface <xsl:value-of select="$var" />{
			<xsl:call-template name="getwebmethods" />
}
			<xsl:call-template name="endl" />
		</xsl:for-each>

	</xsl:template>
	



	<xsl:template name="api-header">
package com.raileurope.web.mobile.ws.gen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

/**
 * @author rkhan
 * @category API
 * 
 * This file was auto-generated from api-endpoints.xml during the project build.
 * Please do not edit directly.
 */
 
	</xsl:template>


</xsl:stylesheet>
