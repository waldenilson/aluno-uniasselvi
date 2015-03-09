package br.com.csl.alunouniasselvi;

import java.util.ArrayList;
import java.util.List;

import br.com.csl.alunouniasselvi.abstractactivity.IActivity;
import br.com.csl.alunouniasselvi.controller.GlobalController;
import br.com.csl.alunouniasselvi.list.ListViewImageAdapter;
import br.com.csl.alunouniasselvi.list.ListViewMenuAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements IActivity, OnItemClickListener {

    private ProgressDialog pd;
    private ListView lvmenu;
    private GlobalController control = new GlobalController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		List<String> lista = new ArrayList<String>();
		List<String> desc = new ArrayList<String>();
		List<Drawable> img = new ArrayList<Drawable>();
		
		lista.add( getString(R.string.lb_simulador_notas) ); desc.add( getString( R.string.lb_desc_simulador_notas) );//simulador
		img.add( getResources().getDrawable(R.drawable.calculator) );
		lista.add( getString(R.string.lb_seminarios) ); desc.add( getString( R.string.lb_desc_seminarios) );//seminario
		img.add( getResources().getDrawable(R.drawable.paper) );
		lista.add( getString(R.string.lb_codigo_fonte) ); desc.add( getString( R.string.lb_desc_codigo_fonte) );//codigo fonte
		img.add( getResources().getDrawable(R.drawable.code) );
		lista.add( getString(R.string.lb_creditos) ); desc.add( getString( R.string.lb_desc_creditos) );//creditos
		img.add( getResources().getDrawable(R.drawable.user) );
		lista.add( getString(R.string.lb_novidades) ); desc.add( getString( R.string.lb_desc_novidades) );//novidades
		img.add( getResources().getDrawable(R.drawable.newspaper) );
		
		ListViewImageAdapter lv = new ListViewImageAdapter(this, lista, desc, img);
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
		else if( arg2 == 1){
			Intent data = new Intent(this, SeminariosActivity.class);
			data.putExtra("control", control);
			startActivityForResult(data,1);				
		}
		else if( arg2 == 3){
			Intent data = new Intent(this, CreditosActivity.class);
			startActivityForResult(data,1);				
		}
		else if( arg2 == 2){
			Intent data = new Intent(this, CodigoFonteActivity.class);
			startActivityForResult(data,1);				
		}
		else if( arg2 == 4){
			Intent data = new Intent(this, NovidadesActivity.class);
			startActivityForResult(data,1);				
		}		
	}

}
