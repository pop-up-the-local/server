package com.popup_the_local.server.repository

import com.popup_the_local.server.entity.Category
import com.popup_the_local.server.entity.Popup
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PopupRepository: MongoRepository<Popup, String> {

    fun findByMemberId(memberId: String): List<Popup>

    @Query("SELECT r FROM Result r WHERE (:category IS NULL OR r.category = :category) AND (:city IS NULL OR r.city IN :city)")
    fun findByCategoryAndCity(@Param("category") category: Category?, @Param("city") city: String?): List<Popup>

}