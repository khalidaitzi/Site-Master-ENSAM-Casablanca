package com.Master.SiteMasterBackEnd.Security;

public class SecutiryParams {
	public static final String SECRET = "secret1";//une clé privé bach ysawb la signature li hya secret1
	public static final long EXPIRATION_TIME = 10000; // 1 day
	public static final String HEADER_PRIFIX = "Bearer";//use in authentification.service frontend
	public static final String HEADER_NAME = "Authorization";
}
