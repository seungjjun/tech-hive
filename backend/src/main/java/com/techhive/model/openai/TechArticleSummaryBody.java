package com.techhive.model.openai;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public record TechArticleSummaryBody(
    @SerializedName("one_line_summary") String oneLineSummary,
    @SerializedName("three_line_summary") List<String> threeLineSummary,
    @SerializedName("core_summary") List<CoreSummaryItem> coreSummary
    ) {
}