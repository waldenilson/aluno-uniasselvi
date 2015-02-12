package br.com.csl.alunouniasselvi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GlobalController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String keyAccess = "chave";
		
	public GlobalController(){
	}

	public Bitmap getBitmapFromURL(String src) {
        try {
            
        	Bitmap bitmap = BitmapFactory.
			decodeStream((InputStream)
				new URL(src).getContent());
	
        	return bitmap;
        
        
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public List<String> subLista(List<String> list,int ini,int fim){
		
		List<String> aux = new ArrayList<String>();
		for(int x=ini ; x < fim;x++){
			aux.add( list.get(x) );
		}
		return aux;
	}
		
	//Função para criar hash da senha informada  
	   public static String md5(String senha) 
	   {  
		   try 
	       {  
			   MessageDigest md = MessageDigest.getInstance("MD5");  
	           BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));  
	           return  hash.toString(16);           
	       }
		   catch (NoSuchAlgorithmException  e)
	       {  
			   e.printStackTrace();  
	       	   return "null";
	       }  
	   }

	public String getKeyAccess() {
		return keyAccess;
	}

	public void setKeyAccess(String keyAccess) {
		this.keyAccess = keyAccess;
	}	
}
