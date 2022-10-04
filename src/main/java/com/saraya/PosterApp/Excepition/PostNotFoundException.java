package com.saraya.PosterApp.Excepition;

import java.text.MessageFormat;

public class PostNotFoundException extends Exception{
    public PostNotFoundException(Long id){
        super(MessageFormat.format("This post {0} does not exist", id));
    }
}
