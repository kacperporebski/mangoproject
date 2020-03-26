package database.datatables;

import database.SQLDatabase;
import model.Post;
import model.PostCategory;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class PostTable extends SQLDatabase {
    public PostTable() {
        super();
        createPostTable();
    }

    private void createPostTable() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "Post", null);

            if (rs.next() == false) {
                String query = "CREATE TABLE Post " +
                        "(IDNum VARCHAR(255) NOT NULL," +
                        "PostContent VARCHAR(255) NOT NULL," +
                        "GeneratedName VARCHAR(255) NOT NULL," +
                        "Category VARCHAR(255) NOT NULL," +
                        "Likes INT NOT NULL," +
                        "Date DATE NOT NULL," +
                        "PRIMARY KEY (IDNum)," +
                        "UserID VARCHAR(255) REFERENCES MangoUser(IDNum)" +
                        "ON DELETE SET NULL ON UPDATE CASCADE);";
                statement.executeUpdate(query);
                System.out.println("Created post table\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a post from the database from UUID
     */
    public Optional<Post> getPostByUUID(UUID postID) {
        Post post = null;
        try {
            String query = "SELECT * FROM Post WHERE IDNum = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, postID.toString());
            resultSet = pState.executeQuery();

            if (resultSet.next()) {
                Date date = resultSet.getDate("date");
                UUID userID = UUID.fromString(resultSet.getString("userID"));
                String content = resultSet.getString("PostContent");
                String displayName = resultSet.getString("GeneratedName");
                int likes = resultSet.getInt("likes");
                PostCategory category = PostCategory.valueOf(resultSet.getString("category"));

                post = new Post(postID, date, content, displayName, userID, likes, category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(post);
    }

    /**
     * Retrieves all post from the database
     */
    public ArrayList<Post> getAllPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            String query = "SELECT * FROM Post";
            PreparedStatement pState = connection.prepareStatement(query);
            resultSet = pState.executeQuery();

            while (resultSet.next()) {
                Date date = resultSet.getDate("date");
                UUID postID = UUID.fromString(resultSet.getString("IDNum"));
                UUID userID = UUID.fromString(resultSet.getString("userID"));
                String content = resultSet.getString("PostContent");
                String displayName = resultSet.getString("GeneratedName");
                int likes = resultSet.getInt("likes");
                PostCategory category = PostCategory.valueOf(resultSet.getString("category"));

                posts.add(new Post(postID, date, content, displayName, userID, likes, category));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    /**
     * Retrieves post from the database under a given category
     */
    public ArrayList<Post> getPostsByCategory(PostCategory category) {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            String query = "SELECT * FROM Post WHERE category = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, category.toString());
            resultSet = pState.executeQuery();

            while (resultSet.next()) {
                Date date = resultSet.getDate("date");
                UUID postID = UUID.fromString(resultSet.getString("IDNum"));
                UUID userID = UUID.fromString(resultSet.getString("userID"));
                String content = resultSet.getString("PostContent");
                String displayName = resultSet.getString("GeneratedName");
                int likes = resultSet.getInt("likes");

                posts.add(new Post(postID, date, content, displayName, userID, likes, category));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    /**
     * Retrieves all post from the database
     */
    public ArrayList<Post> getPostsByUserID(UUID userID) {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            String query = "SELECT * FROM Post WHERE UserID = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, userID.toString());
            resultSet = pState.executeQuery();

            while (resultSet.next()) {
                Date date = resultSet.getDate("date");
                UUID postID = UUID.fromString(resultSet.getString("IDNum"));
                String content = resultSet.getString("PostContent");
                String displayName = resultSet.getString("GeneratedName");
                int likes = resultSet.getInt("likes");
                PostCategory category = PostCategory.valueOf(resultSet.getString("category"));

                posts.add(new Post(postID, date, content, displayName, userID, likes, category));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }


    /**
     * Adds a post to the database.
     * Returns true if user is successfully added, false if otherwise.
     */
    public boolean addPost(Post post)
    {
        try
        {
            String query = "INSERT INTO Post (IDNum, PostContent, GeneratedName, Category, Likes, Date, UserID)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, post.getPostID().toString());
            pState.setString(2, post.getContent());
            pState.setString(3, post.getDisplayName());
            pState.setString(4, post.getCategory().toString());
            pState.setInt(5, post.getLikes());
            pState.setDate(6, new java.sql.Date(post.getDate().getTime()));
            pState.setString(7, post.getUserID().toString());
            pState.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the posts in the database
     */
    public boolean updatePost(Post post) {
        try {
            String query = "UPDATE Post SET PostContent = ?, Category = ?, Likes = ? WHERE IDNum = ?";
            PreparedStatement pState = connection.prepareStatement(query);
            pState.setString(1, post.getContent());
            pState.setString(2, post.getCategory().toString());
            pState.setInt(3, post.getLikes());
            pState.setString(4, post.getPostID().toString());
            pState.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}