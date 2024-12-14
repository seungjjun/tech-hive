package com.techhive.model.openai;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public record Category(
    @SerializedName("parent_category") String parentCategory,
    @SerializedName("child_categories") List<String> childCategories) {
}
