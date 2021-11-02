package com.group2.katching.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.group2.katching.entity.User;

import java.util.List;

//    @Dao
//    public interface UserDao {
//
//        // allowing the insert of the same word multiple times by passing a
//        // conflict resolution strategy (when a same duplicate primary key gets inserted)
//        @Insert(onConflict = OnConflictStrategy.IGNORE)
//        void insert(User user);
//
//        @Query("DELETE FROM user")
//        void deleteAll();
//
//        @Query("SELECT * FROM user ORDER BY firstName ASC")
//        LiveData<List<User>> getAlphabetizedWords();
//
//    }

