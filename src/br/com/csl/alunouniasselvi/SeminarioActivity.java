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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SeminarioActivity extends Activity implements IActivity {

    private ProgressDialog pd;
	private GlobalController control;
	private JSONObject obj;
	private int id_seminario;
	private TextView tvcurso,tvtema_base,tvparticipantes;
	private LinearLayout llseminario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seminario);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		final Bundle extra = getIntent().getExtras();
		control = (GlobalController) extra.getSerializable("control");				
		id_seminario = extra.getInt("seminario");
		montarActivity();
	}

	private void init(){
		tvcurso = (TextView) findViewById(R.id.tv_curso);
		tvtema_base = (TextView) findViewById(R.id.tv_tema_base);
		tvparticipantes = (TextView) findViewById(R.id.tv_participantes);
		llseminario = (LinearLayout) findViewById(R.id.ll_seminario);
	}
	
	private void montarActivity()
	{
		try {
			JSONArray j = new JSONArray(control.seminario);
			obj = j.getJSONObject(id_seminario);
			tvcurso.setText( "Curso: "+obj.getString("curso")+". "+obj.getString("modulo") );
			tvtema_base.setText( "Tema Base: "+obj.getString("tema_base") );
			tvparticipantes.setText( "Participantes do grupo: "+obj.getString("grupo") );
			
			TextView tvetapa = new TextView(this);
			tvetapa.setText("etapa");
			llseminario.addView( tvetapa );
			
		} catch (JSONException e) {
			Toast.makeText(this, getString(R.string.er_json), Toast.LENGTH_LONG).show();
			super.finish();						
		}
	}

	public void bt_salvar(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "salvo", Toast.LENGTH_LONG).show();
	}

	public void click_bt_bar_alterar(View v) {

		Intent data = new Intent(this, EditSeminarioActivity.class);
		data.putExtra("control", control);
		data.putExtra("seminario", id_seminario);
		startActivityForResult(data,1);				

	}
	
	public void click_bt_bar_excluir(View v) {
		try
		{
			JSONArray j = new JSONArray(control.seminario);
			JSONArray aux = new JSONArray();
			for (int x=0; x < j.length(); x++){
				if( x != id_seminario )
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
			montarActivity();
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
		getIntent().putExtra("control", control);
		setResult(1, getIntent());
		super.finish();
	}

}
