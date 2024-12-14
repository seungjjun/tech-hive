package com.techhive.model.openai;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public record CoreSummaryItem(
    @SerializedName("title") String title,
    @SerializedName("summaries") List<String> summaries) {
}
