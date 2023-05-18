package Main.Entity;

import java.sql.*;
import java.util.*;

import Database.DB;

public class ManageReviews {

    private final DB db = new DB();
    private final Connection conn = db.getConnection();
    private PreparedStatement stmt;

    public ManageReviews() {
    }

    /** 
     * Get all reviews for a movie
     * @param String movieName 
     * @return ArrayList<String> of all reviews & ratings for a movie
     */
    public ArrayList<String> getAllReviewsRatingsForMovie(String movieName) {
        ArrayList<String> reviews = new ArrayList<String>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM reviews WHERE movieName = ?");
            stmt.setString(1, movieName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                reviews.add(rs.getString("movieName"));
                reviews.add(rs.getString("rating"));
                reviews.add(rs.getString("review"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return reviews;
    }

    /**
     * Add a review and rating for a movie
     * @param ArrayList<String> reviewInfo
     * @return boolean true if success, false if fail
     */
    public boolean addReviewRating(ArrayList<String> reviewInfo) {
        try {
            stmt = conn.prepareStatement("INSERT INTO reviews (email, movieName, review, rating) VALUES (?, ?, ?, ?)");
            stmt.setString(1, reviewInfo.get(0));
            stmt.setString(2, reviewInfo.get(1));
            stmt.setString(3, reviewInfo.get(2));
            stmt.setString(4, reviewInfo.get(3));

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

}
