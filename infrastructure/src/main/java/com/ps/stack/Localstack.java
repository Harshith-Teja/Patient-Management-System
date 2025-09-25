package com.ps.stack;

import software.amazon.awscdk.*;


public class Localstack extends Stack {

    public Localstack(final App scope, final String id, final StackProps props){
        super(scope, id, props);
    }

    public static void main(final String[] args) {
        //the cloud formation will be stored in ./cdk.out
        App app = new App(AppProps.builder().outdir("./cdk.out").build());

        //synthesizer converts our java infrastructure code into cloud formation template, and bootStraplessSynthesizer is a type of synthesizer, we are telling cdk code to skip the initial bootstrapping, as it is not needed for our localstack
        StackProps props = StackProps.builder().synthesizer(new BootstraplessSynthesizer()).build();

        new Localstack(app, "localstack", props);
        app.synth(); //creates the cloud formation template in ./cdk.out
        System.out.println("App synthesizing in progress...");
    }
}
