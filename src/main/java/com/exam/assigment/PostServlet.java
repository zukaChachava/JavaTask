package com.exam.assigment;

import com.exam.assigment.database.DatabaseHelper;
import com.exam.assigment.models.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "postServlet", value = "/post")
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = null;

        try{
            posts = DatabaseHelper.getInstance().getPosts();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<table border='1' cellpadding='10'>");
        out.println("<thead><tr><th>ID</th><th>Header</th><th>Body</th><th>Author</th><th>CreatedAt</th></tr></thead>");
        out.println("<tbody>");
        posts.forEach(post -> {
            out.println("<tr>");
            out.println("<td>" + post.getId() + "</td>");
            out.println("<td>" + post.getHeader() + "</td>");
            out.println("<td>" + post.getBody() + "</td>");
            out.println("<td>" + post.getAuthor() + "</td>");
            out.println("<td>" + post.getCreatedAt() + "</td>");
            out.println("</tr>");
        });
        out.println("</tbody>");
        out.println("</table>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post = new Post();
        post.setHeader(req.getParameter("header"));
        post.setBody(req.getParameter("body"));
        post.setAuthor(req.getParameter("author"));

        try{
            DatabaseHelper.getInstance().addPost(post);
            String redirectUrl = "post";
            resp.setStatus(HttpServletResponse.SC_FOUND);
            resp.setHeader("Location", redirectUrl);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
