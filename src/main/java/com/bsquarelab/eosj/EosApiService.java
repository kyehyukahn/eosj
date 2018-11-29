package com.bsquarelab.eosj;

public interface EosApiService {

	String setARTInfo(String info);
	String setHoldersList (String holdersList);
	String setTransactions(String Txs);
	String getArtInfo();
	String getHoldersList();
	String getTransactions();
}
