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
import android.widget.ListView;
import android.widget.Toast;

public class SeminariosActivity extends Activity implements IActivity {

    private ProgressDialog pd;
    private ListView lvseminario;
	private GlobalController control;
	private List<String> seminarios;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seminarios);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		final Bundle extra = getIntent().getExtras();
		control = (GlobalController) extra.getSerializable("control");		

		abrirDialogProcessamento();
		try
		{
			JSONArray j = new JSONArray(control.seminario);
			seminarios = new ArrayList<String>();
			if (j.length()>0)
				for(int x=0;x<j.length();x++)
					seminarios.add( j.getJSONObject(x).getString("modulo") );
			else{
				fecharDialogProcessamento();
				Toast.makeText(this, getString(R.string.ale_retorno_vazio), Toast.LENGTH_LONG).show();
				super.finish();			
			}
		}
		catch(JSONException e)
		{
			fecharDialogProcessamento();
			Toast.makeText(this, getString(R.string.er_json), Toast.LENGTH_LONG).show();
			super.finish();						
		}

		ListViewMenuAdapter lv = new ListViewMenuAdapter(this, seminarios, seminarios);
		lvseminario.setAdapter(lv);
		lvseminario.setTextFilterEnabled(true);
				
	}

	private void init(){
		lvseminario = (ListView) findViewById(R.id.lv_seminario);
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
		getIntent().putExtra("control", control);
		setResult(1, getIntent());
		super.finish();
	}

}
