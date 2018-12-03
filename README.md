# EOS Java API Wrapper

*A Java implementation of the EOS RPC Calls. Under the [MIT Licence](https://raw.githubusercontent.com/Fletch153/eos-java-rpc-wrapper/master/LICENSE)*.

Created by [eos42](http://www.eos42.io).

The api documentation can be found in the official eos developers portal:
https://developers.eos.io/eosio-nodeos/reference

All but the following queries are supported:
1. CHAIN
- get_header_block_state
- get_producers
- push_block
2. WALLET
- set_dir
- set_eosio_key
3. NET
- connect
- disconnect
- connections
- status
4. PRODUCER
- pause
- resume
- paused

#### Requirements
* Java 8
* Maven

#### Installation
Install using maven build tool. The artifact will need to be published locally.

Currently the artifiac is not in the official maven repositories.
If you want to use it in a maven build, you can add the following repository

``` xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

and dependency

``` xml
<dependency>
    <groupId>com.github.EOSEssentials</groupId>
    <artifactId>eos-java-rpc-wrapper</artifactId>
    <version>master</version>
</dependency>
```

#### Configuration
Create a new instance of EosApiClient using the EosApiClientFactory, this will require
a baseurl to be passed in.

This will use the same base url for all three api endpoints (history/chain/wallet).
```java
EosApiRestClient eosApiRestClient = EosApiClientFactory.newInstance("http://127.0.0.1:8888").newRestClient();
```

If you want to use separate urls for those endpoints (e.g. you have a local wallet):
```java
EosApiRestClient eosApiRestClient = EosApiClientFactory.newInstance(
    walletBaseUrl, chainBaseUrl, historyBaseUrl).newRestClient();
```

#### Example Usage
##### Creating a wallet
```java
eosApiRestClient.createWallet("walletName");
```

#### Getting a block
```java
eosApiRestClient.getBlock("blockNumberOrId")
```

#### Signing and pushing a transaction

```java
        String CHAIN_ID_MAINET = "aca376f206b8fc25a6ed44dbdc66547c36c6c33e3a119ffbeaef943642f0e906";
        String CHAIN_ID_JUNGLE = "038f4b0fc8ff18a4f0842a8f0564611f6e96e8535901dd45e43ac8691a1c4dca";
        
        // Set the client
        EosApiRestClient eosApiRestClient = EosApiClientFactory.newInstance("http://127.0.0.1:8899", "http://127.0.0.1:8888", "http://127.0.0.1:8888").newRestClient();
        
        // Open the wallet
        eosApiRestClient.openWallet("default");
        eosApiRestClient.unlockWallet("default", "PW5KhdSnrn1ubnUqg3xUNJ7NzjWNcaaM31Qdr2MgAmwrpj7rdLsoV");
        
        
        /* Create the json array of arguments */
        Map<String, String> args = new HashMap<String, String>(4);
        args.put("user", "kye");
        AbiJsonToBin data = eosApiRestClient.abiJsonToBin("hello", "hi", args);

        /* Get the head block */
        Block block = eosApiRestClient.getBlock(eosApiRestClient.getChainInfo().getHeadBlockId());
        String ts = block.getTimeStamp();

        /* Create Transaction Action Authorization */
        TransactionAuthorization transactionAuthorization = new TransactionAuthorization();
        transactionAuthorization.setActor("eosio");
        transactionAuthorization.setPermission("active");

        /* Create Transaction Action */
        TransactionAction transactionAction = new TransactionAction();
        transactionAction.setAccount("hello");
        transactionAction.setName("hi");
        transactionAction.setData(data.getBinargs());
        //transactionAction.setHex_data(data.getBinargs());
        transactionAction.setAuthorization(Collections.singletonList(transactionAuthorization));
        
        /* Create a transaction */
        PackedTransaction packedTransaction = new PackedTransaction();
        packedTransaction.setRefBlockPrefix(block.getRefBlockPrefix().toString());
        packedTransaction.setRefBlockNum(block.getBlockNum().toString());
        // expired after 3 minutes
        String expiration = ZonedDateTime.now(ZoneId.of("GMT")).plusMinutes(3).truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        packedTransaction.setExpiration(expiration);
        packedTransaction.setRegion("0");
        packedTransaction.setMax_net_usage_words("0");
        packedTransaction.setActions(Collections.singletonList(transactionAction));

        /* Sign the Transaction */
        
        SignedPackedTransaction signedPackedTransaction 
        = eosApiRestClient.signTransaction(packedTransaction, 
        								Collections.singletonList("EOS6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV"), 
        								"cf057bbfb72640471fd910bcb67639c22df9f92470936cddc1ade0e2f2e7dc4f");

        
        /* Push the transaction */
        PushedTransaction PushedTransaction = eosApiRestClient.pushTransaction("none", signedPackedTransaction);  

```

```java
   		String wallet = "default";
    	String wallet_password = "PW5KhdSnrn1ubnUqg3xUNJ7NzjWNcaaM31Qdr2MgAmwrpj7rdLsoV";
    	String account = "prorata";
    	String account_password = "EOS8f3xTQz16gM51GnmFWoE2Mwwyg7rVRq691sjbYfHTRrJYqX1cG";
    	EosApiService eosARTApiService 
        = new EosApiServiceImpl(wallet, wallet_password, account, account_password );
        System.out.println(eosARTApiService.setArtHash("hash03"));
        System.out.println(eosARTApiService.setHoldersHash("Users03"));
        System.out.println(eosARTApiService.addTransactionsHash("Txs03"));
        System.out.println(eosARTApiService.getArtHash());
        System.out.println(eosARTApiService.getHoldersHash());
        System.out.println(eosARTApiService.getTransactionsHash());            
```


#### Notes
* All methods are synchronous and blocking.
* All methods will throw a catchable EOSApiException.
