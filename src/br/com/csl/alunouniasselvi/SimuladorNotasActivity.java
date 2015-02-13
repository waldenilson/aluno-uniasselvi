package br.com.csl.alunouniasselvi;

import java.util.ArrayList;
import java.util.List;
import br.com.csl.alunouniasselvi.abstractactivity.IActivity;
import br.com.csl.alunouniasselvi.controller.GlobalController;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SimuladorNotasActivity extends Activity implements IActivity, OnCheckedChangeListener {

    private ProgressDialog pd;
	private GlobalController control = new GlobalController();
	private EditText etredacao;
	private Spinner spnota2;
	private CheckBox cbnota2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simulador_notas);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		cbnota2.setOnCheckedChangeListener(this);
		spnota2.setVisibility(View.INVISIBLE);
	}

	private void init() {
		etredacao = (EditText) findViewById( R.id.et_redacao );
		spnota2 = (Spinner) findViewById(R.id.sp_2nota);
		cbnota2 = (CheckBox) findViewById(R.id.cb_2nota);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void bt_simular(View v){
		
		
		final double p1=1.0,p2=1.0,p3=3.2,p4=4.8;
		double redacao,res;
		int nota2;
		List<String> mensagens = new ArrayList<String>(); 
		
		if ( etredacao.getText().toString().equals("") || etredacao.getText().toString().equals(".") ) 
		{
			Toast.makeText(this, "Informe a nota da Redação", Toast.LENGTH_LONG).show();			
		}
		else
		{
		
			redacao = Double.parseDouble( etredacao.getText().toString() );
			
			double peso = p1+p2+p3+p4;
			
			redacao = redacao/(p1*peso);
			
			int pos = 0;
			
			if( !spnota2.isShown() )
			{
				for (int x=0; x<=10;x++)
				{
					for ( int y=0; y<=15;y++)
					{
						for ( int z=1; z<=3; z++)
						{
							res = redacao + (x/(p1*peso)) + (y*(p3/15)) + (z*(p4/3));
							if(res>6.6 && res<=6.7)
							{
								pos++;
								mensagens.add("Acertando "+x+" na 1ª objetiva, "+y+" na objetiva final e "+z+" na discursiva.");
								//System.out.println("Acertando "+x+" na 2ª semana, "+y+" na objetiva final e "+z+" na discursiva, voce passa. nota: "+res);					
								break;
							}	
						}						
					}
				}
			}
			else
			{
				if ( spnota2.getSelectedItem().toString().equals("") ) 
				{
					Toast.makeText(this, "Informe a nota da 2ª prova", Toast.LENGTH_LONG).show();			
				}
				else
				{
					nota2 = Integer.parseInt( spnota2.getSelectedItem().toString() );
					
					for ( int y=0; y<=15;y++)
					{
						for ( int z=1; z<=3; z++)
						{
							res = redacao + (nota2/(p1*peso)) + (y*(p3/15)) + (z*(p4/3));
							if(res>6.6 && res<=7.0)
							{
								pos++;
								mensagens.add("Acertando "+y+" na objetiva final e "+z+" na discursiva.");
								//System.out.println("Acertando "+x+" na 2ª semana, "+y+" na objetiva final e "+z+" na discursiva, voce passa. nota: "+res);					
								break;
							}	
						}						
					}				
				}
			}
	
			Toast.makeText(this, "Possibilidades: "+pos, Toast.LENGTH_LONG).show();
		}
		
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
		getIntent().putExtra("control", control);
		setResult(1, getIntent());
		super.finish();
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		if( arg0.getId() == cbnota2.getId() )
			if (arg1)
				spnota2.setVisibility(View.VISIBLE);
			else
				spnota2.setVisibility(View.INVISIBLE);
	}

}
