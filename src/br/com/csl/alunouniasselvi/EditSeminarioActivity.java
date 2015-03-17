package br.com.csl.alunouniasselvi;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.csl.alunouniasselvi.abstractactivity.IActivity;
import br.com.csl.alunouniasselvi.controller.GlobalController;
import br.com.csl.alunouniasselvi.list.ListViewMenuAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditSeminarioActivity extends Activity implements IActivity {

    private ProgressDialog pd;
	private GlobalController control;
	private EditText ettemabase, etcurso, etgrupo;
	private Spinner spmodulo;
	private int idmodulo;
	private ArrayAdapter<String> aa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_seminario);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		final Bundle extra = getIntent().getExtras();
		control = (GlobalController) extra.getSerializable("control");
				
		int seminario = extra.getInt("seminario");
		
		try
		{
			JSONArray j = new JSONArray(control.seminario);
			JSONObject obj = j.getJSONObject(seminario);
			Toast.makeText(this, obj.getString("modulo"), Toast.LENGTH_LONG).show();
		}
		catch(JSONException e)
		{
			fecharDialogProcessamento();
			Toast.makeText(this, getString(R.string.er_json), Toast.LENGTH_LONG).show();
			super.finish();						
		}

		
	}

	private void init(){
		ettemabase = (EditText) findViewById(R.id.et_edit_tema_base);
		etcurso = (EditText) findViewById(R.id.et_edit_nome_curso);
		spmodulo = (Spinner) findViewById(R.id.sp_edit_modulo);
		etgrupo = (EditText) findViewById(R.id.et_edit_grupo);

		aa = new ArrayAdapter<String>(this, R.layout.spinner_item);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spmodulo.setAdapter(aa);
		aa.add("1º Modulo");
		aa.add("2º Modulo");
		aa.add("3º Modulo");
		aa.add("4º Modulo");
		aa.add("5º Modulo");
		aa.add("6º Modulo");
		aa.add("7º Modulo");
		aa.add("8º Modulo");
		aa.add("9º Modulo");
		aa.add("10º Modulo");
	}

	public void bt_salvar(View v) {
		// TODO Auto-generated method stub
		
		if( ettemabase.getText().toString().length() >= 3 )
		{
			if( etcurso.getText().toString().length() >= 3 )
			{
				try
				{
					JSONArray j = new JSONArray(control.seminario);
					JSONObject jo = new JSONObject();
					jo.put("tema_base", ettemabase.getText().toString() );//*
					jo.put("curso", etcurso.getText().toString() );//*
					jo.put("grupo", etgrupo.getText().toString() );
					jo.put("modulo", spmodulo.getSelectedItem().toString() );//*
					//etapas
					JSONArray etapas = new JSONArray();
					JSONObject et1 = new JSONObject(); et1.put("id", "1"); et1.put("nome", "Orientação"); etapas.put(et1);
					JSONObject et2 = new JSONObject(); et2.put("id", "2"); et2.put("nome", "Estudos preliminares"); etapas.put(et2);
					JSONObject et3 = new JSONObject(); et3.put("id", "3"); et3.put("nome", "Planejamento"); etapas.put(et3);
					JSONObject et4 = new JSONObject(); et4.put("id", "4"); et4.put("nome", "Execução"); etapas.put(et4);
					JSONObject et5 = new JSONObject(); et5.put("id", "5"); et5.put("nome", "Análise"); etapas.put(et5);
					JSONObject et6 = new JSONObject(); et6.put("id", "6"); et6.put("nome", "Socialização"); etapas.put(et6);				
					jo.put("etapas", etapas);
					
					j.put(jo);
					control.seminario = j.toString();
					control.updateSeminario();
					finish();
				}
				catch(JSONException e){
					Toast.makeText(this, getString(R.string.er_json), Toast.LENGTH_LONG).show();
				}
			}
			else
				Toast.makeText(this, getString(R.string.ale_curso), Toast.LENGTH_LONG).show();
		}
		else
			Toast.makeText(this, getString(R.string.ale_temabase), Toast.LENGTH_LONG).show();
	}

	@Override
	public void click_bt_bar_info(View v) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void abrirDialogProcessamento() {
		// TODO Auto-generated method stub
		pd = new ProgressDialog(this);
		pd.setTitle("");
		pd.setMessage(getString(R.string.ale_buscando_dados));
		pd.setCancelable(true);
		pd.setIndeterminate(true);
		pd.show();		
	}

	@Override
	public void fecharDialogProcessamento() {
		// TODO Auto-generated method stub
		if(pd != null)
			if(pd.isShowing())
				pd.dismiss();		
	}

	public void voltar(View v){
		finish();
	}
	
	@Override
	public void finish() 
	{
		getIntent().putExtra("control", control);
		setResult(1, getIntent());
		super.finish();
	}

}
