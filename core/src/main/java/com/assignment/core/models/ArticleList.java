package com.assignment.core.models;

import org.apache.sling.api.resource.Resource;

import javax.inject.Inject;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;


@Model(adaptables = Resource.class)
public class ArticleList {
	   @Inject
	   @Optional
	   public Resource article;
	   
}
