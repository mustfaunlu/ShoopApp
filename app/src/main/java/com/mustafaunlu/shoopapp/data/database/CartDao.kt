package com.mustafaunlu.shoopapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mustafaunlu.shoopapp.domain.entity.UserCartEntity

@Dao
interface CartDao {
    @Insert
    fun insertUserCart(userCartEntity: UserCartEntity)

    @Query("SELECT * FROM user_carts WHERE userId = :userId")
    fun getCartByUserId(userId: Int): List<UserCartEntity>
}
