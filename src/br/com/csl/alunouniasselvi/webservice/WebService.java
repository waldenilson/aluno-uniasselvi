package br.com.csl.alunouniasselvi.webservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class WebService 
{	
	private static final String PROTOCOL = "";//http
	private static final String SERVER = "";//IP;
	private static final String PORT = "";//80
	private static final String PROJECT = "";//ws
	
	public WebService(){
	}
	
	public static String consumirServicosRest(String servico)
	{
		String res = "0";
		try
		{
			URL url = new URL(PROTOCOL+"://"+SERVER+":"+PORT+"/"+PROJECT+"/"+servico);

			 BufferedReader br = new BufferedReader(
			 new InputStreamReader(url.openStream()));

			 StringBuffer buffer = new StringBuffer();

			 String linha;
			 while ((linha = br.readLine()) != null) {
			 buffer.append(linha);
			 }
			 br.close();

			 res = buffer.toString(); 
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}

		return res;
	}

	public static String getProtocol() {
		return PROTOCOL;
	}

	public static String getServer() {
		return SERVER;
	}

	public static String getPort() {
		return PORT;
	}

	public static String getProject() {
		return PROJECT;
	}
	
}
