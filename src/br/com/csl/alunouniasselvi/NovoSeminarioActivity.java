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

public class NovoSeminarioActivity extends Activity implements IActivity {

    private ProgressDialog pd;
	private GlobalController control;
	private EditText ettemabase, etcurso;
	private Spinner spmodulo;
	private int idmodulo;
	private ArrayAdapter<String> aa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo_seminario);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		final Bundle extra = getIntent().getExtras();
		control = (GlobalController) extra.getSerializable("control");		
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

	private void init(){
		ettemabase = (EditText) findViewById(R.id.et_tema_base);
		etcurso = (EditText) findViewById(R.id.et_nome_curso);
		spmodulo = (Spinner) findViewById(R.id.sp_modulo);
	}

	public void bt_criar(View v) {
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
					jo.put("grupo", "" );
					jo.put("modulo", spmodulo.getSelectedItem().toString() );//*
					//etapas
					JSONArray etapas = new JSONArray();
					JSONObject et1 = new JSONObject(); et1.put("id", "1"); et1.put("nome", "Orientação"); et1.put("descricao", ""); et1.put("ch", "4"); etapas.put(et1);
					//tarefas
						JSONArray t1 = new JSONArray();
						JSONObject t11 = new JSONObject(); t11.put("check", "0"); t11.put("nome", "Tarefa 01"); t11.put("descricao", ""); t1.put(t11);
						JSONObject t12 = new JSONObject(); t12.put("check", "0"); t12.put("nome", "Tarefa 02"); t12.put("descricao", ""); t1.put(t12);					
						et1.put("tarefas", t1);					
					JSONObject et2 = new JSONObject(); et2.put("id", "2"); et2.put("nome", "Estudos preliminares"); et2.put("descricao", ""); et2.put("ch", "5"); etapas.put(et2);
					//tarefas
						JSONArray t2 = new JSONArray();
						JSONObject t21 = new JSONObject(); t21.put("check", "0"); t21.put("nome", "Tarefa 01"); t21.put("descricao", ""); t2.put(t21);
						JSONObject t22 = new JSONObject(); t22.put("check", "0"); t22.put("nome", "Tarefa 02"); t22.put("descricao", ""); t2.put(t22);					
						et2.put("tarefas", t2);
					JSONObject et3 = new JSONObject(); et3.put("id", "3"); et3.put("nome", "Planejamento"); et3.put("descricao", ""); et3.put("ch", "5"); etapas.put(et3);
					//tarefas
						JSONArray t3 = new JSONArray();
						JSONObject t31 = new JSONObject(); t31.put("check", "0"); t31.put("nome", "Tarefa 01"); t31.put("descricao", ""); t3.put(t31);
						JSONObject t32 = new JSONObject(); t32.put("check", "0"); t32.put("nome", "Tarefa 02"); t32.put("descricao", ""); t3.put(t32);					
						et3.put("tarefas", t3);
					JSONObject et4 = new JSONObject(); et4.put("id", "4"); et4.put("nome", "Execução"); et4.put("descricao", ""); et4.put("ch", "4"); etapas.put(et4);
					//tarefas
						JSONArray t4 = new JSONArray();
						JSONObject t41 = new JSONObject(); t41.put("check", "0"); t41.put("nome", "Tarefa 01"); t41.put("descricao", ""); t4.put(t41);
						JSONObject t42 = new JSONObject(); t42.put("check", "0"); t42.put("nome", "Tarefa 02"); t42.put("descricao", ""); t4.put(t42);					
						et4.put("tarefas", t4);
					JSONObject et5 = new JSONObject(); et5.put("id", "5"); et5.put("nome", "Análise"); et5.put("descricao", ""); et5.put("ch", "18"); etapas.put(et5);
					//tarefas
						JSONArray t5 = new JSONArray();
						JSONObject t51 = new JSONObject(); t51.put("check", "0"); t51.put("nome", "Tarefa 01"); t51.put("descricao", ""); t5.put(t51);
						JSONObject t52 = new JSONObject(); t52.put("check", "0"); t52.put("nome", "Tarefa 02"); t52.put("descricao", ""); t5.put(t52);					
						et5.put("tarefas", t5);
					JSONObject et6 = new JSONObject(); et6.put("id", "6"); et6.put("nome", "Socialização"); et6.put("descricao", ""); et6.put("ch", "4"); etapas.put(et6);				
					//tarefas
						JSONArray t6 = new JSONArray();
						JSONObject t61 = new JSONObject(); t61.put("check", "0"); t61.put("nome", "Tarefa 01"); t61.put("descricao", ""); t6.put(t61);
						JSONObject t62 = new JSONObject(); t62.put("check", "0"); t62.put("nome", "Tarefa 02"); t62.put("descricao", ""); t6.put(t62);					
						et6.put("tarefas", t6);
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
