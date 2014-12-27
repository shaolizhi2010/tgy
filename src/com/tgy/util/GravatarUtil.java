package com.tgy.util;

import com.timgroup.jgravatar.Gravatar;
import com.timgroup.jgravatar.GravatarDefaultImage;
import com.timgroup.jgravatar.GravatarRating;

public class GravatarUtil {
 
	public static String getGravatar160pxUrl(String email) {
	    Gravatar gravatar = new Gravatar();
	    gravatar.setSize(160);
	    gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
	    gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);
	    return gravatar.getUrl(email);
	}

	public static String getGravatar80pxUrl(String email) {
	    Gravatar gravatar = new Gravatar();
	    gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
	    gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);
	    return gravatar.getUrl(email);
	}
	
	public static void main(String[] args) {
		
		System.out.println(getGravatar80pxUrl("shaolizhi2010#gmail.com"));
	}

}
