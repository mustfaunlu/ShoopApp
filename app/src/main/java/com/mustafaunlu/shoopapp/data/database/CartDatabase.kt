package com.mustafaunlu.shoopapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity

@Database(entities = [UserCartEntity::class], version = 1)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}