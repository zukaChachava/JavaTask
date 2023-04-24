package com.exam.assigment.database;

import com.exam.assigment.models.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final Object lock = new Object();
    private static DatabaseHelper databaseHelper;

    private DatabaseHelper(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void addPost(Post post) throws SQLException {
        synchronized (lock){
            Connection connection = null;

            try{
                connection = getConnection();
                PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO ZurasPosts.Posts(Header, Body, Author) VALUES (?, ?, ?)");
                insertStatement.setString(1, post.getHeader());
                insertStatement.setString(2, post.getBody());
                insertStatement.setString(3, post.getAuthor());
                insertStatement.executeUpdate();
            }
            finally {
                if (connection != null)
                    connection.close();
            }

        }
    }

    public List<Post> getPosts() throws SQLException{
        synchronized (lock){
            Connection connection = null;
            try{
                connection = getConnection();
                return readPosts(connection.createStatement().executeQuery("SELECT * FROM ZurasPosts.Posts"));
            }
            finally {
                if (connection != null)
                    connection.close();
            }
        }
    }

    private Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/ZurasPosts?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "Password123");
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<Post> readPosts(ResultSet resultSet) throws SQLException{
        List<Post> posts = new ArrayList<>();
        while (resultSet.next()){
            Post post = new Post();
            post.setId(resultSet.getInt("Id"));
            post.setHeader(resultSet.getString("Header"));
            post.setBody(resultSet.getString("Body"));
            post.setAuthor(resultSet.getString("Author"));
            post.setCreatedAt(resultSet.getDate("CreatedAt"));
            posts.add(post);
        }
        return posts;
    }

    public static DatabaseHelper getInstance(){
        synchronized (lock){
            if(databaseHelper == null)
                databaseHelper = new DatabaseHelper();

            return databaseHelper;
        }
    }
}
