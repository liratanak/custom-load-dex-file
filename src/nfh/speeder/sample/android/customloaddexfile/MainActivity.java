package nfh.speeder.sample.android.customloaddexfile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView helloTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		helloTextView = (TextView) findViewById(R.id.textview);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		helloTextView.setText("Hello " + new nfh.speeder.sample.gradle.hello.Word().speeder());
	}
}
