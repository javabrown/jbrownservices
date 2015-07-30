<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="no" omit-xml-declaration="yes" />
	<xsl:strip-space elements="*" />
	
	<ParameterBinding Name="url_PATH_INFO" Location="ServerVariable(PATH_INFO)" DefaultValue=""/>
	<ParameterBinding Name="url_HTTP_HOST" Location="ServerVariable(HTTP_HOST)" DefaultValue=""/>
  
  	<xsl:param name="url_PATH_INFO" />
	<xsl:param name="url_HTTP_HOST" />

	<xsl:variable name="CurrentPageUrl" select="concat('http://',$url_HTTP_HOST,$url_PATH_INFO)" />
    <xsl:variable name="CurrentPageUrl1" select="concat('http://',$url_HTTP_HOST,$url_PATH_INFO)" /> 

	<xsl:param name="hostName"/>
	<xsl:param name="domain"/>
	
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
					
					<body class="well transparent">
						<div class="well transparent">
							<center>
							    <div class="jumbotron">
									<h1><xsl:value-of select="$var"/></h1>      
									<p>Open Source API by JavaBrown Foundation</p>
							    </div>
						    </center>
						    						
							<hr/>
							<xsl:call-template name="make-service-body" />
						</div>
					</body>
				</html>
		</xsl:for-each>

	</xsl:template>
	
	<!-- **** -->
	
	<xsl:template name="make-service-body">
       <div class="page-container">
            
						    
					<table id="apiList" class="display cell-border" width="100%" cellspacing="0">
					        <thead>
					            <tr>
					                <th>SERVICE-NAME</th>
					                <th>REQUEST-TYPE</th>
					                <th>URI</th>
					                <th>VERSION</th>
					                <th>DESCRIPTION</th>
					            </tr>
					        </thead>
					 
					        <tfoot>
					            <tr>
					                <th>SERVICE-NAME</th>
					                <th>REQUEST-TYPE</th>
					                <th>URI</th>
					                <th>VERSION</th>
					                <th>DESCRIPTION</th>
					            </tr>
					        </tfoot>
					 
					        <tbody>
					        
								<xsl:for-each select="web-methods/web-method">
									<xsl:variable name="service-name"><xsl:value-of select="name"/></xsl:variable>
									<xsl:variable name="service-version"><xsl:value-of select="version"/></xsl:variable>
									<xsl:variable name="service-request-type"><xsl:value-of select="request-type"/></xsl:variable>
									<xsl:variable name="service-mapping-uri"><xsl:value-of select="mapping-uri"/></xsl:variable>
									<xsl:variable name="sample-request"><xsl:value-of select="sample-request"/></xsl:variable>
									<xsl:variable name="service-full-uri">http://javabrown.com/jbrownservices/api/ws/v<xsl:value-of select="version"/><xsl:value-of select="$service-mapping-uri"/></xsl:variable>
									<xsl:variable name="js-backed-service-uri-block"><xsl:value-of select="version"/><xsl:value-of select="$service-mapping-uri"/></xsl:variable>
									
									<xsl:variable name="service-description"><xsl:value-of select="description"/></xsl:variable>
							            <tr>
							                <td><xsl:value-of select="$service-name"/></td>
							                <td><xsl:value-of select="$service-request-type"/></td>
							                <!-- td><xsl:value-of select="$service-full-uri"/></td -->
							                <td>
							                    
							                	<div class="launchSample">						                	    
								                	<span class='uri'>{{Filled by JS}}</span><xsl:value-of select="$js-backed-service-uri-block"/>
								                	
								                	<button class="btn launchSample">
	  							                	  <i class="icon-search icon-green"></i>
								                	</button>
								                	<sample  class="hide sample-request"><xsl:value-of select='$sample-request'/></sample>
								                </div>
								             
							                </td>
							                <td><xsl:value-of select="$service-version"/></td>
							                <td><xsl:value-of select="$service-description"/></td>
							            </tr>
								</xsl:for-each>

								

					       </tbody>
					</table>
					
					<br/> 	
					
					 <div id="confirm" class="modal hide fade">
						<div class="modal-body">
							<p class="dialog-msg">-</p>
						</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal" class="btn" data-value="0">Cancel</button>
						</div>
					</div>
						
			 
		</div>					
	</xsl:template>
	
	 
	<xsl:template name="html-header">
   	   <link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css" media="screen" />
   	   <link rel="stylesheet" href="https://cdn.datatables.net/plug-ins/3cfcc339e89/integration/jqueryui/dataTables.jqueryui.css" type="text/css" media="screen" />
   	   <link rel="stylesheet" href=" http://getbootstrap.com/2.3.2/assets/css/bootstrap.css" type="text/css" media="screen" />
   	   <link rel="stylesheet" href="http://jschr.github.io/bootstrap-modal/css/bootstrap-modal.css" type="text/css" media="screen" />
   	  
       <script src="https://code.jquery.com/jquery-1.11.1.min.js"/>
       <script src="https://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"/>
       <script src="https://cdn.datatables.net/plug-ins/3cfcc339e89/integration/jqueryui/dataTables.jqueryui.js"/>
       <script src="http://getbootstrap.com/2.3.2/assets/js/bootstrap.js"/>
       <script src="http://jschr.github.io/bootstrap-modal/js/bootstrap-modal.js"/>
       <script src="http://jschr.github.io/bootstrap-modal/js/bootstrap-modalmanager.js"/>
       
       
       <script>
       		$(document).ready(function() {
       			 var host = window.location.host ? window.location.host : "localhost:8080";
   				 var wsHost = window.location.protocol + "//" + host + "/jbrownservices/api/ws/v";
   				 $('.uri').html(wsHost);
   			     prepareDialog();
   			     
   			     //alert('done');
   			     $('#apiList').dataTable();
			} );
			
			function prepareDialog() {
				$('.launchSample').on('click', function (e) {
				    var sampleRequest = $(this).find('.sample-request').html();
				    $('.dialog-msg').html(sampleRequest);
				    
				    $('#confirm')
				        .modal({ backdrop: 'static', keyboard: false })
				        .one('click', '[data-value]', function (e) {
				            if($(this).data('value')) {
				                //alert('confirmed');
				            } else {
				                //alert('canceled');
				            }
				        });
				});				
			}
       </script>
       
       <style>
			hr {
				height: 1px;
				color: #ed1d61;
				background: #ed1d61;
				font-size: 0;
				border: 0;
			}
			
			.hide{
			  display:none;
			}
			
			body,
			.modal-open .page-container,
			.modal-open .page-container .navbar-fixed-top,
			.modal-open .modal-container {
				overflow-y: scroll;
			}
			
			@media (max-width: 979px) {
				.modal-open .page-container .navbar-fixed-top{
					overflow-y: visible;
				}
			}
			
			* {
			  box-sizing: border-box;
			}
			
			.background-image {
			  background-image: url('https://raw.githubusercontent.com/javabrown/jbrownservices/master/war/WEB-INF/icons/jb-api.jpg');
			  background-size: cover;
			  display: block;
			  filter: blur(5px);
			  -webkit-filter: blur(5px);
			  height: 800px;
			  left: 0;
			  position: fixed;
			  right: 0;
			  z-index: 1;
			}
			
			html {
            background: url('https://raw.githubusercontent.com/javabrown/jbrownservices/master/war/WEB-INF/icons/jb-api.jpg') no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
           }
			
			.content {
			  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
			}			
			
		    .transparent {
               opacity: 0.9;
            }
      </style>
      
	</xsl:template>
	
</xsl:stylesheet>