package com.bsquarelab.eosj.domain.request.chain.transaction;

import com.bsquarelab.eosj.domain.common.transaction.PackedTransaction;
import com.bsquarelab.eosj.domain.common.transaction.SignedPackedTransaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.bsquarelab.eosj.blockchain.util.GsonEosTypeAdapterFactory;

import java.util.List;



@JsonIgnoreProperties(ignoreUnknown = true)
public class PushTransactionRequest {

    private String compression;
    
    private String packed_context_free_data = "";
    
    private String packed_trx;

    private List<String> signatures;

    public PushTransactionRequest(String compression, SignedPackedTransaction signedTransaction, List<String> signatures) {
        this.compression = compression;
        this.signatures = signatures;
        
        //packed transaction
        //1. make the json object
        ObjectMapper oMapper = new ObjectMapper();
        String signedTransactionString = "";
		try {
			signedTransactionString = oMapper.writeValueAsString(signedTransaction);
	        System.out.println( "Signed Transaction : " + signedTransactionString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//signedTransactionString = "{\"expiration\":\"2018-11-28T10:59:17\",\"ref_block_num\":27553,\"ref_block_prefix\":1022477542,\"max_net_usage_words\":0,\"max_cpu_usage_ms\":0,\"delay_sec\":0,\"context_free_actions\":[],\"actions\":[{\"account\":\"hello\",\"name\":\"hi\",\"authorization\":[{\"actor\":\"eosio\",\"permission\":\"active\"}],\"data\":\"0000000000009487\"}],\"transaction_extensions\":[],\"signatures\":[\"SIG_K1_KYtQGzScjmDFDPrm8gAgdndntXL6kzcHpP2Yqz4oDRiEJMfQF6HxMy9NMesqvRVcVAXhbmqJMTRqUma21KEESqWpa4JE1S\"],\"context_free_data\":[]}";

		//2. make a signedTransaction from json
	    Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonEosTypeAdapterFactory()).excludeFieldsWithoutExposeAnnotation().create();
		com.bsquarelab.eosj.blockchain.chain.SignedTransaction _signedTransaction
		= gson.fromJson(signedTransactionString, com.bsquarelab.eosj.blockchain.chain.SignedTransaction.class);        

    	//3. make a packedTransaction 
        com.bsquarelab.eosj.blockchain.chain.PackedTransaction packedTransaction 
        = new com.bsquarelab.eosj.blockchain.chain.PackedTransaction(_signedTransaction, com.bsquarelab.eosj.blockchain.chain.PackedTransaction.CompressType.none);
        
        this.packed_trx = packedTransaction.getPackedTrx();
        System.out.println( "packed trx : " + packed_trx);
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    public String getpacked_context_free_data() {
    	return packed_context_free_data;
    }
    
    public void setpacked_context_free_data(String packedContext) {
    	this.packed_context_free_data = packedContext;
    }
    
    public String getpacked_trx() {
        return packed_trx;
    }

    public void setpacked_trx(PackedTransaction transaction) {
        this.packed_trx = transaction.toString();
    }

    public List<String> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<String> signatures) {
        this.signatures = signatures;
    }
}
