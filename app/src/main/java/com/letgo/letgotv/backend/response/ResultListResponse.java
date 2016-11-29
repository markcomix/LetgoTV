package com.letgo.letgotv.backend.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.letgo.letgotv.backend.model.ResultEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapp the Response to and Object
 */
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class ResultListResponse extends BaseResponse {

    @JsonProperty("page")
    private int page;

    @JsonProperty("results")
    private List<ResultEntity> result;

    @JsonProperty("total_results")
    private int totalResults;

    @JsonProperty("total_pages")
    private int totalPages;

    public ResultListResponse() {

        this.page = 0;
        this.result = new ArrayList<>();
        this.totalResults = 0;
        this.totalPages = 0;
    }

    public int getPage() {
        return page;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
