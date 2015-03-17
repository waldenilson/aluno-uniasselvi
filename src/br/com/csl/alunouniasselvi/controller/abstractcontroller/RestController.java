package br.com.csl.alunouniasselvi.controller.abstractcontroller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class RestController extends AbstractRestController implements Serializable {

	@SuppressWarnings("unused")
	private static List<Object> tratarWS(String res)
	{
		List<Object> retorno = new ArrayList<Object>();
		try
		{
			List<JSONObject> l = new ArrayList<JSONObject>();
			JSONArray ja = new JSONArray(res);
			for(int x=0; x<ja.length();x++)
				l.add(ja.getJSONObject(x));
			retorno.add("99");
			retorno.add(l);
			return retorno;
		}
		catch(Exception e)
		{
			retorno.add(res);
			retorno.add(null);
			return retorno;
		}
	}
	
}
