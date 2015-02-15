package br.com.csl.alunouniasselvi;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.csl.alunouniasselvi.abstractactivity.IActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ResultadoSimuladorActivity extends Activity implements IActivity {

    private ProgressDialog pd;
	private TextView tvlistaresultado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado_simulador);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		final Bundle extra = getIntent().getExtras();
		JSONObject possibilidades = null;
		try
		{
			possibilidades = new JSONObject( extra.getString("possibilidades") );
			for( int x=0; x< possibilidades.length();x++)
			{
				int pos = x+1;
				if( pos==1 )
					tvlistaresultado.setText( tvlistaresultado.getText()+"\n"+ possibilidades.getString(pos+"") );
				else
					tvlistaresultado.setText( tvlistaresultado.getText()+"\nou\n"+ possibilidades.getString(pos+"") );
			}
		}
		catch (JSONException e) {}
		
	}

	private void init(){
		tvlistaresultado = (TextView) findViewById(R.id.tv_lista_resultado);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
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

}
