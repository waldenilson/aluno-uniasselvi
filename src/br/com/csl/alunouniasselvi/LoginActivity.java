package br.com.csl.alunouniasselvi;

import java.util.List;
import org.json.JSONObject;
import br.com.csl.alunouniasselvi.abstractactivity.IActivity;
import br.com.csl.alunouniasselvi.controller.GlobalController;
import br.com.csl.alunouniasselvi.controller.abstractcontroller.RestController;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements IActivity {

    private ProgressDialog pd;
	private GlobalController control;
	private EditText nome, senha;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		final Bundle extra = getIntent().getExtras();
		control = (GlobalController) getApplication();
	}

	private void init(){
		nome = (EditText) findViewById(R.id.et_login_nome);
		senha = (EditText) findViewById(R.id.et_login_senha);
	}

	public void bt_acessar(View v){
		
		List<Object> user = RestController.login(control.getKeyAccess(), nome.getText().toString(), senha.getText().toString());

		@SuppressWarnings("unchecked")
		List<JSONObject> lj = (List<JSONObject>) user.get(1);
		
		switch ( Integer.parseInt( user.get(0).toString() ) ) {
		case 99:
		{
			Toast.makeText(this, "logado: "+lj.toString(), Toast.LENGTH_LONG).show();
			break;
		}
		default:
			break;
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
		setResult(1, getIntent());
		super.finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if( resultCode == 1){
			control = (GlobalController) data.getSerializableExtra("control");
		}
	}

}
