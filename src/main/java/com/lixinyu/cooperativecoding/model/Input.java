package com.lixinyu.cooperativecoding.model;

import java.util.List;

public class Input {
    private List<byte[]> inputs;

    public Input(){

    }
    public Input(List<byte[]> inputs) {
        this.inputs = inputs;
    }

    public List<byte[]> getInputs() {
        return inputs;
    }

    public void setInputs(List<byte[]> inputs) {
        this.inputs = inputs;
    }
}
