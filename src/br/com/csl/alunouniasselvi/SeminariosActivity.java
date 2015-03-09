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
import android.widget.TextView;
import android.widget.Toast;

public class SeminariosActivity extends Activity implements IActivity {

    private ProgressDialog pd;
    private ListView lvseminario;
	private GlobalController control;
	private List<String> seminarios;
	private TextView tvnolist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seminarios);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		final Bundle extra = getIntent().getExtras();
		control = (GlobalController) extra.getSerializable("control");		
		criarLista();				
	}

	private void criarLista()
	{
		abrirDialogProcessamento();
		try
		{
			JSONArray j = new JSONArray(control.seminario);
			seminarios = new ArrayList<String>();
			if (j.length()>0)
			{
				for(int x=0;x<j.length();x++)
					seminarios.add( j.getJSONObject(x).getString("modulo") );
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

		ListViewMenuAdapter lv = new ListViewMenuAdapter(this, seminarios, seminarios);
		lvseminario.setAdapter(lv);
		lvseminario.setTextFilterEnabled(true);		
	}
	
	private void init(){
		lvseminario = (ListView) findViewById(R.id.lv_seminario);
		tvnolist = (TextView) findViewById(R.id.tv_nolist);
	}

	public void bt_novo(View v) {
		// TODO Auto-generated method stub
		Intent data = new Intent(this, NovoSeminarioActivity.class);
		data.putExtra("control", control);
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
		getIntent().putExtra("control", control);
		setResult(1, getIntent());
		super.finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if( resultCode == 1)
		{
			control = (GlobalController) data.getSerializableExtra("control");
			criarLista();
		}
	}

}
