package br.com.csl.alunouniasselvi;

import br.com.csl.alunouniasselvi.abstractactivity.IActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity implements IActivity {

    private ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
	}

	private void init(){

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void bt_simulador_notas(View v){
		Intent data = new Intent(this, SimuladorNotasActivity.class);
		startActivityForResult(data,1);		
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

	@Override
	public void finish() 
	{
		setResult(1, getIntent());
		super.finish();
	}

}
