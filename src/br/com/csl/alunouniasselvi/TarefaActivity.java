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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TarefaActivity extends Activity implements IActivity {

    private ProgressDialog pd;
	private GlobalController control;
	private JSONObject obj;
	private int id_seminario, id_etapa, id_tarefa;
	private TextView tvcurso, tvtema, tvetapa;
	private EditText etdescricao;
	private CheckBox cbtarefa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tarefa);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		final Bundle extra = getIntent().getExtras();
		control = (GlobalController) extra.getSerializable("control");				
		id_seminario = extra.getInt("seminario");
		id_etapa = extra.getInt("etapa");
		id_tarefa = extra.getInt("tarefa");
		
		try 
		{
			JSONArray j = new JSONArray(control.seminario);
			JSONObject seminario = j.getJSONObject(id_seminario);
			JSONObject etapa = seminario.getJSONArray("etapas").getJSONObject(id_etapa);
			obj = etapa.getJSONArray("tarefas").getJSONObject(id_tarefa);

			tvcurso.setText( "Curso: "+seminario.getString("curso") );
			tvtema.setText( seminario.getString("modulo")+" - Tema: "+seminario.getString("tema_base") );
			tvetapa.setText("Etapa: "+etapa.getString("nome"));
			
			cbtarefa.setText( obj.getString("nome") );
			cbtarefa.setChecked( Boolean.parseBoolean( obj.getString("check") ) );
			etdescricao.setText(obj.getString("descricao"));
			
		} catch (JSONException e) {
			Toast.makeText(this, getString(R.string.er_json)+e.getMessage(), Toast.LENGTH_LONG).show();
			super.finish();						
		}

	}

	private void init(){
		tvcurso = (TextView) findViewById(R.id.tv_task_curso);
		tvtema = (TextView) findViewById(R.id.tv_task_tema_base);
		tvetapa = (TextView) findViewById(R.id.tv_task_etapa);
		etdescricao = (EditText) findViewById(R.id.et_task_descricao);
		cbtarefa = (CheckBox) findViewById(R.id.cb_task);
	}
	
	public void bt_salvar(View v) {
		// TODO Auto-generated method stub
		
		try 
		{
			JSONArray j = new JSONArray(control.seminario);
			obj = j.getJSONObject(id_seminario).getJSONArray("etapas").getJSONObject(id_etapa).getJSONArray("tarefas").getJSONObject(id_tarefa);
			obj.put("descricao", etdescricao.getText().toString());
			obj.put("check", ""+cbtarefa.isChecked());
			
			j.getJSONObject(id_seminario).getJSONArray("etapas").getJSONObject(id_etapa).getJSONArray("tarefas").put(id_tarefa, obj);
			control.seminario = j.toString();
			control.updateSeminario();
			finish();
			
		} catch (JSONException e) {
			Toast.makeText(this, getString(R.string.er_json), Toast.LENGTH_LONG).show();
			super.finish();						
		}
		
	
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if( resultCode == 1){
			control = (GlobalController) data.getSerializableExtra("control");
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

}
