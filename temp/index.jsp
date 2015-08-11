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
      </style></head><body class="well transparent"><div class="well transparent"><center><div class="jumbotron"><h1>JavaBrown API</h1><p>Open Source API by JavaBrown Foundation</p></div></center><hr><div class="page-container"><table id="apiList" class="display cell-border" width="100%" cellspacing="0"><thead><tr><th>SERVICE-NAME</th><th>REQUEST-TYPE</th><th>URI</th><th>VERSION</th><th>DESCRIPTION</th></tr></thead><tfoot><tr><th>SERVICE-NAME</th><th>REQUEST-TYPE</th><th>URI</th><th>VERSION</th><th>DESCRIPTION</th></tr></tfoot><tbody><tr><td>register</td><td>POST</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/user/register<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				    header=&gt;{
				     
				    }
				    body=&gt;
				    {
				        "name" : "User Name",
				        "email" : "email@domain.com",
				        "phone" : "123456",
				        "password" : "xxxxx",
				        "domain" : "www.yourdomain.com"
				    }
				</sample></div></td><td>1</td><td>Register a new user's account.</td></tr><tr><td>getUserInfo</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/user/info/{userName}/{email}<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				    {
				     "user" : "raja_khan"
				    }
				</sample></div></td><td>1</td><td>Register new account.</td></tr><tr><td>auth</td><td>POST</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/user/auth<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				     header=&gt;{
				     
				    }
				    body=&gt; 
				    {
				        "email" : "email@domain.com",
				        "password" : "xxxxx",
				        "auth-code" : "xxxxx"
				    }
				</sample></div></td><td>1</td><td>Authorize an account.</td></tr><tr><td>getIsoCountries</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/countryinfo<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				     header=&gt;{
				     "access_token" : "facebook-token-from-jbrown-web-app"
				    }
				    body=&gt;{
				     empty
				    }
				</sample></div></td><td>1</td><td>Register new account.</td></tr><tr><td>getCountryInfo</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/countryinfo/{countryName}<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				    header=&gt;{
				     "access_token" : "facebook-token-from-jbrown-web-app"
				    }
				    body=&gt;{
				      http://localhost:8080/jbrownservices/api/ws/v1/countryinfo/in/
				    }
				</sample></div></td><td>1</td><td>Register new account.</td></tr><tr><td>getStateInfo</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/countryinfo/{countryName}/{stateName}<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				 	header=&gt;{
				     "access_token" : "facebook-token-from-jbrown-web-app"
				    }
				    GET-SAMPLE=&gt;{
				      http://localhost:8080/jbrownservices/api/ws/v1/countryinfo/in/up/
				    }
				</sample></div></td><td>1</td><td>Register new account.</td></tr><tr><td>getCityInfo</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/countryinfo/{countryName}/{stateName}/{cityName}<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				 	header=&gt;{
				     "access_token" : "facebook-token-from-jbrown-web-app"
				    }
				    GET-SAMPLE=&gt;{
				      http://localhost:8080/jbrownservices/api/ws/v1/countryinfo/in/up/agra/
				    }				
				</sample></div></td><td>1</td><td>Register new account.</td></tr><tr><td>getAllAirport</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/airport<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
					header=&gt;{
				     "access_token" : "facebook-token-from-jbrown-web-app"
				    }
				    GET-SAMPLE=&gt;{
				      http://localhost:8080/jbrownservices/api/ws/v1/airport
				    }
				</sample></div></td><td>1</td><td>Return Airport Info by IATA or airport code.</td></tr><tr><td>getAirporByIata</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/airport/{iata}<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
					header=&gt;{
				     "access_token" : "facebook-token-from-jbrown-web-app"
				    }
				    GET-SAMPLE=&gt;{
				      http://localhost:8080/jbrownservices/api/ws/v1/airport/jfk
				    }
				</sample></div></td><td>1</td><td>Return Airport Info by IATA or airport code.</td></tr><tr><td>getDistance</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/distance/location-a/{countryName1}/location-b/{countryName1}<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request"></sample></div></td><td>1</td><td>
					Calculate the distance between the two locations
				</td></tr><tr><td>getLineChart</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>2/ui/linechart<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request"></sample></div></td><td>2</td><td>Generate Chart Line</td></tr><tr><td>encode</td><td>POST</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/util/encode<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request"></sample></div></td><td>1</td><td>Generate Chart Line</td></tr><tr><td>getPublicPhotos</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/media/search-images/{pattern}/{size}<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				   header=&gt;{
				     "access_token" : "facebook-token-from-jbrown-web-app"
				    }
				    GET Sample URL=&gt;{
				      http://localhost:8080/jbrownservices/api/ws/v1/media/search-images/raja/50px
				    }
				</sample></div></td><td>1</td><td>Find image services</td></tr><tr><td>getAllPhotoSizeConstants</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/media/all-image-size<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request"></sample></div></td><td>1</td><td>Return the list of available search constants
				</td></tr><tr><td>textToAudio</td><td>GET</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/media/text-to-audio<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request"></sample></div></td><td>1</td><td>Convert Text to Audio
				</td></tr><tr><td>setCache</td><td>POST</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/data/cache/set<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				   header=&gt;{
				     "access_token" : "facebook-token-from-jbrown-web-app"
				    }
				    POST BODY=&gt;
				    {
				     "cache_key" : "Enter-Cache-Key",
				     "cache_value" : "Enter Cache Value"
				    }
				</sample></div></td><td>1</td><td>Cache Store Service</td></tr><tr><td>getCache</td><td>POST</td><td><div class="launchSample"><span class="uri">{{Filled by JS}}</span>1/data/cache/get<button class="btn launchSample"><i class="icon-search icon-green"></i></button><sample class="hide sample-request">
				   header=&gt;{
				     "access_token" : "facebook-token-from-jbrown-web-app"
				    }
				    POST BODY=&gt;
				    {
				     "cache_key" : "Enter-Cache-Key"
				    }
				</sample></div></td><td>1</td><td>Cache Fetch Service</td></tr></tbody></table><br><div id="confirm" class="modal hide fade"><div class="modal-body"><p class="dialog-msg">-</p></div><div class="modal-footer"><button type="button" data-dismiss="modal" class="btn" data-value="0">Cancel</button></div></div></div></div></body></html>