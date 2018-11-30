package com.bsquarelab.eosj.impl;

import com.bsquarelab.eosj.exception.EosApiErrorCode;
import com.bsquarelab.eosj.exception.EosApiError;
import com.bsquarelab.eosj.exception.EosApiException;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class EosApiServiceGenerator {
	
	private static boolean LOGGING = false;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create());

    private static Retrofit retrofit;

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
    	
    	if (LOGGING) {
	    	HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
	    	interceptor.setLevel(Level.BODY);	
	    	httpClient.addInterceptor(interceptor);
    	}
        builder.baseUrl(baseUrl);
        builder.client(httpClient.build());
        builder.addConverterFactory(JacksonConverterFactory.create());
        retrofit = builder.build();
  

        return retrofit.create(serviceClass);
    }

    /**
     * Execute a REST call and block until the response is received.
     */
    public static <T> T executeSync(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                EosApiError apiError = getEosApiError(response);
                throw new EosApiException(apiError.getDetailedMessage(), EosApiErrorCode.get(apiError.getEosErrorCode()));
            }
        } catch (IOException e) {
            throw new EosApiException(e);
        }
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    private static EosApiError getEosApiError(Response<?> response) throws IOException, EosApiException {
        return (EosApiError) retrofit.responseBodyConverter(EosApiError.class, new Annotation[0])
                .convert(response.errorBody());
    }
}
