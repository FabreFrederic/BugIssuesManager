package com.fabrefrederic.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fabrefrederic.library.impl.SvnRevisionControlSystem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/com/fabrefrederic/library/spring/applicationContext-library.xml" })
public class SvnRevisionControlSystemTest {
	@Autowired
	private SvnRevisionControlSystem revisionControlSystem;
	
	/**
	 * 
	 */
	// TODO : in progress
	@Test
	public void getIssueNameFromCommentTest() {
		 String comment = "AIC-100 : here is my comment issue";
		 String result = revisionControlSystem.extractIssueNameFromMessage(comment);
		System.out.println(result);
		
		comment = "AIC-12345: here is my comment issue";
		result = revisionControlSystem.extractIssueNameFromMessage(comment);
		System.out.println(result);
		
		comment = " here is my comment issue - AIC-9876";
		result = revisionControlSystem.extractIssueNameFromMessage(comment);
		System.out.println(result);
	}
}
