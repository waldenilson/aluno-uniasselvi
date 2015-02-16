package br.com.csl.alunouniasselvi;

import java.util.ArrayList;
import java.util.List;

import br.com.csl.alunouniasselvi.abstractactivity.IActivity;
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

public class MainActivity extends Activity implements IActivity, OnItemClickListener {

    private ProgressDialog pd;
    private ListView lvmenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		List<String> lista = new ArrayList<String>();
		List<String> desc = new ArrayList<String>();
		lista.add( getString(R.string.lb_simulador_notas) ); desc.add( getString( R.string.lb_desc_simulador_notas) );
		lista.add( getString(R.string.lb_simulador_notas) ); desc.add( getString( R.string.lb_desc_simulador_notas) );
		lista.add( getString(R.string.lb_simulador_notas) ); desc.add( getString( R.string.lb_desc_simulador_notas) );
		lista.add( getString(R.string.lb_simulador_notas) ); desc.add( getString( R.string.lb_desc_simulador_notas) );
		lista.add( getString(R.string.lb_simulador_notas) ); desc.add( getString( R.string.lb_desc_simulador_notas) );

		ListViewMenuAdapter lv = new ListViewMenuAdapter(this, lista, desc);
		lvmenu.setAdapter(lv);
		lvmenu.setTextFilterEnabled(true);
		lvmenu.setOnItemClickListener(this);	

	}

	private void init(){
		lvmenu = (ListView) findViewById(R.id.lv_menu);
	}

	public void bt_sair(View v){
		finish();
	}
	
	public void bt_simulador_notas(){
		Intent data = new Intent(this, SimuladorNotasActivity.class);
		startActivityForResult(data,1);		
	}

	@Override
	public void click_bt_bar_info(View v) {
		// TODO Auto-generated method stub
		Intent data = new Intent(this, InfoActivity.class);
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

	@Override
	public void finish() 
	{
		setResult(1, getIntent());
		super.finish();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
		if( arg2 == 0 )
			bt_simulador_notas();
	}

}
