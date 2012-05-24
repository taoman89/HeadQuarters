<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>

<%@ 
	page import="	java.util.*, 
					org.persistence.Product, 
					com.sap.security.core.server.csi.XSSEncoder" 			
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Products</title> 
	</head>
		
	<body>
		<table border="1">
			<tr><th colspan="5">
				<% 
					List<Product> resultList = (List<Product>)request.getAttribute("products"); 
					
					if(resultList.isEmpty())
					{
						out.print("");						
					}
					else
					{
						out.print(resultList.size() + " ");
					}
				%>
				
			Entries in the Database</th></tr>
			
				<%
					if (resultList.isEmpty()) 
					{
						//out.print("<tr><td colspan=\"3\">Database is empty</td></tr>");
						out.print("<tr><td colspan=\"5\">Database is empty</td></tr>");
					} 
					else 
					{
						out.print(	"<tr>" +
									"<th>ID</th>" +
									"<th>Name</th>" +
									"<th>Price</th>" +
									"<th>Quantity</th>" +
									"<th>Description</th>" +
									"</tr>");
					}

	                for (Product p : resultList) 
	                {	                	
	                	out.print(	"<tr>" +
	                				"<td>");
	                	
	                	// Product ID
	                	out.print(p.getId());
	                	
	                	out.print(	"</td>" +
                					"<td>");
	                	
	                	// Product Name
	                	if(p.getPName().length() > 0)
	                	{
	                		out.print(XSSEncoder.encodeHTML(p.getPName()));
	                	}
	                	else
	                	{
	                		out.print("&nbsp");
	                	}
	                	
	                	out.print(	"</td>" +
	                				"<td>");
	                	
	                	// Product Price
	                	if(p.getPPrice().length() > 0)
	                	{
	                		out.print(XSSEncoder.encodeHTML(p.getPPrice()));
	                	}
	                	else
	                	{
	                		out.print("&nbsp");
	                	}
	                	
	                	out.print(	"</td>" +
	                				"<td>");
	                	
	                	// Product Quantity
	                	if(p.getPQuantity().length() > 0)
	                	{
	                		out.print(XSSEncoder.encodeHTML(p.getPQuantity()));
	                	}
	                	else
	                	{
	                		out.print("&nbsp");
	                	}
	                	
	                	out.print(	"</td>" +
	                				"<td>");
	                	
	                	// Product Description
	                	if(p.getPDescription().length() > 0)
	                	{
	                		out.print(XSSEncoder.encodeHTML(p.getPDescription()));
	                	}
	                	else
	                	{
	                		out.print("&nbsp");
	                	}
	                	
	                	out.print(	"</td>" +
	                				"</tr>");
	                }
				%>
			
		</table>
		
		<!-- database add/remove buttons -->		
		<!-- Product -->
		<form method = "POST" action="Add"> 
		<table>
		<tr>
			<td>
				ID*: 
			</td>
			<td>
				<input type="text" name="pID">
			</td>
		</tr>
		
		<tr>
			<td>
				Name:
			</td>
			<td>
				<input type="text" name="pName">
			</td>
		</tr>
		
		<tr>
			<td>
				Price:
			</td> 
			<td>
				<input type="text" name="pPrice">
			</td>
		</tr>
		
		<tr>
			<td>
				Quantity:
			</td> 
			<td>
				<input type="text" name="pQuantity">
			</td>
		</tr>
			
		<tr>
			<td>
				Description:
			</td>
			<td>
				<input type="text" name="pDescription">
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="Add Product">
			</td>
		</tr>
		</table>
		</form>
		
		<form method = "POST" action="Remove">
		<table>
			<tr>
				<td>
					ID*:
				</td>
				<td>
					<input type="text" name="delID">
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="Remove Product">
				</td>
			</tr>
		</table>
		</form>
		
	</body>
</html>