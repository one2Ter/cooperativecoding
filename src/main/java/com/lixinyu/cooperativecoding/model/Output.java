package com.lixinyu.cooperativecoding.model;

public class Output {
    private String error;
    private String input;
    private String output;


    public Output() {
    }

    public Output(String error, String input, String output) {
        this.error = error;
        this.input = input;
        this.output = output;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}
