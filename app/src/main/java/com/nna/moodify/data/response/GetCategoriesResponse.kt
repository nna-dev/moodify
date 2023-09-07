package com.nna.moodify.data.response

import com.nna.moodify.domain.model.Category
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCategoriesResponse(
    val categories: CategoriesResponse
)

@JsonClass(generateAdapter = true)
data class CategoriesResponse(
    val href: String,
    val items: List<CategoryResponse>,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int
)

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    val href: String,
    val icons: List<ImageResponse>,
    val id: String,
    val name: String
)

fun CategoryResponse.toCategory() = Category(
    id = this.id,
    href = this.href,
    name = this.name,
    icons = this.icons.toResolutionImageMap()
)
