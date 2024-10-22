<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10/22/2024
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<a href="/admin/categories/add">Add category</a>
<c:if test="${message != null}">
  <i>${message}</i>
</c:if>
<table>
  <tr>
    <th>STT</th>
    <th>Images</th>
    <th>Category name</th>
    <th>Status</th>
    <th>Action</th>
  </tr>
  <c:forEach items="${categories}" var="category" varStatus="index">
    <tr>
      <td>${index.index+1}</td>
      <td>${category.images}</td>
      <td>${category.name}</td>
      <td>${category.status}</td>
      <td>
        <a href="/admin/categories/edit/${category.id}">Edit</a>
        <a href="/admin/categories/delete/${category.id}">Delete</a>
      </td>
    </tr>
  </c:forEach>

</table>
