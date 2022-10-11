package com.revature.repos;

import com.revature.models.FlashCard;
import com.revature.utils.CRUDDaoInterface;
import com.revature.utils.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlashCardRepo implements CRUDDaoInterface<FlashCard> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlashCardRepo.class);
    Connection conn;

    public FlashCardRepo() {

        try {
            this.conn = ConnectionManager.getConnection();
           //** System.out.println(this.conn.getSchema());
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException.getMessage());
            }
    }

    @Override
    public int create(FlashCard flashCard) {
        try{
            String sql = "INSERT INTO \"flashcards\" (id, question, answer, creator_id) VALUES(default,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, flashCard.getQuestion());//, user.getLastName(), user.getEmail(), user.getPassword()
            pstmt.setString(2, flashCard.getAnswer());
            pstmt.setInt(3, flashCard.getCreatorId());

            // this executes the sql statement above
            pstmt.executeUpdate();

            // this gives us a result set of the row that was just created
            ResultSet rs = pstmt.getGeneratedKeys();

            // the cursor is right in front of the first (or only) element in our result set
            // calling rs.next() iterates us into the first row
            rs.next();

            return rs.getInt("id"); // this will give us the int in column labeled id
//             return rs.getInt(1); this will give us the int on column position 1

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    @Override
    public List<FlashCard> getAll() {
        try{

            String sql = "SELECT * FROM flashcards";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            List<FlashCard> flashCardList = new ArrayList<>();
            while(rs.next()) {
                FlashCard currFlashCard = new FlashCard(rs.getInt("id"), rs.getString("question"), rs.getString("answer"), rs.getInt("creator_id"));
                flashCardList.add(currFlashCard);
            }
            return flashCardList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public FlashCard getById(int id) {
        try {
            String sql = "SELECT * FROM flashcards WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            // we are returning a user
            // therefore we have to create a new instance of a user from the database
            FlashCard flashCard = null;
            while(rs.next()) {
                flashCard = new FlashCard(rs.getInt("id"), rs.getString("question"), rs.getString("answer"), rs.getInt("creator_id"));
            }
            return flashCard;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public FlashCard update(FlashCard flashCard) {
        try {
            String sql = "UPDATE flashcards SET answer = ? WHERE creator_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, flashCard.getAnswer());
            pstmt.setInt(2, flashCard.getId());
            System.out.println(pstmt.executeUpdate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // returns true if deleted more than 0 entries
    @Override
    public boolean delete(FlashCard flashCard) {

        try{
            String sql = "DELETE FROM flashcards WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,flashCard.getId());

            //note that false is returned because a resultSet is NOT returned when this statement is executed

            int number = pstmt.executeUpdate();
            return number > 0;




        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return false;
    }
    public boolean deleteById(int id) {
        //Delete from tablename where id = ?

        try {

            String sql = "DELETE FROM flashcards WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            //pstmt.execute() returns a boolean
            // it returns false if the executed statement returns void

            pstmt.execute();
            return true;
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return false;
    }
}
