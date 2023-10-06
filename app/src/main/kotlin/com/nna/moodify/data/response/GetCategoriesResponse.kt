/*
 * Designed and developed by 2023 nna-dev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
