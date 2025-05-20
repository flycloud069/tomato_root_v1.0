package com.root.sevice.classs;

public class SysSqlTransformForPostgreSQL {
    private String[] sql={""};
    private int numberOfSentence=0;
    public SysSqlTransformForPostgreSQL from(){
    return this;
    }
    public SysSqlTransformForPostgreSQL select(){
        return this;
    }
    public SysSqlTransformForPostgreSQL orderBy(){
        return this;
    }

    public SysSqlTransformForPostgreSQL groubBy(){
        return this;
    }
    public SysSqlTransformForPostgreSQL distinct(){
        return this;
    }
    public SysSqlTransformForPostgreSQL createTemp(){
        return this;
    }
    public SysSqlTransformForPostgreSQL insert(){
        return this;
    }

    public SysSqlTransformForPostgreSQL update(){
        return this;
    }

    public SysSqlTransformForPostgreSQL delete(){
        return this;
    }


    public SysSqlTransformForPostgreSQL next(){
        return this;
    }


}
