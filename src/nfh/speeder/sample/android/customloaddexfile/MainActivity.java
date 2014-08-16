package nfh.speeder.sample.android.customloaddexfile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import dalvik.system.DexClassLoader;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	TextView helloTextView;
	DexClassLoader dexClassLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		CustomizedDexClassLoader.setContext(this);

		try {
			dexClassLoader = CustomizedDexClassLoader.load("hello.dex");
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}

		helloTextView = (TextView) findViewById(R.id.textview);
	}

	@Override
	protected void onStart() {
		super.onStart();

		try {

			Class<?> wordClass = dexClassLoader.loadClass("nfh.speeder.sample.gradle.hello.Word");
			Method speederMethod = wordClass.getMethod("speeder");
			String speeder = (String) speederMethod.invoke(wordClass.newInstance());
			helloTextView.setText("Hello " + speeder);

			//The above 4 lines equals below
			//helloTextView.setText("Hello " + new nfh.speeder.sample.gradle.hello.Word().speeder());

		} catch (ClassNotFoundException e) {
		} catch (NoSuchMethodException e) {
		} catch (IllegalAccessException e) {
		} catch (IllegalArgumentException e) {
		} catch (InvocationTargetException e) {
		} catch (InstantiationException e) {
		}

	}
}
