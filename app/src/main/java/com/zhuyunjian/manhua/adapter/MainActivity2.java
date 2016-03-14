package com.zhuyunjian.manhua.adapter;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.zhuyunjian.manhua.R;

/**
 * @deprecated
 */
public class MainActivity2 extends Activity {

	private Spinner spinner;
	
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
//		//��ȡspinner
//		spinner=(Spinner)findViewById(R.id.spinner);
//		//��ȡ����
//
//		List<Map<String, Object>> spinnerdata3=Adapter.getspinner3data();
//		//����adapter
//
//		SimpleAdapter spinner3adAdapter=new SimpleAdapter(this, spinnerdata3, R.layout.spinner3_item, new String[]{"log"}, new int[]{R.id.image});
//		//��spinner���adapter
//		spinner.setAdapter(spinner3adAdapter);
//		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//					int position, long id) {
//
////				String nameString=((Map<String,Object>)spinner.getItemAtPosition(position)).get("listname").toString();
////
////
////				setTitle(nameString);
//
//
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//				// TODO Auto-generated method stub
//
//			}
//	});
//
	}

}