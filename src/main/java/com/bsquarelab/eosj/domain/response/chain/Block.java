package com.bsquarelab.eosj.domain.response.chain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.bsquarelab.eosj.domain.response.chain.block.NewProducers;
import com.bsquarelab.eosj.domain.response.history.transaction.Transaction;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block {

    private String previous;

    private String timeStamp;

    private String transactionMerkleRoot;

    private String actionMerkleRoot;

    private String blockMerkleRoot;

    private String producer;

    private String scheduleVersion;

    private NewProducers newProducers;

    private String producerSignature;

    private String id;

    private Long blockNum;

    private Long refBlockPrefix;

    private Boolean confirmed;

    private Transaction[] transactions;

    private String[] headerExtensions;

    private String[] blockExtensions;

    public Block() {
    }

    public String getActionMerkleRoot() {
        return actionMerkleRoot;
    }

    @JsonProperty("action_mroot")
    public void setActionMerkleRoot(String actionMerkleRoot) {
        this.actionMerkleRoot = actionMerkleRoot;
    }

    public String getBlockMerkleRoot() {
        return blockMerkleRoot;
    }

    @JsonProperty("block_mroot")
    public void setBlockMerkleRoot(String blockMerkleRoot) {
        this.blockMerkleRoot = blockMerkleRoot;
    }

    public String getPrevious() {
        return previous;
    }

    @JsonProperty("previous")
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    @JsonProperty("timestamp")
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTransactionMerkleRoot() {
        return transactionMerkleRoot;
    }

    @JsonProperty("transaction_mroot")
    public void setTransactionMerkleRoot(String transactionMerkleRoot) {
        this.transactionMerkleRoot = transactionMerkleRoot;
    }

    public String getProducer() {
        return producer;
    }

    @JsonProperty("producer")
    public void setProducer(String producer) {
        this.producer = producer;
    }


    public String getProducerSignature() {
        return producerSignature;
    }

    @JsonProperty("producer_signature")
    public void setProducerSignature(String producerSignature) {
        this.producerSignature = producerSignature;
    }

    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Long getBlockNum() {
        return blockNum;
    }

    @JsonProperty("block_num")
    public void setBlockNum(Long blockNum) {
        this.blockNum = blockNum;
    }

    public Long getRefBlockPrefix() {
        return refBlockPrefix;
    }

    @JsonProperty("ref_block_prefix")
    public void setRefBlockPrefix(Long refBlockPrefix) {
        this.refBlockPrefix = refBlockPrefix;
    }

    public NewProducers getNewProducers() {
        return newProducers;
    }

    @JsonProperty("new_producers")
    public void setNewProducers(NewProducers newProducers) {
        this.newProducers = newProducers;
    }

    public String getScheduleVersion() {
        return scheduleVersion;
    }

    @JsonProperty("schedule_version")
    public void setScheduleVersion(String scheduleVersion) {
        this.scheduleVersion = scheduleVersion;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }

    public String[] getHeaderExtensions() {
        return headerExtensions;
    }

    @JsonProperty("header_extensions")
    public void setHeaderExtensions(String[] headerExtensions) {
        this.headerExtensions = headerExtensions;
    }

    public String[] getBlockExtensions() {
        return blockExtensions;
    }

    @JsonProperty("block_extensions")
    public void setBlockExtensions(String[] blockExtensions) {
        this.blockExtensions = blockExtensions;
    }
}
