<%@ page language="java" pageEncoding="UTF-8"%>
<div style="text-align: right;">
	
  <nav class="navbar navbar-default " role="navigation">
	<div >
		<p class="navbar-text" >
			总记录数:${pageView.totalrecord}条 | 每页显示:${pageView.maxresult}条 |
			总页数:${pageView.totalpage}页
		</p>
		<ul class=" pagination " >
			<c:if test="${1!= pageView.totalpage}">
				<c:if test="${1!= pageView.currentpage}">
					<li><a href="javascript:topage('${pageView.currentpage-1 }')">&laquo;</a></li>
				</c:if>
				<c:forEach begin="${pageView.pageindex.startindex}"
					end="${pageView.pageindex.endindex}" step="1" var="page">
					<c:choose>
						<c:when test="${page== pageView.currentpage}">
							<li class="active"><a href="javascript:topage('${page }')">${page }</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:topage('${page }')">${page }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if
					test="${pageView.totalpage!=0 && pageView.totalpage!= pageView.currentpage }">
					<li><a href="javascript:topage('${pageView.currentpage+1 }')">&raquo;</a></li>
				</c:if>
			</c:if>
		</ul>
	</div>
  </nav>
</div>
