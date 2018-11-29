package com.bsquarelab.eosj.impl;

import com.bsquarelab.eosj.domain.response.history.action.Actions;
import com.bsquarelab.eosj.domain.response.history.controlledaccounts.ControlledAccounts;
import com.bsquarelab.eosj.domain.response.history.keyaccounts.KeyAccounts;
import com.bsquarelab.eosj.domain.response.history.transaction.Transaction;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.Map;

public interface EosHistoryApiService {

    @POST("/v1/history/get_actions")
    Call<Actions> getActions(@Body Map<String, Object> requestFields);

    @POST("/v1/history/get_transaction")
    Call<Transaction> getTransaction(@Body Map<String, String> requestFields);

    @POST("/v1/history/get_key_accounts")
    Call<KeyAccounts> getKeyAccounts(@Body Map<String, String> requestFields);

    @POST("/v1/history/get_controlled_accounts")
    Call<ControlledAccounts> getControlledAccounts(@Body Map<String, String> requestFields);

}
