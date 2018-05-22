package miprimeraapp.android.teaching.com.misegundaapp;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao



public interface UserDao {

    @Query("SELECT*FROM user")
    List<user> getAll();
    @Query("SELECT * FROM user WHERE username IS :username")
            user findByUsername (String username);

    @Insert
    void insert (user user);

    @Delete
    void delete (user user);
}
