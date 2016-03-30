<html><head><META http-equiv="Content-Type" content="text/html; charset=UTF-8"><link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css" media="screen"><link rel="stylesheet" href="https://cdn.datatables.net/plug-ins/3cfcc339e89/integration/jqueryui/dataTables.jqueryui.css" type="text/css" media="screen"><link rel="stylesheet" href=" http://getbootstrap.com/2.3.2/assets/css/bootstrap.css" type="text/css" media="screen"><link rel="stylesheet" href="http://jschr.github.io/bootstrap-modal/css/bootstrap-modal.css" type="text/css" media="screen"><script src="https://code.jquery.com/jquery-1.11.1.min.js"></script><script src="https://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script><script src="https://cdn.datatables.net/plug-ins/3cfcc339e89/integration/jqueryui/dataTables.jqueryui.js"></script><script src="http://getbootstrap.com/2.3.2/assets/js/bootstrap.js"></script><script src="http://jschr.github.io/bootstrap-modal/js/bootstrap-modal.js"></script><script src="http://jschr.github.io/bootstrap-modal/js/bootstrap-modalmanager.js"></script><script>
       		$(document).ready(function() {
       			 var host = window.location.host ? window.location.host : "localhost:8080";
   				 var wsHost = window.location.protocol + "//" + host + "/api/ws/v";
   				 $('.uri').html(wsHost);
   			     prepareDialog();
   			     
   			     //alert('done');
   			     $('#apiList').dataTable();
			} );
			
			function prepareDialog() {
					$('.launchSample').on('click', function (e) {
					    //var sampleRequest = $(this).find('.sample-request').html();
					    var sampleRequestHeader = $(this).find('.sample-request-header').html();
					    var sampleRequestBody = $(this).find('.sample-request-body').html();
					    
					    //read uri by peices
					    var sampleRequestUri1 =  $(this).find('.uri').html();
					    var sampleRequestUri2 =  $(this).find('.uri1').html();
					    
					    if(sampleRequestHeader){
					      sampleRequestHeader = sampleRequestHeader.replace(/\s/g, '');
					      sampleRequestHeader = JSON.parse(sampleRequestHeader);
					      sampleRequestHeader = JSON.stringify(sampleRequestHeader, null, 4)
					    }
					    
					    if(sampleRequestBody){
					      sampleRequestBody = sampleRequestBody.replace(/\s/g, '');
					      sampleRequestBody = JSON.parse(sampleRequestBody);
					      sampleRequestBody = JSON.stringify( sampleRequestBody, null, 4);
					    }
					     
					    $('#service-description-header').html(sampleRequestHeader);
					    $('#service-description-body').html(sampleRequestBody);
					    
						   $('#service-uri').html( sampleRequestUri1+sampleRequestUri2);
						   //alert(sampleRequestUri1+sampleRequestUri2);
	
	
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
       </script><style>
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
      </style></head><body class="well transparent"><div class="well transparent"><center><div class="jumbotron"><h1>JavaBrown API</h1><p>Open Source API by JavaBrown Foundation</p></div></center><hr><div class="page-container"><table id="apiList" class="display cell-border" width="100%" cellspacing="0"><thead><tr><th>SERVICE-NAME</th><th>REQUEST-TYPE</th><th>URI</th><th>VERSION</th><th>DESCRIPTION</th></tr></thead><tfoot><tr><th>SERVICE-NAME</th><th>REQUEST-TYPE</th><th>URI</th><th>VERSION</th><th>DESCRIPTION</th></tr></tfoot><tbody><tr><td>register</td><td>POST</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/user/register</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request"> 
				      {
				        "name" : "User Name",
				        "email" : "email@domain.com",
				        "phone" : "123456",
				        "password" : "xxxxx",
				        "domain" : "www.yourdomain.com"
				      }
				    </sample><sample-request-header class="hide sample-request-header"></sample-request-header><sample-request-body class="hide sample-request-body"> 
				      {
				        "name" : "User Name",
				        "email" : "email@domain.com",
				        "phone" : "123456",
				        "password" : "xxxxx",
				        "domain" : "www.yourdomain.com"
				      }
				    </sample-request-body></div></td><td>1</td><td>Register a new user's account.</td></tr><tr><td>getUserInfo</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/user/info/{userName}/{email}</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				    {
				     "user" : "raja_khan"
				    }
				    </sample><sample-request-header class="hide sample-request-header"></sample-request-header><sample-request-body class="hide sample-request-body">
				    {
				     "user" : "raja_khan"
				    }
				    </sample-request-body></div></td><td>1</td><td>Register new account.</td></tr><tr><td>auth</td><td>POST</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/user/auth</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				     {
				        "email" : "email@domain.com",
				        "password" : "xxxxx",
				        "auth-code" : "xxxxx"
				     }
				    </sample><sample-request-header class="hide sample-request-header"></sample-request-header><sample-request-body class="hide sample-request-body">
				     {
				        "email" : "email@domain.com",
				        "password" : "xxxxx",
				        "auth-code" : "xxxxx"
				     }
				    </sample-request-body></div></td><td>1</td><td>Authorize an account.</td></tr><tr><td>logout</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/user/logout</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request"></sample><sample-request-header class="hide sample-request-header"></sample-request-header><sample-request-body class="hide sample-request-body"></sample-request-body></div></td><td>1</td><td>Invalidate the active user session</td></tr><tr><td>getIsoCountries</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/countryinfo</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">{ "access_token" : "facebook-token-from-jbrown-web-app" }</sample><sample-request-header class="hide sample-request-header">{ "access_token" : "facebook-token-from-jbrown-web-app" }</sample-request-header><sample-request-body class="hide sample-request-body"></sample-request-body></div></td><td>1</td><td>Register new account.</td></tr><tr><td>getCountryInfo</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/countryinfo/{countryName}</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">{ "access_token" : "facebook-token-from-jbrown-web-app" }http://localhost:8080/jbrownservices/api/ws/v1/countryinfo/in/</sample><sample-request-header class="hide sample-request-header">{ "access_token" : "facebook-token-from-jbrown-web-app" }</sample-request-header><sample-request-body class="hide sample-request-body">http://localhost:8080/jbrownservices/api/ws/v1/countryinfo/in/</sample-request-body></div></td><td>1</td><td>Register new account.</td></tr><tr><td>getStateInfo</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/countryinfo/{countryName}/{stateName}</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">{ "access_token" : "facebook-token-from-jbrown-web-app" }http://localhost:8080/jbrownservices/api/ws/v1/countryinfo/in/up/</sample><sample-request-header class="hide sample-request-header">{ "access_token" : "facebook-token-from-jbrown-web-app" }</sample-request-header><sample-request-body class="hide sample-request-body">http://localhost:8080/jbrownservices/api/ws/v1/countryinfo/in/up/</sample-request-body></div></td><td>1</td><td>Register new account.</td></tr><tr><td>getCityInfo</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/countryinfo/{countryName}/{stateName}/{cityName}</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">{ "access_token" : "facebook-token-from-jbrown-web-app" }http://localhost:8080/jbrownservices/api/ws/v1/countryinfo/in/up/agra/</sample><sample-request-header class="hide sample-request-header">{ "access_token" : "facebook-token-from-jbrown-web-app" }</sample-request-header><sample-request-body class="hide sample-request-body">http://localhost:8080/jbrownservices/api/ws/v1/countryinfo/in/up/agra/</sample-request-body></div></td><td>1</td><td>Register new account.</td></tr><tr><td>getAllAirport</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/airport</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">{ "access_token" : "facebook-token-from-jbrown-web-app" }http://localhost:8080/jbrownservices/api/ws/v1/airport</sample><sample-request-header class="hide sample-request-header">{ "access_token" : "facebook-token-from-jbrown-web-app" }</sample-request-header><sample-request-body class="hide sample-request-body">http://localhost:8080/jbrownservices/api/ws/v1/airport</sample-request-body></div></td><td>1</td><td>Return Airport Info by IATA or airport code.</td></tr><tr><td>getAirporByIata</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/airport/{iata}</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">{ "access_token" : "facebook-token-from-jbrown-web-app" }http://localhost:8080/jbrownservices/api/ws/v1/airport/jfk</sample><sample-request-header class="hide sample-request-header">{ "access_token" : "facebook-token-from-jbrown-web-app" }</sample-request-header><sample-request-body class="hide sample-request-body">http://localhost:8080/jbrownservices/api/ws/v1/airport/jfk</sample-request-body></div></td><td>1</td><td>Return Airport Info by IATA or airport code.</td></tr><tr><td>getDistance</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/distance/location-a/{countryName1}/location-b/{countryName1}</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request"></sample><sample-request-header class="hide sample-request-header"></sample-request-header><sample-request-body class="hide sample-request-body"></sample-request-body></div></td><td>1</td><td>
					Calculate the distance between the two locations
				</td></tr><tr><td>getLineChart</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">2/ui/linechart</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request"></sample><sample-request-header class="hide sample-request-header"></sample-request-header><sample-request-body class="hide sample-request-body"></sample-request-body></div></td><td>2</td><td>Generate Chart Line</td></tr><tr><td>encode</td><td>POST</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/util/encode</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request"></sample><sample-request-header class="hide sample-request-header"></sample-request-header><sample-request-body class="hide sample-request-body"></sample-request-body></div></td><td>1</td><td>Generate Chart Line</td></tr><tr><td>getPublicPhotos</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/media/search-images/{pattern}/{size}</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">{ "access_token" : "facebook-token-from-jbrown-web-app" }http://localhost:8080/jbrownservices/api/ws/v1/media/search-images/raja/50px</sample><sample-request-header class="hide sample-request-header">{ "access_token" : "facebook-token-from-jbrown-web-app" }</sample-request-header><sample-request-body class="hide sample-request-body">http://localhost:8080/jbrownservices/api/ws/v1/media/search-images/raja/50px</sample-request-body></div></td><td>1</td><td>Find image services</td></tr><tr><td>getAllPhotoSizeConstants</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/media/all-image-size</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request"></sample><sample-request-header class="hide sample-request-header"></sample-request-header><sample-request-body class="hide sample-request-body"></sample-request-body></div></td><td>1</td><td>Return the list of available search constants
				</td></tr><tr><td>textToAudio</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/media/text-to-audio</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request"></sample><sample-request-header class="hide sample-request-header"></sample-request-header><sample-request-body class="hide sample-request-body"></sample-request-body></div></td><td>1</td><td>Convert Text to Audio
				</td></tr><tr><td>setCache</td><td>POST</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/data/cache/set</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">{ "access_token" : "facebook-token-from-jbrown-web-app" }
				       {
				         "cache_key" : "Enter-Cache-Key",
				         "cache_value" : "Enter Cache Value"
				      }
				    </sample><sample-request-header class="hide sample-request-header">{ "access_token" : "facebook-token-from-jbrown-web-app" }</sample-request-header><sample-request-body class="hide sample-request-body">
				       {
				         "cache_key" : "Enter-Cache-Key",
				         "cache_value" : "Enter Cache Value"
				      }
				    </sample-request-body></div></td><td>1</td><td>Cache Store Service</td></tr><tr><td>getCache</td><td>POST</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/data/cache/get</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				      {  "access_token" : "facebook-token-from-jbrown-web-app" }
				    
				      {
				        "cache_key" : "Enter-Cache-Key"
				      }		    
				    </sample><sample-request-header class="hide sample-request-header">
				      {  "access_token" : "facebook-token-from-jbrown-web-app" }
				    </sample-request-header><sample-request-body class="hide sample-request-body">
				      {
				        "cache_key" : "Enter-Cache-Key"
				      }		    
				    </sample-request-body></div></td><td>1</td><td>Cache Fetch Service</td></tr><tr><td>getAllQuestions</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span><span class="uri1">1/data/quiz/question/all</span><button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">{  "access_token" : "facebook-token-from-jbrown-web-app" }</sample><sample-request-header class="hide sample-request-header">{  "access_token" : "facebook-token-from-jbrown-web-app" }</sample-request-header><sample-request-body class="hide sample-request-body"></sample-request-body></div></td><td>1</td><td>Cache Store Service</td></tr></tbody></table><br><div id="confirm" class="modal hide fade"><div class="modal-body"><p class="dialog-msg">-</p><table class="table table-condensed"><tr><td class="info"><samp><b>URL:</b></samp></td><td class="active"><samp id="service-uri" class="form-control" rows="1"></samp></td></tr><tr><td class="info"><samp><b>Header:</b></samp></td><td class="active"><samp id="service-description-header" class="form-control" rows="2"></samp></td></tr><tr><td class="info"><samp><b>Body:</b></samp></td><td class="success"><samp id="service-description-body" class="form-control" rows="3"></samp></td></tr></table></div><div class="modal-footer"><button type="button" data-dismiss="modal" class="btn" data-value="0">Cancel</button></div></div></div></div></body></html>