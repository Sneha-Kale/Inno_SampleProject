package com.example.inno_sampleproject.Retrofit;



public enum KsAppEnvironment {

    SANDBOX {

        public  String api_base_url(){return "https://reqres.in/api/";}

    };

    public abstract String api_base_url();

}
