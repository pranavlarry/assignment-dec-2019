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
public class QuickHelpModel {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	@Inject
	@Optional
	private Resource resource;
	
	@Inject
	@Optional
	private String quickhelpLink;
	
	private String title;
	
	@Inject
	private ResourceResolver resolver;
	
	@PostConstruct
	protected void init() {
		log.debug("nothing wrong here2");
		title=resolver.getResource(quickhelpLink).getName();
		log.debug("heool-->"+title);
	}

	public String getQuickhelpLink() {
		return quickhelpLink;
	}


	public String getTitle() {
		return title;
	}


	
}
