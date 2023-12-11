package com.qradmin.rest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class BaseController {
    protected Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .disableHtmlEscaping()
            .create();
    protected static final Gson gson = new Gson();
}
