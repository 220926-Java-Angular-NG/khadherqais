package com.revature;
import com.revature.controllers.FlashCardController;
import com.revature.controllers.UserController;
import com.revature.models.FlashCard;
import com.revature.models.User;
import com.revature.repos.FlashCardRepo;
import com.revature.repos.UserRepo;
import com.revature.models.User;
import com.revature.services.FlashCardService;
import com.revature.services.UserService;
import io.javalin.Javalin;

import javax.jws.soap.SOAPBinding;
import java.util.List;

import java.util.Properties;

import java.io.PrintStream;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) {

       UserRepo userRepo = new UserRepo();
        FlashCardRepo userFlash =new FlashCardRepo();

        /*
         *public :Available anywhere
         * * protected access within the same package and the class's subclasses
         * *default:access within the same package.
         * *private:Access only within the same class.

         */


        //add the user
      // User user = new User("QAIS", "KHADHER", "QAISKHADHER@gmail.com", "pasword");
       //  User user4 = new User("Tiffany","Ob","Tiffany.obi@gmail.com","pasword");

        // add the user to the database


       //int user4Id = userRepo.create(user4);
        //System.out.println(user4Id);
        FlashCard flash1 = new FlashCard("what is TDD","Test driven development ",9);
        userFlash.create(flash1);
       // System.out.println(flash1);
       FlashCard flash2 = new FlashCard("what is TCL"," Transaction Control Language",8);
        // below add the new information we create to DB table (flashcard-demo);
       userFlash.create(flash2);
        System.out.println(flash2);
      // List<FlashCard> dbflash1 = userFlash.getAll();
      // FlashCard flashcardfromdb = userFlash.getById(5);
       // userFlash.delete(flashcardfromdb);
        //System.out.println(flashcardfromdb.getClass());

       // for(FlashCard flashcard:dbflash1){
           // System.out.println(flashcard.toString());

      //  List<User> userdb =userRepo.getAll();
       // User user1fromdb = userRepo.getById(5);
       // System.out.printf(user1fromdb.getFirstname());
        //System.out.println(user1fromdb.getLastname());
       // System.out.println(user1fromdb.getEmail());
       // System.out.println(user1fromdb.getPassword());
       // System.out.println(user1fromdb);
       // System.out.println(user1fromdb.getEmail());
       // user1fromdb.setEmail("qais_khadher@gmail.com");
        //User updateduseremail = userRepo.update(user1fromdb);
        //System.out.println("this is the updated Email - " +updateduseremail.getEmail());
        //for(User user:dbUsers1) {
        // System.out.println(user.toString());}


        //---delete user


       // userRepo.deleteById(3);  //test is ok


        //--------------------------useController below implmentation

        Javalin app = Javalin.create().start(8080);

        UserService userService = new UserService();
        UserController userController = new UserController(userService);


        //users uris
       app.get("/users",userController.getAllUsers);
        app.get("/user/{id}",userController.getUserById);
        app.post("/user",userController.createNewUser);
        app.put("/user",userController.updateUser);
        app.delete("/user", userController.deleteUser);
        app.delete("/user/{id}", userController.deleteUserById);

        FlashCardService flashCardService=new FlashCardService();
        FlashCardController flashCardController= new FlashCardController(flashCardService);
        //flashcard uris


       app.get("/flashcards",flashCardController.getAllFlashCards);
        app.get("/flashcard/{id}",flashCardController.getFlashCardById);
        app.post("/flashcard",flashCardController.createNewFlashCard);
        app.put("/flashcard",flashCardController.updateFlashCard);
        app.delete("/flashcard",flashCardController.deleteFlashCard);
        app.delete("/flashcard/{id}",flashCardController.deleteFlashCardByID);

        /**
         * public: Available anywhere
         * protected: Access within the same package and the class's subclasses
         * default: Access only within the same package.
         * private: Access only within the same class.
         */











    }













    }




