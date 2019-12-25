package com.assignment.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class)
public class RecentPostModel {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	@Inject
	@Optional
	private Resource resource;
	
	@Inject
	@Optional
	private String recentPostLink;
	
	private String title;
	
	@Inject
	private ResourceResolver resolver;
	
	@PostConstruct
	protected void init() {
		log.debug("nothing wrong here3");
		title=resolver.getResource(recentPostLink).getName();
	}

	public String getRecentPostLink() {
		return recentPostLink;
	}
	
	public String getTitle() {
		return title;
	}

	
}
