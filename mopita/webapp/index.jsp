<% 
	String language=request.getLocale().getLanguage();
	response.sendRedirect("home.action?l="+language);
%>