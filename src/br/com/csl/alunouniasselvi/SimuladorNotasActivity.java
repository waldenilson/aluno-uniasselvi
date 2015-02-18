package br.com.csl.alunouniasselvi;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.csl.alunouniasselvi.abstractactivity.IActivity;
import br.com.csl.alunouniasselvi.controller.GlobalController;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SimuladorNotasActivity extends Activity implements IActivity, OnCheckedChangeListener {

    private ProgressDialog pd;
	private GlobalController control = new GlobalController();
	private EditText etredacao;
	private Spinner spnota2;
	private CheckBox cbnota2;
	private TextView tv1objetiva;
	private ArrayAdapter<String> aa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simulador_notas);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		cbnota2.setOnCheckedChangeListener(this);
		aa = new ArrayAdapter<String>(this, R.layout.spinner_item);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnota2.setAdapter(aa);
		aa.add("0");
		aa.add("1");
		aa.add("2");
		aa.add("3");
		aa.add("4");
		aa.add("5");
		aa.add("6");
		aa.add("7");
		aa.add("8");
		aa.add("9");
		aa.add("10");
		
		spnota2.setVisibility(View.INVISIBLE);
		tv1objetiva.setVisibility(View.INVISIBLE);
	}

	private void init() {
		etredacao = (EditText) findViewById( R.id.et_redacao );
		spnota2 = (Spinner) findViewById(R.id.sp_2nota);
		cbnota2 = (CheckBox) findViewById(R.id.cb_2nota);
		tv1objetiva = (TextView) findViewById(R.id.tv_1_objetiva);
	}

	public void bt_simular(View v) throws JSONException{
		
		
		final double p1=1.0,p2=1.0,p3=3.2,p4=4.8;
		double redacao,res;
		int nota2 = 0;
		JSONObject mensagens = new JSONObject();
		
		if ( etredacao.getText().toString().equals("") || etredacao.getText().toString().equals(".") ) 
		{
			Toast.makeText(this, getString(R.string.ale_nota_redacao), Toast.LENGTH_LONG).show();			
		}
		else
		{
		
			redacao = Double.parseDouble( etredacao.getText().toString() );
			
			if( redacao < 0 || redacao > 10)
			{
				Toast.makeText(this, getString(R.string.ale_nota_0_10), Toast.LENGTH_LONG).show();							
			}
			else
			{
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
									String qx1 = x+" questão", qx2 = x+" questões";
									String qy1 = y+" questão", qy2 = y+" questões";
									
									String str1 = "", str2 = "";
									if (x == 1) str1 = qx1; else if(x==0) str1 = "nenhuma questão"; else str1 = qx2;
									if (y == 1) str2 = qy1; else if(y==0) str2 = "nenhuma questão"; else str2 = qy2;
									mensagens.put(pos+"","Acertando "+str1+" na 1ª objetiva, "+str2+" na objetiva final e "+z+" na discursiva.");
									break;
								}	
							}						
						}
					}
					if ( pos > 0)
					{
						Intent data = new Intent(this, ResultadoSimuladorActivity.class);
						data.putExtra("redacao", redacao);
						data.putExtra("2nota", nota2);
						data.putExtra("possibilidades", mensagens.toString());
						data.putExtra("total", pos);
						startActivityForResult(data,1);		
					}
					else
						Toast.makeText(this, getString(R.string.ale_aprovacao_0), Toast.LENGTH_LONG).show();
				}
				else
				{
					if ( spnota2.getSelectedItem().toString().equals("") ) 
					{
						Toast.makeText(this, getString(R.string.ale_nota_2_prova), Toast.LENGTH_LONG).show();			
					}
					else
					{
						nota2 = Integer.parseInt( spnota2.getSelectedItem().toString() );
						
						for ( int y=0; y<=15;y++)
						{
							for ( int z=1; z<=3; z++)
							{
								res = redacao + (nota2/(p1*peso)) + (y*(p3/15)) + (z*(p4/3));
								if(res>6.6 && res<=6.8)
								{
									pos++;
									mensagens.put(pos+"","Acertando "+y+" questão(ôes) na objetiva final e "+z+" na discursiva.");
									break;
								}	
							}						
						}
						if ( pos > 0)
						{
							Intent data = new Intent(this, ResultadoSimuladorActivity.class);
							data.putExtra("redacao", redacao);
							data.putExtra("2nota", nota2);
							data.putExtra("possibilidades", mensagens.toString());
							data.putExtra("total", pos);
							startActivityForResult(data,1);		
						}
						else
							Toast.makeText(this, getString(R.string.ale_aprovacao_0), Toast.LENGTH_LONG).show();			
					}
				}			
			}
		}
		
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
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		if( arg0.getId() == cbnota2.getId() )
			if (arg1){
				spnota2.setVisibility(View.VISIBLE);
				tv1objetiva.setVisibility(View.VISIBLE);
			}else{
				spnota2.setVisibility(View.INVISIBLE);
				tv1objetiva.setVisibility(View.INVISIBLE);
			}
	}

}
