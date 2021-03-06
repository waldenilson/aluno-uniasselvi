package br.com.csl.alunouniasselvi;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.csl.alunouniasselvi.abstractactivity.IActivity;
import br.com.csl.alunouniasselvi.controller.GlobalController;
import br.com.csl.alunouniasselvi.list.ListViewColorAdapter;
import br.com.csl.alunouniasselvi.list.ListViewDetailColorAdapter;
import br.com.csl.alunouniasselvi.list.ListViewMenuAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TarefasActivity extends Activity implements IActivity, OnItemClickListener {

    private ProgressDialog pd;
	private GlobalController control;
	private JSONObject obj;
	private int id_seminario, id_etapa;
	private TextView tvcurso, tvtema_base,tvparticipantes;
    private ListView lvetapa;
    private List<String> tarefas, descricoes;
    private List<Integer> colors;
	private List<Integer> auxetapa = new ArrayList<Integer>();
	private List<Integer> auxtarefa = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tarefas);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		final Bundle extra = getIntent().getExtras();
		control = (GlobalController) extra.getSerializable("control");				
		id_seminario = extra.getInt("seminario");
		criarLista();
	}

	private void init(){
		tvcurso = (TextView) findViewById(R.id.tv_curso);
		tvtema_base = (TextView) findViewById(R.id.tv_tema_base);
		tvparticipantes = (TextView) findViewById(R.id.tv_participantes);
		lvetapa = (ListView) findViewById(R.id.lv_etapa);
	}
	
	private void criarLista()
	{
		tarefas = new ArrayList<String>();
		descricoes = new ArrayList<String>();
		colors = new ArrayList<Integer>();
		try 
		{
			JSONArray j = new JSONArray(control.seminario);
			obj = j.getJSONObject(id_seminario);
			tvcurso.setText( "Curso: "+obj.getString("curso") );
			tvtema_base.setText( obj.getString("modulo")+" - Tema: "+obj.getString("tema_base") );
			tvparticipantes.setText( "Participantes: "+obj.getString("grupo") );
			
			if (obj.getJSONArray("etapas").length()>0)
			{
				for(int x=0;x<obj.getJSONArray("etapas").length();x++)
				{
					JSONArray tasks = obj.getJSONArray("etapas").getJSONObject(x).getJSONArray("tarefas");
					for(int y=0; y<tasks.length();y++)
					{
						auxetapa.add( x );
						auxtarefa.add( y );
						tarefas.add( tasks.getJSONObject(y).getString("nome") );
						descricoes.add( obj.getJSONArray("etapas").getJSONObject(x).getString("nome") );
						if( Boolean.parseBoolean(tasks.getJSONObject(y).getString("check")) )
							colors.add( Color.rgb(110, 255, 110) );
						else
							colors.add( Color.WHITE );
					}
				}
			}			
		} catch (JSONException e) {
			Toast.makeText(this, getString(R.string.er_json), Toast.LENGTH_LONG).show();
			super.finish();						
		}

		ListViewColorAdapter lv = new ListViewColorAdapter(this, tarefas, descricoes, colors);
		lvetapa.setAdapter(lv);
		lvetapa.setTextFilterEnabled(true);
		lvetapa.setOnItemClickListener(this);

	}
	
	public void bt_alterar(View v) {
		
		 Intent data = new Intent(this, EditSeminarioActivity.class);
		 startActivityForResult(data,1);
		
		 }
		
	public void bt_excluir(View v) {
		 Intent data = new Intent(this, ConfirmDeleteSeminarioActivity.class);
		 startActivityForResult(data,1);
	}

	private void excluir() {
		 try
		 {
		 JSONArray j = new JSONArray(control.seminario);
		 JSONArray aux = new JSONArray();
		 for (int x=0; x < j.length(); x++){
		 if( x != id_etapa )
		 aux.put( j.getJSONObject(x) );
		 }
		 control.seminario = aux.toString();
		 control.updateSeminario();
		 finish();
		 }
		 catch(JSONException e){
		 Toast.makeText(this, getString(R.string.er_json), Toast.LENGTH_LONG).show();
		 }	 
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if( resultCode == 1){
			control = (GlobalController) data.getSerializableExtra("control");
			criarLista();
		}
		else if (resultCode == 2){
			control = (GlobalController) data.getSerializableExtra("control");
			excluir();			
		}
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
		setResult(1, getIntent());
		super.finish();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

		Intent data = new Intent(this, TarefaActivity.class);
		startActivityForResult(data,1);				

	}

}
