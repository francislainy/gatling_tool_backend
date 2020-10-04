package com.francislainy.gatling_tool.dto.stats;

import com.francislainy.gatling_tool.debug.model_manual.GroupLoginFlowCe67c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Contents {

    @SerializedName("group_login-flow-ce67c")
    @Expose
    public GroupLoginFlowCe67c groupLoginFlowCe67c;
   
}
