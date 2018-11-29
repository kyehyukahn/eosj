package com.bsquarelab.eosj.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface ErrorCode {
    int getNumber();
}
