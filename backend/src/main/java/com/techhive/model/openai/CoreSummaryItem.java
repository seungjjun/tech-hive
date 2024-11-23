package com.techhive.model.openai;

import com.google.gson.annotations.SerializedName;

public record CoreSummaryItem(
    @SerializedName("title") String title,
    @SerializedName("description") String description) {
}
