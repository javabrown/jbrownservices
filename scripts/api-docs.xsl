<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="no" omit-xml-declaration="yes" />
	<xsl:strip-space elements="*" />
	
	<ParameterBinding Name="url_PATH_INFO" Location="ServerVariable(PATH_INFO)" DefaultValue="localhost"/>
	<ParameterBinding Name="url_HTTP_HOST" Location="ServerVariable(HTTP_HOST)" DefaultValue="localhost"/>
  
  	<xsl:param name="url_PATH_INFO" />
	<xsl:param name="url_HTTP_HOST" />

	<xsl:variable name="CurrentPageUrl" select="concat('http://',$url_HTTP_HOST,$url_PATH_INFO)" />

	<xsl:template match="/">
		<xsl:apply-templates/>
	</xsl:template>
	
	<!--  **  -->
	<xsl:template match="mobile-api">
	
		<!-- xsl:call-template name="api-header" / -->
		<xsl:for-each select="service">
			<xsl:variable name="var"><xsl:value-of select="description" /></xsl:variable>
			
				<html>
					<head>
						<xsl:call-template name="html-header"/>
					</head>
					
					<body>
						<center><h1 class="style-five"><xsl:value-of select="$var"/></h1></center>
						<hr/> 
						<xsl:call-template name="make-service-body" />
					</body>			
				</html>
		</xsl:for-each>

	</xsl:template>
	
	<!-- **** -->
	
	<xsl:template name="make-service-body">
		
			<table id="apiList" class="display cell-border" width="100%" cellspacing="0">
			        <thead>
			            <tr>
			                <th>Service Name</th>
			                <th>REQUEST-TYPE</th>
			                <th>URI</th>
			                <th>Version</th>
			                <th>Description</th>
			            </tr>
			        </thead>
			 
			        <tfoot>
			            <tr>
			                <th>Service Name</th>
			                <th>REQUEST-TYPE</th>
			                <th>URI</th>
			                <th>Version</th>
			                <th>Description</th>
			            </tr>
			        </tfoot>
			 
			        <tbody>
			        
						<xsl:for-each select="web-methods/web-method">
							<xsl:variable name="service-name"><xsl:value-of select="name"/></xsl:variable>
							<xsl:variable name="service-version"><xsl:value-of select="version"/></xsl:variable>
							<xsl:variable name="service-request-type"><xsl:value-of select="request-type"/></xsl:variable>
							<xsl:variable name="service-mapping-uri"><xsl:value-of select="mapping-uri"/></xsl:variable>
							<xsl:variable name="service-description"><xsl:value-of select="description"/></xsl:variable>
					            <tr>
					                <td><xsl:value-of select="$service-name"/></td>
					                <td><xsl:value-of select="$service-request-type"/></td>
					                <td><xsl:value-of select="$CurrentPageUrl" /> / <xsl:value-of select="$service-mapping-uri"/></td>
					                <td><xsl:value-of select="$service-version"/></td>
					                <td><xsl:value-of select="$service-description"/></td>
					            </tr>
						</xsl:for-each>
						
			       </tbody>
			</table>
			<br/> 						
	</xsl:template>
	
	 
	<xsl:template name="html-header">
   	   <link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css" media="screen" />
   	   <link rel="stylesheet" href="https://cdn.datatables.net/plug-ins/3cfcc339e89/integration/jqueryui/dataTables.jqueryui.css" type="text/css" media="screen" />
   	   
       <script src="https://code.jquery.com/jquery-1.11.1.min.js"/>
       <script src="https://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"/>
       <script src="https://cdn.datatables.net/plug-ins/3cfcc339e89/integration/jqueryui/dataTables.jqueryui.js"/>
       <script>
       		$(document).ready(function() {
   				 $('#apiList').dataTable();
			} );
       </script>
       
       <style>
				 hr {
				    height: 1px;
				    color: #ed1d61;
				    background: #ed1d61;
				    font-size: 0;
				    border: 0;
				}
       </style>
	</xsl:template>
	
</xsl:stylesheet>