package com.bsquarelab.eosj.domain.response.chain.block;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewProducers {
	/*
	  # cleos -u http://mainnet.genereos.io get block 13981050
	  ......
	  "new_producers": {
	    "version": 308,
	    "producers": [{
	        "producer_name": "argentinaeos",
	        "block_signing_key": "EOS7jq4FHrFrtCXxpRQ39dBeDMa5AjM4VaRbqBECkSa5aZnizJzrx"
	      },{
	        "producer_name": "bitfinexeos1",
	        "block_signing_key": "EOS4tkw7LgtURT3dvG3kQ4D1sg3aAtPDymmoatpuFkQMc7wzZdKxc"
	      }, ......
	      ......
	
	*/
	
    private int version;

    private Producer[] producers;
    
    public NewProducers()
    {
    	
    }
    
    @JsonProperty("version")
    public void setVersion(int version) {
        this.version = version;
    }
    
    @JsonProperty("producers")
    public void setProducers(Producer[] producers) {
        this.producers = producers;
    }
    
}
