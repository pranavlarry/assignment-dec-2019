package com.assignment.core.models;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model(adaptables=Resource.class)
public class FooterModel {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	@Optional
	private Resource recentPost;
	
	@Inject
	@Optional
	private Resource quickhelp;
	
	private ArrayList<RecentPostModel> recentPostList;
	private ArrayList<QuickHelpModel> quickHelpList;
	
	@PostConstruct
	protected void init() {
		if(recentPost != null) {
			
			recentPostList = new ArrayList<>();
			for(Resource currentResource: recentPost.getChildren()) {
				recentPostList.add(currentResource.adaptTo(RecentPostModel.class));
			}
		}
		
		if(quickhelp != null) {
			log.debug("nothing wrong here");
			quickHelpList = new ArrayList<>();
			for(Resource currentResource: quickhelp.getChildren()) {
				quickHelpList.add(currentResource.adaptTo(QuickHelpModel.class));
			}
		}
	}
	
	public ArrayList<RecentPostModel> getPostLinks() {
		return recentPostList;
	}
	
	public ArrayList<QuickHelpModel> getHelpLinks() {
		return quickHelpList;
	}
		
}
	
	


