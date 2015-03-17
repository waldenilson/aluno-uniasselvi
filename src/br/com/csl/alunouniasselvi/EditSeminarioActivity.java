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
	private JSONObject obj;
	private int id_seminario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_seminario);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		final Bundle extra = getIntent().getExtras();
		control = (GlobalController) extra.getSerializable("control");
				
		id_seminario = extra.getInt("seminario");
		
		try
		{
			JSONArray j = new JSONArray(control.seminario);
			obj = j.getJSONObject(id_seminario);
			etcurso.setText( obj.getString("curso") );
			ettemabase.setText( obj.getString("tema_base") );
			spmodulo.setSelection( ( Integer.parseInt( obj.getString("modulo").substring(0, 1) ) - 1 ) );
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
					obj.put("tema_base", ettemabase.getText().toString() );//*
					obj.put("curso", etcurso.getText().toString() );//*
					obj.put("grupo", etgrupo.getText().toString() );
					obj.put("modulo", spmodulo.getSelectedItem().toString() );//*
					
					
					j.put(id_seminario, obj);
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
