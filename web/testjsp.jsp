<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
<head>
<title>Home Page</title>
</head>
<body>
	<f:view>
		<h:form>
			<h:panelGrid border="1" columns="2">
				
				<h:outputText value="Enter your Login ID: "></h:outputText>
				<h:inputText value="#{LoginController.username}"></h:inputText>
				
				<h:outputText value="Enter your Password: "></h:outputText>
				<h:inputSecret value="#{LoginController.password}"></h:inputSecret>
				
                                
				
				<h:commandButton action="#{LoginController.Authentication}" value="#{LoginController.buttonName()}"></h:commandButton>
			</h:panelGrid>
                        
                        <p:messages id="msg" showDetail="true" closable="true" globalOnly="false" autoUpdate="true" >
                    <p:effect type="bounce" event="load" delay="0" />
                </p:messages>
		</h:form>
	</f:view>
</body>
</html>