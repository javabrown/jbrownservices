<html>

<head>
	<style>
		body {
			cursor: default;
			font-size: 14px;
			line-height: 21px;
			font-family: "Segoe UI","Helvetica",Garuda,Arial,sans-serif;
			color: #666;
			padding: 14px;
		}
		.box{
			width: 265px;
			float: left;
			margin-right: 25px;
		}

		.title {
			display: block;
			
			padding: 14px 0 15px 0;
			font-size: 20px;
			font-weight: 100;
			margin-bottom: 10px;
			border-bottom: 2px solid;
		}

		ul {
			color: #666;
			list-style:square;
			padding: 0;
			margin-left: 20px;
			font-size: 12px;
		}
		
		.orange { color: #F5876E; }

		.blue{ color: #61A8DC; }

		.green{ color: #8EBD40; }

		.purple { color: #988CC3; }

		.gold { color: #D8C86E; }

	</style>
</head>

<body>
	<%@ page import="com.jbrown.cache.BrownCache" %>
 
	<div class="box blue">
		<span class="title">Active Cache Route</span> 
		<ul> 
				<li>
					<h3><b><%=BrownCache.getInstance().getRoute().getName()%></b></h3>
				</li> 
				 
		</ul>
	</div>
	
	<div class="box orange">
		<span class="title">System Environment</span> 
		<ul> 
				<li>
					<h3><b><%=com.google.appengine.api.utils.SystemProperty.environment.value()%></b></h3>
				</li> 
				 
		</ul>
	</div>
		
		
		<!-- div class="box orange">
			<span class="title">Windows</span> 
			<ul> 
				<li>Windows 7</li> 
				<li>Windows 8</li> 
			</ul>
		</div>
		
		<div class="box green">
			<span class="title">Windows</span> 
			<ul> 
				<li>Windows 7</li> 
				<li>Windows 8</li> 
			</ul>
		</div>
		
		<div class="box purple">
			<span class="title">Windows</span> 
			<ul> 
				<li>Windows 7</li> 
				<li>Windows 8</li> 
			</ul>
		</div>
		
		<div class="box gold">
			<span class="title">Windows</span> 
			<ul> 
				<li>Windows 7</li> 
				<li>Windows 8</li> 
			</ul>
		</div -->
		
</body>


</html>