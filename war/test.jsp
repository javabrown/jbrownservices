<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<%
String str = "";
for(String s : com.jbrown.db.core.DBTester.read()){
     str  = String.format("[ %s ] - %s", s, str);
}
%>

<h2><%= str %></h2>