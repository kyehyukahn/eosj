# Example of push_transaction
# Tested with eos version 1.2.1
# below script are same as
# cleos push action eosio.token transfer '{"from":"applecookies","to":"blueberryjam","quantity":"1.0000 EOS","memo":"memo"}' -p applecookies@active
CODE="hello"
ACTION="hi"
ARGS='{"user":"kye","memo":"memo"}'
ACTOR="eosio"
PUBLIC_KEY="EOS6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV" # ACTOR's

# edit this for your environment
CHAIN="curl --request POST --url http://127.0.0.1:8888/v1/chain"
WALLET="curl --request POST --url http://127.0.0.1:8899/v1/wallet"
#CLEOS="cleos -u http://127.0.0.1:8888 --wallet-url=http://127.0.0.1:8899"
CLEOS="cleos"
# 1. Get required information (chain_id, block_num, block_prefix, expiration)
GET_INFO=`$CHAIN/get_info`
CHAIN_ID=`echo $GET_INFO | jq '.chain_id'`
BLOCK_NUM=`echo $GET_INFO | jq '.last_irreversible_block_num'`
GET_BLOCK=`$CHAIN/get_block --data '{"block_num_or_id":'$BLOCK_NUM'}'`
BLOCK_PREFIX=`echo $GET_BLOCK | jq '.ref_block_prefix'`
TIMESTAMP=`echo $GET_BLOCK | jq '.timestamp'`
EXPIRATION=`jq -n ''$TIMESTAMP' | strptime("%Y-%m-%dT%H:%M:%S.%3f") | mktime | . + 30 | todate | sub("Z"; .before)'`
EXPIRATION='"2018-11-28T10:59:17"'
echo "chain_id: $CHAIN_ID"
echo "block_num: $BLOCK_NUM"
echo "block_prefix: $BLOCK_PREFIX"
echo "expiration: $EXPIRATION"

# 2. Convert arguments: json -> binary
BINARGS=`$CHAIN/abi_json_to_bin --data '{"args":'"$ARGS"',"code":"'$CODE'","action":"'$ACTION'"}' | jq '.binargs'`
echo "binargs: $BINARGS"


# 3. Sign the transaction
TX='{"actions":[{"account":"'$CODE'","name":"'$ACTION'","authorization":[{"actor":"'$ACTOR'","permission":"active"}],"data":'$BINARGS'}],"signatures":[],"expiration":'$EXPIRATION',"ref_block_num":"'$BLOCK_NUM'","ref_block_prefix":"'$BLOCK_PREFIX'"}'
echo "TX: $TX"
SIGNED_TX=`$WALLET/sign_transaction --data '['$TX', ["'$PUBLIC_KEY'"], '$CHAIN_ID']'`
echo "SIGNED_TX: $SIGNED_TX"

# 4. Pack the signed_transaction
PACKED_TX=`$CLEOS convert pack_transaction ''$SIGNED_TX''`
echo "PACKED_TX: $PACKED_TX"

# 5. Call push transaction using packed transaction
echo "\n-- Results of push_transaction --"
#$CHAIN/push_transaction --data ''"$PACKED_TX"''
echo ""
