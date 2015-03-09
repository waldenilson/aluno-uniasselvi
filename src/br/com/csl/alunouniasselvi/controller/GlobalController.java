package br.com.csl.alunouniasselvi.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
import android.os.Environment;

public class GlobalController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String keyAccess = "chave";
	public String seminario = "[]";
	private final String FILES = Environment.getExternalStorageDirectory()+"/AlunoUniasselvi/files/";
		
	public GlobalController(){
		criarConfig();
	}

	public void updateSeminario(){
		criarArquivo("seminario", this.seminario, "txt");
	}
	
	public void criarConfig(){
		if ( ! isFile("seminario", "txt") )
			criarArquivo("seminario", this.seminario, "txt");
		else
			this.seminario = buscarArquivo("seminario", "txt");
	}
	
	private boolean criarArquivo(String nome, String corpo, String extensao)
	{
		
		// teste criacao arquivo txt com xml da app
			try 
			{
				File dir = new File( FILES );
                dir.mkdirs();
				new File(dir,nome+"."+extensao);
				FileWriter fw = new FileWriter( FILES+nome+"."+extensao , false );
				fw.write(corpo);
				fw.close();
				
				return true;
			}
			catch (IOException e) {
				return false;
			}
	}

	private boolean isFile(String nome, String extensao)
	{
			File dir = new File( FILES );
			File f = new File(dir,nome+"."+extensao);
			if(f.exists()) // se arquivo existe
				return true;
			else
				return false;		
	}
	
	private String buscarArquivo(String nome, String extensao)
	{
		String retorno = "";
		// teste buscar arquivo txt com xml da app
		try 
		{
			File dir = new File( FILES );
            
			File f = new File(dir,nome+"."+extensao);
			StringBuffer bufSaida = new StringBuffer();

			if(f.exists()) // se arquivo existe
			{
				BufferedReader br = new BufferedReader(new FileReader(f) );
                String line;
                while( (line = br.readLine() ) != null)
                {
                    bufSaida.append(line+"\n");
                }
                br.close();
                retorno = bufSaida.toString();
			}
			else // se arquivo nao existir
				retorno = "03";
		}
		catch (IOException e) {
			retorno = "00";
		}
		return retorno;
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
