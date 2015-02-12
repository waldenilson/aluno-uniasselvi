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
	private String nomeUsuario;
	private Integer regiao;
	private Integer servico;
	private Integer estabelecimento;
	private Integer categoria;
	
	private String urlImgAreas;
	private String urlImgModulos;
	private String jsonArrayAreas,jsonArrayModulos,jsonArrayRelatorios,jsonArrayDadosRelatorio,
					jsonArrayContasBancarias;
	
	public GlobalController()
	{
		setUrlImgAreas("http://187.33.1.93/e-cidade/imagens/files/area/");
		setUrlImgModulos("http://187.33.1.93/e-cidade/imagens/modulos/");
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
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getJsonArrayAreas() {
		return jsonArrayAreas;
	}

	public void setJsonArrayAreas(String jsonArrayAreas) {
		this.jsonArrayAreas = jsonArrayAreas;
	}

	public String getJsonArrayModulos() {
		return jsonArrayModulos;
	}

	public void setJsonArrayModulos(String jsonArrayModulos) {
		this.jsonArrayModulos = jsonArrayModulos;
	}

	public String getJsonArrayRelatorios() {
		return jsonArrayRelatorios;
	}

	public void setJsonArrayRelatorios(String jsonArrayRelatorios) {
		this.jsonArrayRelatorios = jsonArrayRelatorios;
	}

	public String getUrlImgAreas() {
		return urlImgAreas;
	}

	public void setUrlImgAreas(String urlImgAreas) {
		this.urlImgAreas = urlImgAreas;
	}

	public String getUrlImgModulos() {
		return urlImgModulos;
	}

	public void setUrlImgModulos(String urlImgModulos) {
		this.urlImgModulos = urlImgModulos;
	}

	public String getJsonArrayDadosRelatorio() {
		return jsonArrayDadosRelatorio;
	}

	public void setJsonArrayDadosRelatorio(String jsonArrayDadosRelatorio) {
		this.jsonArrayDadosRelatorio = jsonArrayDadosRelatorio;
	}

	public String getJsonArrayContasBancarias() {
		return jsonArrayContasBancarias;
	}

	public void setJsonArrayContasBancarias(String jsonArrayContasBancarias) {
		this.jsonArrayContasBancarias = jsonArrayContasBancarias;
	}

	public Integer getRegiao() {
		return regiao;
	}

	public void setRegiao(Integer regiao) {
		this.regiao = regiao;
	}

	public Integer getServico() {
		return servico;
	}

	public void setServico(Integer servico) {
		this.servico = servico;
	}

	public Integer getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Integer estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	} 
}
