package com.bsquarelab.eosj;

public interface EosApiService {
	String setArtHash(String info);
	String setHoldersHash (String holdersList);
	String addTransactionsHash(String Txs);
	String getArtHash();
	String getHoldersHash();
	String getTransactionsHash();
}
