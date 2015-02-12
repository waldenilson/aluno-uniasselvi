package br.com.csl.alunouniasselvi.controller.abstractcontroller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.csl.alunouniasselvi.controller.GlobalController;

@SuppressWarnings("serial")
public class RestController extends AbstractRestController implements Serializable {

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
	
	
	public static List<Object> ufs(GlobalController control){
		return tratarWS( wsRest("ufs/?key="+control.getKeyAccess()) );
	}

	public static List<Object> cidades(GlobalController control){
		return tratarWS( wsRest("cidades/?key="+control.getKeyAccess()) );
	}

	public static List<Object> regioes(GlobalController control, int cidade){
		return tratarWS( wsRest("regioes/?key="+control.getKeyAccess()+"&cidade="+cidade) );
	}

	public static List<Object> servicos(GlobalController control,int regiao){
		return tratarWS( wsRest("servicos/?key="+control.getKeyAccess()+"&regiao="+regiao) );
	}

	public static List<Object> estabelecimentos(GlobalController control,int regiao, int servico){
		return tratarWS( wsRest("estabelecimentos/?key="+control.getKeyAccess()+"&regiao="+regiao+"&servico="+servico) );
	}

	public static List<Object> categorias(GlobalController control, int est){
		return tratarWS( wsRest("categorias/?key="+control.getKeyAccess()+"&estabelecimento="+est) );
	}

	public static List<Object> produtos(GlobalController control, int cat, int est){
		return tratarWS( wsRest("produtos/?key="+control.getKeyAccess()+"&categoria="+cat+"&estabelecimento="+est) );
	}

}
