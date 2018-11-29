package com.bsquarelab.eosj.impl;


import com.bsquarelab.eosj.EosApiService;
import com.bsquarelab.eosj.domain.common.WalletKeyType;
import com.bsquarelab.eosj.domain.common.transaction.SignedPackedTransaction;
import com.bsquarelab.eosj.domain.request.chain.AbiJsonToBinRequest;
import com.bsquarelab.eosj.domain.request.chain.RequiredKeysRequest;
import com.bsquarelab.eosj.domain.request.chain.transaction.PushTransactionRequest;
import com.bsquarelab.eosj.domain.request.wallet.transaction.SignTransactionRequest;
import com.bsquarelab.eosj.domain.response.chain.*;
import com.bsquarelab.eosj.domain.response.chain.account.Account;
import com.bsquarelab.eosj.domain.response.chain.abi.Abi;
import com.bsquarelab.eosj.domain.response.chain.code.Code;
import com.bsquarelab.eosj.domain.common.transaction.PackedTransaction;
import com.bsquarelab.eosj.domain.response.chain.currencystats.CurrencyStats;
import com.bsquarelab.eosj.domain.response.chain.transaction.PushedTransaction;
import com.bsquarelab.eosj.domain.response.chain.transaction.ScheduledTransactionResponse;
import com.bsquarelab.eosj.domain.response.history.action.Actions;
import com.bsquarelab.eosj.domain.response.history.controlledaccounts.ControlledAccounts;
import com.bsquarelab.eosj.domain.response.history.keyaccounts.KeyAccounts;
import com.bsquarelab.eosj.domain.response.history.transaction.Transaction;


import java.util.*;

public class EosApiServiceImpl implements EosApiService {

    private final EosWalletApiService eosWalletApiService;

    private final EosChainApiService eosChainApiService;

    private final EosHistoryApiService eosHistoryApiService;

    public EosApiServiceImpl(String baseUrl){
        eosWalletApiService = EosApiServiceGenerator.createService(EosWalletApiService.class, baseUrl);
        eosChainApiService = EosApiServiceGenerator.createService(EosChainApiService.class, baseUrl);
        eosHistoryApiService = EosApiServiceGenerator.createService(EosHistoryApiService.class, baseUrl);
    }

    public EosApiServiceImpl(String walletBaseUrl, String chainBaseUrl, String historyBaseUrl){
        eosWalletApiService = EosApiServiceGenerator.createService(EosWalletApiService.class, walletBaseUrl);
        eosChainApiService = EosApiServiceGenerator.createService(EosChainApiService.class, chainBaseUrl);
        eosHistoryApiService = EosApiServiceGenerator.createService(EosHistoryApiService.class, historyBaseUrl);
    }

    public String setARTInfo(String info) {
		return "";
	}
	
	public String setHoldersList (String holdersList) {
		return "";
	}
	public String setTransactions(String Txs) {return "";}
	public String getArtInfo() {return "";}
	public String getHoldersList() {return "";}
	public String getTransactions() {return "";}
}
