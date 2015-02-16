package br.com.csl.alunouniasselvi;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.csl.alunouniasselvi.abstractactivity.IActivity;
import br.com.csl.alunouniasselvi.list.ListViewSimpleAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ResultadoSimuladorActivity extends Activity implements IActivity, OnItemClickListener {

    private ProgressDialog pd;
	private ListView listresultado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado_simulador);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		final Bundle extra = getIntent().getExtras();
		
		List<String> title = new ArrayList<String>();
		
		JSONObject possibilidades = null;
		try
		{
			possibilidades = new JSONObject( extra.getString("possibilidades") );
			for( int x=0; x< possibilidades.length();x++)
			{
				int pos = x+1;
				title.add(possibilidades.getString(pos+""));
			}
		}
		catch (JSONException e) {}
		
		ListViewSimpleAdapter list = new ListViewSimpleAdapter(this, title);
		listresultado.setAdapter(list);
		listresultado.setTextFilterEnabled(true);	
		listresultado.setOnItemClickListener(this);
	}

	private void init(){
		listresultado = (ListView) findViewById(R.id.lv_resultado);
	}

	@Override
	public void click_bt_bar_back(View v) {
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
		
	}

}
