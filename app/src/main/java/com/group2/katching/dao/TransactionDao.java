package com.group2.katching.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.group2.katching.entity.Transaction;
import com.group2.katching.entity.User;

import java.util.List;

//    @Dao
//    public interface TransactionDao {
//
//        // allowing the insert of the same word multiple times by passing a
//        // conflict resolution strategy (when a same duplicate primary key gets inserted)
//        @Insert(onConflict = OnConflictStrategy.IGNORE)
//        void insert(Transaction transaction);
//
//        @Query("DELETE FROM transaction_table")
//        void deleteAll();
//
//        @Query("SELECT * FROM transaction_table ORDER BY amount ASC")
//        LiveData<List<User>> getAlphabetizedUsers();
//
//    }

