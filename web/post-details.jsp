<%@ page import="don.bitlab.models.Post" %>
<%@ page import="don.bitlab.db.PostDb" %>
<%@ page import="don.bitlab.models.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="don.bitlab.models.Comment" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: coldkey
  Date: 27.10.2023
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container col-8 mx-auto">
    <%
        Post post = (Post) request.getAttribute("post");
        List<Category> categories = (List<Category>) request.getAttribute("categories");
    %>
    <form action="/post-details" method="post">
        <input class="form-control mt-1" type="text" name="post_title" value="<%=post.getTitle()%>">
        <input class="form-control mt-1" type="text" name="post_content" value="<%=post.getContent()%>">
        <input class="form-control mt-1" type="text" name="post_full_name" value="<%=post.getUser().getFullName()%>"
               readonly>
        <input name="post_id" type="hidden" value="<%=post.getId()%>">
        <select class="form-control mt-1" name="post_category_id">
            <% for (Category category : categories) { %>
            <option value="<%=category.getId()%>"
                    <% if (category.getId() == post.getCategory().getId()) { %>selected<% } %>>
                <%=category.getName()%>
            </option>
            <% } %>
        </select>
        <button class="mt-1 btn btn-dark">UPDATE BLOG</button>
    </form>

    <div class="row d-flex justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-0 border" style="background-color: #ffffff;">
                <div class="card-body p-4">
                    <form action="/add-comment" method="post">
                        <div class="form-outline mb-4">
                            <input type="hidden" name="post_id" value="<%=post.getId()%>">
                            <input name="comment_content" type="text" class="form-control"
                                   placeholder="Type comment..."/>
                            <button class="mt-1 btn btn-dark">ADD COMMENT</button>
                        </div>
                    </form>
                    <%
                        List<Comment> comments = (List<Comment>) request.getAttribute("comments");
                        for (Comment comment : comments) {
                    %>
                    <div class="card mb-4">
                        <div class="card-body">
                            <p>
                                <%=comment.getContent()%>
                            </p>
                            <div class="d-flex justify-content-between">
                                <div class="d-flex flex-row align-items-center">
                                    <p class="small mb-0 ms-2">
                                        <%=comment.getUser().getFullName()%>
                                    </p>
                                </div>
                                <div class="d-flex flex-row align-items-center">
                                    <p class="small text-muted mb-0">
                                        <%=comment.getPostDate()%>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
