package br.com.csl.alunouniasselvi;

import br.com.csl.alunouniasselvi.abstractactivity.IActivity;
import br.com.csl.alunouniasselvi.controller.GlobalController;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;

public class SeminariosInfoActivity extends Activity implements IActivity {

    private ProgressDialog pd;
	private GlobalController control;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_seminarios);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		final Bundle extra = getIntent().getExtras();
		control = (GlobalController) extra.getSerializable("control");		
		
	}

	private void init(){
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
		setResult(2, getIntent());
		super.finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if( resultCode == 1)
			control = (GlobalController) data.getSerializableExtra("control");
	}

}
