<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10/22/2024
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<h2>${category.isEdit ? 'Edit Category' : 'Add new category'}</h2>
<form action="/admin/categories/insert" method="post">
  <input type="hidden" value="${category.isEdit}" name="isEdit">
  <input type="hidden" value="${category.id}" name="id">
  <input type="text" name="name" value="${category.name}">
  <input type="text" name="images" value="${category.images}">
  <input type="text" name="status" value="${category.status}">
  <c:if test="${category.isEdit}">
    <input type="submit" value="Update">
  </c:if>
  <c:if test="${!category.isEdit}">
    <input type="submit" value="Insert">
  </c:if>
</form>
