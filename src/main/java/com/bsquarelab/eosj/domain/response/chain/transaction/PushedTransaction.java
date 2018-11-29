package com.bsquarelab.eosj.domain.response.chain.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PushedTransaction {

    private Processed processed;

    private String transactionId;

    public Processed getProcessed() {
        return processed;
    }

    public void setProcessed(Processed processed) {
        this.processed = processed;
    }


    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transaction_id")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
