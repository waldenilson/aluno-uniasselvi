package br.com.csl.alunouniasselvi;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import br.com.csl.alunouniasselvi.abstractactivity.IActivity;
import br.com.csl.alunouniasselvi.controller.GlobalController;
import br.com.csl.alunouniasselvi.list.ListViewDetailAdapter;
import br.com.csl.alunouniasselvi.list.ListViewMenuAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SeminariosActivity extends Activity implements IActivity, OnItemClickListener {

    private ProgressDialog pd;
    private ListView lvseminario;
	private TextView tvnolist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seminarios);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		criarLista();				
	}

	private void criarLista()
	{
		abrirDialogProcessamento();
		List<String> modulos = new ArrayList<String>();
		List<String> descricoes = new ArrayList<String>();
		List<String> porcentagens = new ArrayList<String>();
		try
		{
			JSONArray j = new JSONArray();
			if (j.length()>0)
			{
								
				for(int x=0;x<j.length();x++)
				{
					modulos.add( j.getJSONObject(x).getString("modulo")+" - "+j.getJSONObject(x).getString("curso") );
					descricoes.add( j.getJSONObject(x).getString("tema_base") );

					double total_task = 0, ok_task = 0;
					if (j.getJSONObject(x).getJSONArray("etapas").length()>0)
					{
						for(int y=0;y<j.getJSONObject(x).getJSONArray("etapas").length();y++)
						{
							JSONArray tasks = j.getJSONObject(x).getJSONArray("etapas").getJSONObject(y).getJSONArray("tarefas");
							total_task += tasks.length();
							for(int z=0; z<tasks.length();z++)
							{
								if( Boolean.parseBoolean( tasks.getJSONObject(z).getString("check") ) )
									ok_task ++;
							}
						}
					}
					Double porc = (ok_task/total_task)*100;
					porcentagens.add( porc.intValue()+"% Concluído" );
				}
				fecharDialogProcessamento();
				tvnolist.setVisibility(View.INVISIBLE);
				lvseminario.setVisibility(View.VISIBLE);
			}
			else{
				fecharDialogProcessamento();
				lvseminario.setVisibility(View.INVISIBLE);
				tvnolist.setVisibility(View.VISIBLE);
			}
		}
		catch(JSONException e)
		{
			fecharDialogProcessamento();
			Toast.makeText(this, getString(R.string.er_json), Toast.LENGTH_LONG).show();
			super.finish();						
		}

		ListViewDetailAdapter lv = new ListViewDetailAdapter(this, modulos, descricoes, porcentagens);
		lvseminario.setAdapter(lv);
		lvseminario.setTextFilterEnabled(true);
		lvseminario.setOnItemClickListener(this);
	}
	
	private void init(){
		lvseminario = (ListView) findViewById(R.id.lv_seminario);
		tvnolist = (TextView) findViewById(R.id.tv_nolist);
	}

	public void bt_novo(View v) {
		// TODO Auto-generated method stub
		Intent data = new Intent(this, NovoSeminarioActivity.class);
		startActivityForResult(data,1);				
	}

	@Override
	public void click_bt_bar_info(View v) {
		// TODO Auto-generated method stub
		Intent data = new Intent(this, SeminariosInfoActivity.class);
		startActivityForResult(data,1);				
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
		setResult(1, getIntent());
		super.finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if( resultCode == 1)
		{
			criarLista();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

		Intent data = new Intent(this, TarefasActivity.class);
		startActivityForResult(data,1);				

	}

}
