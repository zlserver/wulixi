<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- <link rel="stylesheet" href="/css/vip.css" type="text/css"> -->
<body>
	<base href="<%=basePath%>">
	<!-- 在何处打开页面中所有的链接 -->
	<div align="center">
		<table>
			<tr>
				<td height="40"></td>
			</tr>
			<tr>
				<td align="center">
					<table width="305" border="0" cellpadding="0" cellspacing="0"
						align="center">
						<tr>
							<td height="212" align="center" valign="middle" bgcolor="#95CBFD"><table
									width="295" border="0" cellpadding="0" bgcolor="#FFFFFF">
									<tr>
										<td width="288" align="center" bgcolor="#C2E1FE"><table
												width="100" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td>&nbsp;</td>
												</tr>
											</table>
											<table width="273" border="0" cellpadding="0"
												cellspacing="10" bgcolor="#FFFFFF">
												<tr>
													<td width="253" height="60" align="center" valign="bottom"
														class="font12">
														<p>
															<c:out value="${message}" escapeXml="false" />
														</p>
													</td>
												</tr>
												<tr>
													<td height="80" align="center" valign="middle"><font
														size="2"><span class="content"> <input
																type="button" name="sure" value="确 定"
																onclick="javascript:window.location.href='<c:url value="${urladdress}"/>'">
														</span></font></td>
												</tr>
											</table>
											<table width="100" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td>&nbsp;</td>
												</tr>
											</table></td>
									</tr>
								</table></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="40"></td>
			</tr>
		</table>
	</div>
</body>
</html>
